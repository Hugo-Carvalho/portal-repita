package gerente;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

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
                //close last ZipEntry
                zis.closeEntry();
                zis.close();
                fis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

            out.close();
            socket.close();
        } catch (Exception e) {
            System.err.println("Ocorreu um erro no gerenciador");
            e.printStackTrace();
        }
    }

}
