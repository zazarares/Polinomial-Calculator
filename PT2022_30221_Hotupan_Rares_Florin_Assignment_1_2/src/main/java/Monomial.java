
public class Monomial {
	private int grad;
	private double coef;
	Monomial(int grad,double coef){
		this.grad=grad;
		this.coef=coef;
	};
	protected int getGrad()
	{
		return grad;
	}
	protected double getCoef()
	{
		return coef;
	}
	protected void setGrad(int Grad)
	{
		this.grad=Grad;	
	}
	protected void setCoef(double Coef)
	{
		this.coef=Coef;
	}
}
