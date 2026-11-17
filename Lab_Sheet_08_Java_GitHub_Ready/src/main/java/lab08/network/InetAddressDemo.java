package lab08.network;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class InetAddressDemo {
    public static void main(String[] args) {
        try {
            InetAddress local = InetAddress.getLocalHost();
            System.out.println("Host name: " + local.getHostName());
            System.out.println("IP address: " + local.getHostAddress());
        } catch (UnknownHostException exception) {
            System.err.println("Could not resolve local host: " + exception.getMessage());
        }
    }
}
