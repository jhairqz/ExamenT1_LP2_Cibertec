package pe.edu.cibertec.t1.swing;

import pe.edu.cibertec.t1.entity.*;
import pe.edu.cibertec.t1.dao.impl.*;
import pe.edu.cibertec.t1.service.*;
import pe.edu.cibertec.t1.service.impl.*;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class RegistroCertificacion extends JFrame {

    private JComboBox<String> cboClientes;
    private JComboBox<String> cboAuditorias;
    private JComboBox<String> cboEspecialistas;
    private JTextField txtFechaEmision;
    private JTextField txtFechaVencimiento;
    private JComboBox<String> cboEstado;
    private JButton btnRegistrar;
    private JButton btnCancelar;
    private JTable tblCertificaciones;
    private DefaultTableModel modeloTabla;

    private ClienteService clienteService = new ClienteServiceImpl(new ClienteDAOImpl());
    private TipoauditoriaService tipoauditoriaService = new TipoauditoriaServiceImpl(new TipoauditoriaDAOImpl());
    private EspecialistaService especialistaService = new EspecialistaServiceImpl(new EspecialistaDAOImpl());
    private CertificacionService certificacionService = new CertificacionServiceImpl(new CertificacionDAOImpl());

    public RegistroCertificacion() {
        setTitle("Sistema de Certificaciones");
        setSize(715, 350);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        initUI();
    }

    private void initUI() {
        JTabbedPane tabs = new JTabbedPane();
        tabs.addTab("Registrar", crearPanelRegistro());
        tabs.addTab("Listado", crearPanelListado());
        getContentPane().add(tabs);
        cargarClientes();
        cargarTiposAuditoria();
        cargarEspecialistas();
        cargarCertificaciones();
    }

    private JPanel crearPanelRegistro() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(15, 25, 15, 25));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(6, 8, 6, 8);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel lblCliente = new JLabel("Cliente:");
        JLabel lblAuditoria = new JLabel("Tipo de Auditoría:");
        JLabel lblEspecialista = new JLabel("Especialista:");
        JLabel lblEmision = new JLabel("Fecha de Emisión:");
        JLabel lblVencimiento = new JLabel("Fecha de Vencimiento:");
        JLabel lblEstado = new JLabel("Estado:");

        cboClientes = new JComboBox<>();
        cboAuditorias = new JComboBox<>();
        cboEspecialistas = new JComboBox<>();
        txtFechaEmision = new JTextField(30);
        txtFechaEmision.setEditable(false);
        txtFechaVencimiento = new JTextField(30);
        txtFechaVencimiento.setEditable(false);
        cboEstado = new JComboBox<>(new String[]{"Seleccione estado","VIGENTE", "EXPIRADA", "EN_REVISIÓN"});

        Locale locale = new Locale("es", "PE");
        SimpleDateFormat formatoFecha = new SimpleDateFormat("dd 'de' MMMM 'de' yyyy", locale);
        Date hoy = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(hoy);
        txtFechaEmision.setText(formatoFecha.format(hoy));
        cal.add(Calendar.YEAR, 1);
        txtFechaVencimiento.setText(formatoFecha.format(cal.getTime()));

        JButton btnNuevoCliente = new JButton("+ Nuevo Cliente");
        btnNuevoCliente.addActionListener(e -> mostrarDialogoNuevoCliente());

        int row = 0;
        gbc.gridx = 0; gbc.gridy = row;
        panel.add(lblCliente, gbc);
        gbc.gridx = 1; gbc.weightx = 1.0;
        panel.add(cboClientes, gbc);
        gbc.gridx = 2; gbc.weightx = 0;
        panel.add(btnNuevoCliente, gbc);

        row++;
        gbc.gridx = 0; gbc.gridy = row;
        panel.add(lblAuditoria, gbc);
        gbc.gridx = 1; gbc.gridwidth = 2;
        panel.add(cboAuditorias, gbc);

        row++;
        gbc.gridx = 0; gbc.gridy = row; gbc.gridwidth = 1;
        panel.add(lblEspecialista, gbc);
        gbc.gridx = 1; gbc.gridwidth = 2;
        panel.add(cboEspecialistas, gbc);

        row++;
        gbc.gridx = 0; gbc.gridy = row; gbc.gridwidth = 1;
        panel.add(lblEmision, gbc);
        gbc.gridx = 1; gbc.gridwidth = 2;
        panel.add(txtFechaEmision, gbc);

        row++;
        gbc.gridx = 0; gbc.gridy = row; gbc.gridwidth = 1;
        panel.add(lblVencimiento, gbc);
        gbc.gridx = 1; gbc.gridwidth = 2;
        panel.add(txtFechaVencimiento, gbc);

        row++;
        gbc.gridx = 0; gbc.gridy = row; gbc.gridwidth = 1;
        panel.add(lblEstado, gbc);
        gbc.gridx = 1; gbc.gridwidth = 2;
        panel.add(cboEstado, gbc);

        btnRegistrar = new JButton("Registrar");
        btnCancelar = new JButton("Cancelar");
        
        btnRegistrar.addActionListener(e -> registrarCertificacion());
        btnCancelar.addActionListener(e -> limpiarFormulario());

        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 0));
        panelBotones.add(btnRegistrar);
        panelBotones.add(btnCancelar);

        row++;
        gbc.gridx = 0; gbc.gridy = row; gbc.gridwidth = 3;
        panel.add(panelBotones, gbc);

        return panel;
    }

    private JPanel crearPanelListado() {
        JPanel panel = new JPanel(new BorderLayout());

        modeloTabla = new DefaultTableModel();
        modeloTabla.setColumnIdentifiers(new String[]{
            "ID", "Empresa", "Auditoría", "Especialista", "Fecha Emisión", "Fecha Vencimiento", "Estado"
        });

        tblCertificaciones = new JTable(modeloTabla);
        
        tblCertificaciones.getColumnModel().getColumn(0).setPreferredWidth(30);
        
        tblCertificaciones.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        tblCertificaciones.getColumnModel().getColumn(1).setPreferredWidth(130); 
        tblCertificaciones.getColumnModel().getColumn(2).setPreferredWidth(120); 
        tblCertificaciones.getColumnModel().getColumn(3).setPreferredWidth(120); 
        tblCertificaciones.getColumnModel().getColumn(4).setPreferredWidth(90); 
        tblCertificaciones.getColumnModel().getColumn(5).setPreferredWidth(110);
        tblCertificaciones.getColumnModel().getColumn(6).setPreferredWidth(90); 

        JScrollPane scrollPane = new JScrollPane(tblCertificaciones);
        panel.add(scrollPane, BorderLayout.CENTER);
        
        DefaultTableCellRenderer centrado = new DefaultTableCellRenderer();
        centrado.setHorizontalAlignment(SwingConstants.CENTER);

        for (int i = 0; i < tblCertificaciones.getColumnCount(); i++) {
            tblCertificaciones.getColumnModel().getColumn(i).setCellRenderer(centrado);
        }

        return panel;
    }

    private void mostrarDialogoNuevoCliente() {
        JTextField txtNombre = new JTextField();
        JTextField txtRuc = new JTextField();
        JTextField txtCorreo = new JTextField();

        Object[] campos = {
            "Nombre de la Empresa:", txtNombre,
            "RUC (11 dígitos):", txtRuc,
            "Correo Electrónico:", txtCorreo
        };

        int opcion = JOptionPane.showConfirmDialog(this, campos, "Nuevo Cliente", JOptionPane.OK_CANCEL_OPTION);

        if (opcion == JOptionPane.OK_OPTION) {
            String nombre = txtNombre.getText().trim();
            String ruc = txtRuc.getText().trim();
            String correo = txtCorreo.getText().trim();

            if (nombre.isEmpty() || ruc.isEmpty() || correo.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Por favor, completa todos los campos.");
                return;
            }

            Cliente nuevo = new Cliente();
            nuevo.setNombreEmpresa(nombre);
            nuevo.setRuc(ruc);
            nuevo.setCorreo(correo);

            try {
                clienteService.save(nuevo);
                cargarClientes();
                cboClientes.setSelectedItem(nombre);
                JOptionPane.showMessageDialog(this, "Cliente registrado correctamente.");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Error al guardar el cliente: " + e.getMessage());
                e.printStackTrace();
            }
        }
    }


    private void cargarClientes() {
        cboClientes.removeAllItems();
        cboClientes.addItem("Seleccione cliente");
        for (Cliente cliente : clienteService.getAll()) {
            cboClientes.addItem(cliente.getNombreEmpresa());
        }
    }
    
    private void cargarTiposAuditoria() {
        cboAuditorias.removeAllItems();
        cboAuditorias.addItem("Seleccione un tipo");
        for (Tipoauditoria tipo : tipoauditoriaService.getAll()) {
            cboAuditorias.addItem(tipo.getDescripcion());
        }
    }

    private void cargarEspecialistas() {
        cboEspecialistas.removeAllItems();
        cboEspecialistas.addItem("Seleccione un especialista");
        for (Especialista esp : especialistaService.getAll()) {
            cboEspecialistas.addItem(esp.getNombreCompleto());
        }
    }
    
    private void cargarCertificaciones() {
        modeloTabla.setRowCount(0);
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");

        for (Certificacion cert : certificacionService.getAll()) {
            modeloTabla.addRow(new Object[]{
                cert.getIdCertificacion(),
                cert.getCliente().getNombreEmpresa(),
                cert.getTipoauditoria().getDescripcion(),
                cert.getEspecialista().getNombreCompleto(),
                formato.format(cert.getFechaEmision()),
                formato.format(cert.getFechaVencimiento()),
                cert.getEstado()
            });
        }
    }

    
    private void registrarCertificacion() {
        try {
            Cliente cliente = clienteService.getAll().stream()
                    .filter(c -> c.getNombreEmpresa().equals(cboClientes.getSelectedItem()))
                    .findFirst().orElse(null);

            Tipoauditoria tipo = tipoauditoriaService.getAll().stream()
                    .filter(t -> t.getDescripcion().equals(cboAuditorias.getSelectedItem()))
                    .findFirst().orElse(null);

            Especialista esp = especialistaService.getAll().stream()
                    .filter(e -> e.getNombreCompleto().equals(cboEspecialistas.getSelectedItem()))
                    .findFirst().orElse(null);

            if (cliente == null || tipo == null || esp == null) {
                JOptionPane.showMessageDialog(this, "Complete todos los campos antes de registrar.");
                return;
            }

            Certificacion cert = new Certificacion();
            cert.setCliente(cliente);
            cert.setTipoauditoria(tipo);
            cert.setEspecialista(esp);
            cert.setFechaEmision(new Date());
            Calendar cal = Calendar.getInstance();
            cal.setTime(new Date());
            cal.add(Calendar.YEAR, 1);
            cert.setFechaVencimiento(cal.getTime());
            cert.setEstado((String) cboEstado.getSelectedItem());

            certificacionService.save(cert);

            JOptionPane.showMessageDialog(this, "Certificación registrada exitosamente.");
            cargarCertificaciones();
            limpiarFormulario();

        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al registrar: " + ex.getMessage());
        }
    }

    private void limpiarFormulario() {
        cboClientes.setSelectedIndex(0);
        cboAuditorias.setSelectedIndex(0);
        cboEspecialistas.setSelectedIndex(0);
        cboEstado.setSelectedIndex(0);
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            RegistroCertificacion ventana = new RegistroCertificacion();
            ventana.setVisible(true);
        });
    }
}
