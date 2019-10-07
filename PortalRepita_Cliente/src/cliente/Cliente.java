package cliente;

import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import mensageria.Resposta;

/**
 *
 * @author Hugo Carvalho
 */
public class Cliente {

    public static void main(String[] args) {

        Socket socket;

        try {

            socket = new Socket("localhost", 40000);

            ObjectInputStream in = new ObjectInputStream(socket.getInputStream());

            Resposta res = (Resposta) in.readObject();

            if (res.getCodigoResposta() == Resposta.ARQUIVO_EXISTE) {

                FileOutputStream fileOut = new FileOutputStream("C:\\Users\\hugan\\Documents\\Teste FDP 2\\tete.txt");
                ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
                objectOut.writeObject(res.getConteudoResposta());
            } else if (res.getCodigoResposta() == Resposta.ARQUIVO_NAO_ENCONTRADO) {
                System.err.println("Robô não foi encontrado no servidor");
            }

            in.close();
            socket.close();
        } catch (Exception e) {
            System.err.println("Ocorreu um erro no cliente");
            e.printStackTrace();
        }
    }

}
