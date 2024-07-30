package ru.panyukovnn.hibernatecourse.transactions.repository;

import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import ru.panyukovnn.hibernatecourse.transactions.model.CounterOptimistic;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CounterOptimisticRepository extends JpaRepository<CounterOptimistic, UUID> {

    @Lock(LockModeType.OPTIMISTIC)
    Optional<CounterOptimistic> findOptimisticById(UUID id);

    @Lock(LockModeType.OPTIMISTIC_FORCE_INCREMENT)
    Optional<CounterOptimistic> findOptimisticForceIncrementById(UUID id);

    List<CounterOptimistic> findAll();
}
