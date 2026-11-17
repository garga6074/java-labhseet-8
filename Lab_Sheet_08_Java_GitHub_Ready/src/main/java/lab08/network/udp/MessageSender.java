package lab08.network.udp;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.nio.charset.StandardCharsets;

public class MessageSender {
    public static void main(String[] args) {
        String host = args.length > 0 ? args[0] : "127.0.0.1";
        int port = args.length > 1 ? Integer.parseInt(args[1]) : 6000;
        byte[] data = "Ping".getBytes(StandardCharsets.UTF_8);
        try (DatagramSocket socket = new DatagramSocket()) {
            DatagramPacket packet = new DatagramPacket(
                data, data.length, InetAddress.getByName(host), port);
            socket.send(packet);
            System.out.println("Sent UDP message: Ping");
        } catch (Exception exception) {
            System.err.println("MessageSender error: " + exception.getMessage());
        }
    }
}
