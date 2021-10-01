package bo.edu.ucb.est;

import java.util.Random;

import bo.edu.ucb.est.banca.Cuenta;
import bo.edu.ucb.est.utils.Mensaje;

public class Answer {
	
	private Mensaje mensaje;
	
	private Usuario user;
	private  UserInteraction interaction;
	public Answer(Usuario user)
	{
		this.user=user;
		mensaje= new Mensaje();
		this.interaction=user.getInteraction();
	}
	
	public void receiveAnswer(String input)
	{
		Mensaje mensaje=interaction.getMessage();
		String send=interaction.getReceived();
		switch(user.getStatus())
		{
		case "Nuevo":
			if(user.getNombre().equals("") && send.equals(""))
			{
				System.out.println("Lugar nombre");
				defineRegisterName();
			}
			else 
			{
				if(!send.equals("") && mensaje.validarMensaje(send, input)!=-1 && user.getNombre().equals(""))
				{
					System.out.println("Lugar Pin");
					user.setNombre(input);
				}
				else if(mensaje.validarMensaje(send, input)!=-1)
				{
					if(input.length()==4)
					{
						user.setPin(input);
						System.out.println("Registrado correctamente");
					}
				}
				defineRegisterPin();
			}

		;break;
		case "Registrado":
			if(mensaje.validarMensaje(send, input)!=-1 && send.contains("Solo por seguridad"))
			{
				if(user.getPin().equals(input))
				{
					defineMenu();
				}
				else
				{
					defineWelcome();
				}
			}
			else if(send.contains("Elige"))
			{
				if(input.equals("1") || input.equals("2") || input.equals("3"))
				{
					if(user.getAccounts().size()==0)
					{
						defineWithoutAccount();
					}
					else
					{
						user.getInteraction().getSend().add(0, input);
						defineShowAccounts();
					}
				}
				else if(input.equals("4"))
				{
					defineSelectCoin();
				}
				else if(input.equals("5"))
				{
					defineWelcome();
				}
				else
				{
					defineMenu();
				}
			}
			else if(send.contains("moneda"))
			{
				int option=mensaje.validarMensaje(send, input);
				if(send.contains(input))
				{
					if(option==2) user.getInteraction().addSend("Bolivianos");
					else if(option==1) user.getInteraction().addSend("Dólares");
					defineSelectType();
					
				}
				else
				{
					mensaje.agregarMensaje("Opcion incorrecta");
					defineSelectCoin();
				}		
			}
			else if(send.contains("tipo"))
			{
				int option=mensaje.validarMensaje(send, input);
				if(send.contains(input))
				{
					if(option==1)user.getInteraction().addSend("Cuenta Corriente");
					else if(option==2)user.getInteraction().addSend("Caja de Ahorros");
					defineAccountCreated();
					
				}
				else
				{
					mensaje.agregarMensaje("Opcion incorrecta");
					defineSelectType();
				}
			}
			else if(send.contains("Seleccione una Cuenta"))
			{
				System.out.println("Selecciono una cuenta: ");
				if(send.contains(input) && mensaje.validarMensaje(send, input)!=-1 
						&& mensaje.validarMensaje(send,input)>0 
						&& mensaje.validarMensaje(send, input)<=user.getAccounts().size())
				{
					System.out.println("Opcion: "+user.getInteraction().getSend().get(0));
					user.getInteraction().addSend(input);
					if(user.getInteraction().getSend().get(0).equals("1"))
					{
						System.out.println("Ver cuenta");
						defineShowInfo();
					}
					else if(user.getInteraction().getSend().get(0).equals("2")) defineWithdraw();
					else if(user.getInteraction().getSend().get(0).equals("3")) defineDeposit();
					user.getInteraction().clearSend();
					
				}else
				{
					defineShowAccounts();
				}
			}
			else
			{
				defineWelcome();
			}
		;break;
		}
		user.getInteraction().setReceived(this.mensaje.getMensajes().get(this.mensaje.getKey()-1));
		user.getInteraction().setMessage(this.mensaje);
		System.out.println("Nombre: "+user.getNombre());
		System.out.println("Pin: "+user.getPin());
		System.out.println("Ultimo mensaje recibido: "+interaction.getReceived());
		System.out.println("Mensaje: "+interaction.getMessage().getProperties());
	}
	public Answer getAnswer()
	{
		return this;
	}
	public Mensaje getMensaje()
	{
		return this.mensaje;
	}
	public void defineRegisterName()
	{
		mensaje.agregarMensaje("Bienvenido al Banco de los Dioses");
		mensaje.agregarMensaje("He notado que aún no eres cliente, procedamos a registrarte.");
		mensaje.agregarMensaje("¿Cúal es tu nombre completo?","String");
	}
	public void defineWithoutAccount()
	{
		mensaje.agregarMensaje("Usted no tiene cuentas, cree una primero");
		defineMenu();
	}
	public void defineRegisterPin()
	{
		if(user.getPin().equals(""))
		{
			mensaje.agregarMensaje("Por favor elige un PIN de seguridad, este te será requerido cada que ingreses al sistema.","Integer");
		}
		else
		{
			mensaje.agregarMensaje("Te hemos registrado correctamente.");
			user.setStatus("Registrado");
			defineWelcome();
		}
		
	
	}
	public void defineWelcome()
	{
		mensaje.agregarMensaje("Hola de nuevo "+user.getNombre());
		mensaje.agregarMensaje("Solo por seguridad ¿Cuál es tu PIN?","Integer");
	}
	public void defineMenu() 
	{
		mensaje.agregarMensaje("Bienvenido.");
		mensaje.agregarMensaje("Elige una opción: "
						+ "\n1. Ver Saldo."
						+ "\n2. Retirar dinero."
						+ "\n3. Depositar dinero."
						+ "\n4. Crear cuenta."
						+ "\n5. Salir.","Integer");
	}
	public void defineSelectCoin()
	{
		mensaje.agregarMensaje("Seleccione la moneda: "
				+ "\n1. Dólares."
				+ "\n2. Bolivianos","Integer");
	}
	public void defineSelectType() 
	{
		mensaje.agregarMensaje("Seleccione el tipo de cuenta: "
				+ "\n1. Cuenta Corriente."
				+ "\n2. Caja de Ahorros","Integer");
	}
	public void defineAccountCreated()
	{
		String moneda=user.getInteraction().getSend().get(0);
		String tipo=user.getInteraction().getSend().get(1);
		String nro="";
		if(tipo.equals("Caja de Ahorros"))
		{
			nro+="301-";
		}
		else if(tipo.equals("Cuenta Corriente"))
		{
			nro+="101-";
		}
		if(moneda.equals("Dólares"))
		{
			nro+="200-";
		}
		else if(moneda.equals("Bolivianos"))
		{
			nro+="104-";
		}
		Random rand= new Random();
		int rng= rand.nextInt(500);
		nro+=rng;
		user.agregarCuenta(new Cuenta(moneda,nro,tipo,0.0));
		mensaje.agregarMensaje("Se le ha creado una cuenta: "
				+ "\nNro de Cuenta: "+nro
				+"\nMoneda: "+moneda
				+ "\nTipo: "+tipo
				+ "\nSaldo: 0.0");
		defineMenu();
		user.getInteraction().clearSend();
	}
	public void defineShowInfo() 
	{
		
		int n= Integer.parseInt(user.getInteraction().getSend().get(1));
		n-=1;
		System.out.println("Definir cuenta info: "+n);
		Cuenta cuenta= user.getAccounts().get(n);
		String msg="Nro cuenta: " +cuenta.getNroCuenta()
				+ "\nTipo: "+cuenta.getTipo()
				+ "\nMoneda: "+cuenta.getMoneda()
				+ "\nSaldo disponible: "+cuenta.getSaldo();
		mensaje.agregarMensaje(msg);
		defineMenu();
	}
	public void defineWithdraw()
	{
		
	}
	public void defineDeposit()
	{
		
	}
	public void defineShowAccounts()
	{
		String msg="Seleccione una Cuenta: ";
		for(int i=0;i<user.getAccounts().size();i++) 
		{
			Cuenta account=user.getAccounts().get(i);
			msg+="\n";
			msg+=(i+1)+". "+account.getTipo()+"-"+account.getNroCuenta();
		}
		mensaje.agregarMensaje(msg,"Integer");
	}
}
