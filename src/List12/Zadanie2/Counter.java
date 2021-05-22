package List12.Zadanie2;

public class Counter {
	private long bufor;
	private int chybionych;
	private long operacjiDlaChybionych;
	private int trafionych;
	private long operacjiDlaTrafionych;
	private double sredniaTrafionych;
	private double sredniaChybionych;
	private double sredniaLacznie;

	public Counter() {
		clear();
	}

	public void clear() {
		bufor = 0;
		chybionych = 0;
		operacjiDlaChybionych = 0;
		trafionych = 0;
		operacjiDlaTrafionych = 0;
		sredniaChybionych = 0;
		sredniaTrafionych = 0;
	}

	public void addToBuffer(int count) {
		bufor += count;
	}

	public void addOne() {
		bufor++;
	}

	public void convertToHited() {
		operacjiDlaTrafionych += bufor;
		trafionych++;
		bufor = 0;
	}

	public void convertToMissed() {
		operacjiDlaChybionych += bufor;
		chybionych++;
		bufor = 0;
	}

	public void prepareResults() {
		sredniaChybionych = (double) operacjiDlaChybionych / chybionych;
		sredniaTrafionych = (double) operacjiDlaTrafionych / trafionych;
		sredniaLacznie = (double) (operacjiDlaChybionych + operacjiDlaTrafionych) / (chybionych + trafionych);
	}

	public int getChybionych() {
		return chybionych;
	}

	public long getOperacjiDlaChybionych() {
		return operacjiDlaChybionych;
	}

	public int getTrafionych() {
		return trafionych;
	}

	public long getOperacjiDlaTrafionych() {
		return operacjiDlaTrafionych;
	}

	public double getSredniaTrafionych() {
		return sredniaTrafionych;
	}

	public double getSredniaChybionych() {
		return sredniaChybionych;
	}

	public double getSredniaLacznie() {
		return sredniaLacznie;
	}
}
