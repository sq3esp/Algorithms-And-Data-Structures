package List9.Zadanie1.Array;

public class LinearHash extends HashArray {
	public LinearHash(int size) {
		super(size);
	}

	public LinearHash() {
	}

	public int getShifted(int i, int hash) {
		return ((i + hash) % (table.length));
	}


	public HashArray resize() {
		HashArray result = new LinearHash(table.length * 2);
		for (int i = 0; i < table.length; i++) {
			if (table[i] != null) {
				result.put(table[i].getKey(), table[i].getValue());
			}
		}
		return result;
	}
}
