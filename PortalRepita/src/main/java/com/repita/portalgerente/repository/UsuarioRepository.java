package com.repita.portalgerente.repository;

import com.repita.portalgerente.model.Usuario;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, String>{
    List <Usuario> findAll();
    Usuario findById(Long id);
    Usuario findByLogin(String user, String senha);
}