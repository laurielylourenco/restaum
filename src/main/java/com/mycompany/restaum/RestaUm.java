/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package com.mycompany.restaum;

import java.util.Iterator;
import java.util.Objects;
import java.util.Scanner;

/**
 *
 * @author lauriely
 */
public class RestaUm {
    
    public static String[] entrada(String jogada){
       
        
        Integer begin = jogada.indexOf("(");
        Integer end = jogada.indexOf(")");
        String  stringSeparada = jogada.substring(begin+1,end);
        
        return stringSeparada.split(",");
    }
    
    public static Boolean analiseJogo(int[][] matriz){
        boolean rtn = true;
        for (int l = 0; l < 7; l++)  {  
                for (int c = 0; c < 7; c++)     { 
                   
                    if(matriz[l][c] == 1){
                       rtn = false;
                    }
                   
                }  
            }
     
        return rtn;
    }
    
    public static int[][] matrizInicial(int[][] matriz){
    
        for (int l = 0; l < 7; l++)  {  
            for (int c = 0; c < 7; c++)     { 
                    matriz[l][c] = 1;
                    if( (1 >= l && 1 >= c) || (1 >= l && c >= 5)){
                       matriz[l][c] = -1;
                    }
                   if((l >= 5 && c >= 5)  || (l >= 5 && 1 >= c)){

                       matriz[l][c] = -1;
                    }
                }  
        }
        matriz[3][3] = 0;
    
        return matriz;
    }
    
    public static Boolean validaJogada(String [] jogada){
        
        boolean valido = true;
       
        for (String v : jogada) {
            if (Integer.parseInt(v) < 0) {
                valido = false;
            }
        }
        
        
        return valido;
    }
    
    public static void main(String[] args) {
        
        
        Scanner teclado = new Scanner(System.in);
        System.out.print("Comece o jogo ([linha da peça], [coluna da peça], [linha do espaço em branco], [coluna do espaço em branco]). ");
        System.out.println(" ");
        String jogada = teclado.nextLine();
        
        if(!jogada.equalsIgnoreCase("sair")){
        
            String [] stringSeparada1 = entrada(jogada);
      
            Integer lp = Integer.parseInt(stringSeparada1[0]);
            Integer cp = Integer.parseInt(stringSeparada1[1]);
            Integer leb = Integer.parseInt(stringSeparada1[2]);
            Integer ceb = Integer.parseInt(stringSeparada1[3]);
            
            
            int[][] matriz = new int[7][7];
            matriz = matrizInicial(matriz);
            
            do {
               
                if(matriz[lp][cp] != -1 && matriz[leb][ceb] == 0){
                    
                    matriz[lp][cp] = 0;
                    matriz[leb][ceb] = 1;
                   
                    
                    if((Objects.equals(lp, leb)) && (cp < ceb)){
                        cp = cp+1;
                    }
                    
                    if( (Objects.equals(lp, leb)) && (cp > ceb) ){
                         cp = ceb+1;
                    }
                    if( (Objects.equals(cp, ceb)) && (lp < leb) ){
                        lp = lp+1;
                    }
                    if( (Objects.equals(cp, ceb)) && (lp > leb) ){
                        lp = leb+1;
                    } 
                    
                    matriz[lp][cp] = 0;
                   
                }
                
                
                for (int l = 0; l < 7; l++)  {  
                    for (int c = 0; c < 7; c++)     { 
                        if(matriz[l][c] != -1){
                            System.out.print(matriz[l][c] + " "); //imprime caracter a caracter 
                        }else{
                             System.out.print("  ");
                        }
                        
                    }  
                     System.out.println(" "); //muda de linha
                }
                
                
                if(analiseJogo(matriz)){
                    
                    System.out.print("vc venceu !!!");
                    System.out.println(" ");
                    System.out.print("Deseja jogar de novo ?");
                    System.out.println(" ");
                    jogada = teclado.nextLine();
                    
                    if(jogada.equalsIgnoreCase("S")){
                        
                        matriz = matrizInicial(matriz); 
                        System.out.print("Para continuar ([linha da peça], [coluna da peça], [linha do espaço em branco], [coluna do espaço em branco]). ");
                        System.out.println(" ");
                        jogada = teclado.nextLine();
                        
                    }else{
                        break;
                    }
                    
                    
                }else{
                    System.out.print("Para continuar ([linha da peça], [coluna da peça], [linha do espaço em branco], [coluna do espaço em branco]). ");
                    System.out.println(" ");
                    System.out.print("Para reiniciar o jogo o usuário deve digitar “reiniciar”.");
                    System.out.println(" ");
                    System.out.print("Para sair do jogo o usuário deve digitar “sair”.");
                    System.out.println(" ");
                    
                    jogada = teclado.nextLine();

                     if(jogada.equalsIgnoreCase("reiniciar")){

                       matriz = matrizInicial(matriz);
                       System.out.print("Para continuar ([linha da peça], [coluna da peça], [linha do espaço em branco], [coluna do espaço em branco]). ");
                        System.out.println(" ");
                       jogada = teclado.nextLine();
                        stringSeparada1 = entrada(jogada);
                            lp = Integer.parseInt(stringSeparada1[0]);
                            cp = Integer.parseInt(stringSeparada1[1]);
                            leb = Integer.parseInt(stringSeparada1[2]);
                            ceb = Integer.parseInt(stringSeparada1[3]);
                    
                    }
                     
                    if(!jogada.equalsIgnoreCase("sair") && !jogada.equalsIgnoreCase("reiniciar")){
                         stringSeparada1 = entrada(jogada);
                            lp = Integer.parseInt(stringSeparada1[0]);
                            cp = Integer.parseInt(stringSeparada1[1]);
                            leb = Integer.parseInt(stringSeparada1[2]);
                            ceb = Integer.parseInt(stringSeparada1[3]);
                            
                    }
                    
                   
                }
                

            } while (!jogada.equalsIgnoreCase("sair"));      
        }
    }
}
