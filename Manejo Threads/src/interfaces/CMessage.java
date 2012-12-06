package interfaces;
//
//	jSMSEngine API.
//	An open-source API package for sending and receiving SMS via a GSM device.
//	Copyright (C) 2002-2005, Thanasis Delenikas, Athens/GREECE
//		Web Site: http://www.jsmsengine.org
//
//	jSMSEngine is a package which can be used in order to add SMS processing
//		capabilities in an application. jSMSEngine is written in Java. It allows you
//		to communicate with a compatible mobile phone or GSM Modem, and
//		send / receive SMS messages.
//
//	jSMSEngine is distributed under the LGPL license.
//
//	This library is free software; you can redistribute it and/or
//		modify it under the terms of the GNU Lesser General Public
//		License as published by the Free Software Foundation; either
//		version 2.1 of the License, or (at your option) any later version.
//	This library is distributed in the hope that it will be useful,
//		but WITHOUT ANY WARRANTY; without even the implied warranty of
//		MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
//		Lesser General Public License for more details.
//	You should have received a copy of the GNU Lesser General Public
//		License along with this library; if not, write to the Free Software
//		Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
//

//package org.jsmsengine;

import java.util.*;

/**
	This class encapsulates the basic characteristics of an SMS message. A message
	is further subclassed to an "Incoming" message and an "Outgoing" message.
	<br><br>
	This class is <strong>never</strong> used directly. Please use one of its descendants.

	@see	CIncomingMessage
	@see	COutgoingMessage
	@see	CPhoneBook
*/

public class CMessage
{
	public static final int MESSAGE_ENCODING_7BIT = 1;
	public static final int MESSAGE_ENCODING_8BIT = 2;
	public static final int MESSAGE_ENCODING_UNICODE = 3;

	public static final int TYPE_INCOMING = 1;
	public static final int TYPE_OUTGOING = 2;
	public static final int TYPE_PROTOCOL = 3;

	private int type;
	protected String id;
	protected int memIndex;
	protected Date date;
	protected String originator;
	protected String recipient;
	protected String text;
	protected int messageEncoding;
	protected int sid1;

	/**
		Default constructor of the class.

		@param	type	the type (incoming/outgoing) of the message.
		@param	date	the creation date of the message.
		@param	originator	the originator's number. Applicable only for incoming messages.
		@param	recipient	the recipient's number. Applicable only for outgoing messages.
		@param	text	the actual text of the message.
		@param	memIndex		the index of the memory location in the GSM device where
						this message is stored. Applicable only for incoming messages.

		<br><br>Notes:<br>
		<ul>
			<li>Phone numbers are represented in their international format (e.g. +306974... for Greece).</li>
			<li>"Recipient" may be an entry from the phonebook.</li>
		</ul>
	*/
	//Maurix
	public CMessage(int type, Date date, String originator, String recipient, String text, int memIndex, int sid)
	//Maurix
	{
		this.type = type;
		this.date = date;
		this.originator = originator;
		this.recipient = recipient;
		this.text = text;
		this.memIndex = memIndex;
		this.messageEncoding = MESSAGE_ENCODING_7BIT;
		//Maurix
		this.sid1 = sid;
		//Maurix
	}

	/**
		Returns the type of the message. Type is either incoming or outgoing, as denoted
		by the class' static values INCOMING and OUTGOING.

		@return  the type of the message.
	*/
	public int getType() { return type; }

	/**
		Returns the id of the message.

		@return  the id of the message.
	*/
	//Maurix
    //public String getId() { return id; }
	public int getId() { return sid1; }
    //Maurix
	/**
		Returns the memory index of the GSM device, where the message is stored.
		Applicable only for incoming messages.

		@return  the memory index of the message.
	*/
	public int getMemIndex() { return memIndex; }

	/**
		Returns the date of the message. For incoming messages, this is the sent date.
		For outgoing messages, this is the creation date.

		@return  the date of the message.
	*/
	public Date getDate() { return date; }

	/**
		Returns the actual text of the message (ASCII).

		@return  the text of the message.
	*/
	public String getText() { return text; }

	/**
		Returns the text of the message, in hexadecimal format.

		@return  the text of the message (HEX format).
	*/
	public String getHexText() { return CGSMAlphabets.text2Hex(text, CGSMAlphabets.GSM7BITDEFAULT); }

	/**
		Returns the encoding method of the message. Returns of the constants
		MESSAGE_ENCODING_7BIT, MESSAGE_ENCODING_8BIT, MESSAGE_ENCODING_UNICODE.
		This is meaningful only when working in PDU mode.

		@return  the message encoding.
	*/
	public int getMessageEncoding() { return messageEncoding; }

	/**
		Set the id of the message.

		@param	id	the id of the message.
	*/
	//Maurix
	//public void setId(String id) { this.id = id; }
	public void setId(int id) { this.sid1 = id; }
	//Maurix
	/**
		Set the text of the message.

		@param	text	the text of the message.
	*/
	public void setText(String text) { this.text = text; }

	/**
		Set the date of the message.

		@param	date	the date of the message.
	*/
	public void setDate(Date date) { this.date = date; }

	/**
		Set the message encoding. Should be one of the constants
		MESSAGE_ENCODING_7BIT, MESSAGE_ENCODING_8BIT, MESSAGE_ENCODING_UNICODE.
		This is meaningful only when working in PDU mode - default is 7bit.

		@param	messageEncoding	one of the message encoding contants.
	*/
	public void setMessageEncoding(int messageEncoding) { this.messageEncoding = messageEncoding; }

/*	public String toString()
	{
		String str;

		str = "** GSM MESSAGE **\n";
		str += "  Type: " + (type == TYPE_INCOMING ? "Incoming." : "Outgoing.") + "\n";
		//str += "  Id: " + id + "\n";
		str += "  Id: " + sid1 + "\n";
		str += "  Memory Index: " + memIndex + "\n";
		str += "  Date: " + date + "\n";
		str += "  Originator: " + originator + "\n";
		str += "  Recipient: " + recipient + "\n";
		str += "  Text: " + text + "\n";
		str += "  Hex Text: " + CGSMAlphabets.text2Hex(text, CGSMAlphabets.GSM7BITDEFAULT) + "\n";
		str += "  Encoding: " + messageEncoding + "\n";
		str += "***\n";
		return str;
	} */ // (MODIF)
	
    public String CrearFecha(Date FechaDato) {					
    	String FechaTemp, FechaFinal;
    	FechaTemp = FechaFinal = "";
    	Date Fecha;
    	Fecha = FechaDato; 	
    	FechaTemp = Fecha.toString();
   		if (FechaTemp.contains("Sun")) {FechaFinal = "Domingo ";}
    	else if (FechaTemp.contains("Mon")) {FechaFinal = "Lunes ";}
    	else if (FechaTemp.contains("Tue")) {FechaFinal = "Martes ";}
    	else if (FechaTemp.contains("Wed")) {FechaFinal = "Miércoles ";}
    	else if (FechaTemp.contains("Thu")) {FechaFinal = "Jueves ";}
    	else if (FechaTemp.contains("Fri")) {FechaFinal = "Viernes ";}
    	else if (FechaTemp.contains("Sat")) {FechaFinal = "Sábado ";}
   		FechaFinal += FechaTemp.substring(8, 10) + "/";
   		if (FechaTemp.contains("Jan")) {FechaFinal += "01";}
   		else if (FechaTemp.contains("Feb")) {FechaFinal += "02";}
   		else if (FechaTemp.contains("Mar")) {FechaFinal += "03";}
   		else if (FechaTemp.contains("Apr")) {FechaFinal += "04";}
   		else if (FechaTemp.contains("May")) {FechaFinal += "05";}
   		else if (FechaTemp.contains("Jun")) {FechaFinal += "06";}
   		else if (FechaTemp.contains("Jul")) {FechaFinal += "07";}
   		else if (FechaTemp.contains("Aug")) {FechaFinal += "08";}
   		else if (FechaTemp.contains("Sep")) {FechaFinal += "09";}
   		else if (FechaTemp.contains("Oct")) {FechaFinal += "10";}
   		else if (FechaTemp.contains("Nov")) {FechaFinal += "11";}
   		else if (FechaTemp.contains("Dec")) {FechaFinal += "12";}
   		FechaFinal += "/" + FechaTemp.substring(32, 34) + " " + FechaTemp.substring(11, 19);
    	return FechaFinal;
    }
	
	public String toString() {
		String str = "";
//		String Temp = "";
		if (type == TYPE_INCOMING) {
			str  = " Nuevo Mensaje SMS\n";
//			str += " Tipo Mensaje: " + (type == TYPE_INCOMING ? "Incoming." : "Outgoing.") + "\n";
			str += " Número De ID Del SMS: " + sid1 + "\n";
			str += " Indice De Memoria: " + memIndex + "\n";
			str += " Fecha De Recepción: " + CrearFecha(date) + "\n";
			str += " Número Del Celular De Origen: " + originator + "\n";
//			str += " Recipiente: " + recipient + "\n";
			if (messageEncoding == MESSAGE_ENCODING_7BIT) { str += " Codificación Del Texto: 7 bits\n"; }
			else if (messageEncoding == MESSAGE_ENCODING_8BIT) { str += " Codificación Del Texto: 8 bits\n"; }
			else { str += " Codificación Del Texto: Unicode\n"; }
			str += " Texto: " + text + "\n";
/*			if (text.length()<=33) { str += text + "\n"; }
			else {
				Temp = text.substring(0, 32);
				text = text.substring(32);
				str += Temp + "\n ";
				if (text.length()<=33) { str += text + "\n"; }
				else {
					Temp = text.substring(0,32);
					text = text.substring(32);
					str += Temp + "\n ";
					if (text.length()<=33) { str += text + "\n"; }
					else {
						Temp = text.substring(0,32);
						text = text.substring(32);
						str += Temp + "\n " + text + "\n";
					}
				}
			}*/
			str += " Texto Codificado: " + CGSMAlphabets.text2Hex(text, CGSMAlphabets.GSM7BITDEFAULT);
		}
		else if (type == TYPE_OUTGOING) {
//			str += " Tipo Mensaje: " + (type == TYPE_INCOMING ? "Incoming." : "Outgoing.") + "\n";
			str  = " Envío De Mensaje SMS\n";
			str += " Número De ID Del SMS: " + sid1 + "\n";
			str += " Indice De Memoria: " + memIndex + "\n";
			str += " Fecha De Creación: " + CrearFecha(date) + "\n";
//			str += " Número Celular Originador: " + originator + "\n";
			str += " Número Del Celular De Destino: " + recipient + "\n";
			str += " Codificación Del Texto: 7 bits\n";
			str += " Texto: " + text + "\n";
			str += " Texto Codificado: " + CGSMAlphabets.text2Hex(text, CGSMAlphabets.GSM7BITDEFAULT);
		}
		else {
			str  = " Nuevo Mensaje De Información De Protocolo\n";
//			str += " Tipo Mensaje: " + (type == TYPE_INCOMING ? "Incoming." : "Outgoing.") + "\n";
//			str += " Número De ID: " + sid1 + "\n";
//			str += " Indice De Memoria: " + memIndex + "\n";
			str += " Fecha De Creación: " + CrearFecha(date) + "\n";
//			str += " Número Celular Originador: " + originator + "\n";
//			str += " Número Celular Destino: " + recipient + "\n";
			str += " Texto De Estado: " + text;
//			str += " Codificación Del Texto: " + messageEncoding + "\n";
		}
		return str;
	}
}
