package sort_crazy;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Queue;

public class main {
	public static void main(String[] args) {
		List<Integer> list  = new ArrayList<>();
		/*list.add(100);
		list.add(3560);
		list.add(88192);
		list.add(21);
		list.add(88191);
		*/
		
		list.add(10);
		list.add(-110);
		list.add(1);
		list.add(11);
		list.add(500);
		list.add(10);
		
		
		System.out.println("Iniziale: " + list);
		
		// TROVO MAX
		int max = Collections.max(list);

		
		// NUMERO CIFRE MAX
		int numeroCifre = new String("" + max).length();
		
		Queue<Integer> res = new ArrayDeque<>();
		sort(list, numeroCifre, res, numeroCifre);
}
	
	static void sort(List<Integer> list, int index, Queue<Integer>res, int maxIndex) {
		List<List<Integer>> ll = new ArrayList<>();
		
		for(int i = 0; i<10; i++) {
			ll.add(new ArrayList<>());
		}
		
		// Se la lista attuale contiene un solo element lo aggiungo ai risultati
		// se index == 1 allora anche se ho più elementi li aggiungo al risultato, dato che sono uguali
		if(list.size() == 1 || index == 1){
			for(Integer a: list) {
				res.add(a);
			}
			return;
		}
			
		// Scorro lista attuale
		for(Integer a : list) {
			// Se la cifra index che voglio controllare è zero, metto il numero nella lista zeri
			if(a < Math.pow(10, index-1)) {
				ll.get(0).add(a);
			}else {
				// Cifra iniziale destra
				String cifra = new String(""+(int)(a/Math.pow(10,index-1)));
				int newIndex = 0;
				if(cifra.length() > 1)
					newIndex = cifra.charAt(maxIndex-index) - '0';
				else
					newIndex = Integer.parseInt(cifra);
				
				// Aggiungo il valore alla lista in base alla cifra index che sto controllando
				ll.get(newIndex).add(a);
			}
		}
		
		// Per ciascuna delle 10 liste eseguo ricorsione, riducendo di 1 l'index della cifra da controllare
		for(List<Integer> la : ll) {
			if(la.size() != 0)
				sort(la, index-1 ,res, maxIndex);
		}
		
		// Stampo il risultato
		if(index == maxIndex)
			System.out.println("res: " + res);
	}
}
