import java.io.*;
import java.net.*;

public class EchoServer {

    public static void main(String[] args) {

        int port = 5000;

        try (ServerSocket serverSocket = new ServerSocket(port)) {

            System.out.println("Сервер запущен.");
            System.out.println("Ожидание клиента...");

            Socket socket = serverSocket.accept();

            System.out.println("Клиент подключился!");

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(socket.getInputStream())
            );

            PrintWriter out = new PrintWriter(
                    socket.getOutputStream(), true
            );

            String message;

            while ((message = in.readLine()) != null) {

                System.out.println("Получено: " + message);

                out.println("Эхо: " + message);

                if (message.equalsIgnoreCase("exit")) {
                    break;
                }
            }

            socket.close();

            System.out.println("Соединение закрыто.");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
