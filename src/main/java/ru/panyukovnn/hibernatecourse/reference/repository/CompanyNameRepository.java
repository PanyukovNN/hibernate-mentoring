package ru.panyukovnn.hibernatecourse.reference.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.panyukovnn.hibernatecourse.reference.model.CompanyName;

import java.util.UUID;

public interface CompanyNameRepository extends JpaRepository<CompanyName, UUID> {

}
