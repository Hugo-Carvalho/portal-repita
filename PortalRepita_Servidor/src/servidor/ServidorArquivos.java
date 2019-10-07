package servidor;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import mensageria.Requisicao;
import mensageria.Resposta;

/**
 *
 * @author Hugo Carvalho
 */
public class ServidorArquivos {

    public static void main(String[] args) {

        ServerSocket serverSocket;
        Socket cliente;
        Resposta res;

        try {
            serverSocket = new ServerSocket(40000);
            System.out.println("DEBUG - Servidor iniciado na porta 40000");

            while (true) {
                cliente = serverSocket.accept();

                ObjectOutputStream out = new ObjectOutputStream(cliente.getOutputStream());

                File f = new File("C:\\Users\\hugan\\Documents\\Teste FDP 1\\tete.txt");

                res = new Resposta();

                if (f.exists()) {
                    res.setCodigoResposta(Resposta.ARQUIVO_EXISTE);
                    res.setConteudoResposta(f);
                    out.writeObject(res);
                } else {
                    res.setCodigoResposta(Resposta.ARQUIVO_NAO_ENCONTRADO);
                    out.writeObject(res);
                }

                out.close();
                cliente.close();
                
                break;
            }
        } catch (Exception e) {
            System.err.println("Ocorreu um erro no servidor");
            e.printStackTrace();
        }
    }

}
