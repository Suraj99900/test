
import java.io.*;
import java.net.*;

public class UDPFileReceiver {

    public static void main(String[] args) throws IOException {
        DatagramSocket socket = new DatagramSocket(8080);
        byte[] buffer = new byte[1024];
        FileOutputStream fos = new FileOutputStream("output.txt");

        while (true) {
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
            socket.receive(packet);

            String message = new String(packet.getData(), 0, packet.getLength());
            if (message.equals("end")) {
                System.out.println("File received successfully.");
                break;
            }

            fos.write(packet.getData(), 0, packet.getLength());
        }

        fos.close();
        socket.close();
        System.out.println("Server closed.");
    }
}
