package kr.co.sist.tablegenerator;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

public class WorkEvt implements ActionListener {

	private Work w;
	private StringBuilder sb;
	private boolean colFlag;
	private boolean priFlag;
	private boolean nnFlag;
	private List<String> colList;

	public WorkEvt(Work w) {
		this.w = w;
		sb = new StringBuilder();
		colList = new ArrayList<String>();
	}

	public void setTableName(String tName) {
		sb.append("CREATE TABLE ").append(tName).append("(\n)");
		w.getJta().setText(sb.toString());
	}

	public void setColumn(String cName, String type, String size) {
		StringBuilder temp = new StringBuilder();
		String constraint = "";

		if (!(type.equals("DATE") || size.equals(""))) {
			size = "(" + size + ")";
		}

		if (w.getJchkp().isSelected()) {
			constraint = " PRIMARY KEY";
		} else if (w.getJchkn().isSelected()) {
			constraint = " NOT NULL";
		}

		if (!colFlag) {
			temp.append("  ").append(cName).append(" ").append(type).append(size).append(constraint);
			sb.insert(sb.indexOf(")"), temp.toString());
			sb.insert(sb.length() - 1, "\n");
			colFlag = true;
		} else {
			temp.append(",\n  ").append(cName).append(" ").append(type).append(size).append(constraint);
			sb.insert(sb.length() - 2, temp.toString());
		}
		w.getJta().setText(sb.toString());
		colList.add(cName);
	}

	public void clearAll() {
		w.getJta().setText("");
		colFlag = false;
		sb = new StringBuilder();
		colList.clear();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == w.getJbtnTset()) {
			if (!w.getJtfTname().getText().equals("")) {
				if (w.getJta().getText().equals("")) {
					setTableName(w.getJtfTname().getText().toUpperCase());
				} else {
					JOptionPane.showMessageDialog(w, "�̹� ���̺���� ���ǵǾ� �ֽ��ϴ�.");
				}
			} else {
				JOptionPane.showMessageDialog(w, "���̺���� �Է��ϼ���.");
			}
		}
		if (e.getSource() == w.getJbtnCset()) {
			if (!w.getJtfCname().getText().equals("")) {
				if (!w.getJta().getText().equals("")) {
					String size = w.getJtfPre().getText();
					String type = (String) w.getJcb().getSelectedItem();
					String cName = w.getJtfCname().getText().toUpperCase();

					if (colList.indexOf(cName) < 0) {
						setColumn(cName, type, size);
					} else {
						JOptionPane.showMessageDialog(w, "������ �̸��� �÷��� �̹� �����մϴ�.");
					}
				} else {
					JOptionPane.showMessageDialog(w, "���̺���� ���� �������ּ���.");
				}
			} else {
				JOptionPane.showMessageDialog(w, "�÷����� �Է����ּ���.");
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
			WorkDAO wDAO = WorkDAO.getInstance();
			Connection con = null;
			PreparedStatement pstmt = null;
			String createTable = w.getJta().getText();
			try {
				con = wDAO.getConnection();
				pstmt = con.prepareStatement(createTable);
				pstmt.executeUpdate(createTable);
				JOptionPane.showMessageDialog(w, "���̺��� �����Ǿ����ϴ�.");
			} catch (SQLException se) {
				JOptionPane.showMessageDialog(w, "���̺� ���� �� ���� �߻�. ���� : " + se.getMessage());
			} finally {
				wDAO.close(con, pstmt);
				clearAll();
			}
		}
		if (e.getSource() == w.getJbtnClear()) {
			clearAll();
		}
	}
}