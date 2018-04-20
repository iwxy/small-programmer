package wannianli;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class Calendars extends JFrame implements ItemListener{
	JComboBox jcb[]=new JComboBox[2];
	JLabel jl[]=new JLabel[2];
	Box box;
	JTextArea jta;
	public Calendars(){
		this.setBounds(600,300,600,400);
		this.setTitle("万年历");
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLayout(new BorderLayout());
		init();
		validate();
	}
	
	public void init(){
		jl[0]=new JLabel("年");
		jl[1]=new JLabel("月");
		jta=new JTextArea();
		box=Box.createHorizontalBox();
		int count=2050-1970+1;
		int year[]=new int[count];
		int month[]=new int[12];
		for(int i=0;i<2;i++){
			jcb[i]=new JComboBox();
		}
		jcb[1].addItemListener(this);
		int j=1970;
		for(int i=0;i<count;i++){
			year[i]=j;
			j++;
			jcb[0].addItem(year[i]);
			if(i<12){
				month[i]=i+1;
				jcb[1].addItem(month[i]);
			}
		}
		box.add(Box.createHorizontalStrut(100));
		box.add(jl[0]);
		box.add(Box.createHorizontalStrut(10));
		box.add(jcb[0]);
		box.add(Box.createHorizontalStrut(100));
		box.add(jl[1]);
		box.add(Box.createHorizontalStrut(10));
		box.add(jcb[1]);
		box.add(Box.createHorizontalStrut(100));
		add(box,BorderLayout.NORTH);
		add(jta,BorderLayout.CENTER);
		jcb[0].addItemListener(this);
		
		
	}
	
	public void itemStateChanged(ItemEvent e){	
		int y=(int)jcb[0].getSelectedItem();
		int m=(int)jcb[1].getSelectedItem();
		String xq[]={"日","一","二","三","四","五","六"};
		Calendar c1=Calendar.getInstance();
		c1.set(y,m-1,1);
		int weekday=c1.get(Calendar.DAY_OF_WEEK)-1;
		int day=0;
		if(m==1 || m==3 || m==5 || m==7 || m==8 || m==10 || m==12){
			day=31;
		}
		if(m==4 || m==6 || m==9 || m==11){
			day=30;
		}
		if(m==2){
			if(((y%4==0)&&(y%100!=0)) || (y%400==0)){
				day=29;
			}
			else
				day=28;
		}
		jta.setText("");
		jta.append("\n");
		for(int i=0;i<7;i++){
			jta.append(xq[i]+"\t");
		}
		
		jta.append("\n");
		jta.append("\n");
		String md[]=new String[42];
		for(int i=0;i<weekday;i++){
			md[i]="";
		}
		for(int i=weekday;i<weekday+day;i++){
			md[i]=String.valueOf(i-weekday+1);
			}
		for(int i=weekday+day;i<42;i++){
			md[i]="";
		}
		for(int i=1;i<43;i++){
			jta.append(md[i-1]+"\t");
			if(i%7==0 && i!=0){
				jta.append("\n");
				jta.append("\n");
			}
		}
	}			
}


		
	
	


