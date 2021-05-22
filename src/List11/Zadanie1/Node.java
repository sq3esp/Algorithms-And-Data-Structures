package List11.Zadanie1;

public class Node {
	private int key;
	private boolean isRed;
	private Node left;
	private Node right;


	public Node(int key, boolean isRed) {
		this.key = key;
		this.isRed = isRed;
		left = null;
		right = null;
	}

	public int getKey() {
		return key;
	}

	public void setKey(int key) {
		this.key = key;
	}

	public boolean getIsRed() {
		return isRed;
	}

	public void setIsRed(boolean red) {
		isRed = red;
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

	@Override
	public String toString() {
		String result = "Klucz: " + key;

		if (isRed) {
			result += " Kolor: Czerwony\n";
		} else {
			result += " Kolor: Czarny\n";
		}

		return result;
	}
}
