package com.repita.portalgerente.controller;

import com.repita.portalgerente.model.Usuario;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class IndexController {

    @RequestMapping("/")
    public ModelAndView index(HttpSession session) {
        ModelAndView mv = new ModelAndView("portalrepita/index");
        Usuario usuarioLogado = (Usuario) session.getAttribute("usuarioLogado");
        
        mv.addObject("usuarioLogado", usuarioLogado);
        return mv;
    }
}
