import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.io.DataOutputStream;
import java.io.IOException;

public class Servidor {
	
	public static void main(String[] args) {
		
		final int puerto = 5000;
		byte[] recibirDatos = new byte [1024];
		byte[] enviarDatos;
		
		try {
			System.out.println("Iniciado el servidor UDP");
			DatagramSocket socketUDP = new DatagramSocket(puerto);//Se crea el socket
			
			//Creamos la peticion, lo que le llegara al servidor
			DatagramPacket peticion = new DatagramPacket(recibirDatos, recibirDatos.length);
			
			//Se recibe el datagrama
			socketUDP.receive(peticion);
			System.out.println("Recibo la informacion del cliente");
			//Recibimos el mensaje del cliente
			String numerosCliente = new String(peticion.getData());
			
			//Calculamos la suma de los numeros
			enviarDatos = sumar(numerosCliente).getBytes();
			
			System.out.println("la suma desde el servidor es: "+sumar(numerosCliente));
			
			int puertoCliente = peticion.getPort(); //Obtenemos el puerto del cliente
			InetAddress direccion = peticion.getAddress(); //Obtenemos la direccion del cliente
			
			//HAcemos la respuesta al respectivo cliente segun su puerto y direccion
			DatagramPacket respuesta = new DatagramPacket(enviarDatos, enviarDatos.length, direccion, puertoCliente);
			
			//Enviamos la respuesta
			socketUDP.send(respuesta);
			
		} catch (SocketException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	/**
	   * Metodo que hace una suma simple con 2 numeros
	   * @param x Primer numero
	   * @param y Segundo numero
	   * @return Suma total
	   */
	  static String sumar(String numeros) {
		  String numeroString[] = numeros.split(",");
		  int resultado = Integer.parseInt(numeroString[0]) + Integer.parseInt(numeroString[1]);
		  String enviar = Integer.toString(resultado);
		  return enviar;
	  }

}
