
import java.io.*;
import java.net.*;

public class UDPFileSender {

    public static void main(String[] args) throws IOException {
        DatagramSocket socket = new DatagramSocket();
        InetAddress address = InetAddress.getByName("localhost");
        int port = 8080;

        FileInputStream fis = new FileInputStream("input.txt");
        byte[] buffer = new byte[1024]; // Buffer size

        while (fis.read(buffer) != -1) {
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length, address, port);
            socket.send(packet);
        }

        // Sending "end" message to indicate completion
        byte[] endMessage = "end".getBytes();
        DatagramPacket endPacket = new DatagramPacket(endMessage, endMessage.length, address, port);
        socket.send(endPacket);

        fis.close();
        socket.close();
        System.out.println("File sent successfully.");
    }
}
