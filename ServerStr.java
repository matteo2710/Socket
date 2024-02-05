import java.io.*;
import java.net.*;

public class ServerStr {
    ServerSocket server = null;
    Socket client = null;
    String stringaRicevuta = null;
    String stringaModificata = null;
    BufferedReader inDalClient;
    DataOutputStream outVersoClient;

    public Socket attendi() {
        try {
            System.out.println("1 SERVER partito in esecuzione...");
            server = new ServerSocket(6789);
            System.out.println("2 Server pronto. In attesa di connessione...");
            client = server.accept();
            System.out.println("3 Connessione accettata con il client: " + client);

            inDalClient = new BufferedReader(new InputStreamReader(client.getInputStream()));
            outVersoClient = new DataOutputStream(client.getOutputStream());
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Errore durante l'istanza del server!");
            System.exit(1);
        }
        return client;
    }

    public void comunica() {
        try {
            System.out.println("4 Benvenuto client, scrivi una frase e la trasformo in maiuscolo.");
            stringaRicevuta = inDalClient.readLine();
            System.out.println("5 Ricevuta la stringa dal cliente: " + stringaRicevuta);

            stringaModificata = stringaRicevuta.toUpperCase();
            System.out.println("6 Invio la stringa modificata al client...");

            outVersoClient.writeBytes(stringaModificata + '\n');
            System.out.println("7 SERVER: Fine elaborazione. Chiusura connessione.");
            client.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Errore durante la comunicazione con il client!");
            System.exit(1);
        }
    }

    public static void main(String[] args) {
        ServerStr server = new ServerStr();
        Socket clientSocket = server.attendi();
        server.comunica();
    }
}
