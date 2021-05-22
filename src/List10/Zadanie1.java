package List10;

import java.util.Scanner;

public class Zadanie1 {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		BinarySearchTree tree = new BinarySearchTree();
		tree.insert(3);
		for (int i = 0; i < 10; i++) {
			tree.insert(i);
		}

		while (true) {
			System.out.println("\n\n\n\nMENU:");
			System.out.println("1 = Wstaw nowy węzeł");
			System.out.println("2 = Wyświetl w porządku inorder");
			System.out.println("3 = Wyświetl w porządku preorder");
			System.out.println("4 = Wyświetl w porządku postorder");
			System.out.println("5 = Znajdź węzeł");
			System.out.println("6 = Wyznacz parametry drzewa");
			System.out.println("7 = Wyświetl drzewo poziomami");
			System.out.println("8 = Wyznacz następnik");
			System.out.println("9 = Wyznacz poprzednik");
			System.out.println("10 = Usuń węzeł");
			System.out.println("11 = Narusuj drzewo");
			System.out.println("12 = Nowe drzewo");
			System.out.println("13 = Wyświetl najmniejszy element");
			System.out.println("14 = Wyświetl największy element");
			System.out.println("15 = Koniec programu");
			System.out.print("OPCJA: ");
			int parametr = scanner.nextInt();
			System.out.println("\n");

			switch (parametr) {
				case 1 -> {
					System.out.print("Klucz: ");
					parametr = scanner.nextInt();
					System.out.println("\n\n");
					tree.insert(parametr);

				}
				case 2 -> {
					tree.inorder();
				}
				case 3 -> {
					tree.preorder();
				}
				case 4 -> {
					tree.postorder();
				}
				case 5 -> {
					System.out.print("Klucz: ");
					parametr = scanner.nextInt();
					System.out.println("\n\n");
					System.out.println(tree.search(parametr));
				}
				case 6 -> {
					tree.printParameters();
				}
				case 7 -> {
					tree.printLevels();
				}
				case 8 -> {
					System.out.print("Klucz: ");
					parametr = scanner.nextInt();
					System.out.println("\n\n");
					System.out.println(tree.findSuccesor(parametr));
				}
				case 9 -> {
					System.out.print("Klucz: ");
					parametr = scanner.nextInt();
					System.out.println("\n\n");
					System.out.println(tree.findPredecessor(parametr));
				}
				case 10 -> {
					System.out.print("Klucz: ");
					parametr = scanner.nextInt();
					System.out.println("\n\n");
					tree.deleteKey(parametr);

				}
				case 11 -> {
					tree.draw();
				}
				case 12 -> {
					tree = new BinarySearchTree();
				}
				case 13 -> {
					System.out.println(tree.treeMinimum());
				}
				case 14 -> {
					System.out.println(tree.treeMaximum());
				}
				case 15 -> {
					return;
				}


			}
		}
	}
}


class BinarySearchTree {

	private Node root;
	private int wysokosc, wezlow, lisci;

	BinarySearchTree() {
		root = null;
	}

	void insert(int key) {
		root = insertRec(root, key);
	}


	private Node insertRec(Node node, int key) {

		if (node == null) {
			node = new Node(key);
			return node;
		}

		if (key < node.getKey()) {
			node.setLeft(insertRec(node.getLeft(), key));
		} else {
			node.setRight(insertRec(node.getRight(), key));
		}
		return node;
	}


	void preorder() {
		preorderRec(root);
	}


	private void preorderRec(Node node) {
		if (node != null) {
			System.out.println(node.getKey());
			preorderRec(node.getLeft());
			preorderRec(node.getRight());
		}
	}

	void inorder() {
		inorderRec(root);
	}


	private void inorderRec(Node node) {
		if (node != null) {
			inorderRec(node.getLeft());
			System.out.println(node.getKey());
			inorderRec(node.getRight());
		}
	}


	void postorder() {
		postorderRec(root);
	}


	private void postorderRec(Node node) {
		if (node != null) {
			postorderRec(node.getLeft());
			postorderRec(node.getRight());
			System.out.println(node.getKey());
		}
	}

	int search(int key) {
		Node temp = root;

		while (true) {
			if (temp == null) {
				return -1;
			}
			if (temp.getKey() == key) {
				return temp.getKey();
			}

			if (temp.getKey() < key) {
				temp = temp.getRight();
			} else {
				temp = temp.getLeft();
			}
		}
	}

	public int treeMinimum() {
		Node temp = root;

		while (temp.getLeft() != null) {
			temp = temp.getLeft();
		}
		return (temp.getKey());
	}

	public int treeMaximum() {
		Node temp = root;

		while (temp.getRight() != null) {
			temp = temp.getRight();
		}
		return (temp.getKey());
	}


	void repairNumbering() {
		repairNumberingRec(root, 1);
	}

	private int repairNumberingRec(Node node, int height) {
		if (node != null) {
			node.setHeight(height);
			int knots = 1;
			knots += repairNumberingRec(node.getLeft(), height + 1);
			knots += repairNumberingRec(node.getRight(), height + 1);
			node.setKnots(knots);
			return knots;
		}
		return 0;
	}


	void printParameters() {
		wysokosc = 0;
		lisci = 0;
		wezlow = 0;
		printParametersRec(root, 1);
		System.out.println("Wysokość drzewa: " + wysokosc + " liczba węzłów: " + wezlow + " liczba liści:" + lisci);
	}

	private int printParametersRec(Node node, int height) {
		if (node != null) {
			int temp;
			wezlow++;
			temp = printParametersRec(node.getLeft(), height + 1);
			temp += printParametersRec(node.getRight(), height + 1);
			if (temp == 2) {
				lisci++;
			}
			if (height > wysokosc) wysokosc = height;
			return 0;
		}
		return 1;
	}


	void printLevels() {
		repairNumbering();
		printParametersRec(root, 1);

		for (int i = 1; i <= wysokosc; i++) {
			printLevelsRec(root, 1, i);
			System.out.println("");
		}
	}

	private void printLevelsRec(Node node, int height, int wysokosc) {
		if (node != null) {
			if (node.getHeight() == wysokosc) {
				System.out.print(node.getKey() + " ");
				return;
			}
			if (node.getHeight() > wysokosc) return;
			printLevelsRec(node.getLeft(), height + 1, wysokosc);
			printLevelsRec(node.getRight(), height + 1, wysokosc);
		}
	}


	Integer findPredecessor(int key) {
		Node predecessor = null;
		Node node = root;
		if (node == null)
			return null;

		while (node != null) {
			if (node.getKey() == key) {
				if (node.getLeft() != null) {
					predecessor = node.getLeft();
					while (predecessor.getRight() != null)
						predecessor = predecessor.getRight();
				}
				if (predecessor != null) {
					return predecessor.getKey();
				}
				return null;
			} else if (node.getKey() < key) {
				predecessor = node;
				node = node.getRight();
			} else {
				node = node.getLeft();
			}
		}
		return null;
	}

	Integer findSuccesor(int key) {
		Node succesor = null;
		Node node = root;
		if (node == null)
			return null;

		while (node != null) {
			if (node.getKey() == key) {
				if (node.getRight() != null) {
					succesor = node.getRight();
					while (succesor.getLeft() != null)
						succesor = succesor.getLeft();
				}
				if (succesor != null) {
					return succesor.getKey();
				}
				return null;
			} else if (node.getKey() < key) {
				node = node.getRight();
			} else {
				succesor = node;
				node = node.getLeft();
			}
		}
		return null;
	}

	void deleteKey(int key) {
		root = deleteRec(root, key);
	}

	private Node deleteRec(Node node, int key) {
		if (node == null)
			return node;

		if (key < node.getKey())
			node.setLeft(deleteRec(node.getLeft(), key));
		else if (key > node.getKey())
			node.setRight(deleteRec(node.getRight(), key));

		else {
			if (node.getLeft() == null)
				return node.getRight();
			else if (node.getRight() == null)
				return node.getLeft();

			node.setKey(minValue(node.getRight()));

			node.setRight(deleteRec(node.getRight(), node.getKey()));
		}

		return node;
	}


	private int minValue(Node node) {
		int minv = node.getKey();
		while (node.getLeft() != null) {
			minv = node.getLeft().getKey();
			node = node.getLeft();
		}
		return minv;
	}

	void draw() {
		repairNumbering();
		drawRec(root);
	}


	void drawRec(Node node) {
		if (node != null) {
			drawRec(node.getLeft());
			String tabs = "";
			for (int i = 0; i < node.getHeight(); i++) {
				tabs += "\t";
			}
			System.out.println(tabs + node.getKey());
			drawRec(node.getRight());
		}
	}


}

class Node {
	private int key, height, knots;
	private Node left, right;

	public Node(int key) {
		this.key = key;
		left = null;
		right = null;
		height = 0;
		knots = 0;
	}

	public int getKey() {
		return key;
	}

	public void setKey(int key) {
		this.key = key;
	}

	public Node getLeft() {
		return left;
	}

	public void setLeft(Node left) {
		this.left = left;
	}

	public Node getRight() {
		return right;
	}

	public void setRight(Node right) {
		this.right = right;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getKnots() {
		return knots;
	}

	public void setKnots(int knots) {
		this.knots = knots;
	}
}
