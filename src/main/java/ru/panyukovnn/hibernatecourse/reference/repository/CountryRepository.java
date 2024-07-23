package ru.panyukovnn.hibernatecourse.reference.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.panyukovnn.hibernatecourse.reference.model.Country;

import java.util.UUID;

public interface CountryRepository extends JpaRepository<Country, UUID> {
}
