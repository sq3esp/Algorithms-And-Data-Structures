package List12.Zadanie2;

import java.util.Random;

public class Zadanie2 {
	public static void main(String[] args) {
		Random rand = new Random();
		for (int j = 1; j <= 100; j *= 10) {
			Counter counter1 = new Counter();
			Counter counter2 = new Counter();
			Counter counter3 = new Counter();
			TwoThreeFourTree twoThreeFourTree = new TwoThreeFourTree(counter1);
			RedBlackTree redBlackTree = new RedBlackTree(counter2);
			BinarySearchTree binarySearchTree = new BinarySearchTree(counter3);

			for (int i = 0; i < 1000 * j; i++) {
				int random = rand.nextInt(1000 * j);
				twoThreeFourTree.insert(random);
				redBlackTree.insert(random);
				binarySearchTree.insert(random);
			}
			for (int i = 0; i < 2000 * j; i++) {
				twoThreeFourTree.find(i);
				redBlackTree.find(i);
				binarySearchTree.search(i);
			}
			System.out.println("Wynik dla " + j * 1000 + " elementów:");
			System.out.println("\n\tDla drzewa 2-3-4:");
			counter1.prepareResults();
			System.out.println("\t\tŚrednio trafionych: " + counter1.getSredniaTrafionych());
			System.out.println("\t\tŚrednio chybionych: " + counter1.getSredniaChybionych());

			System.out.println("\n\tDla drzewa czerwono czarnego:");
			counter2.prepareResults();
			System.out.println("\t\tŚrednio trafionych: " + counter2.getSredniaTrafionych());
			System.out.println("\t\tŚrednio chybionych: " + counter2.getSredniaChybionych());

			System.out.println("\n\tDla drzewa BST:");
			counter3.prepareResults();
			System.out.println("\t\tŚrednio trafionych: " + counter3.getSredniaTrafionych());
			System.out.println("\t\tŚrednio chybionych: " + counter3.getSredniaChybionych());
		}
	}
}
