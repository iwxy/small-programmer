package jisuanqi;

import java.text.DecimalFormat;

public class Jisuan {
	double a1,a2,sum;
	String fuhao,s;
	public Jisuan(double a,String f,double b){
		a1=a;
		a2=b;
		fuhao=f;
	}
	public void ji(){
		if(fuhao.equals("/")){
			sum=a1/a2;
		}
		if(fuhao.equals("*")){
			sum=a1*a2;
		}
		if(fuhao.equals("+")){
			sum=a1+a2;
		}
		if(fuhao.equals("－")){
			sum=a1-a2;
		}
	}
	public String jisuan(){    //去掉小数点后面无意义的0
		ji();
		String s=String.valueOf(sum);
		int a=s.length();
		if(s.substring(a-1,a).equals("0")){
			s=s.substring(0,a-2);
		}
		return s;
	}

}
