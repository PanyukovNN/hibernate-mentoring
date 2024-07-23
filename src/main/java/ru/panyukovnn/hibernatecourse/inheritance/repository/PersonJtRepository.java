package ru.panyukovnn.hibernatecourse.inheritance.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.panyukovnn.hibernatecourse.inheritance.model.joinedtable.PersonJt;

import java.util.UUID;

public interface PersonJtRepository extends JpaRepository<PersonJt, UUID> {
}
