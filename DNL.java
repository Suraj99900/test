
import java.net.*;
import java.util.Scanner;

public class DNL {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        System.out.println("Please Enter the 1) for url to ip and 2) for ip to url");

        int iChoice = sc.nextInt();
        sc.nextLine();  // Consume newline
        try {
            switch (iChoice) {
                case 1: {
                    System.out.print("Enter URL: ");
                    String url = sc.nextLine();
                    InetAddress ip = InetAddress.getByName(url);
                    System.out.println("IP Address: " + ip.getHostAddress());
                }
                case 2: {

                    System.out.print("Enter IP Address: ");
                    String ipAddress = sc.nextLine();
                    InetAddress host = InetAddress.getByName(ipAddress);
                    System.out.println("Host Name: " + host.getHostName());

                }
                default:
                    System.out.println("Please enter the right choice....");
            }
        } catch (Exception e) {
            System.out.println("Error : " + e.getMessage());
        }

    }
}
