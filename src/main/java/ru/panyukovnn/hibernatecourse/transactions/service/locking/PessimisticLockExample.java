package ru.panyukovnn.hibernatecourse.transactions.service.locking;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.panyukovnn.hibernatecourse.transactions.model.Counter;
import ru.panyukovnn.hibernatecourse.transactions.repository.CounterPessimisticRepository;
import ru.panyukovnn.hibernatecourse.util.TransactionalService;

import java.util.UUID;

import static ru.panyukovnn.hibernatecourse.util.SleepUtil.sleep;

@Service
@RequiredArgsConstructor
public class PessimisticLockExample {

    private final CounterPessimisticRepository counterRepository;
    private final TransactionalService transactionalService;

    public void pessimisticReadLocking() throws InterruptedException {
        Counter counter = counterRepository.save(Counter.builder()
            .amount(100)
            .build());

        Thread thread1 = new Thread(() -> decrementCounter(counter.getId(), 75, 1000));
        thread1.start();

        Thread thread2 = new Thread(() -> decrementCounter(counter.getId(), 50, 1000));
        thread2.start();

        thread1.join();
        thread2.join();

        counterRepository.findById(counter.getId())
            .ifPresent(System.out::println);
    }

    private void decrementCounter(UUID counterId, int transactionAmount, int sleepMs) {
        transactionalService.runInTransaction(() -> {
            Counter dbCounter = counterRepository.findPessimisticById(counterId)
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

    public void pessimisticWriteLocking() throws InterruptedException {
        Counter counter = counterRepository.save(Counter.builder()
            .amount(100)
            .build());

        Thread thread1 = new Thread(() -> decrementCounterWrite(counter.getId(), 50, 500));
        thread1.start();

        Thread thread2 = new Thread(() -> decrementCounterWrite(counter.getId(), 75, 1000));
        thread2.start();

        thread1.join();
        thread2.join();

        transactionalService.runInTransaction(() ->
            counterRepository.findById(counter.getId())
                .ifPresent(System.out::println));
    }

    private void decrementCounterWrite(UUID counterId, int transactionAmount, int sleepMs) {
        transactionalService.runInTransaction(() -> {
            Counter dbCounter = counterRepository.findPessimisticForceIncrementById(counterId)
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

}
