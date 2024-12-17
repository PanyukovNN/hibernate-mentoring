package ru.panyukovnn.hibernatecourse.transaction;

import org.junit.jupiter.api.Test;
import ru.panyukovnn.hibernatecourse.AbstractTest;

public class LockingTest extends AbstractTest {

    @Test
    void noLocking() throws InterruptedException {
        noLockingExample.noLocking();
    }

    @Test
    void optimisticReadLocking() throws InterruptedException {
        optimisticLockExample.optimisticReadLocking();
    }

    @Test
    void optimisticWriteLocking() throws InterruptedException {
        optimisticLockExample.optimisticWriteLocking();
    }

    @Test
    void optimisticLockingWithRetry() throws InterruptedException {
        optimisticLockExample.optimisticLockingWithRetry();
    }

    @Test
    void pessimisticReadLocking() throws InterruptedException {
        // Возникает дедлок, одна из транзакций откатывается

        pessimisticLockExample.pessimisticReadLocking();
    }

    @Test
    void pessimisticWriteLocking() throws InterruptedException {
        // Ошибок не возникнет

        pessimisticLockExample.pessimisticWriteLocking();
    }
}
