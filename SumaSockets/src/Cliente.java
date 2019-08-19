import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class Cliente {
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	// Los argumentos proporcionan el mensaje y el nombre del servidor
	  public static void main(String args[]) {
		  
		  final int puertoServidor = 5000;//Puerto elegido
		  byte[] enviarDatos = new byte [1024];//Array de bytes para enviar
		  byte[] recibirDatos = new byte [1024];//Array de bytes para recibir

	    try {
	      InetAddress direccion = InetAddress.getByName("localhost");//direccion local para saber la id del cliente
	      DatagramSocket socketUDP = new DatagramSocket();//No es necesario el puerto, puesto que lo asignara automaticamente el sistema
	      
	      //Capturamos los numeros del cliente por Consola
	      System.out.println("Ingrese el Primer numero");
	      String numeroX = br.readLine();
	      System.out.println("Ingrese el Segundo numero");
	      String numeroY = br.readLine();
	      
	      br.close();
	      
	      String mensaje = numeroX+","+numeroY+",";//Guardamos los numeros en un String unido
	      
	      enviarDatos = mensaje.getBytes();//Convertimos el mensaje en bytes
	      
	      // Construimos un datagrama para enviar el mensaje al servidor
	      DatagramPacket enviarPaquete =
	        new DatagramPacket(enviarDatos, enviarDatos.length, direccion,
	                           puertoServidor);

	      // Enviamos el datagrama
	      socketUDP.send(enviarPaquete);

	      //Construimos el datagrama para recibir el paquete
	      DatagramPacket recibirPaquete = new DatagramPacket(recibirDatos, recibirDatos.length);
	      
	      //Recibimos lo que llega desde el servidor
	      socketUDP.receive(recibirPaquete);
	      
	      mensaje = new String (recibirPaquete.getData());
	      System.out.print("La suma es: ");
	      System.out.println(mensaje);
	      
	      socketUDP.close();
	      
	      
	    } catch (SocketException e) {
	      System.out.println("Socket: " + e.getMessage());
	    } catch (IOException e) {
	      System.out.println("IO: " + e.getMessage());
	    }
	  }
	  
	  
}
