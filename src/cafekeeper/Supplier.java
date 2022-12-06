package cafekeeper;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.TextField;
import java.util.ArrayList;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

public class Supplier extends JFrame {

	private JPanel contentPane;

	private static ArrayList<JLabel> dataFields = new ArrayList<>();
	private JTable table;

	static String first;
	static String last;
	static int id;
	static String search;
	static DefaultTableModel model = new DefaultTableModel();

	

	String day = "";
	JDialog d;
	JButton[] button = new JButton[49];

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) throws Exception {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Supplier frame = new Supplier();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

	}

	Connection connection = null;
	private JTextField textFirst;
	private JTextField textlast;
	private JTextField txtSearch;

	/**
	 * Create the frame.
	 * 
	 * @throws Exception
	 */
	public Supplier () throws Exception {
		connection = SqlConnection.dbConnector();

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 660, 390);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(25, 149, 427, 193);
		contentPane.add(scrollPane_1);

		table = new JTable();
		scrollPane_1.setViewportView(table);

		Object[] column = { "id", "first", "last", "gender", "class", "email", "discount"};
		model.setColumnIdentifiers(column);
		table.setModel(model);
		Object[] row = new Object[10];
		// Object [] newrow = new Object[3];
		
		String[] list = { "one", "two", "three", "four" };

		JButton btnDisplayTable = new JButton("Display table");
		btnDisplayTable.setBounds(25, 116, 162, 23);
		btnDisplayTable.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent b) {
				
				//JOptionPane.showConfirmDialog(null, "You have run out of lives :(\nWould you like to play again?", "MESSAGE", JOptionPane.YES_OPTION);
				
				// display the table from my sql
				model.setRowCount(0);
				String query = "SELECT * FROM test";// "id, first, last";
				try {
					Statement stm = connection.createStatement();
					ResultSet rts = stm.executeQuery(query);

					while (rts.next()) {
						// pull variable from mysql into array
						row[0] = rts.getInt("id");
						row[1] = rts.getString("first");
						row[2] = rts.getString("last");
						row[3] = rts.getString("Gender");
						row[4] = rts.getString("Class");
						row[5] = rts.getString("Email");
						row[6] = rts.getString("Discount");
						
						model.addRow(row);// add row to table
					} // end while
					stm.close();

				} catch (Exception e) {
					System.out.println("error");
				} // end catch
			}// end actionPerformed
		});
		contentPane.add(btnDisplayTable);

		textFirst = new JTextField();
		textFirst.setBounds(130, 42, 96, 20);
		contentPane.add(textFirst);
		textFirst.setColumns(10);

		textlast = new JTextField();
		textlast.setBounds(236, 42, 96, 20);
		contentPane.add(textlast);
		textlast.setColumns(10);

		JLabel lblfirst = new JLabel("First Name");
		lblfirst.setBounds(144, 17, 89, 14);
		contentPane.add(lblfirst);

		JLabel lblLastName = new JLabel("Last Name");
		lblLastName.setBounds(253, 17, 79, 14);
		contentPane.add(lblLastName);

		JLabel label = new JLabel("");
		label.setBounds(364, 17, 49, 14);
		contentPane.add(label);
		
		JTextField TComments = new JTextField();
		TComments.setBounds(462, 177, 174, 165);
		contentPane.add(TComments);
		TComments.setColumns(10);

		// System.out.println("\nlinkedList: "+ dataFields);

		JButton btnAdd = new JButton("Add");
		btnAdd.setBounds(20, 41, 89, 23); 
		contentPane.add(btnAdd);
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent c) {

				JOptionPane.showMessageDialog(null, "You have deleted data from Supplier sucessfully!", "MESSAGE", getDefaultCloseOperation());
				
				first = textFirst.getText();
				last = textlast.getText();

				try {
					JOptionPane.showMessageDialog(null, "Your input is invalid.", "MESSAGE", getDefaultCloseOperation());
					PreparedStatement addS = connection
							.prepareStatement("insert into CKdatabase.test values (default, ?, ?)");

					// addS.setString(0, idString);
					addS.setString(1, first);
					addS.setString(2, last);

					addS.executeUpdate();
				} catch (Exception e) {

				}

				model.addRow(row);
				textFirst.setText("");
				textlast.setText("");

			}
		});

		txtSearch = new JTextField();
		txtSearch.setBounds(130, 73, 96, 20);
		contentPane.add(txtSearch);
		txtSearch.setColumns(10);

		JButton btnSearch = new JButton("Search");
		btnSearch.setBounds(20, 75, 89, 23);
		contentPane.add(btnSearch);
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent c) {
				String query = "";

				query = ("SELECT * FROM CKdatabase.test WHERE (first LIKE '%" + txtSearch.getText()
						+ "%' OR last LIKE '%" + txtSearch.getText()
						+ "%' OR id LIKE '%" + txtSearch.getText() + "%' )");

				model.setRowCount(0);

				Statement stm;
				try {
					stm = connection.createStatement();
					ResultSet rts = stm.executeQuery(query);

					while (rts.next()) {

						row[0] = rts.getInt("id");
						// id = rts.getInt("id");
						row[1] = rts.getString("first");
						row[2] = rts.getString("last");

						model.addRow(row);
						// model.addColumn(column);

					}
					stm.close();
				} catch (Exception e) {
					System.out.println("error");
				}

			}
		});

		JButton btnDelete = new JButton("Delete");
		btnDelete.setBounds(197, 115, 89, 23);
		contentPane.add(btnDelete);
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent c) {
				model.setRowCount(0);
				search = txtSearch.getText();

				try {
					PreparedStatement deleteS = connection
							.prepareStatement("DELETE FROM CKbase.test WHERE (first = ?) OR (last = ?)");
//							+ "(first LIKE '%" + txtSearch.getText()
//							+ "%' OR last LIKE '%" + txtSearch.getText() + "%' OR '%" + txtSearch.getText() +
//							"%' OR id LIKE '%" + txtSearch.getText() + "%' )"); 

					// deleteS.setString(0, search);
					deleteS.setString(1, search);
					deleteS.setString(2, search);

					deleteS.executeUpdate();

				} catch (Exception e) {
					System.out.println("error in deletion");
				}

			}
		});

		JButton btnEdit = new JButton("edit");
		btnEdit.setBounds(296, 115, 89, 23);
		contentPane.add(btnEdit);
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent c) {
				search = txtSearch.getText();
				// id = Integer.valueOf(search);
				first = textFirst.getText();
				last = textlast.getText();

				try {
					PreparedStatement editS = connection
							.prepareStatement("UPDATE CKdatabase.test SET first = ? WHERE (last = ?)");

					// editS.setInt(0, id);
					editS.setString(1, first);
					editS.setString(2, search);

					editS.executeUpdate();
				} catch (Exception e) {
					System.out.println("error in editing");
				}
			}
		});

	}

}

