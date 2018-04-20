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
		setTitle("������");
		setBounds(100,100,400,530);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(null);
		init();
	}
	public void init(){
		jtf1=new JTextField();
		jtf2=new JTextField();
		jtf1.setBorder(null);   //����ʾ�ı���ı߽�
		jtf2.setBorder(null);
		jtf1.setBounds(10,10,360,50);   //�����ı���Ĵ�Сλ��
		jtf2.setBounds(10,65,360,50);
		jtf1.setHorizontalAlignment(JTextField.RIGHT);    //����ʱ���ı�����ұ߿�ʼ�����ַ�
		jtf2.setHorizontalAlignment(JTextField.RIGHT);
		jp=new JPanel();      //��������5��4�е�����
		grid=new GridLayout(5,4);
		jp.setLayout(grid);
		menubar=new JMenuBar();
		menu=new JMenu("����");
		item=new JMenuItem("��ͨ");
		menu1=new JMenu("������");
		menu1.addMouseListener(this);
		menu.add(item);
		menubar.add(menu);
		menubar.add(menu1);
		setJMenuBar(menubar);
		jb[0][0]=new JButton("��");        //���ð�ť
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
		jb[2][3]=new JButton("��");
		jb[3][0]=new JButton("1");
		jb[3][1]=new JButton("2");
		jb[3][2]=new JButton("3");
		jb[3][3]=new JButton("+");
		jb[4][0]=new JButton("��");
		jb[4][1]=new JButton("0");
		jb[4][2]=new JButton(".");
		jb[4][3]=new JButton("=");
		for(int i=0;i<5;i++){
			for(int j=0;j<4;j++){
				jb[i][j].addActionListener(this);    //����ť�Ӽ�����
				jp.add(jb[i][j]);
			}
		}
		jp.setBounds(10,130,360,300);     //��������λ��
		add(jtf1);
		add(jtf2);
		add(jp);
		this.validate();
	}
	public void actionPerformed(ActionEvent e){
		//�����ְ�ť��������֮���ı������ʾʲô
		if(e.getActionCommand().matches("[\\d.��]")){
			if(count==1 && x==0){    //��count��x���Ƶڶ����ı���ʲôʱ����գ���Ҫ����
				jtf2.setText("");    //һ��ʽ���еڶ���ʹ�÷���ʱ
				x=1;
			}
			if(count==0 && d==1){    //�����ں�֮��ֱ�Ӱ����ּ�����������ı���
				jtf1.setText("");
				jtf2.setText("");
				d=0;
			}
			if(count==1 && d==1){    //�����ں�֮���ٰ����ţ�ֱ������һ��ʽ�ӵĵ������㣬��������ı���
				d=0;
			}
			if(d==0){     //δ�������ںţ�������ںŲ�����ı���֮�󣬸տ�ʼ����
				String r=e.getActionCommand();
				if(p!=0){    //��p�����ı�����ʾ����������ʾ14��542�Ȳ���һλ����
					String w=jtf2.getText();
					jtf2.setText(w+r);
				}
				else{
					jtf2.setText(r);
					p=1;
				}
			}
		}
		//�����Ű�ť
		if(e.getActionCommand().matches("[/*��+]")){
			if(count==1){     //��һ��ʽ���еڶ����������ʱcount=1
				a2=Double.parseDouble(jtf2.getText());  //�ӵڶ����ı�����a2
				String r1=jtf1.getText();
				String r=jtf2.getText();
				jtf1.setText(r1+r+e.getActionCommand());   //�����ı�����ʾ������
				Jisuan js=new Jisuan(a1,fuhao,a2);
				sum=js.jisuan();
				jtf2.setText(sum);   //���㲢������ڵڶ����ı�������ʾ����
				a1=Double.parseDouble(jtf2.getText());    //�ӵڶ����ı����л��a1��Ϊ����ļ�����׼��
				fuhao=e.getActionCommand();
				x=0;
			}
			if(count==0){     //ʽ���е�һ��������ţ������ı������ʾ�������a1
				a1=Double.parseDouble(jtf2.getText());
				String r=jtf2.getText();
				fuhao=e.getActionCommand();
				jtf1.setText(r+fuhao);
				count=1;
			}
		}
		//�����ں�
		if(e.getSource()==jb[4][3]){
			a2=Double.parseDouble(jtf2.getText());   //���a2���������ı�����ʾ������
			String r=jtf2.getText();
			String r1=jtf1.getText();
			jtf1.setText(r1+r);
			Jisuan js=new Jisuan(a1,fuhao,a2);    //���㣬�ڵڶ����ı�������ʾ���
			sum=js.jisuan();
			jtf2.setText(sum);
			d=1;
			count=0;
			x=0;
		}
		//��CE����յڶ����ı���
		if(e.getSource()==jb[0][1]){
			jtf2.setText("");
			
		}
		//��C����������ı���,���ж����ع��ʼ״̬
		if(e.getSource()==jb[0][2]){
			jtf1.setText("");
			jtf2.setText("");
			count=0;x=0;d=0;
		}
		//���˸�����ӵڶ����ı�����ɾ��һλ��
		if(e.getSource()==jb[0][0]){
			String r=jtf2.getText();
			jtf2.setText(r.substring(0,r.length()-1));
			
		}
		
	}
	//"������"�ļ�����
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		if(e.getClickCount()==1){
			JOptionPane.showMessageDialog(this,"���ߣ�������\n ����ʱ�䣺2017.10.26\n ��Ʒ�����׼�����",
					"��Ϣ��",JOptionPane.WARNING_MESSAGE);
		}
	}
	public void mousePressed(MouseEvent e) {}
	public void mouseReleased(MouseEvent e) {}
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}

}
