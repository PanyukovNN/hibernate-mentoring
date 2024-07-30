package ru.panyukovnn.hibernatecourse.util;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.function.Supplier;

@Service
@RequiredArgsConstructor
public class TransactionalService {

    @Transactional(timeout = 3000)
    public void runInTransaction(Runnable runnable) {
        runnable.run();
    }

    @Transactional(timeout = 3000)
    public <T> T callInTransaction(Supplier<T> supplier) {
        return supplier.get();
    }

}
