package List9.Zadanie1.Array;

public class QuadraticHash extends HashArray {
	public QuadraticHash(int size) {
		super(size);
	}

	public QuadraticHash() {
	}

	public int getShifted(int i, int hash) {
		return ((Math.abs(hash + ((((i + 1) / 2)) ^ 2) * (-i)) % table.length));
	}

	public HashArray resize() {
		HashArray result = new QuadraticHash(table.length * 2);
		for (int i = 0; i < table.length; i++) {
			if (table[i] != null) {
				result.put(table[i].getKey(), table[i].getValue());
			}
		}
		return result;
	}
}
