package netbin;

// import CIncomingMessage;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;

public class XML {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
//		private void saveToXmlInQueue(CIncomingMessage message) throws Exception

			File xmlFile;
			PrintWriter out;
			
			for (int i=1;i<4;i++) {
			xmlFile = File.createTempFile("RecvMrx" + i + "-", ".xml", new File("C:\\Mensajes\\"));
			out = new PrintWriter(new FileWriter(xmlFile.getCanonicalPath()));
			out.println("<?xml version='1.0' encoding='iso-8859-7'?>");
			out.println("<message>");
			out.println("	<originator>" + "HOLA" + "</originator>");
			out.println("	<date>" + "DATE" + "</date>");
			out.println("	<text> <![CDATA[ " + "PELOTA" + " ]]> </text>");
			out.println("</message>");
			out.close();
		}
	}
}
