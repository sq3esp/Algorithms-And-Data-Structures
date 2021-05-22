package List11.Zadanie2;

public class Zadanie2 {
	public static void main(String[] args) {
		TwoThreeFourTree tree = new TwoThreeFourTree();
		System.out.println("Wstawiamy elementy od 1 do 15 ");
		for (int i = 1; i <= 15; i ++) {
			tree.insert(i);
		}
		System.out.println("\nWyszukujemy węzeł z wartością 13:");
		tree.find(13);
		System.out.println("\nWyświetlamy poziomami: ");
		tree.printByLevels();
		System.out.println("\nKlucz minimalny: " + tree.minimum());
		System.out.println("Klucz maksymalny: " + tree.maximum());
		System.out.println("\nWyświetlamy w porządku inorder: ");
		tree.inorder();
		System.out.println("\nNastępnik dla 10: " + tree.inorderSuccessor(10) + "\nPoprzednik dla 10: " + tree.inorderPreSuccessor(10));
	}

}
