import Models.Character;
import Models.Enums.Gender;
import Models.Enums.Status;
import org.json.JSONArray;
import org.json.JSONObject;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;

public class Main {
    public static Character mapCharacter(JSONObject object) {
        int id = object.getInt("id");
        String name = object.getString("name");
        Status status = object.getEnum(Status.class, "status");
        Gender gender = object.getEnum(Gender.class, "gender");
        String species = object.getString("species");
        String type = object.getString("type");

        JSONObject origin = object.getJSONObject("origin");
        String originName = origin.getString("name");

        JSONArray episodesJSON = object.getJSONArray("episode");
        ArrayList<String> episodes = new ArrayList<>();

        for (int i = 0; i < episodesJSON.length(); i++) episodes.add(episodesJSON.getString(i));

        return new Character(id, name, status, gender, species, type, originName, episodes);
    }

    public static void main(String[] args) {
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create("https://rickandmortyapi.com/api/character"))
                .build();

        try {
            HttpResponse<String> response = HttpClient.newHttpClient()
                    .send(request, HttpResponse.BodyHandlers.ofString());

            JSONObject obj = new JSONObject(response.body());
            JSONArray results = (JSONArray) obj.get("results");

            ArrayList<Character> charactersList = new ArrayList<>();

            for (int i = 0; i < results.length(); i++) {
                JSONObject character = (JSONObject) results.get(i);
                charactersList.add(mapCharacter(character));
            }

            for (Character character : charactersList) character.print();

        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }
}