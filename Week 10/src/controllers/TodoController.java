package controllers;

import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import database.DB;
import models.Todo;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.*;
import java.util.ArrayList;

public class TodoController implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        if (exchange.getRequestMethod().equals("GET")) {
            DB.loadData();
            String response = listTodos().toString();

            exchange.sendResponseHeaders(200, response.length());
            OutputStream out = exchange.getResponseBody();
            out.write(response.getBytes());
            out.close();
            return;
        }

        if (exchange.getRequestMethod().equals("POST")) {
            // Tomado de StackOverflow
            InputStreamReader isr =  new InputStreamReader(exchange.getRequestBody(),"utf-8");
            BufferedReader br = new BufferedReader(isr);

            StringBuilder buf = new StringBuilder(512);
            int b;
            while ((b = br.read()) != -1) buf.append((char) b);

            br.close();
            isr.close();

            // Propio
            String body = buf.toString();
            JSONObject inputTodo;

            try {
                inputTodo = new JSONObject(body);
            } catch (JSONException e) {
                System.out.println("JSONException");
                exchange.sendResponseHeaders(400, 0);
                exchange.getResponseBody().write("JSON Inválido".getBytes());
                return;
            }

            // Ejemplo de la clase
            DB.loadData();
            ArrayList<Todo> todos = new ArrayList<>();
            JSONArray jsonArray = DB.data.getJSONArray("todos");

            for (int i = 0; i < jsonArray.length(); i++) {
                todos.add(new Gson().fromJson(
                        jsonArray.getJSONObject(i).toString(),
                        Todo.class
                ));
            }

            Todo newTodo = new Todo(
                    inputTodo.has("id") ? inputTodo.getString("id") : "",
                    inputTodo.getString("title"),
                    inputTodo.getBoolean("completed")
            );

            todos.add(newTodo);
            DB.data.put("todos", todos);
            DB.saveData();

            exchange.sendResponseHeaders(200, body.length());
            exchange.getResponseBody().write(body.getBytes());
        }
    }

    public JSONObject listTodos() {
        JSONObject response = new JSONObject();
        response.put("data", DB.data.getJSONArray("todos"));
        response.put("status", "OK");
        return response;
    }
}
