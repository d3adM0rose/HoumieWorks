import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.CompletableFuture;

public class AsyncRequests {

    public static void main(String[] args) {

        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request1 = HttpRequest.newBuilder()
                .uri(URI.create("https://jsonplaceholder.typicode.com/posts/1"))
                .GET()
                .build();

        HttpRequest request2 = HttpRequest.newBuilder()
                .uri(URI.create("https://jsonplaceholder.typicode.com/posts/2"))
                .GET()
                .build();

        HttpRequest request3 = HttpRequest.newBuilder()
                .uri(URI.create("https://jsonplaceholder.typicode.com/posts/3"))
                .GET()
                .build();

        CompletableFuture<HttpResponse<String>> future1 =
                client.sendAsync(request1, HttpResponse.BodyHandlers.ofString());

        CompletableFuture<HttpResponse<String>> future2 =
                client.sendAsync(request2, HttpResponse.BodyHandlers.ofString());

        CompletableFuture<HttpResponse<String>> future3 =
                client.sendAsync(request3, HttpResponse.BodyHandlers.ofString());

        CompletableFuture.allOf(future1, future2, future3)
                .thenRun(() -> {
                    try {
                        System.out.println("Response 1: " + future1.join().statusCode());
                        System.out.println("Response 2: " + future2.join().statusCode());
                        System.out.println("Response 3: " + future3.join().statusCode());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                })
                .join();
    }
}
