package ru.panyukovnn.hibernatecourse.transactions.service.locking;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.panyukovnn.hibernatecourse.transactions.model.CounterOptimistic;
import ru.panyukovnn.hibernatecourse.transactions.repository.CounterOptimisticRepository;
import ru.panyukovnn.hibernatecourse.util.RetryService;
import ru.panyukovnn.hibernatecourse.util.TransactionalService;

import java.util.UUID;

import static ru.panyukovnn.hibernatecourse.util.SleepUtil.sleep;

@Service
@RequiredArgsConstructor
public class OptimisticLockExample {

    private final RetryService retryService;
    private final TransactionalService transactionalService;
    private final CounterOptimisticRepository counterRepository;

    public void optimisticReadLocking() throws InterruptedException {
        CounterOptimistic counter = counterRepository.save(CounterOptimistic.builder()
            .amount(100)
            .build());

        Thread thread1 = new Thread(() -> decrementCounter(counter.getId(), 75, 1000));
        thread1.start();

        Thread thread2 = new Thread(() -> decrementCounter(counter.getId(), 50, 500));
        thread2.start();

        thread1.join();
        thread2.join();

        counterRepository.findById(counter.getId())
            .ifPresent(System.out::println);
    }

    private void decrementCounter(UUID counterId, int transactionAmount, int sleepMs) {
        transactionalService.runInTransaction(() -> {
            CounterOptimistic dbCounter = counterRepository.findOptimisticById(counterId)
                .orElseThrow();

            sleep(sleepMs);

            if (dbCounter.getAmount() > transactionAmount) {
                dbCounter.setAmount(dbCounter.getAmount() - transactionAmount);
            } else {
                throw new RuntimeException("Счётчик не может быть уменьшен");
            }

            counterRepository.save(dbCounter);
        });
    }

    // --------------------------------

    public void optimisticWriteLocking() throws InterruptedException {
        CounterOptimistic counter = counterRepository.save(CounterOptimistic.builder()
            .amount(100)
            .build());

        Thread thread1 = new Thread(() -> findForceIncrement(counter));
        thread1.start();

        Thread thread2 = new Thread(() -> findForceIncrement(counter));
        thread2.start();

        thread1.join();
        thread2.join();

        transactionalService.runInTransaction(() ->
            counterRepository.findById(counter.getId())
                .ifPresent(System.out::println));
    }

    private void findForceIncrement(CounterOptimistic counter) {
        transactionalService.runInTransaction(() -> {
            counterRepository.findOptimisticForceIncrementById(counter.getId())
                .orElseThrow();

            sleep(500);
        });
    }

    // --------------------------------

    public void optimisticLockingWithRetry() throws InterruptedException {
        CounterOptimistic counter = counterRepository.save(CounterOptimistic.builder()
            .amount(100)
            .build());

        Thread thread1 = new Thread(() -> decrementCounterWithRetry(counter.getId(), 75, 1000));
        thread1.start();

        Thread thread2 = new Thread(() -> decrementCounterWithRetry(counter.getId(), 50, 500));
        thread2.start();

        thread1.join();
        thread2.join();

        counterRepository.findById(counter.getId())
            .ifPresent(System.out::println);
    }

    private void decrementCounterWithRetry(UUID counterId, int transactionAmount, int sleepMs) {
        retryService.runWithRetry(() ->
            decrementCounter(counterId, transactionAmount, sleepMs));
    }
}
