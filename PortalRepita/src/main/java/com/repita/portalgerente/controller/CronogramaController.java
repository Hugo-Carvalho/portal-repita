package com.repita.portalgerente.controller;

import com.repita.portalgerente.model.Cronograma;
import com.repita.portalgerente.model.Usuario;
import com.repita.portalgerente.repository.CronogramaRepository;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
public class CronogramaController {

    @Autowired
    private CronogramaRepository cronogramaRepository;

    @RequestMapping("/cronogramas")
    public ModelAndView cronogramas(HttpSession session) {

        ModelAndView mv = new ModelAndView("portalrepita/cronogramas");

        List<Cronograma> cronogramas = cronogramaRepository.findAll();
        Usuario usuario = (Usuario) session.getAttribute("usuarioLogado");

        mv.addObject("usuario", usuario);
        mv.addObject("cronogramas", cronogramas);

        return mv;
    }

    @RequestMapping("/editarCronograma")
    public ModelAndView editarCronograma(Long id, HttpSession session) {

        ModelAndView mv = new ModelAndView("portalrepita/editarCronograma");

        Cronograma cronograma = cronogramaRepository.findById(id);
        Usuario usuario = (Usuario) session.getAttribute("usuarioLogado");

        mv.addObject("usuario", usuario);
        mv.addObject("cronograma", cronograma);
        return mv;
    }

    @RequestMapping("/adicionarCronograma")
    public ModelAndView adicionarCronograma(HttpSession session) {

        ModelAndView mv = new ModelAndView("portalrepita/adicionarCronograma");

        Cronograma newCronograma = new Cronograma();
        Usuario usuario = (Usuario) session.getAttribute("usuarioLogado");

        mv.addObject("usuario", usuario);
        mv.addObject("newCronograma", newCronograma);
        return mv;
    }

    @RequestMapping(value = "/saveCronograma", method = RequestMethod.POST)
    public ModelAndView form(@Valid Cronograma cronograma, HttpSession session, HttpServletResponse response, BindingResult result, RedirectAttributes attributes) {

        cronogramaRepository.save(cronograma);

        try {
            response.sendRedirect("/cronogramas");
        } catch (IOException ex) {
            Logger.getLogger(CronogramaController.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    @RequestMapping("/excluirCronograma")
    public String excluirCronograma(Long id) {

        cronogramaRepository.delete(cronogramaRepository.findById(id));

        return "redirect:/cronogramas";
    }
}
