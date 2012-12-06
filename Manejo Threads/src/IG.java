import java.io.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import javax.swing.*;
import javax.swing.event.*;

public class IG extends JFrame {

	public static IG Ventanas;
	//public final JProgressBar Barra;
	private JMenuBar menuBar;
	private JMenu fileMenu,aboutMenu;
	private JMenuItem mniConnect, mniDisconnect, mniExit;
	private JMenuItem mniAbout;
	private JLabel lblManuf,lblManuf2,lblManuf3,PanelModemModem,PanelModemSenal,PanelModemBateria;
	private JTextField txtManuf;
	private JLabel lblInFrom,sms1label,sms2label,sms3label;
	private JTextArea txtInText,sms1text,sms2text,sms3text;
	private JProgressBar Barra1, Barra2;
	private JLabel PanelEstadoConectado, PanelEstadoBD, PanelEstadoProceso, PanelEstadoEnv, PanelEstadoWeb;
	public static Color white;
	public static Graphics Ovalo;

	public IG() {
		setTitle(CConstants.MAIN_WINDOW_TITLE);
		setSize(1100,850);
		setLocation(25,25);
		getContentPane().setLayout(new GridBagLayout());
		menuBar = new JMenuBar();
		fileMenu = new JMenu(CConstants.MENU_FILE_MAIN);
		mniConnect = new JMenuItem(CConstants.MENU_FILE_OPTION_01);
		mniConnect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				System.out.println("Presiono Boton Abrir");
				}
			});
		mniDisconnect = new JMenuItem(CConstants.MENU_FILE_OPTION_02);
		mniDisconnect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				System.out.println("Presiono Boton Guardar");
				}
			});
		mniExit = new JMenuItem(CConstants.MENU_FILE_OPTION_99);
		mniExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				System.exit(0);
				}
			});
		fileMenu.add(mniConnect);
		fileMenu.add(mniDisconnect);
		fileMenu.addSeparator();
		fileMenu.add(mniExit);
		menuBar.add(fileMenu);
		aboutMenu = new JMenu(CConstants.MENU_ABOUT_MAIN);
		mniAbout = new JMenuItem(CConstants.MENU_ABOUT_OPTION_01);
		mniAbout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				System.out.println("Presiono Boton Acerca de Maurix Corp");
				}	
			});
		aboutMenu.add(mniAbout);
		menuBar.add(aboutMenu);
		setJMenuBar(menuBar);
		GridBagConstraints gbc = new GridBagConstraints();
	    gbc.anchor = GridBagConstraints.NORTHWEST;
		//Configuro Etiqueta "Fabricante:"
	    //gbc.weightx = 5;
		//gbc.weighty = 5;
		//gbc.fill = GridBagConstraints.BOTH;
	    gbc.anchor = GridBagConstraints.NORTHWEST;
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.weightx= 1;
		gbc.weighty= 1;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		JPanel statusPanel = new JPanel();
		statusPanel.setLayout(new GridBagLayout());
		lblManuf = new JLabel("Servidor SMS", JLabel.LEFT);
		statusPanel.add(lblManuf, gbc);
		lblManuf2 = new JLabel("Maurix Corp Inc.", JLabel.LEFT);
		gbc.gridx = 0;
		gbc.gridy = 1;
		//gbc.insets.left = 10; gbc.insets.top = 10;
		gbc.insets.right = 250;
		statusPanel.add(lblManuf2, gbc);				
		//Configuro Caja Texto de "Fabricante:"
	    //gbc.weightx = 5;
		//gbc.weighty = 5;
		//gbc.fill = GridBagConstraints.BOTH;
		//gbc.gridx = 0;
		//gbc.gridy = 2;
		//gbc.gridwidth = 10;
		//gbc.gridheight = 1;
		//statusPanel.add(txtManuf, gbc);
		statusPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder()/*,CConstants.BORDER_MOBILE_INFORMATION*/));
		//Configuro statusPanel
	    //gbc.anchor = GridBagConstraints.NORTHWEST;
	    //gbc.weightx = 5;
		//gbc.weighty = 5;
		//gbc.fill = GridBagConstraints.BOTH;
		//gbc.gridx = 0;
		//gbc.gridy = 0;
		//gbc.gridwidth = 10;
		//gbc.gridheight = 10;
		//gbc.anchor = GridBagConstraints.SOUTHWEST;
		//gbc.weightx = 100;
		//gbc.weighty = 100;
	    gbc.anchor = GridBagConstraints.NORTHWEST;
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.weightx= 1.0;
		gbc.weighty= 1.0;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		gbc.insets.left = 10; gbc.insets.top = 10;
		gbc.insets.right = 10;
		getContentPane().add(statusPanel, gbc);
		
		//Modem
		
		JPanel PanelModem = new JPanel();
		PanelModem.setLayout(new GridBagLayout());
		PanelModemModem = new JLabel("Modem Utilizado", JLabel.LEFT);
	    gbc.anchor = GridBagConstraints.NORTHWEST;
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.weightx= 1.0;
		gbc.weighty= 1.0;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		gbc.insets.left = 10; gbc.insets.top = 10;
		//gbc.insets.right = 10;
		gbc.insets.bottom = 5;
		PanelModem.add(PanelModemModem, gbc);
		
		PanelModemSenal = new JLabel("Potencia Señal", JLabel.LEFT);
	    gbc.anchor = GridBagConstraints.NORTHWEST;
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.weightx= 1.0;
		gbc.weighty= 1.0;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		//gbc.insets.left = 10;
		gbc.insets.top = 10;
		gbc.insets.bottom = 5;
		//gbc.insets.right = 10;	
		PanelModem.add(PanelModemSenal, gbc);
		
		PanelModemBateria = new JLabel("Bateria Celular", JLabel.LEFT);
	    gbc.anchor = GridBagConstraints.NORTHWEST;
		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.weightx= 1.0;
		gbc.weighty= 1.0;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		//gbc.insets.left = 10; 
		gbc.insets.top = 10;
		gbc.insets.bottom = 15;
		//gbc.insets.right = 10;	
		PanelModem.add(PanelModemBateria, gbc);
		
		txtManuf = new JTextField("",15);
		txtManuf.setHorizontalAlignment(JTextField.CENTER);
		//txtManuf.setFont();
		//RED = txtManuf.getBackground();
		//RED = RED.decode("RED"); 
        //white = Color.BLUE;
		white = new Color(255,255,255);
        //white = Color.BLUE;
		txtManuf.setBackground(white);
		txtManuf.setText("HOLA");
		//txtManuf.setSize(50,150);
		txtManuf.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder()));
		txtManuf.setEditable(false);
	    gbc.anchor = GridBagConstraints.NORTHWEST;
		gbc.gridx = 1;
		gbc.gridy = 0;
		//gbc.weightx= 1.0;
		//gbc.weighty= 1.0;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		//gbc.insets.left = 10;
		gbc.insets.top = 3;
		gbc.insets.bottom = 3;
		//gbc.insets.right = 10;	
		PanelModem.add(txtManuf, gbc);
		
		Barra1 = new JProgressBar();
		Barra1.setValue(50);
		Barra1.setStringPainted(true);
		gbc.anchor = GridBagConstraints.NORTHWEST;
		gbc.gridx = 1;
		gbc.gridy = 1;
		//gbc.weightx= 1.0;
		//gbc.fill = GridBagConstraints.EAST;
		//gbc.weighty= 1.0;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		//gbc.insets.left = 10;
		gbc.insets.top = 8;
		//gbc.insets.bottom = 3;
		//gbc.insets.right = 10;	
		PanelModem.add(Barra1, gbc);
		
		Barra2 = new JProgressBar();
		Barra2.setValue(80);
		Barra2.setString("CARGA");
		//Barra2.setSize(1000,2000);
		Barra2.setStringPainted(true);
		gbc.anchor = GridBagConstraints.NORTHWEST;
		gbc.gridx = 1;
		gbc.gridy = 2;
		//gbc.weightx= 1.0;
		//gbc.weighty= 1.0;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		//gbc.insets.left = 10;
		gbc.insets.top = 8;
		//gbc.insets.bottom = 3;
		//gbc.insets.right = 10;	
		PanelModem.add(Barra2, gbc);
		
	    gbc.anchor = GridBagConstraints.NORTHWEST;
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.weightx= 1.0;
		gbc.weighty= 1.0;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		gbc.insets.left = 10; 
		gbc.insets.top = 75;
		//gbc.insets.right = 10;
		PanelModem.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder()/*,CConstants.BORDER_MOBILE_INFORMATION*/));
		getContentPane().add(PanelModem, gbc);
		
		JPanel PanelEstado = new JPanel();
		PanelEstado.setLayout(new GridBagLayout());
		
		PanelEstadoConectado = new JLabel("Escuchando SMS", JLabel.LEFT);
	    gbc.anchor = GridBagConstraints.NORTHWEST;
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.weightx= 1.0;
		gbc.weighty= 1.0;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		//gbc.insets.left = 10; 
		gbc.insets.top = 10;
		gbc.insets.bottom = 10;
		//gbc.insets.right = 10;	
		PanelEstado.add(PanelEstadoConectado, gbc);
		
		PanelEstadoProceso = new JLabel("Procesando SMS", JLabel.LEFT);
	    gbc.anchor = GridBagConstraints.NORTHWEST;
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.weightx= 1.0;
		gbc.weighty= 1.0;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		//gbc.insets.left = 10; 
		gbc.insets.top = 10;
		gbc.insets.bottom = 10;
		//gbc.insets.right = 10;	
		PanelEstado.add(PanelEstadoProceso, gbc);
		
		PanelEstadoEnv = new JLabel("Enviando SMS", JLabel.LEFT);
	    gbc.anchor = GridBagConstraints.NORTHWEST;
		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.weightx= 1.0;
		gbc.weighty= 1.0;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		//gbc.insets.left = 10; 
		gbc.insets.top = 10;
		gbc.insets.bottom = 10;
		//gbc.insets.right = 10;	
		PanelEstado.add(PanelEstadoEnv, gbc);
		
		PanelEstadoBD = new JLabel("Conexion Base De Datos", JLabel.LEFT);
	    gbc.anchor = GridBagConstraints.NORTHWEST;
		gbc.gridx = 0;
		gbc.gridy = 3;
		gbc.weightx= 1.0;
		gbc.weighty= 1.0;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		//gbc.insets.left = 10; 
		gbc.insets.top = 10;
		gbc.insets.bottom = 10;
		//gbc.insets.right = 10;	
		PanelEstado.add(PanelEstadoBD, gbc);

		PanelEstadoWeb = new JLabel("Conexion Web", JLabel.LEFT);
	    gbc.anchor = GridBagConstraints.NORTHWEST;
		gbc.gridx = 0;
		gbc.gridy = 4;
		gbc.weightx= 1.0;
		gbc.weighty= 1.0;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		//gbc.insets.left = 10; 
		gbc.insets.top = 10;
		gbc.insets.bottom = 20;
		//gbc.insets.right = 10;	
		PanelEstado.add(PanelEstadoWeb, gbc);
		
		
		//Ovalo = new Graphics();
		JPanel Treo = new JPanel();
		Treo.setLayout(new GridBagLayout());
		gbc.anchor = GridBagConstraints.NORTHEAST;
		Treo.setBackground(Color.blue);
		//public void paint(Graphics Ovalo) {
		//	super.paint(g)(Ovalo);
		//	Ovalo.drawOval(5,5,5,5);
		//}
		//super.paintComponents(Ovalo);
		//Ovalo.drawOval(5,5,5,5);
		//Ovalo.fillOval(x, y, width, height)
	    //gbc.anchor = GridBagConstraints.NORTHWEST;
		//gbc.gridx = 1;
		//gbc.gridy = 0;
		//gbc.weightx= 1.0;
		//gbc.weighty= 1.0;
		//gbc.gridwidth = 1;
		//gbc.gridheight = 1;
		//gbc.insets.left = 10; 
		//gbc.insets.top = 10;
		//gbc.insets.bottom = 20;
		//gbc.insets.right = 10;	
		//Treo.add(gbc);
		getContentPane().add(Treo);

		JRadioButton b1 = new JRadioButton("");
		b1.setBackground(Color.GREEN);
		b1.setContentAreaFilled(true);
		b1.setForeground(Color.blue);
		b1.setSelected(true);
		b1.setContentAreaFilled(false);
		b1.setEnabled(false);
		b1.setForeground(Color.blue);
	    gbc.anchor = GridBagConstraints.NORTHWEST;
		gbc.gridx = 1;
		gbc.gridy = 0;
		gbc.weightx= 1.0;
		gbc.weighty= 1.0;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		//gbc.insets.left = 10; 
		//gbc.insets.top = 10;
		//gbc.insets.bottom = 20;
		//gbc.insets.right = 10;	
		PanelEstado.add(b1, gbc);
		
		ImageIcon alo = new ImageIcon("Luces.jpg");
		
	    JLabel pepin = new JLabel("a",alo,JLabel.CENTER);
	    pepin.setVerticalTextPosition(JLabel.TOP);
	    pepin.setHorizontalTextPosition(JLabel.LEFT);
		gbc.anchor = GridBagConstraints.NORTHWEST;
		gbc.gridx = 1;
		gbc.gridy = 1;
		gbc.weightx= 1.0;
		gbc.weighty= 1.0;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		//gbc.insets.left = 10; 
		//gbc.insets.top = 10;
		//gbc.insets.bottom = 20;
		//gbc.insets.right = 10;	
		PanelEstado.add(pepin, gbc);
		
		//try {UIManager.setLookAndFeel("javax.swing.plaf.metal");}
		//catch (Exception ex) {};
		
		gbc.anchor = GridBagConstraints.NORTHWEST;
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.weightx= 1.0;
		gbc.weighty= 1.0;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		gbc.insets.left = 8;
		gbc.insets.top = 210;
		gbc.insets.bottom = 10;
		PanelEstado.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(),"Estado Servidor SMS"/*,TitledBorder.CENTER,TitledBorder.ABOVE_TOP*/));
		getContentPane().add(PanelEstado, gbc);
		
		JPanel Mensajes1 = new JPanel();
		Mensajes1.setLayout(new GridBagLayout());
				
		sms1label = new JLabel("SMS Proviene De Cel:",JLabel.LEFT);
		gbc.anchor = GridBagConstraints.CENTER;
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.weightx= 1.0;
		gbc.weighty= 1.0;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		//gbc.insets.left = 10; 
		//gbc.insets.left = 10;
		//gbc.insets.bottom = 10;
		//gbc.insets.right = 10;	
		Mensajes1.add(sms1label, gbc);
		
		sms1text = new JTextArea(8,20);
		sms1text.setEditable(true);
		sms1text.setLineWrap(true);
		sms1text.setText("HOLAAAAAAAAAAAA");
		gbc.anchor = GridBagConstraints.SOUTHWEST;
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.weightx= 1.0;
		gbc.weighty= 1.0;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		//gbc.insets.left = 10; 
		//gbc.insets.left = 10;
		//gbc.insets.bottom = 10;
		//gbc.insets.right = 10;	
		Mensajes1.add(sms1text, gbc);
		
		gbc.anchor = GridBagConstraints.SOUTHWEST;
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.weightx= 1.0;
		gbc.weighty= 1.0;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		gbc.insets.left = 10;
		gbc.insets.top = 550;
		gbc.insets.bottom = 10;
		Mensajes1.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(),"SMS Entrante"));
		getContentPane().add(Mensajes1, gbc);
		
		//JPanel Mensajes2 = new JPanel();
		//Mensajes2.setLayout(new GridBagLayout());
		
		//JPanel Mensajes3 = new JPanel();
		//Mensajes3.setLayout(new GridBagLayout());
		
		//getContentPane().add(statusPanel, gbc);
		/*JPanel incomingPanel = new JPanel();
		incomingPanel.setLayout(new GridBagLayout());
		lblInFrom = new JLabel(CConstants.LABEL_INCOMING_FROM, JLabel.LEFT);
		gbc.gridx = 0;
		gbc.gridy = 0;
		incomingPanel.add(lblInFrom, gbc);
		txtInText = new JTextArea(8,20);
		txtInText.setEditable(true);
		txtInText.setLineWrap(true);
		gbc.gridx = 1;
		gbc.gridy = 2;
		gbc.gridheight = 8;
		incomingPanel.add(txtInText, gbc);
		incomingPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), CConstants.BORDER_INCOMING_MESSAGES));
		gbc.anchor = GridBagConstraints.NORTH;
		gbc.weightx = 100;
		gbc.weighty = 100;
		gbc.gridx = 1;
		gbc.gridy = 0;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		gbc.insets.left = 5; gbc.insets.top = 5; gbc.insets.bottom = 5;
		getContentPane().add(incomingPanel, gbc);
		//Barra de Progreso
		gbc.gridheight = 0;
		gbc.gridwidth = 2;
		gbc.anchor = GridBagConstraints.CENTER;
		gbc.gridx=0;
		gbc.gridy=0;
		Barra = new JProgressBar();
		//Barra.setLayout(GridBagLayout());
		//Barra.setLayout(new GridBagLayout());
		Barra.setValue(50);
		getContentPane().add(Barra, gbc);
		Barra.setStringPainted(true);*/
		
		//Agregamos un boton
		JButton boton = new JButton("Testing");
		JPanel bicho = new JPanel();
		bicho.setLayout(new FlowLayout());
		bicho.add(boton);
		boton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				GridBagConstraints gbz = new GridBagConstraints();
				//final Graphics g;
			    //g = pepin.getGraphics();
			    //pepin.imageUpdate(img, infoflags, x, y, w, h)
				JLabel pepin = new JLabel("a",new ImageIcon("1.jpg"),JLabel.CENTER);
			    pepin.setVerticalTextPosition(JLabel.TOP);
			    pepin.setHorizontalTextPosition(JLabel.LEFT);
				gbz.anchor = GridBagConstraints.NORTHWEST;
				gbz.gridx = 1;
				gbz.gridy = 1;
				gbz.weightx= 1.0;
				gbz.weighty= 1.0;
				gbz.gridwidth = 1;
				gbz.gridheight = 1;
				//gbc.insets.left = 10; 
				//gbc.insets.top = 10;
				//gbc.insets.bottom = 20;
				//gbc.insets.right = 10;	
				//PanelEstado.add(pepin, gbz);
				
				//Barra.setValue(Barra.getValue()+10);
			}
		});
		gbc.anchor = GridBagConstraints.SOUTHEAST;
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.weightx= 1.0;
		gbc.weighty= 1.0;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		//gbc.insets.left = 8;
		//gbc.insets.top = 210;
		gbc.insets.bottom = 10;
		gbc.insets.right = 10;
		PanelEstado.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(),"Botones"/*,TitledBorder.CENTER,TitledBorder.ABOVE_TOP*/));
		getContentPane().add(bicho, gbc);
	}
	
	public static void main(String[] args) {
		
		Ventanas = new IG();
		Ventanas.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Ventanas.show();	

	}
}
