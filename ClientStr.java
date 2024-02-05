
import java.io.*;
import java.net.*;

public class ClientStr {
    String nomeServer = "localhost";
    int portaServer = 6789;
    Socket miosocket;
    BufferedReader tastiera;
    String stringaUtente;
    String stringaRicevutaDalServer;
    DataOutputStream outVersoServer;
    BufferedReader inDalServer;
    
    public Socket connetti(){
        System.out.println("2 CLIENT partito");
        try{
            tastiera = new BufferedReader(new InputStreamReader(System.in));
            miosocket = new Socket(nomeServer, portaServer);
            outVersoServer = new DataOutputStream(miosocket.getOutputStream());
            inDalServer = new BufferedReader(new InputStreamReader(miosocket.getInputStream()));
        }
        catch(UnknownHostException e){
            System.err.println("host sconosciuto");
        }
        catch(Exception e){
            System.out.println(e.getMessage());
            System.out.println("Errore in connessione");
            System.exit(1);
        }
        return miosocket;
    }
    public void comunica(){
        try{
            System.out.println("4 .. inserisci la stringa");
            stringaUtente = tastiera.readLine();
            System.out.println("5 .. invio stringa");
            outVersoServer.writeBytes(stringaUtente + '\n');
            stringaRicevutaDalServer = inDalServer.readLine();
            System.out.println("8 .. risposta dal server " + '\n' + stringaRicevutaDalServer);
        }catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Errore durante la comunicazione con il client!");
            System.exit(1);
        }
    }

public static void main(String args[]) {

ClientStr client = new ClientStr();

client.connetti ();

client.comunica();

}
}
