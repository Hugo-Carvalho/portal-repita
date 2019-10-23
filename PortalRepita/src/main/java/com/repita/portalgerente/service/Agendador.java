package com.repita.portalgerente.service;

import com.repita.portalgerente.model.Cronograma;
import com.repita.portalgerente.model.CronogramaExecutado;
import com.repita.portalgerente.model.CronogramaExecutando;
import com.repita.portalgerente.repository.CronogramaExecutadoRepository;
import com.repita.portalgerente.repository.CronogramaExecutandoRepository;
import com.repita.portalgerente.repository.CronogramaRepository;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class Agendador {
    
    private String data;
    private String hora;
    
    @Autowired
    private CronogramaRepository cronogramaRepository;
    
    @Autowired
    private CronogramaExecutandoRepository cronogramaExecutandoRepository;
    
    @Autowired
    private CronogramaExecutadoRepository cronogramaExecutadoRepository;
    
    @Scheduled(fixedRate = 60L * 1000L, initialDelay = 0)
    public void run() {
        data = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
        hora = new SimpleDateFormat("HH:mm").format(new Date());
        
        List<Cronograma> cronogramas = cronogramaRepository.findByHoraInicio(hora);
        
        for (Cronograma cronograma : cronogramas) {
            if (Objects.nonNull(cronograma.getDataInicio()) && !cronograma.getDataInicio().isEmpty()) {
                if (cronograma.getDataInicio().equals(data)) {
                    
                    gerente(cronograma);
                    cronogramaRepository.delete(cronograma);
                }
            } else {
                gerente(cronograma);
            }
        }
    }
    
    private void gerente(Cronograma cronograma) {
        
        CronogramaExecutando cronogramaExecutando = new CronogramaExecutando();
        cronogramaExecutando.setDataInicio(data);
        cronogramaExecutando.setHoraInicio(hora);
        cronogramaExecutando.setNome(cronograma.getNome());
        cronogramaExecutando.setReceptor(cronograma.getReceptor());
        cronogramaExecutando.setRobo(cronograma.getRobo());
        cronogramaExecutandoRepository.saveAndFlush(cronogramaExecutando);
        
        Socket socket = null;
        String saida = null;
        
        try {
            socket = new Socket("localhost", 38888);
            
            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
            
            FileInputStream fis;
            //buffer for read and write data to file
            try {
                fis = new FileInputStream("C:\\Users\\hugo.carvalho\\Documents\\NetBeansProjects\\TestRobot\\dist\\TestRobot.jar");
                
                out.writeObject("TestRobot");
                
                ZipInputStream zis = new ZipInputStream(fis);
                ZipEntry ze = zis.getNextEntry();
                while (ze != null) {
                    if (!ze.isDirectory()) {
                        byte[] bytes = new byte[(int) ze.getSize()];
                        BufferedInputStream bis = new BufferedInputStream(zis);
                        bis.read(bytes, 0, bytes.length);
                        out.write(bytes, 0, bytes.length);
                        
                        out.writeObject(ze.getName().replace("/", File.separator));
                    } else {
                        out.writeObject(ze.getName().replace("/", File.separator));
                    }
                    //close this ZipEntry
                    zis.closeEntry();
                    ze = zis.getNextEntry();
                }
                // indica que o arquivo terminou
                out.writeObject(null);
                //close last ZipEntry
                zis.closeEntry();
                zis.close();
                fis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            
            saida = (String) in.readObject();
            
            out.close();
            in.close();
            
        } catch (Exception e) {
            System.err.println("Ocorreu um erro no gerenciador");
            e.printStackTrace();
        }
        
        try {
            socket.close();
        } catch (IOException ex) {
            System.err.println("Ocorreu um erro no gerenciador");
            ex.printStackTrace();
        }
        
        cronogramaExecutandoRepository.delete(cronogramaExecutando);
        
        CronogramaExecutado cronogramaExecutado = new CronogramaExecutado();
        cronogramaExecutado.setDataInicio(data);
        cronogramaExecutado.setDataTermino(new SimpleDateFormat("dd-MM-yyyy").format(new Date()));
        cronogramaExecutado.setHoraInicio(hora);
        cronogramaExecutado.setHoraTermino(new SimpleDateFormat("HH:mm").format(new Date()));
        cronogramaExecutado.setMensagem(saida);
        cronogramaExecutado.setNome(cronograma.getNome());
        cronogramaExecutado.setReceptor(cronograma.getReceptor());
        cronogramaExecutado.setRobo(cronograma.getRobo());
        if (saida.contains("sucesso")) {
            cronogramaExecutado.setStatus("Ok");
        } else {
            cronogramaExecutado.setStatus("Erro");
        }
        cronogramaExecutadoRepository.save(cronogramaExecutado);
        
    }
}
