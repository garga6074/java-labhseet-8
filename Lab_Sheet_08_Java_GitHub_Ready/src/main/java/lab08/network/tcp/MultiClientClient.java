package lab08.network.tcp;
import java.io.PrintWriter;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class MultiClientClient {
    public static void main(String[] args) {
        String host = args.length > 0 ? args[0] : "127.0.0.1";
        int port = args.length > 1 ? Integer.parseInt(args[1]) : 5002;
        String message = args.length > 2
            ? String.join(" ", java.util.Arrays.copyOfRange(args, 2, args.length))
            : "Hello from a client";
        try (Socket socket = new Socket(host, port);
             PrintWriter writer = new PrintWriter(socket.getOutputStream(), true,
                                                  StandardCharsets.UTF_8)) {
            writer.println(message);
            System.out.println("Sent: " + message);
        } catch (Exception exception) {
            System.err.println("MultiClientClient error: " + exception.getMessage());
        }
    }
}
