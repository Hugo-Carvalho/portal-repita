package com.repita.portalgerente.controller;

import com.repita.portalgerente.model.Usuario;
import com.repita.portalgerente.repository.UsuarioRepository;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class IndexController {
    
    @Autowired
    private UsuarioRepository usuarioRepository;

    @RequestMapping("/")
    public ModelAndView index(HttpSession session) {
        ModelAndView mv = new ModelAndView("faturamento/index");
        Usuario usuario = (Usuario) session.getAttribute("usuarioLogado");
        usuario = usuarioRepository.findByLogin(usuario.getUser(), usuario.getSenha());
        
        mv.addObject("tipoUsuario", usuario.getTipo());
        mv.addObject("usuario", usuario);
        return mv;
    }
}
