import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

import javax.swing.JFileChooser;
import javax.swing.JFrame;

/**
 * Client
 */
public class Client {

    public static void main(String[] args) {

        JFileChooser var = new JFileChooser();
        var.showOpenDialog(null);
        File fichier = var.getSelectedFile();

        try {
            Socket client = new Socket("localhost", 1609);
            OutputStream outPut = client.getOutputStream();
            DataOutputStream dataOutPut = new DataOutputStream(outPut);
            FileInputStream fileInput = new FileInputStream(fichier);
            byte[] nombre = new byte[(int) fichier.length()];
            int x = fileInput.read(nombre);

            // Envoye le fichier
            dataOutPut.writeInt(fichier.getName().getBytes().length);
            dataOutPut.write(fichier.getName().getBytes());

            // Envoye les contenus du fichier
            dataOutPut.writeInt(x);
            dataOutPut.write(nombre);

            // Sortie du flux
            client.close();

            System.out.println("Fichier reçu avec succes dans le dossier fichier");
        } catch (Exception e) {

            // TODO: handle exception
            e.printStackTrace();
        }
    }

}