import java.io.DataInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Serveur {
    public static void main(String[] args) {
        try {
            ServerSocket serveur = new ServerSocket(1609);

            Socket client = serveur.accept();
            InputStream inputStream = client.getInputStream();
            DataInputStream dataInput = new DataInputStream(inputStream);

            int donnee = dataInput.readInt();
            byte[] donneeByte = new byte[donnee];
            dataInput.readFully(donneeByte, 0, donnee);
            String nomFichier = new String(donneeByte);
            File f = new File("./fichier/" + nomFichier);

            FileOutputStream fileOutputStream = new FileOutputStream(f);
            int cont = dataInput.readInt();
            byte[] byteCont = new byte[cont];
            dataInput.readFully(byteCont, 0, cont);
            fileOutputStream.write(byteCont);
            serveur.close();
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }

    }
}