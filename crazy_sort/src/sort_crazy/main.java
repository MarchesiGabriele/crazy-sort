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
		
		long startCompute = System.currentTimeMillis();
		sort(queue, numeroCifre, res, numeroCifre);
		long endCompute = System.currentTimeMillis();
		long endTotal= System.currentTimeMillis();
		
		System.out.println("TEMPO COMPUTAZIONE: " + (endCompute-startCompute) + " ms");
		System.out.println("TEMPO TOTALE: " + (endTotal-startTotal) + " ms");
		
		startCompute = System.currentTimeMillis();
		Collections.sort(list);
		endCompute = System.currentTimeMillis();
		System.out.println("TEMPO TOTALE COLLECTIONS : " + (endCompute-startCompute) + " ms");
}
	
	static void sort(Queue<Integer> list, int index, Queue<Integer>res, int maxIndex) {
		//List<List<Integer>> ll = new ArrayList<>(10);
		Map<Integer, ArrayDeque<Integer>> ll = new HashMap<>();
		
		for(int i = 0; i<10; i++) {
			//ll.add(new ArrayList<>());
			ll.put(i, new ArrayDeque<Integer>());
		}
		
		// Se la lista attuale contiene un solo element lo aggiungo ai risultati
		// se index == 1 allora anche se ho più elementi li aggiungo al risultato, dato che sono uguali
		if(list.size() == 1 || index == 0){
			for(Integer a: list) {
				res.add(a);
			}
			return;
		}
			
		// Scorro lista attuale
		String cifra = "";
		int newIndex = 0;
		for(Integer a : list) {
			// Se la cifra index che voglio controllare è zero, metto il numero nella lista zeri
			if(a < Math.pow(10, index-1)) {
				//System.out.println("zeros " + a);
				//System.out.println(Math.pow(10, index-1));
				ll.get(0).add(a);
			}else {
				// Cifra iniziale destra
				cifra = new String(""+(int)(a/Math.pow(10,index-1)));
				//System.out.println(cifra + " len "+ cifra.length());
				
				newIndex = 0;
				if(cifra.length() > 1) {
					//System.out.println("cifra: " + cifra + " , index: " + index + " , maxIndex: " + maxIndex + 
					//"\n cifraLen: " + cifra.length());
					newIndex = cifra.charAt(cifra.length()-1) - '0';
				}
				else
					newIndex = Integer.parseInt(cifra);
				
				//System.out.println("non zeros " + newIndex);
				// Aggiungo il valore alla lista in base alla cifra index che sto controllando
				ll.get(newIndex).add(a);
			}
		}
		
		// Per ciascuna delle 10 liste eseguo ricorsione, riducendo di 1 l'index della cifra da controllare
		for(ArrayDeque<Integer> queue: ll.values()) {
			if(queue.peek() != null)
				sort(queue, index-1 ,res, maxIndex);
		}
		
		// Stampo il risultato
		if(index == maxIndex)
			//System.out.println("res: " + res);
			System.out.println("fine");
	}
}
