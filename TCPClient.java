
import java.io.*;
import java.net.*;

public class TCPClient {

    public static void main(String[] args) {
        try {
            // Connect to the server at localhost on port 8080
            Socket socket = new Socket("127.0.0.1", 8080);

            // Output stream to send data to the server
            PrintWriter output = new PrintWriter(socket.getOutputStream(), true);

            // Input stream to receive data from server
            BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            // Send message to the server
            output.println("Hello from Client!");

            // Receive response from server
            String serverMessage = input.readLine();
            System.out.println("Received from server: " + serverMessage);

            // Close resources
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
