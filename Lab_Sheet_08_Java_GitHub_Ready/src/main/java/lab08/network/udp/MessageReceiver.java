package lab08.network.udp;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.nio.charset.StandardCharsets;

public class MessageReceiver {
    public static void main(String[] args) {
        int port = args.length > 0 ? Integer.parseInt(args[0]) : 6000;
        byte[] buffer = new byte[2048];
        try (DatagramSocket socket = new DatagramSocket(port)) {
            System.out.println("MessageReceiver waiting on UDP port " + port);
            while (true) {
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
                socket.receive(packet);
                String message = new String(packet.getData(), packet.getOffset(),
                    packet.getLength(), StandardCharsets.UTF_8);
                System.out.printf("Received from %s:%d: %s%n",
                    packet.getAddress().getHostAddress(), packet.getPort(), message);
            }
        } catch (Exception exception) {
            System.err.println("MessageReceiver error: " + exception.getMessage());
        }
    }
}
