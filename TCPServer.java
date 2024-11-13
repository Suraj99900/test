
import java.io.*;
import java.net.*;

public class TCPServer {

    public static void main(String[] args) {
        try {
            // Create a server socket bound to port 8080
            ServerSocket serverSocket = new ServerSocket(8080);
            System.out.println("Server is listening on port 8080...");

            // Accept client connection
            Socket socket = serverSocket.accept();
            System.out.println("Client connected.");

            // Input stream to receive data from client
            BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            // Output stream to send data to client
            PrintWriter output = new PrintWriter(socket.getOutputStream(), true);

            // Receive message from client
            String clientMessage = input.readLine();
            System.out.println("Received from client: " + clientMessage);

            // Send response to client
            output.println("Hello from Server!");

            // Close resources
            socket.close();
            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
