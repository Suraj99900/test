
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class UDPC {

    public static void main(String[] args) throws IOException, Exception {
        DatagramSocket datagramSocket = new DatagramSocket();
        InetAddress address = InetAddress.getByName("localhost");
        int port = 8080;

        byte[] receiveByte = new byte[1024];
        byte[] sendByte;

        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("Enter message (type 'bye' to exit): ");
            String clientMessage = sc.nextLine();
            sendByte = clientMessage.getBytes();
            DatagramPacket senDatagramPacket = new DatagramPacket(sendByte, sendByte.length, address, port);
            datagramSocket.send(senDatagramPacket);
            // Check if client wants to exit
            if (clientMessage.equals("bye")) {
                System.out.println("Client exiting...");
                break;
            }

            DatagramPacket rDatagramPacket = new DatagramPacket(receiveByte, receiveByte.length);
            datagramSocket.receive(rDatagramPacket);

            String serverMsg = new String(rDatagramPacket.getData(), 0, rDatagramPacket.getLength());

            System.out.println("Server Say: " + serverMsg);

        }
    }
}
