package com.gef_biotag.Biotag.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.gef_biotag.Biotag.model.Paciente;
import com.gef_biotag.Biotag.model.Pulseira;

public interface PacienteRepository extends JpaRepository<Paciente, Long> {
    Page<Paciente> findByNomeContainingIgnoreCase(String nome, Pageable pageable);

    Optional<Paciente> findByPulseira(Pulseira pulseira);
}