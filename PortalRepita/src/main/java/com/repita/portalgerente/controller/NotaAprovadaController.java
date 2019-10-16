package com.repita.portalgerente.controller;

import com.repita.portalgerente.model.CadastroNota;
import com.repita.portalgerente.model.Nota;
import com.repita.portalgerente.model.PDF;
import com.repita.portalgerente.model.Usuario;
import com.repita.portalgerente.repository.CadastroNotaRepository;
import com.repita.portalgerente.repository.NotaAprovadaRepository;
import com.repita.portalgerente.repository.PDFRepository;
import com.repita.portalgerente.repository.UsuarioRepository;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class NotaAprovadaController {

    @Autowired
    private PDFRepository pdfRepository;

    @Autowired
    private CadastroNotaRepository cadastroNotaRepository;
    
    @Autowired
    private UsuarioRepository usuarioRepository;

    private Nota nota = new Nota();

    @Autowired
    private NotaAprovadaRepository notaAprovadaRepository;

    @RequestMapping("/notasAprovadas")
    public ModelAndView notasAprovadas(HttpSession session) {
        ModelAndView mv = new ModelAndView("faturamento/notasAprovadas");

        List<CadastroNota> allNotaAprov = cadastroNotaRepository.findByAllNotaAprov();
        
        Usuario usuario = (Usuario) session.getAttribute("usuarioLogado");
        usuario = usuarioRepository.findByLogin(usuario.getUser(), usuario.getSenha());
        
        mv.addObject("tipoUsuario", usuario.getTipo());

        mv.addObject("allNotaAprov", allNotaAprov);

        return mv;
    }

    @RequestMapping("/visualizarNotaAprovada")
    public ModelAndView visualizarNotaAprovada(Long id, HttpSession session) {
        ModelAndView mv = new ModelAndView("faturamento/visualizarNotaAprovada");

        nota.setCadastroNota(cadastroNotaRepository.findById(id));
        nota.setPdf((ArrayList<PDF>)pdfRepository.findAllPDF(id));
        
        Usuario usuario = (Usuario) session.getAttribute("usuarioLogado");
        usuario = usuarioRepository.findByLogin(usuario.getUser(), usuario.getSenha());
        
        mv.addObject("tipoUsuario", usuario.getTipo());
        
        mv.addObject("nota", nota);

        return mv;
    }
    
    @RequestMapping("/visualizarPdfNotaAprovada")
    public ModelAndView visualizarPdfNotaAprovada(Long id, HttpSession session) {
        ModelAndView mv = new ModelAndView("faturamento/visualizarPdfNotaAprovada");

        PDF pdf = pdfRepository.findById(id);
        
        Usuario usuario = (Usuario) session.getAttribute("usuarioLogado");
        usuario = usuarioRepository.findByLogin(usuario.getUser(), usuario.getSenha());
        
        mv.addObject("tipoUsuario", usuario.getTipo());

        mv.addObject("pdf", pdf);

        return mv;
    }
}
