package com.repita.portalgerente.controller;

import com.repita.portalgerente.model.Usuario;
import com.repita.portalgerente.repository.UsuarioRepository;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class LoginController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @RequestMapping("/login")
    public ModelAndView login() {
        ModelAndView mv = new ModelAndView("faturamento/login");
        Usuario usuario = new Usuario();
        mv.addObject("usuario", usuario);
        return mv;
    }

    @RequestMapping(value = "/logar", method = RequestMethod.POST)
    public ModelAndView form(@ModelAttribute(name="usuario") Usuario usuario, HttpSession session, BindingResult result, RedirectAttributes attributes) {

        if (usuarioRepository.findByLogin(usuario.getUser(), usuario.getSenha()) != null) {
            session.setAttribute("usuarioLogado", usuario);
            ModelAndView mv = new ModelAndView("faturamento/index");
            usuario = usuarioRepository.findByLogin(usuario.getUser(), usuario.getSenha());
            mv.addObject("usuario", usuario);
            
            mv.addObject("tipoUsuario", usuario.getTipo());
            return mv;
        }

        ModelAndView mv = new ModelAndView("faturamento/login");
        mv.addObject("msgErro", "Usuario ou senha inválidos!");
        return mv;
    }
    
    @RequestMapping("/logout")
    public String logout(HttpSession session) {
        
        session.removeAttribute("usuarioLogado");
        return "redirect:/login";
    }
}
