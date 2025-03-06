import java.io.IOException;
import java.net.BindException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.Date;

public class Server {

    private DatagramSocket dSocket;
    private int port;

    public Server(int port) throws BindException, SocketException {
        this.port = port;
        // Create a socket and bind it to the specified port
        this.dSocket = new DatagramSocket(port);
        System.out.println("Apertura porta in corso!");
    }

    public void startListening() {
        DatagramPacket inPacket;
        DatagramPacket outPacket;
        byte[] bufferIn, bufferOut;
        String messageIn, messageOut;
        Date currentDate;

        while (true) {
            try {
                System.out.println("Server in ascolto sulla porta " + port + "!\n");
                bufferIn = new byte[256];

                // Create a datagram packet to receive data
                inPacket = new DatagramPacket(bufferIn, bufferIn.length);

                // Receive the packet from the client
                dSocket.receive(inPacket);

                // Get the client's address and port
                InetAddress clientAddress = inPacket.getAddress();
                int clientPort = inPacket.getPort();

                // Convert the received bytes into a string message
                messageIn = new String(inPacket.getData(), 0, inPacket.getLength());
                System.out.println("SONO IL CLIENT " + clientAddress + 
                                   ":" + clientPort + "> " + messageIn);

                // Get the current date and time
                currentDate = new Date();
                messageOut = currentDate.toString();
                bufferOut = messageOut.getBytes();

                // Create a packet to send the response
                outPacket = new DatagramPacket(bufferOut, bufferOut.length, clientAddress, clientPort);

                // Send the response to the client
                dSocket.send(outPacket);
                System.out.println("Risposta inviata!");
            } catch (IOException e) {
                System.err.println("Errore di I/O");
            }
        }
    }

    public void closeConnection() {
        if (dSocket != null && !dSocket.isClosed()) {
            dSocket.close();
            System.out.println("Comunicazione chiusa!");
        }
    }
}
