import java.io.*;
import java.net.*;
import java.util.*;

public class Client {
    private String name;
    private Map<String, String> ipMap = new HashMap<>();  // UserID -> IP

    public Client(String name) {
        this.name = name;
    }

    public void loadIPsFromFile(String filepath) throws IOException {
               BufferedReader br = new BufferedReader(new FileReader(filepath));
        String line;
        while ((line = br.readLine()) != null) {
            String[] parts = line.trim().split("\\s+");
            if (parts.length == 2) {
                ipMap.put(parts[0], parts[1]);
            }
        }
        br.close();
    }

    public void startServer(int port) {
              new Thread(() -> {
            try (ServerSocket serverSocket = new ServerSocket(port)) {
                System.out.println(name + " listening on port " + port);
                while (true) {
                    Socket clientSocket = serverSocket.accept();
                    new Thread(() -> handleClient(clientSocket)).start();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }

    private void handleClient(Socket socket) {
        try (BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
            String line;
            while ((line = in.readLine()) != null) {
                System.out.println("Message: " + line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void chat(String targetName, int port) {
        String targetIP = ipMap.get(targetName);
        if (targetIP == null) {
            System.out.println("Target user not found.");
            return;
        }
        try (Socket socket = new Socket(targetIP, port);
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             Scanner scanner = new Scanner(System.in)) {
            System.out.println("Chatting with " + targetName);
            while (true) {
                String msg = scanner.nextLine();
                out.println(name + ": " + msg);
            }
        } catch (IOException e) {
            System.out.println("Could not connect to " + targetName);
        }
    }

    public static void main(String[] args) throws IOException {
        if (args.length != 3) {
            System.out.println("Usage: java Client <YourName> <YourPort> <PathToHadoopOutputFile>");
            return;
        }

        Client client = new Client(args[0]);
        client.loadIPsFromFile(args[2]);
        client.startServer(Integer.parseInt(args[1]));

        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Enter target username to chat or type 'exit' to quit:");
            String target = scanner.nextLine();
            if (target.equalsIgnoreCase("exit")) break;
            client.chat(target, Integer.parseInt(args[1]));
        }
    }
}
