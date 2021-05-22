package List12.Zadanie2;

public class RedBlackTree {
	private NodeRB root = null;
	private Counter counter;

	public RedBlackTree(Counter counter) {
		this.counter = counter;
	}

	public void insert(int key) {
		root = insertRec(root, key);
		root.setIsRed(false);
	}

	private NodeRB insertRec(NodeRB node, int key) {
		if (node == null) {
			return new NodeRB(key, true);
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

	private void repairColors(NodeRB node) {
		node.getLeft().setIsRed(false);
		node.getRight().setIsRed(false);
		node.setIsRed(true);
	}

	private NodeRB rotateRight(NodeRB node) {
		NodeRB node1 = node.getLeft();
		node.setLeft(node1.getRight());
		node1.setRight(node);
		node1.setIsRed(node.getIsRed());
		node.setIsRed(true);
		return node1;
	}

	private NodeRB rotateLeft(NodeRB node) {
		NodeRB node1 = node.getRight();
		node.setRight(node1.getLeft());
		node1.setLeft(node);
		node1.setIsRed(node.getIsRed());
		node.setIsRed(true);
		return node1;
	}

	public NodeRB find(int key) {
		return findRec(root, key);
	}

	private NodeRB findRec(NodeRB node, int key) {
		counter.addOne();
		if (node == null) {
			counter.convertToMissed();
			return null;
		}

		if (key == node.getKey()) {
			counter.convertToHited();
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

	private void inorderRec(NodeRB node) {
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

	private void postorderRec(NodeRB node) {
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

	private void preorderRec(NodeRB node) {
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

	private void printLevelRec(NodeRB node, int level) {
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

	private int heightRec(NodeRB node) {
		if (node == null) return 0;
		return Math.max(heightRec(node.getLeft()), heightRec(node.getRight())) + 1;
	}

	private int nodesCount(NodeRB node) {
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

	private int nodesCountRec(NodeRB node) {
		if (node != null) {
			int counter = 1;
			counter += nodesCountRec(node.getLeft());
			counter += nodesCountRec(node.getRight());
			return counter;
		}
		return 0;
	}


}
