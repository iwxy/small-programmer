package wannianli;

import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Calendar;

import javax.swing.*;

public class Calendar2 extends JFrame implements ItemListener {
	JPanel panel=new JPanel();	
	GridLayout grid=new GridLayout(7,7);
	JLabel l[][]=new JLabel[7][7];
	JComboBox b[]=new JComboBox[2];
	JLabel label[]=new JLabel[2];
	Box box;
	int x=1,day;
	public Calendar2(){
		this.setBounds(600,300,600,400);
		this.setTitle("万年历");
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLayout(new BorderLayout());
		panel.setLayout(grid);
		init();
		validate();
	}
	public void init(){
		label[0]=new JLabel("年");
		label[1]=new JLabel("月");
		for(int i=0;i<7;i++){
			for(int j=0;j<7;j++){
				l[i][j]=new JLabel("",JLabel.CENTER);  //标签居中
				panel.add(l[i][j]);
			}
		}
		box=Box.createHorizontalBox();
		int count=2050-1970+1;
		int year[]=new int[count];
		int month[]=new int[12];
		for(int i=0;i<2;i++){
			b[i]=new JComboBox();
		}
		int j=1970;
		for(int i=0;i<count;i++){
			year[i]=j;
			j++;
			b[0].addItem(year[i]);
			if(i<12){
				month[i]=i+1;
				b[1].addItem(month[i]);
			}
		}
		x=0;
		box.add(Box.createHorizontalStrut(70));
		box.add(label[0]);
		box.add(Box.createHorizontalStrut(10));
		box.add(b[0]);
		box.add(Box.createHorizontalStrut(50));
		box.add(label[1]);
		box.add(Box.createHorizontalStrut(10));
		box.add(b[1]);
		box.add(Box.createHorizontalStrut(70));
		add(box,BorderLayout.NORTH);
		add(panel,BorderLayout.CENTER);
		b[1].addItemListener(this);
	}
	
	public void itemStateChanged(ItemEvent e){
		if(x==0){
			int y=(int)b[0].getSelectedItem();
			int m=(int)b[1].getSelectedItem();
			String xq[]={"日","一","二","三","四","五","六"};;
			Calendar c1=Calendar.getInstance();
			c1.set(y,m-1,1);
			int weekday=c1.get(Calendar.DAY_OF_WEEK)-1;
			if(m==1 || m==3 || m==5 || m==7 || m==8 || m==10 || m==12){
				day=31;
			}
			else if(m==4 || m==6 || m==9 || m==11){
				day=30;
			}
			else if(m==2){
				if(((y%4==0)&&(y%100!=0)) || (y%400==0)){
					day=29;
				}
				else
					day=28;
			}
			String a[]=new String[42];
			for(int i=0;i<weekday;i++){
				a[i]=" ";
			}
			for(int i=weekday;i<weekday+day;i++){
				a[i]=String.valueOf(i-weekday+1);
			}
			for(int i=weekday+day;i<a.length;i++){
				a[i]=" ";
			}
			int count=0;
			for(int i=0;i<7;i++){
				for(int j=0;j<7;j++){
					if(i==0){
						l[i][j].setText(xq[j]);
					}
					else{
						l[i][j].setText(a[count]);
						count++;
					}
				}
			}
		}
	}
	public static void main(String[] args) {
		
		new Calendar2();

	}
}
	



