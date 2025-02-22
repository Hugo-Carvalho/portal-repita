package com.repita.portalgerente.repository;

import com.repita.portalgerente.model.CronogramaExecutando;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CronogramaExecutandoRepository extends JpaRepository<CronogramaExecutando, String> {

    List<CronogramaExecutando> findAll();
    CronogramaExecutando findById(Long id);
}
