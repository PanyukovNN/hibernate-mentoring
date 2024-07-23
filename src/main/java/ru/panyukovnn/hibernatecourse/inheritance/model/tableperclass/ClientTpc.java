package ru.panyukovnn.hibernatecourse.inheritance.model.tableperclass;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class ClientTpc extends PersonTpc {

    private String clientType;
    private String cardNumber;
}
