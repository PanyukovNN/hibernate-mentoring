package ru.panyukovnn.hibernatecourse.inheritance.model.tableperclass;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class EmployeeTpc extends PersonTpc {

    private String department;
    private String position;
    private BigDecimal salary;
}
