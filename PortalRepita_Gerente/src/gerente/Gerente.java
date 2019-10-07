package gerente;

import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 *
 * @author Hugo Carvalho
 */
public class Gerente {

    public static void main(String[] args) {

        Socket socket;

        try {
            socket = new Socket("10.2.1.107", 49998);

            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());

            File f = new File("C:\\Users\\hugo.carvalho\\Documents\\NetBeansProjects\\TestRobot\\src\\testrobot\\TestRobot.java");
            byte[] bytes = new byte[16 * 1024];
            FileInputStream fis = new FileInputStream(f);
            fis.read(bytes);
            fis.close();
            out.writeObject(bytes);
            out.close();
            socket.close();
        } catch (Exception e) {
            System.err.println("Ocorreu um erro no servidor");
            e.printStackTrace();
        }
    }

}
