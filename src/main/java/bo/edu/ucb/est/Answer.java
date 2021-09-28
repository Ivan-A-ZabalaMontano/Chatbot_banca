package bo.edu.ucb.est;

import bo.edu.ucb.est.banca.Cuenta;
import bo.edu.ucb.est.utils.Mensaje;

public class Answer {
	
	private Mensaje mensaje;
	
	private Usuario user;
	public Answer(Usuario user)
	{
		this.user=user;
		mensaje= new Mensaje();
	}
	
	public Mensaje receiveAnswer(String input)
	{
		Mensaje mensaje=user.getMensaje();
		System.out.println("Nombre: "+user.getNombre());
		System.out.println("Pin: "+user.getPin());
		System.out.println("Ultimo mensaje recibido: "+user.getLastMSG());
		System.out.println("Mensaje: "+user.getMensaje().getProperties());
		switch(user.getStatus())
		{
		case "Nuevo":
		
			
			if(user.getNombre().equals("") && user.getLastMSG().equals(""))
			{
				System.out.println("Lugar nombre");
				defineRegisterName();
			}
			else 
			{
				if(!user.getLastMSG().equals("") && mensaje.validarMensaje(user.getLastMSG(), input)!=-1 && user.getNombre().equals(""))
				{
					System.out.println("Lugar Pin");
					user.setNombre(input);
				}
				else if(mensaje.validarMensaje(user.getLastMSG(), input)!=-1)
				{
					user.setPin(input);
					System.out.println("Registrado correctamente");
				}
				defineRegisterPin();
			}

		;break;
		case "Registrado":
			
		;break;
		}
		user.setLastMSG(this.mensaje.getMensajes().get(this.mensaje.getKey()-1));
		user.setMensaje(this.mensaje);
		return getMensaje();
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
		
		user.setLastMSG("¿Cúal es tu nombre completo?");
	
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
	public void defineCrearCuenta()
	{
		mensaje.agregarMensaje("Seleccione la moneda: "
				+ "\n1. Dólares."
				+ "\n2. Bolivianos","Integer");
		String moneda=user.getCuenta(user.getNroCuenta()).getMoneda();
		double saldo=user.getCuenta(user.getNroCuenta()).getSaldo();
		String nro=user.getCuenta(user.getNroCuenta()).getNroCuenta();
		mensaje.agregarMensaje("Se le ha creado una cuenta en "+moneda+"con saldo"+saldo+", cuyo número es "+nro);
	}
	public void defineVerSaldo() 
	{
		
	}
	public void defineRetirarDinero()
	{
		
	}
	public void defineDepositarDinero()
	{
		
	}
	public void defineVerCuentas()
	{
		int n=user.getNroCuenta();
		for(int i=1;i<n;i++)
		{
			mensaje.agregarMensaje(i+"");
		}
	}
	public void defineSalir() 
	{
		
	}
}
