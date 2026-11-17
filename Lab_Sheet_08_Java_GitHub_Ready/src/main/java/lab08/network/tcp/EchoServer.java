package lab08.network.tcp;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class EchoServer {
    public static void main(String[] args) {
        int port = args.length > 0 ? Integer.parseInt(args[0]) : 5001;
        try (ServerSocket server = new ServerSocket(port)) {
            System.out.println("EchoServer listening on port " + port);
            while (true) {
                try (Socket client = server.accept();
                     BufferedReader reader = new BufferedReader(
                         new InputStreamReader(client.getInputStream(), StandardCharsets.UTF_8));
                     PrintWriter writer = new PrintWriter(client.getOutputStream(), true,
                                                          StandardCharsets.UTF_8)) {
                    String message;
                    while ((message = reader.readLine()) != null) {
                        System.out.println("Received: " + message);
                        writer.println(message);
                    }
                }
            }
        } catch (Exception exception) {
            System.err.println("EchoServer stopped: " + exception.getMessage());
        }
    }
}
