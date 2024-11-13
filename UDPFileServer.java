
import java.io.*;
import java.net.*;

public class UDPFileServer {

    public static void main(String[] args) {
        DatagramSocket socket = null;

        try {
            socket = new DatagramSocket(9876);
            byte[] receiveData = new byte[4096]; // Buffer size

            System.out.println("Server is listening on port 9876...");

            while (true) {
                DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
                socket.receive(receivePacket); // Receive packet

                // Extract data from packet
                String fileName = new String(receivePacket.getData(), 0, receivePacket.getLength());
                int bytesReceived = receivePacket.getLength();

                // Prepare to save file
                FileOutputStream fos = new FileOutputStream(fileName);
                fos.write(receiveData, 0, bytesReceived);
                fos.close();

                System.out.println("Received file: " + fileName + " (" + bytesReceived + " bytes)");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (socket != null && !socket.isClosed()) {
                socket.close();
            }
        }
    }
}
