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
public class CountryName {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    private Country country;

    @Enumerated(value = EnumType.STRING)
    private Lang lang;

}
