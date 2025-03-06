import java.net.UnknownHostException;
import java.net.SocketException;

public class MainClient {

    public static void main(String[] args) {
        // Server address and port
        String serverIp = "127.0.0.1";  // Use local IP or replace with the actual server IP
        int port = 2000;

        try {
            // Create a new client object
            Client client = new Client(serverIp, port);

            // Request date and time from the server
            String response = client.requestDateAndTime();

            // Print the server response
            if (!response.isEmpty()) {
                System.out.println("Il server ha inviato i dati!");
                System.out.println("Data e ora del server: " + response);
            }
        } catch (UnknownHostException e) {
            System.err.println("Errore DNS");
        } catch (SocketException e) {
            System.err.println("Errore Socket");
        }
    }
}
