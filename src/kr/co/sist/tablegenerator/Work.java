package kr.co.sist.tablegenerator;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.Box;
import javax.swing.ButtonGroup;
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

	private JTextArea jta;
	private JButton jbtnTset;
	private JButton jbtnCset;
	private JButton jbtnCreate;
	private JButton jbtnClear;
	private JTextField jtfTname;
	private JTextField jtfCname;
	private JTextField jtfPre;
	private DefaultComboBoxModel<String> dcbm;
	private JComboBox<String> jcb;
	private JCheckBox jchkp;
	private JCheckBox jchkn;
	private ButtonGroup bg;

	public Work() {
		super("���̺� ����");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(700, 500);
		setLocationRelativeTo(null);

		String[] typeArr = { "NUMBER", "CHAR", "VARCHAR2", "DATE" };

		FlowLayout flL = new FlowLayout(FlowLayout.LEFT);
		FlowLayout flR = new FlowLayout(FlowLayout.RIGHT);
		JPanel[] jpIn = new JPanel[3];
		JPanel jpInfo = new JPanel();
		JPanel jp = new JPanel();
		bg = new ButtonGroup();

		jpInfo.setLayout(new GridLayout(jpIn.length, 1));
		jp.setLayout(flR);
		for (int i = 0; i < jpIn.length; i++) {
			jpIn[i] = new JPanel();
			jpIn[i].setLayout(flL);
		}

		JLabel jlt = new JLabel("���̺�� : ");
		JLabel jlc = new JLabel("�÷��� : ");
		JLabel jld = new JLabel("�������� : ");
		JLabel jls = new JLabel("ũ�� : ");
		JLabel jlp = new JLabel("Primary Key");
		JLabel jln = new JLabel("Not Null");

		jbtnTset = new JButton("����");
		jbtnCset = new JButton("�߰�");
		jbtnCreate = new JButton("����");
		jbtnClear = new JButton("�ʱ�ȭ");
		jtfTname = new JTextField();
		jtfCname = new JTextField();
		jtfPre = new JTextField();

		jtfTname.setColumns(6);
		jtfCname.setColumns(6);
		jtfPre.setColumns(6);

		jta = new JTextArea();
		jta.setEditable(false);

		dcbm = new DefaultComboBoxModel<String>();
		for (int i = 0; i < typeArr.length; i++) {
			dcbm.addElement(typeArr[i]);
		}
		jcb = new JComboBox<String>(dcbm);
		jchkp = new JCheckBox();
		jchkn = new JCheckBox();
		bg.add(jchkn);
		bg.add(jchkp);

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

		jp.add(jbtnClear);
		jp.add(jbtnCreate);

		WorkEvt we = new WorkEvt(this);

		jbtnTset.addActionListener(we);
		jbtnCset.addActionListener(we);
		jbtnCreate.addActionListener(we);
		jbtnClear.addActionListener(we);
		jchkp.addActionListener(we);
		jchkn.addActionListener(we);
		jcb.addActionListener(we);

		this.add(jpInfo, BorderLayout.NORTH);
		this.add(Box.createHorizontalStrut(10), BorderLayout.WEST);
		this.add(jta, BorderLayout.CENTER);
		this.add(Box.createHorizontalStrut(10), BorderLayout.EAST);
		this.add(jp, BorderLayout.SOUTH);

		setVisible(true);
	}

	public ButtonGroup getBg() {
		return bg;
	}

	public JButton getJbtnClear() {
		return jbtnClear;
	}

	public JTextArea getJta() {
		return jta;
	}

	public JButton getJbtnTset() {
		return jbtnTset;
	}

	public JButton getJbtnCset() {
		return jbtnCset;
	}

	public JButton getJbtnCreate() {
		return jbtnCreate;
	}

	public JTextField getJtfTname() {
		return jtfTname;
	}

	public JTextField getJtfCname() {
		return jtfCname;
	}

	public JTextField getJtfPre() {
		return jtfPre;
	}

	public DefaultComboBoxModel<String> getDcbm() {
		return dcbm;
	}

	public JComboBox<String> getJcb() {
		return jcb;
	}

	public JCheckBox getJchkp() {
		return jchkp;
	}

	public JCheckBox getJchkn() {
		return jchkn;
	}

}
