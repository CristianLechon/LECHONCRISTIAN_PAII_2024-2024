package models;

import interfaces.Observable;

public class Task implements Runnable {

    int contadorUsuarios = 0;
    private Observable sistemaVenta;
    boolean running = true;

    @Override
    public void run() {
        //acceso a db, que se demore mucho en obtener
        System.out.println("Inicio la tarea");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        sistemaVenta = new SubjectObservable();
        System.out.println("Finalizo la tarea");
        
    }

    public void agregarObserver(){
        sistemaVenta.agregarObserver(new ObserverClass(contadorUsuarios++));
    }

    public void setRunning(boolean running) {
        this.running = running;
    }

    public SubjectObservable getSistemaVenta() {
        return (SubjectObservable) sistemaVenta;
    }
}
