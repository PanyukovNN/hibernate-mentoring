package ru.panyukovnn.hibernatecourse.reference.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class CompanyName {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String name;

    @ManyToOne
    @ToString.Exclude
    private Company company;

    @Enumerated(value = EnumType.STRING)
    private Lang lang;

}
