package com.repita.portalgerente.controller;

import com.repita.portalgerente.model.CronogramaExecutando;
import com.repita.portalgerente.model.Usuario;
import com.repita.portalgerente.repository.CronogramaExecutandoRepository;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CronogramaExecutandoController {
    
    @Autowired
    private CronogramaExecutandoRepository cronogramaExecutandoRepository;

    @RequestMapping("/cronogramasExecutando")
    public ModelAndView cronogramasExecutando(HttpSession session) {
        
        ModelAndView mv = new ModelAndView("portalrepita/cronogramasExecutando");

        List<CronogramaExecutando> cronogramasExecutando = cronogramaExecutandoRepository.findAll();
        Usuario usuarioLogado = (Usuario) session.getAttribute("usuarioLogado");
        
        mv.addObject("usuarioLogado", usuarioLogado);
        mv.addObject("cronogramasExecutando", cronogramasExecutando);

        return mv;
    }

    @RequestMapping("/visualizarCronogramaExecutando")
    public ModelAndView visualizarCronogramaExecutando(Long id, HttpSession session) {
        
        ModelAndView mv = new ModelAndView("portalrepita/visualizarCronogramaExecutando");

        CronogramaExecutando cronogramaExecutando = cronogramaExecutandoRepository.findById(id);
        Usuario usuarioLogado = (Usuario) session.getAttribute("usuarioLogado");
        
        mv.addObject("usuarioLogado", usuarioLogado);
        mv.addObject("cronogramaExecutando", cronogramaExecutando);

        return mv;
    }
}
