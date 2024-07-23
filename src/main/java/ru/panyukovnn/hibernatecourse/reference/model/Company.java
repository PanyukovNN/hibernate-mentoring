package ru.panyukovnn.hibernatecourse.reference.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldNameConstants;

import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@FieldNameConstants
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

//    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    private Country country;

    @ToString.Exclude
    @OneToMany(mappedBy = "company")
    private Set<CompanyName> companyNames;

    @ManyToMany
    @JoinTable(
        name = "user_companies",
        joinColumns = @JoinColumn(name = "company_id"),
        inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    @ToString.Exclude
    private Set<User> users;

}
