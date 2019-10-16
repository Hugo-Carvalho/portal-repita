package com.repita.portalgerente.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.repita.portalgerente.model.NotaAprovada;

public interface NotaAprovadaRepository extends JpaRepository<NotaAprovada, String>{
	NotaAprovada findById(Long id);
}
