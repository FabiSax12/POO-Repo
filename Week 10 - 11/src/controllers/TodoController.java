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
        String response = "";
        String method = exchange.getRequestMethod();
        OutputStream out = exchange.getResponseBody();

        System.out.println(method);

        if (method.equals("GET")) {
            DB.loadData();
            response = listTodos().toString();

            exchange.sendResponseHeaders(200, response.length());
            out.write(response.getBytes());
            out.close();
            return;
        }

        if (method.equals("POST")) {
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

            try {
                addTodo(body);
            } catch (JSONException e) {
                System.out.println("JSONException");
                exchange.sendResponseHeaders(400, 0);
                exchange.getResponseBody().write("JSON Inválido".getBytes());
                return;
            }

            exchange.sendResponseHeaders(200, body.length());
            exchange.getResponseBody().write(body.getBytes());
        }

        if (method.equals("DELETE")) {
            String[] paths = exchange.getRequestURI().getPath().split("/");
            String id = paths[paths.length - 1];
            deleteTodo(id);
            exchange.sendResponseHeaders(200, 0);

            response = new JSONObject().put("result", "Todo con el id " + id + " ha sido eliminado correctamente").toString();
            out.write(response.getBytes());
            out.close();
        }
    }

    private JSONObject listTodos() {
        JSONObject response = new JSONObject();
        response.put("data", DB.data.getJSONArray("todos"));
        response.put("status", "OK");
        return response;
    }

    private void addTodo(String body) throws IOException {
        JSONObject inputTodo = new JSONObject(body);

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
    }

    private void deleteTodo(String id) throws IOException {
        // Ejemplo de la clase
        DB.loadData();
        ArrayList<Todo> todos = new ArrayList<>();
        JSONArray jsonArray = DB.data.getJSONArray("todos");

        for (int i = 0; i < jsonArray.length(); i++) {
            if (jsonArray.getJSONObject(i).getString("id").equals(id)) continue;

            todos.add(new Gson().fromJson(
                    jsonArray.getJSONObject(i).toString(),
                    Todo.class
            ));
        }

        DB.data.put("todos", todos);
        DB.saveData();
    }
}
