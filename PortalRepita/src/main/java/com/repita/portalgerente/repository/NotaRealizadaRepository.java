package com.repita.portalgerente.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.repita.portalgerente.model.NotaRealizada;

public interface NotaRealizadaRepository extends JpaRepository<NotaRealizada, String>{
	NotaRealizada findById(Long id);
}
