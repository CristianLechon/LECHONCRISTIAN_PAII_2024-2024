package ec.edu.uce.ProyectoSolo.view;

import ec.edu.uce.ProyectoSolo.controller.Container;
import ec.edu.uce.ProyectoSolo.model.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;

public class ViewPerson extends JFrame {

    private final Container container;
    private static final Logger log = LoggerFactory.getLogger(ViewPerson.class);
    private static final ExecutorService executorService = Executors.newSingleThreadExecutor();
    private final DefaultListModel<String> carritoModel;
    private final Map<Product, Integer> productosSeleccionados;
    private final JLabel jtTotalCarrito;

    public ViewPerson(Container container) {
        this.container = container;
        carritoModel = new DefaultListModel<>();
        productosSeleccionados = new HashMap<>();

        setTitle("Client");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setSize(screenSize.width, screenSize.height);

        int x = screenSize.width / 96;
        int y = screenSize.height / ((screenSize.height * 96) / screenSize.width);

        //Lienzo Princpal
        JPanel lienzoPrincipal = new JPanel();
        lienzoPrincipal.setLayout(null);
        lienzoPrincipal.setBackground(new Color(255, 255, 255, 155));

        //Panel Encabezado y sus componentes
        JPanel panelEncabezado = new JPanel();
        panelEncabezado.setLayout(null);
        panelEncabezado.setBounds(-3, 0, (96 * x) + 6, 6 * y);
        panelEncabezado.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));

        // Nombre del Clientes
        JLabel jlClient = new JLabel("Bienvenido " + container.personName() + "!");
        jlClient.setBounds(2 * x, y, 53 * x, 4 * y);
        jlClient.setFont(new Font("Consolas", Font.BOLD + Font.ITALIC, 35));
        jlClient.setHorizontalAlignment(SwingConstants.CENTER);
        panelEncabezado.add(jlClient);

        // Boton de Salir
        JButton jbSalir = new JButton("Salir");
        jbSalir.setBounds(83 * x, y, 10 * x, 4 * y);
        jbSalir.setContentAreaFilled(false);
        jbSalir.setBorderPainted(true);
        jbSalir.setFont(new Font("Consolas", Font.BOLD + Font.ITALIC, 30));

        jbSalir.addActionListener(e -> dispose());

        // Boton para ver pedidos pendientes
        JButton jbPendientes = new JButton("Pedidos");
        jbPendientes.setBounds(65 * x, y, 15 * x, 4 * y);
        jbPendientes.setContentAreaFilled(false);
        jbPendientes.setBorderPainted(true);
        jbPendientes.setFont(new Font("Consolas", Font.BOLD + Font.ITALIC, 30));

        jbPendientes.addActionListener(e -> {
            container.startViewPedidos();
        });

        panelEncabezado.add(jbSalir);
        panelEncabezado.add(jbPendientes);
        lienzoPrincipal.add(panelEncabezado);

        //Panel de Productos
        JPanel productos = new JPanel();
        productos.setLayout(null);
        productos.setOpaque(false);
        productos.setBounds(2 * x, 7 * y, 92 * x, 32 * y);
        productos.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        lienzoPrincipal.add(productos);

        // Carrito y sus funcionalidad

        // Icono Carrito
        JLabel jlCarrito = new JLabel("carrito");
        jlCarrito.setBounds(2 * x, 42 * y + (y / 2), 6 * x, 5 * y);
        lienzoPrincipal.add(jlCarrito);

        JList<String> jtCarrito = new JList<>(carritoModel);
        jtCarrito.setFont(new Font("Consolas", Font.BOLD + Font.ITALIC, 20));
        jtCarrito.setOpaque(false);
        jtCarrito.setBackground(new Color(235, 235, 220));

        jtCarrito.setPreferredSize(new Dimension(23 * x, 20 * y));
        JScrollPane jsCarrito = new JScrollPane(jtCarrito);
        jsCarrito.setBounds(9 * x, 40 * y, 25 * x, 10 * y);
        jsCarrito.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        jsCarrito.setBackground(new Color(235, 235, 220));
        jsCarrito.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        lienzoPrincipal.add(jsCarrito);

        //Total Carrito
        jtTotalCarrito = new JLabel();
        jtTotalCarrito.setBounds(9 * x, 50 * y, 25 * x, 2 * y);
        jtTotalCarrito.setFont(new Font("Consolas", Font.BOLD + Font.ITALIC, 25));
        jtTotalCarrito.setBackground(new Color(235, 235, 220));
        jtTotalCarrito.setHorizontalAlignment(SwingConstants.RIGHT);
        jtTotalCarrito.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        lienzoPrincipal.add(jtTotalCarrito);
        jtTotalCarrito.setText("Total: $0.00");

        // Boton Reinciar Carrito
        JButton jbReiniciar = new JButton("Reiniciar Carrito");
        jbReiniciar.setBounds(37 * x, 41 * y, 20 * x, 5 * y);
        jbReiniciar.setFont(new Font("Consolas", Font.BOLD + Font.ITALIC, 20));
        lienzoPrincipal.add(jbReiniciar);

        jbReiniciar.addActionListener(e -> {
            carritoModel.clear();
            productosSeleccionados.clear();
            jtTotalCarrito.setText("Total: $0.00");
        });

        // Boton Comprar
        JButton jbComprar = new JButton("Comprar");
        jbComprar.setBounds(37 * x, 47 * y, 20 * x, 5 * y);
        jbComprar.setFont(new Font("Consolas", Font.BOLD + Font.ITALIC, 20));
        lienzoPrincipal.add(jbComprar);

        jbComprar.addActionListener(e -> {
            container.realizarCompra(productosSeleccionados);
            carritoModel.clear();
            productosSeleccionados.clear();
            jtTotalCarrito.setText("Total: $0.00");
        });

        // Botón para actualizar productos
        JButton jbActualizar = new JButton("Actualizar Productos");
        jbActualizar.setBounds(59 * x, 41 * y, 20 * x, 5 * y);
        jbActualizar.setFont(new Font("Consolas", Font.BOLD + Font.ITALIC, 20));
        lienzoPrincipal.add(jbActualizar);

        jbActualizar.addActionListener(e -> cargarProductos(productos));

        getContentPane().add(lienzoPrincipal);

    }

    private void cargarProductos(JPanel productosPanel) {
        productosPanel.removeAll();

        // Crear una tarea asíncrona para cargar productos
        Callable<List<Product>> cargarProductosTask = () -> {
            try {
                return container.getAllProducts();
            } catch (Exception e) {
                log.error("Error loading products", e);
                return null;
            }
        };

        // Enviar la tarea al ExecutorService del controlador
        Future<List<Product>> future = executorService.submit(cargarProductosTask);

        // Crear una tarea para actualizar la UI una vez que los productos se hayan cargado
        SwingUtilities.invokeLater(() -> {
            try {
                List<Product> productos = future.get();

                if (productos != null) {
                    int y = 10;
                    for (Product producto : productos) {
                        JLabel productoLabel = new JLabel(producto.getName() + " - " + producto.getMaterial() + " - $" + producto.getPrice());
                        productoLabel.setBounds(10, y, productosPanel.getWidth() - 20, 30);
                        productoLabel.setOpaque(true);
                        productoLabel.setBackground(new Color(235, 235, 220));
                        productoLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));

                        // Añadir MouseListener para seleccionar producto
                        productoLabel.addMouseListener(new MouseAdapter() {
                            @Override
                            public void mouseClicked(MouseEvent e) {
                                productosSeleccionados.put(producto, productosSeleccionados.getOrDefault(producto, 0) + 1);
                                actualizarCarrito();
                            }
                        });

                        productosPanel.add(productoLabel);
                        y += 40;
                    }
                    productosPanel.revalidate();
                    productosPanel.repaint();
                }
            } catch (InterruptedException | ExecutionException e) {
                log.error("Error retrieving products", e);
            }
        });
    }

    private String calcularTotal() {
        double total = productosSeleccionados.entrySet().stream()
                .mapToDouble(entry -> entry.getKey().getPrice() * entry.getValue())
                .sum();
        return String.format("$%.2f", total);
    }

    private void actualizarCarrito() {
        carritoModel.clear();
        for (Map.Entry<Product, Integer> entry : productosSeleccionados.entrySet()) {
            Product producto = entry.getKey();
            int cantidad = entry.getValue();
            carritoModel.addElement(producto.getName() + " - $" + producto.getPrice() + " x " + cantidad);
        }
        jtTotalCarrito.setText("Total: " + calcularTotal());
    }

}
