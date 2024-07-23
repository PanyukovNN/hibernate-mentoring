package ru.panyukovnn.hibernatecourse.inheritance.model.singletable;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@DiscriminatorColumn(name = "person_type") // по умолчанию будет иметь наименование DTYPE
public class PersonSt {

    // Обратите внимание что personType в полях сущности не указывается

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String firstName;
    private String lastName;
    private String sex;
    private LocalDate birthDate;
}
