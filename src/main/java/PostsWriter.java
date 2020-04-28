import com.google.gson.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class PostsWriter {

    public static void splitAndWrite(String result) throws IOException {
        JsonElement tradeElement = JsonParser.parseString(result);
        JsonArray jsonArray = tradeElement.getAsJsonArray();
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        Path postsFolder = createNewFolderForPostsAndGetAbsolutePath();
        String postsFolderString = postsFolder.toString();

        int postIndex = 1, arrayLength = jsonArray.size();
        for (int i = 0; i < arrayLength; i++) {
            Path newPostPath = Paths.get(postsFolderString, "post_" + (postIndex++) + ".json");
            Files.writeString(newPostPath, gson.toJson(jsonArray.get(i)));
        }
    }

    private static Path createNewFolderForPostsAndGetAbsolutePath() throws IOException {
        Path resourceDirectory = Paths.get("src", "main", "resources");
        Path postsFolder = Paths.get(resourceDirectory.toAbsolutePath() + "/posts");
        Files.createDirectories(postsFolder);

        return postsFolder;
    }
}
