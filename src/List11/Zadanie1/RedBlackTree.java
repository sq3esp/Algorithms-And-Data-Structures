package List11.Zadanie1;

public class RedBlackTree {
	private Node root = null;

	public void insert(int key) {
		root = insertRec(root, key);
		root.setIsRed(false);
	}

	private Node insertRec(Node node, int key) {
		if (node == null) {
			return new Node(key, true);
		}

		if (key - node.getKey() > 0) {
			node.setRight(insertRec(node.getRight(), key));
		} else if (key - node.getKey() < 0) {
			node.setLeft(insertRec(node.getLeft(), key));
		}

		if (node.getLeft() != null && node.getLeft().getIsRed() && node.getLeft().getLeft() != null && node.getLeft().getLeft().getIsRed()) {
			node = rotateRight(node);
		}

		if (node.getRight() != null && node.getRight().getIsRed() && !(node.getLeft() != null && node.getLeft().getIsRed())) {
			node = rotateLeft(node);
		}

		if (node.getLeft() != null && node.getLeft().getIsRed() && node.getRight() != null && node.getRight().getIsRed()) {
			repairColors(node);
		}

		return node;
	}

	private void repairColors(Node node) {
		node.getLeft().setIsRed(false);
		node.getRight().setIsRed(false);
		node.setIsRed(true);
	}

	private Node rotateRight(Node node) {
		Node node1 = node.getLeft();
		node.setLeft(node1.getRight());
		node1.setRight(node);
		node1.setIsRed(node.getIsRed());
		node.setIsRed(true);
		return node1;
	}

	private Node rotateLeft(Node node) {
		Node node1 = node.getRight();
		node.setRight(node1.getLeft());
		node1.setLeft(node);
		node1.setIsRed(node.getIsRed());
		node.setIsRed(true);
		return node1;
	}

	public Node find(int key) {
		return findRec(root, key);
	}

	private Node findRec(Node node, int key) {
		if (node == null) {
			return null;
		}

		if (key == node.getKey()) {
			return node;
		}

		if (key < node.getKey()) {
			return findRec(node.getLeft(), key);
		}
		return findRec(node.getRight(), key);
	}

	public void inorder() {
		inorderRec(root);
	}

	private void inorderRec(Node node) {
		if (node != null) {
			inorderRec(node.getLeft());
			if (node.getIsRed()) {
				System.out.println(node.getKey() + "-Red ");
			} else {
				System.out.println(node.getKey() + "-Black ");
			}
			inorderRec(node.getRight());
		}
	}

	public void postorder() {
		postorderRec(root);
	}

	private void postorderRec(Node node) {
		if (node != null) {
			postorderRec(node.getLeft());
			postorderRec(node.getRight());
			if (node.getIsRed()) {
				System.out.println(node.getKey() + "-Red ");
			} else {
				System.out.println(node.getKey() + "-Black ");
			}
		}
	}

	public void preorder() {
		preorderRec(root);
	}

	private void preorderRec(Node node) {
		if (node != null) {
			if (node.getIsRed()) {
				System.out.println(node.getKey() + "-Red ");
			} else {
				System.out.println(node.getKey() + "-Black ");
			}
			preorderRec(node.getLeft());
			preorderRec(node.getRight());
		}
	}

	public void printByLevels() {
		int h = heightRec(root);
		for (int i = 1; i <= h; i++) {
			printLevelRec(root, i);
			System.out.println();
		}
	}

	private void printLevelRec(Node node, int level) {
		if (node != null) {
			if (level == 1) {
				if (node.getIsRed()) {
					System.out.print(node.getKey() + "-Red    ");
				} else {
					System.out.print(node.getKey() + "-Black  ");
				}
			} else if (level > 1) {
				level--;
				printLevelRec(node.getLeft(), level);
				printLevelRec(node.getRight(), level);
			}
		}
	}

	public int height() {
		return heightRec(root);
	}

	public int heightLeft() {
		if (root == null) return 0;
		return heightRec(root.getLeft());
	}

	public int heightRight() {
		if (root == null) return 0;
		return heightRec(root.getRight());
	}

	private int heightRec(Node node) {
		if (node == null) return 0;
		return Math.max(heightRec(node.getLeft()), heightRec(node.getRight())) + 1;
	}

	private int nodesCount(Node node) {
		return nodesCountRec(node);
	}

	public int nodesCount() {
		return nodesCountRec(root);
	}

	public int nodesCountLeft() {
		if (root == null) return 0;
		return nodesCountRec(root.getLeft());
	}

	public int nodesCountRight() {
		if (root == null) return 0;
		return nodesCountRec(root.getRight());
	}

	private int nodesCountRec(Node node) {
		if (node != null) {
			int counter = 1;
			counter += nodesCountRec(node.getLeft());
			counter += nodesCountRec(node.getRight());
			return counter;
		}
		return 0;
	}

}
