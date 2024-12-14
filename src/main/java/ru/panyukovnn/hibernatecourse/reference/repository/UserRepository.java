package ru.panyukovnn.hibernatecourse.reference.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.panyukovnn.hibernatecourse.reference.model.Company;
import ru.panyukovnn.hibernatecourse.reference.model.User;

import java.util.List;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {

    @EntityGraph(attributePaths = {
        "address",
        "companies.country",
        "companies.companyNames"
    })
    List<User> findAllBy();

    @EntityGraph(attributePaths = {
        "address",
        "companies.country",
        "companies.companyNames"
    })
    @Query("""
        FROM User u
        """)
    List<User> findAllWithJoinFetch();
}
