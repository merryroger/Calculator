package calculator;

public class Calc_run {

	protected Calculator calc;
	protected Calc_view cview;
	
	private Calc_run() {
		calc = new Calculator();
		cview = new Calc_view(calc);
	}
	
	public static void main(String[] args) {
		new Calc_run();
	}

}
