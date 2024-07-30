package ru.panyukovnn.hibernatecourse.transactions.service.locking;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.panyukovnn.hibernatecourse.transactions.model.Counter;
import ru.panyukovnn.hibernatecourse.transactions.repository.CounterRepository;
import ru.panyukovnn.hibernatecourse.util.TransactionalService;

import java.util.UUID;

import static ru.panyukovnn.hibernatecourse.util.SleepUtil.sleep;

@Service
@RequiredArgsConstructor
public class NoLockingExample {

    private final CounterRepository counterRepository;
    private final TransactionalService transactionalService;

    public void noLocking() throws InterruptedException {
        Counter counter = counterRepository.save(Counter.builder()
            .amount(100)
            .build());

        Thread thread1 = new Thread(() -> decrementCounter(counter.getId(), 75));
        thread1.start();

        Thread thread2 = new Thread(() -> decrementCounter(counter.getId(), 50));
        thread2.start();

        thread1.join();
        thread2.join();

        counterRepository.findById(counter.getId())
            .ifPresent(System.out::println);
    }

    private void decrementCounter(UUID counterId, int transactionAmount) {
        transactionalService.runInTransaction(() -> {
            Counter dbCounter = counterRepository.findById(counterId)
                .orElseThrow();

            sleep(1000);

            if (dbCounter.getAmount() > transactionAmount) {
                dbCounter.setAmount(dbCounter.getAmount() - transactionAmount);
            } else {
                throw new RuntimeException("Счётчик не может быть уменьшен");
            }

            counterRepository.save(dbCounter);
        });
    }

}
