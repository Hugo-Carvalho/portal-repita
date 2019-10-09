package receptor;

import java.io.BufferedOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

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

            String dir;

            List<File> tobeJared = new ArrayList<>();

            while (true) {

                socket = serverSocket.accept();

                ObjectInputStream in = new ObjectInputStream(socket.getInputStream());

                Object ob;
                String dirName, dirNameRaiz;
                File fileW;

                ob = in.readObject();
                dirNameRaiz = (String) ob;
                fileW = new File("C:\\Program Files\\Receptor_Repita\\" + dirNameRaiz);
                if (!fileW.exists()) {
                    fileW.mkdirs();
                }
                dir = "C:\\Program Files\\Receptor_Repita\\" + dirNameRaiz;

                do {
                    try {
                        try {
                            ob = in.readObject();
                            dirName = (String) ob;
                            fileW = new File(dir + File.separator + dirName);
                            if (!fileW.exists()) {
                                fileW.mkdirs();
                            }
                        } catch (Exception e) {
                            byte[] bytes = new byte[70 * 1024];
                            int bytesRead = in.read(bytes, 0, bytes.length);

                            int current = bytesRead;
                            do {
                                bytesRead = in.read(bytes, current, (bytes.length - current));
                                if (bytesRead >= 0) {
                                    current += bytesRead;
                                }
                            } while (bytesRead > -1);
                            ob = in.readObject();
                            dirName = (String) ob;
                            FileOutputStream fos = new FileOutputStream(new File(dir + File.separator + dirName));
                            tobeJared.add(new File(dir + File.separator + dirName));
                            BufferedOutputStream bos = new BufferedOutputStream(fos);
                            bos.write(bytes, 0, current);
                            bos.flush();
                            bos.close();
                        }
                    } catch (EOFException | IndexOutOfBoundsException e) {
                        break;
                    }
                } while (ob != null);

                in.close();

                Process pc;
                pc = Runtime.getRuntime().exec("\"C:\\Program Files\\Receptor_Repita\\7z.exe\" a \"C:\\Program Files\\Receptor_Repita\\arquivo.jar\" " + dir + "\\*");
                pc = Runtime.getRuntime().exec("java -jar \"C:\\Program Files\\Receptor_Repita\\arquivo.jar\"");
                //pc = Runtime.getRuntime().exec("RD /S /Q \"" + dir + "\"");

                socket.close();
            }

        } catch (Exception e) {
            System.err.println("Ocorreu um erro no receptor");
            e.printStackTrace();
        }
    }
}
