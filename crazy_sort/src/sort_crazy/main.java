package sort_crazy;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;

/**
 * 
 *	TODO: fixare funzionamento quando ci sono valori doppi 
 *	TODO: misurare performance 
 *	TODO: fixare funzionamento per valori decimali 
 *
 * @author gabriele 
 *
 */



public class main {
	public static void main(String[] args) {
		
		/*
		List<Integer> list = new ArrayList<>();
		list.add(100);
		list.add(3560);
		list.add(81922);
		list.add(21);
		list.add(81921);
		*/
		
		List<Integer> list  = new ArrayList<>();
		/*list.add(100);
		list.add(3560);
		list.add(88192);
		list.add(21);
		list.add(88191);
		*/
		
		list.add(10);
		list.add(1);
		list.add(10);
		list.add(500);
		
		
		
		System.out.println("Iniziale: " + list);
		
		// TROVO MAX
		int max = Collections.max(list);

		
		// NUMERO CIFRE MAX
		int numeroCifre = new String("" + max).length();
		

		
		Queue<Integer> res = new ArrayDeque<>();
		sort(list, numeroCifre, res);
		
		
}
	
	// Prendo lista attuale, se è di lunghezza uno aggiungo il contenuto al res e ritorno
	// Altrimenti scorro la lista
	// - Se un valore quelli che sono inferiori a Math.pow(10, index) allora vuol dire che è come se avesse uno
	// 	 zero come prima cifra
	// - Se un valore quelli che sono inferiori a Math.pow(10, index) 
	static void sort(List<Integer> list, int index, Queue<Integer>res) {
		List<List<Integer>> ll = new ArrayList<>();
		
		for(int i = 0; i<10; i++) {
			ll.add(new ArrayList<>());
		}
		
		// TODO: cercare di ottimizzare 
		int tt = list.get(0);
		boolean flag = true;
		for(Integer b: list) {
			if(b != tt) {
				flag = false;
				break;
			}
		}
		if(list.size() == 1 || flag){
			for(Integer a: list) {
			res.add(a);
			}
			return;
		}
		
		for(Integer a : list) {
			if(a < Math.pow(10, index-1)) {
				ll.get(0).add(a);
			}else {
				String cifra = new String(""+(int)(a/Math.pow(10,index-1)));
				// Cifra iniziale destra
				int newIndex = 0;
				if(cifra.length() > 1){
					System.out.println(5-index);
					newIndex = cifra.charAt(5-index) - '0';
				}
				else
					newIndex = Integer.parseInt(cifra);
				
				ll.get(newIndex).add(a);
			}
		}
		
		for(List<Integer> la : ll) {
			if(la.size() != 0)
				sort(la, index-1 ,res);
		}
		
		System.out.println("res: " + res);
	}
}
