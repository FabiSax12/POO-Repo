/*
* Autores:
* - Fabián Vargas Araya
* - Joseph Salas Rivas
*
* Patrones utilizados:
* - MVC
* - Singletone
* - Strategy
* - Factory
* */

import controller.TaskController;
import model.TaskModel;
import view.TaskView;

public class Main {
    public static void main(String[] args) {
        new TaskController(new TaskView(), new TaskModel());
    }
}
