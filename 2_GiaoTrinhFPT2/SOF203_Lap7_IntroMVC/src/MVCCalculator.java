
public class MVCCalculator {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		CalculatorView view = new CalculatorView();
		CalculatorModel model = new CalculatorModel();
		CalculatorController controller = new CalculatorController(view, model);
		view.setVisible(true);
	}

}
