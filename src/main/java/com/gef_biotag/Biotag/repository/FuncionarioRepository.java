package com.gef_biotag.Biotag.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gef_biotag.Biotag.model.Funcionario;


public interface FuncionarioRepository extends JpaRepository<Funcionario, Long>{
      Optional<Funcionario> findByEmail(String email);
      
}

