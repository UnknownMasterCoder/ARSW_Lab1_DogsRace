package edu.eci.arsw.primefinder;

public class Main {

	public static void main(String[] args) throws InterruptedException {
		

		ParallelCalculation prueba = new ParallelCalculation(0, 30000000);
		prueba.calculate();
		
		
	}
	
}
