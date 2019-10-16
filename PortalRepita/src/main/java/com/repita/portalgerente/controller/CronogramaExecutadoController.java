package com.repita.portalgerente.controller;

import com.repita.portalgerente.model.CronogramaExecutado;
import com.repita.portalgerente.model.Usuario;
import com.repita.portalgerente.repository.CronogramaExecutadoRepository;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CronogramaExecutadoController {

    @Autowired
    private CronogramaExecutadoRepository cronogramaExecutadoRepository;

    @RequestMapping("/cronogramasExecutados")
    public ModelAndView cronogramasExecutados(HttpSession session) {
        
        ModelAndView mv = new ModelAndView("portalrepita/cronogramasExecutando");

        List<CronogramaExecutado> cronogramasExecutado = cronogramaExecutadoRepository.findAll();
        Usuario usuario = (Usuario) session.getAttribute("usuarioLogado");
        
        mv.addObject("usuario", usuario);
        mv.addObject("cronogramasExecutado", cronogramasExecutado);
        
        return mv;
    }

    @RequestMapping("/visualizarNotaRealizada")
    public ModelAndView visualizarCronogramaExecutando(Long id, HttpSession session) {
        
        ModelAndView mv = new ModelAndView("portalrepita/visualizarCronogramaExecutando");

        CronogramaExecutado cronogramaExecutado = cronogramaExecutadoRepository.findById(id);
        Usuario usuario = (Usuario) session.getAttribute("usuarioLogado");
        
        mv.addObject("usuario", usuario);
        mv.addObject("cronogramaExecutado", cronogramaExecutado);

        return mv;
    }
}
