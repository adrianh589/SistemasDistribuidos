package Servidor;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author englinx
 */
public class Servidor {
    //initialize socket and input stream 
    private static Socket          socket   = null; 
    private static ServerSocket    server   = null; 
    private static DataInputStream in       =  null; 
    
    public static void main(String args[]) 
    { 
        Servidor server = new Servidor(5000); 
    } 
  
    // constructor with port 
    public Servidor(int port) 
    { 
        //starts server and waits for a connection 
    	iniciarServidor(port);
 
        String line = ""; 
  
        // reads message from client until "Over" is sent 
        over(line);
  
        // close connection 
        cerrarConexion(); 
    }
    
    /**
     * Metodo para iniciar el servidor
     * @param port
     */
    public static void iniciarServidor(int port) {
    	
    	// starts server and waits for a connection 
        try
        { 
            server = new ServerSocket(port); 
            System.out.println("Server started"); 
  
            System.out.println("Waiting for a client ..."); 
  
            socket = server.accept(); 
            System.out.println("Client accepted"); 
  
            // takes input from the client socket 
            in = new DataInputStream(new BufferedInputStream(socket.getInputStream())); 
    
    
        }catch(IOException i) 
        { 
            System.out.println(i); 
        } 
    }
    
    /**
     * Metodo para verificar la entrada del cliente
     * @param line
     */
    public static void over(String line) {
    	// reads message from client until "Over" is sent 
        while (!line.equals("Over")) 
        { 
            try
            { 
                line = in.readUTF(); 
                System.out.println(line); 

            } 
            catch(IOException i) 
            { 
                System.out.println(i); 
            } 
        } 
    }
    
    /**
     * Metodo para cerrar la conexion
     */
    public static void cerrarConexion() {
    	System.out.println("Closing connection");
    	try {
			socket.close();
			in.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
    }
    
}
