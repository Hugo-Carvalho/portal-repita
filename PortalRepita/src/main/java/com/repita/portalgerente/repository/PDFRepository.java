package com.repita.portalgerente.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.repita.portalgerente.model.PDF;
import java.util.List;

public interface PDFRepository extends JpaRepository<PDF, String>{
	PDF findById(Long id);
        List<PDF> findAllPDF(Long id);
}
