package interfaces;

import java.util.LinkedList;

import javax.swing.*;

public class CInterfazGrafica extends JFrame {

	private static final long serialVersionUID = 1L;

    public CInterfazGrafica() {
        initComponents();
        Inicio = new CInicio(this);
        Ventanas = new CVentanas(this);		// Inicio Objeto Creador De Ventanas
        Caracteres = 160;
    }
    // Maurix
    public JFrame VentanaPrincipal;
    public CProductor Prod1;
    public CConsumidor Consu1;
    public CService Srv1;
	public CInicio Inicio;
    public DefaultListModel SampleModel,ModeloLista2;
    private CVentanas Ventanas;						// Declaro Objeto Que Dibuja Ventanas De Dialogo
//  private CDeviceInfo InfoDispositivo;			// Declaro un Objeto Que Tiene Info Del Celular 
    public int Caracteres;
    // Maurix
    
    // Declaro Variables
    private JButton jButton1; 			// Boton "ACT/DES"
    private JButton jButton4;			// Boton "Enviar SMS"
    private JButton jButton7;			// Boton "Guardar" (Contactos)
    private JButton jButton8;			// Boton "Cargar"  (Contactos)
    private JButton jButton9;			// Boton de Reservas
    private JButton jButton10;			// Boton de Reservas
    private JLabel jLabel1;
    private JLabel jLabel5;
    private JLabel jLabel7;				// Label de "Reservas"
    private JLabel jLabel8;				// Prueba (MODIF)
    private JLabel jLabel9;				// Prueba (MODIF)
    private JLabel jLabel10;			// Prueba (MODIF)
    private JLabel jLabel11;			// Prueba (MODIF)
    private JLabel jLabel12;			// Prueba (MODIF)
    private JLabel jLabel13;
    private JLabel jLabel14;
    private JLabel jLabel15;
    private JLabel jLabel16;			// Etiqueta "Consola"
    private JLabel jLabel17;			// Etiqueta "Puerto Conexión"
    private JLabel jLabel18;			// Etiqueta "Velocidad (bps)"
    private JLabel jLabel19;			// Etiqueta "Consumidor De SMS" (MODIF)
    private JLabel jLabel20;			// Etiqueta "Productor De SMS" (MODIF)
    private JLabel jLabel21;			// Etiqueta "Datos De Reserva" (MODIF)
    public  JList jList1;				// Lista De Contactos
    public  JList jList2;				// Lista De Reservas
    private JMenu jMenu1;				// Menu "Servidor"
    private JMenu jMenu2;				// Menu "Configuración"
    private JMenu jMenu3;				// Menu "Acerca De"
    private JMenu jMenu4; 				// Menu "Base De Datos"
    private JMenu jMenu5; 				// Menu "Base De Datos" - Menu "Agregar"
    private JMenuBar jMenuBar1;			// Barra Menu
    public JMenuItem jMenuItem1;		// Menu "Menu Principal" - Opción "Conectar"
    public JMenuItem jMenuItem2;        // Menu "Base De Datos" - Opción "Desconectar"
    private JMenuItem jMenuItem3;		// Menu "Menu Principal" - Opción "Salir"
    public  JMenuItem jMenuItem4;		// Menu "Base De Datos" - Opción "Conectar" 
    private JMenuItem jMenuItem5;		// Menu "Acerca De" - Opción "Maurix Corp"
    public JMenuItem jMenuItem6;		// Menu "Menu Principal" - Opción "Desconectar"
    private JMenuItem jMenuItem7; 		// Menu "Base De Datos" - Opción "Aeropuertos"
    private JMenuItem jMenuItem8; 		// Menu "Base De Datos" - Opción "Aviones"
    private JMenuItem jMenuItem9;		// Menu "Base De Datos" - Opción "Clientes"
    private JMenuItem jMenuItem10;		// Menu "Base De Datos" - Opción "Destinos"
    private JMenuItem jMenuItem11;		// Menu "Base De Datos" - Opción "Vuelos"
    private JMenuItem jMenuItem12;		// Menu "Configuracion" - Opción "Dispositivo GSM"
    private JMenuItem jMenuItem13;		// Menu "Configuracion" - Opción "Archivos XML"
    private JMenuItem jMenuItem14;		// Menu "Configuracion" - Opción "Tiempos De Lectura"
    private JMenuItem jMenuItem15;		// Menu "Configuracion" - Opción "Archivo De Log"
    private JMenuItem jMenuItem16;		// Menu "Base De Datos" - Opción "Estados De Vuelos"
    private JPanel jPanel1;
    private JPanel jPanel2;
    private JPanel jPanel3;
    private JScrollPane jScrollPane1;   // Panel Escritura 0,0
    private JScrollPane jScrollPane2;	// Panel Escritura 1,1
    private JScrollPane jScrollPane3;   // Panel Reservas
    private JScrollPane jScrollPane6;
    private JScrollPane jScrollPane7;
    private JScrollPane jScrollPane8;
    private JScrollPane jScrollPane9;	// Panel Grande
    private JSeparator jSeparator1;
    public  JTextArea jTextArea1;
    public  JTextArea jTextArea2;
    public  JTextArea jTextArea6;		// Visualiza Informe Del Consumidor De SMS
    public  JTextArea jTextArea7;		// Visualiza Informe Del Productor De SMS
    public  JTextArea jTextArea9;		// Visualiza Datos De Las Reservas
    public  JTextField jTextField1;
    public  JTextField jTextField5;		// Indica Si el Servidor Esta Activado/Desactivado
    public  JTextField jTextField6;		// Prueba (MODIF)
    public  JTextField jTextField7;		// Prueba (MODIF)
    public  JTextField jTextField8;		// Prueba (MODIF)	Indica Porcentaje De Señal Del TC
    private JTextField jTextField9;		// Prueba (MODIF)
    public  JTextField jTextField10;	// Indica Nivel De Batería Del TC	
    public  JTextField jTextField11;	// Coloco Cantidad de SMS Entrantes
    public  JTextField jTextField12;	// Coloco Cantidad de SMS Salientes
    public  JTextField jTextField13;	// Coloco Puerto de Conexión
    public  JTextField jTextField14;	// Coloco Velocidad (bps)
    // Declaro Variables       
    
    private void initComponents() {
    	
        jPanel1 = new JPanel();
        jButton1 = new JButton();
//      jRadioButton1 = new JRadioButton();
        jTextField1 = new JTextField();
        jScrollPane1 = new JScrollPane();
//      jProgressBar1 = new JProgressBar();
        jScrollPane2 = new JScrollPane();
        jTextArea2 = new JTextArea();
        jScrollPane3 = new JScrollPane();
//      jTextArea3 = new JTextArea();
        jLabel1 = new JLabel();
        jButton4 = new JButton();
//      jProgressBar2 = new JProgressBar();
//      jProgressBar3 = new JProgressBar();
        jLabel8 = new JLabel();
        jLabel9 = new JLabel();
        jLabel11 = new JLabel();
//      jScrollPane4 = new JScrollPane();
//      jTextArea4 = new JTextArea();
//      jScrollPane5 = new JScrollPane(); (MODIF)
//      jTextArea5 = new JTextArea();
        jPanel2 = new JPanel();
        jScrollPane6 = new JScrollPane();
        jTextArea6 = new JTextArea();
        jScrollPane7 = new JScrollPane();
        jTextArea7 = new JTextArea();
        jPanel3 = new JPanel();
//      jTextField2 = new JTextField();
//      jTextField3 = new JTextField();
        jLabel5 = new JLabel();
//      jLabel6 = new JLabel();
//      jTextField4 = new JTextField();
        jScrollPane8 = new JScrollPane();
        jScrollPane9 = new JScrollPane();
        jTextArea9 = new JTextArea();
        jList1 = new JList();
        jList2 = new JList();
//      jButton5 = new JButton();
//      jButton6 = new JButton();
        jButton7 = new JButton();
        jButton8 = new JButton();
        jButton9 = new JButton();
        jButton10 = new JButton();
//      jButton11 = new JButton();
        jLabel7 = new JLabel();
        jLabel10 = new JLabel();
        jLabel12 = new JLabel();
        jLabel13 = new JLabel();
        jLabel14 = new JLabel();
        jLabel15 = new JLabel();
        jLabel16 = new JLabel();
        jLabel17 = new JLabel();
        jLabel18 = new JLabel();
        jLabel19 = new JLabel();
        jLabel20 = new JLabel();
        jLabel21 = new JLabel();
        jTextArea1 = new JTextArea();
        jTextField5 = new JTextField();
        jTextField6 = new JTextField();
        jTextField7 = new JTextField();
        jTextField8 = new JTextField();
        jTextField9 = new JTextField();
        jTextField10 = new JTextField();
        jTextField11 = new JTextField();
        jTextField12 = new JTextField();
        jTextField13 = new JTextField();
        jTextField14 = new JTextField();
//		Barra Menu
        jMenuBar1 = new JMenuBar();
//		Menu "Menu Principal"
        jMenu1 = new JMenu();
        jMenuItem1 = new JMenuItem();
        jMenuItem2 = new JMenuItem();
        jMenuItem6 = new JMenuItem();
        jSeparator1 = new JSeparator();
        jMenuItem3 = new JMenuItem();
//		Menu "Configuración"
        jMenu2 = new JMenu();
        jMenuItem12 = new JMenuItem();
        jMenuItem13 = new JMenuItem();
        jMenuItem14 = new JMenuItem();
        jMenuItem15 = new JMenuItem();
//		Menu "Base De Datos"
        jMenu4 = new JMenu();
        jMenuItem4 = new JMenuItem();
        jMenuItem16 = new JMenuItem();
//		Menu "Base De Datos" - "Agregar"
        jMenu5 = new JMenu();
        jMenuItem7 = new JMenuItem();
        jMenuItem8 = new JMenuItem();
        jMenuItem9 = new JMenuItem();
        jMenuItem10 = new JMenuItem();
        jMenuItem11 = new JMenuItem();
//		Menu "Acerca De"        
        jMenu3 = new JMenu();
        jMenuItem5 = new JMenuItem();
               
//		Opciones De Ventana Principal
        

        setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Servidor De SMS - AeroIguazú S.A.");
//      setIconImage (new ImageIcon("fichero.gif").getImage());
        setResizable(false);
        jPanel1.setBorder(BorderFactory.createEtchedBorder());
        jButton1.setText("Borrar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	jButton1ActionPerformed(evt);
            }
        });

        addWindowListener( new java.awt.event.WindowAdapter() {
        	public void windowClosing(java.awt.event.WindowEvent event) {
        		CerrarAplicacion(event);
        	}
        });

        jTextField1.setFont(jLabel9.getFont());
        jTextField1.setText(" Mensaje - Caracteres Restantes: 160");
        jTextField1.setEditable(false);
        
        
        jTextArea1.setColumns(20);
        jTextArea1.setEditable(true);
        jTextArea1.setLineWrap(true);
        jTextArea1.setRows(5);
        jTextArea1.setMinimumSize(new java.awt.Dimension(164, 22));
        jTextArea1.setText("Escriba Aqui SMS a Enviar.");
        jScrollPane1.setViewportView(jTextArea1);
 
        jTextArea1.addKeyListener(new java.awt.event.KeyAdapter() {
        	public void keyReleased(java.awt.event.KeyEvent event) {
        		jTextArea1ActionPerformed(event);
        	}
        });
     
        jTextArea2.setColumns(20);
        jTextArea2.setEditable(false);
        jTextArea2.setLineWrap(true);
        jTextArea2.setWrapStyleWord(true);
        jTextArea2.setRows(3);
        jTextArea2.setMinimumSize(new java.awt.Dimension(164, 14));
        jTextArea2.setFont(jLabel9.getFont());
        jScrollPane2.setViewportView(jTextArea2);

/*      jTextArea3.setColumns(20);
        jTextArea3.setEditable(false);
        jTextArea3.setLineWrap(true);
        jTextArea3.setRows(5);
        jTextArea3.setMinimumSize(new java.awt.Dimension(164, 22)); (MODIF) */
//      jScrollPane3.setViewportView(jTextArea3);(MODIF)

        jLabel1.setFont(new java.awt.Font("SansSerif", 1, 24));
        jLabel1.setText("Servidor SMS 1.0");
        jLabel1.setHorizontalAlignment(SwingConstants.CENTER);
        jLabel1.setBorder(BorderFactory.createEtchedBorder());
 

        jButton4.setText("Enviar SMS");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

/*      jTextArea4.setColumns(20);
        jTextArea4.setRows(5);
        jTextArea4.setLineWrap(true);
        jTextArea4.setMinimumSize(new java.awt.Dimension(164, 22));
        jScrollPane4.setViewportView(jTextArea4);*/
        
//		jList2.setBorder(BorderFactory.createEtchedBorder());
        ModeloLista2 = new DefaultListModel();
        jList2.setModel(ModeloLista2);
        jScrollPane3.setViewportView(jList2);
        
        jLabel7.setHorizontalAlignment(SwingConstants.CENTER);
        jLabel7.setText("Reservas");
        jLabel7.setBorder(BorderFactory.createEtchedBorder());
        
        jLabel16.setHorizontalAlignment(SwingConstants.CENTER);
        jLabel16.setText("Consola");
        jLabel16.setBorder(BorderFactory.createEtchedBorder());
        
        jLabel9.setText("Fecha Conexión    ");
        jLabel9.setHorizontalAlignment(SwingConstants.RIGHT);
        jLabel11.setText("Estado Servidor    ");
        jLabel11.setHorizontalAlignment(SwingConstants.RIGHT);
        jLabel8.setText("Base De Datos    ");
        jLabel8.setHorizontalAlignment(SwingConstants.RIGHT);
        jLabel10.setText("Dispositivo GSM    ");
        jLabel10.setHorizontalAlignment(SwingConstants.RIGHT);
        jLabel12.setText("Potencia Señal    ");
        jLabel12.setHorizontalAlignment(SwingConstants.RIGHT);
        jLabel13.setText("Nivel De Batería    ");
        jLabel13.setHorizontalAlignment(SwingConstants.RIGHT);
        jLabel14.setText("SMS Recibidos    ");
        jLabel14.setHorizontalAlignment(SwingConstants.RIGHT);
        jLabel15.setText("SMS Enviados    ");
        jLabel15.setHorizontalAlignment(SwingConstants.RIGHT);
        jLabel17.setText("Puerto Conexión    ");
        jLabel17.setHorizontalAlignment(SwingConstants.RIGHT);
        jLabel18.setText("Velocidad (bps)    ");
        jLabel18.setHorizontalAlignment(SwingConstants.RIGHT);

          
        jTextField5.setHorizontalAlignment(SwingConstants.CENTER);
        jTextField5.setText("Desactivado");
        jTextField5.setFont(jLabel9.getFont());
        jTextField5.setEditable(false);
        
        jTextField6.setHorizontalAlignment(SwingConstants.CENTER);
        jTextField6.setText("Desconectada");
        jTextField6.setFont(jLabel9.getFont());
        jTextField6.setEditable(false);
        
        jTextField7.setHorizontalAlignment(SwingConstants.CENTER);
        jTextField7.setText("Desconectado");
        jTextField7.setFont(jLabel9.getFont());
        jTextField7.setEditable(false);
        
        jTextField8.setHorizontalAlignment(SwingConstants.CENTER);
        jTextField8.setText(" -");
        jTextField8.setFont(jLabel9.getFont());
        jTextField8.setEditable(false);
        
        jTextField9.setHorizontalAlignment(SwingConstants.CENTER);
        jTextField9.setText(" -");
        jTextField9.setFont(jLabel9.getFont());
        jTextField9.setEditable(false);
        
        jTextField10.setHorizontalAlignment(SwingConstants.CENTER);
        jTextField10.setText(" -");
        jTextField10.setFont(jLabel9.getFont());
        jTextField10.setEditable(false);
        
        jTextField11.setHorizontalAlignment(SwingConstants.CENTER);
        jTextField11.setText(" -");
        jTextField11.setFont(jLabel9.getFont());
        jTextField11.setEditable(false);
        
        jTextField12.setHorizontalAlignment(SwingConstants.CENTER);
        jTextField12.setText(" -");
        jTextField12.setFont(jLabel9.getFont());
        jTextField12.setEditable(false);
        
        jTextField13.setHorizontalAlignment(SwingConstants.CENTER);
        jTextField13.setText(" -");
        jTextField13.setFont(jLabel9.getFont());
        jTextField13.setEditable(false);
        
        jTextField14.setHorizontalAlignment(SwingConstants.CENTER);
        jTextField14.setText(" -");
        jTextField14.setFont(jLabel9.getFont());
        jTextField14.setEditable(false);

        jLabel19.setHorizontalAlignment(SwingConstants.CENTER);
        jLabel19.setText("Consumidor De SMS");
        jLabel19.setBorder(BorderFactory.createEtchedBorder());
        
        jLabel20.setHorizontalAlignment(SwingConstants.CENTER);
        jLabel20.setText("Productor De SMS");
        jLabel20.setBorder(BorderFactory.createEtchedBorder());
        
        jLabel21.setHorizontalAlignment(SwingConstants.CENTER);
        jLabel21.setText("Datos De Las Reservas");
        jLabel21.setBorder(BorderFactory.createEtchedBorder());
        
/*      jTextArea5.setColumns(20);
        jTextArea5.setRows(5);
        jTextArea5.setLineWrap(true);
        jTextArea5.setMinimumSize(new java.awt.Dimension(164, 22));
        jScrollPane5.setViewportView(jTextArea5); (MODIF)*/
        
        GroupLayout jPanel1Layout = new GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel9, GroupLayout.PREFERRED_SIZE, 110, GroupLayout.PREFERRED_SIZE)
                        .addComponent(jTextField9, GroupLayout.PREFERRED_SIZE, 110, GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel10, GroupLayout.PREFERRED_SIZE, 110, GroupLayout.PREFERRED_SIZE)
                        .addComponent(jTextField7, GroupLayout.PREFERRED_SIZE, 110, GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel11, GroupLayout.PREFERRED_SIZE, 110, GroupLayout.PREFERRED_SIZE)
                        .addComponent(jTextField5, GroupLayout.PREFERRED_SIZE, 110, GroupLayout.PREFERRED_SIZE)) 
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel8, GroupLayout.PREFERRED_SIZE, 110, GroupLayout.PREFERRED_SIZE)
                        .addComponent(jTextField6, GroupLayout.PREFERRED_SIZE, 110, GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()   
                        .addComponent(jLabel12, GroupLayout.PREFERRED_SIZE, 110, GroupLayout.PREFERRED_SIZE)
                        .addComponent(jTextField8, GroupLayout.PREFERRED_SIZE, 110, GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()                        
                		.addComponent(jLabel13, GroupLayout.PREFERRED_SIZE, 110, GroupLayout.PREFERRED_SIZE)
                		.addComponent(jTextField10, GroupLayout.PREFERRED_SIZE, 110, GroupLayout.PREFERRED_SIZE))                		
                	.addGroup(jPanel1Layout.createSequentialGroup()
                		.addComponent(jLabel14, GroupLayout.PREFERRED_SIZE, 110, GroupLayout.PREFERRED_SIZE)
                		.addComponent(jTextField11, GroupLayout.PREFERRED_SIZE, 110, GroupLayout.PREFERRED_SIZE))
                	.addGroup(jPanel1Layout.createSequentialGroup()                		
                		.addComponent(jLabel15, GroupLayout.PREFERRED_SIZE, 110, GroupLayout.PREFERRED_SIZE)
                		.addComponent(jTextField12, GroupLayout.PREFERRED_SIZE, 110, GroupLayout.PREFERRED_SIZE))
                	.addGroup(jPanel1Layout.createSequentialGroup()                		
                		.addComponent(jLabel17, GroupLayout.PREFERRED_SIZE, 110, GroupLayout.PREFERRED_SIZE)
                		.addComponent(jTextField13, GroupLayout.PREFERRED_SIZE, 110, GroupLayout.PREFERRED_SIZE))
                     .addGroup(jPanel1Layout.createSequentialGroup()                		
                		.addComponent(jLabel18, GroupLayout.PREFERRED_SIZE, 110, GroupLayout.PREFERRED_SIZE)
                		.addComponent(jTextField14, GroupLayout.PREFERRED_SIZE, 110, GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel1, GroupLayout.PREFERRED_SIZE, 221, GroupLayout.PREFERRED_SIZE))                  
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createSequentialGroup()
                	.addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                		.addGroup(jPanel1Layout.createSequentialGroup()
                			.addComponent(jButton4, GroupLayout.PREFERRED_SIZE, 107, GroupLayout.PREFERRED_SIZE)
                			.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jButton1, GroupLayout.PREFERRED_SIZE, 107, GroupLayout.PREFERRED_SIZE))
                        .addComponent(jLabel16,GroupLayout.DEFAULT_SIZE,221,Short.MAX_VALUE)
                		.addComponent(jTextField1, GroupLayout.PREFERRED_SIZE, 220, GroupLayout.PREFERRED_SIZE)
                		.addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, 221, GroupLayout.PREFERRED_SIZE)
                		.addComponent(jScrollPane2, GroupLayout.PREFERRED_SIZE, 221, GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
//                  .addComponent(jScrollPane5, GroupLayout.DEFAULT_SIZE, 219, Short.MAX_VALUE) (MODIF)
//                  .addComponent(jScrollPane3, GroupLayout.DEFAULT_SIZE, 219, Short.MAX_VALUE)) (MODIF)
                	.addComponent(jScrollPane3,GroupLayout.PREFERRED_SIZE,221,GroupLayout.PREFERRED_SIZE)
                	.addComponent(jLabel7,GroupLayout.DEFAULT_SIZE,221,Short.MAX_VALUE)
                	.addGroup(jPanel1Layout.createSequentialGroup()
                		.addComponent(jButton9,GroupLayout.PREFERRED_SIZE,107,GroupLayout.PREFERRED_SIZE)
                		.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                		.addComponent(jButton10,GroupLayout.PREFERRED_SIZE,107,GroupLayout.PREFERRED_SIZE)))          	
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                	.addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                    	.addGroup(GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                    	  	.addComponent(jLabel7)
                    	  	.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    	  	.addComponent(jScrollPane3,GroupLayout.PREFERRED_SIZE,240,GroupLayout.PREFERRED_SIZE)
                    	  	.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    	  	.addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    	  		.addComponent(jButton9)
                    	  		.addComponent(jButton10)))
                    	.addGroup(jPanel1Layout.createSequentialGroup()
                    		.addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                    			.addGroup(GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                    				.addComponent(jLabel1, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
                    				.addGap(10)
                    				.addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
                                		.addComponent(jTextField9)
                                		.addComponent(jLabel9, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE))
                                	.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)	
                                	.addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
                            			.addComponent(jTextField5)
                            			.addComponent(jLabel11, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE))
                    				.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        			.addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
                        				.addComponent(jTextField7)
                                		.addComponent(jLabel10, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE))
                        			.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        			.addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
                        				.addComponent(jLabel8, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
                        				.addComponent(jTextField6, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE))
                        			.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        			.addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(jTextField13)
                                        .addComponent(jLabel17, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE))                    			
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(jTextField14)
                                        .addComponent(jLabel18, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE))                    			
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                               		.addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
                                    	.addComponent(jTextField8)
                                    	.addComponent(jLabel12, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE))
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)                        				
                                    .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
                                		.addComponent(jTextField10)
                                		.addComponent(jLabel13, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE))
                                	.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                	.addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(jTextField11)
                                        .addComponent(jLabel14, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE))                      			
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(jTextField12)
                                        .addComponent(jLabel15, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE))                    			
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED))
                        		.addGroup(jPanel1Layout.createSequentialGroup()                        			
                                    .addComponent(jLabel16)
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jScrollPane2, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        			.addComponent(jTextField1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        			.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                	.addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, 115, Short.MAX_VALUE)
                                	.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            				.addComponent(jButton1)
        		             				.addComponent(jButton4))))))                                	
                .addContainerGap())
        );

//      jTextArea6.setColumns(20);
//      jTextArea6.setRows(5);
        jTextArea6.setEditable(false);
        jTextArea6.setLineWrap(true);
//      System.out.println("AVER: " + jTextArea6.getLineStartOffset(1));
//      jTextArea6.setMargin(jTextArea6.getInsets());
        jTextArea6.setWrapStyleWord(true);
        jTextArea6.setFont(jLabel9.getFont());
        jScrollPane6.setViewportView(jTextArea6);
                
//      jTextArea7.setColumns(20);
//      jTextArea7.setRows(5);
        jTextArea7.setEditable(false);
        jTextArea7.setLineWrap(true);
        jTextArea7.setWrapStyleWord(true);
        jTextArea7.setFont(jLabel9.getFont());
        jScrollPane7.setViewportView(jTextArea7);
     
//      jTextArea9.setColumns(20);
//      jTextArea9.setRows(5);
        jTextArea9.setEditable(false);
        jTextArea9.setLineWrap(true);
        jTextArea9.setWrapStyleWord(true);        
        jTextArea9.setFont(jLabel9.getFont());
        jScrollPane9.setViewportView(jTextArea9);

        GroupLayout jPanel2Layout = new GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
        		.addGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
        			.addComponent(jScrollPane6, GroupLayout.PREFERRED_SIZE, 319, GroupLayout.PREFERRED_SIZE)	//314 era antes (MODIF)
        			.addComponent(jLabel19, GroupLayout.PREFERRED_SIZE, 319, GroupLayout.PREFERRED_SIZE))	//314 era antes (MODIF)
        		.addGap(6)	
//         		.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
          		.addGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
           			.addComponent(jScrollPane7, GroupLayout.PREFERRED_SIZE, 321, GroupLayout.PREFERRED_SIZE)
           			.addComponent(jLabel20, GroupLayout.PREFERRED_SIZE, 321, GroupLayout.PREFERRED_SIZE))
        		.addGap(6)	
//           		.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
           		.addGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
           			.addComponent(jScrollPane9, GroupLayout.PREFERRED_SIZE, 319, GroupLayout.PREFERRED_SIZE)
           			.addComponent(jLabel21, GroupLayout.PREFERRED_SIZE, 319, GroupLayout.PREFERRED_SIZE)))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
            	.addComponent(jLabel19)
            	.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
            	.addComponent(jScrollPane6, GroupLayout.DEFAULT_SIZE, 324, GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel2Layout.createSequentialGroup()
            	.addComponent(jLabel20)
            	.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)            	
            	.addComponent(jScrollPane7, GroupLayout.DEFAULT_SIZE, 324, GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel2Layout.createSequentialGroup()
            	.addComponent(jLabel21)
            	.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)              	
            	.addComponent(jScrollPane9, GroupLayout.DEFAULT_SIZE, 324, GroupLayout.PREFERRED_SIZE))
        );

        jPanel3.setBorder(BorderFactory.createEtchedBorder());
//      jPanel3.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(),"Contactos"));
//      jTextField2.setText("Nombre");

//      jTextField3.setText("Celular Sin 15");

        jLabel5.setHorizontalAlignment(SwingConstants.CENTER);
        jLabel5.setText("Lista De Clientes");
        jLabel5.setBorder(BorderFactory.createEtchedBorder());
        
//      jLabel6.setText("Nro Celular");

//      jTextField4.setText("Area");

        /*jList1.setModel(new AbstractListModel() {
			private static final long serialVersionUID = 1L;
			public String[] strings = {"Agenda De Contactos"};
			public void FijoDato(String[] strings) { this.strings = strings; };
			public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
    	});*/   
        SampleModel = new DefaultListModel();
        //String[] entries = {"Agenda De Contactos"};
        	//SampleModel.addElement(entries[0]);
            jList1.setModel(SampleModel);
        
        jScrollPane8.setViewportView(jList1);

//      jButton5.setText("Agregar");
/*      jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });*/

//      jButton6.setText("Remover");
/*      jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });*/
        
        jButton7.setText("Eliminar");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jButton8.setText("Cargar");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt); 
            }
        });
        
        jButton9.setText("Ver Datos");
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt); 
            }
        });
        
        jButton10.setText("Finalizar");
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt); 
            }
        });
        
//      jButton11.setText("");

//		Opciones Del Panel 3        
        
        GroupLayout jPanel3Layout = new GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane8, GroupLayout.Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 221, GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5, GroupLayout.DEFAULT_SIZE,221, GroupLayout.PREFERRED_SIZE)
                    .addGroup(GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                    	.addComponent(jButton8,GroupLayout.PREFERRED_SIZE,107, GroupLayout.PREFERRED_SIZE)
                    	.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    	.addComponent(jButton7,GroupLayout.PREFERRED_SIZE,107, GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane8,GroupLayout.PREFERRED_SIZE,240,GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)                	
                	.addComponent(jButton7)
                	.addComponent(jButton8))
                .addContainerGap())
        );
        
//		Opciones De Menu "Menu Principal"
        
        jMenu1.setText("Servidor");
        jMenuItem1.setText("Activar");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });

        jMenu1.add(jMenuItem1);
//      jMenuItem1.getAccessibleContext().setAccessibleName("Encender Servidor");       

        jMenuItem6.setText("Desactivar");
        jMenuItem6.setEnabled(false);
        jMenuItem6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem6ActionPerformed(evt);
            }
        });
        
        jMenu1.add(jMenuItem6);

        jMenu1.add(jSeparator1);

        jMenuItem3.setText("Salir");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });

        jMenu1.add(jMenuItem3);

        jMenuBar1.add(jMenu1);
        
//		Opciones De Menu "Configuración"        

        jMenu2.setText("Configuraci\u00f3n");
        jMenuItem12.setText("Dispositivo GSM");
        jMenuItem12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem12ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem12);
       
        jMenuItem13.setText("Archivos XML");
        jMenuItem13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem13ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem13);
       
        jMenuItem14.setText("Tiempos");
        jMenuItem14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem14ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem14);
        
        jMenuItem15.setText("Archivo De Log");
        jMenuItem15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem15ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem15);
        
        jMenuBar1.add(jMenu2);

// 		Opciones de Menu "Base De Datos"
        
        jMenu4.setText("Base De Datos");
        jMenuItem4.setText("Conectar");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuItem4);
        
        jMenuItem2.setText("Desconectar");
        jMenuItem2.setEnabled(false);
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuItem2);
               
        jMenuItem16.setText("Estado De Vuelos");
        jMenuItem16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem16ActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuItem16);

        jMenu5.setText("Agregar Datos");
        jMenuItem7.setText("Aeropuertos");
        jMenuItem7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem7ActionPerformed(evt);
            }
        });       
        jMenu5.add(jMenuItem7);
        
        jMenuItem8.setText("Aviones");
        jMenuItem8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem8ActionPerformed(evt);
            }
        });
        jMenu5.add(jMenuItem8);
        
        jMenuItem9.setText("Clientes");
        jMenuItem9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem9ActionPerformed(evt);
            }
        });
        jMenu5.add(jMenuItem9);
        
        jMenuItem10.setText("Destinos");
        jMenuItem10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem10ActionPerformed(evt);
            }
        });
        jMenu5.add(jMenuItem10);
        
        jMenuItem11.setText("Vuelos");
        jMenuItem11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem11ActionPerformed(evt);
            }
        });
        jMenu5.add(jMenuItem11);
        
        jMenu4.add(jMenu5);
               
        jMenuBar1.add(jMenu4);
        
//		Opciones De Menu "Acerca De"              

        jMenu3.setText("Acerca De");
        jMenuItem5.setText("Mi Trabajo Final De Carrera");
        jMenuItem5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem5ActionPerformed(evt);
            }
        });

        jMenu3.add(jMenuItem5);

        jMenuBar1.add(jMenu3);

//		Opciones de Barra Menu
        
        setJMenuBar(jMenuBar1);
        
//		Opciones de Agregado De Paneles 1,2 y 3 A La IG        

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                        .addComponent(jPanel2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jPanel1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jPanel3, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel3, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 11, Short.MAX_VALUE)
                .addComponent(jPanel2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        pack();                     
}	// </editor-fold>
    
//  Métodos que manejan eventos

//  Indicador De Caracteres Faltantes Para SMS A Enviar
    
    private void jTextArea1ActionPerformed(java.awt.event.KeyEvent event) {
  		String Texto = jTextArea1.getText();
   		int Largo = Texto.length();
   		Caracteres = 160 - Largo;
   		jTextField1.setText(" Mensaje - Caracteres Restantes: " + Caracteres);
    }
    
//	Boton "Borrar"
    
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {
    	jTextArea2.setText("");
    	Caracteres = 160;
		jTextField1.setText(" Mensaje - Caracteres Restantes: " + Caracteres);
    	jTextArea1.setText("");
    }

//  Boton "Enviar SMS"    
    
    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {                                               
    	jTextArea2.setText("");
    	if (!Inicio.ListaContactos.isEmpty()) { Inicio.EnviarMensaje(SampleModel); }
    	else { jTextArea2.setText("No hay clientes cargados en la lista de contactos para enviar mensajes SMS."); }
    }                                              

//  Boton "Eliminar" (Contactos)
    
    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {  
    	jTextArea2.setText("");
		Inicio.MostrarReserva();
    	if (!Inicio.ListaContactos.isEmpty()) {
    		 Inicio.BorrarContacto(SampleModel);
    	}
    	else { jTextArea2.setText("No hay clientes cargados en la lista de contactos."); }
    }

//  Boton "Cargar" (Contactos) 
    
    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {
    	jTextArea2.setText("");
    	if (jTextField6.getText().compareTo("Desconectada") == 0) { jMenuItem4ActionPerformed(evt); }		// Activo BD Si Está Desconectada
    	if (jTextField6.getText().compareTo("Desconectada") != 0) {
    		if (Ventanas.CargarClientes == null) {
    			Ventanas.CargarClientes = Ventanas.new VentanaCargarClientes();   	
    			System.out.println("Ventana Aviones Size: X:" + Ventanas.CargarClientes.getWidth() + " Y:" + Ventanas.CargarClientes.getHeight());        	
    		}
    		else { 
    			Ventanas.CargarClientes.setLocation(getBounds().x + 328, getBounds().y + 290);
    			Ventanas.CargarClientes.CargarDatos();
    		}
    		Ventanas.CargarClientes.setVisible(true);
    	}
    	else { jTextArea2.setText("Para cargar los contactos primero debe conectarse a la base de datos."); }
    }

//  Boton "Mostrar (En Pantalla)" (Reservas) 
    
    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {
    	jTextArea2.setText("");
    	if (jList2.getSelectedIndex() >= 0) {
    		if (jTextField6.getText().compareTo("Desconectada") == 0) { jMenuItem4ActionPerformed(evt); }		// Activo BD Si Está Desconectada
    		if (jTextField6.getText().compareTo("Desconectada") != 0) { Inicio.ImprimirReserva(jList2.getSelectedIndices()); }
    		else { jTextArea2.setText("Para visualizar los datos de las reservas primero debe conectarse a la base de datos."); }
    	}
    	else { jTextArea2.setText("Se debe seleccionar una o varias reservas de la lista de reservas para poder visualizar todos los datos correspondientes."); } // Modif Maurix
//  	else { jTextArea2.setText("No se encuentran ciudades\ndisponibles para cargar los datos de\nun aeropuerto. Agregue un nuevo\ndestino en la base de datos y vuelva a\nintentar."); }
    }
    
//  Boton "Finalizar" (Reservas) 
    
    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {
    	String NombreCliente, Cel, Texto;
    	int NroReserva, PrecioFinal, Flag;
    	Flag = 0;
    	jTextArea2.setText("");
    	if (jList2.getSelectedIndex() >= 0) {
        	if (jTextField6.getText().compareTo("Desconectada") == 0) { jMenuItem4ActionPerformed(evt); }					// Activo BD Si Está Desconectada
        	if (jTextField6.getText().compareTo("Desconectada") != 0) {
        		if (Ventanas.Ventas == null) {
        			Ventanas.Ventas = Ventanas.new VentanaVentas();
        			System.out.println("Ventana Ventas Size: X:" + Ventanas.Ventas.getWidth() + " Y:" + Ventanas.Ventas.getHeight());
        		}
        		else { Ventanas.Ventas.setLocation(getBounds().x + 318, getBounds().y + 220); }
        		NroReserva = Inicio.ListaReservas.get(jList2.getSelectedIndex());
        		Cel = Inicio.BD.BuscoCelReserva(NroReserva);
        		PrecioFinal = Inicio.BD.BuscoPrecioFinal(NroReserva);
        		if (Inicio.BD.EsCliente(NroReserva)) { NombreCliente = Inicio.BD.BuscoNombreCliente(Cel); }
        		else {  		
        			jMenuItem9ActionPerformed(evt);
        			NombreCliente = Inicio.BD.BuscoNombreCliente(Cel);
        			Flag = 1;
        		}
        		if (Flag == 1) { if (Ventanas.Clientes.BotonPresionado == 1) { Flag = 2; } }
        		if (Flag == 0 || Flag == 2) {
        			if (NombreCliente.compareTo("Inexistente") != 0) {
        				Inicio.BD.AltaClienteReserva(Cel);																// Cambio El "N" Por "S" En El Campo Cliente De La Tabla Reservas
        				Ventanas.Ventas.jTextField1.setText(NombreCliente);
        				Ventanas.Ventas.jTextField2.setText(Cel);
        				Ventanas.Ventas.jTextField3.setText(Integer.toString(NroReserva));
        				Ventanas.Ventas.jTextField4.setText(Integer.toString(PrecioFinal));
        				Ventanas.Ventas.setVisible(true);    		
        			}
        			else {
        				Texto = jTextArea2.getText();
        				jTextArea2.setText(Texto + " pero el número de celular ingresado no coincide con el de la reserva.");
        			}
        		}
            	else { jTextArea2.setText("Para dar por finalizada una reserva primero deberá ingresar los datos del cliente que no se encuentra registrado."); }
        	}							   	
        	else { jTextArea2.setText("Para dar por finalizada una reserva primero debe conectarse a la base de datos."); }
    	}
		else { jTextArea2.setText("Se debe seleccionar una reserva de la lista de reservas para poder generar la finalización del mismo."); }
    }

//  Menu "Base De Datos" - Opcion "Conectar" 
    
    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {                                           
    	jTextArea2.setText("");
    	if (Ventanas.AccesoBD == null) {
        	Ventanas.AccesoBD = Ventanas.new VentanaAccesoBD();   	
        	Ventanas.AccesoBD.setVisible(true);
    		System.out.println("Ventana Aviones Size: X:" + Ventanas.AccesoBD.getWidth() + " Y:" + Ventanas.AccesoBD.getHeight());
       	}
    	else {
    		Ventanas.AccesoBD.setLocation(getBounds().x + 332, getBounds().y + 295);
    		Ventanas.AccesoBD.setVisible(true);    		
    	} 	      
    }

//  Menu "Acerca De" - Opción "Mi T.F.C."    
    
    private void jMenuItem5ActionPerformed(java.awt.event.ActionEvent evt) {                                           
    	jTextArea2.setText("");
    	if (Ventanas.AcercaDe == null) {
    		Ventanas.AcercaDe = Ventanas.new VentanaAcercaDe();   	
    		Ventanas.AcercaDe.setVisible(true);
    		System.out.println("Ventana Aviones Size: X:" + Ventanas.AcercaDe.getWidth() + " Y:" + Ventanas.AcercaDe.getHeight());
       	}
    	else {
    		Ventanas.AcercaDe.setLocation(getBounds().x + 300, getBounds().y + 276);
    		Ventanas.AcercaDe.setVisible(true);    		
    	}
    }

//  Menu "Base De Datos" - Opción "Aeropuertos"
    
    private void jMenuItem7ActionPerformed(java.awt.event.ActionEvent evt) {
		LinkedList<Object> Ciudades = new LinkedList<Object>();
    	jTextArea2.setText("");
    	if (jTextField6.getText().compareTo("Desconectada") == 0) {jMenuItem4ActionPerformed(evt);}		// Activo BD Si Está Desconectada
    	if (jTextField6.getText().compareTo("Desconectada") != 0) {
    		Ciudades = Inicio.BD.BuscoCiudadesSinAeropuertos();
    		if (!Ciudades.isEmpty()) {
    			if (Ventanas.Aeropuertos == null) {
    				Ventanas.Aeropuertos = Ventanas.new VentanaAeropuertos();   	
    				System.out.println("Ventana Aviones Size: X:" + Ventanas.Aeropuertos.getWidth() + " Y:" + Ventanas.Aeropuertos.getHeight());
    			}
    			else {
    				Ventanas.Aeropuertos.setLocation(getBounds().x + 318, getBounds().y + 239);
        			Ventanas.Aeropuertos.CargarDatos();
    			}
			Ventanas.Aeropuertos.setVisible(true);
    		}
    		else { jTextArea2.setText("No se encuentran ciudades disponibles para cargar los datos de un aeropuerto. Agregue un nuevo destino en la base de datos y vuelva a intentar."); }
    	}
    	else { jTextArea2.setText("Para cargar datos primero debe conectarse a la base de datos."); }
    }    
    
//  Menu "Menu Principal" - Opción "Salir"
    
    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {                                           
		System.out.println ("Finaliza Aplicación");
		Inicio.CierroLog();
		System.exit(0);
    }                                          

//  Menu "Base De Datos" - Opción "Desconectar"
    
    public void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {                                           
    	jTextArea2.setText("");
    	Inicio.DesconectarBD();
    }                

//  Menu "Menu Principal" - Opción "Desactivar"    
    
    public void jMenuItem6ActionPerformed(java.awt.event.ActionEvent evt) {                                           
    	jTextArea2.setText("");
//		FijoTexto2("Desactivo Servidor");
//    	jMenuItem1.setEnabled(true);																	// Desactivo Opción "Desconectar" y Activo "Conectar"
    	jMenuItem6.setEnabled(false);
    	jMenuItem2.setEnabled(true);																	// Activo Opción "Desconectar" del Menu Base De Datos
        jTextField5.setText("Desactivando");													
    	Inicio.Desconexion();
    }          

//  Menu "Menu Principal" - Opción "Activar"    
    
    public void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {                                          
        
    	String Fecha;
    	jTextArea2.setText("");
    	jMenuItem6.setEnabled(true);
    	jMenuItem1.setEnabled(false);
    	if (jTextField6.getText().compareTo("Desconectada") == 0) {jMenuItem4ActionPerformed(evt);}		// Activo BD Si Está Desconectada
    	if (jTextField6.getText().compareTo("Desconectada") != 0) {
    		jMenuItem2.setEnabled(false);
    		jTextField5.setText("Activado");
    		Fecha = Inicio.CrearFecha(1,null);
    		jTextField9.setText(Fecha);
    		if (jTextField11.getText().compareTo(" -") == 0 ) { jTextField11.setText("0"); }
    		if (jTextField12.getText().compareTo(" -") == 0 ) { jTextField12.setText("0"); }
    		Inicio.EscriboLog("Servidor Activado");
    		Inicio.Conexion();
    	}
    	else { jTextArea2.setText("Para iniciar el servidor de SMS primero debe conectarse a la base de datos."); }
    	jMenuItem6.setEnabled(false);
    	jMenuItem1.setEnabled(true);
    }

//  Menu "Configuración" - Opción "Dispositivo GSM"  
    
    public void jMenuItem12ActionPerformed(java.awt.event.ActionEvent evt) {
    	jTextArea2.setText("");
    	if (Ventanas.OpcionesGSM == null) {
    		Ventanas.OpcionesGSM = Ventanas.new VentanaOpcionesGSM();
    		Ventanas.OpcionesGSM.setVisible(true);
			System.out.println("Ventana Aviones Size: X:" + Ventanas.OpcionesGSM.getWidth() + " Y:" + Ventanas.OpcionesGSM.getHeight());
    	}
    	else {
    		Ventanas.OpcionesGSM.setLocation(getBounds().x + 335, getBounds().y + 295);
    		Ventanas.OpcionesGSM.setVisible(true);    		
    	}	
    }

//  Menu "Configuración" - Opción "Archivos XML"
    
    public void jMenuItem13ActionPerformed(java.awt.event.ActionEvent evt) {
    	jTextArea2.setText("");
    	if (Ventanas.ArchivosXML == null) {
    		Ventanas.ArchivosXML = Ventanas.new VentanaArchivosXML();   	
    		Ventanas.ArchivosXML.setVisible(true);
			System.out.println("Ventana Aviones Size: X:" + Ventanas.ArchivosXML.getWidth() + " Y:" + Ventanas.ArchivosXML.getHeight());
    	}
    	else {
    		Ventanas.ArchivosXML.setLocation(getBounds().x + 335, getBounds().y + 292);
    		Ventanas.ArchivosXML.setVisible(true);    		
    	}	
    }

//  Menu "Configuración" - Opción "Tiempos"  
    
    public void jMenuItem14ActionPerformed(java.awt.event.ActionEvent evt) {
    	jTextArea2.setText("");
    	if (Ventanas.Tiempos == null) {
        	Ventanas.Tiempos = Ventanas.new VentanaTiempos();   	
        	Ventanas.Tiempos.setVisible(true);
			System.out.println("Ventana Aviones Size: X:" + Ventanas.Tiempos.getWidth() + " Y:" + Ventanas.Tiempos.getHeight());        	
    	}
    	else {
    		Ventanas.Tiempos.setLocation(getBounds().x + 281, getBounds().y + 278);
    		Ventanas.Tiempos.setVisible(true);    		
    	}	  
    }

//  Menu "Configuración" - Opción "Archivo De Log"  
    
    public void jMenuItem15ActionPerformed(java.awt.event.ActionEvent evt) {
    	jTextArea2.setText("");
    	if (Ventanas.ArchivoLog == null) {
        	Ventanas.ArchivoLog = Ventanas.new VentanaArchivoLog();   	
        	Ventanas.ArchivoLog.setVisible(true);
        	System.out.println("Ventana Aviones Size: X:" + Ventanas.ArchivoLog.getWidth() + " Y:" + Ventanas.ArchivoLog.getHeight());
    	}
    	else {
    		Ventanas.ArchivoLog.setLocation(getBounds().x + 331, getBounds().y + 292);
    		Ventanas.ArchivoLog.setVisible(true);    		
    	}	
    }
    
//  Menu "Base De Datos" - Opción "Aviones"   
    
    public void jMenuItem8ActionPerformed(java.awt.event.ActionEvent evt) {                                          
    	jTextArea2.setText("");
    	if (jTextField6.getText().compareTo("Desconectada") == 0) {jMenuItem4ActionPerformed(evt);}		// Activo BD Si Está Desconectada
    	if (jTextField6.getText().compareTo("Desconectada") != 0) {
    		if (Ventanas.Aviones == null) {
    			Ventanas.Aviones = Ventanas.new VentanaAviones();   	
    			Ventanas.Aviones.setVisible(true);
    			System.out.println("Ventana Aviones Size: X:" + Ventanas.Aviones.getWidth() + " Y:" + Ventanas.Aviones.getHeight());    			
    		}
    		else {
    			Ventanas.Aviones.setLocation(getBounds().x + 311, getBounds().y + 275);
    			Ventanas.Aviones.setVisible(true);    		
    		}
    	}
    	else { jTextArea2.setText("Para cargar datos primero debe conectarse a la base de datos."); }
    } 
    
//  Menu "Base De Datos" - Opción "Clientes"
    
    public void jMenuItem9ActionPerformed(java.awt.event.ActionEvent evt) { 				  
    	jTextArea2.setText("");
    	if (jTextField6.getText().compareTo("Desconectada") == 0) {jMenuItem4ActionPerformed(evt);}		// Activo BD Si Está Desconectada
    	if (jTextField6.getText().compareTo("Desconectada") != 0) {
    		if (Ventanas.Clientes == null) {
    			Ventanas.Clientes = Ventanas.new VentanaClientes();
    			System.out.println("Ventana Aviones Size: X:" + Ventanas.Clientes.getWidth() + " Y:" + Ventanas.Clientes.getHeight());        	
    		}
    		else { 
    			Ventanas.Clientes.setLocation(getBounds().x + 298, getBounds().y + 167);
				Ventanas.Clientes.CargarDatos();
    		}
//  		if (Flag == 1) { Ventanas.Clientes.jTextField6.setText(Cel); }
    		Ventanas.Clientes.setVisible(true);
    	}
    	else { jTextArea2.setText("Para cargar datos primero debe conectarse a la base de datos."); }
    } 
    
//  Menu "Base De Datos" - Opción "Destinos"
    
    public void jMenuItem10ActionPerformed(java.awt.event.ActionEvent evt) {
    	jTextArea2.setText("");
    	if (jTextField6.getText().compareTo("Desconectada") == 0) {jMenuItem4ActionPerformed(evt);}		// Activo BD Si Está Desconectada
    	if (jTextField6.getText().compareTo("Desconectada") != 0) {
    		if (Ventanas.Destinos == null) {
    			Ventanas.Destinos = Ventanas.new VentanaDestinos();   	
    			Ventanas.Destinos.setVisible(true);
  			System.out.println("Ventana Aviones Size: X:" + Ventanas.Destinos.getWidth() + " Y:" + Ventanas.Destinos.getHeight());
    		}
    		else {
    			Ventanas.Destinos.setLocation(getBounds().x + 318, getBounds().y + 257);
    			Ventanas.Destinos.setVisible(true);    		
    		}
    	}
    	else { jTextArea2.setText("Para cargar datos primero debe conectarse a la base de datos."); }
    }

//  Menu "Base De Datos" - Opción "Vuelos"    
    
    public void jMenuItem11ActionPerformed(java.awt.event.ActionEvent evt) {
    	jTextArea2.setText("");
    	if (jTextField6.getText().compareTo("Desconectada") == 0) {jMenuItem4ActionPerformed(evt);}		// Activo BD Si Está Desconectada
    	if (jTextField6.getText().compareTo("Desconectada") != 0) {
    		if (Ventanas.Vuelos == null) {
    			Ventanas.Vuelos = Ventanas.new VentanaVuelos();   	   		
    			System.out.println("Ventana Aviones Size: X:" + Ventanas.Vuelos.getWidth() + " Y:" + Ventanas.Vuelos.getHeight());
    		}
    		else {
    			Ventanas.Vuelos.setLocation(getBounds().x + 159, getBounds().y + 239);
				Ventanas.Vuelos.CargarDatos();
    		}
			Ventanas.Vuelos.setVisible(true);   
    	}
    	else { jTextArea2.setText("Para cargar datos primero debe conectarse a la base de datos."); }
    }

//  Menu "Base De Datos" - Opción "Estados De Vuelos"  
    
    public void jMenuItem16ActionPerformed(java.awt.event.ActionEvent evt) {
    	jTextArea2.setText("");
    	if (jTextField6.getText().compareTo("Desconectada") == 0) {jMenuItem4ActionPerformed(evt);}		// Activo BD Si Está Desconectada
    	if (jTextField6.getText().compareTo("Desconectada") != 0) {
    		if (Ventanas.ModificarVuelos == null) {
    			Ventanas.ModificarVuelos = Ventanas.new VentanaModificarVuelos();   	
    			System.out.println("Ventana Aviones Size: X:" + Ventanas.ModificarVuelos.getWidth() + " Y:" + Ventanas.ModificarVuelos.getHeight());           	
    		}
    		else { 
    			Ventanas.ModificarVuelos.setLocation(getBounds().x + 318, getBounds().y + 275);
    			Ventanas.ModificarVuelos.CargarDatos();
    		}
    		Ventanas.ModificarVuelos.setVisible(true); 
    	}
    	else { jTextArea2.setText("Para modificar el estado de un vuelo primero debe conectarse a la base de datos."); }
    } 
    
//	Metodos Extras
      
/*  public void AjustoSrv(CService Srv){
    	Srv1 = Srv;
    }*/
    
/*  public void FijoTexto2(String Texto){
    	String Temp = jTextArea2.getText();
    	if (Temp.length() > 0) {Temp = Temp + "\n";}
    	Temp = Temp + Texto;
    	jTextArea2.setText(Temp);
    }*/
   
    public void CerrarAplicacion(java.awt.event.WindowEvent event) {
		System.out.println ("Finaliza Aplicación");
		Inicio.CierroLog();
		System.exit(0);
    }
         
}

