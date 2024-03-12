package com.rodrigues.propappstudy.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Proposal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double requestedAmount;
    private int paymentTerm;
    private Boolean isApproved;
    private boolean isIntegrated;
    private String observation;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "id_user")
    private User user;
}
