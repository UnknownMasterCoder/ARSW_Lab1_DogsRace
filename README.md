# ARSW_Lab1 Dogs Race case
Laboratorio 1 de ARSW Dogs Race case
# Group:
+ **PEDRO JOSE MAYORGA NAVARRETE** - *Initial work* - [unknownmastercoder](https://github.com/unknownmastercoder)
+ **ANDRES DAVID VASQUEZ IBAÑEZ** - *Initial work* - [VASHIGO](https://github.com/vashigo)
----
                
+ # **Dogs Race case**
    + ## Part 1:
        **Creation, commissioning and coordination of threads.**
        
        + **Review the "concurrent cousins" program (in the folder part1), provided in the package edu.eci.arsw.primefinder. This is a program that calculates the prime numbers between two intervals, distributing their search among independent threads. For now, it has a single thread that seeks cousins ​​between 0 and 30,000,000. Run it, open the operating system process manager, and verify how many cores are used by it.**

        <p align="center">
        <img src="https://drive.google.com/uc?export=view&id=1x2KThkhMhFwTFJo3IYO8ugCF6oFby1QJ" />
        </p>

        + **Modify the program so that, instead of solving the problem with a single thread, do it with three, where each of these will make up the first part of the original problem. Check the operation again, and again check the use of the equipment cores.**

        ```java
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
        ```    

        <p align="center">
        <img src="https://drive.google.com/uc?export=view&id=14BnxeoljTvjm2-x5k2YMHTpU_SfUcZOK" />
        </p>    

        + **What you have been asked for is: you must modify the application so that when 5 seconds have elapsed since the execution started, all the threads are stopped and the number of primes ​​found so far is displayed. Then, you must wait for the user to press ENTER to resume their execution.**