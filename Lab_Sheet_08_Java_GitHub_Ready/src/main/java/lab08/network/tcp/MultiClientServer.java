package lab08.network.tcp;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/** Accepts clients continuously and handles each connection on a worker thread. */
public class MultiClientServer {
    public static void main(String[] args) {
        int port = args.length > 0 ? Integer.parseInt(args[0]) : 5002;
        ExecutorService workers = Executors.newCachedThreadPool();
        try (ServerSocket server = new ServerSocket(port)) {
            System.out.println("MultiClientServer listening on port " + port);
            while (true) {
                Socket client = server.accept();
                workers.execute(() -> handleClient(client));
            }
        } catch (Exception exception) {
            System.err.println("MultiClientServer stopped: " + exception.getMessage());
        } finally {
            workers.shutdown();
        }
    }

    private static void handleClient(Socket client) {
        try (Socket connection = client;
             BufferedReader reader = new BufferedReader(
                 new InputStreamReader(connection.getInputStream(), StandardCharsets.UTF_8))) {
            String message = reader.readLine();
            System.out.printf("[%s] Message from %s: %s%n",
                Thread.currentThread().getName(),
                connection.getRemoteSocketAddress(),
                message == null ? "(no message)" : message);
        } catch (Exception exception) {
            System.err.println("Client handler error: " + exception.getMessage());
        }
    }
}
