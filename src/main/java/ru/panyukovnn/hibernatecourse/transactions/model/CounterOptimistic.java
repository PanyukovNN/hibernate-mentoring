package ru.panyukovnn.hibernatecourse.transactions.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OptimisticLocking;

import java.util.UUID;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "counter")
@OptimisticLocking
public class CounterOptimistic {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private UUID clientId;

    private Integer amount;

    @Version
    private Long version;

}
