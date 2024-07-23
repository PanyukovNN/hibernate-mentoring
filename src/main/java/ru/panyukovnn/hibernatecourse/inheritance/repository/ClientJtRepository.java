package ru.panyukovnn.hibernatecourse.inheritance.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.panyukovnn.hibernatecourse.inheritance.model.joinedtable.ClientJt;

import java.util.UUID;

public interface ClientJtRepository extends JpaRepository<ClientJt, UUID> {
}
