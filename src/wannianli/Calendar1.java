package wannianli;

import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Calendar;

import javax.swing.*;

public class Calendar1 extends JFrame implements ItemListener {
	JPanel jp=new JPanel();	
	GridLayout g=new GridLayout(7,7);
	JButton button[][]=new JButton[7][7];
	JComboBox jcb[]=new JComboBox[3];
	JLabel jl[]=new JLabel[3];
	Box box;
	int x;
	public Calendar1(){
		this.setBounds(600,300,600,400);
		this.setTitle("万年历");
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLayout(new BorderLayout());
		jp.setLayout(g);
		init();
		validate();
	}
	
	public void init(){
		jl[0]=new JLabel("年");
		jl[1]=new JLabel("月");
		jl[2]=new JLabel("日");
		for(int i=0;i<7;i++){
			for(int j=0;j<7;j++){
				button[i][j]=new JButton("");
				button[i][j].setBackground(Color.WHITE);
				button[i][j].setBorder(null);
				jp.add(button[i][j]);
			}
		}
		box=Box.createHorizontalBox();
		int count=2050-1970+1;
		int year[]=new int[count];
		int month[]=new int[12];
		for(int i=0;i<3;i++){
			jcb[i]=new JComboBox();
		}
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
		jcb[1].addItemListener(new ItemListener(){
			public void itemStateChanged(ItemEvent e){
				x=1;
				jcb[2].removeAllItems();
				int y=(int)jcb[0].getSelectedItem();
				int m=(int)jcb[1].getSelectedItem();
				MonthDay md=new MonthDay();
				md.setY(y);
				md.setM(m);
				int day1=md.monthday();
				int day[]=new int[day1];
				for(int i=0;i<day1;i++){
					day[i]=i+1;
					jcb[2].addItem(day[i]);
				}
				x=0;
			}
		});
		box.add(Box.createHorizontalStrut(50));
		box.add(jl[0]);
		box.add(Box.createHorizontalStrut(10));
		box.add(jcb[0]);
		box.add(Box.createHorizontalStrut(40));
		box.add(jl[1]);
		box.add(Box.createHorizontalStrut(10));
		box.add(jcb[1]);
		box.add(Box.createHorizontalStrut(40));
		box.add(jl[2]);
		box.add(Box.createHorizontalStrut(10));
		box.add(jcb[2]);
		box.add(Box.createHorizontalStrut(50));
		add(box,BorderLayout.NORTH);
		add(jp,BorderLayout.CENTER);
		jcb[2].addItemListener(this);
	}
	
	public void itemStateChanged(ItemEvent e){
		if(x==0){
			int y=(int)jcb[0].getSelectedItem();
			int m=(int)jcb[1].getSelectedItem();
			int d=(int)jcb[2].getSelectedItem();
			String xq[]={"日","一","二","三","四","五","六"};;
			Calendar c1=Calendar.getInstance();
			c1.set(y,m-1,1);
			int weekday=c1.get(Calendar.DAY_OF_WEEK)-1;
			MonthDay md=new MonthDay();
			md.setY(y);
			md.setM(m);
			int day=md.monthday();
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
						button[i][j].setText(xq[j]);
					}
					else{
						button[i][j].setText(a[count]);
						if(a[count].equals(String.valueOf(d))){
							button[i][j].setBackground(Color.CYAN);
						}
						count++;
					}
				}
			}
		}
	}	
}
	


