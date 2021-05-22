package List9.Zadanie1;

import List9.Zadanie1.Array.DoubleHash;
import List9.Zadanie1.Array.HashArray;
import List9.Zadanie1.Array.LinearHash;
import List9.Zadanie1.Array.QuadraticHash;

public class Zadanie1 {

	public static void main(String[] args) {
		HashArray linear = new LinearHash();
		HashArray quadratic = new QuadraticHash();
		HashArray doubleHash = new DoubleHash();


		System.out.println("Wyświetlamy puste tablice:");
		System.out.println("Liniowe");
		linear.dump();
		System.out.println("Kwadratowe:");
		quadratic.dump();
		System.out.println("Podwójne hashowanie:");
		doubleHash.dump();


		System.out.println("\nWypełniamy tablicę parami klucz=x i wartość x+10");
		for (int i = 0; i < 10; i++) {
			linear.put(i, i + 10);
			quadratic.put(i, i + 10);
			doubleHash.put(i, i + 10);
		}

		System.out.println("\nWyświetlamy połowicznie zapełnione tablice :");
		System.out.println("Liniowe");
		linear.dump();
		System.out.println("Kwadratowe:");
		quadratic.dump();
		System.out.println("Podwójne hashowanie:");
		doubleHash.dump();

		System.out.println("\nPobieramy element o kluczu 9:");
		System.out.println("Liniowe: " + linear.get(9));
		System.out.println("Kwadratowe: " + quadratic.get(9));
		System.out.println("Podwójne hashowanie: " + doubleHash.get(9));


		System.out.println("\nPodwajamy rozmiar tablicy");
		linear = linear.resize();
		quadratic = quadratic.resize();
		doubleHash = doubleHash.resize();


		System.out.println("\nWyświetlamy podwojone tablice:");
		System.out.println("Liniowe");
		linear.dump();
		System.out.println("Kwadratowe:");
		quadratic.dump();
		System.out.println("Podwójne hashowanie:");
		doubleHash.dump();

		System.out.println("\nSprawdzamy ile elementów posiadają tablice:");
		System.out.println("Liniowe: " + linear.size());
		System.out.println("Kwadratowe: " + quadratic.size());
		System.out.println("Podwójne hashowanie: " + doubleHash.size());

		System.out.println("\nSprawdzamy czy zawierają klucz 3:");
		System.out.println("Liniowe: " + linear.containsKey(3));
		System.out.println("Kwadratowe: " + quadratic.containsKey(3));
		System.out.println("Podwójne hashowanie: " + doubleHash.containsKey(3));

		System.out.println("\nSprawdzamy czy zawierają klucz 13:");
		System.out.println("Liniowe: " + linear.containsKey(13));
		System.out.println("Kwadratowe: " + quadratic.containsKey(13));
		System.out.println("Podwójne hashowanie: " + doubleHash.containsKey(13));
	}
}





