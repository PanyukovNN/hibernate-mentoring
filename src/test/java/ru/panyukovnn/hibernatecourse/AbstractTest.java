package ru.panyukovnn.hibernatecourse;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.support.TransactionTemplate;
import ru.panyukovnn.hibernatecourse.inheritance.repository.ClientJtRepository;
import ru.panyukovnn.hibernatecourse.inheritance.repository.PersonJtRepository;
import ru.panyukovnn.hibernatecourse.reference.repository.*;
import ru.panyukovnn.hibernatecourse.transactions.repository.CounterRepository;
import ru.panyukovnn.hibernatecourse.transactions.service.locking.NoLockingExample;
import ru.panyukovnn.hibernatecourse.transactions.service.locking.OptimisticLockExample;
import ru.panyukovnn.hibernatecourse.transactions.service.locking.PessimisticLockExample;
import ru.panyukovnn.hibernatecourse.transactions.service.transactions.TransactionExample;
import ru.panyukovnn.hibernatecourse.util.TransactionalService;

@SpringBootTest
public class AbstractTest {

    @PersistenceContext
    protected EntityManager entityManager;

    @Autowired
    protected TransactionTemplate transactionTemplate;

    @Autowired
    protected PersonJtRepository personJtRepository;

    @Autowired
    protected ClientJtRepository clientJtRepository;

    @Autowired
    protected UserRepository userRepository;

    @Autowired
    protected CompanyRepository companyRepository;

    @Autowired
    protected CountryRepository countryRepository;

    @Autowired
    protected CompanyNameRepository companyNameRepository;

    @Autowired
    protected UserCompaniesRepository userCompaniesRepository;

    @Autowired
    protected AddressRepository addressRepository;

    @Autowired
    protected TransactionalService transactionalService;

    @Autowired
    protected TransactionExample transactionExample;

    @Autowired
    protected CounterRepository counterRepository;

    @Autowired
    protected NoLockingExample noLockingExample;

    @Autowired
    protected OptimisticLockExample optimisticLockExample;

    @Autowired
    protected PessimisticLockExample pessimisticLockExample;
}
