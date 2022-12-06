package cafekeeper;

//import established libraries 
import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JList;
import javax.swing.JComboBox;
import javax.swing.ListSelectionModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTable;

//import mysql libraires 
import java.sql.Connection;
import java.sql.DriverManager;

public class Test extends JFrame {//start class

	private static final long serialVersionUID = 8559427675924901367L;// serial ID required for no issues 
	private static JPanel contentPane;//create content pains
	
	//declare variables
	private static boolean pressed = false;//if start is pressed
	private static String[] selectedTbl = {"COFFEE", "PASTRIES", "SALES", "SUPPLIERS"};//the different tables in the database
	private static String selectTbl = ("");//selecting a table
	private static int menuNum = 0;//which menue are you on
	private static int x = 130, y = 213, t = 0, temp = -1;//ints for setting bounds on contentpane & temporary placements
		
	//state variables for main menu interface
	private static JLabel lblActions = new JLabel("ACTIONS");//lable for actions able to perfomed on the interface
	private static JButton btnAddM = new JButton("ADD");//button to add new items into table
	private static JButton btnEditM = new JButton("EDIT");//button to edit items in table
	private static JButton btnSortM = new JButton("SORT");//button to sort items in table
	private static JButton btnDelete = new JButton("DELETE");//button to delete items
	private static JTextField txtSearch = new JTextField();//search bar
	private static JButton btnSearch = new JButton("Search");//search button
	
	//creates variables for database table
	private JTable tblDB;
	private static JScrollPane scrollPane = new JScrollPane();
	private static JButton btnDisplayTable = new JButton("Display table");
	
	//variables for misc interface
	private static JButton[][] tblSelect = new JButton[2][2];//buttons for table select interface
	private static JButton btnStart = new JButton("START"); //start button
	private static JLabel lblCK = new JLabel("CAFE \r\nKEEPER\r\n");//lable of interface
	private static JButton btnBack = new JButton("Back");//back button, goes to pervious interface
	private static JButton btnAdd = new JButton("Add");//add new data to database
	private static ArrayList<JLabel> dataField = new ArrayList<>();
	
	//variables for add/edit
	private static int fieldNum = 9;
	private static JLabel[] dataFields = new JLabel[6];
	private static String[] coffeeFields = {"Bag ID: ", "Ground: ", "Size(kg): ", "Cost:", "Best Before: ", "Comments:"};
	static String[] months = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
	private static JTextField fieldsEntry = new JTextField();
	static String[] grounds = {"Bean", "Filtered", "French Pressed", "Esspresso"};
	private static JComboBox groundSelect = new JComboBox(grounds);
	static String[] size = {"50g", "250g", "1kg", "2kg"};
	private static JComboBox sizeSelect = new JComboBox(size);
	static String[] costC = {"120", "440", "880"};
	private static JComboBox costSelect = new JComboBox(costC);
	//private static JComboBox bbDate = new JComboBox();
	static JLabel bbDate = new JLabel();
	
	//variables for mysql
	static Connection con = null; //connection to mysql
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {//start main
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {//runs program creating the jframe
					Test frame = new Test();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
		
	}

	/**
	 * Create the frame.
	 */
	public Test() {
		setTitle("CAFEKEEPER");		
		
		//set up contentpane
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 901, 690);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		//setup lblActions
		lblActions.setLabelFor(this);
		lblActions.setFont(new Font("Courier New", Font.BOLD, 49));
		lblActions.setBounds(578, 53, 248, 106);
		contentPane.add(lblActions);
		lblActions.setVisible(false);
		
		//set up button add
		btnAddM.setFont(new Font("Courier New", Font.BOLD, 40));
		btnAddM.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Add("Add " ,selectTbl, temp);
			}
		});
		btnAddM.setBounds(577, 170, 201, 44);
		
		//set up edit button
		btnEditM.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnEditM.setFont(new Font("Courier New", Font.BOLD, 40));
		btnEditM.setBounds(578, 225, 201, 44);
		
		//set up sort button
		btnSortM.setFont(new Font("Courier New", Font.BOLD, 40));
		btnSortM.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnSortM.setBounds(578, 280, 201, 44);
		
		//set up delete button
		btnDelete.setFont(new Font("Courier New", Font.BOLD, 40));
		btnDelete.setBounds(578, 335, 201, 44);
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		
		//set up search bar
		txtSearch.setFont(new Font("Courier New", Font.BOLD, 40));
		txtSearch.setBounds(620, 409, 240, 62);
		txtSearch.setColumns(10);
		contentPane.add(txtSearch);
		txtSearch.setVisible(false);
		
		btnBack.setBounds(771, 619, 89, 23);
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Back(menuNum);
			}
		});
		
		//set up lable  
		lblCK.setHorizontalAlignment(SwingConstants.CENTER);
		lblCK.setFont(new Font("Courier New", Font.BOLD, 49));
		lblCK.setBounds(162, 95, 545, 89);
		contentPane.add(lblCK);
		
		//set up start button
		btnStart.setFont(new Font("Courier New", Font.BOLD, 40));
		btnStart.setBounds(319, 278, 248, 119);
		contentPane.add(btnStart);		
		
		tblDB = new JTable();
		//tblDB.setBounds(43, 141, 491, 458);
		
		//set up scroll pane
		scrollPane.setBounds(43, 141, 491, 458);
		scrollPane.setViewportView(tblDB);
		
		btnAdd.setBounds(10, 600, 201, 44);
		btnAddM.setFont(new Font("Courier New", Font.BOLD, 40));
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		
		for(int row = 0; row < 2; row++) {//Create boxes in each row of grid
			for(int column = 0; column < 2; column++) {//Creates boxes for each column in grid
				tblSelect[row][column] = new JButton(selectedTbl[t]);
				tblSelect[row][column].setBounds(x, y, 264, 119);
				tblSelect[row][column].setFont(new Font("Courier New", Font.BOLD, 40));
				
				int counterT = t;				
				tblSelect[row][column].addActionListener(new ActionListener() { // if pressed on...						
					public void actionPerformed(ActionEvent e) {//start actionPerformed
						if (pressed == true) {//if the start button is pressed									
							if (counterT == 0) {//set up coffee interface
								//	Menu("CURRENT INVENTORY");
								lblCK.setBounds(10, 10, 545, 89);
								selectTbl = ("COFFEE");
								
								temp = counterT;
							} else if (counterT == 2) {//set up sales interface
								Menu("SALES DATA");
								temp = counterT;
							} else {//else
							Menu(selectedTbl[counterT]);
							selectTbl = (selectedTbl[counterT]);
							temp = counterT;
							}//end else 
						}//end if
					}//end actionPerformed
				});//end actionListener
				t++;
				x = 523;
			}//end for
			x = 130;
			y = 437;
		}//end for
		
		btnStart.addActionListener(new ActionListener() {//button to add into the database
			public void actionPerformed(ActionEvent e) {//start actionPerformed start button
				//starts program 
				pressed = true;
				btnStart.setVisible(false);
				contentPane.add(btnBack);		
				btnBack.setVisible(true);
				Start();
			}//end actionPerformed
		});//end actionListener
		
	}//end Prototype
	
	public void Start() {
		//display & set variables for table select
		lblCK.setText("TABLE SELECT");
		setTitle("TABLE SELECT");	
		menuNum = 1;
		
		for(int row = 0; row < 2; row++) {
			//Create boxes in each row of grid
			for(int column = 0; column < 2; column++) {
				//Creates boxes for each column in grid
				contentPane.add(tblSelect[row][column]);	
				//Add button to content pane
				tblSelect[row][column].setVisible(true);
			}//end for
		}//end for
	}//end start
	
	public void Menu(String N) { //displays menu variables
		//disable variables for table select
		for(int row = 0; row < 2; row++) {//Create boxes in each row of grid
			for(int column = 0; column < 2; column++) {
				tblSelect[row][column].setVisible(false);
			}//end for
		}//end for
		lblCK.setText(N);
		lblCK.setBounds(10, 10, 328, 89);
		setTitle(N);
		selectTbl = (N);
		
		//display variables for main menu
		lblActions.setVisible(true);
		txtSearch.setVisible(true);
		contentPane.add(btnAddM);
		btnAddM.setVisible(true);
		contentPane.add(btnEditM);
		btnEditM.setVisible(true);
		contentPane.add(btnDelete);
		btnDelete.setVisible(true);
		contentPane.add(btnSortM);
		btnSortM.setVisible(true);
		contentPane.add(scrollPane);
		scrollPane.setVisible(true);
		menuNum = 2;
	}
	
	public void Back(int N) {//going to perviois interface
		switch (N) {
		case 1: //Going from table select to start
			lblCK.setText("CAFE KEEPER");
			setTitle("CAFE KEEPER");
			lblCK.setBounds(162, 95, 545, 89);
			btnStart.setVisible(true);
			for(int row = 0; row < 2; row++) {
				//Create boxes in each row of grid
				for(int column = 0; column < 2; column++) {
					tblSelect[row][column].setVisible(false);
				}//end for
			}//end for
			btnBack.setVisible(false);
			menuNum = 0;
			break;
		case 2://From specific table to table select
			Start();
			lblCK.setBounds(162, 95, 545, 89);
			btnAddM.setVisible(false);
			btnEditM.setVisible(false);
			btnDelete.setVisible(false);
			btnSortM.setVisible(false);
			lblActions.setVisible(false);
			txtSearch.setVisible(false);
			tblDB.setVisible(false);
			scrollPane.setVisible(false);
			menuNum = 1;
			break;
		}//end switch case
	}//end back
	
	public void Add(String N, String M, int P) {
		//creating interface for adding to table
		menuNum = 3;
		lblCK.setText(N + M);
		setTitle(N + M);
		
		lblCK.setBounds(10, 10, 450, 89);
		btnAddM.setVisible(false);
		btnEditM.setVisible(false);
		btnDelete.setVisible(false);
		btnSortM.setVisible(false);
		txtSearch.setVisible(false);
		tblDB.setVisible(false);
		scrollPane.setVisible(false);
		lblActions.setVisible(false);
		y = 60;
		t = 0;
		
		switch(P) {//start switch
		case 0: 
			contentPane.add(btnAdd);
			
			//set up drop box for selecting grounds
			groundSelect.setBounds(350, 130, 400, 50);
			groundSelect.setFont(new Font("Courier New", Font.BOLD, 40));
			contentPane.add(groundSelect);
			
			sizeSelect.setBounds(350, 193, 400, 50);
			sizeSelect.setFont(new Font("Courier New", Font.BOLD, 40));
			contentPane.add(sizeSelect);
			
			costSelect.setBounds(350, 256, 400, 50);
			costSelect.setFont(new Font("Courier New", Font.BOLD, 40));
			contentPane.add(costSelect);
			
			 Calendar cal = Calendar.getInstance();
			int currentM = cal.get(Calendar.MONTH);
			int year = cal.get(Calendar.YEAR);
			int bbmonth = currentM + 6;
			if (bbmonth > 12) {
				bbmonth = bbmonth - 12;
				year++;
			}
			bbDate.setText(months[bbmonth] + " " + year);
			bbDate.setBounds(350, 319, 400, 50);
			bbDate.setFont(new Font("Courier New", Font.BOLD, 40));
			contentPane.add(bbDate);
			
			for (int i = 0; i < 6; i++) {//Set up lables for adding to coffee
				dataFields[i] = new JLabel(coffeeFields[t]);
				dataFields[i].setBounds(25, y, 450, 89);
				dataFields[i].setFont(new Font("Courier New", Font.BOLD, 40));

				contentPane.add(dataFields[i]);

				t++;
				y = y + 60;
			}//end for
			
			fieldsEntry.setBounds(350, y-40, 450, 50);
			fieldsEntry.setFont(new Font("Courier New", Font.BOLD, 40));
			contentPane.add(fieldsEntry);
			
			
			
			break;
		}//end switch
	}//end add
	
	public void open(int N) {
		switch (N) {
		case 1:
			try {
				//Coffee frame = new Coffee();
				//frame.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
		}
	}
	
	public static Connection dbConnector() throws Exception {	
		//create connection to database on mysql
		try {
			con = (Connection)DriverManager.getConnection("jdbc:mysql://localhost:3306/CKdatabase", "root", "cosc310");
			
			if (con != null) {
				//confirm the connection is established 
				System.out.println("database is connected");
				return con;
			}//end if
		}catch(Exception e) {
			System.out.println("not connected");
		}//end catch
		return con;
	}//end dbConnector
	
}//end class

