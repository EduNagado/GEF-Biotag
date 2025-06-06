package com.gef_biotag.Biotag.repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gef_biotag.Biotag.model.Abrigo;


public interface AbrigoRepository extends JpaRepository<Abrigo, Long> {
    Optional<Abrigo> findByNome(String nome);
}