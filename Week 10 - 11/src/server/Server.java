package server;

import com.sun.net.httpserver.HttpServer;
import controllers.TodoController;

import java.io.IOException;
import java.net.InetSocketAddress;

public class Server {
    public void start() throws IOException {
        HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);
        server.createContext("/api/todos", new TodoController());
        server.createContext("/api/todos/{id}", new TodoController());
        server.setExecutor(null);
        server.start();
    }
}
