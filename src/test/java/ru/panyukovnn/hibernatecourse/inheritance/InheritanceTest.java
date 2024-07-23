package ru.panyukovnn.hibernatecourse.inheritance;

import org.junit.jupiter.api.Test;
import ru.panyukovnn.hibernatecourse.AbstractTest;
import ru.panyukovnn.hibernatecourse.inheritance.model.joinedtable.ClientJt;

public class InheritanceTest extends AbstractTest {

    @Test
    void createClientJt() {
        ClientJt clientJt = ClientJt.builder()
            .firstName("John")
            .sex("m")
            .clientType("regular")
            .cardNumber("123")
            .build();

        clientJtRepository.save(clientJt);

        System.out.println(clientJtRepository.findById(clientJt.getId()));
    }

    @Test
    void getPersonJt() {
        personJtRepository.findAll();
    }
}
