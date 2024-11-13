
import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class TCPC {

    public static void main(String[] args)
            throws IOException, Exception {
        Socket socket = new Socket("localhost", 8080);

        BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        Scanner scanner = new Scanner(System.in);

        PrintWriter output = new PrintWriter(socket.getOutputStream(), true);
        output.println("hi");
        while (true) {
            System.out.println("Enter Message :");
            String ClientSay = scanner.nextLine();
            output.println(ClientSay);
            if (ClientSay.equals("bye")) {
                break;
            }

            String serverMessage = input.readLine();
            if (serverMessage != null) {
                System.out.println("Server Say: " + serverMessage);
            } else {
                System.out.println("Server connection closed.");
                break;
            }

        }
        socket.close();
    }
}
