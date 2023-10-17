package com.indovinanumero;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        try{
            ServerSocket server = new ServerSocket(3000);
            Socket s = server.accept();
            System.out.println("Connessione effettuata.");
            BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
            DataOutputStream out = new DataOutputStream(s.getOutputStream());
            boolean indovina = true;
            int number = (int)(Math.random()*100+1);
            System.out.print("il numero e': " + number);
            int risposta;
            int cont = 0;
            while(indovina){
                cont++;
                risposta = Integer.parseInt(in.readLine());
                if(risposta == number ){
                    out.writeBytes(3 + "\n");
                    out.writeBytes(cont + "\n");
                    indovina = false;
                }else{
                    if(risposta > number){
                        out.writeBytes(2 + "\n");
                    }else{
                        out.writeBytes(1 + "\n");
                    }
                }
            }
            s.close();
            System.out.println("Connessione chiusa.");
            server.close();
            

        }catch (Exception e){
            System.out.println(e.getMessage());
            System.out.println("Errore durante l'instanza.");
            System.exit(1);
        }
    }
}
