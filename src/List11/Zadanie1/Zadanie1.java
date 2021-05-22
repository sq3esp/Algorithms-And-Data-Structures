package List11.Zadanie1;

public class Zadanie1 {
	public static void main(String[] args) {
		RedBlackTree tree = new RedBlackTree();

		System.out.println("Wstawiamy 10 elementów:");
		for (int i = 1; i <= 10; i++) {
			tree.insert(i);
		}
		System.out.println("\nWyszukujemy węzła o kluczu 6:");
		System.out.println(tree.find(6));

		System.out.println("\n\nWyświetlamy w porządku inorder:");
		tree.inorder();

		System.out.println("\n\nWyświetlamy w porządku postorder:");
		tree.postorder();

		System.out.println("\n\nWyświetlamy w porządku preorder:");
		tree.preorder();

		System.out.println("\n\nWyświetlamy poziomami: ");
		tree.printByLevels();

		System.out.println("\nWysokość drzewa: " + tree.height());

		System.out.println("Wysokość lewego poddrzewa: " + tree.heightLeft()); //wewnątrz klasy istnieją metody na wybranie wyświetlenia wysokości poddrzewa konkretnego węzła

		System.out.println("Wysokość prawego poddrzewa: " + tree.heightRight());

		System.out.println("Ilość węzłów: " + tree.nodesCount());

		System.out.println("Ilość węzłów w lewym poddrzewie: " + tree.nodesCountLeft());//wewnątrz klasy istnieją metody na wybranie wyświetlenia ilości węzłów w poddrzewie konkretnego węzła

		System.out.println("Ilość węzłów w prawym poddrzewie: " + tree.nodesCountRight());
	}
}
