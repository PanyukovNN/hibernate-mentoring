package ru.panyukovnn.hibernatecourse.reference.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.panyukovnn.hibernatecourse.reference.model.UserCompanies;

public interface UserCompaniesRepository extends JpaRepository<UserCompanies, UserCompanies.UserCompaniesId> {

}
