package com.repita.portalgerente.controller;

import com.repita.portalgerente.model.CadastroNota;
import com.repita.portalgerente.model.Nota;
import com.repita.portalgerente.model.NotaAprovada;
import com.repita.portalgerente.model.PDF;
import com.repita.portalgerente.model.Usuario;
import com.repita.portalgerente.repository.CadastroNotaRepository;
import com.repita.portalgerente.repository.NotaAprovadaRepository;
import com.repita.portalgerente.repository.NotaRealizadaRepository;
import com.repita.portalgerente.repository.PDFRepository;
import com.repita.portalgerente.repository.UsuarioRepository;
import java.io.IOException;
import java.util.ArrayList;
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
public class NotaNaoAprovadaController {

    @Autowired
    private PDFRepository pdfRepository;

    @Autowired
    private CadastroNotaRepository cadastroNotaRepository;

    private Nota nota = new Nota();

    @Autowired
    private NotaAprovadaRepository notaAprovadaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private NotaRealizadaRepository notaRealizadaRepository;

    @RequestMapping("/notasNaoAprovadasNaoAtribuidas")
    public ModelAndView notasNaoAprovadasNaoAtribuidas(HttpSession session) {
        ModelAndView mv = new ModelAndView("faturamento/notasNaoAprovadasNaoAtribuidas");

        List<CadastroNota> allNotaNaoAprov = cadastroNotaRepository.findByAllNotaNaoAprovAndNaoAtrib();

        Usuario usuario = (Usuario) session.getAttribute("usuarioLogado");
        usuario = usuarioRepository.findByLogin(usuario.getUser(), usuario.getSenha());
        
        List<Usuario> usuarios = usuarioRepository.findAll();
        
        mv.addObject("tipoUsuario", usuario.getTipo());
        mv.addObject("usuarios", usuarios);
        mv.addObject("allNotaNaoAprovAndNaoAtrib", allNotaNaoAprov);

        return mv;
    }

    @RequestMapping("/notasNaoAprovadasAtribuidas")
    public ModelAndView notasNaoAprovadasAtribuidas(HttpSession session) {
        ModelAndView mv = new ModelAndView("faturamento/notasNaoAprovadasAtribuidas");

        Usuario usuario = (Usuario) session.getAttribute("usuarioLogado");
        List<CadastroNota> allNotaNaoAprovAndAtribByUser = cadastroNotaRepository.findByAllNotaNaoAprovAndAtribByUser(usuario.getUser());

        usuario = usuarioRepository.findByLogin(usuario.getUser(), usuario.getSenha());

        mv.addObject("tipoUsuario", usuario.getTipo());
        mv.addObject("allNotaNaoAprovAndAtrib", allNotaNaoAprovAndAtribByUser);
        mv.addObject("mostrar", true);
        
        return mv;
    }

    @RequestMapping("/allNotasNaoAprovadasAtribuidas")
    public ModelAndView allNotasNaoAprovadasAtribuidas(HttpSession session) {

        Usuario usuario = (Usuario) session.getAttribute("usuarioLogado");
        usuario = usuarioRepository.findByLogin(usuario.getUser(), usuario.getSenha());

        ModelAndView mv = new ModelAndView("faturamento/notasNaoAprovadasAtribuidas");

        if (usuario.getTipo().equals("admin")) {
            
            List<CadastroNota> allNotaNaoAprovAndAtrib = cadastroNotaRepository.findByAllNotaNaoAprovAndAtrib();
            mv.addObject("tipoUsuario", usuario.getTipo());
            mv.addObject("allNotaNaoAprovAndAtrib", allNotaNaoAprovAndAtrib);
        } else {
            List<CadastroNota> allNotaNaoAprovAndAtribByUser = cadastroNotaRepository.findByAllNotaNaoAprovAndAtribByUser(usuario.getUser());
            mv.addObject("tipoUsuario", usuario.getTipo());
            mv.addObject("allNotaNaoAprovAndAtrib", allNotaNaoAprovAndAtribByUser);
        }
        
        mv.addObject("mostrar", false);
        return mv;
    }

    @RequestMapping("/atribuirNota")
    public ModelAndView atribuirNota(Long id, HttpSession session) {

        ModelAndView mv = new ModelAndView("faturamento/notasNaoAprovadasNaoAtribuidas");
        CadastroNota cadastroNota = cadastroNotaRepository.findById(id);
        
        if(cadastroNota.getAtribuidoPara().equals("")){
            Usuario usuario = (Usuario) session.getAttribute("usuarioLogado");
            cadastroNota.setAtribuidoPara(usuario.getUser());

            cadastroNotaRepository.save(cadastroNota);
            
            return mv;
        }
        
        mv.addObject("msgErro", "Nota já atribuida para " + cadastroNota.getAtribuidoPara());
        return mv;
    }
    
    @RequestMapping("/atribuirNotaTo")
    public ModelAndView atribuirNotaTo(Long id, String user) {
        
        ModelAndView mv = new ModelAndView("faturamento/notasNaoAprovadasNaoAtribuidas");
        CadastroNota cadastroNota = cadastroNotaRepository.findById(id);
        
        if(cadastroNota.getAtribuidoPara().equals("")){
            cadastroNota.setAtribuidoPara(user);

            cadastroNotaRepository.save(cadastroNota);

            return mv;
        }
        
        mv.addObject("msgErro", "Nota já atribuida para " + cadastroNota.getAtribuidoPara());
        return mv;
    }

    @RequestMapping("/editarNota")
    public ModelAndView editarNota(Long id, String pageAnt, HttpSession session) {
        ModelAndView mv = new ModelAndView("faturamento/editarNota");

        nota.setCadastroNota(cadastroNotaRepository.findById(id));
        nota.setPdf((ArrayList<PDF>) pdfRepository.findAllPDF(id));
        mv.addObject("nota", nota);
        
        Usuario usuario = (Usuario) session.getAttribute("usuarioLogado");
        usuario = usuarioRepository.findByLogin(usuario.getUser(), usuario.getSenha());
        
        mv.addObject("tipoUsuario", usuario.getTipo());
        mv.addObject("pageAnt", pageAnt);
        return mv;
    }

    @RequestMapping("/editarPdfNota")
    public ModelAndView editarPdfNota(Long id, String pageAnt, HttpSession session) {
        ModelAndView mv = new ModelAndView("faturamento/editarPdfNota");

        PDF pdf = pdfRepository.findById(id);
        
        CadastroNota cadastroNota = cadastroNotaRepository.findById(pdf.getIdCadastNota());
        
        Usuario usuario = (Usuario) session.getAttribute("usuarioLogado");
        usuario = usuarioRepository.findByLogin(usuario.getUser(), usuario.getSenha());
        
        mv.addObject("tipoUsuario", usuario.getTipo());

        mv.addObject("atribuidoPara", cadastroNota.getAtribuidoPara());
        mv.addObject("pdf", pdf);

        mv.addObject("pageAnt", pageAnt);
        return mv;
    }

    @RequestMapping("/adicionarPDF")
    public ModelAndView adicionarPDF(Long id, String pageAnt, HttpSession session) {
        ModelAndView mv = new ModelAndView("faturamento/adicionarPDF");

        PDF newPDF = new PDF();

        newPDF.setIdCadastNota(id);
        
        CadastroNota cadastroNota = cadastroNotaRepository.findById(id);
        
        Usuario usuario = (Usuario) session.getAttribute("usuarioLogado");
        usuario = usuarioRepository.findByLogin(usuario.getUser(), usuario.getSenha());
        
        mv.addObject("tipoUsuario", usuario.getTipo());

        mv.addObject("atribuidoPara", cadastroNota.getAtribuidoPara());
        mv.addObject("newPDF", newPDF);

        mv.addObject("pageAnt", pageAnt);
        return mv;
    }

    @RequestMapping(value = "/saveNota", method = RequestMethod.POST)
    public ModelAndView form(@Valid Nota nota, HttpSession session, HttpServletResponse response, BindingResult result, RedirectAttributes attributes) {
                
        CadastroNota cadastroNota = cadastroNotaRepository.findById(nota.getCadastroNota().getId());
        Usuario usuario = (Usuario) session.getAttribute("usuarioLogado");
        
        usuario = usuarioRepository.findByLogin(usuario.getUser(), usuario.getSenha());
        
        if(cadastroNota.getAtribuidoPara().equals("") || cadastroNota.getAtribuidoPara().equals(usuario.getUser()) || usuario.getTipo().equals("admin")){
            nota.getCadastroNota().setAtribuidoPara(usuario.getUser());
            cadastroNotaRepository.save(nota.getCadastroNota());

            try {
                response.sendRedirect("/notasNaoAprovadasAtribuidas");
            } catch (IOException ex) {
                Logger.getLogger(NotaNaoAprovadaController.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            return null;
        }
        
        ModelAndView mv = new ModelAndView("faturamento/notasNaoAprovadasNaoAtribuidas");
        
        mv.addObject("msgErro", "Nota já atribuida para " + cadastroNota.getAtribuidoPara());
        return mv;
    }

    @RequestMapping(value = "/savePdfNota", method = RequestMethod.POST)
    public ModelAndView form(@Valid PDF pdf, HttpSession session, HttpServletResponse response, BindingResult result, RedirectAttributes attributes) {

        CadastroNota cadastroNota = cadastroNotaRepository.findById(pdf.getIdCadastNota());
        Usuario usuario = (Usuario) session.getAttribute("usuarioLogado");
        
        usuario = usuarioRepository.findByLogin(usuario.getUser(), usuario.getSenha());
        
        if(cadastroNota.getAtribuidoPara().equals("") || cadastroNota.getAtribuidoPara().equals(usuario.getUser()) || usuario.getTipo().equals("admin")){
            cadastroNota.setAtribuidoPara(usuario.getUser());
            
            pdf.setPdfFile(pdfRepository.findById(pdf.getId()).getPdfFile());

            pdfRepository.save(pdf);
            try {
                response.sendRedirect("/editarNota?id=" + pdf.getIdCadastNota() + "#pdf");
            } catch (IOException ex) {
                Logger.getLogger(NotaNaoAprovadaController.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            return null;
        }
        
        ModelAndView mv = new ModelAndView("faturamento/notasNaoAprovadasNaoAtribuidas");

        mv.addObject("msgErro", "Nota já atribuida para " + cadastroNota.getAtribuidoPara());
        return mv;
    }

    @RequestMapping("/aprovarNota")
    public String aprovarNota(Long id) {

        NotaAprovada notaAprovada = new NotaAprovada();
        notaAprovada.setIdNota(id);
        notaAprovadaRepository.save(notaAprovada);

        return "redirect:/notasNaoAprovadasAtribuidas";
    }

    @RequestMapping("/excluirNota")
    public String excluirNota(Long id) {

        cadastroNotaRepository.delete(cadastroNotaRepository.findById(id));
        pdfRepository.deleteAll((ArrayList<PDF>) pdfRepository.findAllPDF(id));

        return "redirect:/notasNaoAprovadasAtribuidas";
    }

    @RequestMapping("/excluirPdfNota")
    public String excluirPdfNota(Long id) {

        PDF pdf = pdfRepository.findById(id);
        pdfRepository.delete(pdf);

        return "redirect:/editarNota?id=" + pdf.getIdCadastNota() + "#pdf";
    }
}
