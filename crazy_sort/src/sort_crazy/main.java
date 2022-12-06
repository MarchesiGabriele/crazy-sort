package sort_crazy;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Random;

public class main {
	public static void main(String[] args) {
		List<Integer> list  = new ArrayList<>();
		
		Random r = new Random();
		for (int i = 0; i < 1000000; i++) {
			list.add(Math.abs(r.nextInt()));
		}
		
		
		// DOESNT WORK WITH THIS:
		/*		
		list.add(1);
		list.add(-1);
		list.add(1);
		list.add(-5);
		 */		
		
		list.add(1000);
		list.add(650);
		list.add(3);
		list.add(82);
		
		//System.out.println("Iniziale: " + list);
		long startCompute = System.currentTimeMillis();
		Collections.sort(list);
		long endCompute = System.currentTimeMillis();
		System.out.println("TEMPO TOTALE COLLECTIONS : " + (endCompute-startCompute) + " ms");

		// TROVO MAX ASSOLUTO
		long startTotal = System.currentTimeMillis();
		List<Integer> x = new ArrayList<Integer>(list);
		for( int i = 0; i < x.size(); i++ ){
		   x.set( i, Math.abs(x.get(i)) );
		}
		int max = Collections.max(x);		

		//int max = Collections.max(list);

		
		// NUMERO CIFRE MAX
		int numeroCifre = new String("" + max).length();

		Queue<Integer> res = new ArrayDeque<>();
		
		Queue<Integer> queue = new ArrayDeque<>(list);
		
		startCompute = System.currentTimeMillis();
		CrazyRecursive.sort(queue, numeroCifre, res, numeroCifre);
		endCompute = System.currentTimeMillis();
		long endTotal= System.currentTimeMillis();
		
		System.out.println("TEMPO COMPUTAZIONE: " + (endCompute-startCompute) + " ms");
		System.out.println("TEMPO TOTALE: " + (endTotal-startTotal) + " ms");
	}	
}
