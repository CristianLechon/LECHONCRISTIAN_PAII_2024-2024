package controller;

import models.ObserverClass;
import models.Task;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class Controlador {

    ExecutorService executors; //Hilo
    Task task;

    public Controlador() {
        executors = Executors.newSingleThreadExecutor();
        task = new Task();//Tarea
        executors.submit(task); //Asignar Tarea a hilo
        executors.shutdown();//

    }

    public void crearHiloUsuario() {
       task.agregarObserver();
       listarHiloUsuario();
    }

    public String listarHiloUsuario() {
        return task.getSistemaVenta().listarConexiones();
    }

    public void comprarTickets(int numTickets) {
        ObserverClass primerHilo = (ObserverClass) task.getSistemaVenta().getPoolObservers().getFirst();
        task.getSistemaVenta().getPoolObservers().remove(primerHilo);
        task.getSistemaVenta().setTotalTickets(task.getSistemaVenta().getTotalTickets() - numTickets);
        listarHiloUsuario();
    }

    public int getTotalTickets() {
        return task.getSistemaVenta().getTotalTickets();
    }

    public void evaluarTickets() {
        if (task.getSistemaVenta().getTotalTickets() <= 0)
            task.getSistemaVenta().notificarTodos();
    }
}
