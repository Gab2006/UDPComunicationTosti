import java.net.BindException;
import java.net.SocketException;

public class MainServer {

    public static void main(String[] args) {
        int port = 2000; // Port to listen on

        try {
            // Create a new server object
            Server server = new Server(port);

            // Start the server to listen for client requests
            server.startListening();
        } catch (BindException e) {
            System.err.println("Errore porta già in uso");
        } catch (SocketException e) {
            System.err.println("Errore Socket");
        } catch (Exception e) {
            System.err.println("Errore generico");
        }
    }
}
