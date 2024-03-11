package com.rodrigues.propappstudy.entity;

import jakarta.persistence.*;

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

    @OneToOne
    @JoinColumn(name = "id_user")
    private User user;
}
