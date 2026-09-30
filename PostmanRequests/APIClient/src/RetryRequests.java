import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class RetryRequests {

    private static final int MAX_RETRIES = 3;
    private static final int RETRY_DELAY_MS = 1000;

    public static void main(String[] args) throws Exception {

        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://jsonplaceholder.typicode.com/posts/1"))
                .GET()
                .build();

        HttpResponse<String> response = sendWithRetry(client, request);

        System.out.println("Итоговый статус: " + response.statusCode());
        System.out.println("Ответ: " + response.body());
    }

    public static HttpResponse<String> sendWithRetry(
            HttpClient client,
            HttpRequest request
    ) throws IOException, InterruptedException {

        HttpResponse<String> response = null;

        for (int attempt = 1; attempt <= MAX_RETRIES; attempt++) {

            System.out.println("Попытка №" + attempt);

            response = client.send(
                    request,
                    HttpResponse.BodyHandlers.ofString()
            );

            int statusCode = response.statusCode();

            if (statusCode < 500 || statusCode >= 600) {
                return response;
            }

            if (attempt < MAX_RETRIES) {
                System.out.println(
                        "Получен статус " + statusCode +
                                ". Повтор через 1 секунду..."
                );

                Thread.sleep(RETRY_DELAY_MS);
            }
        }

        return response;
    }
}
