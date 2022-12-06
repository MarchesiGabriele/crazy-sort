package sort_crazy;

import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;

public class CrazyIterative {
	static void sort(Queue<Integer> list, int index, Queue<Integer>res, int maxIndex) {
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
