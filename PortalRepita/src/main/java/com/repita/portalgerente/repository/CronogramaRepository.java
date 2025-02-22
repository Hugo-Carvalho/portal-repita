package com.repita.portalgerente.repository;

import com.repita.portalgerente.model.Cronograma;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CronogramaRepository extends JpaRepository<Cronograma, String> {

    List<Cronograma> findAll();
    Cronograma findById(Long id);
    List<Cronograma> findByHoraInicio(String horaInicio);
}