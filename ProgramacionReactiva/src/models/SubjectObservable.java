package models;

import interfaces.Observable;
import interfaces.Observer;

import java.util.ArrayList;
import java.util.List;

public class SubjectObservable implements Observable {

    int totalTickets = 100;
    List<Observer> poolObservers = new ArrayList<>();

    public SubjectObservable() {

    }

    public int getTotalTickets() {
        return totalTickets;
    }

    public List<Observer> getPoolObservers() {
        return poolObservers;
    }

    public void setTotalTickets(int totalTickets) {
        this.totalTickets = totalTickets;
    }


    public String listarConexiones() {
        String listado = " ";
        for (Observer observer : poolObservers) {
            listado += observer.toString() + "\n";
        }
        return listado;
    }

    @Override
    public void notificarTodos() {
        for (Observer observer : poolObservers) {
            observer.notificar();
        }
    }

    @Override
    public void agregarObserver(Observer observer) {
        poolObservers.add(observer);
    }

    @Override
    public void removerObserver(Observer observer) {
        poolObservers.remove(observer);
    }
}
