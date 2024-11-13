
import java.io.*;
import java.net.*;

public class UDPFileClient {

    public static void main(String[] args) {
        DatagramSocket socket = null;
        String filePath = "file.txt"; // Change this to your file path

        try {
            socket = new DatagramSocket();
            byte[] sendData = new byte[4096]; // Buffer size

            // Read file and send to server
            FileInputStream fis = new FileInputStream(filePath);
            String fileName = new File(filePath).getName();
            socket.send(new DatagramPacket(fileName.getBytes(), fileName.length(), InetAddress.getByName("localhost"), 9876));

            int bytesRead;
            while ((bytesRead = fis.read(sendData)) != -1) {
                socket.send(new DatagramPacket(sendData, bytesRead, InetAddress.getByName("localhost"), 9876));
            }

            fis.close();
            System.out.println("File sent: " + fileName);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (socket != null && !socket.isClosed()) {
                socket.close();
            }
        }
    }
}
