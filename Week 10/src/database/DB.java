package database;

import org.apache.commons.io.IOUtils;
import org.json.JSONObject;

import java.io.*;
import java.nio.charset.StandardCharsets;

public abstract class DB {
    private static final String filePath = System.getProperty("user.dir") + "\\src\\database\\data.json";
    public static JSONObject data = new JSONObject();

    public static void loadData() throws IOException {
        File file = new File(filePath);
        if (file.exists()){
            InputStream inputStream = new FileInputStream(file);
            String jsonText = IOUtils.toString(inputStream, StandardCharsets.UTF_8);
            data = new JSONObject(jsonText);
        }
    }
    public static void saveData() throws IOException {
        File file = new File(filePath);
        FileOutputStream outputStream = new FileOutputStream(file);
        String jsonText = data.toString();
        outputStream.write(jsonText.getBytes(StandardCharsets.UTF_8));
        outputStream.close();
    }

}
