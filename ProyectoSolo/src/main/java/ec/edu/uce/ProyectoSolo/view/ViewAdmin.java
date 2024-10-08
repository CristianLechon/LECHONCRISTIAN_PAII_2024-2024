package ec.edu.uce.ProyectoSolo.view;

import ec.edu.uce.ProyectoSolo.controller.Container;
import ec.edu.uce.ProyectoSolo.model.Process;
import ec.edu.uce.ProyectoSolo.model.Product;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class ViewAdmin extends JFrame {
    private Container container;
    private JPanel panelFabricacion;
    private String selectedProduct;
    private List<String> productosPendientes;
    private final DefaultListModel<String> modelo;
    private DefaultListModel<String> modeloProcesos;
    private DefaultListModel<String> modeloProductos;
    private JScrollPane scrollPanelFabricacion;

    public ViewAdmin(Container container) {
        this.container = container;
        setTitle("Admin");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        modelo = new DefaultListModel<>();
        modeloProcesos = new DefaultListModel<>();
        modeloProductos = new DefaultListModel<>();

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setSize(screenSize.width, screenSize.height);

        int x = screenSize.width / 96;
        int y = screenSize.height / ((screenSize.height * 96) / screenSize.width);

        // Panel que contiene
        JPanel panelPrincipal = new JPanel();
        panelPrincipal.setLayout(new BorderLayout());
        panelPrincipal.setBackground(new Color(255, 255, 255, 155));

        // Panel del encabezado
        JPanel panelEncabezado = new JPanel();
        panelEncabezado.setLayout(null);
        panelEncabezado.setPreferredSize(new Dimension(screenSize.width, 6 * y));
        panelEncabezado.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));

        // Bienvenida al usuario
        JLabel jlClient = new JLabel("Bienvenido " + container.personName());
        jlClient.setBounds(x, y, 62 * x, 4 * y);
        jlClient.setFont(new Font("Consola", Font.BOLD + Font.ITALIC, 40));
        jlClient.setHorizontalAlignment(SwingConstants.CENTER);
        jlClient.setVerticalAlignment(SwingConstants.CENTER);
        panelEncabezado.add(jlClient);

        JButton botonSalir = new JButton("Salir");
        botonSalir.setBounds(81 * x, y, 14 * x, 4 * y);
        botonSalir.setContentAreaFilled(false);
        botonSalir.setBorderPainted(true);
        panelEncabezado.add(botonSalir);

        botonSalir.addActionListener(e -> dispose());

        panelPrincipal.add(panelEncabezado, BorderLayout.NORTH);

        // Configurar las pestañas
        JTabbedPane tabbedPane = new JTabbedPane();

        // Panel de Fabricación
        JPanel tabFabricacion = new JPanel();
        tabFabricacion.setLayout(null);
        tabFabricacion.setBackground(new Color(245, 245, 220));

        // Configurar la lista de productos pendientes

        JList<String> jlProductosPendientes = new JList<>(modelo);
        productosPendientes = container.getPendingRequestsDetails();

        JScrollPane jsProductosPendientes = new JScrollPane(jlProductosPendientes);
        jsProductosPendientes.setBounds(2 * x, 2 * y, 44 * x, 44 * y);
        jsProductosPendientes.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        jsProductosPendientes.setOpaque(false);
        tabFabricacion.add(jsProductosPendientes);

        jlProductosPendientes.setFont(new Font("Georgia", Font.BOLD + Font.ITALIC, 20));
        jlProductosPendientes.setOpaque(false);
        jlProductosPendientes.setPreferredSize(new Dimension(44 * x, 100 * y));

        jlProductosPendientes.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                selectedProduct = jlProductosPendientes.getSelectedValue();
                updateFabricationPanel();
                jlProductosPendientes.repaint();
            }
        });

        panelFabricacion = new JPanel();
        panelFabricacion.setLayout(new BoxLayout(panelFabricacion, BoxLayout.Y_AXIS));
        panelFabricacion.setBackground(new Color(245, 245, 220));

        scrollPanelFabricacion = new JScrollPane(panelFabricacion);
        scrollPanelFabricacion.setBounds(48 * x, 3 * y, 46 * x, 30 * y);
        scrollPanelFabricacion.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        scrollPanelFabricacion.setBackground(new Color(245, 245, 220));
        scrollPanelFabricacion.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        tabFabricacion.add(scrollPanelFabricacion);

        // Configurar botones en la pestaña de Fabricación
        JButton jbEliminar = new JButton("Eliminar");
        jbEliminar.setBounds(59 * x, 41 * y, 24 * x, 5 * y);
        jbEliminar.setFont(new Font("Georgia", Font.BOLD + Font.ITALIC, 20));
        jbEliminar.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        tabFabricacion.add(jbEliminar);

        jbEliminar.addActionListener(e -> {
            if (selectedProduct != null) {
                container.eliminateProductForId(selectedProduct);
                productosPendientes = container.getPendingRequestsDetails();
                modelo.clear();
                for (String producto : productosPendientes) {
                    modelo.addElement(producto);
                }
                jlProductosPendientes.setModel(modelo);
                updateFabricationPanel();
            } else {
                JOptionPane.showMessageDialog(null, "No se encontraron solicitudes pendientes para el producto seleccionado.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        JButton jbFabricar = new JButton("Fabricar");
        jbFabricar.setBounds(59 * x, 34 * y, 24 * x, 5 * y);
        jbFabricar.setFont(new Font("Consola", Font.BOLD + Font.ITALIC, 20));
        jbFabricar.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        tabFabricacion.add(jbFabricar);

        jbFabricar.addActionListener(e -> {
            if (selectedProduct != null) {
                container.fabricarProducto(selectedProduct);
                productosPendientes = container.getPendingRequestsDetails();
                modelo.clear();
                for (String producto : productosPendientes) {
                    modelo.addElement(producto);
                }
                jlProductosPendientes.setModel(modelo);
                updateFabricationPanel();
            } else {
                JOptionPane.showMessageDialog(null, "No se encontraron solicitudes pendientes para el proyecto seleccionado.", "Error", JOptionPane.ERROR_MESSAGE);
            }
            //controler.notifyObservers();
        });

        tabbedPane.addTab("Fabricación", tabFabricacion);

        // Panel de Procesos (vacío por ahora)
        JPanel tabProcesos = new JPanel();
        tabProcesos.setLayout(new BorderLayout());
        tabProcesos.setBackground(new Color(245, 245, 220));
        tabbedPane.addTab("Procesos", tabProcesos);

        JPanel panelNuevoProceso = new JPanel();
        panelNuevoProceso.setLayout(new GridLayout(3, 2, 10, 10));
        panelNuevoProceso.setBorder(BorderFactory.createTitledBorder("Ingresar Nuevo Proceso"));
        panelNuevoProceso.setBackground(new Color(245, 245, 220));

        JLabel lblNombre = new JLabel("Nombre:");
        JTextField txtNombre = new JTextField();
        JLabel lblTiempo = new JLabel("Tiempo:");
        JTextField txtTiempo = new JTextField();
        JButton btnIngresar = new JButton("Ingresar");

        panelNuevoProceso.add(lblNombre);
        panelNuevoProceso.add(txtNombre);
        panelNuevoProceso.add(lblTiempo);
        panelNuevoProceso.add(txtTiempo);
        panelNuevoProceso.add(new JLabel());
        panelNuevoProceso.add(btnIngresar);

        // Acción del botón Ingresar
        btnIngresar.addActionListener(e -> {
            String nombre = txtNombre.getText();
            String tiempoStr = txtTiempo.getText();
            try {
                int tiempo = Integer.parseInt(tiempoStr);

                // Crear un panel para el JOptionPane
                JPanel panel = new JPanel(new GridLayout(2, 1));
                JLabel lblMaterial = new JLabel("A qué material pertenece este proceso:");
                JTextField txtMaterial = new JTextField();

                panel.add(lblMaterial);
                panel.add(txtMaterial);

                int result = JOptionPane.showConfirmDialog(null, panel, "Ingresar Material", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);

                if (result == JOptionPane.OK_OPTION) {
                    String material = txtMaterial.getText();
                    long id = container.addProcess(nombre, material, tiempo).getId();
                    container.processMaterial(material, id);
                    JOptionPane.showMessageDialog(null, "Proceso ingresado correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "Proceso cancelado.", "Cancelación", JOptionPane.WARNING_MESSAGE);
                }

                txtNombre.setText("");
                txtTiempo.setText("");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Por favor ingrese un tiempo válido.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        // Panel para modificar el tiempo de un proceso existente
        JPanel panelModificarProceso = new JPanel();
        panelModificarProceso.setLayout(new GridLayout(3, 2, 10, 10));
        panelModificarProceso.setBorder(BorderFactory.createTitledBorder("Modificar Tiempo de Proceso"));
        panelModificarProceso.setBackground(new Color(245, 245, 220));

        JLabel lblNombreModificar = new JLabel("Nombre:");
        JTextField txtNombreModificar = new JTextField();
        JLabel lblNuevoTiempo = new JLabel("Nuevo Tiempo:");
        JTextField txtNuevoTiempo = new JTextField();
        JButton btnActualizar = new JButton("Actualizar");

        panelModificarProceso.add(lblNombreModificar);
        panelModificarProceso.add(txtNombreModificar);
        panelModificarProceso.add(lblNuevoTiempo);
        panelModificarProceso.add(txtNuevoTiempo);
        panelModificarProceso.add(new JLabel());
        panelModificarProceso.add(btnActualizar);

        btnActualizar.addActionListener(e -> {
            String nombreModificar = txtNombreModificar.getText();
            String nuevoTiempoStr = txtNuevoTiempo.getText();
            try {
                double nuevoTiempo = Double.parseDouble(nuevoTiempoStr);
                container.updateProcessTime(nombreModificar, nuevoTiempo);

                JOptionPane.showMessageDialog(null, "Tiempo de proceso actualizado correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                txtNombreModificar.setText("");
                txtNuevoTiempo.setText("");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Por favor ingrese un tiempo válido.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });


        JList<String> jlProcesos = new JList<>(modeloProcesos);
        JScrollPane scrollProcesos = new JScrollPane(jlProcesos);
        scrollProcesos.setBorder(BorderFactory.createTitledBorder("Procesos Existentes"));
        scrollProcesos.setBackground(new Color(245, 245, 220));

        tabProcesos.add(panelNuevoProceso, BorderLayout.NORTH);
        tabProcesos.add(scrollProcesos, BorderLayout.CENTER);
        tabProcesos.add(panelModificarProceso, BorderLayout.SOUTH);

        tabbedPane.addTab("Procesos", tabProcesos);
        List<Process> procesos = container.getAllProcess();
        for (Process process : procesos) {
            modeloProcesos.addElement(process.getNameProcess() + "  " + process.getTime());
        }
        jlProcesos.setModel(modeloProcesos);

        JPanel tabProductos = new JPanel();
        tabProductos.setLayout(new BorderLayout());
        tabProductos.setBackground(new Color(245, 245, 220));
        tabbedPane.addTab("Productos", tabProductos);

        // Panel para ingresar un nuevo producto
        JPanel panelNuevoProducto = new JPanel();
        panelNuevoProducto.setLayout(new GridLayout(4, 2, 10, 10));
        panelNuevoProducto.setBorder(BorderFactory.createTitledBorder("Ingresar Nuevo Producto"));
        panelNuevoProducto.setBackground(new Color(245, 245, 220));

        JLabel lblNombreProducto = new JLabel("Nombre:");
        JTextField txtNombreProducto = new JTextField();
        JLabel lblMaterialProducto = new JLabel("Material:");
        JTextField txtMaterialProducto = new JTextField();
        JLabel lblPrecioProducto = new JLabel("Precio:");
        JTextField txtPrecioProducto = new JTextField();
        JButton btnIngresarProducto = new JButton("Ingresar");

        panelNuevoProducto.add(lblNombreProducto);
        panelNuevoProducto.add(txtNombreProducto);
        panelNuevoProducto.add(lblMaterialProducto);
        panelNuevoProducto.add(txtMaterialProducto);
        panelNuevoProducto.add(lblPrecioProducto);
        panelNuevoProducto.add(txtPrecioProducto);
        panelNuevoProducto.add(new JLabel());
        panelNuevoProducto.add(btnIngresarProducto);

        // Acción del botón Ingresar
        btnIngresarProducto.addActionListener(e -> {
            String nombre = txtNombreProducto.getText();
            String material = txtMaterialProducto.getText();
            String precioStr = txtPrecioProducto.getText();
            try {
                double precio = Double.parseDouble(precioStr);
                Product product = new Product(nombre,material,precio);
                container.addProduct(product);
                JOptionPane.showMessageDialog(null, "Producto ingresado correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                txtNombreProducto.setText("");
                txtMaterialProducto.setText("");
                txtPrecioProducto.setText("");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Por favor ingrese un precio válido.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        JPanel panelModificarProducto = new JPanel();
        panelModificarProducto.setLayout(new GridLayout(4, 2, 10, 10));
        panelModificarProducto.setBorder(BorderFactory.createTitledBorder("Modificar Precio de Producto"));
        panelModificarProducto.setBackground(new Color(245, 245, 220));

        JLabel lblNombreModificarProducto = new JLabel("Nombre:");
        JTextField txtNombreModificarProducto = new JTextField();
        JLabel lblMaterialModificarProducto = new JLabel("Material:");
        JTextField txtMaterialModificarProducto = new JTextField();
        JLabel lblNuevoPrecioProducto = new JLabel("Nuevo Precio:");
        JTextField txtNuevoPrecioProducto = new JTextField();
        JButton btnActualizarProducto = new JButton("Actualizar");

        panelModificarProducto.add(lblNombreModificarProducto);
        panelModificarProducto.add(txtNombreModificarProducto);
        panelModificarProducto.add(lblMaterialModificarProducto);
        panelModificarProducto.add(txtMaterialModificarProducto);
        panelModificarProducto.add(lblNuevoPrecioProducto);
        panelModificarProducto.add(txtNuevoPrecioProducto);
        panelModificarProducto.add(new JLabel());
        panelModificarProducto.add(btnActualizarProducto);

        // Acción del botón Actualizar
        btnActualizarProducto.addActionListener(e -> {
            String nombreModificar = txtNombreModificarProducto.getText();
            String materialModificar = txtMaterialModificarProducto.getText();
            String nuevoPrecioStr = txtNuevoPrecioProducto.getText();
            try {
                double nuevoPrecio = Double.parseDouble(nuevoPrecioStr);
                container.changePrice(nombreModificar, materialModificar, nuevoPrecio);
                JOptionPane.showMessageDialog(null, "Precio de producto actualizado correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                txtNombreModificarProducto.setText("");
                txtMaterialModificarProducto.setText("");
                txtNuevoPrecioProducto.setText("");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Por favor ingrese un precio válido.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        JList<String> jlProductos = new JList<>(modeloProductos);
        JScrollPane scrollProductos = new JScrollPane(jlProductos);
        scrollProductos.setBorder(BorderFactory.createTitledBorder("Productos Existentes"));
        scrollProductos.setBackground(new Color(245, 245, 220));

        tabProductos.add(panelNuevoProducto, BorderLayout.NORTH);
        tabProductos.add(scrollProductos, BorderLayout.CENTER);
        tabProductos.add(panelModificarProducto, BorderLayout.SOUTH);

        tabbedPane.addTab("Productos", tabProductos);
        List<Product> products = container.getAllProducts();
        for (Product product : products) {
            modeloProductos.addElement(product.getName() + "  " + product.getMaterial() + "  " + String.valueOf(product.getPrice()));
        }
        jlProductos.setModel(modeloProductos);

        panelPrincipal.add(tabbedPane, BorderLayout.CENTER);

        getContentPane().add(panelPrincipal);
        setVisible(true);

        actualizaciones();
    }

    private void updateFabricationPanel() {
        panelFabricacion.removeAll();

        JLabel labelTitulo = new JLabel("Detalles de la solicitud en fabricación:");
        labelTitulo.setFont(new Font("Consola", Font.BOLD + Font.ITALIC, 20));
        panelFabricacion.add(labelTitulo);

        List<String> makingRequestsDetails = container.getMakingRequestsDetails();

        for (String details : makingRequestsDetails) {
            JLabel labelDetalles = new JLabel(details);
            labelDetalles.setFont(new Font("Consola", Font.BOLD + Font.ITALIC, 16));
            panelFabricacion.add(labelDetalles);
        }

        panelFabricacion.revalidate();
        panelFabricacion.repaint();
    }

    private void actualizaciones() {
        container.startBackgroundUpdate(this::actualizarListaProcesos);
        container.startBackgroundUpdate(this::actualizarListaProductos);
        container.startBackgroundUpdate(this::actualizarListaProductosPendientes);
    }

    private void actualizarListaProcesos() {
        SwingUtilities.invokeLater(() -> {
            List<Process> proces = container.getAllProcess();
            modeloProcesos.clear();
            for (Process process : proces) {
                modeloProcesos.addElement(process.getNameProcess() + " " + process.getTime());
            }
        });
    }

    private void actualizarListaProductos() {
        SwingUtilities.invokeLater(() -> {
            List<Product> productos = container.getAllProducts();
            modeloProductos.clear();
            for (Product product : productos) {
                modeloProductos.addElement(product.getName() + " " + product.getMaterial() + " " + String.valueOf(product.getPrice()));
            }
        });
    }

    private void actualizarListaProductosPendientes() {
        SwingUtilities.invokeLater(() -> {
            List<String> productosPendientes = container.getPendingRequestsDetails();
            modelo.clear();
            for (String producto : productosPendientes) {
                modelo.addElement(producto);
            }
        });
    }
}
