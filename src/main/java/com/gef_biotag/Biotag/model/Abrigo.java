package com.gef_biotag.Biotag.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "abrigo")
@Entity
public class Abrigo {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "abrigo_seq")
    @SequenceGenerator(name = "abrigo_seq", sequenceName = "SEQ_ABRIGO", allocationSize = 1)
    private long abrigoId;

    private String nome;
    private String endereco;

    @OneToMany(mappedBy = "abrigo")
    @JsonManagedReference
    private List<Paciente> usuarios= new ArrayList<>();

}
