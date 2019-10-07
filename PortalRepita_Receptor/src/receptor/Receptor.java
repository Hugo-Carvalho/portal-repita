package receptor;

import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author Hugo Carvalho
 */
public class Receptor {

    public static void main(String[] args) {

        ServerSocket serverSocket;
        Socket socket;

        try {

            serverSocket = new ServerSocket(49998);

            while (true) {

                socket = serverSocket.accept();

                ObjectInputStream in = new ObjectInputStream(socket.getInputStream());

                File file = new File("C:\\Users\\hugo.carvalho\\Desktop\\tete.java");

                OutputStream os = new FileOutputStream(file);

                os.write((byte[]) in.readObject());
                os.close();
                in.close();
                socket.close();
            }

        } catch (Exception e) {
            System.err.println("Ocorreu um erro no cliente");
            e.printStackTrace();
        }
    }

}
