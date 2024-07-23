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
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String city;

    private String street;

    @ToString.Exclude
    @OneToOne(fetch = FetchType.LAZY)
    private User user;
}
