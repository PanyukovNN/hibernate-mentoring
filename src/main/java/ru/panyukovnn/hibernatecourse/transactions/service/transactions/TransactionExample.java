package ru.panyukovnn.hibernatecourse.transactions.service.transactions;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import ru.panyukovnn.hibernatecourse.transactions.model.Counter;
import ru.panyukovnn.hibernatecourse.transactions.repository.CounterRepository;
import ru.panyukovnn.hibernatecourse.util.TransactionalService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TransactionExample {

    private final CounterRepository counterRepository;
    private final TransactionalService transactionalService;

    @jakarta.transaction.Transactional
    public void jakartaTransaction() {
    }

    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public void repeatableReadIsolationLevel() {
        transactionalService.runInTransaction(() -> {
            counterRepository.findAll();
        });

        List<Counter> counters = counterRepository.findAll();

        // doSomething

        List<Counter> counters2 = counterRepository.findAll();

        // doSomething
    }

    @Transactional
    public void transactionWithCheckedException() throws Exception {
        counterRepository.save(Counter.builder()
            .amount(111)
            .build());

        throw new Exception();
    }

    @Transactional
    public void transactionWithUncheckedException() {
        counterRepository.save(Counter.builder()
            .amount(222)
            .build());

        throw new RuntimeException();
    }

    @Transactional(rollbackFor = Exception.class)
    public void correctTransactionWithCheckedException() throws Exception {
        counterRepository.save(Counter.builder()
            .amount(333)
            .build());

        throw new Exception();
    }
}
