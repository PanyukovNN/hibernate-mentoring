package ru.panyukovnn.hibernatecourse.reference;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import ru.panyukovnn.hibernatecourse.AbstractTest;
import ru.panyukovnn.hibernatecourse.reference.model.*;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@Slf4j
class ReferenceTest extends AbstractTest {

    @Test
    @Transactional
    void oneToOneLazyEager() {
        List<Address> all = addressRepository.findAll();

        all.forEach(it -> log.info(it.toString()));
    }

    @Test
    @Transactional
    void oneToOneLazyEager_doesNotWork() {
        userRepository.findAll()
            .forEach(it -> log.info(it.toString()));
    }

    @Test
    @Transactional // требуется транзакция из-за дополнительных запросов при выводе в консоль
    void nPlusOneProblem() {
        // Добавить FetchType.EAGER над user#companies

        List<User> all = userRepository.findAll();

        all.forEach(it -> log.info(it.toString()));
    }

    @Test
    void joinFetchSolution() {
        userRepository.findAllWithJoinFetch()
            .forEach(it -> log.info(it.toString()));
    }

    @Test
    @Transactional
    void entityGraphSolution() {
        userRepository.findAllBy()
            .forEach(it -> log.info(it.toString()));
    }

    @Test
    void fetchEager() {
        // Выставить FetchType.EAGER над company#companyName

        companyRepository.findById(UUID.fromString("37140dc9-6ab4-450d-8757-38e2e708d760"))
            .ifPresent(System.out::println);
    }

    @Test
    void cascade() {
        // Добавить CascadeType.PERSIST в company#companyName

        CompanyName companyName = CompanyName.builder()
            .lang(Lang.RU)
            .name("Убер")
            .build();
        Company company = Company.builder()
            .companyNames(Set.of(companyName))
            .build();

        companyName.setCompany(company);

        companyRepository.save(company);
    }

    @Test
    void cascadeAll() {
        // Добавить CascadeType.ALL в company#companyName и в companyName#company

        CompanyName companyNameRu = CompanyName.builder()
            .lang(Lang.RU)
            .name("Убер")
            .build();
        CompanyName companyNameEn = CompanyName.builder()
            .lang(Lang.EN)
            .name("Uber")
            .build();
        Company company = Company.builder()
            .companyNames(Set.of(companyNameRu, companyNameEn))
            .build();

        companyNameRu.setCompany(company);
        companyNameEn.setCompany(company);

        companyRepository.save(company);

        companyNameRepository.delete(companyNameRu);
    }

    @Test
    @Transactional
    void noCascade() {
        // Убрать cascade

        CompanyName companyName = CompanyName.builder()
            .lang(Lang.RU)
            .name("Убер")
            .build();
        Company company = Company.builder()
            .companyNames(Set.of(companyName))
            .build();

        companyName.setCompany(company);

        companyRepository.save(company);
        companyNameRepository.save(companyName);
    }

    @Test
    void entityGraphLimitAndOffsetDoesNotWork() {
        Pageable pageRequest = PageRequest.of(1, 2, Sort.unsorted());

        companyRepository.findAllByCompanyNamesNameIs("Huawei", pageRequest) // Не отработает limit и offset
            .forEach(System.out::println);
    }

}