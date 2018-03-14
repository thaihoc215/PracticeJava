import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CalculatorController {
	private CalculatorView view;
	private CalculatorModel model;
	
	public CalculatorController(CalculatorView view,CalculatorModel model) {
		this.view = view;
		this.model = model;
		//set event of button in view
		this.view.addCalListener(new CalculateListener());
	}
	
	class CalculateListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			int fstNum, sndNum = 0;
			
			try {
				fstNum = view.getFirstNumber();
				sndNum = view.getSecondNumber();
				
				model.addTwoNumbers(fstNum, sndNum);
				
				view.setCalSolution(model.getCalcuValue());
			} catch (Exception e2) {
				// TODO: handle exception
			}
			
		}
		
	}
	

}
