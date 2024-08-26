package models;

import interfaces.Observer;

public class ObserverClass implements Observer {

    int id;
    String estadoObserver = "Esperando compra";

    public ObserverClass(int id) {
        this.id = id;
    }

    public int getIdentification() {
        return id;
    }


    @Override
    public String toString() {
        return "Usuario: " + id + " " + estadoObserver;
    }

    @Override
    public void notificar() {
        estadoObserver = "Ya no puede comprar";
    }
}