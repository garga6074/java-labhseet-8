package lab08.network.tcp;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class EchoClient {
    public static void main(String[] args) {
        String host = args.length > 0 ? args[0] : "127.0.0.1";
        int port = args.length > 1 ? Integer.parseInt(args[1]) : 5001;
        try (Socket socket = new Socket(host, port);
             BufferedReader console = new BufferedReader(
                 new InputStreamReader(System.in, StandardCharsets.UTF_8));
             BufferedReader reader = new BufferedReader(
                 new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8));
             PrintWriter writer = new PrintWriter(socket.getOutputStream(), true,
                                                  StandardCharsets.UTF_8)) {
            System.out.print("Enter a message: ");
            String message = console.readLine();
            if (message == null) return;
            writer.println(message);
            System.out.println("Echo from server: " + reader.readLine());
        } catch (Exception exception) {
            System.err.println("EchoClient error: " + exception.getMessage());
        }
    }
}
