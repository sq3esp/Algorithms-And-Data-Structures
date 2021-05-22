package List12.Zadanie2;

public class NodeBST {
	private int key, height, knots;
	private NodeBST left, right;

	public NodeBST(int key) {
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

	public NodeBST getLeft() {
		return left;
	}

	public void setLeft(NodeBST left) {
		this.left = left;
	}

	public NodeBST getRight() {
		return right;
	}

	public void setRight(NodeBST right) {
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
