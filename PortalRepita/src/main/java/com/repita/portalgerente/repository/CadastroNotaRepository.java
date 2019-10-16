package com.repita.portalgerente.repository;

import com.repita.portalgerente.model.CadastroNota;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CadastroNotaRepository extends JpaRepository<CadastroNota, String>{
	CadastroNota findById(Long id);
        List<CadastroNota> findByAllNotaNaoAprovAndNaoAtrib();
        List<CadastroNota> findByAllNotaNaoAprovAndAtrib();
        List<CadastroNota> findByAllNotaNaoAprovAndAtribByUser(String user);
        List<CadastroNota> findByAllNotaNaoAprov();
        List<CadastroNota> findByAllNotaAprov();
        List<CadastroNota> findByAllNotaRealiz();
}
