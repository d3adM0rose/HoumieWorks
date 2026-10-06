import java.io.*;
import java.net.*;

public class EchoClient {

    public static void main(String[] args) {

        String host = "localhost";
        int port = 5000;

        try (
                Socket socket = new Socket(host, port);

                BufferedReader in = new BufferedReader(
                        new InputStreamReader(socket.getInputStream())
                );

                PrintWriter out = new PrintWriter(
                        socket.getOutputStream(), true
                );

                BufferedReader console = new BufferedReader(
                        new InputStreamReader(System.in)
                )
        ) {

            System.out.println("Подключение к серверу установлено.");
            System.out.println("Введите сообщение:");

            String message;

            while (true) {

                System.out.print("> ");
                message = console.readLine();

                out.println(message);

                String response = in.readLine();

                System.out.println("Ответ сервера: " + response);

                if (message.equalsIgnoreCase("exit")) {
                    break;
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
