package List9.Zadanie1.Array;

public abstract class HashArray {
	Pair[] table;
	Counter counter;

	public HashArray() {
		table = new Pair[20];
		counter = new Counter();
	}

	public HashArray(int size) {
		this.table = new Pair[size];
		counter = new Counter();
	}

	public Counter getCounter() {
		return counter;
	}

	public int getShifted(int iteration, int hash) {
		return -1;
	}

	public int get(int key) {
		int hash = hash(key);


		if (table[hash] != null && table[hash].getKey() == key) {
			counter.addToBuffer(1);
			counter.convertToHited();
			return table[hash].getValue();
		}

		int i = 0;

		while (table[getShifted(i, hash)] != null && table[getShifted(i, hash)].getKey() != key) {
			if (i > table.length) {
				counter.addToBuffer(i + 1);
				counter.convertToMissed();
				return -1;
			}
			i++;
		}

		counter.addToBuffer(i + 1);

		if (table[getShifted(i, hash)] == null) {
			counter.convertToMissed();
			return -1;
		} else {
			counter.convertToHited();
			return table[getShifted(i, hash)].getValue();
		}
	}


	public int put(int k, int v) {
		int hash = hash(k);
		int i = 0;
		if (table[hash] == null) {
			table[hash] = new Pair(k, v);
			return -1;
		} else {
			if (size() == table.length) {
				return -1;
			}
			while (table[getShifted(i, hash)] != null) {
				if (table[getShifted(i, hash)].getKey() == k) {
					int result = table[getShifted(i, hash)].getValue();
					table[getShifted(i, hash)].setValue(v);
					return result;
				}

				i++;
			}
			table[getShifted(i, hash)] = new Pair(k, v);
		}
		return -1;
	}

	public boolean containsKey(int key) {
		for (int i = 0; i < table.length; i++) {
			if (table[i] != null && table[i].getKey() == key) {
				return true;
			}
		}
		return false;
	}


	public int size() {
		int result = 0;
		for (int i = 0; i < table.length; i++) {
			if (table[i] != null) {
				result++;
			}
		}
		return result;
	}

	public boolean isEmpty() {
		for (int i = 0; i < table.length; i++) {
			if (table[i] != null) return false;
		}
		return true;
	}

	public HashArray resize() {
		return null;
	}

	public void dump() {
		for (int i = 0; i < table.length; i++) {
			if (table[i] == null) {
				System.out.print("[-,-] ");
			} else {
				System.out.print(table[i]);
			}
		}
		System.out.println();
	}

	public int hash(int k) {
		return (((k * 127) + (k % 13)) % table.length) / 10;
	}

	public int secondHash(int k) {
		return (((k * 17) + (k % 3)) % table.length);
	}
}
