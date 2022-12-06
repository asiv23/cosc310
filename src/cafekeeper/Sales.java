package cafekeeper;

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

public class Sales extends JFrame {

	private JPanel contentPane;

	private static ArrayList<JLabel> dataFields = new ArrayList<>();
	private JTable table;

	static String  ground, size, cost, bb, comments;
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
					Sales frame = new Sales();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

	}

	Connection connection = null;
	private JTextField TComments;
	private JTextField txtSearch;
	private JComboBox SGround = new JComboBox();
	private JComboBox SSize = new JComboBox(new Object[]{});
	private JComboBox SCost = new JComboBox(new Object[]{});
	private JComboBox SMonth = new JComboBox(new Object[]{});	
	private JComboBox SYear = new JComboBox(new Object[]{});	
	
	/**
	 * Create the frame.
	 * 
	 * @throws Exception
	 */
	public Sales() throws Exception {
		connection = SqlConnection.dbConnector();

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 772, 390);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(25, 149, 544, 193);
		contentPane.add(scrollPane_1);

		table = new JTable();
		scrollPane_1.setViewportView(table);

		Object[] column = { "ID", "Ground", "Size", "Price", "Ordered", "Sold", "Res", "Paid", "Comments"};
		model.setColumnIdentifiers(column);
		table.setModel(model);
		Object[] row = new Object[10];
		// Object [] newrow = new Object[3];

		JButton btnDisplayTable = new JButton("Display table");
		btnDisplayTable.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnDisplayTable.setBounds(25, 116, 162, 23);
		btnDisplayTable.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent b) {
				// display the table from my sql
				model.setRowCount(0);
				String query = "SELECT * FROM sales";// "id, first, last";
				try {
					
					Statement stm = connection.createStatement();
					ResultSet rts = stm.executeQuery(query);

					while (rts.next()) {
						// pull variable from mysql into array
						row[0] = rts.getInt("ID");
						row[1] = rts.getString("Ground");
						row[2] = rts.getString("Size");
						row[3] = rts.getString("Price");
						row[4] = rts.getString("Ordered");
						row[5] = rts.getString("Sold");
						row[6] = rts.getString("Res");
						row[7] = rts.getString("Paid");
						row[8] = rts.getString("Comments");

						model.addRow(row);// add row to table
					} // end while
					stm.close();

				} catch (Exception e) {
					System.out.println("error");
				} // end catch
			}// end actionPerformed
		});
		contentPane.add(btnDisplayTable);

		TComments = new JTextField();
		TComments.setBounds(579, 149, 174, 193);
		contentPane.add(TComments);
		TComments.setColumns(10);

		JLabel lblfirst = new JLabel("Ground");
		lblfirst.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblfirst.setBounds(137, 17, 89, 14);
		contentPane.add(lblfirst);

		JLabel lblLastName = new JLabel("Size");
		lblLastName.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblLastName.setBounds(253, 17, 79, 14);
		contentPane.add(lblLastName);
		
		
		SGround.setModel(new DefaultComboBoxModel(new String[] {"Bean", "Filtered", "French Pressed", "Esspresso "}));
		SGround.setFont(new Font("Tahoma", Font.PLAIN, 13));
		SGround.setMaximumRowCount(4);
		SGround.setBounds(119, 42, 103, 22);
		contentPane.add(SGround);
		
		
		SSize.setModel(new DefaultComboBoxModel(new String[] {"250g", "1kg", "2kg"}));
		SSize.setFont(new Font("Tahoma", Font.PLAIN, 13));
		SSize.setMaximumRowCount(4);
		SSize.setBounds(236, 42, 103, 22);
		contentPane.add(SSize);
		
		SCost.setModel(new DefaultComboBoxModel(new String[] {"300B"}));
		SCost.setFont(new Font("Tahoma", Font.PLAIN, 13));
		SCost.setMaximumRowCount(4);
		SCost.setBounds(349, 42, 103, 22);
		contentPane.add(SCost);

		SMonth.setModel(new DefaultComboBoxModel(new String[] {"", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12"}));
		SMonth.setMaximumRowCount(4);
		SMonth.setFont(new Font("Tahoma", Font.PLAIN, 13));
		SMonth.setBounds(462, 42, 49, 22);
		contentPane.add(SMonth);
		
		SYear.setModel(new DefaultComboBoxModel(new String[] {"", "20", "21"}));
		SYear.setMaximumRowCount(4);
		SYear.setFont(new Font("Tahoma", Font.PLAIN, 13));
		SYear.setBounds(521, 42, 49, 22);
		contentPane.add(SYear);
		
		JButton btnAdd = new JButton("Add");
		btnAdd.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnAdd.setBounds(20, 41, 89, 23);
		contentPane.add(btnAdd);
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent c) {

				ground = String.valueOf(SGround.getSelectedItem());
				size = String.valueOf(SSize.getSelectedItem());
				cost = String.valueOf(SCost.getSelectedItem());
				bb = String.valueOf(SMonth.getSelectedItem()) + String.valueOf(SYear.getSelectedItem());
				comments = TComments.getText();

				try {
					PreparedStatement addS = connection
							.prepareStatement("insert into CKdatabase.coffee values (default, ?, ?, ?, ?, ?)");

					// addS.setString(0, idString);
					addS.setString(1, ground);
					addS.setString(2, size);
					addS.setString(3, cost);
					addS.setString(4, bb);
					addS.setString(5, comments);
					
					addS.executeUpdate();
				} catch (Exception e) {
					System.out.println("fuckin idiot");
				}

				model.addRow(row);
				TComments.setText("");

			}
		});

		txtSearch = new JTextField();
		txtSearch.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtSearch.setBounds(119, 77, 227, 20);
		contentPane.add(txtSearch);
		txtSearch.setColumns(10);

		JButton btnSearch = new JButton("Search");
		btnSearch.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnSearch.setBounds(20, 75, 89, 23);
		contentPane.add(btnSearch);
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent c) {
				String query = "";

				query = ("SELECT * FROM CKdatabase.Coffee WHERE (ID LIKE '%" + txtSearch.getText()
						+ "%' OR Gorund LIKE '%" + txtSearch.getText() + "%' OR Size Like'%" + txtSearch.getText()
						+ "%' OR Cost LIKE '%" + txtSearch.getText() + "%' OR Best Before LIKE '%" + txtSearch.getText()
						+ "%' )");

				model.setRowCount(0);

				Statement stm;
				try {
					stm = connection.createStatement();
					ResultSet rts = stm.executeQuery(query);

					while (rts.next()) {

						row[0] = rts.getInt("ID");
						row[1] = rts.getString("Ground");
						row[2] = rts.getString("Size");
						row[3] = rts.getString("Cost");
						row[4] = rts.getString("Best Before ");
						row[5] = rts.getString("Comments");

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
		btnDelete.setBounds(472, 77, 89, 23);
		contentPane.add(btnDelete);
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent c) {
				model.setRowCount(0);
				// String query = "delete from CKdatabase.test where first = ? ";
				search = txtSearch.getText();

				try {
					PreparedStatement deleteS = connection
							.prepareStatement("DELETE FROM CKdatabase.Coffee WHERE (first = ?) OR (last = ?)");
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
		btnEdit.setBounds(364, 77, 89, 23);
		contentPane.add(btnEdit);
		
		JLabel lblCost = new JLabel("Price");
		lblCost.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblCost.setBounds(349, 18, 89, 14);
		contentPane.add(lblCost);
		
		JLabel lblBestBeforemmyy = new JLabel("Order Date(mm/yy)\r\n");
		lblBestBeforemmyy.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblBestBeforemmyy.setBounds(462, 18, 126, 14);
		contentPane.add(lblBestBeforemmyy);
		
		JLabel lblSaleDatemmyy = new JLabel("Sale Date(mm/yy)\r\n");
		lblSaleDatemmyy.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblSaleDatemmyy.setBounds(590, 18, 126, 14);
		contentPane.add(lblSaleDatemmyy);
		
		JComboBox comboBox = new JComboBox(new Object[]{});
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"", "1", "2", "3", "4"}));
		comboBox.setMaximumRowCount(4);
		comboBox.setFont(new Font("Tahoma", Font.PLAIN, 13));
		comboBox.setBounds(590, 42, 49, 22);
		contentPane.add(comboBox);
		
		JComboBox comboBox_1 = new JComboBox(new Object[]{});
		comboBox_1.setModel(new DefaultComboBoxModel(new String[] {"", "20", "21"}));
		comboBox_1.setMaximumRowCount(4);
		comboBox_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		comboBox_1.setBounds(649, 42, 49, 22);
		contentPane.add(comboBox_1);
		
		JRadioButton rdbtnNewRadioButton = new JRadioButton("Reserved");
		rdbtnNewRadioButton.setFont(new Font("Tahoma", Font.PLAIN, 13));
		rdbtnNewRadioButton.setBounds(590, 71, 111, 23);
		contentPane.add(rdbtnNewRadioButton);
		
		JRadioButton rdbtnPaid = new JRadioButton("Paid");
		rdbtnPaid.setFont(new Font("Tahoma", Font.PLAIN, 13));
		rdbtnPaid.setBounds(590, 97, 111, 23);
		contentPane.add(rdbtnPaid);
		
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent c) {
				JOptionPane.showMessageDialog(null, "You have deleted data from Coffee sucessfully!", "MESSAGE", getDefaultCloseOperation());
				
				search = txtSearch.getText();
				// id = Integer.valueOf(search);
				//first = TDate.getText();
				last = TComments.getText();

				try {
					PreparedStatement editS = connection
							.prepareStatement("UPDATE CKdatabase.Coffee SET first = ? WHERE (last = ?)");

					// editS.setInt(0, id);
					//editS.setString(1, first);
					editS.setString(2, search);

					editS.executeUpdate();
				} catch (Exception e) {
					System.out.println("error in editing");
				}
			}
		});

	}
}
