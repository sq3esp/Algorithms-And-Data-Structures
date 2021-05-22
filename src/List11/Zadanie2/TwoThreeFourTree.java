package List11.Zadanie2;

import java.util.ArrayList;

public class TwoThreeFourTree {
	private Node root = new Node();

	public void insert(int value) {
		Node curNode = root;

		while (true) {
			if (curNode.isFull()) {
				split(curNode);
				curNode = curNode.getParent();

				curNode = nextChild(curNode, value);
			} else if (curNode.isLeaf()) {
				break;
			} else {
				curNode = nextChild(curNode, value);
			}
		}
		curNode.insertItem(value);
	}

	public void find(int key) {
		Node curNode = root;
		while (true) {
			if (curNode.findItem(key) != null) {
				curNode.print();
				return;
			} else if (curNode.isLeaf()) {
				System.out.println("Nie znaleziono");
				return;
			} else {
				curNode = nextChild(curNode, key);
			}
		}
	}

	public void printByLevels() {
		for (int i = 1; i <= height(root); i++) {
			printLevel(root, i);
			System.out.println();
		}
	}

	private void printLevel(Node node, int level) {
		if (node != null) {
			if (level == 1) {
				node.print();
			} else if (level > 1) {
				for (int i = 0; i < node.getChildArray().length; i++) {
					printLevel(node.getChild(i), level - 1);
				}
			}
		}
	}

	private int height(Node node) {
		if (node == null) return 0;
		return 1 + Math.max(height(node.getChild(0)), height(node.getChild(0)));
	}

	public int minimum() {
		return minimumRec(root);
	}

	private int minimumRec(Node node) {
		if (!node.isLeaf()) {
			return minimumRec(node.getChild(0));
		} else {
			return node.getItem(0);
		}
	}

	public int maximum() {
		return maximumRec(root);
	}

	private int maximumRec(Node node) {
		if (node.isLeaf()) {
			return node.getItem(node.getItems() - 1);
		} else {
			for (int i = 0; i < node.getChildArray().length; i++) {
				if (node.getChildArray()[i] == null) {
					return maximumRec(node.getChildArray()[i - 1]);
				}
			}
			return 0;
		}
	}

	public void inorder() {
		ArrayList<Integer> list = new ArrayList<>();
		inorderRec(root, list);
		System.out.print("Inorder: ");
		for (Integer x : list) {
			System.out.print("\"" + x + "\" ");
		}
		System.out.println();
	}

	private void inorderRec(Node node, ArrayList<Integer> inorder) {
		if (node == null) return;
		inorderRec(node.getChild(0), inorder);
		for (int i = 0; i < node.getItems(); i++) {
			inorder.add(node.getItem(i));
			inorderRec(node.getChild(i + 1), inorder);
		}
	}

	public Integer inorderSuccessor(int key) {
		ArrayList<Integer> list = new ArrayList<>();
		inorderRec(root, list);

		for (int i = 0; i < list.size() - 1; i++) {
			if (list.get(i) == key) {
				return list.get(i + 1);
			}
		}
		return null;
	}

	public Integer inorderPreSuccessor(int key) {
		ArrayList<Integer> list = new ArrayList<>();
		inorderRec(root, list);

		for (int i = 1; i < list.size(); i++) {
			if (list.get(i) == key) {
				return list.get(i - 1);
			}
		}
		return null;
	}

	public void split(Node node) {
		int keyA, keyB;
		Node parent, childA, childB;

		childA = node.removeChild(2);
		childB = node.removeChild(3);
		keyA = node.removeItem();
		keyB = node.removeItem();

		if (node != root) {
			parent = node.getParent();
		} else {
			root = new Node();
			parent = root;
			root.addChild(0, node);
		}

		int index = parent.insertItem(keyA);

		for (int j = parent.getItems() - 1; j > index; j--) {
			Node tmp = parent.removeChild(j);
			parent.addChild(j + 1, tmp);
		}

		Node newNode = new Node();

		parent.addChild(index + 1, newNode);

		newNode.insertItem(keyB);
		newNode.addChild(0, childA);
		newNode.addChild(1, childB);
	}

	public Node nextChild(Node theNode, long value) {
		int numItems = theNode.getItems();
		int i;
		for (i = 0; i < numItems; i++) {
			if (value < theNode.getItem(i)) {
				return theNode.getChild(i);
			}
		}
		return theNode.getChild(i);
	}
}
