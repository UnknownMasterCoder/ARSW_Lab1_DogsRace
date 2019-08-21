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
        public class PrimeFinderThread extends Thread{

            int a,b;
            private List<Integer> primes=new LinkedList<Integer>();
            
            public PrimeFinderThread(int a, int b) {
                super();
                this.a = a;
                this.b = b;
            }

            public void run(){
                for (int i=a;i<=b;i++){						
                    if (isPrime(i)){
                        primes.add(i);
                        System.out.println(i);
                    }
                }	
            }
            
            boolean isPrime(int n) {
                if (n%2==0) return false;
                for(int i=3;i*i<=n;i+=2) {
                    if(n%i==0)
                        return false;
                }
                return true;
            }

            public List<Integer> getPrimes() {
                return primes;
            }
            
        }
        ```        

        + **What you have been asked for is: you must modify the application so that when 5 seconds have elapsed since the execution started, all the threads are stopped and the number of primes ​​found so far is displayed. Then, you must wait for the user to press ENTER to resume their execution.**