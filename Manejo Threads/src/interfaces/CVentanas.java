package interfaces;

import java.util.LinkedList;
import javax.swing.*;

// Heredarla de interfaz gráfica (MODIF)

public class CVentanas {

	private static final long serialVersionUID = 1L;
	
	public VentanaAeropuertos Aeropuertos;
	public VentanaAviones Aviones;
	public VentanaClientes Clientes;
	public VentanaDestinos Destinos;
	public VentanaVuelos Vuelos;
	public VentanaVentas Ventas;	
	public VentanaOpcionesGSM OpcionesGSM;
	public VentanaAccesoBD AccesoBD;
	public VentanaCargarClientes CargarClientes;
	public VentanaModificarVuelos ModificarVuelos;
	public VentanaArchivosXML ArchivosXML;
	public VentanaTiempos Tiempos;
	public VentanaArchivoLog ArchivoLog;
	public VentanaAcercaDe AcercaDe;
	public CInterfazGrafica GUI;
	
	public CVentanas(CInterfazGrafica GUI) {
		this.GUI = GUI;
		Aeropuertos = null;
		Aviones = null;
		Clientes = null;
		Destinos = null;
		Vuelos = null;
		Ventas = null;
		OpcionesGSM = null;
		AccesoBD = null;
		CargarClientes = null;
		ModificarVuelos = null;
		ArchivosXML = null;
		Tiempos = null;
		ArchivoLog = null;
		AcercaDe = null;
	}
	
//  Creo Un Método Para Pasar Un Valor de String a Objetos y Poder Grabarlos En Una Cola De Objetos
	
	public Object makeObj(final String item) { 
	return new Object() { 
		public String toString() { return item; } };
	}	
	
	public class VentanaAeropuertos extends JDialog {

		private static final long serialVersionUID = 1L;
		
		private JLabel jLabel1, jLabel2, jLabel3, jLabel4, jLabel5;
		private JTextField jTextField1, jTextField2, jTextField6;
		private JComboBox jComboBox1, jComboBox2;
		private JButton jButton1, jButton2;
		private JPanel jPanel1;
		private LinkedList<Object> Ciudades;
		private String Provincia;
//		private Largo, Ancho;
		
		public VentanaAeropuertos() {

//	 		Opciones De Ventana "Agregar Aeropuertos"
			
	        jPanel1 = new JPanel();
	        jLabel1 = new JLabel();
	        jTextField1 = new JTextField();
	        jTextField2 = new JTextField();
	        jLabel2 = new JLabel();
	        jLabel3 = new JLabel();
	        jButton1 = new JButton();
	        jButton2 = new JButton();
	        jLabel4 = new JLabel();
	        jLabel5 = new JLabel();
	        jTextField6 = new JTextField();
	        jComboBox1 = new JComboBox();
	        jComboBox2 = new JComboBox();
	        Ciudades = new LinkedList<Object>();
	        Provincia = "";
	        
	        setTitle("Agregar Aeropuertos A Base De Datos");
			setModalityType(java.awt.Dialog.ModalityType.APPLICATION_MODAL);
	        setResizable(false);
	        setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
	        // (MODIF)
	        System.out.println("Centro X: " + GUI.getBounds().getCenterX() + "Centro Y: " + GUI.getBounds().getCenterY());
	        System.out.println("Punto X: " + GUI.getBounds().x + "Punto Y: " + GUI.getBounds().y);
	        setBounds(new java.awt.Rectangle(GUI.getBounds().x + 318, GUI.getBounds().y + 239, 364, 288));
	        System.out.println("Centro Ventanita X: " + getBounds().getCenterX() + "Centro Ventanita Y: " + getBounds().getCenterY());
	        // (MODIF)
	        jPanel1.setBorder(BorderFactory.createEtchedBorder());
	        jPanel1.setName("");
	        jLabel1.setHorizontalAlignment(SwingConstants.RIGHT);
	        jLabel1.setText("Nombre");

	        jLabel2.setHorizontalAlignment(SwingConstants.RIGHT);
	        jLabel2.setText("Cantidad De Pistas");

	        jLabel3.setHorizontalAlignment(SwingConstants.RIGHT);
	        jLabel3.setText("Ciudad");

	        jButton1.setText("Agregar");
	        jButton1.addActionListener(new java.awt.event.ActionListener() {
	            public void actionPerformed(java.awt.event.ActionEvent evt) {
	            	jButton1ActionPerformed(evt);
	            }
	        });

	        jButton2.setText("Salir");
	        jButton2.addActionListener(new java.awt.event.ActionListener() {
	            public void actionPerformed(java.awt.event.ActionEvent evt) {
	            	jButton2ActionPerformed(evt);
	            }
	        });

	        jLabel4.setHorizontalAlignment(SwingConstants.RIGHT);
	        jLabel4.setText("Provincia");

	        jLabel5.setHorizontalAlignment(SwingConstants.RIGHT);
	        jLabel5.setText("Código");
	        
	        addWindowListener( new java.awt.event.WindowAdapter() {
	        	public void windowClosing(java.awt.event.WindowEvent event) {
	        		CerrarVentana(event);
	        	}
	        });
	        
	        for (int i=1;i<9;i++) {
				jComboBox1.addItem(makeObj(Integer.toString(i)));
			}
	        
	        Ciudades = GUI.Inicio.BD.BuscoCiudadesSinAeropuertos();
			for (int i=0;i<Ciudades.size();i++) {
				jComboBox2.addItem(Ciudades.get(i));
			}
			
			jComboBox2.addItemListener(new java.awt.event.ItemListener() {
	            public void itemStateChanged(java.awt.event.ItemEvent evt) {
	            	jComboBox2ActionPerformed(evt);
	            }
	        });
			
			Provincia = GUI.Inicio.BD.BuscoProvincia(jComboBox2.getSelectedItem().toString());
			jTextField2.setEditable(false);
			jTextField2.setText(Provincia);
			
//			jComboBox2.setSelectedIndex(7);
			
//	        Provincias = GUI.Inicio.BD.BuscoProvincias(); // MODIF
//			for (int i=0;i<Provincias.size();i++) {
//				jComboBox3.addItem(Provincias.get(i));
//			}
//			jComboBox3.setSelectedIndex(4);
			
	        GroupLayout jPanel1Layout = new GroupLayout(jPanel1);
	        jPanel1.setLayout(jPanel1Layout);
	        jPanel1Layout.setHorizontalGroup(
	            jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
	            .addGroup(jPanel1Layout.createSequentialGroup()
	                .addContainerGap()
	                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
	                    .addGroup(jPanel1Layout.createSequentialGroup()
	                        .addComponent(jLabel4, GroupLayout.PREFERRED_SIZE, 113, GroupLayout.PREFERRED_SIZE)
	                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
	                        .addComponent(jTextField2, GroupLayout.PREFERRED_SIZE, 179, GroupLayout.PREFERRED_SIZE))
	                    .addGroup(jPanel1Layout.createSequentialGroup()
	                        .addComponent(jLabel5, GroupLayout.PREFERRED_SIZE, 113, GroupLayout.PREFERRED_SIZE)
	                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
	                        .addComponent(jTextField6, GroupLayout.PREFERRED_SIZE, 180, GroupLayout.PREFERRED_SIZE))
	                    .addGroup(jPanel1Layout.createSequentialGroup()
	                        .addComponent(jLabel2, GroupLayout.PREFERRED_SIZE, 113, GroupLayout.PREFERRED_SIZE)
	                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
	                        .addComponent(jComboBox1, GroupLayout.PREFERRED_SIZE, 179, GroupLayout.PREFERRED_SIZE))
	                    .addGroup(jPanel1Layout.createSequentialGroup()       
	                        .addComponent(jLabel1, GroupLayout.PREFERRED_SIZE, 113, GroupLayout.PREFERRED_SIZE)
	                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
	                        .addComponent(jTextField1, GroupLayout.PREFERRED_SIZE, 180, GroupLayout.PREFERRED_SIZE))
	                    .addGroup(jPanel1Layout.createSequentialGroup()
	                        .addComponent(jLabel3, GroupLayout.PREFERRED_SIZE, 113, GroupLayout.PREFERRED_SIZE)
	                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
	                        .addComponent(jComboBox2, GroupLayout.PREFERRED_SIZE, 179, GroupLayout.PREFERRED_SIZE)))
	                .addContainerGap())
	            .addGroup(jPanel1Layout.createSequentialGroup()
	            	.addGap(60, 60, 60)
	                .addComponent(jButton1, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
	                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 10, Short.MAX_VALUE)
	                .addComponent(jButton2, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
	                .addGap(60, 60, 60))                
	        );
	        jPanel1Layout.setVerticalGroup(
	            jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
	            .addGroup(jPanel1Layout.createSequentialGroup()
	                .addContainerGap()
	                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
	                    .addComponent(jLabel1)
	                    .addComponent(jTextField1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
	                .addGap(16, 16, 16)
	                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
	                    .addComponent(jComboBox1, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
	                    .addComponent(jLabel2, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
	                .addGap(16, 16, 16)
	                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
	                    .addComponent(jComboBox2, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
	                    .addComponent(jLabel3))
	                .addGap(16, 16, 16)
	                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
	                    .addComponent(jTextField2, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
	                    .addComponent(jLabel4))
	                .addGap(16, 16, 16)
	                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
	                    .addComponent(jTextField6, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
	                    .addComponent(jLabel5))
	                .addGap(14, 14, 14)
	                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
	                    .addComponent(jButton1)
	                    .addComponent(jButton2))
	                .addContainerGap())
	        );

	        GroupLayout layout = new GroupLayout(getContentPane());
	        getContentPane().setLayout(layout);
	        layout.setHorizontalGroup(
	            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
	            .addGroup(layout.createSequentialGroup()
	                .addContainerGap()
	                .addComponent(jPanel1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
	                .addContainerGap())
	        );
	        layout.setVerticalGroup(
	            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
	            .addGroup(layout.createSequentialGroup()
	                .addContainerGap()
	                .addComponent(jPanel1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
	                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
	        );
//	        pack();
		}
		
    	private void jComboBox2ActionPerformed(java.awt.event.ItemEvent evt) {
    		if (this.isVisible()) {
    			Provincia = GUI.Inicio.BD.BuscoProvincia(jComboBox2.getSelectedItem().toString());
    			jTextField2.setText(Provincia);
    		}
    	}
		
		private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {
			int Resultado = GUI.Inicio.BD.AgregoAeropuerto(jTextField1.getText(), Integer.parseInt(jComboBox1.getSelectedItem().toString()), jComboBox2.getSelectedItem().toString(), 
					jTextField2.getText(), jTextField6.getText());
    	  	if (Resultado == 0) { GUI.jTextArea2.setText("Los datos del aeropuerto " + jTextField1.getText() + " han sido cargados en la base de datos"); }
    	  	else { GUI.jTextArea2.setText("No se pudo almacenar los datos del aeropuerto en la base de datos. Por favor, reingreselos."); }
    	  	jTextField1.setText("");
			jTextField6.setText("");
			jComboBox1.setSelectedIndex(0);
			dispose();
        }
        
        private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {
			jTextField1.setText("");
			jTextField6.setText("");
			jComboBox1.setSelectedIndex(0);
        	dispose();
        }
        
	    public void CargarDatos() {
			jComboBox2.removeAllItems();
	        Ciudades = GUI.Inicio.BD.BuscoCiudadesSinAeropuertos();
			for (int i=0;i<Ciudades.size();i++) { jComboBox2.addItem(Ciudades.get(i));}
			Provincia = GUI.Inicio.BD.BuscoProvincia(jComboBox2.getSelectedItem().toString());
			jTextField2.setText(Provincia);
		}
        
        public void CerrarVentana(java.awt.event.WindowEvent event) {
			jTextField1.setText("");
			jTextField6.setText("");
			jComboBox1.setSelectedIndex(0);
        	dispose();
        }
	}
    
	public class VentanaAviones extends JDialog {

		private static final long serialVersionUID = 1L;
		
		private JLabel jLabel1, jLabel2, jLabel3;
		private JTextField jTextField1 ,jTextField2 ,jTextField3;
		private JButton jButton1, jButton2;
		private JPanel jPanel1;
		
		public VentanaAviones() {
			
//	 		Opciones De Ventana "Agregar Aviones"
			
	        jPanel1 = new JPanel();
	        jLabel1 = new JLabel();
	        jTextField1 = new JTextField();
	        jLabel2 = new JLabel();
	        jTextField2 = new JTextField();
	        jLabel3 = new JLabel();
	        jTextField3 = new JTextField();
	        jButton1 = new JButton();
	        jButton2 = new JButton();

	        setTitle("Agregar Aviones A Base De Datos");
			setModalityType(java.awt.Dialog.ModalityType.APPLICATION_MODAL);
	        setResizable(false);
	        setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
	        // (MODIF)
	        System.out.println("Centro X: " + GUI.getBounds().getCenterX() + " Centro Y: " + GUI.getBounds().getCenterY());
	        System.out.println("Punto X: " + GUI.getBounds().x + "Punto Y: " + GUI.getBounds().y);
	        setBounds(new java.awt.Rectangle(GUI.getBounds().x + 311, GUI.getBounds().y + 275, 379, 216));
	        //setBounds(new java.awt.Rectangle(450, 234, 1, 1));
	        System.out.println("Centro Ventanita X: " + getBounds().getCenterX() + " Centro Ventanita Y: " + getBounds().getCenterY());
	        // (MODIF)
	        jPanel1.setBorder(BorderFactory.createEtchedBorder());
	        jPanel1.setName("");
	        jLabel1.setHorizontalAlignment(SwingConstants.CENTER);
	        jLabel1.setText("Tipo De Avi\u00f3n");

	        jLabel2.setHorizontalAlignment(SwingConstants.CENTER);
	        jLabel2.setText("Capacidad (Pasajeros)");

	        jLabel3.setHorizontalAlignment(SwingConstants.CENTER);
	        jLabel3.setText("C\u00f3digo Del Avi\u00f3n");

	        jButton1.setText("Agregar");
	        jButton1.addActionListener(new java.awt.event.ActionListener() {
	            public void actionPerformed(java.awt.event.ActionEvent evt) {
	            	jButton1ActionPerformed(evt);
	            }
	        });

	        jButton2.setText("Salir");
	        jButton2.addActionListener(new java.awt.event.ActionListener() {
	            public void actionPerformed(java.awt.event.ActionEvent evt) {
	            	jButton2ActionPerformed(evt);
	            }
	        });
	        
	        addWindowListener( new java.awt.event.WindowAdapter() {
	        	public void windowClosing(java.awt.event.WindowEvent event) {
	        		CerrarVentana(event);
	        	}
	        });

	        GroupLayout jPanel1Layout = new GroupLayout(jPanel1);
	        jPanel1.setLayout(jPanel1Layout);
	        jPanel1Layout.setHorizontalGroup(
	            jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
	            .addGroup(jPanel1Layout.createSequentialGroup()
	                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
	                    .addGroup(jPanel1Layout.createSequentialGroup()
	                        .addContainerGap()
	                        .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
	                            .addComponent(jLabel2, GroupLayout.DEFAULT_SIZE, 113, GroupLayout.PREFERRED_SIZE)
	                            .addComponent(jLabel1, GroupLayout.DEFAULT_SIZE, 113, GroupLayout.PREFERRED_SIZE)
	                            .addComponent(jLabel3, GroupLayout.DEFAULT_SIZE, 113, GroupLayout.PREFERRED_SIZE))
	                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
	                        .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
	                            .addComponent(jTextField2, GroupLayout.PREFERRED_SIZE, 180, GroupLayout.PREFERRED_SIZE)
	                            .addComponent(jTextField3, GroupLayout.PREFERRED_SIZE, 180, GroupLayout.PREFERRED_SIZE)
	                            .addComponent(jTextField1, GroupLayout.PREFERRED_SIZE, 180, GroupLayout.PREFERRED_SIZE))
	                        .addContainerGap())
	                    .addGroup(jPanel1Layout.createSequentialGroup()
	                        .addContainerGap()
	                        .addGroup(jPanel1Layout.createSequentialGroup()
	                            .addGap(67, 67, 67)
	                            .addComponent(jButton1, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
	                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 10, Short.MAX_VALUE)
	                            .addComponent(jButton2, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
	                            .addGap(68, 68, 68))
	                        .addContainerGap())))
	        );
	        jPanel1Layout.setVerticalGroup(
	            jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
	            .addGroup(jPanel1Layout.createSequentialGroup()
	                .addContainerGap()
	                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
	                    .addComponent(jLabel1)
	                    .addComponent(jTextField1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
	                .addGap(16, 16, 16)
	                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
	                    .addComponent(jTextField2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
	                    .addComponent(jLabel2, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
	                .addGap(16, 16, 16)
	                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
	                    .addComponent(jTextField3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
	                    .addComponent(jLabel3))
	                .addGap(14, 14, 14)
	                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
	                    .addComponent(jButton1)
	                    .addComponent(jButton2))
	                .addContainerGap())
	        );

	        GroupLayout layout = new GroupLayout(getContentPane());
	        getContentPane().setLayout(layout);
	        layout.setHorizontalGroup(
	            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
	            .addGroup(layout.createSequentialGroup()
	                .addContainerGap()
	                .addComponent(jPanel1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
	                .addContainerGap())
	        );
	        layout.setVerticalGroup(
	            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
	            .addGroup(layout.createSequentialGroup()
	                .addContainerGap()
	                .addComponent(jPanel1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
	                .addContainerGap())
	        );
//	        pack();
		}
		
		private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {
			int Resultado = GUI.Inicio.BD.AgregoAvion(jTextField1.getText(), Integer.parseInt(jTextField2.getText()), jTextField3.getText());
    	  	if (Resultado == 0) { GUI.jTextArea2.setText("Los datos del avión " + jTextField1.getText() + " han sido cargados en la base de datos"); }
    	  	else { GUI.jTextArea2.setText("No se pudo almacenar los datos del avión en la base de datos. Por favor, reingreselos."); } 
    	  	jTextField1.setText("");
			jTextField2.setText("");
			jTextField3.setText("");
			dispose();
        }
        
        private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {
    	  	jTextField1.setText("");
			jTextField2.setText("");
			jTextField3.setText("");
        	dispose();
        }
        
        public void CerrarVentana(java.awt.event.WindowEvent event) {
    	  	jTextField1.setText("");
			jTextField2.setText("");
			jTextField3.setText("");
        	dispose();
        }   
	}
	
	public class VentanaClientes extends JDialog {

		private static final long serialVersionUID = 1L;
		
		private JLabel jLabel1, jLabel2, jLabel3, jLabel4, jLabel5, jLabel6, jLabel7, jLabel8, jLabel9, jLabel10, jLabel11, jLabel12, jLabel13, jLabel14;
		public  JTextField jTextField1, jTextField3, jTextField6, jTextField7, jTextField8;
		private JComboBox jComboBox1, jComboBox2, jComboBox3, jComboBox4, jComboBox5, jComboBox6, jComboBox7;
		private JButton jButton1, jButton2;
		private JPanel jPanel1;
		private LinkedList<Object> Ciudades;
		public int BotonPresionado;
		
		public VentanaClientes() {

//	 		Opciones De Ventana "Agregar Clientes"
			
	        jPanel1 = new JPanel();
	        jLabel1 = new JLabel();
	        jTextField1 = new JTextField();
	        jLabel2 = new JLabel();
	        jLabel3 = new JLabel();
	        jTextField3 = new JTextField();
	        jButton1 = new JButton();
	        jButton2 = new JButton();
	        jLabel4 = new JLabel();
	        jLabel5 = new JLabel();
	        jTextField6 = new JTextField();
	        jLabel6 = new JLabel();				// Apellido 
	        jTextField7 = new JTextField();		// Colocar Apellido Del Cliente 
	        jTextField8 = new JTextField();		// Colocar Número Del Celular (Sin 15)
	        jComboBox1 = new JComboBox();
	        jComboBox2 = new JComboBox();
	        jComboBox3 = new JComboBox();
	        jComboBox4 = new JComboBox();		// Tipo de Documento
	        jComboBox5 = new JComboBox();		// Recibe Novedades Vía SMS 
	        jComboBox6 = new JComboBox();		// Ciudad De Residencia
	        jComboBox7 = new JComboBox();		// Tipo De Cliente
	        jLabel7 = new JLabel();
	        jLabel8 = new JLabel();
	        jLabel9 = new JLabel();
	        jLabel10 = new JLabel();
	        jLabel11 = new JLabel();
	        jLabel12 = new JLabel();
	        jLabel13 = new JLabel();
	        jLabel14 = new JLabel();
	        Ciudades = new LinkedList<Object>();
	        BotonPresionado = 0;
	        
			for (int i=1;i<32;i++) {
				if (i<10) {
					jComboBox1.addItem(makeObj("0" + Integer.toString(i)));
					jComboBox2.addItem(makeObj("0" + Integer.toString(i)));
				}
				else {
					if (i<13) {jComboBox2.addItem(makeObj(Integer.toString(i)));}
					jComboBox1.addItem(makeObj(Integer.toString(i)));
				}
			}
			
			for (int i=0;i<60;i++) {
				jComboBox3.addItem(makeObj("19" + Integer.toString(90-i)));
			}
			
	        jComboBox4.setModel(new DefaultComboBoxModel(new String[] { "DNI", "LE", "LC", "CI" }));
	        jComboBox5.setModel(new DefaultComboBoxModel(new String[] { "Si", "No" }));
	        Ciudades = GUI.Inicio.BD.BuscoCiudades();
			for (int i=0;i<Ciudades.size();i++) { jComboBox6.addItem(Ciudades.get(i)); }
			jComboBox6.setSelectedIndex(10);	        
	        jComboBox7.setModel(new DefaultComboBoxModel(new String[] { "Nuevo", "Frecuente", "Preferencial", "Gold" }));					    
	        
	        setTitle("Agregar Clientes a La Base De Datos");
			setModalityType(java.awt.Dialog.ModalityType.APPLICATION_MODAL);
	        setResizable(false);
	        setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
	        // (MODIF)
	        System.out.println("Centro X: " + GUI.getBounds().getCenterX() + " Centro Y: " + GUI.getBounds().getCenterY());
	        System.out.println("Punto X: " + GUI.getBounds().x + "Punto Y: " + GUI.getBounds().y);
	        setBounds(new java.awt.Rectangle(GUI.getBounds().x + 298, GUI.getBounds().y + 167, 405, 432));
	        System.out.println("Centro Ventanita X: " + getBounds().getCenterX() + " Centro Ventanita Y: " + getBounds().getCenterY());
	        // (MODIF)
	        //setBounds(new java.awt.Rectangle(307, 228, 363, 323));
	        jPanel1.setBorder(BorderFactory.createEtchedBorder());
	        jPanel1.setName("");
	        jLabel1.setHorizontalAlignment(SwingConstants.CENTER);
	        jLabel1.setText("Nombre");

	        jLabel2.setHorizontalAlignment(SwingConstants.CENTER);
	        jLabel2.setText("Documento Tipo");

	        jLabel3.setHorizontalAlignment(SwingConstants.CENTER);
	        jLabel3.setText("Documento Número");

	        jButton1.setText("Agregar");
	        jButton1.addActionListener(new java.awt.event.ActionListener() {
	            public void actionPerformed(java.awt.event.ActionEvent evt) {
	            	jButton1ActionPerformed(evt);
	            }
	        });

	        jButton2.setText("Salir");
	        jButton2.addActionListener(new java.awt.event.ActionListener() {
	            public void actionPerformed(java.awt.event.ActionEvent evt) {
	            	jButton2ActionPerformed(evt);
	            }
	        });
	        
	        addWindowListener( new java.awt.event.WindowAdapter() {
	        	public void windowClosing(java.awt.event.WindowEvent event) {
	        		CerrarVentana(event);
	        	}
	        });

	        jLabel4.setHorizontalAlignment(SwingConstants.RIGHT);
	        jLabel4.setText("Fecha De Nacimiento");

	        jLabel5.setHorizontalAlignment(SwingConstants.RIGHT);
	        jLabel5.setText("Teléfono Celular   ");
	        
	        jLabel13.setHorizontalAlignment(SwingConstants.CENTER);
	        jLabel13.setText("Nro. (Sin 15)");
	        
	        jLabel14.setHorizontalAlignment(SwingConstants.CENTER);
	        jLabel14.setText("Area");
	        
	        jLabel6.setHorizontalAlignment(SwingConstants.CENTER);
	        jLabel6.setText("Apellido");
	        
	        jLabel7.setHorizontalAlignment(SwingConstants.CENTER);
	        jLabel7.setText("Día");
	        
	        jLabel8.setHorizontalAlignment(SwingConstants.CENTER);
	        jLabel8.setText("Mes");
	        
	        jLabel9.setHorizontalAlignment(SwingConstants.CENTER);
	        jLabel9.setText("Año");
	        
	        jLabel10.setHorizontalAlignment(SwingConstants.CENTER);
	        jLabel10.setText("Recibe Novedades Vía SMS");
	        
	        jLabel11.setHorizontalAlignment(SwingConstants.CENTER);
	        jLabel11.setText("Ciudad De Residencia");
	        
	        jLabel12.setHorizontalAlignment(SwingConstants.CENTER);
	        jLabel12.setText("Tipo De Cliente");

	        GroupLayout jPanel1Layout = new GroupLayout(jPanel1);
	        jPanel1.setLayout(jPanel1Layout);
	        jPanel1Layout.setHorizontalGroup(
	            jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
	            .addGroup(jPanel1Layout.createSequentialGroup()
	                .addContainerGap()
	                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
	                    .addGroup(jPanel1Layout.createSequentialGroup()
//		                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 8, Short.MAX_VALUE)
	                    	.addComponent(jLabel4, GroupLayout.PREFERRED_SIZE, 136, GroupLayout.PREFERRED_SIZE)
//	                    	.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
	                        .addComponent(jLabel7, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
	                        .addComponent(jComboBox1, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)	
	                        .addComponent(jLabel8, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
	                        .addComponent(jComboBox2, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
	                        .addComponent(jLabel9, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
	                        .addComponent(jComboBox3, GroupLayout.PREFERRED_SIZE, 55, GroupLayout.PREFERRED_SIZE))
	                    .addGroup(jPanel1Layout.createSequentialGroup()
	                        .addComponent(jLabel5, GroupLayout.PREFERRED_SIZE, 100, Short.MAX_VALUE)
	                        .addComponent(jLabel14, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
//	                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
	                        .addComponent(jTextField6, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
//	                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
	                        .addComponent(jLabel13, GroupLayout.PREFERRED_SIZE, 75, GroupLayout.PREFERRED_SIZE)
//	                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
	                        .addComponent(jTextField8, GroupLayout.PREFERRED_SIZE, 70, GroupLayout.PREFERRED_SIZE))
	                    .addGroup(jPanel1Layout.createSequentialGroup()
                    		.addComponent(jLabel1, GroupLayout.DEFAULT_SIZE, 155, GroupLayout.PREFERRED_SIZE)
	                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    		.addComponent(jTextField1, GroupLayout.PREFERRED_SIZE, 180, GroupLayout.PREFERRED_SIZE))
                    	.addGroup(jPanel1Layout.createSequentialGroup()	
                       		.addComponent(jLabel6, GroupLayout.DEFAULT_SIZE, 155, GroupLayout.PREFERRED_SIZE)
	                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    		.addComponent(jTextField7, GroupLayout.PREFERRED_SIZE, 180, GroupLayout.PREFERRED_SIZE))
                    	.addGroup(jPanel1Layout.createSequentialGroup()	                        
	                        .addComponent(jLabel10, GroupLayout.DEFAULT_SIZE, 155, GroupLayout.PREFERRED_SIZE)
	                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
	                    	.addComponent(jComboBox5, GroupLayout.PREFERRED_SIZE, 179, GroupLayout.PREFERRED_SIZE))
	                    .addGroup(jPanel1Layout.createSequentialGroup()	                        
	                        .addComponent(jLabel11, GroupLayout.DEFAULT_SIZE, 155, GroupLayout.PREFERRED_SIZE)
	                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
	                    	.addComponent(jComboBox6, GroupLayout.PREFERRED_SIZE, 179, GroupLayout.PREFERRED_SIZE))
	                    .addGroup(jPanel1Layout.createSequentialGroup()	                        
	                        .addComponent(jLabel12, GroupLayout.DEFAULT_SIZE, 155, GroupLayout.PREFERRED_SIZE)
	                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
	                    	.addComponent(jComboBox7, GroupLayout.PREFERRED_SIZE, 179, GroupLayout.PREFERRED_SIZE))
	                    .addGroup(jPanel1Layout.createSequentialGroup()	                        
	                        .addComponent(jLabel2, GroupLayout.DEFAULT_SIZE, 155, GroupLayout.PREFERRED_SIZE)
	                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
	                    	.addComponent(jComboBox4, GroupLayout.PREFERRED_SIZE, 179, GroupLayout.PREFERRED_SIZE))
	                    .addGroup(jPanel1Layout.createSequentialGroup()
		                    .addComponent(jLabel3, GroupLayout.DEFAULT_SIZE, 113, Short.MAX_VALUE)
	                    	.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
		                    .addComponent(jTextField3, GroupLayout.PREFERRED_SIZE, 180, GroupLayout.PREFERRED_SIZE)))	                            	                            
	                .addContainerGap())
	            .addGroup(jPanel1Layout.createSequentialGroup()
	                .addGap(80, 80, 80)
	                .addComponent(jButton1, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
	                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 10, Short.MAX_VALUE)
	                .addComponent(jButton2, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
	                .addGap(81, 81, 81))
	        );
	        jPanel1Layout.setVerticalGroup(
	            jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
	            .addGroup(jPanel1Layout.createSequentialGroup()
	                .addContainerGap()
	                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
	                    .addComponent(jLabel1)
	                    .addComponent(jTextField1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
	                .addGap(16, 16, 16)
	                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
	                	.addComponent(jLabel6)
		                .addComponent(jTextField7, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
	                .addGap(16, 16, 16)	                
	                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
	                	.addComponent(jLabel5)
	                	.addComponent(jLabel14)
	                	.addComponent(jTextField6, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
	                	.addComponent(jLabel13)
	                	.addComponent(jTextField8, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
	                .addGap(16, 16, 16)
	                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
	                    .addComponent(jTextField3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
	                    .addComponent(jLabel3))
	                .addGap(16, 16, 16)
	                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
	                	.addComponent(jComboBox4, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
		                .addComponent(jLabel2))
		            .addGap(16, 16, 16)
	                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
	                	.addComponent(jComboBox5, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
		                .addComponent(jLabel10))
		            .addGap(16, 16, 16)
	                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
	                	.addComponent(jComboBox6, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
		                .addComponent(jLabel11))
		            .addGap(16, 16, 16)
	                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
	                	.addComponent(jComboBox7, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
		                .addComponent(jLabel12))
	                .addGap(16, 16, 16)
	                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
	                    .addComponent(jLabel7)
	                    .addComponent(jComboBox1, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
	                    .addComponent(jLabel8)
	                    .addComponent(jComboBox2, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
	                    .addComponent(jLabel9)
	                    .addComponent(jComboBox3, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
	                    .addComponent(jLabel4))
	                .addGap(14, 14, 14)
	                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
	                    .addComponent(jButton1)
	                    .addComponent(jButton2))
	                .addContainerGap())
	        );

	        GroupLayout layout = new GroupLayout(getContentPane());
	        getContentPane().setLayout(layout);
	        layout.setHorizontalGroup(
	            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
	            .addGroup(layout.createSequentialGroup()
	                .addContainerGap()
	                .addComponent(jPanel1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
	                .addContainerGap())
	        );
	        layout.setVerticalGroup(
	            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
	            .addGroup(layout.createSequentialGroup()
	                .addContainerGap()
	                .addComponent(jPanel1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
	                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
	        );
//	        pack();
		}
	
	    public void CargarDatos() {
			jComboBox6.removeAllItems();
	        Ciudades = GUI.Inicio.BD.BuscoCiudades();
			for (int i=0;i<Ciudades.size();i++) { jComboBox6.addItem(Ciudades.get(i)); }
			jComboBox6.setSelectedIndex(10);	  	
		}
		
		private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {
			int Resultado = GUI.Inicio.BD.AgregoCliente(jTextField1.getText(), jTextField7.getText(), jTextField6.getText() + jTextField8.getText(), Integer.parseInt(jTextField3.getText()),
					jComboBox4.getSelectedItem().toString(), jComboBox3.getSelectedItem().toString() + "-" + jComboBox2.getSelectedItem().toString() + "-" + 
					jComboBox1.getSelectedItem().toString(), jComboBox7.getSelectedItem().toString(), jComboBox6.getSelectedItem().toString(), jComboBox5.getSelectedItem().toString());
    	  	if (Resultado == 0) { 
    	  		GUI.jTextArea2.setText("Los datos del cliente " + jTextField1.getText() + " " + jTextField7.getText() + " han sido cargados en la base de datos"); }
    	  	else { GUI.jTextArea2.setText("No se pudo almacenar los datos del cliente en la base de datos. Por favor, reingreselos."); }
    	  	jTextField1.setText("");
			jTextField3.setText("");
			jTextField6.setText("");
			jTextField7.setText("");
			jTextField8.setText("");
			jComboBox1.setSelectedIndex(0);
			jComboBox2.setSelectedIndex(0);
			jComboBox3.setSelectedIndex(0);
			jComboBox4.setSelectedIndex(0);
			jComboBox5.setSelectedIndex(0);	 
			jComboBox7.setSelectedIndex(0);
			BotonPresionado = 1;
			dispose();
        }
        
        private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {
    	  	jTextField1.setText("");
			jTextField3.setText("");
			jTextField6.setText("");
			jTextField7.setText("");
			jTextField8.setText("");
			jComboBox1.setSelectedIndex(0);
			jComboBox2.setSelectedIndex(0);
			jComboBox3.setSelectedIndex(0);
			jComboBox4.setSelectedIndex(0);
			jComboBox5.setSelectedIndex(0);
			jComboBox7.setSelectedIndex(0);
			BotonPresionado = 2;
        	dispose();
        }
        
        public void CerrarVentana(java.awt.event.WindowEvent event) {
    	  	jTextField1.setText("");
			jTextField3.setText("");
			jTextField6.setText("");
			jTextField7.setText("");
			jTextField8.setText("");
			jComboBox1.setSelectedIndex(0);
			jComboBox2.setSelectedIndex(0);
			jComboBox3.setSelectedIndex(0);
			jComboBox4.setSelectedIndex(0);
			jComboBox5.setSelectedIndex(0);
			jComboBox7.setSelectedIndex(0);
			BotonPresionado = 3;
        	dispose();
        }
	}
	
	public class VentanaDestinos extends JDialog {

		private static final long serialVersionUID = 1L;
		
		private JLabel jLabel1, jLabel2, jLabel4, jLabel5;
		private JTextField jTextField1 , jTextField5, jTextField6;
		private JButton jButton1, jButton2;
		private JComboBox jComboBox1;
		private JPanel jPanel1;
		
		public VentanaDestinos() {

//	 		Opciones De Ventana "Agregar Destinos"
			
	        jPanel1 = new JPanel();
	        jLabel1 = new JLabel();
	        jTextField1 = new JTextField();
	        jLabel2 = new JLabel();
	        jComboBox1 = new JComboBox();
	        jButton1 = new JButton();
	        jButton2 = new JButton();
	        jLabel4 = new JLabel();
	        jLabel5 = new JLabel();
	        jTextField5 = new JTextField();
	        jTextField6 = new JTextField();
	        
	        setTitle("Agregar Destinos A Base De Datos");
			setModalityType(java.awt.Dialog.ModalityType.APPLICATION_MODAL);
	        setResizable(false);
	        setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
	        // (MODIF)
	        System.out.println("Centro X: " + GUI.getBounds().getCenterX() + " Centro Y: " + GUI.getBounds().getCenterY());
	        System.out.println("Punto X: " + GUI.getBounds().x + "Punto Y: " + GUI.getBounds().y);
	        setBounds(new java.awt.Rectangle(GUI.getBounds().x + 318, GUI.getBounds().y + 257, 364, 252));
	        System.out.println("Centro Ventanita X: " + getBounds().getCenterX() + " Centro Ventanita Y: " + getBounds().getCenterY());
	        // (MODIF)
	        //setBounds(new java.awt.Rectangle(308, 224, 363, 321));
	        jPanel1.setBorder(BorderFactory.createEtchedBorder());
	        jPanel1.setName("");
	        jLabel1.setHorizontalAlignment(SwingConstants.CENTER);
	        jLabel1.setText("Nombre Ciudad");

	        jLabel2.setHorizontalAlignment(SwingConstants.CENTER);
	        jLabel2.setText("Zona Horaria");

	        jButton1.setText("Agregar");
	        jButton1.addActionListener(new java.awt.event.ActionListener() {
	            public void actionPerformed(java.awt.event.ActionEvent evt) {
	            	jButton1ActionPerformed(evt);
	            }
	        });

	        jButton2.setText("Salir");
	        jButton2.addActionListener(new java.awt.event.ActionListener() {
	            public void actionPerformed(java.awt.event.ActionEvent evt) {
	            	jButton2ActionPerformed(evt);
	            }
	        });
	        
	        addWindowListener( new java.awt.event.WindowAdapter() {
	        	public void windowClosing(java.awt.event.WindowEvent event) {
	        		CerrarVentana(event);
	        	}
	        });

	        jLabel4.setHorizontalAlignment(SwingConstants.CENTER);
	        jLabel4.setText("Provincia");

	        jLabel5.setHorizontalAlignment(SwingConstants.CENTER);
	        jLabel5.setText("Código Ciudad");
	        
	        jComboBox1.addItem(makeObj("GMT"));
	        
	        for (int i=1;i<=12;i++) {
				jComboBox1.addItem(makeObj("GMT -" + Integer.toString(i)));
			}  
	        
			for (int i=1;i<14;i++) {
				jComboBox1.addItem(makeObj("GMT +" + Integer.toString(i)));
			}
			
			jComboBox1.setSelectedIndex(3);

	        GroupLayout jPanel1Layout = new GroupLayout(jPanel1);
	        jPanel1.setLayout(jPanel1Layout);
	        jPanel1Layout.setHorizontalGroup(
	            jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
	            .addGroup(jPanel1Layout.createSequentialGroup()
	                .addContainerGap()
	                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
	                    .addGroup(jPanel1Layout.createSequentialGroup()
	                        .addComponent(jLabel4, GroupLayout.PREFERRED_SIZE, 113, GroupLayout.PREFERRED_SIZE)
	                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
	                        .addComponent(jTextField5, GroupLayout.PREFERRED_SIZE, 180, GroupLayout.PREFERRED_SIZE))
	                    .addGroup(jPanel1Layout.createSequentialGroup()
	                        .addComponent(jLabel5, GroupLayout.PREFERRED_SIZE, 113, GroupLayout.PREFERRED_SIZE)
	                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
	                        .addComponent(jTextField6, GroupLayout.PREFERRED_SIZE, 180, GroupLayout.PREFERRED_SIZE))
	                    .addGroup(jPanel1Layout.createSequentialGroup()
	                        .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
	                            .addComponent(jLabel2, GroupLayout.DEFAULT_SIZE, 113, GroupLayout.PREFERRED_SIZE)
	                            .addComponent(jLabel1, GroupLayout.DEFAULT_SIZE, 113, GroupLayout.PREFERRED_SIZE))
	                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
	                        .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
	                            .addComponent(jComboBox1, GroupLayout.PREFERRED_SIZE, 179, GroupLayout.PREFERRED_SIZE)
	                            .addComponent(jTextField1, GroupLayout.PREFERRED_SIZE, 180, GroupLayout.PREFERRED_SIZE))))
	                .addContainerGap())
	            .addGroup(jPanel1Layout.createSequentialGroup()
	                .addGap(60, 60, 60)
	                .addComponent(jButton1, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
	                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 10, Short.MAX_VALUE)
	                .addComponent(jButton2, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
	                .addGap(60, 60, 60))
	        );
	        jPanel1Layout.setVerticalGroup(
	            jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
	            .addGroup(jPanel1Layout.createSequentialGroup()
	                .addContainerGap()
	                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
	                    .addComponent(jLabel1)
	                    .addComponent(jTextField1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
	                .addGap(16, 16, 16)
	                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
	                    .addComponent(jTextField5, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
	                    .addComponent(jLabel4))
	                .addGap(16, 16, 16)
	                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
	                    .addComponent(jTextField6, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
	                    .addComponent(jLabel5))
	                .addGap(16, 16, 16)
	                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
	                    .addComponent(jComboBox1, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
	                    .addComponent(jLabel2, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGap(14, 14, 14)
	                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
	                    .addComponent(jButton1)
	                    .addComponent(jButton2))
	                .addContainerGap())
	        );

	        GroupLayout layout = new GroupLayout(getContentPane());
	        getContentPane().setLayout(layout);
	        layout.setHorizontalGroup(
	            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
	            .addGroup(layout.createSequentialGroup()
	                .addContainerGap()
	                .addComponent(jPanel1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
	                .addContainerGap())
	        );
	        layout.setVerticalGroup(
	            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
	            .addGroup(layout.createSequentialGroup()
	                .addContainerGap()
	                .addComponent(jPanel1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
	                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
	        );
//	        pack();
		}
		
		private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {
			int Resultado = GUI.Inicio.BD.AgregoCiudad(jTextField1.getText(), jComboBox1.getSelectedItem().toString(), 0, jTextField5.getText(), 
					jTextField6.getText());
    	  	if (Resultado == 0) { GUI.jTextArea2.setText("Los datos del destino " + jTextField1.getText() + " han sido cargados en la base de datos"); }
    	  	else { GUI.jTextArea2.setText("No se pudo almacenar los datos del destino en la base de datos. Por favor, reingreselos."); }
    	  	jComboBox1.setSelectedIndex(3);
    	  	jTextField1.setText("");
			jTextField5.setText("");
			jTextField6.setText("");
			dispose();
        }
        
        private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {
			jComboBox1.setSelectedIndex(3);
        	jTextField1.setText("");
			jTextField5.setText("");
			jTextField6.setText("");
        	dispose();
        }
        
        public void CerrarVentana(java.awt.event.WindowEvent event) {
			jComboBox1.setSelectedIndex(3);
        	jTextField1.setText("");
			jTextField5.setText("");
			jTextField6.setText("");
        	dispose();
        }          
	}
	
	public class VentanaVuelos extends JDialog {

		private static final long serialVersionUID = 1L;
		
		private JLabel jLabel1, jLabel2, jLabel3, jLabel4, jLabel5, jLabel6, jLabel7, jLabel8, jLabel9, jLabel10, jLabel11, jLabel12, jLabel13, jLabel14, jLabel15, jLabel16, jLabel17;
		private JLabel jLabel18, jLabel19, jLabel20;
		private JTextField jTextField1, jTextField10, jTextField11;
		private JComboBox jComboBox1, jComboBox2, jComboBox3, jComboBox4, jComboBox5, jComboBox6, jComboBox7, jComboBox8, jComboBox9, jComboBox10, jComboBox11, jComboBox12;
		private JComboBox jComboBox13, jComboBox14, jComboBox15;
		private JButton jButton1, jButton2;
		private JPanel jPanel1;
		private LinkedList<Object> Ciudades, Aviones;
		private int Capacidad;
		
		public VentanaVuelos() {

//	 		Opciones De Ventana "Agregar Vuelos"
			
	        jPanel1 = new JPanel();
	        jLabel1 = new JLabel();
	        jTextField1 = new JTextField();
	        jLabel2 = new JLabel();
	        jComboBox1 = new JComboBox();
	        jLabel3 = new JLabel();
	        jComboBox2 = new JComboBox();
	        jButton1 = new JButton();
	        jComboBox3 = new JComboBox(); // Para Elegir Código De Avión
	        jComboBox4 = new JComboBox(); // Para Elegir Hora De Salida
	        jComboBox5 = new JComboBox(); // Para Elegir Minutos De Salida
	        jComboBox6 = new JComboBox(); // Para Elegir AM/PM De Salida
	        jComboBox7 = new JComboBox(); // Para Elegir Hora De Llegada
	        jComboBox8 = new JComboBox(); // Para Elegir Minutos De Llegada
	        jComboBox9 = new JComboBox(); // Para Elegir AM/PM De Llegada
	        jComboBox10 = new JComboBox(); // Para Elegir Dia De Salida
	        jComboBox11 = new JComboBox(); // Para Elegir Mes De Salida
	        jComboBox12 = new JComboBox(); // Para Elegir Año de Salida
	        jComboBox13 = new JComboBox(); // Para Elegir Dia de Llegada
	        jComboBox14 = new JComboBox(); // Para Elegir Mes de Llegada
	        jComboBox15 = new JComboBox(); // Para Elegir Año de Llegada
	        jButton2 = new JButton();
	        jLabel4 = new JLabel();
	        jLabel5 = new JLabel();
	        jLabel6 = new JLabel();
	        jLabel7 = new JLabel();
	        jLabel8 = new JLabel();
	        jLabel9 = new JLabel();
	        jLabel10 = new JLabel();
	        jLabel11 = new JLabel();
	        jLabel12 = new JLabel();
	        jLabel13 = new JLabel();
	        jLabel14 = new JLabel();
	        jLabel15 = new JLabel();
	        jLabel16 = new JLabel();
	        jLabel17 = new JLabel();
	        jLabel18 = new JLabel();
	        jLabel19 = new JLabel();
	        jLabel20 = new JLabel();
	        jTextField10 = new JTextField();
	        jTextField11 = new JTextField();
	        Ciudades = new LinkedList<Object>();
	        Aviones = new LinkedList<Object>();
	        Capacidad = 0;

	        setTitle("Agregar Vuelos A Base De Datos");
			setModalityType(java.awt.Dialog.ModalityType.APPLICATION_MODAL);
	        setResizable(false);
	        setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
	        // (MODIF)
	        System.out.println("Centro X: " + GUI.getBounds().getCenterX() + " Centro Y: " + GUI.getBounds().getCenterY());
	        System.out.println("Punto X: " + GUI.getBounds().x + "Punto Y: " + GUI.getBounds().y);
	        setBounds(new java.awt.Rectangle(GUI.getBounds().x + 159, GUI.getBounds().y + 239, 683, 288));
	        System.out.println("Centro Ventanita X: " + getBounds().getCenterX() + " Centro Ventanita Y: " + getBounds().getCenterY());
	        // (MODIF)
	        //setBounds(new java.awt.Rectangle(143, 223, 680, 321));
	        jPanel1.setBorder(BorderFactory.createEtchedBorder());
	        jPanel1.setName("");

	        jButton1.setText("Agregar");
	        jButton1.addActionListener(new java.awt.event.ActionListener() {
	            public void actionPerformed(java.awt.event.ActionEvent evt) {
	            	jButton1ActionPerformed(evt);
	            }
	        });
	        
	        addWindowListener( new java.awt.event.WindowAdapter() {
	        	public void windowClosing(java.awt.event.WindowEvent event) {
	        		CerrarVentana(event);
	        	}
	        });
 
	        Ciudades = GUI.Inicio.BD.BuscoCiudadesConAeropuertos();
			for (int i=0;i<Ciudades.size();i++) {
				jComboBox1.addItem(Ciudades.get(i));
				jComboBox2.addItem(Ciudades.get(i));
			}
			jComboBox1.setSelectedIndex(9);
			jComboBox2.setSelectedIndex(2);
			
			Aviones = GUI.Inicio.BD.BuscoAviones();
			for (int i=0;i<Aviones.size();i++) { jComboBox3.addItem(Aviones.get(i)); }
			
			for (int i=0;i<13;i++) {
				jComboBox4.addItem(makeObj(Integer.toString(i)));
				jComboBox7.addItem(makeObj(Integer.toString(i)));
				if (i<2) {
					jComboBox5.addItem(makeObj("0" + Integer.toString(i*5)));
					jComboBox8.addItem(makeObj("0" + Integer.toString(i*5)));
				}
				else if (i<12) {
					jComboBox5.addItem(makeObj(Integer.toString(i*5)));
					jComboBox8.addItem(makeObj(Integer.toString(i*5)));
				}
			}
			
			for (int i=1;i<32;i++) {
				if (i<10) {
					jComboBox10.addItem(makeObj("0" + Integer.toString(i)));
					jComboBox11.addItem(makeObj("0" + Integer.toString(i)));
					jComboBox13.addItem(makeObj("0" + Integer.toString(i)));
					jComboBox14.addItem(makeObj("0" + Integer.toString(i)));
				}
				else {
					if (i<13) {
						jComboBox11.addItem(makeObj(Integer.toString(i)));
						jComboBox14.addItem(makeObj(Integer.toString(i)));
					}
					jComboBox10.addItem(makeObj(Integer.toString(i)));
					jComboBox13.addItem(makeObj(Integer.toString(i)));
				}
			}
			
			jComboBox6.addItem(makeObj("AM"));
			jComboBox6.addItem(makeObj("PM"));
			jComboBox9.addItem(makeObj("AM"));
			jComboBox9.addItem(makeObj("PM"));
			jComboBox12.addItem(makeObj("2008"));
			jComboBox12.addItem(makeObj("2009"));
			jComboBox12.addItem(makeObj("2010"));
			jComboBox15.addItem(makeObj("2008"));
			jComboBox15.addItem(makeObj("2009"));
			jComboBox15.addItem(makeObj("2010"));
			
			jComboBox3.addItemListener(new java.awt.event.ItemListener() {
	            public void itemStateChanged(java.awt.event.ItemEvent evt) {
	            	jComboBox3ActionPerformed(evt);
	            }
	        });
			
			Capacidad = GUI.Inicio.BD.BuscoCapacidadAvion(jComboBox3.getSelectedItem().toString());
			jTextField11.setText(Integer.toString(Capacidad));
			
	        jButton2.setText("Salir");
	        jButton2.addActionListener(new java.awt.event.ActionListener() {
	            public void actionPerformed(java.awt.event.ActionEvent evt) {
	            	jButton2ActionPerformed(evt);
	            }
	        });

	        jLabel1.setHorizontalAlignment(SwingConstants.RIGHT);
	        jLabel1.setText("Número De Vuelo");

	        jLabel2.setHorizontalAlignment(SwingConstants.RIGHT);
	        jLabel2.setText("Ciudad Origen");

	        jLabel3.setHorizontalAlignment(SwingConstants.RIGHT);
	        jLabel3.setText("Ciudad Destino");
	        
	        jLabel4.setHorizontalAlignment(SwingConstants.RIGHT);
	        jLabel4.setText("Código De Avión");

	        jLabel5.setHorizontalAlignment(SwingConstants.RIGHT);
	        jLabel5.setText("Horario Salida");

	        jLabel6.setHorizontalAlignment(SwingConstants.RIGHT);
	        jLabel6.setText("Horario Llegada");

	        jLabel7.setHorizontalAlignment(SwingConstants.RIGHT);
	        jLabel7.setText("Fecha Salida");

	        jLabel8.setHorizontalAlignment(SwingConstants.RIGHT);
	        jLabel8.setText("Fecha Llegada");

	        jLabel9.setHorizontalAlignment(SwingConstants.RIGHT);
	        jLabel9.setText("Precio (Pesos Arg.)");

	        jLabel10.setHorizontalAlignment(SwingConstants.RIGHT);
	        jLabel10.setText("Asientos Libres");
	        
	        jLabel11.setHorizontalAlignment(SwingConstants.CENTER);
	        jLabel11.setText("Hs");
	        
	        jLabel12.setHorizontalAlignment(SwingConstants.CENTER);
	        jLabel12.setText("Min");
	        
	        jLabel13.setHorizontalAlignment(SwingConstants.CENTER);
	        jLabel13.setText("Hs");
	        
	        jLabel14.setHorizontalAlignment(SwingConstants.CENTER);
	        jLabel14.setText("Min");
	        
	        jLabel15.setHorizontalAlignment(SwingConstants.CENTER);
	        jLabel15.setText("Día");
	        
	        jLabel16.setHorizontalAlignment(SwingConstants.CENTER);
	        jLabel16.setText("Mes");
	        
	        jLabel17.setHorizontalAlignment(SwingConstants.CENTER);
	        jLabel17.setText("Año");
	        
	        jLabel18.setHorizontalAlignment(SwingConstants.CENTER);
	        jLabel18.setText("Día");
	        
	        jLabel19.setHorizontalAlignment(SwingConstants.CENTER);
	        jLabel19.setText("Mes");
	        
	        jLabel20.setHorizontalAlignment(SwingConstants.CENTER);
	        jLabel20.setText("Año");

	        GroupLayout jPanel1Layout = new GroupLayout(jPanel1);
	        jPanel1.setLayout(jPanel1Layout);
	        jPanel1Layout.setHorizontalGroup(
	            jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
	            .addGroup(jPanel1Layout.createSequentialGroup()
	                .addContainerGap()
	                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
	                    .addGroup(jPanel1Layout.createSequentialGroup()
	                        .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING) //Arranca lado izq.
	                            .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(jLabel1, GroupLayout.PREFERRED_SIZE, 113, GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jTextField1, GroupLayout.PREFERRED_SIZE, 180, GroupLayout.PREFERRED_SIZE))
	                            .addGroup(jPanel1Layout.createSequentialGroup()
	                                .addComponent(jLabel2, GroupLayout.PREFERRED_SIZE, 113, GroupLayout.PREFERRED_SIZE)
	                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
	                                .addComponent(jComboBox1, GroupLayout.PREFERRED_SIZE, 179, GroupLayout.PREFERRED_SIZE))
	                            .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(jLabel3, GroupLayout.PREFERRED_SIZE, 113, GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jComboBox2, GroupLayout.PREFERRED_SIZE, 179, GroupLayout.PREFERRED_SIZE))
	                            .addGroup(jPanel1Layout.createSequentialGroup()
	                                .addComponent(jLabel5, GroupLayout.PREFERRED_SIZE, 113, GroupLayout.PREFERRED_SIZE)
	                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                   	.addComponent(jComboBox4, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
                                   	.addComponent(jLabel11, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
                                   	.addComponent(jComboBox5, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
                                   	.addComponent(jLabel12, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
                                  	.addComponent(jComboBox6, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE))	                                
	                            .addGroup(jPanel1Layout.createSequentialGroup()
	                        	    .addComponent(jLabel6, GroupLayout.PREFERRED_SIZE, 113, GroupLayout.PREFERRED_SIZE)
	                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                	.addComponent(jComboBox7, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel13, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jComboBox8, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel14, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jComboBox9, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE))) // Termina Lado Izquierdo
	                        .addGap(8, 8, 8)
                            .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
	                            .addGroup(jPanel1Layout.createSequentialGroup()
	                                .addComponent(jLabel10, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE)
	                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
	                                .addComponent(jTextField11, GroupLayout.PREFERRED_SIZE, 180, GroupLayout.PREFERRED_SIZE))
	                            .addGroup(jPanel1Layout.createSequentialGroup()
	                                .addComponent(jLabel9, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE)
	                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
	                                .addComponent(jTextField10, GroupLayout.PREFERRED_SIZE, 180, GroupLayout.PREFERRED_SIZE))
	                            .addGroup(jPanel1Layout.createSequentialGroup()
	                                .addComponent(jLabel8, GroupLayout.PREFERRED_SIZE, 95, GroupLayout.PREFERRED_SIZE)
	                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 3, GroupLayout.PREFERRED_SIZE)
	                               	.addComponent(jLabel18, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
	                                .addComponent(jComboBox13, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel19, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
	                                .addComponent(jComboBox14, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
	                                .addComponent(jLabel20, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jComboBox15, GroupLayout.PREFERRED_SIZE, 55, GroupLayout.PREFERRED_SIZE))
	                            .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(jLabel4, GroupLayout.DEFAULT_SIZE, 120, GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                	.addComponent(jComboBox3, GroupLayout.PREFERRED_SIZE, 179, GroupLayout.PREFERRED_SIZE))
	                            .addGroup(jPanel1Layout.createSequentialGroup()
	                                .addComponent(jLabel7, GroupLayout.PREFERRED_SIZE, 95, GroupLayout.PREFERRED_SIZE)
	                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 3, GroupLayout.PREFERRED_SIZE)
	                               	.addComponent(jLabel15, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
	                                .addComponent(jComboBox10, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel16, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
	                                .addComponent(jComboBox11, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
	                                .addComponent(jLabel17, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jComboBox12, GroupLayout.PREFERRED_SIZE, 55, GroupLayout.PREFERRED_SIZE)))
	                        .addContainerGap())
	                    .addGroup(GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
		                    .addGap(207, 207, 207)
	                    	.addComponent(jButton1, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
	                    	.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 10, Short.MAX_VALUE)
	                        .addComponent(jButton2, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
	                        .addGap(220, 220, 220))))
	        );
	        jPanel1Layout.setVerticalGroup(
	            jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
	            .addGroup(jPanel1Layout.createSequentialGroup()
	                .addContainerGap()
	                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
	                    .addGroup(jPanel1Layout.createSequentialGroup()
	                        .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
	                            .addComponent(jLabel1)
	                            .addComponent(jTextField1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
	                            .addComponent(jLabel15)
	                            .addComponent(jLabel16)
	                            .addComponent(jLabel17)
	                            .addComponent(jLabel7)
	                            .addComponent(jComboBox10, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
	                            .addComponent(jComboBox11, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
	                            .addComponent(jComboBox12, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE))
	                        .addGap(16, 16, 16)
	                        .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
	                            .addComponent(jLabel2, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
	                            .addComponent(jComboBox1, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
	                            .addComponent(jLabel8)
	                            .addComponent(jLabel18)
	                            .addComponent(jLabel19)
	                            .addComponent(jLabel20)
	                            .addComponent(jComboBox13, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
	                            .addComponent(jComboBox14, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
	                            .addComponent(jComboBox15, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE))
	                        .addGap(16, 16, 16)
	                        .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
	                            .addComponent(jComboBox2, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
		                        .addComponent(jLabel4)
		                        .addComponent(jComboBox3, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
	                            .addComponent(jLabel3))
	                        .addGap(16, 16, 16)
	                        .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
	                            .addComponent(jLabel10)
	                            .addComponent(jTextField11, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
	                            .addComponent(jComboBox4, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
	                            .addComponent(jComboBox5, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
	                            .addComponent(jComboBox6, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
	                            .addComponent(jLabel5)
	                            .addComponent(jLabel11)
	                            .addComponent(jLabel12))
	                        .addGap(16, 16, 16)
	                        .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
		                        .addComponent(jLabel9)
   	                            .addComponent(jTextField10, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
   	                            .addComponent(jLabel13)
	                            .addComponent(jLabel14)
	                            .addComponent(jLabel6)
	                            .addComponent(jComboBox7, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
	                            .addComponent(jComboBox8, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
	                            .addComponent(jComboBox9, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE))
	                        .addGap(14, 14, 14)))
	                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
	                    .addComponent(jButton1)
	                    .addComponent(jButton2))
	                .addContainerGap())
	        );

	        GroupLayout layout = new GroupLayout(getContentPane());
	        getContentPane().setLayout(layout);
	        layout.setHorizontalGroup(
	            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
	            .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
	                .addContainerGap()
	                .addComponent(jPanel1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
	                .addContainerGap())
	        );
	        layout.setVerticalGroup(
	            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
	            .addGroup(layout.createSequentialGroup()
	                .addContainerGap()
	                .addComponent(jPanel1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
	                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
	        );
//	        pack();
	    }
		
    	private void jComboBox3ActionPerformed(java.awt.event.ItemEvent evt) {
    		if (this.isVisible()) {
    			Capacidad = GUI.Inicio.BD.BuscoCapacidadAvion(jComboBox3.getSelectedItem().toString());
    			jTextField11.setText(Integer.toString(Capacidad));
    		}
    	}
		
	    public void CargarDatos() {
			jComboBox1.removeAllItems();
			jComboBox2.removeAllItems();
			jComboBox3.removeAllItems();
	        Ciudades = GUI.Inicio.BD.BuscoCiudadesConAeropuertos();
			for (int i=0;i<Ciudades.size();i++) {
				jComboBox1.addItem(Ciudades.get(i));
				jComboBox2.addItem(Ciudades.get(i));
			}
			Aviones = GUI.Inicio.BD.BuscoAviones();
			for (int i=0;i<Aviones.size();i++) { jComboBox3.addItem(Aviones.get(i)); }
			jComboBox1.setSelectedIndex(9);
			jComboBox2.setSelectedIndex(2);
			Capacidad = GUI.Inicio.BD.BuscoCapacidadAvion(jComboBox3.getSelectedItem().toString());
			jTextField11.setText(Integer.toString(Capacidad));
		}
		
	    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {
	    	int Resultado = GUI.Inicio.BD.AgregoVuelo(jTextField1.getText(), jComboBox1.getSelectedItem().toString(), jComboBox2.getSelectedItem().toString(), 
	    			jComboBox3.getSelectedItem().toString(), jComboBox4.getSelectedItem().toString() + ":" + jComboBox5.getSelectedItem().toString() + " " + 
	    			jComboBox6.getSelectedItem().toString(), jComboBox7.getSelectedItem().toString() + ":" + jComboBox8.getSelectedItem().toString() + " " + 
	    			jComboBox9.getSelectedItem().toString(), jComboBox10.getSelectedItem().toString() + "/" + jComboBox11.getSelectedItem().toString() + "/" + 
	    			jComboBox12.getSelectedItem().toString(), jComboBox13.getSelectedItem().toString() + "/" + jComboBox14.getSelectedItem().toString() + "/" + 
	    			jComboBox15.getSelectedItem().toString(), jTextField10.getText(), jTextField11.getText());
    	  	if (Resultado == 0) { GUI.jTextArea2.setText("Los datos del vuelo " + jTextField1.getText() + " han sido cargados en la base de datos"); }
    	  	else { GUI.jTextArea2.setText("No se pudo almacenar los datos del vuelo en la base de datos. Por favor, reingreselos."); }
        	jComboBox4.setSelectedIndex(0);
        	jComboBox5.setSelectedIndex(0);
        	jComboBox6.setSelectedIndex(0);
        	jComboBox7.setSelectedIndex(0);
        	jComboBox8.setSelectedIndex(0);
        	jComboBox9.setSelectedIndex(0);
        	jComboBox10.setSelectedIndex(0);
        	jComboBox11.setSelectedIndex(0);
        	jComboBox12.setSelectedIndex(0);
        	jComboBox13.setSelectedIndex(0);
        	jComboBox14.setSelectedIndex(0);
        	jComboBox15.setSelectedIndex(0);
    	  	jTextField1.setText("");
			jTextField10.setText("");
			jTextField11.setText("");
			dispose();
	    }
        
        private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {
        	jComboBox4.setSelectedIndex(0);
        	jComboBox5.setSelectedIndex(0);
        	jComboBox6.setSelectedIndex(0);
        	jComboBox7.setSelectedIndex(0);
        	jComboBox8.setSelectedIndex(0);
        	jComboBox9.setSelectedIndex(0);
        	jComboBox10.setSelectedIndex(0);
        	jComboBox11.setSelectedIndex(0);
        	jComboBox12.setSelectedIndex(0);
        	jComboBox13.setSelectedIndex(0);
        	jComboBox14.setSelectedIndex(0);
        	jComboBox15.setSelectedIndex(0);
        	jTextField1.setText("");
			jTextField10.setText("");
			jTextField11.setText("");
        	dispose();
        }
        
        public void CerrarVentana(java.awt.event.WindowEvent event) {
        	jComboBox4.setSelectedIndex(0);
        	jComboBox5.setSelectedIndex(0);
        	jComboBox6.setSelectedIndex(0);
        	jComboBox7.setSelectedIndex(0);
        	jComboBox8.setSelectedIndex(0);
        	jComboBox9.setSelectedIndex(0);
        	jComboBox10.setSelectedIndex(0);
        	jComboBox11.setSelectedIndex(0);
        	jComboBox12.setSelectedIndex(0);
        	jComboBox13.setSelectedIndex(0);
        	jComboBox14.setSelectedIndex(0);
        	jComboBox15.setSelectedIndex(0);
        	jTextField1.setText("");
			jTextField10.setText("");
			jTextField11.setText("");
        	dispose();
        }     
	}
	
	public class VentanaOpcionesGSM extends JDialog {

		private static final long serialVersionUID = 1L;
		
		private JLabel jLabel1, jLabel2; // jLabel4, jLabel5;
		private JComboBox jComboBox1, jComboBox2; //, jComboBox4, jComboBox5;
		private JButton jButton1, jButton2;
		private JPanel jPanel1;
		private int indice1, indice2;
		
		public VentanaOpcionesGSM() {
			
//	 		Opciones De Ventana "Opciones GSM" del Menu Configuración
			
	        jPanel1 = new JPanel();
	        jLabel1 = new JLabel();
	        jLabel2 = new JLabel();
	        jButton1 = new JButton();
	        jButton2 = new JButton();
//	        jLabel4 = new JLabel();
//	        jLabel5 = new JLabel();
	        jComboBox1 = new JComboBox();
	        jComboBox2 = new JComboBox();
//	        jComboBox4 = new JComboBox();
//	        jComboBox5 = new JComboBox();
	        indice1 = 6;						// Selección Del Puerto De Comunicaciones
	        indice2 = 1;						// Selección De Velocidad De Conexión
       
	        setTitle("Opciones Del Dispositivo GSM");
	        setModalityType(java.awt.Dialog.ModalityType.APPLICATION_MODAL);
	        setResizable(false);
	        setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
	        // (MODIF)
	        System.out.println("Centro X: " + GUI.getBounds().getCenterX() + " Centro Y: " + GUI.getBounds().getCenterY());
	        System.out.println("Punto X: " + GUI.getBounds().x + "Punto Y: " + GUI.getBounds().y);
	        setBounds(new java.awt.Rectangle(GUI.getBounds().x + 335, GUI.getBounds().y + 295, 330, 177));
	        System.out.println("Centro Ventanita X: " + getBounds().getCenterX() + " Centro Ventanita Y: " + getBounds().getCenterY());
	        // (MODIF)	        
	        //setBounds(new java.awt.Rectangle(324, 303, 329, 176)); 
	        jPanel1.setBorder(BorderFactory.createEtchedBorder());
	        jPanel1.setName("");
	        jLabel1.setHorizontalAlignment(SwingConstants.CENTER);
	        jLabel1.setText("Puerto Comunicaciones");

	        jLabel2.setHorizontalAlignment(SwingConstants.CENTER);
	        jLabel2.setText("Velocidad (bps)");

	        jButton1.setText("Aceptar");
	        jButton1.addActionListener(new java.awt.event.ActionListener() {
	            public void actionPerformed(java.awt.event.ActionEvent evt) {
	            	jButton1ActionPerformed(evt);
	            }
	        });

	        jButton2.setText("Cancelar");
	        jButton2.addActionListener(new java.awt.event.ActionListener() {
	            public void actionPerformed(java.awt.event.ActionEvent evt) {
	            	jButton2ActionPerformed(evt);
	            }
	        });
	        
	        addWindowListener( new java.awt.event.WindowAdapter() {
	        	public void windowClosing(java.awt.event.WindowEvent event) {
	        		CerrarVentana(event);
	        	}
	        });

//	        jLabel4.setHorizontalAlignment(SwingConstants.CENTER);
//	        jLabel4.setText("Tiempo de Lectura de SMS");

//	        jLabel5.setHorizontalAlignment(SwingConstants.CENTER);
//	        jLabel5.setText("Tiempo De Envio De SMS");

	        jComboBox1.setModel(new DefaultComboBoxModel(new String[] { "COM1", "COM2", "COM3", "COM4", "COM5", "COM6", "COM7", "COM8", "COM9", "COM10" }));

	        jComboBox2.setModel(new DefaultComboBoxModel(new String[] { "19200", "115200", }));

//	        jComboBox3.setModel(new DefaultComboBoxModel(new String[] { "Sony Ericsson", "Nokia", "Motorola", "Samsung" }));
	        
	        jComboBox1.setSelectedIndex(indice1);
	        jComboBox2.setSelectedIndex(indice2);

//	        jComboBox4.setModel(new DefaultComboBoxModel(new String[] { "Art\u00edculo 1", "Art\u00edculo 2", "Art\u00edculo 3", "Art\u00edculo 4" }));

//	        jComboBox5.setModel(new DefaultComboBoxModel(new String[] { "Art\u00edculo 1", "Art\u00edculo 2", "Art\u00edculo 3", "Art\u00edculo 4" }));

	        GroupLayout jPanel1Layout = new GroupLayout(jPanel1);
	        jPanel1.setLayout(jPanel1Layout);
	        jPanel1Layout.setHorizontalGroup(
	            jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
	            .addGroup(jPanel1Layout.createSequentialGroup()
	                .addContainerGap()
	                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
	                    .addGroup(jPanel1Layout.createSequentialGroup()
	                        .addGap(31, 31, 31)
	                    	.addComponent(jButton1, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
	                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 10, Short.MAX_VALUE)
	                        .addComponent(jButton2, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
	                        .addGap(31, 31, 31))
	                    .addGroup(GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
	                        .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
	                            .addComponent(jLabel1, GroupLayout.DEFAULT_SIZE, 134, Short.MAX_VALUE)
	                            .addComponent(jLabel2, GroupLayout.DEFAULT_SIZE, 134, Short.MAX_VALUE))
	                            //.addComponent(jLabel4, GroupLayout.DEFAULT_SIZE, 134, Short.MAX_VALUE)
	                            //.addComponent(jLabel5, GroupLayout.DEFAULT_SIZE, 134, Short.MAX_VALUE))
	                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
	                        .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
	                            .addComponent(jComboBox2, GroupLayout.PREFERRED_SIZE, 124, GroupLayout.PREFERRED_SIZE)
	                            .addComponent(jComboBox1, GroupLayout.PREFERRED_SIZE, 124, GroupLayout.PREFERRED_SIZE))))
	                            //.addComponent(jComboBox4, GroupLayout.PREFERRED_SIZE, 124, GroupLayout.PREFERRED_SIZE)
	                            //.addComponent(jComboBox5, GroupLayout.PREFERRED_SIZE, 124, GroupLayout.PREFERRED_SIZE))))
	                .addContainerGap())
	        );
	        jPanel1Layout.setVerticalGroup(
	            jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
	            .addGroup(jPanel1Layout.createSequentialGroup()
	                .addContainerGap()
	                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
	                    .addComponent(jLabel1)
	                    .addComponent(jComboBox1, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE))
	                .addGap(13, 13, 13)
	                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
	                    .addComponent(jLabel2)
	                    .addComponent(jComboBox2, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE))
	                .addGap(14, 14, 14)
/*	                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
	                    .addComponent(jLabel4)
	                    .addComponent(jComboBox4, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE))
	                .addGap(15, 15, 15)
	                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
	                    .addComponent(jComboBox5, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
	                    .addComponent(jLabel5))
	                .addGap(16, 16, 16)*/
	                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
	                    .addComponent(jButton1)
	                    .addComponent(jButton2))
	                .addContainerGap())
	        );

	        GroupLayout layout = new GroupLayout(getContentPane());
	        getContentPane().setLayout(layout);
	        layout.setHorizontalGroup(
	            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
	            .addGroup(layout.createSequentialGroup()
	                .addContainerGap()
	                .addComponent(jPanel1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
	                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
	        );
	        layout.setVerticalGroup(
	            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
	            .addGroup(layout.createSequentialGroup()
	                .addContainerGap()
	                .addComponent(jPanel1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
	                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
	        );
//	        pack();
		}
		
		private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {
        	GUI.Inicio.FijoDatosConexion(jComboBox1.getSelectedItem().toString(), jComboBox2.getSelectedItem().toString());
        	indice1 = jComboBox1.getSelectedIndex();
        	indice2 = jComboBox2.getSelectedIndex();
        	dispose();
        }
        
        private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {
	        jComboBox1.setSelectedIndex(indice1);
	        jComboBox2.setSelectedIndex(indice2);
        	dispose();
        }
        
        public void CerrarVentana(java.awt.event.WindowEvent event) {
	        jComboBox1.setSelectedIndex(indice1);
	        jComboBox2.setSelectedIndex(indice2);
	        dispose();
        }
	}

	public class VentanaArchivosXML extends JDialog {

		private static final long serialVersionUID = 1L;
		
		private JCheckBox jCheckBox1, jCheckBox2;
//		private JComboBox jComboBox1, jComboBox2, jComboBox3;
		private JButton jButton1, jButton2;
		private JPanel jPanel1;
		private boolean indice1, indice2;
		
		public VentanaArchivosXML() {
			
//	 		Opciones De Ventana "Archivos XML" del Menu Configuración
			
	        jPanel1 = new JPanel();
	        jCheckBox1 = new JCheckBox();
	        jCheckBox2 = new JCheckBox();
	        jButton1 = new JButton();
	        jButton2 = new JButton();
	        indice1 = indice2 = true;

	        setTitle("Opciones Para Los Archivos XML");
			setModalityType(java.awt.Dialog.ModalityType.APPLICATION_MODAL);
	        setResizable(false);
	        setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
	        // (MODIF)
	        System.out.println("Centro X: " + GUI.getBounds().getCenterX() + " Centro Y: " + GUI.getBounds().getCenterY());
	        System.out.println("Punto X: " + GUI.getBounds().x + "Punto Y: " + GUI.getBounds().y);
	        setBounds(new java.awt.Rectangle(GUI.getBounds().x + 335, GUI.getBounds().y + 292, 330, 182));
	        System.out.println("Centro Ventanita X: " + getBounds().getCenterX() + " Centro Ventanita Y: " + getBounds().getCenterY());
	        // (MODIF)
	        //setBounds(new java.awt.Rectangle(324, 300, 329, 182));
	        jPanel1.setBorder(BorderFactory.createEtchedBorder());
	        jPanel1.setName("");
	        
	        jCheckBox1.setText("Generar XML Para SMS Recibido");
	        jCheckBox1.setHorizontalAlignment(SwingConstants.LEFT);
	        jCheckBox1.setSelected(indice1);

	        jCheckBox2.setText("Generar XML Para SMS Enviado");
	        jCheckBox2.setHorizontalAlignment(SwingConstants.LEFT);
	        jCheckBox2.setSelected(indice2);
	        
	        jButton1.setText("Aceptar");
	        jButton1.addActionListener(new java.awt.event.ActionListener() {
	            public void actionPerformed(java.awt.event.ActionEvent evt) {
	            	jButton1ActionPerformed(evt);
	            }
	        });

	        jButton2.setText("Cancelar");
	        jButton2.addActionListener(new java.awt.event.ActionListener() {
	            public void actionPerformed(java.awt.event.ActionEvent evt) {
	            	jButton2ActionPerformed(evt);
	            }
	        });
	        
	        addWindowListener( new java.awt.event.WindowAdapter() {
	        	public void windowClosing(java.awt.event.WindowEvent event) {
	        		CerrarVentana(event);
	        	}
	        });

	        GroupLayout jPanel1Layout = new GroupLayout(jPanel1);
	        jPanel1.setLayout(jPanel1Layout);
	        jPanel1Layout.setHorizontalGroup(
	            jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
	            .addGroup(jPanel1Layout.createSequentialGroup()
	                .addContainerGap()
	                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
	                    .addGroup(jPanel1Layout.createSequentialGroup()
	                    	.addGap(42, 42, 42)
	                    	.addComponent(jCheckBox1, GroupLayout.PREFERRED_SIZE, 246, GroupLayout.PREFERRED_SIZE))
	                    .addGroup(jPanel1Layout.createSequentialGroup()
	                    	.addGap(42, 42, 42)
	                    	.addComponent(jCheckBox2, GroupLayout.PREFERRED_SIZE, 246, GroupLayout.PREFERRED_SIZE))
	                    .addGroup(jPanel1Layout.createSequentialGroup()
		                    .addGap(43, 43, 43)
	                    	.addComponent(jButton1, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
		                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 10, Short.MAX_VALUE)
		                    .addComponent(jButton2, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
	                        .addGap(43, 43, 43)))
	                .addContainerGap())
	        );
	        jPanel1Layout.setVerticalGroup(
	            jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
	            .addGroup(jPanel1Layout.createSequentialGroup()
	                .addContainerGap()
	                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
	                    .addComponent(jCheckBox1))
	                .addGap(13, 13, 13)
	                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
	                    .addComponent(jCheckBox2))
	                .addGap(15, 15, 15)
	                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
	                    .addComponent(jButton1)
	                    .addComponent(jButton2))
	                .addContainerGap())
	        );

	        GroupLayout layout = new GroupLayout(getContentPane());
	        getContentPane().setLayout(layout);
	        layout.setHorizontalGroup(
	            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
	            .addGroup(layout.createSequentialGroup()
	                .addContainerGap()
	                .addComponent(jPanel1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
	                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
	        );
	        layout.setVerticalGroup(
	            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
	            .addGroup(layout.createSequentialGroup()
	                .addContainerGap()
	                .addComponent(jPanel1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
	                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
	        );
//	        pack();
		}
		
		private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {
			if (jCheckBox1.getSelectedObjects() == null && indice1)	{ indice1 = false; }
			else if (jCheckBox1.getSelectedObjects() != null && !indice1) { indice1 = true; }
			if (jCheckBox2.getSelectedObjects() == null && indice2)	{ indice2 = false; }
			else if (jCheckBox2.getSelectedObjects() != null && !indice2) { indice2 = true; }
			GUI.Inicio.FijoConfigXML(indice1,indice2);
        	dispose();
        }
        
        private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {
        	jCheckBox1.setSelected(indice1);
        	jCheckBox2.setSelected(indice2);
        	dispose();
        }
        
        public void CerrarVentana(java.awt.event.WindowEvent event) {
        	jCheckBox1.setSelected(indice1);
        	jCheckBox2.setSelected(indice2);
	        dispose();
        }    
	}	
	
	public class VentanaTiempos extends JDialog {

		private static final long serialVersionUID = 1L;
		
		private JLabel jLabel1, jLabel2, jLabel3;
		private JComboBox jComboBox1, jComboBox2, jComboBox3;
		private JButton jButton1, jButton2;
		private JPanel jPanel1;
		private int indice1, indice2, indice3;
		
		public VentanaTiempos() {
			
//	 		Opciones De Ventana "Tiempos" del Menu Configuración
			
	        jPanel1 = new JPanel();
	        jLabel1 = new JLabel();
	        jLabel2 = new JLabel();
	        jLabel3 = new JLabel();
	        jButton1 = new JButton();
	        jButton2 = new JButton();
	        jComboBox1 = new JComboBox();
	        jComboBox2 = new JComboBox();
	        jComboBox3 = new JComboBox();
	        indice1 = 3;
	        indice2 = 0;
	        indice3 = 1;

	        setTitle("Opciones De Los Tiempos De Lectura/Escritura");
			setModalityType(java.awt.Dialog.ModalityType.APPLICATION_MODAL);
	        setResizable(false);
	        setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
	        // (MODIF)
	        System.out.println("Centro X: " + GUI.getBounds().getCenterX() + " Centro Y: " + GUI.getBounds().getCenterY());
	        System.out.println("Punto X: " + GUI.getBounds().x + "Punto Y: " + GUI.getBounds().y);
	        setBounds(new java.awt.Rectangle(GUI.getBounds().x + 281, GUI.getBounds().y + 278, 438, 211));
	        System.out.println("Centro Ventanita X: " + getBounds().getCenterX() + " Centro Ventanita Y: " + getBounds().getCenterY());
	        // (MODIF)
	        //setBounds(new java.awt.Rectangle(273, 302, 431, 178));
	        jPanel1.setBorder(BorderFactory.createEtchedBorder());
	        jPanel1.setName("");
	        jLabel1.setHorizontalAlignment(SwingConstants.RIGHT);
	        jLabel1.setText("Tiempo De Lectura De Datos Del Dispositivo GSM (seg.)");

	        jLabel2.setHorizontalAlignment(SwingConstants.RIGHT);
	        jLabel2.setText("Tiempo Entre Procesos de Mensajes SMS (seg.)");
	        
	        jLabel3.setHorizontalAlignment(SwingConstants.RIGHT);
	        jLabel3.setText("Tiempo Para Mantener Pedidos En Base De Datos (min.)");

	        jButton1.setText("Aceptar");
	        jButton1.addActionListener(new java.awt.event.ActionListener() {
	            public void actionPerformed(java.awt.event.ActionEvent evt) {
	            	jButton1ActionPerformed(evt);
	            }
	        });

	        jButton2.setText("Cancelar");
	        jButton2.addActionListener(new java.awt.event.ActionListener() {
	            public void actionPerformed(java.awt.event.ActionEvent evt) {
	            	jButton2ActionPerformed(evt);
	            }
	        });
	        
	        addWindowListener( new java.awt.event.WindowAdapter() {
	        	public void windowClosing(java.awt.event.WindowEvent event) {
	        		CerrarVentana(event);
	        	}
	        });

	        jComboBox1.setModel(new DefaultComboBoxModel(new String[] { "5", "10", "15", "20", "25", "30", "35", "40", "80", "120" }));
	        jComboBox1.setSelectedIndex(indice1);

	        jComboBox2.setModel(new DefaultComboBoxModel(new String[] { "5", "10", "15", "20", "25", "30", "35", "40", "80", "120" }));

	        jComboBox3.setModel(new DefaultComboBoxModel(new String[] { "2", "3", "4", "5", "10", "15", "20", "25", "30", "45", "60", "90", "120" }));
	        jComboBox3.setSelectedIndex(indice3);
	        
	        GroupLayout jPanel1Layout = new GroupLayout(jPanel1);
	        jPanel1.setLayout(jPanel1Layout);
	        jPanel1Layout.setHorizontalGroup(
	            jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
	            .addGroup(jPanel1Layout.createSequentialGroup()
	                .addContainerGap()
	                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
	                    .addGroup(jPanel1Layout.createSequentialGroup()
                        	.addComponent(jLabel1, GroupLayout.PREFERRED_SIZE, 318, GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 5, GroupLayout.PREFERRED_SIZE)
                            .addComponent(jComboBox1, GroupLayout.PREFERRED_SIZE, 48, GroupLayout.PREFERRED_SIZE))
	                    .addGroup(jPanel1Layout.createSequentialGroup()
                        	.addComponent(jLabel2, GroupLayout.PREFERRED_SIZE, 318, GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 5, GroupLayout.PREFERRED_SIZE)
                            .addComponent(jComboBox2, GroupLayout.PREFERRED_SIZE, 48, GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                        	.addComponent(jLabel3, GroupLayout.PREFERRED_SIZE, 318, GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 5, GroupLayout.PREFERRED_SIZE)
                            .addComponent(jComboBox3, GroupLayout.PREFERRED_SIZE, 48, GroupLayout.PREFERRED_SIZE))                            
	                    .addGroup(jPanel1Layout.createSequentialGroup()
		                    .addGap(85, 85, 85)
		                    .addComponent(jButton1, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
		                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 10, Short.MAX_VALUE)
		                    .addComponent(jButton2, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
		                    .addGap(97, 97, 97))) 
	                .addContainerGap())
	        );
	        jPanel1Layout.setVerticalGroup(
	            jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
	            .addGroup(jPanel1Layout.createSequentialGroup()
	                .addContainerGap()
	                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
		                .addComponent(jLabel1)
		                .addComponent(jComboBox1, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE))
	                .addGap(13, 13, 13)
	                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
		                .addComponent(jLabel2)
		                .addComponent(jComboBox2, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE))
   	                .addGap(13, 13, 13)
	                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
		                .addComponent(jLabel3)
		                .addComponent(jComboBox3, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE))
		            .addGap(15, 15, 15)
	                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
	                    .addComponent(jButton1)
	                    .addComponent(jButton2))
	                .addContainerGap())
	        );

	        GroupLayout layout = new GroupLayout(getContentPane());
	        getContentPane().setLayout(layout);
	        layout.setHorizontalGroup(
	            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
	            .addGroup(layout.createSequentialGroup()
	                .addContainerGap()
	                .addComponent(jPanel1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
	                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
	        );
	        layout.setVerticalGroup(
	            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
	            .addGroup(layout.createSequentialGroup()
	                .addContainerGap()
	                .addComponent(jPanel1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
	                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
	        );
//	        pack();
		}
		
		private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {
	    	indice1 = jComboBox1.getSelectedIndex();
	    	indice2 = jComboBox2.getSelectedIndex();
	    	indice3 = jComboBox3.getSelectedIndex();
	    	GUI.Inicio.FijoTiempoLecturas(jComboBox1.getSelectedItem().toString(), jComboBox2.getSelectedItem().toString(), jComboBox3.getSelectedItem().toString());
        	dispose();
        }
		
        private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {
            jComboBox1.setSelectedIndex(indice1);
            jComboBox2.setSelectedIndex(indice2);
            jComboBox3.setSelectedIndex(indice3);
        	dispose();
        }
        
        public void CerrarVentana(java.awt.event.WindowEvent event) {
            jComboBox1.setSelectedIndex(indice1);
            jComboBox2.setSelectedIndex(indice2);
            jComboBox3.setSelectedIndex(indice3);
	        dispose();
        }        
	}	
	
	public class VentanaArchivoLog extends JDialog {

		private static final long serialVersionUID = 1L;
		
		private JCheckBox jCheckBox1, jCheckBox2;
//		private JComboBox jComboBox1, jComboBox2, jComboBox3;
		private JButton jButton1, jButton2;
		private JPanel jPanel1;
		private boolean indice1, indice2;
		
		public VentanaArchivoLog() {
			
//	 		Opciones De Ventana "Archivo De Log" del Menu Configuración
			
	        jPanel1 = new JPanel();
	        jCheckBox1 = new JCheckBox();
	        jCheckBox2 = new JCheckBox();
	        jButton1 = new JButton();
	        jButton2 = new JButton();
	        indice1 = indice2 = true;
//	        jLabel4 = new JLabel();
//	        jLabel5 = new JLabel();

	        setTitle("Opciones Para El Archivo De Log");
			setModalityType(java.awt.Dialog.ModalityType.APPLICATION_MODAL);
	        setResizable(false);
	        setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
	        // (MODIF)
	        System.out.println("Centro X: " + GUI.getBounds().getCenterX() + " Centro Y: " + GUI.getBounds().getCenterY());
	        System.out.println("Punto X: " + GUI.getBounds().x + "Punto Y: " + GUI.getBounds().y);
	        setBounds(new java.awt.Rectangle(GUI.getBounds().x + 331, GUI.getBounds().y + 292, 338, 182));
	        System.out.println("Centro Ventanita X: " + getBounds().getCenterX() + " Centro Ventanita Y: " + getBounds().getCenterY());
	        // (MODIF)
	        //setBounds(new java.awt.Rectangle(320, 306, 337, 182));
	        jPanel1.setBorder(BorderFactory.createEtchedBorder());
	        jPanel1.setName("");
	        
	        jCheckBox1.setText("Generar Archivo De Log");
	        jCheckBox1.setHorizontalAlignment(SwingConstants.CENTER);
	        jCheckBox1.setSelected(indice1);

	        jCheckBox2.setText("Almacenar En Log Información De Protocolo");
	        jCheckBox2.setHorizontalAlignment(SwingConstants.CENTER);
	        jCheckBox2.setSelected(indice2);
	        
	        jButton1.setText("Aceptar");
	        jButton1.addActionListener(new java.awt.event.ActionListener() {
	            public void actionPerformed(java.awt.event.ActionEvent evt) {
	            	jButton1ActionPerformed(evt);
	            }
	        });
	        
	        jButton2.setText("Cancelar");
	        jButton2.addActionListener(new java.awt.event.ActionListener() {
	            public void actionPerformed(java.awt.event.ActionEvent evt) {
	            	jButton2ActionPerformed(evt);
	            }
	        });
	        
	        addWindowListener( new java.awt.event.WindowAdapter() {
	        	public void windowClosing(java.awt.event.WindowEvent event) {
	        		CerrarVentana(event);
	        	}
	        });

	        GroupLayout jPanel1Layout = new GroupLayout(jPanel1);
	        jPanel1.setLayout(jPanel1Layout);
	        jPanel1Layout.setHorizontalGroup(
	            jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
	            .addGroup(jPanel1Layout.createSequentialGroup()
	                .addContainerGap()
	                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
	                    .addGroup(jPanel1Layout.createSequentialGroup()
//	                    	.addGap(5, 5, 5)
                    		.addComponent(jCheckBox1, GroupLayout.PREFERRED_SIZE, 171, GroupLayout.PREFERRED_SIZE))
	                    .addGroup(jPanel1Layout.createSequentialGroup()
//	                    	.addGap(5, 5, 5)
	                    	.addComponent(jCheckBox2, GroupLayout.PREFERRED_SIZE, 287, GroupLayout.PREFERRED_SIZE))
	                    .addGroup(jPanel1Layout.createSequentialGroup()
		                    .addGap(39, 39, 39)
	                    	.addComponent(jButton1, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
		                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 10, Short.MAX_VALUE)
		                    .addComponent(jButton2, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
	                        .addGap(47, 47, 47)))
	                .addContainerGap())
	        );
	        jPanel1Layout.setVerticalGroup(
	            jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
	            .addGroup(jPanel1Layout.createSequentialGroup()
	                .addContainerGap()
	                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
	                    .addComponent(jCheckBox1))
	                .addGap(13, 13, 13)
	                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
	                    .addComponent(jCheckBox2))
	                .addGap(15, 15, 15)
	                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
	                    .addComponent(jButton1)
	                    .addComponent(jButton2))
	                .addContainerGap())
	        );

	        GroupLayout layout = new GroupLayout(getContentPane());
	        getContentPane().setLayout(layout);
	        layout.setHorizontalGroup(
	            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
	            .addGroup(layout.createSequentialGroup()
	                .addContainerGap()
	                .addComponent(jPanel1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
	                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
	        );
	        layout.setVerticalGroup(
	            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
	            .addGroup(layout.createSequentialGroup()
	                .addContainerGap()
	                .addComponent(jPanel1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
	                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
	        );
//	        pack();
		}
		
		private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {
			if (jCheckBox1.getSelectedObjects() == null && indice1)	{ indice1 = false; }
			else if (jCheckBox1.getSelectedObjects() != null && !indice1) { indice1 = true; }
			if (jCheckBox2.getSelectedObjects() == null && indice2)	{ indice2 = false; }
			else if (jCheckBox2.getSelectedObjects() != null && !indice2) { indice2 = true; }
			GUI.Inicio.FijoConfigLog(indice1,indice2);
        	dispose();
        }
        
        private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {
        	jCheckBox1.setSelected(indice1);
        	jCheckBox2.setSelected(indice2);
        	dispose();
        }
        
        public void CerrarVentana(java.awt.event.WindowEvent event) {
        	jCheckBox1.setSelected(indice1);
        	jCheckBox2.setSelected(indice2);
	        dispose();
        }        
	}	
	
	public class VentanaVentas extends JDialog {

		private static final long serialVersionUID = 1L;
		
		private JLabel jLabel1, jLabel2, jLabel3, jLabel4, jLabel5;
		public  JTextField jTextField1, jTextField2 ,jTextField3, jTextField4;
		private JComboBox jComboBox1;
		private JButton jButton1, jButton2, jButton3;
		private JPanel jPanel1;
		
		public VentanaVentas() {

//	 		Opciones De Ventana Del Botón "Finalizar" De La Parte De Reservas De La Ventana Principal
			
	        jPanel1 = new JPanel();
	        jLabel1 = new JLabel();
	        jTextField1 = new JTextField();
	        jTextField2 = new JTextField();
	        jTextField3 = new JTextField();
	        jTextField4 = new JTextField();
	        jLabel2 = new JLabel();
	        jLabel3 = new JLabel();
	        jButton1 = new JButton();
	        jButton2 = new JButton();
	        jButton3 = new JButton();
	        jLabel4 = new JLabel();
	        jLabel5 = new JLabel();
	        jComboBox1 = new JComboBox();

	        jComboBox1.setModel(new DefaultComboBoxModel(new String[] { "Tarjeta De Crédito", "Efectivo", "Depósito Bancario" }));
			    
	        setTitle("Finalizar Reserva De Vuelos");
	        setModalityType(java.awt.Dialog.ModalityType.APPLICATION_MODAL);
	        setResizable(false);
	        setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
	        // (MODIF)
	        System.out.println("Centro X: " + GUI.getBounds().getCenterX() + " Centro Y: " + GUI.getBounds().getCenterY());
	        System.out.println("Punto X: " + GUI.getBounds().x + "Punto Y: " + GUI.getBounds().y);
	        setBounds(new java.awt.Rectangle(GUI.getBounds().x + 318, GUI.getBounds().y + 220, 364, 326));
	        System.out.println("Centro Ventanita X: " + getBounds().getCenterX() + " Centro Ventanita Y: " + getBounds().getCenterY());
	        // (MODIF)
	        //setBounds(new java.awt.Rectangle(307, 236, 363, 325));
	        jPanel1.setBorder(BorderFactory.createEtchedBorder());
	        jPanel1.setName("");
	        
	        jLabel1.setHorizontalAlignment(SwingConstants.CENTER);
	        jLabel1.setText("Nombre Del Cliente");

	        jLabel2.setHorizontalAlignment(SwingConstants.CENTER);
	        jLabel2.setText("Celular Número");

	        jLabel3.setHorizontalAlignment(SwingConstants.CENTER);
	        jLabel3.setText("Número De Reserva");
	        
	        jLabel4.setHorizontalAlignment(SwingConstants.CENTER);
	        jLabel4.setText("Precio Final ($ Arg.)");

	        jLabel5.setHorizontalAlignment(SwingConstants.CENTER);
	        jLabel5.setText("Método De Pago");
	        
	        jButton1.setText("Generar Venta");
	        jButton1.addActionListener(new java.awt.event.ActionListener() {
	            public void actionPerformed(java.awt.event.ActionEvent evt) {
	            	jButton1ActionPerformed(evt);
	            }
	        });

	        jButton2.setText("Cancelar Reserva");
	        jButton2.addActionListener(new java.awt.event.ActionListener() {
	            public void actionPerformed(java.awt.event.ActionEvent evt) {
	            	jButton2ActionPerformed(evt);
	            }
	        });
	        
	        jButton3.setText("Salir Sin Modificar");
	        jButton3.addActionListener(new java.awt.event.ActionListener() {
	            public void actionPerformed(java.awt.event.ActionEvent evt) {
	            	jButton3ActionPerformed(evt);
	            }
	        });
	        
	        addWindowListener( new java.awt.event.WindowAdapter() {
	        	public void windowClosing(java.awt.event.WindowEvent event) {
	        		CerrarVentana(event);
	        	}
	        });

	        GroupLayout jPanel1Layout = new GroupLayout(jPanel1);
	        jPanel1.setLayout(jPanel1Layout);
	        jPanel1Layout.setHorizontalGroup(
	            jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
	            .addGroup(jPanel1Layout.createSequentialGroup()
	                .addContainerGap()
	                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
	                    .addGroup(jPanel1Layout.createSequentialGroup()
	                        .addComponent(jLabel4, GroupLayout.PREFERRED_SIZE, 113, GroupLayout.PREFERRED_SIZE)
	                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
	                        .addComponent(jTextField4, GroupLayout.PREFERRED_SIZE, 180, GroupLayout.PREFERRED_SIZE))
	                    .addGroup(jPanel1Layout.createSequentialGroup()
                    		.addComponent(jLabel1, GroupLayout.DEFAULT_SIZE, 113, GroupLayout.PREFERRED_SIZE)
	                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    		.addComponent(jTextField1, GroupLayout.PREFERRED_SIZE, 180, GroupLayout.PREFERRED_SIZE))
                    	.addGroup(jPanel1Layout.createSequentialGroup()
                    		.addComponent(jLabel5, GroupLayout.DEFAULT_SIZE, 113, GroupLayout.PREFERRED_SIZE)
	                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    		.addComponent(jComboBox1, GroupLayout.PREFERRED_SIZE, 179, GroupLayout.PREFERRED_SIZE))
	                    .addGroup(jPanel1Layout.createSequentialGroup()	                        
	                        .addComponent(jLabel2, GroupLayout.DEFAULT_SIZE, 113, GroupLayout.PREFERRED_SIZE)
	                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
	                    	.addComponent(jTextField2, GroupLayout.PREFERRED_SIZE, 180, GroupLayout.PREFERRED_SIZE))
	                    .addGroup(jPanel1Layout.createSequentialGroup()
		                    .addComponent(jLabel3, GroupLayout.DEFAULT_SIZE, 113, GroupLayout.PREFERRED_SIZE)
	                    	.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
		                    .addComponent(jTextField3, GroupLayout.PREFERRED_SIZE, 180, GroupLayout.PREFERRED_SIZE)))	                            	                            
	                .addContainerGap())
	            .addGroup(jPanel1Layout.createSequentialGroup()
	            	.addContainerGap()
	            	.addGap(13, 13, 13)
	                .addComponent(jButton1, GroupLayout.PREFERRED_SIZE, 147, GroupLayout.PREFERRED_SIZE)
	                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 10, Short.MAX_VALUE)
	                .addComponent(jButton2, GroupLayout.PREFERRED_SIZE, 146, GroupLayout.PREFERRED_SIZE)
	                .addGap(14, 14, 14)
	                .addContainerGap())
	            .addGroup(jPanel1Layout.createSequentialGroup()
		            .addContainerGap()
	            	.addGap(92, 92, 92)
	            	.addComponent(jButton3, GroupLayout.PREFERRED_SIZE, 146, GroupLayout.PREFERRED_SIZE)
	            	.addGap(92, 92, 92)
	            	.addContainerGap())
	        );
	        jPanel1Layout.setVerticalGroup(
	            jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
	            .addGroup(jPanel1Layout.createSequentialGroup()
	                .addContainerGap()
	                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
	                    .addComponent(jLabel1)
	                    .addComponent(jTextField1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
	                .addGap(16, 16, 16)
	                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
	                	.addComponent(jLabel2)
		                .addComponent(jTextField2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
	                .addGap(16, 16, 16)	                
	                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
	                    .addComponent(jLabel3)
	                    .addComponent(jTextField3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
	                .addGap(16, 16, 16)
	                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
		                .addComponent(jLabel4)
	                	.addComponent(jTextField4, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
	                .addGap(16, 16, 16)
	                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
			            .addComponent(jLabel5)
	                	.addComponent(jComboBox1, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE))
	                .addGap(14, 14, 14)
	                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
	                    .addComponent(jButton1)
	                    .addComponent(jButton2))
	                .addGap(12, 12, 12)
	                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
	                    .addComponent(jButton3))
	                .addContainerGap())
	        );

	        GroupLayout layout = new GroupLayout(getContentPane());
	        getContentPane().setLayout(layout);
	        layout.setHorizontalGroup(
	            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
	            .addGroup(layout.createSequentialGroup()
	                .addContainerGap()
	                .addComponent(jPanel1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
	                .addContainerGap())
	        );
	        layout.setVerticalGroup(
	            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
	            .addGroup(layout.createSequentialGroup()
	                .addContainerGap()
	                .addComponent(jPanel1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
	                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
	        );
//	        pack();
		}
		
		private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {
			int Resultado = GUI.Inicio.BD.AgregoVenta(jTextField1.getText(), jTextField2.getText(), Integer.parseInt(jTextField3.getText()), Integer.parseInt(jTextField4.getText()), 
					jComboBox1.getSelectedItem().toString());
    	  	if (Resultado == 0) { 
    	  		GUI.jTextArea2.setText("Los datos de la venta que corresponde a la reserva número " + jTextField3.getText() + " han sido cargados en la base de datos."); 
    	  		Resultado = GUI.Inicio.BD.EliminoReserva(Integer.parseInt(jTextField3.getText()));
    	  		if (Resultado == 0) {
    	  			GUI.Inicio.ListaReservas.remove(GUI.jList2.getSelectedIndex());
    	  			GUI.Inicio.ModeloLista2.remove(GUI.jList2.getSelectedIndex());
            		GUI.Inicio.LimpiarConsolaReservas();
    	  		}
    	  	}
    	  	else { GUI.jTextArea2.setText("No se pudo almacenar los datos de la venta en la base de datos. Por favor, reingreselos."); }
        	jComboBox1.setSelectedIndex(0);
        	dispose();
        }
		
        private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {
        	jComboBox1.setSelectedIndex(0);
        	int CodigoReserva = GUI.Inicio.ListaReservas.get(GUI.jList2.getSelectedIndex());
        	GUI.Inicio.BD.ActualizarDatosVuelos(CodigoReserva,1);								// Sumo En Uno Los Asientos De Los Vuelos Reservados
        	int Resultado = GUI.Inicio.BD.EliminoReserva(CodigoReserva);
        	if (Resultado == 0) {
        		GUI.jTextArea2.setText("La reserva número " + CodigoReserva + " ha sido eliminada de la base de datos.");
        		GUI.Inicio.ListaReservas.remove(GUI.jList2.getSelectedIndex());
        		GUI.Inicio.ModeloLista2.remove(GUI.jList2.getSelectedIndex());
        		GUI.Inicio.LimpiarConsolaReservas();
        	}
        	dispose();
        }
        
        private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {
        	jComboBox1.setSelectedIndex(0);
        	dispose();
        }
        
        public void CerrarVentana(java.awt.event.WindowEvent event) {
        	jComboBox1.setSelectedIndex(0);
	        dispose();
        }         
	}
	
	public class VentanaAccesoBD extends JDialog {

		private static final long serialVersionUID = 1L;
		
		private JLabel jLabel1, jLabel2;
		private JButton jButton1;
		private JTextField jTextField1;
		private JPasswordField jPasswordField1;
		private JPanel jPanel1;
		
		public VentanaAccesoBD() {
			
//	 		Opciones De Ventana De Opción "Conectar" del Menu Base De Datos
			
	        jPanel1 = new JPanel();
	        jLabel1 = new JLabel();
	        jLabel2 = new JLabel();
	        jButton1 = new JButton();
	        jTextField1 = new JTextField();
	        jPasswordField1 = new JPasswordField();

	        setTitle("Acceso A La Base De Datos");
			setModalityType(java.awt.Dialog.ModalityType.APPLICATION_MODAL);
	        setResizable(false);
	        setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
	        // (MODIF)
	        System.out.println("Centro X: " + GUI.getBounds().getCenterX() + " Centro Y: " + GUI.getBounds().getCenterY());
	        System.out.println("Punto X: " + GUI.getBounds().x + "Punto Y: " + GUI.getBounds().y);
	        setBounds(new java.awt.Rectangle(GUI.getBounds().x + 332, GUI.getBounds().y + 295, 336, 177));
	        System.out.println("Centro Ventanita X: " + getBounds().getCenterX() + " Centro Ventanita Y: " + getBounds().getCenterY());
	        // (MODIF)
	        //setBounds(new java.awt.Rectangle(321, 300, 335, 180));
	        setFocusCycleRoot(false);
	        jPanel1.setBorder(BorderFactory.createEtchedBorder());
	        jPanel1.setName("");
	        jLabel1.setHorizontalAlignment(SwingConstants.CENTER);
	        jLabel1.setText("Nombre Usuario");

	        jLabel2.setHorizontalAlignment(SwingConstants.CENTER);
	        jLabel2.setText("Contrase\u00f1a");

	        jButton1.setText("Aceptar");
	        jButton1.addActionListener(new java.awt.event.ActionListener() {
	            public void actionPerformed(java.awt.event.ActionEvent evt) {
	            	jButton1ActionPerformed(evt);
	            }
	        });

	        jTextField1.setText("root");

	        jPasswordField1.setText("glendora");
	        
	        addWindowListener( new java.awt.event.WindowAdapter() {
	        	public void windowClosing(java.awt.event.WindowEvent event) {
	        		CerrarVentana(event);
	        	}
	        });

	        GroupLayout jPanel1Layout = new GroupLayout(jPanel1);
	        jPanel1.setLayout(jPanel1Layout);
	        jPanel1Layout.setHorizontalGroup(
	            jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
	            .addGroup(jPanel1Layout.createSequentialGroup()
	                .addContainerGap()
	                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
	                    .addGroup(jPanel1Layout.createSequentialGroup()
	                        .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
	                            .addComponent(jLabel2, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
	                            .addComponent(jLabel1, GroupLayout.DEFAULT_SIZE, 97, Short.MAX_VALUE))
	                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 15, Short.MAX_VALUE)
	                        .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
	                            .addComponent(jPasswordField1)
	                            .addComponent(jTextField1, GroupLayout.DEFAULT_SIZE, 165, Short.MAX_VALUE))
	                        .addContainerGap())
	                    .addGroup(GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
		                    .addGap(89, 89, 89)
	                    	.addComponent(jButton1, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
	                    	.addGap(101, 101, 101))))
	        );
	        jPanel1Layout.setVerticalGroup(
	            jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
	            .addGroup(jPanel1Layout.createSequentialGroup()
	                .addContainerGap()
	                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
	                    .addComponent(jLabel1)
	                    .addComponent(jTextField1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
	                .addGap(13, 13, 13)
	                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
	                    .addComponent(jLabel2)
	                    .addComponent(jPasswordField1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
	                .addGap(14, 14, 14)
	                .addComponent(jButton1)
	                .addContainerGap())
	        );

	        GroupLayout layout = new GroupLayout(getContentPane());
	        getContentPane().setLayout(layout);
	        layout.setHorizontalGroup(
	            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
	            .addGroup(layout.createSequentialGroup()
	                .addContainerGap()
	                .addComponent(jPanel1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
	                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
	        );
	        layout.setVerticalGroup(
	            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
	            .addGroup(layout.createSequentialGroup()
	                .addContainerGap()
	                .addComponent(jPanel1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
	                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
	        );
//	        pack();
		}
		
		private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {
			int Resultado;
			String Password = new String(jPasswordField1.getPassword());
			Resultado = GUI.Inicio.IngresoBD(jTextField1.getText(),Password);
			if (Resultado == 1) { 
				GUI.jTextArea2.setText("No se pudo acceder a la base de datos. Ingrese nuevamente el nombre de usuario y la clave para ingresar.");
			}
			else {
				GUI.jTextField6.setText("Conectada");
		        GUI.jMenuItem4.setEnabled(false);
		        GUI.jMenuItem2.setEnabled(true); 
			}
	        jTextField1.setText("root");
	        jPasswordField1.setText("glendora");
        	dispose();
        }
        
        public void CerrarVentana(java.awt.event.WindowEvent event) {
	        jTextField1.setText("root");
	        jPasswordField1.setText("glendora");
        	dispose();
        }
	}
	
	public class VentanaCargarClientes extends JDialog {

		private static final long serialVersionUID = 1L;
		
		private JRadioButton jRadioButton1, jRadioButton2;
		private JButton jButton1;
		private JComboBox jComboBox1, jComboBox2;
		private JPanel jPanel1;
		private LinkedList<Object> Ciudades;
		private LinkedList<Object> TipoClientes;
		
		public VentanaCargarClientes() {
			
//	 		Opciones De Ventana "Cargar Contactos" Del Boton Cargar (Contactos) De La Ventana Principal
	
	        jPanel1 = new JPanel();
	        jRadioButton1 = new JRadioButton();
	        jRadioButton2 = new JRadioButton();
	        jButton1 = new JButton();
	        jComboBox1 = new JComboBox();
	        jComboBox2 = new JComboBox();
	        Ciudades = new LinkedList<Object>();
	        TipoClientes = new LinkedList<Object>();

	        setTitle("Cargar Grupo De Clientes");
			setModalityType(java.awt.Dialog.ModalityType.APPLICATION_MODAL);
	        setResizable(false);
	        setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
	        // (MODIF)
	        System.out.println("Centro X: " + GUI.getBounds().getCenterX() + " Centro Y: " + GUI.getBounds().getCenterY());
	        System.out.println("Punto X: " + GUI.getBounds().x + "Punto Y: " + GUI.getBounds().y);
	        setBounds(new java.awt.Rectangle(GUI.getBounds().x + 328, GUI.getBounds().y + 290, 345, 186));
	        System.out.println("Centro Ventanita X: " + getBounds().getCenterX() + " Centro Ventanita Y: " + getBounds().getCenterY());
	        // (MODIF)
	        //setBounds(new java.awt.Rectangle(339, 291, 300, 185));
	        setFocusCycleRoot(false);
	        jPanel1.setBorder(BorderFactory.createEtchedBorder());
	        jPanel1.setName("");
	        
	        jRadioButton1.setHorizontalAlignment(SwingConstants.LEFT);
	        jRadioButton1.setText("Por Tipo De Cliente");
	        
	        jRadioButton2.setHorizontalAlignment(SwingConstants.LEFT);
	        jRadioButton2.setText("Por Ciudad De Residencia");
	        
	        jComboBox1.setEnabled(false);
	        jComboBox2.setEnabled(false);
	        
			TipoClientes = GUI.Inicio.BD.CargarTipoClientes();
			for (int i=0;i<TipoClientes.size();i++) { jComboBox1.addItem(TipoClientes.get(i)); }
	        
	        Ciudades = GUI.Inicio.BD.BuscoCiudadesRes();
			for (int i=0;i<Ciudades.size();i++) { jComboBox2.addItem(Ciudades.get(i)); }
			
//	    	jComboBox1.setEnabled(false);
	    	
	    	jRadioButton1.addActionListener(new java.awt.event.ActionListener() {
	            public void actionPerformed(java.awt.event.ActionEvent evt) {
	            	jRadioButton1ActionPerformed(evt);
	            }
	        });
	    	
	    	jRadioButton2.addActionListener(new java.awt.event.ActionListener() {
	            public void actionPerformed(java.awt.event.ActionEvent evt) {
	            	jRadioButton2ActionPerformed(evt);
	            }
	        });
			   	
	        jButton1.setText("Aceptar");
	        jButton1.addActionListener(new java.awt.event.ActionListener() {
	            public void actionPerformed(java.awt.event.ActionEvent evt) {
	                jButton1ActionPerformed(evt);
	            }
	        });
	        
	        addWindowListener( new java.awt.event.WindowAdapter() {
	        	public void windowClosing(java.awt.event.WindowEvent event) {
	        		CerrarVentana(event);
	        	}
	        });

	        GroupLayout jPanel1Layout = new GroupLayout(jPanel1);
	        jPanel1.setLayout(jPanel1Layout);
	        jPanel1Layout.setHorizontalGroup(
	            jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
	            .addGroup(jPanel1Layout.createSequentialGroup()
	                .addContainerGap()
	                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
	                    .addGroup(jPanel1Layout.createSequentialGroup()
	                        .addComponent(jRadioButton1, GroupLayout.PREFERRED_SIZE, 179, GroupLayout.PREFERRED_SIZE)
	                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
	                        .addComponent(jComboBox1, GroupLayout.PREFERRED_SIZE, 110, GroupLayout.PREFERRED_SIZE)
	                        .addContainerGap())
	                    .addGroup(jPanel1Layout.createSequentialGroup()
	                    	.addComponent(jRadioButton2, GroupLayout.PREFERRED_SIZE, 179, GroupLayout.PREFERRED_SIZE)
		                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
		                    .addComponent(jComboBox2, GroupLayout.PREFERRED_SIZE, 110, GroupLayout.PREFERRED_SIZE)
		                    .addContainerGap())
	                    .addGroup(jPanel1Layout.createSequentialGroup()
	                    	.addGap(97, 97, 97)
	                    	.addComponent(jButton1, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)))
	                .addContainerGap())
	        );
	        jPanel1Layout.setVerticalGroup(
	            jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
	            .addGroup(jPanel1Layout.createSequentialGroup()
	                .addContainerGap()
	                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
	                	.addComponent(jRadioButton1)
	                	.addComponent(jComboBox1, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE))
	                .addGap(16, 16, 16)
	                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
	                	.addComponent(jRadioButton2)
	                	.addComponent(jComboBox2, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE))
	                .addGap(14, 14, 14)
	                .addComponent(jButton1)
	                .addContainerGap())
	        );

	        GroupLayout layout = new GroupLayout(getContentPane());
	        getContentPane().setLayout(layout);
	        layout.setHorizontalGroup(
	            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
	            .addGroup(layout.createSequentialGroup()
	                .addContainerGap()
	                .addComponent(jPanel1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
	                .addContainerGap())
	        );
	        layout.setVerticalGroup(
	            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
	            .addGroup(layout.createSequentialGroup()
	                .addContainerGap()
	                .addComponent(jPanel1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
	                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
	        );
//	        pack();
		}
		
		private void jRadioButton1ActionPerformed(java.awt.event.ActionEvent evt) {
			if (jRadioButton1.isSelected()) { jComboBox1.setEnabled(true); }
			else { jComboBox1.setEnabled(false); }
		}
			 
		private void jRadioButton2ActionPerformed(java.awt.event.ActionEvent evt) {  
			if (jRadioButton2.isSelected()) { jComboBox2.setEnabled(true); }
			else { jComboBox2.setEnabled(false); }	
		}
		
	    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {  
	    	if (jRadioButton1.isSelected() && !jRadioButton2.isSelected()) { GUI.Inicio.CargarContactos(jComboBox1.getSelectedItem(), jComboBox2.getSelectedItem(), GUI.SampleModel, 0); }
	    	else if (!jRadioButton1.isSelected() && jRadioButton2.isSelected()) { GUI.Inicio.CargarContactos(jComboBox1.getSelectedItem(), jComboBox2.getSelectedItem(), GUI.SampleModel, 1); }
	    	else if (jRadioButton1.isSelected() && jRadioButton2.isSelected()) { GUI.Inicio.CargarContactos(jComboBox1.getSelectedItem(), jComboBox2.getSelectedItem(), GUI.SampleModel, 2); }
	    	else { GUI.jTextArea2.setText("Para cargar los datos de los contactos se debe seleccionar por tipo de cliente, por ciudad de residencia o por una combinación de ambos."); }
			setVisible(false);
	    }
	    
	    public void CargarDatos() {								// Cargo Datos En JComboBoxes
			jComboBox1.removeAllItems();
			TipoClientes = GUI.Inicio.BD.CargarTipoClientes();
			for (int i=0;i<TipoClientes.size();i++) { jComboBox1.addItem(TipoClientes.get(i)); }
			jComboBox2.removeAllItems();
	        Ciudades = GUI.Inicio.BD.BuscoCiudadesRes();
			for (int i=0;i<Ciudades.size();i++) { jComboBox2.addItem(Ciudades.get(i)); }
	    }
		
        public void CerrarVentana(java.awt.event.WindowEvent event) {
        	dispose();
        }
	}
	
	public class VentanaModificarVuelos extends JDialog {

		private static final long serialVersionUID = 1L;
		
		private JLabel jLabel1, jLabel2 , jLabel3;
		private JButton jButton1, jButton2;
		private JComboBox jComboBox1;
		private JTextField jTextField1, jTextField2;
		private JPanel jPanel1;
		private LinkedList<Object> Vuelos;
		private String Estado;
		
		public VentanaModificarVuelos() {
			
//	 		Opciones De Ventana "Guardar Contactos" Del Boton Guardar (Contactos) De La Ventana Principal			
			
	        jPanel1 = new JPanel();
	        jLabel1 = new JLabel();
	        jLabel2 = new JLabel();
	        jLabel3 = new JLabel();
	        jButton1 = new JButton();
	        jButton2 = new JButton();
	        jComboBox1 = new JComboBox();
	        jTextField1 = new JTextField();
	        jTextField2 = new JTextField();
	        Vuelos = new LinkedList<Object>();
	        Estado = "";

	        setTitle("Modificar Estado De Un Vuelo");
			setModalityType(java.awt.Dialog.ModalityType.APPLICATION_MODAL);
	        setResizable(false);
	        setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
	        // (MODIF)
	        System.out.println("Centro X: " + GUI.getBounds().getCenterX() + " Centro Y: " + GUI.getBounds().getCenterY());
	        System.out.println("Punto X: " + GUI.getBounds().x + "Punto Y: " + GUI.getBounds().y);
	        setBounds(new java.awt.Rectangle(GUI.getBounds().x + 318, GUI.getBounds().y + 275, 364, 216));
	        System.out.println("Centro Ventanita X: " + getBounds().getCenterX() + " Centro Ventanita Y: " + getBounds().getCenterY());
	        // (MODIF)
	        //setBounds(new java.awt.Rectangle(339, 291, 300, 185));
	        setFocusCycleRoot(false);
	        jPanel1.setBorder(BorderFactory.createEtchedBorder());
	        jPanel1.setName("");
	        jLabel1.setHorizontalAlignment(SwingConstants.CENTER);
	        jLabel1.setText("Número De Vuelo");

	        jLabel2.setHorizontalAlignment(SwingConstants.CENTER);
	        jLabel2.setText("Estado Actual");
	        
	        jLabel3.setHorizontalAlignment(SwingConstants.CENTER);
	        jLabel3.setText("Nuevo Estado");

	        jButton1.setText("Aceptar");
	        jButton1.addActionListener(new java.awt.event.ActionListener() {
	            public void actionPerformed(java.awt.event.ActionEvent evt) {
	                jButton1ActionPerformed(evt);
	            }
	        });
	        
	        jButton2.setText("Salir");
	        jButton2.addActionListener(new java.awt.event.ActionListener() {
	            public void actionPerformed(java.awt.event.ActionEvent evt) {
	                jButton2ActionPerformed(evt);
	            }
	        });
	        
	        addWindowListener( new java.awt.event.WindowAdapter() {
	        	public void windowClosing(java.awt.event.WindowEvent event) {
	        		CerrarVentana(event);
	        	}
	        });
	        
	        Vuelos = GUI.Inicio.BD.BuscoVueloModificar();
			for (int i=0;i<Vuelos.size();i++) {
				jComboBox1.addItem(Vuelos.get(i));	
			}
			
			jComboBox1.addItemListener(new java.awt.event.ItemListener() {
	            public void itemStateChanged(java.awt.event.ItemEvent evt) {
	            	jComboBox1ActionPerformed(evt);
	            }
	        });
			
			Estado = GUI.Inicio.BD.BuscoEstadoVuelo(Integer.parseInt(jComboBox1.getSelectedItem().toString()));
			jTextField1.setEditable(false);
			jTextField1.setText(Estado);

	        GroupLayout jPanel1Layout = new GroupLayout(jPanel1);
	        jPanel1.setLayout(jPanel1Layout);
	        jPanel1Layout.setHorizontalGroup(
	            jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
	            .addGroup(jPanel1Layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    	.addGroup(jPanel1Layout.createSequentialGroup()
                    		.addComponent(jLabel1, GroupLayout.PREFERRED_SIZE, 113, GroupLayout.PREFERRED_SIZE)
                    		.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    		.addComponent(jComboBox1, GroupLayout.PREFERRED_SIZE, 179, GroupLayout.PREFERRED_SIZE))
                    	.addGroup(jPanel1Layout.createSequentialGroup()
                    		.addComponent(jLabel2, GroupLayout.PREFERRED_SIZE, 113, GroupLayout.PREFERRED_SIZE)
                    		.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    		.addComponent(jTextField1, GroupLayout.PREFERRED_SIZE, 179, GroupLayout.PREFERRED_SIZE))
                    	.addGroup(jPanel1Layout.createSequentialGroup()
                    		.addComponent(jLabel3, GroupLayout.PREFERRED_SIZE, 113, GroupLayout.PREFERRED_SIZE)
                    		.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    		.addComponent(jTextField2, GroupLayout.PREFERRED_SIZE, 180, GroupLayout.PREFERRED_SIZE)))                       
	                .addContainerGap())
	            .addGroup(jPanel1Layout.createSequentialGroup()
                    .addContainerGap()
                    .addGap(60, 60, 60)
	                .addComponent(jButton1, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
	                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 10, Short.MAX_VALUE)
	                .addComponent(jButton2, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
                    .addGap(60, 60, 60)
	                .addContainerGap())
	        );
	        jPanel1Layout.setVerticalGroup(
	            jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
	            .addGroup(jPanel1Layout.createSequentialGroup()
	                .addContainerGap()
	                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
	                	.addComponent(jLabel1)
	                	.addComponent(jComboBox1, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE))         	
	                .addGap(16, 16, 16)
	                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
	                	.addComponent(jLabel2)
	                	.addComponent(jTextField1))
	                .addGap(16, 16, 16)
	                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
	                	.addComponent(jLabel3)
	                	.addComponent(jTextField2))	
	                .addGap(14, 14, 14)
	                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
	                	.addComponent(jButton1)
	                	.addComponent(jButton2))
	                .addContainerGap())
	        );

	        GroupLayout layout = new GroupLayout(getContentPane());
	        getContentPane().setLayout(layout);
	        layout.setHorizontalGroup(
	            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
	            .addGroup(layout.createSequentialGroup()
	                .addContainerGap()
	                .addComponent(jPanel1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
	                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
	        );
	        layout.setVerticalGroup(
	            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
	            .addGroup(layout.createSequentialGroup()
	                .addContainerGap()
	                .addComponent(jPanel1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
	                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
	        );
//	        pack();
		}

//		private Object makeObj(String item)  {
//			return new Object() { public String toString() { return item; } };
//		}
		
    	private void jComboBox1ActionPerformed(java.awt.event.ItemEvent evt) {
    		if (this.isVisible()) {
    			Estado = GUI.Inicio.BD.BuscoEstadoVuelo(Integer.parseInt(jComboBox1.getSelectedItem().toString()));
    			jTextField1.setText(Estado);
    		}
    	}
		
	    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {
	    	int Resultado = GUI.Inicio.BD.ModificoEstadoVuelo(Integer.parseInt(jComboBox1.getSelectedItem().toString()), jTextField2.getText());
        	if (Resultado == 0) {
        		GUI.jTextArea2.setText("El estado del vuelo número " + jComboBox1.getSelectedItem().toString() + " ha sido modificado.");
        	}
    		else { GUI.jTextArea2.setText("No se pudo modificar el estado del vuelo número " + jComboBox1.getSelectedItem().toString() + ". Por favor, intente nuevamente."); }
	    	jComboBox1.setSelectedIndex(0);	
        	dispose();
	    }
	    
	    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {  
	    	jComboBox1.setSelectedIndex(0);	    	
	       	dispose();
		}
          
	    public void CargarDatos() {	// Cargo Datos En JComboBoxes
			jComboBox1.removeAllItems();
	        Vuelos = GUI.Inicio.BD.BuscoVueloModificar();
			for (int i=0;i<Vuelos.size();i++) { jComboBox1.addItem(Vuelos.get(i)); }
			jComboBox1.setSelectedIndex(0);
			Estado = GUI.Inicio.BD.BuscoEstadoVuelo(Integer.parseInt(jComboBox1.getSelectedItem().toString()));
			jTextField1.setText(Estado);
    		jTextField2.setText("");
	    }
	    
	    public void CerrarVentana(java.awt.event.WindowEvent event) {
	    	jComboBox1.setSelectedIndex(0);
	    	dispose();
        }
	}

	public class VentanaAcercaDe extends JDialog {

		private static final long serialVersionUID = 1L;
		
		private JLabel jLabel1, jLabel2, jLabel3;
		private JButton jButton1;
		private JPanel jPanel1;
		
		public VentanaAcercaDe() {
			
//	 		Opciones De Ventana "Mi Trabajo Final de Carrera" del Menu Acerca De
			
	        jPanel1 = new JPanel();
	        jLabel1 = new JLabel();
	        jLabel2 = new JLabel();
	        jLabel3 = new JLabel();
	        jButton1 = new JButton();
	        
	        setTitle("Acerca De Mi Trabajo Final De Carrera");
			setModalityType(java.awt.Dialog.ModalityType.APPLICATION_MODAL);
	        setResizable(false);
	        setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
	        // (MODIF)
	        System.out.println("Centro X: " + GUI.getBounds().getCenterX() + " Centro Y: " + GUI.getBounds().getCenterY());
	        System.out.println("Punto X: " + GUI.getBounds().x + "Punto Y: " + GUI.getBounds().y);
	        setBounds(new java.awt.Rectangle(GUI.getBounds().x + 300, GUI.getBounds().y + 276, 401, 214));
	        System.out.println("Centro Ventanita X: " + getBounds().getCenterX() + " Centro Ventanita Y: " + getBounds().getCenterY());
	        // (MODIF)
	        //setBounds(new java.awt.Rectangle(289, 235, 400, 300));
	        jPanel1.setBorder(BorderFactory.createEtchedBorder());
	        jPanel1.setName("");

	        jLabel1.setFont(new java.awt.Font("SansSerif", 1, 24));
	        jLabel1.setHorizontalAlignment(SwingConstants.CENTER);
	        jLabel1.setText("Servidor de SMS");
	        
	        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 12));
	        jLabel2.setHorizontalAlignment(SwingConstants.CENTER);
	        jLabel2.setText("Autor: Mauricio Calgaro");

	        jLabel3.setHorizontalAlignment(SwingConstants.CENTER);
	        jLabel3.setText("Version 1.0");

	        jButton1.setText("Aceptar");
	        jButton1.setHorizontalTextPosition(SwingConstants.CENTER);
	        jButton1.addActionListener(new java.awt.event.ActionListener() {
	        	public void actionPerformed(java.awt.event.ActionEvent evt) {
	        		jButton1ActionPerformed(evt);
	        	}
	        });
	        
	        /*this.addKeyListener(new java.awt.event.KeyListener() {
	        	public void actionPerformed(java.awt.event.ActionEvent evt) {
	        		jButton1ActionPerformed(evt);
	        	}
	        }
	        
	        jButton1.addKeyListener(new java.awt.event.k {
	        	public void keyReleased(java.awt.event.KeyEvent event) {
	        		jTextArea1ActionPerformed(event);
	        	}
	        });*/
	        
	        addWindowListener( new java.awt.event.WindowAdapter() {
	        	public void windowClosing(java.awt.event.WindowEvent event) {
	        		CerrarVentana(event);
	        	}
	        });

	        GroupLayout jPanel1Layout = new GroupLayout(jPanel1);
	        jPanel1.setLayout(jPanel1Layout);
	        jPanel1Layout.setHorizontalGroup(
	            jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
	            .addGroup(jPanel1Layout.createSequentialGroup()
	                .addContainerGap()
	                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
	                    .addGroup(jPanel1Layout.createSequentialGroup()
		                  //.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 10, Short.MAX_VALUE)
                    		.addComponent(jLabel1, GroupLayout.PREFERRED_SIZE, 343, GroupLayout.PREFERRED_SIZE))
	                    .addGroup(jPanel1Layout.createSequentialGroup()
			              //.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 10, Short.MAX_VALUE)
	                    	.addComponent(jLabel2, GroupLayout.PREFERRED_SIZE, 343, GroupLayout.PREFERRED_SIZE))
	                    .addGroup(jPanel1Layout.createSequentialGroup()
			              //.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 10, Short.MAX_VALUE)
	                    	.addComponent(jLabel3, GroupLayout.PREFERRED_SIZE, 343, GroupLayout.PREFERRED_SIZE))	                    	
	                    .addGroup(jPanel1Layout.createSequentialGroup()
	                    	.addGap(121, 121, 121)
	                    	.addComponent(jButton1, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
	                    	.addGap(121, 121, 121)))
	                .addContainerGap())
	        );
	        jPanel1Layout.setVerticalGroup(
	            jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
	            .addGroup(jPanel1Layout.createSequentialGroup()
	                .addContainerGap()
	                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
	                    .addComponent(jLabel1))
	                .addGap(13, 13, 13)
	                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
	                    .addComponent(jLabel2))
   	                .addGap(13, 13, 13)
	                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
	                	.addComponent(jLabel3))
	                .addGap(15, 15, 15)
	                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
	                    .addComponent(jButton1))
	                .addContainerGap())
	        );

	        GroupLayout layout = new GroupLayout(getContentPane());
	        getContentPane().setLayout(layout);
	        layout.setHorizontalGroup(
	            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
	            .addGroup(layout.createSequentialGroup()
	                .addContainerGap()
	                .addComponent(jPanel1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
	                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
	        );
	        layout.setVerticalGroup(
	            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
	            .addGroup(layout.createSequentialGroup()
	                .addContainerGap()
	                .addComponent(jPanel1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
	                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
	        );
//	        pack();
		}
		
		private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {
			dispose();
        }
		
		public void CerrarVentana(java.awt.event.WindowEvent event) {
	    	dispose();
	    }
	}
	
	// Otras Ventanas...
	
}

