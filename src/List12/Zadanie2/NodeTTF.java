package List12.Zadanie2;

public class NodeTTF {
	private int items;
	private NodeTTF parent;
	private NodeTTF[] childArray = new NodeTTF[4];
	private Integer[] valuesArray = new Integer[3];

	public void addChild(int childNum, NodeTTF child) {
		childArray[childNum] = child;
		if (child != null) {
			child.parent = this;
		}
	}

	public NodeTTF removeChild(int childNum) {
		NodeTTF tempNode = childArray[childNum];
		childArray[childNum] = null;
		return tempNode;
	}

	public NodeTTF getChild(int childNum) {
		return childArray[childNum];
	}

	public NodeTTF getParent() {
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

	public NodeTTF[] getChildArray() {
		return childArray;
	}

	public void setChildArray(NodeTTF[] childArray) {
		this.childArray = childArray;
	}

	public void print() {
		System.out.print("[");
		for (int j = 0; j < items; j++) {
			System.out.print(" \"" + valuesArray[j] + "\" ");
		}
		System.out.println("]  ");
	}
}
