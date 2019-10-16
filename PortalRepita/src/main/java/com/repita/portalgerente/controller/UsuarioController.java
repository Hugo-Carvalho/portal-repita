package com.repita.portalgerente.controller;

import com.repita.portalgerente.model.Usuario;
import com.repita.portalgerente.repository.UsuarioRepository;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class UsuarioController {
    
    @Autowired
    private UsuarioRepository usuarioRepository;
    
    @RequestMapping("/usuarios")
    public ModelAndView usuarios(HttpSession session) {
        Usuario usuarioLogado = (Usuario) session.getAttribute("usuarioLogado");
        usuarioLogado = usuarioRepository.findByLogin(usuarioLogado.getUser(), usuarioLogado.getSenha());
        
        if(usuarioLogado.getTipo().equals("admin")){
            ModelAndView mv = new ModelAndView("faturamento/usuarios");
            List<Usuario> usuarios = usuarioRepository.findAll();

            mv.addObject("tipoUsuario", usuarioLogado.getTipo());
            mv.addObject("usuarios", usuarios);
            return mv;
        } 
        
        ModelAndView mv = new ModelAndView("faturamento/index");
        
        mv.addObject("tipoUsuario", usuarioLogado.getTipo());
        mv.addObject("usuario", usuarioLogado);
        mv.addObject("msgErro", "Você não é um administrador");
        return mv;
    }
    
    @RequestMapping("/editarUsuario")
    public ModelAndView editarUsuario(Long id, HttpSession session) {
        Usuario usuarioLogado = (Usuario) session.getAttribute("usuarioLogado");
        usuarioLogado = usuarioRepository.findByLogin(usuarioLogado.getUser(), usuarioLogado.getSenha());
        
        if(usuarioLogado.getTipo().equals("admin")){
            ModelAndView mv = new ModelAndView("faturamento/editarUsuario");
            Usuario usuario = usuarioRepository.findById(id);

            mv.addObject("userUsuario", usuarioLogado.getUser());
            mv.addObject("tipoUsuario", usuarioLogado.getTipo());
            mv.addObject("usuario", usuario);

            return mv;
        }
        
        ModelAndView mv = new ModelAndView("faturamento/index");
        
        mv.addObject("tipoUsuario", usuarioLogado.getTipo());
        mv.addObject("usuario", usuarioLogado);
        mv.addObject("msgErro", "Você não é um administrador");
        return mv;
    }
    
    @RequestMapping("/adicionarUsuario")
    public ModelAndView adicionarUsuario(HttpSession session) {
        Usuario usuarioLogado = (Usuario) session.getAttribute("usuarioLogado");
        usuarioLogado = usuarioRepository.findByLogin(usuarioLogado.getUser(), usuarioLogado.getSenha());
        
        if(usuarioLogado.getTipo().equals("admin")){
            ModelAndView mv = new ModelAndView("faturamento/adicionarUsuario");
            Usuario newUsuario = new Usuario();

            mv.addObject("tipoUsuario", usuarioLogado.getTipo());

            mv.addObject("newUsuario", newUsuario);
            return mv;
        }
        
        ModelAndView mv = new ModelAndView("faturamento/index");
        
        mv.addObject("tipoUsuario", usuarioLogado.getTipo());
        mv.addObject("usuario", usuarioLogado);
        mv.addObject("msgErro", "Você não é um administrador");
        return mv;
    }
    
    @RequestMapping("/excluirUsuario")
    public String excluirUsuario(Long id, HttpSession session) {
        Usuario usuarioLogado = (Usuario) session.getAttribute("usuarioLogado");
        usuarioLogado = usuarioRepository.findByLogin(usuarioLogado.getUser(), usuarioLogado.getSenha());
        
        if(usuarioLogado.getTipo().equals("admin")){
            
            Usuario usuario = usuarioRepository.findById(id);
            usuarioRepository.delete(usuario);

            return "redirect:/usuarios";
        }
        
        return "redirect:/index";
    }
    
    @RequestMapping(value = "/saveUsuario", method = RequestMethod.POST)
    public String form(@Valid Usuario usuario, HttpSession session, HttpServletResponse response, BindingResult result, RedirectAttributes attributes) {
        Usuario usuarioLogado = (Usuario) session.getAttribute("usuarioLogado");
        usuarioLogado = usuarioRepository.findByLogin(usuarioLogado.getUser(), usuarioLogado.getSenha());
        
        if(usuarioLogado.getTipo().equals("admin")){
            usuarioRepository.save(usuario);
            return "redirect:/usuarios";
        }
        
        return "redirect:/index";
    }
}
