package interfaces;

import java.io.*;
import java.util.*;
import java.util.logging.*;

public class CService
{
	public static final String _name = "jSMSEngine API";
	public static final String _version = "1.2.6 (B1)";
	public static final String _reldate = "Feb 04, 2005";
	public static final int ERR_OK = 0;
	public static final int ERR_GENERIC_ERROR = -1;
	public static final int ERR_NOT_INITIALIZED = -10;
	public static final int ERR_NOT_CONNECTED = -11;
	public static final int ERR_COMM_NOT_SUPPORTED = -20;
	public static final int ERR_CHARSET_HEX_NOT_SUPPORTED = -21;
	public static final int ERR_CANNOT_DISABLE_INDICATIONS = -22;
	public static final int ERR_MESSAGE_NOT_FOUND = -30;
	public static final int ERR_SEND_FAILED = -40;
	public static final int ERR_PHONEBOOK_NOT_LOADED = -50;
	public static final int ERR_INVALID_DIR = -100;
	public static final int ERR_NO_CACHE = -101;
	public static final int ERR_SIM_PIN_ERROR =-102;
	public static final int ERR_NOT_SUPPORTED = -9999;
	public static final int MODE_ASCII = 1;
	public static final int MODE_PDU = 2;
	public static final int RECEIVE_MODE_SYNC = 1;
	public static final int RECEIVE_MODE_ASYNC = 2;
    private static Logger log = Logger.getLogger("org.jsmsengine");
	public static final String DEFAULT_VALUE_NOT_REPORTED = "* N/A *";
	public static final int MAX_SMS_LEN_7BIT = 160;
	public static final int MAX_SMS_LEN_8BIT = 140;
	public static final int MAX_SMS_LEN_UNICODE = 70;
	private String cacheDir;
	private String smscNumber;
	private String simPin;
	private int operationMode;
	private int supportedModes;
	private int receiveMode;
	private CSerialDriver serialDriver;
	private boolean initialized;
	private boolean connected;
	private CDeviceInfo deviceInfo;
	private CReceiveThread receiveThread;
	private int smsleidos;						// Cantidad de Mensajes SMS Leidos Del Dispositivo GSM
	private int PosMem[];
	public int x;

	/**
		Synchronization object for critical sections of the API.
	*/
	private Object _SYNC_ = new Object();

	/**
		Default constructor of the class.

		@param	port	the serial port where the GSM device is connected (e.g. "com1"). 
		@param	baud	the connection speed (i.e. 9600, 19200 etc).

		<br><br>
		Notes:
		<ul>
			<li>Use one of the standard values for baud. Most GSM devices work well
					at 9600 or 19200. Some may handle speeds up to 115200 (like Nokia
					mobile phone model 6210 does). The connection speed is not that
					important to the speed at which jSMSEngine processes messages.
					Personally, I work at 9200 to avoid pushing the mobile. Dedicated GSM
					modems may handle higher speeds better than mobile phones do.</li>
		</ul>
	*/
	public CService(String port, int baud)
	{
		setInitialized(false);
		setConnected(false);
		serialDriver = new CSerialDriver(port, baud, log);
		deviceInfo = new CDeviceInfo();
		receiveMode = RECEIVE_MODE_SYNC;
		receiveThread = new CReceiveThread();
		receiveThread.start();
		log.setLevel(Level.SEVERE);
		PosMem = new int[200];
		smsleidos = x = 0;
	}

	/**
		Returns TRUE if the service has already been initialized.

		@return  TRUE if the service has already been initialized.
	*/
	public boolean getInitialized() { return initialized; }

	/**
		Returns TRUE if the service is connected with the GSM device.

		@return  TRUE if the service is connected with the GSM device.
	*/
	public boolean getConnected() { return connected; }

	/**
		Returns a CDeviceInfo object that holds information about the GSM
		device in use.

		@return  a CDeviceInfo object.
		@see	CDeviceInfo
	*/
	public CDeviceInfo getDeviceInfo() { return deviceInfo; }

	/**
		Sets the cache directory for messages.

		@param	dir	The directory which will act like a cache.
		@return  One of ERR_* values.
	*/
	public int setCacheDir(String dir)
	{
		if (dir == null) return ERR_INVALID_DIR;
		else
		{
			File f = new File(dir);
			if (f.exists())
			{
				cacheDir = dir;
				return ERR_OK;
			}
			else return ERR_INVALID_DIR;
		}
	}

	/**
		Sets the Short Message Service Center (SMSC) number. Please use international format.
		If you don't want to set the SMSC and use the one defined in your GSM device, use an
		empty string parameter. Another way to do the same, is to pass a null parameter. Some
		phones may prefer one way or the other - please test your phone.

		@param	smscNumber	the SMSC number.
	*/
	public void setSmscNumber(String smscNumber) { this.smscNumber = smscNumber; }

	/**
		Returns the Short Message Service Center (SMSC) number you have previously
		defined with setSmscNumber().

		@return  the SMSC number.
	*/
	public String getSmscNumber() { return smscNumber; }

	/**
		Sets the SIM pin number. This is used if and when the GSM device asks for it. If you set it to
		null, then the API does not give any PIN to the device (in order to avoid locking it up), and
		returns ERR_SIM_PIN_ERROR.

		@param	simPin	the SIM pin number.
	*/
	public void setSimPin(String simPin) { this.simPin = simPin; }

	/**
		Returns the SIM pin number.

		@return  the SIM pin number.
	*/
	public String getSimPin() { return simPin; }

	/**
		Sets the operation mode of the GSM device

		@param	mode	the mode of operation (one of values MODE_ASCII, MODE_PDU).
		@return	TRUE if the change of mode succeded.
		@see	CService#getOperationMode()
	*/
	public boolean setOperationMode(int mode)
	{
		boolean result;

		try
		{
			switch (mode)
			{
				case MODE_ASCII:
					serialDriver.send(CATCommands.AT_ASCII_MODE);
					if (serialDriver.getResponse().equalsIgnoreCase(CATCommands.AT_OK))
					{
						result = true;
						operationMode = MODE_ASCII;
					}
					else result = false;
					break;
				case MODE_PDU:
					serialDriver.send(CATCommands.AT_PDU_MODE);
					if (serialDriver.getResponse().equalsIgnoreCase(CATCommands.AT_OK))
					{
						result = true;
						operationMode = MODE_PDU;
					}
					else result = false;
					break;
				case 0:
					operationMode = 0;
					result = true;
					break;
				default:
					result = false;
			}
		}
		catch (Exception e) { result = false; }
		return result;
	}

	/**
		Returns the operation mode of the GSM device, i.e. one of the values
		MODE_ASCII, MODE_PDU.

		@return  the operation mode.
		@see	CService#setOperationMode(int)
	*/
	public int getOperationMode() { return operationMode; }

	/**
		Sets the reception mode.
		There are two reception modes, the synchronous and the asynchronous.
		In synchronous mode, you should call readMessages() function on demand,
		where you want to check for new messages. In asynchronous mode, the engine
		automatically calls the received() method (which you <strong>should</strong>
		override) for every received message.
		<br>By default, the reception mode is the synchronous one.

		@param	receiveMode	the reception mode (one of values RECEIVE_MODE_ASYNC, RECEIVE_MODE_SYNC).
		@see	CService#getReceiveMode()
	*/
	public void setReceiveMode(int receiveMode) { this.receiveMode = receiveMode; }

	/**
		Returns the reception mode.

		@return	the reception mode (one of values RECEIVE_MODE_ASYNC, RECEIVE_MODE_SYNC).
		@see	CService#setReceiveMode(int)
	*/
	public int getReceiveMode() { return receiveMode; }

	/**
		Returns the cache directory for messages.

		@return  the caching directory.
		@see	CService#setCacheDir(String)
	*/
	public String getCacheDir() { return cacheDir; }

	/**
		Initializes the service. This should be the first method call.

		@return  ERR_OK (for this version).
		@see	CService#connect()
	*/
	public int initialize()
	{
		cacheDir = null;
		smscNumber = null;
		simPin = null;
		operationMode = 0;
		supportedModes = 0;
		setInitialized(true);
		return ERR_OK;
	}

	/**
		Connects to the GSM device. Opens the serial port, and sends the appropriate
		AT commands to initialize the operation mode of the GSM device. Retrieves
		information about the GSM device. This method should be called after
		initialize() has been called.
		<br>
		By default, jSMSEngine API sets your GSM device to PDU mode. If you want to
		switch to ASCII mode (I don't see any reason why, but anyway...), use the
		setOperationMode() method.
		<br><br>
		Notes:
		<br>
		<ul>
			<li>The GSM device specific information (read by the call to refreshDeviceInfo()
					function is called once from this method. Since some information changes
					with time (such as battery or signal level), its your responsibility to
					call refreshDeviceInfo() periodically in order to have the latest information.
					Otherwise, you will get the information snapshot taken at the time
					of the initial connection.
			</li>
		</ul>

		@return  One of ERR_* values.
		@see	CDeviceInfo
		@see	CService#refreshDeviceInfo()
		@see	CService#disconnect()
		@see	CService#initialize()
		@see	CService#setOperationMode(int)
	*/
	public int connect(CColaEntrada ColaInput)
	{
		synchronized (_SYNC_)
		{
			if (getInitialized())
			{
				if (getCacheDir() == null) return ERR_NO_CACHE;
				else
				{
					try
					{
						if (serialDriver.open())
						{
							try { Thread.sleep(2000); } catch (Exception e) {}
							serialDriver.clearBuffer();
							serialDriver.send(CATCommands.AT_ECHO_OFF);
							serialDriver.getResponse();
							serialDriver.send(CATCommands.AT_AT);
							if (serialDriver.getResponse().equalsIgnoreCase(CATCommands.AT_OK))
							{
								if (getSimPin() != null)
								{
									serialDriver.send(CATCommands.AT_CHECK_LOGIN);
									if (serialDriver.getResponse().indexOf(CATCommands.AT_READY) == -1)
									{
										if (getSimPin() == null)
										{
											serialDriver.close();
											setConnected(false);
											return ERR_SIM_PIN_ERROR;
										}
										else
										{
											serialDriver.send(CUtils.substituteSymbol(CATCommands.AT_LOGIN, "{1}", getSimPin()));
											if (serialDriver.getResponse().indexOf(CATCommands.AT_OK) == -1)
											{
												serialDriver.close();
												setConnected(false);
												return ERR_SIM_PIN_ERROR;
											}
											else
											{
												// Pin OK - wait 20 seconds for the GSM device to boot up...
												try { Thread.sleep(10000); } catch (Exception e) {}
												serialDriver.send(CATCommands.AT_AT); serialDriver.getResponse();
												try { Thread.sleep(10000); } catch (Exception e) {}
												serialDriver.send(CATCommands.AT_AT); serialDriver.getResponse();
											}
										}
									}
								}
 								serialDriver.send(CATCommands.AT_ASCII_MODE);
								if (serialDriver.getResponse().equalsIgnoreCase(CATCommands.AT_OK)) supportedModes |= MODE_ASCII;
								serialDriver.send(CATCommands.AT_PDU_MODE);
								if (serialDriver.getResponse().equalsIgnoreCase(CATCommands.AT_OK)) supportedModes |= MODE_PDU;
								if ((supportedModes & MODE_PDU) != 0) setOperationMode(MODE_PDU);
								else if ((supportedModes & MODE_ASCII) != 0) setOperationMode(MODE_ASCII);
								else setOperationMode(0);
								if (operationMode == 0)
								{
									serialDriver.close();
									setConnected(false);
									return ERR_COMM_NOT_SUPPORTED;
								}
								else
								{
									setConnected(true);
									refreshDeviceInfo();
									// Voy Yo
									EnvioMensajeProtocolo(ColaInput,"IH","Dispositivo GSM Conectado - Fabricante: " + getDeviceInfo().getManufacturer() + " - Modelo: " + getDeviceInfo().getModel(),
									0,0,0,0,0,0,getDeviceInfo().getSignalLevel(),getDeviceInfo().getBatteryLevel(),true);
									System.out.println("Estado Servidor: OK - Leyendo Datos Del Cel");
									System.out.println("Celular Marca  : " + getDeviceInfo().getManufacturer());
									// Voy Yo
    								if ((deviceInfo.getManufacturer().toUpperCase().indexOf("ERICSSON") >= 0) && (deviceInfo.getModel().toUpperCase().indexOf("630") >= 0)) serialDriver.send(CATCommands.AT_ERICSSON_T630_DISABLE_INDICATIONS);
									else if (deviceInfo.getManufacturer().toUpperCase().indexOf("ERICSSON") >= 0) serialDriver.send(CATCommands.AT_ERICSSON_DISABLE_INDICATIONS);
									else serialDriver.send(CATCommands.AT_DISABLE_INDICATIONS);
									if (serialDriver.getResponse().equalsIgnoreCase(CATCommands.AT_OK))
									{
										if (operationMode == MODE_ASCII)
										{
											serialDriver.send(CATCommands.AT_CHARSET_HEX);
											if (serialDriver.getResponse().equalsIgnoreCase(CATCommands.AT_OK))
											{
												if (deviceInfo.getManufacturer().toUpperCase().indexOf("SIEMENS") >= 0)
												{
													serialDriver.send(CATCommands.AT_SIEMENS_SMS_STORAGE);
													serialDriver.getResponse();
												}
												else
													if (deviceInfo.getManufacturer().toUpperCase().indexOf("ERICSSON") >= 0)
													{
														serialDriver.send(CATCommands.AT_ERICSSON_SMS_STORAGE);
														serialDriver.getResponse();
													}
											}
											else
											{
												serialDriver.close();
												setConnected(false);
												return ERR_CHARSET_HEX_NOT_SUPPORTED;
											}
										}
										else
										{
											if (deviceInfo.getManufacturer().toUpperCase().indexOf("SIEMENS") >= 0)
											{
												serialDriver.send(CATCommands.AT_SIEMENS_SMS_STORAGE);
												serialDriver.getResponse();
											}
											else
												if (deviceInfo.getManufacturer().toUpperCase().indexOf("ERICSSON") >= 0)
												{
													serialDriver.send(CATCommands.AT_ERICSSON_SMS_STORAGE);
													serialDriver.getResponse();
												}
										}
									}
									else
									{
										serialDriver.close();
										setConnected(false);
										System.out.println("ERROR A1"); // (MODIF)
										return ERR_CANNOT_DISABLE_INDICATIONS;								
									}
								}
							}
							else
							{
								serialDriver.close();
								setConnected(false);
								System.out.println("ERROR A2"); // (MODIF)
							}
						}
						else { 
							setConnected(false);
							EnvioMensajeProtocolo(ColaInput,"IH","La Conexión Al Dispositivo GSM Falló, Tipo De Error: " + ERR_NOT_CONNECTED,0,0,0,0,0,0,0,0,false);
							System.out.println("ERROR A3"); // (MODIF)
						}
						return (getConnected() ? ERR_OK : ERR_NOT_CONNECTED);
					}
					catch (Exception e)
					{
						serialDriver.close();
						System.out.println("Puerto Serial No se Puede Abrir. ERROR A4"); // (MODIF)
						EnvioMensajeProtocolo(ColaInput,"IH","El Puerto Serial Ya Se Encuentra Utilizado Por Otra Aplicación, Tipo De Error: -12",0,0,0,0,0,0,0,0,false);
						return ERR_NOT_CONNECTED;
					}
				}
			}
			else {System.out.println("ERROR A5"); return ERR_NOT_INITIALIZED;} // (MODIF)
		}
	}

	/**
		Disconnects to the GSM device. Closes the serial port. 

		@return  ERR_OK value.
		@see	CService#connect()
	*/
	public int disconnect()
	{
		synchronized (_SYNC_)
		{
			try { serialDriver.close(); } catch (Exception e) {}
			setConnected(false);
			return ERR_OK;
		}
	}
	
	/**
		Loads the phonebook. The phonebook is an XML file containing associations of name
		and phone numbers.
		<br><br>
		<strong>The phonebook is optional.</strong> 

		@param	phoneBookFile	The XML full-path name which keeps the phonebook.
		@return  One of ERR_* values.
		@see	CPhoneBook
		@see	CopyOfCService#sendMessage(COutgoingMessage)
		@see	CopyOfCService#sendMessage(LinkedList)
	*/
	/**
		Refreshes the GSM device specific information. This method is called once during
		connection. Its up to the developer to call it periodically in order to get the latest
		information.

		@return  One of ERR_* values.
		@see	CDeviceInfo
		@see	CService#connect()
		@see	CService#getDeviceInfo()
	*/
	public int refreshDeviceInfo()
	{
		synchronized (_SYNC_)
		{
			if (getConnected())
				try
				{
					deviceInfo.manufacturer = getManufacturer();
					deviceInfo.model = getModel();
					deviceInfo.serialNo = getSerialNo();
					deviceInfo.imsi = getImsi();
					deviceInfo.swVersion = getSwVersion();
					deviceInfo.batteryLevel = getBatteryLevel();
					deviceInfo.signalLevel = getSignalLevel();
					return ERR_OK;
				}
				catch (Exception e)
				{
					e.printStackTrace();
					setConnected(false);
					return ERR_NOT_CONNECTED;
				}
			else return ERR_NOT_CONNECTED;
		}
	}

	public int readMessages(CColaEntrada ColaInput, int messageClass, int superid)
	{
		int i, j, memIndex, smsleidos;
		String response, line, pdu;
		BufferedReader reader;
		
		synchronized (_SYNC_)
		{
			if (getConnected())
			{
				switch (operationMode)
				{
					case MODE_PDU:
						try
						{
							serialDriver.send(CATCommands.AT_CMD_MODE);
							serialDriver.send(CATCommands.AT_PDU_MODE);
							response = serialDriver.getResponse();
							switch (messageClass)
							{
								case CIncomingMessage.CLASS_ALL:
									serialDriver.send(CUtils.substituteSymbol(CATCommands.AT_LIST, "{1}", "4"));
									break;
								case CIncomingMessage.CLASS_REC_UNREAD:
									serialDriver.send(CUtils.substituteSymbol(CATCommands.AT_LIST, "{1}", "0"));
									break;
								case CIncomingMessage.CLASS_REC_READ:
									serialDriver.send(CUtils.substituteSymbol(CATCommands.AT_LIST, "{1}", "1"));
									break;
								case CIncomingMessage.CLASS_STO_UNSENT:
									serialDriver.send(CUtils.substituteSymbol(CATCommands.AT_LIST, "{1}", "2"));
									break;
								case CIncomingMessage.CLASS_STO_SENT:
									serialDriver.send(CUtils.substituteSymbol(CATCommands.AT_LIST, "{1}", "3"));
									break;
							}
							response = serialDriver.getResponse();
							reader = new BufferedReader(new StringReader(response));
							line = reader.readLine().trim();
							smsleidos = 0;
							//Maurix
							//System.out.println("pdu completo:" + reader.toString());
							//System.out.println("pdu completo:" + line);
							//Maurix
							//Date A3 = new Date();
							//System.out.println("Fecha Sistema: " + A3.toString());
							System.out.println("Valor x: " + x);
							if (x>0) { System.out.println("Dato Array: " + PosMem[x-1]); }
							if (superid == 1) {
								while ((line != null) && (line.length() > 0) && (!line.equalsIgnoreCase("OK")))
								{
									i = line.indexOf(':');
									j = line.indexOf(',');
									memIndex = Integer.parseInt(line.substring(i + 1, j).trim());
									pdu = reader.readLine();
									if (isIncomingMessage(pdu))	{
										CIncomingMessage A1 = new CIncomingMessage(pdu, memIndex, superid);
										PosMem[x] = memIndex;
										x++;
										//Date A2 = A1.getDate();
										//String fecha = A2.toString();
										//System.out.println("Fecha: "+fecha);
										//System.out.println("IDMENSAJE: "+A1.getId());
										//CrearXML(A1);
										smsleidos++;
										superid++;
										ColaInput.Agregar(A1);
										deviceInfo.getStatistics().incTotalIn();
									}
								//else messageList.add(new COutgoingMessage(pdu, memIndex));
								line = reader.readLine().trim();
								//x = superid - 1;
								}
							}
							else {
								while ((line != null) && (line.length() > 0) && (!line.equalsIgnoreCase("OK")))
								{
									i = line.indexOf(':');
									j = line.indexOf(',');
									memIndex = Integer.parseInt(line.substring(i + 1, j).trim());
									pdu = reader.readLine();
									if (isIncomingMessage(pdu) && !EstaEnArray(PosMem,memIndex,x)) {									
										//line = reader.readLine().trim();
										//i = line.indexOf(':');
										//j = line.indexOf(',');
										//memIndex = Integer.parseInt(line.substring(i + 1, j).trim());
										//pdu = reader.readLine();
										//El mensaje ya ha sido cargado en el recorrido anterior
										CIncomingMessage A1 = new CIncomingMessage(pdu, memIndex, superid);
										//agrego al array de indices de memoria el nuevo espacio de memoria del nuevo mensaje sms leido
										PosMem[x] = memIndex;
										x++;
										//Date A2 = A1.getDate();
										//String fecha = A2.toString();
										smsleidos++;
										superid++;
										ColaInput.Agregar(A1);
										deviceInfo.getStatistics().incTotalIn();
									}
								//else messageList.add(new COutgoingMessage(pdu, memIndex));
								line = reader.readLine().trim();
								}
							}
							System.out.println("Valor x: " + x);
							this.smsleidos = smsleidos;
							reader.close();
							return ERR_OK;
						}
						catch (Exception e)
						{
							e.printStackTrace();
							return ERR_GENERIC_ERROR;
						}
					default:
						return ERR_GENERIC_ERROR;
				}
			}
			else return ERR_NOT_CONNECTED;
		}
	}

	/**
	 * Verifica si el mensaje SMS ya fue leído anteriormente.
	 * 
	 * @param PosMem Contiene un arreglo con los índices de memoria de los mensajes leídos anteriormente.
	 * @param memIndex Indice de memoria del mensaje SMS.
	 * @param x 
	 * @return Verdadero si el mensaje fue leído anteriormente. Falso en caso contrario.
	 * @author Mauricio Calgaro
	 */
	
	public boolean EstaEnArray(int PosMem[], int memIndex, int x) {
		for (int y=0;y<x;y++) {	
			if (memIndex == PosMem[y]) { return true; }
		}
		return false;
	}
	
	public int MensajesLeidos() { return smsleidos; }
	
	/**
		Send an SMS message from the GSM device. Once connected, you can create a
		COutgoingMessage object with the message you want to send, and pass it
		to this function.
		<br><br>
		<strong>Notes:</strong>
		<ul>
			<li>If you have set a phonebook, you can create the COutgoingMessage
				object with a nickname, instead of the actual phone number.</li>
		</ul>

		@param	message	a COutgoingMessage containing the message you wish to send.
		@return  One of ERR_* values.
		@see	COutgoingMessage
		@see	CPhoneBook
		@see	CService#sendMessage(LinkedList)
		@see	CService#setPhoneBook(String)
	*/
	
	public int sendMessage(COutgoingMessage message)
	{
		LinkedList<COutgoingMessage> messageList;
		COutgoingMessage msg;
		int error;

		synchronized (_SYNC_)
		{
			messageList = new LinkedList<COutgoingMessage>();
			messageList.add(message);
			error = sendMessage(messageList);
			if (error == ERR_OK)
			{
				msg = (COutgoingMessage) messageList.get(0);
				message.setDispatchDate(msg.getDispatchDate());
			}
			return error;
		}
	}

	/**
		Send an series of SMS messages from the GSM device. This method is used
		when you want to send more than one message as a batch. If your GSM device
		support the feature of keeping the GSM link open during message dispatch,
		this method should work faster than calling the sendMessage(COutgoingMessage)
		method many times.
		<br>
		Just create a LinkedList object, add as many COutgoingMessage objects you wish
		and call the method.
		<br><br>
		<strong>Notes:</strong>
		<ul>
			<li>If you have set a phonebook, you can create the COutgoingMessage
				object with a nickname, instead of the actual phone number.</li>
		</ul>

		@param	messageList	a LinkedList filled with COutgoingMessage objects.
		@return  One of ERR_* values.
		@see	COutgoingMessage
		@see	CPhoneBook
		@see	CService#sendMessage(COutgoingMessage)
		@see	CService#setPhoneBook(String)
	*/
	
	public int sendMessage(LinkedList messageList)
	{
		LinkedList outList;
		COutgoingMessage message;
		String response, pdu;
		int i, j, error;

		synchronized (_SYNC_)
		{
			if (getConnected())
			{
				outList = messageList;
				switch (operationMode)
				{
					case MODE_PDU:
						try
						{
							serialDriver.send(CATCommands.AT_PDU_MODE);
							response = serialDriver.getResponse();
							serialDriver.send(CATCommands.AT_KEEP_LINK_OPEN);
							response = serialDriver.getResponse();
							error = ERR_OK;
							System.out.println("MAURICIOCALGARO ENVIO SMS1");
							for (i = 0; i < outList.size(); i ++)
							{
								message = (COutgoingMessage) outList.get(i);
								pdu = message.getPDU(smscNumber);
								j = pdu.length();
								System.out.println("ENVIO SMS1 PDU LENGTH: " + j);
								j /= 2;
								System.out.println("ENVIO SMS1 PDU LENGTH Divido: " + j);
								if (smscNumber == null) ;
								else if (smscNumber.length() == 0) j --;
								else
								{
									j -= ((smscNumber.length() - 1) / 2);
									j -= 2;
								}
								serialDriver.send(CUtils.substituteSymbol(CATCommands.AT_SEND_MESSAGE, "\"{1}\"", "" + j));
								while (serialDriver.dataAvailable()) serialDriver.skipBytes(1);
								serialDriver.send(pdu);
								System.out.println("ENVIO SMS2");
								serialDriver.send((char) 26);
								System.out.println("ENVIO SMS2 PDU: " + pdu);
								response = serialDriver.getResponse();
								if (response.indexOf(CATCommands.AT_OK) > -1)
								{
									System.out.println("MAURICIOCALGARO ENVIO SMS3");
									message.setDispatchDate(new Date());
									deviceInfo.getStatistics().incTotalOut();
								}
								else
								{
									message.setDispatchDate(null);
									error = ERR_SEND_FAILED;
								}
							}
							return error;
						}
						catch (Exception e)
						{
							e.printStackTrace();
							setConnected(false);
							return ERR_NOT_CONNECTED;
						}
					default:
						return ERR_SEND_FAILED;
				}
			}
			else return ERR_NOT_CONNECTED;
		}
	}

	/**
		Deletes an SMS message from the GSM device memory.
		<br><br>
		<strong>Notes:</strong>
		<ul>
			<li>A deleted message cannot be recovered.</li>
		</ul>

		@param	message	a valid CIncomingMessage object, i.e. an object which is
				previously read with readMessages() from the GSM device.
		@return  One of ERR_* values.
		@see	CIncomingMessage
		@see	CService#deleteMessage(int)
	*/
	
	public int deleteMessage(CIncomingMessage message)
	{
		synchronized (_SYNC_)
		{
			return deleteMessage(message.getMemIndex());
		}
	}

	/**
		Deletes an SMS message from the GSM device memory.
		<br><br>
		<strong>Notes:</strong>
		<ul>
			<li>A deleted message cannot be recovered.</li>
			<li>It is highly recommended to use the other form of the deleteMessage()
				method.</li>
		</ul>

		@param	memIndex	the memory index of the GSM device's memory from where
				the message (if there is any message there) should be deleted.
		@return  One of ERR_* values.
		@see	CService#deleteMessage(CIncomingMessage)
	*/
	
	public int deleteMessage(int memIndex)
	{
		String response;

		synchronized (_SYNC_)
		{
			if (getConnected())
			{
				if (memIndex > 0)
				{
					try
					{
						serialDriver.send(CUtils.substituteSymbol(CATCommands.AT_DELETE_MESSAGE, "{1}", "" + memIndex));
						response = serialDriver.getResponse();
						if (response.indexOf(CATCommands.AT_OK) > -1) return ERR_OK;
						else return ERR_MESSAGE_NOT_FOUND;
					}
					catch (Exception e)
					{
						e.printStackTrace();
						return ERR_GENERIC_ERROR;
					}
				}
				else return ERR_GENERIC_ERROR;
			}
			else return ERR_NOT_CONNECTED;
		}
	}

	/**
		Virtual method, called upon receipt of a message (Asynchronous mode only!)
		<br><br>
		<strong>Notes:</strong>
		<ul>
			<li>If you plan to use jSMSEngine API in asynchronous mode, you should
				override this method, making it do your job upon message receipt.</li>
		</ul>

		@param	message the received message.
		@return  return true if you wish the message to be deleted from the GSM device's memory.
					Otherwise false.
		@see	CService#setReceiveMode(int)
	*/
	
	public boolean received(CIncomingMessage message)
	{
		return false;
	}

	public void EnvioMensajeProtocolo(CColaEntrada Cola1, String Originador, String Info, int MemIndex, int SuperID, int Recorrido, int SMSLeidos, int SMSEnviados, int SMSGrupales, int Señal,
	int Bateria, boolean Conexion) {
		Date Fecha = new Date();
		CProtocolMessage B1 = new CProtocolMessage(Fecha, Originador, Info, MemIndex, SuperID, Recorrido, SMSLeidos, SMSEnviados, SMSGrupales, Señal, Bateria, Conexion);
		Cola1.Agregar(B1);
	}

	/**
	* Checks if the message is SMS-DELIVER (incoming) or SMS-SUBMIT
	*
	* @author George Karadimas
	* @param pdu the message pdu
	* @return true if the message is SMS-DELIVER
	*/
	
	private boolean isIncomingMessage(String pdu)
	{
		String str;
		int index, i;

		str = pdu.substring(0, 2);
		i = Integer.parseInt(str, 16);
		index = (i + 1) * 2;

		str = pdu.substring(index, index + 2);
		i = Integer.parseInt(str, 16);
		if ((i & 0x0B) == 0) return true;
		else return false;
	}

	private void setConnected(boolean connected) { this.connected = connected; }
	private void setInitialized(boolean initialized) { this.initialized = initialized; }

	private String getManufacturer() throws Exception
	{
		String response;
		String whatToDiscard;

		whatToDiscard = "+" + CUtils.substituteSymbol(CUtils.substituteSymbol(CATCommands.AT_MANUFACTURER, "AT+", ""), "\r", "") + ": ";
		serialDriver.send(CATCommands.AT_MANUFACTURER);
		response = serialDriver.getResponse();
		if (response.length() == 0) response = "";
		else if (response.indexOf(CATCommands.AT_OK) > -1)
		{
			response = CUtils.substituteSymbol(response, CATCommands.AT_OK, "");
			response = CUtils.substituteSymbol(response, "\r", "");
			response = CUtils.substituteSymbol(response, "\n", "");
			response = CUtils.substituteSymbol(response, whatToDiscard, "");
			response = CUtils.substituteSymbol(response, "\"", "");
		}
		else response = DEFAULT_VALUE_NOT_REPORTED;
		return response;
	}

	private String getModel() throws Exception
	{
		String response;
		String whatToDiscard;

		whatToDiscard = "+" + CUtils.substituteSymbol(CUtils.substituteSymbol(CATCommands.AT_MODEL, "AT+", ""), "\r", "") + ": ";
		serialDriver.send(CATCommands.AT_MODEL);
		response = serialDriver.getResponse();
		if (response.length() == 0) response = "";
		else	if (response.indexOf(CATCommands.AT_OK) > -1)
		{
			response = CUtils.substituteSymbol(response, CATCommands.AT_OK, "");
			response = CUtils.substituteSymbol(response, "\r", "");
			response = CUtils.substituteSymbol(response, "\n", "");
			response = CUtils.substituteSymbol(response, whatToDiscard, "");
			response = CUtils.substituteSymbol(response, "\"", "");
		}
		else response = DEFAULT_VALUE_NOT_REPORTED;
		return response;
	}

	private String getSerialNo() throws Exception
	{
		String response;
		String whatToDiscard;

		whatToDiscard = "+" + CUtils.substituteSymbol(CUtils.substituteSymbol(CATCommands.AT_SERIALNO, "AT+", ""), "\r", "") + ": ";
		serialDriver.send(CATCommands.AT_SERIALNO);
		response = serialDriver.getResponse();
		if (response.length() == 0) response = "";
		else 	if (response.indexOf(CATCommands.AT_OK) > -1)
		{
			response = CUtils.substituteSymbol(response, CATCommands.AT_OK, "");
			response = CUtils.substituteSymbol(response, "\r", "");
			response = CUtils.substituteSymbol(response, "\n", "");
			response = CUtils.substituteSymbol(response, whatToDiscard, "");
		}
		else response = DEFAULT_VALUE_NOT_REPORTED;
		return response;
	}

	private String getImsi() throws Exception
	{
		String response;
		String whatToDiscard;

		whatToDiscard = "+" + CUtils.substituteSymbol(CUtils.substituteSymbol(CATCommands.AT_IMSI, "AT+", ""), "\r", "") + ": ";
		serialDriver.send(CATCommands.AT_IMSI);
		response = serialDriver.getResponse();
		if (response.length() == 0) response = "";
		else 	if (response.indexOf(CATCommands.AT_OK) > -1)
		{
			response = CUtils.substituteSymbol(response, CATCommands.AT_OK, "");
			response = CUtils.substituteSymbol(response, "\r", "");
			response = CUtils.substituteSymbol(response, "\n", "");
			response = CUtils.substituteSymbol(response, whatToDiscard, "");
		}
		else response = DEFAULT_VALUE_NOT_REPORTED;
		return response;
	}

	private String getSwVersion() throws Exception
	{
		String response;
		String whatToDiscard;

		whatToDiscard = "+" + CUtils.substituteSymbol(CUtils.substituteSymbol(CATCommands.AT_SOFTWARE, "AT+", ""), "\r", "") + ": ";
		serialDriver.send(CATCommands.AT_SOFTWARE);
		response = serialDriver.getResponse();
		if (response.length() == 0) response = "";
		else if (response.indexOf(CATCommands.AT_OK) > -1)
		{
			response = CUtils.substituteSymbol(response, CATCommands.AT_OK, "");
			response = CUtils.substituteSymbol(response, "\r", "");
			response = CUtils.substituteSymbol(response, "\n", "");
			response = CUtils.substituteSymbol(response, whatToDiscard, "");
			response = CUtils.substituteSymbol(response, "\"", "");
		}
		else response = DEFAULT_VALUE_NOT_REPORTED;
		return response;
	}

	private int getBatteryLevel() throws Exception
	{
		String response, level;
		StringTokenizer tokens;
		String whatToDiscard;

		whatToDiscard = "+" + CUtils.substituteSymbol(CUtils.substituteSymbol(CATCommands.AT_BATTERY, "AT+", ""), "\r", "") + ": ";
		serialDriver.send(CATCommands.AT_BATTERY);
		response = serialDriver.getResponse();
		if (response.length() == 0) level = "0";
		else 	if (response.indexOf(CATCommands.AT_OK) > -1)
		{
			response = CUtils.substituteSymbol(response, CATCommands.AT_OK, "");
			response = CUtils.substituteSymbol(response, "\r", "");
			response = CUtils.substituteSymbol(response, "\n", "");
			response = CUtils.substituteSymbol(response, whatToDiscard, "");
			tokens = new StringTokenizer(response, ":,");
			tokens.nextToken();
			level = tokens.nextToken().trim();
		}
		else level = "0";
		return (Integer.parseInt(level));
	}

	private int getSignalLevel() throws Exception
	{
		String response, level;
		StringTokenizer tokens;
		String whatToDiscard;

		whatToDiscard = "+" + CUtils.substituteSymbol(CUtils.substituteSymbol(CATCommands.AT_SIGNAL, "AT+", ""), "\r", "") + ": ";
		serialDriver.send(CATCommands.AT_SIGNAL);
		response = serialDriver.getResponse();
		if (response.length() == 0) level = "0";
		else 	if (response.indexOf(CATCommands.AT_OK) > -1)
		{
			response = CUtils.substituteSymbol(response, CATCommands.AT_OK, "");
			response = CUtils.substituteSymbol(response, "\r", "");
			response = CUtils.substituteSymbol(response, "\n", "");
			response = CUtils.substituteSymbol(response, whatToDiscard, "");
			tokens = new StringTokenizer(response, ":,");
			tokens.nextToken();
			level = tokens.nextToken().trim();
		}
		else level = "0";
		return (Integer.parseInt(level) * 100 / 31);
	}

	private class CReceiveThread extends Thread
	{
		public void run()
		{
			LinkedList<CIncomingMessage> messageList;

			messageList = new LinkedList<CIncomingMessage>();
			//while (true)
			//Maurix
			int i=0;
			while (i==0)
			//Maurix
			{
				try { sleep(10000); } catch (Exception e) {}
				synchronized (_SYNC_)
				{
					if ((getConnected()) && (getReceiveMode() == RECEIVE_MODE_ASYNC))
					{
						messageList.clear();
						//Maurix
						//readMessages(messageList, CIncomingMessage.CLASS_ALL);
						//Maurix
						System.out.println("largo messageList = " + messageList.size());
						//System.out.println("MessageList = " + messageList);
						//Maurix
						for (i = 0; i < messageList.size(); i ++)
						{
							CIncomingMessage message = (CIncomingMessage) messageList.get(i);
							//Maurix
							System.out.println("Cursor = " + i);
							//Maurix
							//Maurix//if (received(message)) deleteMessage(message);
							if (received(message)) deleteMessage(message);
						}
					}
				}
			}
		}
	}
	
	public static void main(String[] args)
	{
		System.out.println("jSMSEngine API.");
	}
}
