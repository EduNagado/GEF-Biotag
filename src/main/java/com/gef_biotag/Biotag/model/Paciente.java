package com.gef_biotag.Biotag.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "paciente")
public class Paciente {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "paciente_seq")
    @SequenceGenerator(name = "paciente_seq", sequenceName = "SEQ_PACIENTE", allocationSize = 1)
    private long id;

    private String nome;
    private int idade;
    private String endereco;
    
    // @ManyToOne
    // @JsonBackReference
    // @JoinColumn(name = "abrigo_id", nullable = false)
    // private Abrigo abrigo;

    // @OneToOne
    // @JsonBackReference
    // @JoinColumn(name = "pulseira_id", nullable = false)
    // private Pulseira pulseira;

    // public void atualizarInformacoesPaciente(DadosAtualizadoPaciente dadosPaciente) {
    //     if (dadosPaciente.nome() != null && !dadosPaciente.nome().isBlank()) {
    //         this.nome = dadosPaciente.nome();
    //     }
    //     if (dadosPaciente.idade() != null) {
    //         this.idade = dadosPaciente.idade();
    //     }
    //     if (dadosPaciente.endereco() != null && !dadosPaciente.endereco().isBlank()) {
    //         this.endereco = dadosPaciente.endereco();
    //     }
    // }

}