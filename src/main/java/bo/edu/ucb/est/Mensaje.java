package bo.edu.ucb.est;

import java.util.ArrayList;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class Mensaje {
	
	private ArrayList<String> mensajes;
	
	public Mensaje()
	{
		mensajes= new ArrayList<String>();
	}
	public ArrayList<String> getMensajes()
	{
		return this.mensajes;
	}
	
	
	public void agregarMensaje(String mensaje)
	{
		mensajes.add(mensaje);
	}
	public boolean recibido()
	{
		return true;
	}

}
