package netbin;

import java.io.*;
import java.util.*;

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import javax.swing.*;
import javax.swing.event.*;

public class ProbarCosas4 extends JFrame {

	private JMenuBar menuBar;
	private JMenu fileMenu, aboutMenu;
	private JMenuItem mniConnect, mniDisconnect, mniExit;
	private JMenuItem mniAbout;
	private JLabel lblManuf, lblModel, lblSerialNo, lblIMSI, lblSwVersion, lblBattery, lblSignal, lblStatus;
	private JTextField txtManuf, txtModel, txtSerialNo, txtIMSI, txtSwVersion, txtStatus;
	private JProgressBar pgbBattery, pgbSignal;
	private JLabel lblInFrom, lblInDate, lblInText;
	private JTextField txtInFrom, txtInDate;
	private JTextArea txtInText;
	private JLabel lblOutTo, lblOutDate, lblOutText;
	private JTextField txtOutTo, txtOutDate;
	private JTextArea txtOutText;
	private JLabel lblUpSince, lblTraffic, lblTrafficIn, lblTrafficOut;
	private JTextField txtUpSince, txtTrafficIn, txtTrafficOut;
	private JLabel lblInterfaces, lblInterfaceDB, lblInterfaceXML, lblInterfaceRMI;
	private JLabel lblRawLogs, lblRawInLog, lblRawOutLog;
	
	public ProbarCosas4() {
		setTitle("TITULO");
		setSize(690, 580);
		setLocation(5, 5);
		getContentPane().setLayout(new GridBagLayout());
		
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.anchor = GridBagConstraints.WEST;
		gbc.weightx = 100;
		gbc.weighty = 100;
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		gbc.insets.left = 5; gbc.insets.top = 5; gbc.insets.right = 5;
		JPanel statusPanel = new JPanel();
		statusPanel.setLayout(new GridBagLayout());
			lblManuf = new JLabel("SONY", JLabel.LEFT);
			txtManuf = new JTextField("", 16);
			txtManuf.setEditable(false);
			gbc.gridx = 0;
			gbc.gridy = 0;
			statusPanel.add(lblManuf, gbc);
			gbc.gridx = 1;
			gbc.gridy = 0;
			statusPanel.add(txtManuf, gbc);

			lblModel = new JLabel("K750", JLabel.LEFT);
			txtModel = new JTextField("", 16);
			txtModel.setEditable(false);
			gbc.gridx = 0;
			gbc.gridy = 1;
			statusPanel.add(lblModel, gbc);
			gbc.gridx = 1;
			gbc.gridy = 1;
			statusPanel.add(txtModel, gbc);
			
			statusPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Maurix"));
			gbc.anchor = GridBagConstraints.CENTER;
			gbc.weightx = 100;
			gbc.weighty = 100;
			gbc.gridx = 0;
			gbc.gridy = 0;
			gbc.gridwidth = 1;
			gbc.gridheight = 1;
			gbc.insets.left = 5; gbc.insets.top = 5;
			getContentPane().add(statusPanel, gbc);
	}
	
	public static void main(String[] args) {
		ProbarCosas4 mainWindow;
		mainWindow = new ProbarCosas4();
		mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainWindow.show();
		// TODO Auto-generated method stub
	}

}
