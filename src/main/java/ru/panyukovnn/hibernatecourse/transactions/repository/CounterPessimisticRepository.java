
package ru.panyukovnn.hibernatecourse.transactions.repository;

import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import ru.panyukovnn.hibernatecourse.transactions.model.Counter;

import java.util.Optional;
import java.util.UUID;

public interface CounterPessimisticRepository extends JpaRepository<Counter, UUID> {

    @Lock(LockModeType.PESSIMISTIC_READ)
    Optional<Counter> findPessimisticById(UUID id);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    Optional<Counter> findPessimisticForceIncrementById(UUID id);

}
