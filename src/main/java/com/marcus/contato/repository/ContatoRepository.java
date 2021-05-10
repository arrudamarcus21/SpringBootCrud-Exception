package com.marcus.contato.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.marcus.contato.model.Contato;

public interface ContatoRepository extends JpaRepository<Contato, Long>{

}
