package List11.Zadanie2;

public class Node {
	private int items;
	private Node parent;
	private Node[] childArray = new Node[4];
	private Integer[] valuesArray = new Integer[3];

	public void addChild(int childNum, Node child) {
		childArray[childNum] = child;
		if (child != null) {
			child.parent = this;
		}
	}

	public Node removeChild(int childNum) {
		Node tempNode = childArray[childNum];
		childArray[childNum] = null;
		return tempNode;
	}

	public Node getChild(int childNum) {
		return childArray[childNum];
	}

	public Node getParent() {
		return parent;
	}

	public boolean isLeaf() {
		return childArray[0] == null;
	}

	public int getItems() {
		return items;
	}

	public Integer getItem(int index) {
		return valuesArray[index];
	}

	public boolean isFull() {
		return items == 3;
	}

	public Integer findItem(int key) {
		for (int j = 0; j < 3; j++) {
			if (valuesArray[j] == null) {
				return null;
			} else if (valuesArray[j] == key) {
				return j;
			}
		}
		return null;
	}

	public int insertItem(int newItem) {
		for (int j = 2; j >= 0; j--) {
			if (valuesArray[j] != null) {
				int itsKey = valuesArray[j];
				if (newItem < itsKey) {
					valuesArray[j + 1] = valuesArray[j];
				} else {
					valuesArray[j + 1] = newItem;
					items++;
					return j + 1;
				}
			}
		}
		valuesArray[0] = newItem;
		items++;
		return 0;
	}

	public int removeItem() {
		int temp = valuesArray[items - 1];
		valuesArray[items - 1] = null;
		items--;
		return temp;
	}

	public Node[] getChildArray() {
		return childArray;
	}

	public void setChildArray(Node[] childArray) {
		this.childArray = childArray;
	}

	public void print() {
		System.out.print("[");
		for (int j = 0; j < items; j++) {
			System.out.print(" \""+ valuesArray[j] + "\" ");
		}
		System.out.print("]  ");
	}
}
