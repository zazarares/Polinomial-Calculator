
import java.awt.event.*;
import javax.swing.*;
@SuppressWarnings("serial")
public class GUI extends JFrame implements ActionListener{

	 JFrame Frame=new JFrame("hello world");
	 JPanel panel=new JPanel();
	 JButton plus=new JButton("+");
	 JButton minus=new JButton("-");
	 JButton multiply=new JButton("*");
	 JButton divide=new JButton("/");
	 JButton equals=new JButton("=");
	 JButton derivate=new JButton("Derivate");
	 JButton integrate=new JButton("Integrate");
	 JLabel Current=new JLabel("");
	 public static Polinomial P1=new Polinomial();
	 public static Polinomial P2=new Polinomial();
	 static JTextField T1 = new JTextField("",20);
	 static String result="nu merge!";
	 static JTextField T2 = new JTextField("",20);
	 int operation=0;
	 static JTextField T3 = new JTextField("",20);

	 JLabel L1=new JLabel("Polinomul 1:");

	 JLabel L2=new JLabel("Polinomul 2:");

	 JLabel L3=new JLabel("Polinomul rezultat:");

	 public GUI() {
	 Frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	 Frame.setSize(1000,800);
	 Frame.setLocationRelativeTo(null);
	 panel.setLayout(null);
	 plus.setBounds(350,450,50,50);
	 panel.add(plus);
	 minus.setBounds(410,450,50,50);
	 panel.add(minus);
	 multiply.setBounds(470,450,50,50);
	 panel.add(multiply);
	 divide.setBounds(350,510,50,50);
	 panel.add(divide);
	 equals.setBounds(410,510,110,50);
	 panel.add(equals);
	 derivate.setBounds(350,570,170,50);
	 panel.add(derivate);
	 integrate.setBounds(350,630,170,50);
	 panel.add(integrate);
	 T1.setBounds(100,100,700,25);
	 panel.add(T1);
	 T2.setBounds(100,200,700,25);
	 panel.add(T2);
	 T3.setBounds(100,300,700,25);
	 panel.add(T3);
	 L1.setBounds(100,50,100,50);
	 panel.add(L1);
	 L2.setBounds(100,150,100,50);
	 panel.add(L2);
	 L3.setBounds(100,250,200,50);
	 panel.add(L3);
	 Current.setBounds(600,450,100,100);
	 panel.add(Current);
	 plus.addActionListener(this);
	 minus.addActionListener(this);
	 multiply.addActionListener(this);
	 divide.addActionListener(this);
	 equals.addActionListener(this);
	 derivate.addActionListener(this);
	 integrate.addActionListener(this);
	 //Frame.add(panel);
	 
	 Frame.setVisible(true);
	 Frame.setContentPane(panel);
	 


 }
	protected static String GetFirstPolinome()
	{
		return T1.getText();
	}
	protected static String GetSecondPolinome()
	{
		return T2.getText();
	}
	protected static void SetResultPolinome(String S)
	{
		result=new String(S);
	}
@Override
public void actionPerformed(ActionEvent e) {
	// TODO Auto-generated method stub
	if(e.getSource()==plus)
	{
		operation=1;
	}		
	if(e.getSource()==minus)
	{
		operation=2;
	}
	if(e.getSource()==multiply)
	{
		operation=3;
	}
	if(e.getSource()==divide) 
	{
		operation=4;
	}
	if(e.getSource()==derivate)
	{
		operation=5;
		doequal();
	}
	if(e.getSource()==integrate)
	{
		operation=6;
		doequal();
	}
	if(e.getSource()==equals)
	{			
		doequal();
	}
	settext();			
		}
private void doequal()
{
	P1.SetArrayFromString(T1.getText());
	P2.SetArrayFromString(T2.getText());
	if(operation==1)
	{
		doSum(P1,P2);
	}
	if(operation==2)
	{
		doMinus(P1,P2);
	}
	if(operation==3)
	{
		doMultiply(P1,P2);
	}
	if(operation==5)
	{
		doDerivate(P1);
	}
	if(operation==6)
	{
		doIntegrate(P1);
	}

}
private void doMinus(Polinomial P1,Polinomial P2)
{
	String S=Polinomial.Difference(P1, P2).toString();
	T3.setText(S);
}
private void doMultiply(Polinomial P1,Polinomial P2)
{
	String S=Polinomial.Multiply(P1, P2).toString();
	T3.setText(S);
}
private void doSum(Polinomial P1,Polinomial P2)
{
	String S=Polinomial.Sum(P1,P2).toString();
	T3.setText(S);
}
private void doIntegrate(Polinomial P1)
{
	P1.Integrate();
	T3.setText(P1.toString());
}
private void doDerivate(Polinomial P1)
{
	P1.Derivate();
	T3.setText(P1.toString());
}
private void settext()
{
	switch(operation) {
	case 1:
		Current.setText("+");break;
	case 2:
		Current.setText("-");break;
	case 3:
		Current.setText("*");break;
	case 4:
		Current.setText("/");break;
	case 5: 
		Current.setText("derivate");break;
	case 6:
		Current.setText("integrate");break;
	default:
		Current.setText("error");break;
		}
}

}
