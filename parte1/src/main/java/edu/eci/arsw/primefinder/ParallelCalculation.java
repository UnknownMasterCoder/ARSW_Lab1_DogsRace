/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.primefinder;

import java.util.List;

/**
 *
 * @author 2099190
 */
public class ParallelCalculation {
    
    private final int A;
    private final int B;
    private int digits;
    private double width;
    private final int[] array;

    public ParallelCalculation(int a, int b) {
        this.A = a;
        this.B = b;
        this.array = new int[3];
        digits = B-A;
        //calcular tamaño cada hilo
        int div = (B-A) / 3;
        int mod = (B-A) % 3;
        int suma = div;
        for(int i = 0; i < 3 ; i++){
            if(i+1 == 3){
                this.array[i]=suma+mod;
            }else{
                this.array[i]=suma;
            }
        }
    }
    
    public List<Integer>[] calculate() throws InterruptedException{
        
        PrimeFinderThread threads[] = new PrimeFinderThread[3];
        width = 1.0 / (double) digits;

        for (int i=0; i < 3; i++){
            long start =  i * digits/3;
            long end = (i+1) * (digits/3);
            //int end = (i+1) * (digits/3);
            threads[i] = new PrimeFinderThread((int)start, (int)end);
            threads[i].start();
        }
        
        //Join all the threads
       
        List<Integer>[] res = null;
        for (int i = 0; i < 3; i++) {
            threads[i].join();
            res[i] = threads[i].getPrimes();
        }
       
        System.out.println("\nRes: "+res+"\n");
        return res;
    }
}
