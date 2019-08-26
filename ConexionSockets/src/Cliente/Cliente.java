package Cliente;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 *
 * @author englinx
 */
public class Cliente {
    // initialize socket and input output streams 
    private static Socket socket            = null; 
    private static DataInputStream  input   = null; 
    private static DataOutputStream out     = null; 
    
    public static void main(String args[]) 
    { 
        Cliente client = new Cliente("127.0.0.1", 5000); 
    } 
  
    // constructor to put ip address and port 
    public Cliente(String address, int port) 
    { 
    	// establish a connection
    	conexion(address,port);
    	
        // string to read message from input 
        String line = ""; 
  
        //keep reading until "Over" is input 
        over(line);
  
        // close the connection 
        cerrarConexion(); 
    }
    
    
    
    /**
     * Metodo para crear la conexion
     * @param address
     * @param port
     */
    public static void conexion(String address, int port) {
    	// establish a connection 
        try
        { 
            socket = new Socket(address, port); 
            System.out.println("Connected"); 
  
            // takes input from terminal 
            input  = new DataInputStream(System.in); 
  
            // sends output to the socket 
            out    = new DataOutputStream(socket.getOutputStream()); 
        } 
        catch(UnknownHostException u) 
        { 
            System.out.println(u); 
        } 
        catch(IOException i) 
        { 
            System.out.println(i); 
        } 
    	
    }
    
    /**
     * Metodo para terminar la conexion manualmente
     * @param line
     */
    public static void over(String line) {
    	// keep reading until "Over" is input 
        while (!line.equals("Over")) 
        { 
            try
            { 
                line = input.readLine(); 
                out.writeUTF(line); 
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
    	try
        { 
            input.close(); 
            out.close(); 
            socket.close(); 
        } 
        catch(IOException i) 
        { 
            System.out.println(i); 
        }
    }
    
}
