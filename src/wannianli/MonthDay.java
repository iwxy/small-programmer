package wannianli;

public class MonthDay {
	int y,m,d;
	public void setY(int y){
		this.y=y;
	}
	public void setM(int m){
		this.m=m;
	}
	
	public int monthday(){
		if(m==1 || m==3 || m==5 || m==7 || m==8 || m==10 || m==12){
			return 31;
		}
		else if(m==4 || m==6 || m==9 || m==11){
			return 30;
		}
		else if(m==2){
			if(((y%4==0)&&(y%100!=0)) || (y%400==0)){
				return 29;
			}
			else
				return 28;
		}
		else
			return 0;
	}

}
