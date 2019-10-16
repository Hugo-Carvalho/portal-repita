package com.repita.portalgerente.controller;

import com.repita.portalgerente.model.CadastroNota;
import com.repita.portalgerente.model.Nota;
import com.repita.portalgerente.model.NotaRealizada;
import com.repita.portalgerente.model.PDF;
import com.repita.portalgerente.model.Usuario;
import com.repita.portalgerente.repository.CadastroNotaRepository;
import com.repita.portalgerente.repository.NotaRealizadaRepository;
import com.repita.portalgerente.repository.PDFRepository;
import com.repita.portalgerente.repository.UsuarioRepository;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class NotaRealizadaController {

    @Autowired
    private PDFRepository pdfRepository;

    @Autowired
    private CadastroNotaRepository cadastroNotaRepository;
    
    @Autowired
    private UsuarioRepository usuarioRepository;

    private Nota nota = new Nota();

    @Autowired
    private NotaRealizadaRepository notaRealizadaRepository;

    @RequestMapping("/notasRealizadas")
    public ModelAndView notasRealizadas(HttpSession session) {
        ModelAndView mv = new ModelAndView("faturamento/notasRealizadas");

        Iterable<CadastroNota> allNotaRealiz = cadastroNotaRepository.findByAllNotaRealiz();
        ArrayList<NotaRealizada> notasRealizadas = (ArrayList) notaRealizadaRepository.findAll();

        SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy"); 
        SimpleDateFormat sdf2 = new SimpleDateFormat("dd/MM/yyyy");
        
        for (int i = 0; i < notasRealizadas.size(); i++) {
            for (int j = 0; j < notasRealizadas.size() - 1; j++) {
                try {
                    if (sdf1.parse(notasRealizadas.get(j + 1).getDataRealizacao()).after(sdf2.parse(notasRealizadas.get(j).getDataRealizacao()))) {
                        
                        NotaRealizada aux = notasRealizadas.get(j);
                        notasRealizadas.set(j, notasRealizadas.get(j + 1));
                        notasRealizadas.set(j + 1, aux);
                    }
                } catch (ParseException ex) {
                    Logger.getLogger(NotaRealizadaController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        
        ArrayList<ArrayList> arrayCadastroNotas = new ArrayList<>();

        for (int i = 0; i < notasRealizadas.size(); i++) {
            ArrayList<CadastroNota> cadastroNotas = new ArrayList<>();
            String dataRealizacao = notasRealizadas.get(i).getDataRealizacao();
            int aux = i; 
            for (CadastroNota cadastroNota : allNotaRealiz) {
                if (notasRealizadas.get(i).getIdNota() == cadastroNota.getId()) {
                    cadastroNotas.add(cadastroNota);
                    break;
                }
            }
            for (int j = aux + 1; j < notasRealizadas.size(); j++) {
                if (notasRealizadas.get(j).getDataRealizacao().equals(dataRealizacao)) {
                    for (CadastroNota cadastroNota : allNotaRealiz) {
                        if (notasRealizadas.get(j).getIdNota() == cadastroNota.getId()) {
                            cadastroNotas.add(cadastroNota);
                            break;
                        }
                    }
                    i++;
                }
            }
            if (cadastroNotas.size() > 0) {
                arrayCadastroNotas.add(cadastroNotas);
            }
        }

        ArrayList<String> datas = new ArrayList<>();
        for (int i = 0; i < notasRealizadas.size(); i++) {
            String dataRealizacao = notasRealizadas.get(i).getDataRealizacao();
            datas.add(dataRealizacao);
        }

        for (int i = 0; i < datas.size() - 1; i++) {
            for (int j = i + 1; j < datas.size(); j++) {
                if (datas.get(i).equals(datas.get(j))) {
                    datas.remove(j);
                    i = -1;
                    break;
                }
            }
        }

        String dataRealizacoes[] = new String[arrayCadastroNotas.size()];
        for (int i = 0; i < datas.size(); i++) {
            dataRealizacoes[i] = datas.get(i);
        }
        
        Usuario usuario = (Usuario) session.getAttribute("usuarioLogado");
        usuario = usuarioRepository.findByLogin(usuario.getUser(), usuario.getSenha());
        
        mv.addObject("tipoUsuario", usuario.getTipo());

        mv.addObject("dataRealizacoes", dataRealizacoes);
        mv.addObject("arrayCadastroNotas", arrayCadastroNotas);

        return mv;
    }

    @RequestMapping("/visualizarNotaRealizada")
    public ModelAndView visualizarNotaRealizada(Long id, HttpSession session) {
        ModelAndView mv = new ModelAndView("faturamento/visualizarNotaRealizada");

        nota.setCadastroNota(cadastroNotaRepository.findById(id));
        nota.setPdf((ArrayList<PDF>)pdfRepository.findAllPDF(id));
        
        mv.addObject("nota", nota);
        
        Usuario usuario = (Usuario) session.getAttribute("usuarioLogado");
        usuario = usuarioRepository.findByLogin(usuario.getUser(), usuario.getSenha());
        
        mv.addObject("tipoUsuario", usuario.getTipo());

        return mv;
    }
    
    @RequestMapping("/visualizarPdfNotaRealizada")
    public ModelAndView visualizarPdfNotaRealizada(Long id, HttpSession session) {
        ModelAndView mv = new ModelAndView("faturamento/visualizarPdfNotaRealizada");

        PDF pdf = pdfRepository.findById(id);

        mv.addObject("pdf", pdf);
        
        Usuario usuario = (Usuario) session.getAttribute("usuarioLogado");
        usuario = usuarioRepository.findByLogin(usuario.getUser(), usuario.getSenha());
        
        mv.addObject("tipoUsuario", usuario.getTipo());

        return mv;
    }
}
