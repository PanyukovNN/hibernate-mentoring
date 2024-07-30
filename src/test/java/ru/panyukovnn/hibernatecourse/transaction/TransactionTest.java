package ru.panyukovnn.hibernatecourse.transaction;

import org.junit.jupiter.api.Test;
import ru.panyukovnn.hibernatecourse.AbstractTest;

class TransactionTest extends AbstractTest {

    @Test
    void repeatableReadExample() {
        transactionExample.repeatableReadIsolationLevel();
    }

    @Test
    void noRollbackWithCheckedException() throws Exception {
        transactionExample.transactionWithCheckedException();
    }

    @Test
    void rollbackWithUncheckedException() {
        transactionExample.transactionWithUncheckedException();
    }

    @Test
    void rollbackWithCheckedException() throws Exception {
        transactionExample.correctTransactionWithCheckedException();
    }
}
