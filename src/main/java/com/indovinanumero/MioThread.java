package com.indovinanumero;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;

public class MioThread extends Thread{
    private Socket s;
    private Integer number;

    public MioThread(Socket s, Integer i){
        this.s = s;
        this.number = i;
    }

    public void run(){
        try{
            BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
            DataOutputStream out = new DataOutputStream(s.getOutputStream());
            boolean indovina = true;
            
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
            System.out.println("\nConnessione chiusa.");
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
}
