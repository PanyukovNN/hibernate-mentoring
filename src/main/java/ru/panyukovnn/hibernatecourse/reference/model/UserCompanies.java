package ru.panyukovnn.hibernatecourse.reference.model;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.UUID;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class UserCompanies {

    @EmbeddedId
    private UserCompaniesId id;

    @Getter
    @Setter
    @ToString
    @Embeddable
    @EqualsAndHashCode
    public static class UserCompaniesId implements Serializable {

        @Column(name = "user_id")
        private UUID userId;

        @Column(name = "company_id")
        private UUID companyId;
    }
}
