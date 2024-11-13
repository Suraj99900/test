
import java.net.*;
import java.util.Scanner;

public class DNSLookup {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter 1 to resolve URL to IP or 2 to resolve IP to URL:");
        int choice = scanner.nextInt();
        scanner.nextLine();  // Consume newline

        try {
            switch (choice) {
                case 1 -> {
                    // URL to IP
                    System.out.print("Enter URL: ");
                    String url = scanner.nextLine();
                    InetAddress ip = InetAddress.getByName(url);
                    System.out.println("IP Address: " + ip.getHostAddress());
                }
                case 2 -> {
                    // IP to URL
                    System.out.print("Enter IP Address: ");
                    String ipAddress = scanner.nextLine();
                    InetAddress host = InetAddress.getByName(ipAddress);
                    System.out.println("Host Name: " + host.getHostName());
                }
                default ->
                    System.out.println("Invalid choice.");
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }
}
