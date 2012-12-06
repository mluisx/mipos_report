//Prueba de Semaforo PIOPIO

import java.io.*;

public class Piopio {
	 // 1 - Variables
	 public static Primero Recibe;
	 public static Segundo Proceso;
	 public static Tercero Envia;
	 public static Piopio Todo;
	 // 1 - Variables
     public class Primero extends Thread {
    	 int valor = 0;
    	 int valores = 0;
    	 public int Set(int i, int j){
    	      valor = i;
    	      valores = j;
    	      return 0;
    	 }
    	 public int GetValor() {
              return valor;}
    	 public int GetValores() {
             return valores;}
    	 public void run(){
    	 while (valores==0){
    		   if (valor==0){
    		        System.out.println("Primer Thread ejecutandose");
    		        try { sleep(3000); } catch (Exception e) {}
    		        }
    		   else {
    			    try { sleep(5000); } catch (Exception e) {}
    		        }
    	       }
	      }
     }
     public class Segundo extends Thread {
    	 int valor = 0;
    	 int valores = 0;
    	 public int GetVal() {
             return valor;}
    	 public int Set(int i, int j) {
   	         valor = i;
   	         valores = j;
   	         return 0;
    	     }
    	 public void run(){
    	 int i=0;
    	 int j=0;
    	 while (j==0){
         //System.out.println("Estado Thread Proceso: " + Proceso.getState());
    	 this.suspend();
         //System.out.println("Estado Thread Proceso: " + Proceso.getState());
    	 while (i==0){
    		   System.out.println("Segundo Thread ejecutandose");
    		   try { sleep(5000); } catch (Exception e) {}
    		   valor = valor + 1; 
    	       if (valor == 2) {
    	            i=1;
    	            System.out.println("Finaliza Proceso");
    	            Envia.resume();
    	            System.out.println("Termino de procesar datos, envio SMS");}
    	 	   }
    	 System.out.println("2 - Proceso Pausado");
    	 i=0;
	           }
          }
     }     	 
     public class Tercero extends Thread {
    	  int valor = 0;
          public int GetV() {
              return valor;}
    	  public void run(){
    	  int i=0,j=0;
    	  while (j==0){
               //System.out.println("Estado Thread Proceso: " + Proceso.getState());
    		   this.suspend();
               //System.out.println("Estado Thread Proceso: " + Proceso.getState());
    	       while (i==0){
    	    	    System.out.println("Tercer Thread ejecutandose - Envio SMS");
    	    	    try { sleep(8000); } catch (Exception e) {}
    	    	    i=1;
    	    	    System.out.println("Envio Correcto - Envio SMS");
    	    	    Recibe.Set(0,0);
    	    	    System.out.println("Termino de enviar datos, escucha SMS");
    	    	    Proceso.Set(0,0);
    	            }
    	       System.out.println("3 - Envia Pausado");
    	       i=0;
    	       }
          }
     }
     // 2 - Constructor
     public Piopio() {
     Recibe = new Primero();	 
     Proceso = new Segundo();
     Envia = new Tercero();
     }
	 // 2 - Constructor
     public static void main(String[] args){
    	  System.out.println("Prueba de semaforo");
    	  Todo = new Piopio();
    	  Proceso.start();
    	  System.out.println("2 - Proceso ON");
    	  Envia.start();
    	  System.out.println("3 - Envio ON");
          if ((!Recibe.isAlive())){
               Recibe.start();
               System.out.println("1 - Recibe ON");
          	   Recibe.Set(0,0);
               }
          int i=0;
          int x=0;
     	  String Lectura = "0";
     	  BufferedReader Teclado = new BufferedReader(new InputStreamReader(System.in));
          while (i==0){
               //if (Recibe.GetValor() == 0){
        	   System.out.println("Recibio mensajes de texto: 1 - SI , 2 - No");
   		       try { Lectura = Teclado.readLine(); }
   		       catch (java.io.IOException e) {
   		            System.out.println("Error de entrada de datos");
   		       //     }
               }
    		   System.out.println("Respuesta: " + Lectura);
    		   x = Integer.parseInt(Lectura);
    		   if (Recibe.GetValor() == 0 && x == 1) {
                    Recibe.Set(1,0);
                    System.out.println("Thread IN Interrumpido");
                    //if (!Proceso.isAlive()) {
                    //System.out.println("Thread Proceso no esta vivo");
                    System.out.println("Estado Thread Proceso: " + Proceso.getState());
                    Proceso.resume();
                    System.out.println("Estado Thread Proceso: " + Proceso.getState());
                    //}
                    //else {
                    //System.out.println("Thread Proceso Vivo");
                    //}                
                    System.out.println("Procesando datos");
                    }
    		   /*if (Recibe.GetValor() == 1 && Proceso.GetVal() == 2){
    			    System.out.println("Termino de procesar datos, envio SMS");
    			    //
    			    Proceso.Set(3,0);
    			    }*/
    		   /*if (Proceso.GetVal() == 3 && !Envia.isAlive()){
    		        Recibe.Set(0,0);
    		        System.out.println("Termino de enviar datos, escucha SMS");
    		        Proceso.Set(0,0);
    		        }*/
               }
          }
     }
