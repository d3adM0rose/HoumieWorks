public class Main {

    public static void main(String[] args)
            throws Exception {

        APIClient apiClient = new APIClient();

        var getResponse = apiClient.get(
                "https://jsonplaceholder.typicode.com/posts/1"
        );

        System.out.println("GET: " + getResponse.statusCode());
        System.out.println(getResponse.body());

        String json = """
                {
                    "title": "Test post",
                    "body": "Hello from Java",
                    "userId": 1
                }
                """;

        var postResponse = apiClient.post(
                "https://jsonplaceholder.typicode.com/posts",
                json
        );

        System.out.println("POST: " + postResponse.statusCode());
        System.out.println(postResponse.body());

        var putResponse = apiClient.put(
                "https://jsonplaceholder.typicode.com/posts/1",
                json
        );

        System.out.println("PUT: " + putResponse.statusCode());
        System.out.println(putResponse.body());

        var deleteResponse = apiClient.delete(
                "https://jsonplaceholder.typicode.com/posts/1"
        );

        System.out.println("DELETE: " + deleteResponse.statusCode());
    }
}
