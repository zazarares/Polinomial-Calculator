import java.util.*;
public class Polinomial extends RuntimeException{
	private ArrayList<Monomial> P;
	Polinomial(ArrayList<Monomial> P)
	{
		this.P=new ArrayList<Monomial>(P);
	}
	Polinomial()
	{ 
		P=new ArrayList<Monomial>();
	}
	protected void SetArrayList(ArrayList<Monomial> L)
	{
		int i=0;
		P.clear();
		while(i<L.size())
			{
			P.add(L.get(i));
			i++;
			}
		
	}
	private void error(int ok)
	{
			if(ok==0)
				throw new RuntimeException("Polinom invalid");
	}
	private int check(char a,char b)
	{
		if(a<='z' && a>= 'a')
			if(b=='^')
				return 1;
			return 0;
	}
	private int secondcheck(int ok,char c)
	{
		if(ok==0)
			if(c=='+' || c=='-')
				return 2;
			else
				return 0;
		return 1;
	}
	private int errorcheck(int ok,char i,char j)
	{
		error(ok);
		ok=check(i,j);
		ok=secondcheck(ok,i);
		error(ok);
		return ok;
	}
	public void SetArrayFromString(String S)
	{
		P.clear();
		int i=0;
		int ok=0;
		mainloop:
		while(i<S.length())
		{
			double number=0;
			String num="";
			if(i==0 && S.charAt(0)=='-')
				i++;
			if(i==0 || S.charAt(i-1)=='+')
				{while((S.charAt(i)>='0' && S.charAt(i)<='9') || S.charAt(i)=='.')
					{
					num+=S.charAt(i++);
					if(i==S.length())
						{
						P.add(new Monomial(0,Double.parseDouble(num)));
						break mainloop;
						}
					}
				if(num=="")
					num="1";
				number=Double.parseDouble(num);ok=1;
					}
			else
			if(S.charAt(i-1)=='-')
				{
			while((S.charAt(i)>='0' && S.charAt(i)<='9')|| S.charAt(i)=='.')
			{
				num+=S.charAt(i++);
				ok=1;
			}
			number=-Double.parseDouble(num);
			}
			ok=errorcheck(ok,S.charAt(i),S.charAt(i+1));		
			if(ok==1)
			{
			i+=2;
			int power=0;
			while(i<S.length() && (S.charAt(i)>='0' && S.charAt(i)<='9'))
			{
			power=power*10+S.charAt(i)-'0';i++;ok=1;}
			P.add(new Monomial(power,number));
			error(ok);
			}
			if(ok==2)
				P.add(new Monomial(0,number));
			i++;
		}
	}
	@Override
	public String toString()
	{
		String S=new String();
		S="";
		int i=0;
		S=Double.toString(P.get(i).getCoef());
		S=S+"x^";
		S=S+Integer.toString(P.get(i).getGrad());
		i++;
		while(i<P.size())
		{
			if(P.get(i).getCoef()>=0)
				S=S+'+';
			S+=Double.toString(P.get(i).getCoef());
			S=S+"x^";
			S=S+Integer.toString(P.get(i).getGrad());
			i++;
		}
		return S;
	}
	public static Polinomial Sum(Polinomial P1,Polinomial P2)
	{
		Polinomial S=new Polinomial();
		S.GetArray().clear();
		Iterator<Monomial> i=P1.GetArray().iterator();
		while(i.hasNext())
		{
			Monomial M=i.next();
			Iterator<Monomial> j= P2.GetArray().iterator();
			while(j.hasNext())
			{
				Monomial P=j.next();
				if(M.getGrad()==P.getGrad())
					{
					S.GetArray().add(new Monomial(M.getGrad(),M.getCoef()+P.getCoef()));
					i.remove();
					j.remove();
					}
			}
		}
		for(Monomial j:P2.P)
		{S.GetArray().add(new Monomial(j.getGrad(),j.getCoef()));}
		for(Monomial j:P1.P)
		{S.GetArray().add(new Monomial (j.getGrad(),j.getCoef()));}
		return S;
	}
	public static Polinomial Difference(Polinomial P1,Polinomial P2)
	{
		Polinomial S=new Polinomial();
		S.GetArray().clear();
		Iterator<Monomial> i= 	P1.GetArray().iterator();
		
		while(i.hasNext())
		{
			Monomial M=i.next();
			Iterator<Monomial> j= P2.GetArray().iterator();
			while(j.hasNext())
			{
				Monomial P=j.next();
				if(M.getGrad()==P.getGrad())
					{
					S.GetArray().add(new Monomial(M.getGrad(),M.getCoef()-P.getCoef()));
					i.remove();
					j.remove();
					}
			}
		}
		for(Monomial j:P2.P)
			{S.GetArray().add(new Monomial(j.getGrad(),-j.getCoef()));}
		for(Monomial j:P1.P)
			{S.GetArray().add(new Monomial (j.getGrad(),j.getCoef()));}
		return S;
	}
	public void Integrate()
	{
		for(Monomial i:this.P)
		{
			i.setGrad(i.getGrad()+1);
			i.setCoef(i.getCoef()/(double)i.getGrad());
		}
	}
	public void Derivate()
	{
		for(Monomial i:this.P)
		{
			i.setCoef(i.getCoef()*(double)i.getGrad());
			i.setGrad(i.getGrad()-1);
		}
	}
	public static Polinomial Multiply(Polinomial P1,Polinomial P2)
	{
		Polinomial S=new Polinomial();
		S.GetArray().clear();
		for(Monomial i:P1.P)
		{
			for(Monomial j:P2.P)
			{
				S.GetArray().add(new Monomial(i.getGrad()+j.getGrad(),i.getCoef()*j.getCoef()));
			}
		}
		for(int i=0;i<S.P.size();i++)
		{
			for(int j=0;j<S.P.size();j++)
			{
				if(S.P.get(i).getGrad()==S.P.get(j).getGrad() && i!=j)
					{
					S.P.get(i).setCoef(S.P.get(i).getCoef()+S.P.get(j).getCoef());
					S.P.remove(j);
					}
			}
		}
		return S;
	}
	protected ArrayList<Monomial> GetArray()
	{
		return P;
	}
	
}
