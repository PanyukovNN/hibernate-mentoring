package ru.panyukovnn.hibernatecourse.transactions.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.panyukovnn.hibernatecourse.transactions.model.Counter;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CounterRepository extends JpaRepository<Counter, UUID> {

    Optional<Counter> findById(UUID id);

    List<Counter> findAll();
}
