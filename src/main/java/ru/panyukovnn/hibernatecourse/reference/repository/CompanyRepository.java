package ru.panyukovnn.hibernatecourse.reference.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.panyukovnn.hibernatecourse.reference.model.Company;
import ru.panyukovnn.hibernatecourse.reference.model.User;

import java.util.List;
import java.util.UUID;

public interface CompanyRepository extends JpaRepository<Company, UUID> {

    @EntityGraph(attributePaths = {
        Company.Fields.country,
        Company.Fields.companyNames,
        Company.Fields.users + "." + User.Fields.address
    })
    List<Company> findAllByCompanyNamesNameIs(String uber, Pageable pageable);
}
