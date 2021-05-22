package List9.Zadanie2.Array;

public class DoubleHash extends HashArray {
	int temp = 0;

	public DoubleHash(int size) {
		super(size);
	}

	public DoubleHash() {
	}

	public int hash(int i) {
		temp = i;
		return super.hash(i);
	}

	public int getShifted(int i, int hash) {
		if (i < table.length) {
			return (Math.abs(hash + (i * secondHash(temp))) % table.length);
		} else {
			return ((i + hash) % (table.length));
		}
	}

	public HashArray resize() {
		HashArray result = new DoubleHash(table.length * 2);
		for (int i = 0; i < table.length; i++) {
			if (table[i] != null) {
				result.put(table[i].getKey(), table[i].getValue());
			}
		}
		return result;
	}

}
