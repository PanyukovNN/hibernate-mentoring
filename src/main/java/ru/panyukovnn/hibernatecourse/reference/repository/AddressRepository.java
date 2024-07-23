package ru.panyukovnn.hibernatecourse.reference.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.panyukovnn.hibernatecourse.reference.model.Address;

import java.util.UUID;

public interface AddressRepository extends JpaRepository<Address, UUID> {
}
