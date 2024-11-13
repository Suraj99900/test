
import java.util.Scanner;

public class Subnetting {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Get IP Address and number of subnets
        System.out.println("Enter IP Address (e.g., 192.168.1.0): ");
        String ipAddress = scanner.nextLine();
        System.out.println("Enter the number of subnets: ");
        int subnets = scanner.nextInt();

        // Split the IP Address into octets
        String[] octets = ipAddress.split("\\.");
        int[] ipParts = new int[4];
        for (int i = 0; i < 4; i++) {
            ipParts[i] = Integer.parseInt(octets[i]);
        }

        // Calculate number of bits needed for subnets
        int subnetBits = (int) Math.ceil(Math.log(subnets) / Math.log(2));
        int subnetMask = 32 - subnetBits;

        // Output the subnet mask
        System.out.println("Number of bits for subnets: " + subnetBits);
        System.out.println("Subnet Mask: /" + subnetMask + " (" + getSubnetMask(subnetMask) + ")");

        // Calculate and display the subnet ranges
        int totalSubnets = (int) Math.pow(2, subnetBits);
        int increment = 256 / totalSubnets;
        System.out.println("\nSubnets:");
        for (int i = 0; i < totalSubnets; i++) {
            System.out.println("Subnet " + (i + 1) + ": " + ipParts[0] + "." + ipParts[1] + "." + ipParts[2] + "." + (i * increment) + " - "
                    + ipParts[0] + "." + ipParts[1] + "." + ipParts[2] + "." + ((i + 1) * increment - 1));
        }
    }

    // Method to get subnet mask in dotted decimal format
    public static String getSubnetMask(int subnetMask) {
        int mask = 0xffffffff << (32 - subnetMask);
        return ((mask >>> 24) & 0xff) + "." + ((mask >>> 16) & 0xff) + "." + ((mask >>> 8) & 0xff) + "." + (mask & 0xff);
    }
}
