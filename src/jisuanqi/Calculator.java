package jisuanqi;

import java.awt.*;
import java.awt.event.*;
import java.text.DecimalFormat;

import javax.swing.*;


public class Calculator extends JFrame implements ActionListener,MouseListener{
	double a1=0,a2=0;
	int count=0,x=0,d=0,p=0;
	String fuhao,sum;
	JTextField jtf1,jtf2;
	JButton jb[][]=new JButton[5][4];
	JPanel jp;
	GridLayout grid;
	JMenuBar menubar;
	JMenu menu,menu1;
	JMenuItem item;
	public Calculator(){
		setTitle("计算器");
		setBounds(100,100,400,530);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(null);
		init();
	}
	public void init(){
		jtf1=new JTextField();
		jtf2=new JTextField();
		jtf1.setBorder(null);   //不显示文本框的边界
		jtf2.setBorder(null);
		jtf1.setBounds(10,10,360,50);   //设置文本框的大小位置
		jtf2.setBounds(10,65,360,50);
		jtf1.setHorizontalAlignment(JTextField.RIGHT);    //输入时，文本框从右边开始出现字符
		jtf2.setHorizontalAlignment(JTextField.RIGHT);
		jp=new JPanel();      //面板上面加5行4列的网格
		grid=new GridLayout(5,4);
		jp.setLayout(grid);
		menubar=new JMenuBar();
		menu=new JMenu("类型");
		item=new JMenuItem("普通");
		menu1=new JMenu("关于我");
		menu1.addMouseListener(this);
		menu.add(item);
		menubar.add(menu);
		menubar.add(menu1);
		setJMenuBar(menubar);
		jb[0][0]=new JButton("←");        //设置按钮
		jb[0][1]=new JButton("CE");
		jb[0][2]=new JButton("C");
		jb[0][3]=new JButton("/");
		jb[1][0]=new JButton("7");
		jb[1][1]=new JButton("8");
		jb[1][2]=new JButton("9");
		jb[1][3]=new JButton("*");
		jb[2][0]=new JButton("4");
		jb[2][1]=new JButton("5");
		jb[2][2]=new JButton("6");
		jb[2][3]=new JButton("－");
		jb[3][0]=new JButton("1");
		jb[3][1]=new JButton("2");
		jb[3][2]=new JButton("3");
		jb[3][3]=new JButton("+");
		jb[4][0]=new JButton("±");
		jb[4][1]=new JButton("0");
		jb[4][2]=new JButton(".");
		jb[4][3]=new JButton("=");
		for(int i=0;i<5;i++){
			for(int j=0;j<4;j++){
				jb[i][j].addActionListener(this);    //给按钮加监视器
				jp.add(jb[i][j]);
			}
		}
		jp.setBounds(10,130,360,300);     //设置面板的位置
		add(jtf1);
		add(jtf2);
		add(jp);
		this.validate();
	}
	public void actionPerformed(ActionEvent e){
		//按数字按钮，按数字之后文本框该显示什么
		if(e.getActionCommand().matches("[\\d.±]")){
			if(count==1 && x==0){    //用count和x控制第二个文本框什么时候清空，主要是在
				jtf2.setText("");    //一个式子中第二次使用符号时
				x=1;
			}
			if(count==0 && d==1){    //按等于号之后直接按数字键，清空两个文本框
				jtf1.setText("");
				jtf2.setText("");
				d=0;
			}
			if(count==1 && d==1){    //按等于号之后再按符号，直接用上一个式子的得数计算，不用清空文本框
				d=0;
			}
			if(d==0){     //未按过等于号，或按完等于号并清空文本框之后，刚开始计算
				String r=e.getActionCommand();
				if(p!=0){    //用p控制文本框显示的数，可显示14，542等不是一位的数
					String w=jtf2.getText();
					jtf2.setText(w+r);
				}
				else{
					jtf2.setText(r);
					p=1;
				}
			}
		}
		//按符号按钮
		if(e.getActionCommand().matches("[/*－+]")){
			if(count==1){     //在一个式子中第二次输入符号时count=1
				a2=Double.parseDouble(jtf2.getText());  //从第二个文本框获得a2
				String r1=jtf1.getText();
				String r=jtf2.getText();
				jtf1.setText(r1+r+e.getActionCommand());   //设置文本框显示的内容
				Jisuan js=new Jisuan(a1,fuhao,a2);
				sum=js.jisuan();
				jtf2.setText(sum);   //计算并将结果在第二个文本框中显示出来
				a1=Double.parseDouble(jtf2.getText());    //从第二个文本框中获得a1，为下面的计算做准备
				fuhao=e.getActionCommand();
				x=0;
			}
			if(count==0){     //式子中第一次输入符号，设置文本框的显示，并获得a1
				a1=Double.parseDouble(jtf2.getText());
				String r=jtf2.getText();
				fuhao=e.getActionCommand();
				jtf1.setText(r+fuhao);
				count=1;
			}
		}
		//按等于号
		if(e.getSource()==jb[4][3]){
			a2=Double.parseDouble(jtf2.getText());   //获得a2，并设置文本框显示的内容
			String r=jtf2.getText();
			String r1=jtf1.getText();
			jtf1.setText(r1+r);
			Jisuan js=new Jisuan(a1,fuhao,a2);    //计算，在第二个文本框中显示结果
			sum=js.jisuan();
			jtf2.setText(sum);
			d=1;
			count=0;
			x=0;
		}
		//按CE，清空第二个文本框
		if(e.getSource()==jb[0][1]){
			jtf2.setText("");
			
		}
		//按C，清空两个文本框,所有东西回归初始状态
		if(e.getSource()==jb[0][2]){
			jtf1.setText("");
			jtf2.setText("");
			count=0;x=0;d=0;
		}
		//按退格键，从第二个文本框中删除一位数
		if(e.getSource()==jb[0][0]){
			String r=jtf2.getText();
			jtf2.setText(r.substring(0,r.length()-1));
			
		}
		
	}
	//"关于我"的监视器
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		if(e.getClickCount()==1){
			JOptionPane.showMessageDialog(this,"作者：王欣妍\n 创作时间：2017.10.26\n 作品：简易计算器",
					"消息框",JOptionPane.WARNING_MESSAGE);
		}
	}
	public void mousePressed(MouseEvent e) {}
	public void mouseReleased(MouseEvent e) {}
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}

}
