
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class UDPS {

    public static void main(String[] args) throws IOException, Exception {
        DatagramSocket datagramSocket = new DatagramSocket(8080);
        System.out.println("UDP server started....");
        byte[] receiveByte = new byte[1024];
        byte[] sendByte;

        Scanner sc = new Scanner(System.in);

        while (true) {
            DatagramPacket datagramPacket = new DatagramPacket(receiveByte, receiveByte.length);
            datagramSocket.receive(datagramPacket);

            String ClientMsg = new String(datagramPacket.getData(), 0, datagramPacket.getLength());

            System.out.println("Client Say: " + ClientMsg);

            if ((ClientMsg.equals("bye"))) {
                System.out.println("Client Disconnected. Closing server...");
                break;
            }
            System.out.println("Enter the server message");
            String sendMsg = sc.nextLine();
            sendByte = (sendMsg).getBytes();

            InetAddress address = datagramPacket.getAddress();
            int clientPort = datagramPacket.getPort();
            DatagramPacket senDatagramPacket = new DatagramPacket(sendByte, sendByte.length, address, clientPort);
            datagramSocket.send(senDatagramPacket);
        }

        datagramSocket.close();
        System.out.println("Server close...");
    }
}
