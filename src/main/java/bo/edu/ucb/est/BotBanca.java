package bo.edu.ucb.est;

import java.util.HashMap;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class BotBanca extends TelegramLongPollingBot{

	
	private HashMap<Long,Usuario> usuarios;
	private Mensaje m1;
	
	
	public BotBanca()
	{
		usuarios= new HashMap<Long,Usuario>();
		m1= new Mensaje();
		agregarPrimerosMensajes();
	}
	
	
	@Override
	public void onUpdateReceived(Update update) {
		// TODO Auto-generated method stub
		System.out.println("Nuevo mensaje");
		if(update.hasMessage())
		{
			 // Creo el objeto para enviar un mensaje
            SendMessage message = new SendMessage();
            //Define a quien le vamos a enviar el mensaje
            message.setChatId(update.getMessage().getChatId().toString()); 
            // Obtiene el mensaje del usuario
            String texto=update.getMessage().getText();
            enviarMensaje(m1,message);
		}
	}
	public void enviarMensaje(Mensaje m, SendMessage enviar)
	{
		for(String mensaje: m.getMensajes())
		{
			enviar.setText(mensaje);
			try
			{
				execute(enviar);
			}catch(TelegramApiException e)
			{
				e.printStackTrace();
			}
		}
	}
	public void agregarPrimerosMensajes()
	{
		m1.agregarMensaje("Bienvenido al Banco de los Dioses");
		m1.agregarMensaje("He notado que aún no eres cliente, procedamos a registrarte.");
		m1.agregarMensaje("¿Cúal es tu nombre completo?");
	}
	
	@Override
	public String getBotUsername() {
		// TODO Auto-generated method stub
		return "ISUMA_BOT";
	}

	@Override
	public String getBotToken() {
		// TODO Auto-generated method stub
		return "1970441371:AAGIEE7oMuWkKpjykhbhY_RobZs1SSIwFOI";
	}

}
