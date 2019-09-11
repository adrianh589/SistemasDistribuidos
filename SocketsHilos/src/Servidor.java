/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author englinx
 */
public class Servidor {
    //initialize socket and input stream 
    private Socket          socket   = null; 
    private ServerSocket    server   = null; 
    private DataInputStream in       = null; 
    private DataOutputStream out     = null;
  
    // constructor with port 
    public Servidor(int port) 
    { 
        // starts server and waits for a connection 
        try
        { 
            server = new ServerSocket(port); 
            System.out.println("Server started"); 
  
            System.out.println("Waiting for a client ..."); 
  
            while(true) {//Nos quedamos a la escucha de varios clientes
            socket = server.accept(); 
            System.out.println("Client accepted"); 
  
            // takes input from the client socket 
            in = new DataInputStream(new BufferedInputStream(socket.getInputStream())); 
            
            HiloCliente hilo = new HiloCliente(socket, in, out);
            hilo.start();
 
            }
        } 
        catch(IOException i) 
        { 
            System.out.println(i); 
        } 
    } 
    
    public static void main(String args[]) 
    { 
        Servidor server = new Servidor(5000); 
    } 
    
}
