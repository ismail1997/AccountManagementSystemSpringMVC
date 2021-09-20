package com.ismail.accountsystemspringmvc.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

@Entity @Data @NoArgsConstructor
@AllArgsConstructor @ToString
public class Reclamation implements Serializable {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private String reclamationMessage;
    private LocalDate reclamationDate;
    private boolean answered;
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;
    private String answer;
}
