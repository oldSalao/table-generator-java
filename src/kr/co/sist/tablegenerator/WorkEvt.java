package kr.co.sist.tablegenerator;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

public class WorkEvt implements ActionListener {

	private Work w;
	private StringBuilder sb;
	private boolean colFlag;
	private boolean priFlag;
	private boolean nnFlag;
	private boolean constFlag;
	private List<String> colList;

	public WorkEvt(Work w) {
		this.w = w;
		sb = new StringBuilder();
		colList = new ArrayList<String>();

		w.getJbtnTset().addActionListener(this);
		w.getJbtnCset().addActionListener(this);
		w.getJbtnCreate().addActionListener(this);
		w.getJbtnClear().addActionListener(this);
		w.getJchkp().addActionListener(this);
		w.getJchkn().addActionListener(this);
		w.getJcb().addActionListener(this);
	}

	public void setTableName(String tName) {
		sb.append("CREATE TABLE ").append(tName).append("(\n)");
		w.getJta().setText(sb.toString());
	}

	public void setColumn(String cName, String type, int size) {
		StringBuilder temp = new StringBuilder();
		String constraint = "";
		String prec = "";

		if (!type.equals("DATE")) {
			prec = "(" + size + ")";
		}

		if (w.getJchkp().isSelected()) {
			constraint = " PRIMARY KEY";
			constFlag = true;
		} else if (w.getJchkn().isSelected()) {
			constraint = " NOT NULL";
		}

		if (!colFlag) {
			temp.append("  ").append(cName).append(" ").append(type).append(prec).append(constraint);
			sb.insert(sb.indexOf(")"), temp.toString());
			sb.insert(sb.length() - 1, "\n");
			colFlag = true;
		} else {
			temp.append(",\n  ").append(cName).append(" ").append(type).append(prec).append(constraint);
			sb.insert(sb.length() - 2, temp.toString());
		}
		w.getJta().setText(sb.toString());
		colList.add(cName);
	}

	public void setConstraint() {

	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == w.getJbtnTset()) {
			if (w.getJta().getText().equals("")) {
				setTableName(w.getJtfTname().getText());
			} else {
				JOptionPane.showMessageDialog(w, "이미 테이블명이 정의되어 있습니다.");
			}
		}
		if (e.getSource() == w.getJbtnCset()) {
			if (!w.getJta().getText().equals("")) {
				boolean flag = false;
				int prec = 0;
				String type = (String) w.getJcb().getSelectedItem();
				String cName = w.getJtfCname().getText();
				try {
					if (!type.equals("DATE")) {
						prec = Integer.parseInt(w.getJtfPre().getText());
					}

					if (type.equals("NUMBER")) {
						if (prec > 0 && prec < 39) {
							flag = true;
						}
					} else if (type.equals("CHAR")) {
						if (prec > 0 && prec < 2001) {
							flag = true;
						}
					} else if (type.equals("VARCHAR2")) {
						if (prec > 0 && prec < 4001) {
							flag = true;
						}
					} else {
						flag = true;
					}

					if (flag) {
						if ((!constFlag) || (!w.getJchkp().isSelected())) {
							if (((colList.indexOf(cName.toLowerCase()) < 0)
									&& (colList.indexOf(cName.toUpperCase()) < 0))) {
								setColumn(w.getJtfCname().getText(), type, prec);
							} else {
								JOptionPane.showMessageDialog(w, "동일한 이름의 컬럼이 이미 존재합니다.");
							}
						} else {
							JOptionPane.showMessageDialog(w, "primary key는 1개만 설정 가능합니다.");
						}
					} else {
						JOptionPane.showMessageDialog(w, "크기가 너무 큽니다.");
					}
				} catch (NumberFormatException ne) {
					JOptionPane.showMessageDialog(w, "크기는 숫자만 입력하세요");
				}
			} else {
				JOptionPane.showMessageDialog(w, "테이블명을 먼저 설정해주세요.");
			}
		}
		if (e.getSource() == w.getJcb()) {
			if (((String) w.getJcb().getSelectedItem()).equals("DATE")) {
				w.getJtfPre().setText("");
				w.getJtfPre().setEditable(false);
			} else {
				w.getJtfPre().setEditable(true);
			}
		}
		if (e.getSource() == w.getJchkp()) {
			nnFlag = false;
			if (priFlag) {
				w.getBg().clearSelection();
				priFlag = false;
			} else {
				priFlag = true;
			}
		}
		if (e.getSource() == w.getJchkn()) {
			priFlag = false;
			if (nnFlag) {
				w.getBg().clearSelection();
				nnFlag = false;
			} else {
				nnFlag = true;
			}
		}
		if (e.getSource() == w.getJbtnCreate()) {

		}
		if (e.getSource() == w.getJbtnClear()) {
			w.getJta().setText("");
			sb = new StringBuilder();
			colFlag = false;
			constFlag = false;
			colList.clear();
		}
	}
}