import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        String response = PostsProvider.get();
        PostsWriter.splitAndWrite(response);
    }
}
