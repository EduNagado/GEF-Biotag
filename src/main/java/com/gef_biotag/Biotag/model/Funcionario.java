package com.gef_biotag.Biotag.model;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "funcionario")
public class Funcionario implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "funcionario_seq")
    @SequenceGenerator(name = "funcionario_seq", sequenceName = "SEQ_FUNCIONARIO", allocationSize = 1)
    private Long id;

    private String nome;
    private String email;
    private String password;
    
    @Enumerated(EnumType.STRING)
    private Cargo cargo;

    @ManyToOne
    @JoinColumn(name = "ABRIGO_ID", nullable = false)
    @JsonIgnore
    private Abrigo abrigo;

    // public void atualizarInformacoes(DadosAtualizadoFuncionario dados) {
    //     if (dados.nome() != null && !dados.nome().isBlank()) {
    //         this.nome = dados.nome();
    //     }
    //     if (dados.cargo() != null) {
    //         this.cargo = dados.cargo();
    //     }
    //     if (dados.password() != null && !dados.password().isBlank()) {
    //         this.password = dados.password();
    //     }
    //     if (dados.email() != null && !dados.email().isBlank()) {
    //         this.email = dados.email();
    //     }
    // } 


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_" + cargo.name()));
    }

    @Override
    public String getUsername() {
        return email; 
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}