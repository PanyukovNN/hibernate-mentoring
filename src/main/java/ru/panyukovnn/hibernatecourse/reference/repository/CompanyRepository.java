package ru.panyukovnn.hibernatecourse.reference.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.panyukovnn.hibernatecourse.reference.model.Company;

import java.util.UUID;

public interface CompanyRepository extends JpaRepository<Company, UUID> {

}
