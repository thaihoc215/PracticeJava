import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class CalculatorView extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField firstNumber = new JTextField(10);
	private JLabel additionLabel = new JLabel("+");
	private JTextField secondNumber = new JTextField(10);
	private JButton calButton = new JButton("Calculate");
	private JTextField calSolution = new JTextField(10);
	
	public CalculatorView() {
		JPanel calPanel = new JPanel();
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(600,200);
		
		calPanel.add(firstNumber);
		calPanel.add(additionLabel);
		calPanel.add(secondNumber);
		calPanel.add(calButton);
		calPanel.add(calSolution);
		
		this.add(calPanel);
	}
	
	
	public int getFirstNumber() {
		return Integer.parseInt(firstNumber.getText());
	}
	
	public int getSecondNumber() {
		return Integer.parseInt(secondNumber.getText());
	}
	
	public int getCalSolution() {
		return Integer.parseInt(calSolution.getText());
	}
	
	public void setCalSolution(int solution) {
		calSolution.setText(Integer.toString(solution));
	}
	
	protected void addCalListener(ActionListener listenForCalButton) {
		calButton.addActionListener(listenForCalButton);
	}
	
	protected void displayErrorMessage(String erroMessage) {
		JOptionPane.showMessageDialog(this, erroMessage);
	}
}
