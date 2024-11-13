
import java.io.*;
import java.net.*;
import java.util.Scanner;

public class TCPS {

    public static void main(String[] args)
            throws IOException {
        ServerSocket serverSocket = new ServerSocket(8080);
        System.out.println("Server is listening on port 8080...");
        Socket socket = serverSocket.accept();
        System.out.println("Client connected.");

        BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter output = new PrintWriter(socket.getOutputStream(), true);

        Scanner sc = new Scanner(System.in);
        String CLientMessage;

        while (!(CLientMessage = input.readLine()).equals("bye")) {
            System.out.println("Client Say: " + CLientMessage);
            System.out.println("Enter server Message: ");
            String ServerMessage = sc.nextLine();
            output.println("Server Say: " + ServerMessage);

            CLientMessage = input.readLine();
            System.out.println("Client Say: " + CLientMessage);

        }
        System.out.println("Client disconnected. Closing server...");
        socket.close();
        serverSocket.close();

    }
}
