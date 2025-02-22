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
        
        ModelAndView mv = new ModelAndView("portalrepita/cronogramasExecutados");

        List<CronogramaExecutado> cronogramasExecutados = cronogramaExecutadoRepository.findAll();
        Usuario usuarioLogado = (Usuario) session.getAttribute("usuarioLogado");
        
        mv.addObject("usuarioLogado", usuarioLogado);
        mv.addObject("cronogramasExecutados", cronogramasExecutados);
        
        return mv;
    }

    @RequestMapping("/visualizarCronogramaExecutado")
    public ModelAndView visualizarCronogramaExecutando(Long id, HttpSession session) {
        
        ModelAndView mv = new ModelAndView("portalrepita/visualizarCronogramaExecutado");

        CronogramaExecutado cronogramaExecutado = cronogramaExecutadoRepository.findById(id);
        Usuario usuarioLogado = (Usuario) session.getAttribute("usuarioLogado");
        
        mv.addObject("usuarioLogado", usuarioLogado);
        mv.addObject("cronogramaExecutado", cronogramaExecutado);

        return mv;
    }
}
