package kr.co.sist.tablegenerator;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.Box;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class Work extends JFrame {

	private WorkEvt we;
	private JTextArea jta;
	private JButton jbtnTset;
	private JButton jbtnCset;
	private JButton jbtnCreate;
	private JTextField jtfTname;
	private JTextField jtfCname;
	private JTextField jtfPre;
	private DefaultComboBoxModel<String> dcbm;
	private JComboBox<String> jcb;
	private JCheckBox jchkp;
	private JCheckBox jchkn;

	public Work() {
		super("테이블 생성");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(700, 500);
		setLocationRelativeTo(null);

		FlowLayout fl = new FlowLayout(FlowLayout.LEFT);
		JPanel[] jpIn = new JPanel[3];
		JPanel jpInfo = new JPanel();
		JPanel jp = new JPanel();

		jpInfo.setLayout(new GridLayout(jpIn.length, 1));
		jp.setLayout(fl);
		for (int i = 0; i < jpIn.length; i++) {
			jpIn[i] = new JPanel();
			jpIn[i].setLayout(fl);
		}

		JLabel jlt = new JLabel("테이블명 : ");
		JLabel jlc = new JLabel("컬럼명 : ");
		JLabel jld = new JLabel("데이터형 : ");
		JLabel jls = new JLabel("크기 : ");
		JLabel jlp = new JLabel("Primary Key");
		JLabel jln = new JLabel("Not Null");

		jbtnTset = new JButton("설정");
		jbtnCset = new JButton("설정");
		jbtnCreate = new JButton("생성");
		jtfTname = new JTextField();
		jtfCname = new JTextField();
		jtfPre = new JTextField();

		jtfTname.setColumns(6);
		jtfCname.setColumns(6);
		jtfPre.setColumns(6);

		jta = new JTextArea();
		jta.setEditable(false);

		dcbm = new DefaultComboBoxModel<String>();
		jcb = new JComboBox<String>();
		jchkp = new JCheckBox();
		jchkn = new JCheckBox();

		jpIn[0].add(Box.createHorizontalStrut(10));
		jpIn[0].add(jlt);
		jpIn[0].add(Box.createHorizontalStrut(10));
		jpIn[0].add(jtfTname);
		jpIn[0].add(Box.createHorizontalStrut(10));
		jpIn[0].add(jbtnTset);

		jpIn[1].add(Box.createHorizontalStrut(10));
		jpIn[1].add(jlc);
		jpIn[1].add(Box.createHorizontalStrut(10));
		jpIn[1].add(jtfCname);
		jpIn[1].add(Box.createHorizontalStrut(10));
		jpIn[1].add(jld);
		jpIn[1].add(Box.createHorizontalStrut(10));
		jpIn[1].add(jcb);
		jpIn[1].add(Box.createHorizontalStrut(10));
		jpIn[1].add(jls);
		jpIn[1].add(Box.createHorizontalStrut(10));
		jpIn[1].add(jtfPre);

		jpIn[2].add(Box.createHorizontalStrut(10));
		jpIn[2].add(jchkp);
		jpIn[2].add(Box.createHorizontalStrut(10));
		jpIn[2].add(jlp);
		jpIn[2].add(Box.createHorizontalStrut(10));
		jpIn[2].add(jchkn);
		jpIn[2].add(Box.createHorizontalStrut(10));
		jpIn[2].add(jln);
		jpIn[2].add(Box.createHorizontalStrut(10));
		jpIn[2].add(jbtnCset);
		jpIn[2].add(Box.createHorizontalGlue());

		jpInfo.add(jpIn[0]);
		jpInfo.add(jpIn[1]);
		jpInfo.add(jpIn[2]);

		jp.add(jbtnCreate);

		this.add(jpInfo, BorderLayout.NORTH);
		this.add(Box.createHorizontalStrut(10), BorderLayout.WEST);
		this.add(jta, BorderLayout.CENTER);
		this.add(Box.createHorizontalStrut(10), BorderLayout.EAST);
		this.add(jp, BorderLayout.SOUTH);

		setVisible(true);
	}

	public static void main(String[] args) {
		new Work();
	}
}
