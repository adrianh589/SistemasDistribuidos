import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class HiloCliente extends Thread{
	
	private Socket socket;
	private DataInputStream in       =  null; 
	private DataOutputStream out     = null;
	
	public HiloCliente (Socket socket, DataInputStream in, DataOutputStream out){
		this.socket = socket;
		this.in = in;
		this.out = out;
	}

	public void run(){
		System.out.println("New thread");
		try {
			String mensaje = in.readUTF();
			System.out.println(mensaje);
			
		}
		catch (Exception e) {
			// TODO: handle exception
		}
		
		
	}
	
	
}


