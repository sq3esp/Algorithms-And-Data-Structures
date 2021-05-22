package List9.Zadanie2;

import List9.Zadanie2.Array.DoubleHash;
import List9.Zadanie2.Array.HashArray;
import List9.Zadanie2.Array.LinearHash;
import List9.Zadanie2.Array.QuadraticHash;

public class Zadanie2 {

	public static void main(String[] args) {
		double[] tests = {0.7, 0.8, 0.9};

		for (double x : tests) {

			HashArray linear = new LinearHash(1000);
			HashArray quadratic = new QuadraticHash(1000);
			HashArray doubleHash = new DoubleHash(1000);

			for (int i = 0; i < x * 1000; i++) {
				linear.put(i, i + 10);
				quadratic.put(i, i + 10);
				doubleHash.put(i, i + 10);
			}

			for (int i = 0; i < 1000; i++) {
				linear.get(i);
				quadratic.get(i);
				doubleHash.get(i);
			}

			System.out.println("\nWyniki dla " + x + ":");
			System.out.println("\nLiniowe");
			linear.getCounter().prepareResults();
			System.out.println("Średnia chybionych: " + linear.getCounter().getSredniaChybionych());
			System.out.println("Średnia trafionych: " + linear.getCounter().getSredniaTrafionych());
			System.out.println("Średnia łącznie: " + linear.getCounter().getSredniaLacznie());


			System.out.println("\nKwadratowe:");
			quadratic.getCounter().prepareResults();
			System.out.println("Średnia chybionych: " + quadratic.getCounter().getSredniaChybionych());
			System.out.println("Średnia trafionych: " + quadratic.getCounter().getSredniaTrafionych());
			System.out.println("Średnia łącznie: " + quadratic.getCounter().getSredniaLacznie());


			System.out.println("\nPodwójne hashowanie:");
			doubleHash.getCounter().prepareResults();
			System.out.println("Średnia chybionych: " + doubleHash.getCounter().getSredniaChybionych());
			System.out.println("Średnia trafionych: " + doubleHash.getCounter().getSredniaTrafionych());
			System.out.println("Średnia łącznie: " + doubleHash.getCounter().getSredniaLacznie());
		}
	}
}





