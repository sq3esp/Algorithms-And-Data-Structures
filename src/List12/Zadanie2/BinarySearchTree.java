package List12.Zadanie2;

public class BinarySearchTree {

	private NodeBST root;
	private int wysokosc, wezlow, lisci;
	private Counter counter;

	BinarySearchTree(Counter counter) {
		this.counter = counter;
		root = null;
	}

	void insert(int key) {
		root = insertRec(root, key);
	}


	private NodeBST insertRec(NodeBST node, int key) {

		if (node == null) {
			node = new NodeBST(key);
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


	private void preorderRec(NodeBST node) {
		if (node != null) {
			System.out.println(node.getKey());
			preorderRec(node.getLeft());
			preorderRec(node.getRight());
		}
	}

	void inorder() {
		inorderRec(root);
	}


	private void inorderRec(NodeBST node) {
		if (node != null) {
			inorderRec(node.getLeft());
			System.out.println(node.getKey());
			inorderRec(node.getRight());
		}
	}


	void postorder() {
		postorderRec(root);
	}


	private void postorderRec(NodeBST node) {
		if (node != null) {
			postorderRec(node.getLeft());
			postorderRec(node.getRight());
			System.out.println(node.getKey());
		}
	}

	int search(int key) {
		NodeBST temp = root;

		while (true) {
			counter.addOne();
			if (temp == null) {
				counter.convertToMissed();
				return -1;
			}
			if (temp.getKey() == key) {
				counter.convertToHited();
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
		NodeBST temp = root;

		while (temp.getLeft() != null) {
			temp = temp.getLeft();
		}
		return (temp.getKey());
	}

	public int treeMaximum() {
		NodeBST temp = root;

		while (temp.getRight() != null) {
			temp = temp.getRight();
		}
		return (temp.getKey());
	}


	void repairNumbering() {
		repairNumberingRec(root, 1);
	}

	private int repairNumberingRec(NodeBST node, int height) {
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

	private int printParametersRec(NodeBST node, int height) {
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

	private void printLevelsRec(NodeBST node, int height, int wysokosc) {
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
		NodeBST predecessor = null;
		NodeBST node = root;
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
		NodeBST succesor = null;
		NodeBST node = root;
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

	private NodeBST deleteRec(NodeBST node, int key) {
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


	private int minValue(NodeBST node) {
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


	void drawRec(NodeBST node) {
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
