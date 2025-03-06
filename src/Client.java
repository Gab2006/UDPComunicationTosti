import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

public class Client {

    private int port;
    private InetAddress serverAddress;
    private DatagramSocket dSocket;

    public Client(String serverIp, int port) throws UnknownHostException, SocketException {
        this.port = port;
        this.serverAddress = InetAddress.getByName(serverIp); // Use the server IP address
        this.dSocket = new DatagramSocket();
    }

    public String requestDateAndTime() {
        String message = "RICHIESTA DATA E ORA";
        DatagramPacket outPacket;
        DatagramPacket inPacket;
        byte[] buffer;
        String response = "";

        try {
            // Prepare the datagram with the message to send
            outPacket = new DatagramPacket(message.getBytes(), message.length(), serverAddress, port);

            // Send the data
            dSocket.send(outPacket);

            // Prepare the buffer for receiving data
            buffer = new byte[256];
            inPacket = new DatagramPacket(buffer, buffer.length);

            // Receive the response from the server
            dSocket.receive(inPacket);

            // Extract the response message
            response = new String(inPacket.getData(), 0, inPacket.getLength());
        } catch (IOException e) {
            System.err.println("Errore di I/O");
        } finally {
            closeConnection();
        }

        return response;
    }

    private void closeConnection() {
        if (dSocket != null && !dSocket.isClosed()) {
            dSocket.close();
            System.out.println("Comunicazione chiusa!");
        }
    }
}
