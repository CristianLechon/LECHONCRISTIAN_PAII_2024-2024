package ec.edu.uce.ProyectoSolo.controller;

import ec.edu.uce.ProyectoSolo.model.Person;
import ec.edu.uce.ProyectoSolo.model.Process;
import ec.edu.uce.ProyectoSolo.model.Product;
import ec.edu.uce.ProyectoSolo.model.Requested;
import ec.edu.uce.ProyectoSolo.service.PersonService;
import ec.edu.uce.ProyectoSolo.service.ProcessService;
import ec.edu.uce.ProyectoSolo.service.ProductService;
import ec.edu.uce.ProyectoSolo.service.RequestedService;
import ec.edu.uce.ProyectoSolo.view.JframeRegisterPerson;
import ec.edu.uce.ProyectoSolo.view.ViewAdmin;
import ec.edu.uce.ProyectoSolo.view.ViewPedidos;
import ec.edu.uce.ProyectoSolo.view.ViewPerson;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import javax.swing.*;
import java.util.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Controller
public class Container {

    private PersonService personService;
    private ProductService productService;
    private RequestedService requestedService;
    private ProcessService processService;
    private List<ScheduledExecutorService> executorServices = new ArrayList<>();
    private List<ViewPedidos> pedidosUIS = new ArrayList<>();
    private Person person;

    @Autowired
    public Container(PersonService personService, ProductService productService, RequestedService requestedService, ProcessService processService) {
        this.personService = personService;
        this.productService = productService;
        this.requestedService = requestedService;
        this.processService = processService;
        person = new Person();
    }

    public void startViewRegister() {
        new JframeRegisterPerson(this).setVisible(true);
    }

    public Optional<Product> getOneProductById(Long id) {
        return productService.findById(id);
    }

    public void registerPerson(Person person) {
        personService.savePerson(person);
        JOptionPane.showMessageDialog(null, "Registro exitoso", "Registro", JOptionPane.INFORMATION_MESSAGE);
    }

    public void authenticate(String username, String password) {

        Optional<Person> optionalPerson = personService.findByUsernameAndPassword(username, password);

        if (optionalPerson.isPresent()) {
            JOptionPane.showMessageDialog(null, "Credenciales correctas", "Autenticación Correcta", JOptionPane.INFORMATION_MESSAGE);
            this.person = optionalPerson.get();
            String rol = person.getRol();
            if (rol.equals("Administrador")) {
                startAdmin();
            } else if (rol.equals("Cliente")) {
                startClient();
            }
        } else {
            JOptionPane.showMessageDialog(null, "Credenciales incorrectas", "Error de autenticación", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void realizarCompra(Map<Product, Integer> productosSeleccionados) {
        Long Id = person.getId();
        if (Id == null) {
            JOptionPane.showMessageDialog(null, "No se pudo encontrar el usuario.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String estado = "Pendiente";

        try {
            for (Map.Entry<Product, Integer> entry : productosSeleccionados.entrySet()) {
                Product producto = entry.getKey();
                int cantidad = entry.getValue();
                for (int i = 0; i < cantidad; i++) {
                    requestedService.create(person, producto, estado);
                }
            }
            JOptionPane.showMessageDialog(null, "Compra realizada con éxito.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al realizar la compra.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public List<String> getPendingRequestsDetails() {
        return requestedService.findByEstado("pendiente").stream()
                .map(requested -> {
                    String clientName = personService.findById(requested.getPerson().getId()).map(Person::getName).orElse("Desconocido");
                    String productName = productService.findById(requested.getProduct().getId()).map(Product::getName).orElse("Desconocido");
                    Long requestId = requested.getId();
                    return "Solicitud: " + requestId + " - Cliente: " + clientName + " - Producto: " + productName;
                })
                .collect(Collectors.toList());
    }

    public void eliminateProductForId(String selectedProductName) {
        getPendingRequestsDetails().stream()
                .filter(details -> details.contains(selectedProductName))
                .findFirst()
                .ifPresentOrElse(details -> {
                    String[] parts = details.split(" - ");
                    String idPart = parts[0];
                    String[] idParts = idPart.split(": ");
                    if (idParts.length == 2) {
                        try {
                            Long requestId = Long.parseLong(idParts[1].trim());
                            requestedService.deleteById(requestId);
                            JOptionPane.showMessageDialog(null, "La solicitud con ID " + requestId + " ha sido eliminada.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                        } catch (NumberFormatException e) {
                            System.err.println("No se pudo convertir '" + idParts[1].trim() + "' a Long.");
                        }
                    } else {
                        System.err.println("Formato incorrecto para idPart: " + idPart);
                    }
                }, () -> JOptionPane.showMessageDialog(null, "No se encontraron solicitudes pendientes para el producto seleccionado.", "Error", JOptionPane.ERROR_MESSAGE));
    }

    public void fabricarProducto(String selectedProductName) {
        getPendingRequestsDetails().stream()
                .filter(details -> details.contains(selectedProductName))
                .findFirst()
                .ifPresentOrElse(details -> {
                    String[] parts = details.split(" - ");
                    String idPart = parts[0];
                    String[] idParts = idPart.split(": ");
                    if (idParts.length == 2) {
                        try {
                            Long requestId = Long.parseLong(idParts[1].trim());
                            Requested requestToFabricate = requestedService.findById(requestId).orElse(null);
                            if (requestToFabricate != null) {
                                requestToFabricate.setState("Fabricando...");
                                requestedService.save(requestToFabricate);
                                Thread time = new Thread(() -> {
                                    try {
                                        // Pausar el hilo por 1000 milisegundos (1 segundo)
                                        Process process = new Process();
                                        Thread.sleep(10000);
                                    } catch (InterruptedException e) {
                                        e.printStackTrace();
                                    }
                                    if (requestToFabricate.getState().equals("Fabricando...")) {
                                        requestToFabricate.setState("Terminado");
                                        requestedService.save(requestToFabricate);
                                    }
                                });
                                time.start();
                                JOptionPane.showMessageDialog(null, "El producto seleccionado ha sido marcado como 'Fabricando...'.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                            }
                        } catch (NumberFormatException e) {
                            System.err.println("No se pudo convertir '" + idParts[1].trim() + "' a Long.");
                        }
                    } else {
                        System.err.println("Formato incorrecto para idPart: " + idPart);
                    }
                }, () -> JOptionPane.showMessageDialog(null, "No se encontraron solicitudes pendientes para el producto seleccionado.", "Error", JOptionPane.ERROR_MESSAGE));
    }

    public void processMaterial(String material, Long processId) {
        try {
            productService.addProcessToProductsByMaterial(material, processId);
            System.out.println("Process added to products with material: " + material);
        } catch (EntityNotFoundException e) {
            System.err.println(e.getMessage());
        } catch (Exception e) {
            System.err.println("An error occurred: " + e.getMessage());
        }
    }

    public List<String> getMakingRequestsDetails() {
        return requestedService.findByEstado("Fabricando...").stream()
                .map(requested -> {
                    String clientName = personService.findById(requested.getPerson().getId()).orElseThrow().getName();
                    Long productId = requested.getProduct().getId();
                    String productName = productService.findByIdWithProcesses(productId).getName();
                    Long requestId = requested.getId();

                    Set<Process> processes = productService.findByIdWithProcesses(productId).getProcess();
                    String processDetails = processes.stream()
                            .map(process -> "Proceso: " + process.getNameProcess() + ", Material: " + process.getNameMaterial() + ", Tiempo: " + process.getTime())
                            .collect(Collectors.joining("; "));

                    return "Solicitud: " + requestId + " - Cliente: " + clientName + " - Producto: " + productName + " - Procesos: [" + processDetails + "]" ;
                })
                .collect(Collectors.toList());
    }

    public List<Requested> getRequestedByUserId(Long personId) {
        return requestedService.findByPersonId(personId);
    }

    public void startBackgroundUpdate(Runnable updateTask) {
        ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
        executorService.scheduleAtFixedRate(updateTask, 0, 1, TimeUnit.SECONDS);
        executorServices.add(executorService);
    }

    public void changePrice(String name, String material, double newPrice) {
        productService.updateProductPrice(name, material, newPrice);
    }

    public void updateProcessTime(String name, Double newTime) {
        processService.updateProcessTime(name, newTime);
    }

    public void addProduct(Product product) {
        productService.saveProduct(product);
    }

    public Process addProcess(String name, String material, double time) {
        Process proceso = new Process(name,material,time);
        return processService.saveProcess(proceso);
    }

    public List<Process> getAllProcess() {
        return processService.getAllProcesses();
    }

    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    public void startViewPedidos() {
        pedidosUIS.add(new ViewPedidos(this));
    }

    private void startClient() {
        new ViewPerson(this).setVisible(true);
    }

    private void startAdmin() {
        new ViewAdmin(this).setVisible(true);
    }

    public String personName() {
        return person.getName();
    }

    public Long personId() {
        return person.getId();
    }
}
