package ec.edu.uce.Hilos.model;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class ExecutorServiceExample {

    public static void main(String[] args) {
        // Crear un ExecutorService con un pool de 3 hilos
        ExecutorService executorService = Executors.newFixedThreadPool(3);

        try {
            // Tarea sincrónica: Ejecutar un Runnable (no retorna resultado)
            Runnable syncTask = () -> {
                try {
                    System.out.println("Tarea sincrónica iniciada...");
                    TimeUnit.SECONDS.sleep(10);  // Simula una tarea que tarda 2 segundos
                    System.out.println("Tarea sincrónica completada");
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            };
            executorService.submit(syncTask);

            // Tarea asincrónica: Ejecutar un Callable (retorna resultado)
            Callable<String> asyncTask = () -> {
                System.out.println("Tarea asincrónica iniciada...");
                TimeUnit.SECONDS.sleep(20);  // Simula una tarea que tarda 3 segundos
                return "Tarea asincrónica completada";
            };

            // Enviar la tarea al ExecutorService y obtener un Future
            Future<String> futureResult = executorService.submit(asyncTask);

            // Hacer algo mientras la tarea asincrónica se completa
            System.out.println("Haciendo otras tareas en el hilo principal...");

            // Obtener el resultado de la tarea asincrónica (bloquea hasta que se complete)
            String result = futureResult.get();
            System.out.println(result);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Shutdown del ExecutorService
            executorService.shutdown();  // No acepta nuevas tareas, pero las que están en cola se completan
            try {
                if (!executorService.awaitTermination(5, TimeUnit.SECONDS)) {
                    executorService.shutdownNow();  // Fuerza la terminación si las tareas no completan en el tiempo dado
                }
            } catch (InterruptedException e) {
                executorService.shutdownNow();
            }
            System.out.println("ExecutorService terminado.");
        }
    }
}

