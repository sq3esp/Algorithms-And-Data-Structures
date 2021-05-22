package List12.Zadanie2;

public class NodeRB {
	private int key;
	private boolean isRed;
	private NodeRB left;
	private NodeRB right;


	public NodeRB(int key, boolean isRed) {
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

	public NodeRB getLeft() {
		return left;
	}

	public void setLeft(NodeRB left) {
		this.left = left;
	}

	public NodeRB getRight() {
		return right;
	}

	public void setRight(NodeRB right) {
		this.right = right;
	}

	@Override
	public String toString() {
		String result = "Klucz: " + key;

		if (isRed) {
			result += " Kolor: Czerwony";
		} else {
			result += " Kolor: Czarny";
		}

		return result;
	}
}
