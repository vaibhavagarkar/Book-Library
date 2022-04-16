import java.awt.EventQueue;
import java.sql.*; 
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import net.proteanit.sql.DbUtils;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class javaCrud {

	private JFrame frame;
	private JTextField txtbname;
	private JTextField txtedition;
	private JTextField txtprice;
	private JTable table;
	private JTextField txtbid;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					javaCrud window = new javaCrud();
					window.frame.setVisible(true);						
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public javaCrud() {
		initialize();
		ConnectDatabase();		
	}


 	Connection con;
	PreparedStatement pst;
	ResultSet rs;
	
	public void ConnectDatabase() {
		
	
		try {			
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost/mysql", "root", "");
			table_load();
		}
		catch(ClassNotFoundException ex) {
			System.out.println(ex);
		}
		catch(SQLException ex) {
			System.out.println(ex);
			
		}
	}
	public void table_load()
	{   
		try
		{ 
			pst =con.prepareStatement("select * from book");
			rs = pst.executeQuery();
			table.setModel(DbUtils.resultSetToTableModel(rs));		
			
		}
		catch (SQLException e)
		{ 
			e.printStackTrace();
		}
	}
	 
	
	
	
	
	
	
	
	
	/**
	 * Connect SQL
	 */
	


		
	

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 832, 533);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Book Shop");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblNewLabel.setForeground(Color.BLACK);
		lblNewLabel.setBounds(359, 11, 175, 44);
		frame.getContentPane().add(lblNewLabel);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Registration", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(10, 108, 471, 238);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Book Name");
		lblNewLabel_1.setBounds(10, 52, 141, 29);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Edition");
		lblNewLabel_1_1.setBounds(10, 108, 141, 29);
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		panel.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Price");
		lblNewLabel_1_1_1.setBounds(10, 166, 141, 29);
		lblNewLabel_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		panel.add(lblNewLabel_1_1_1);
		
		txtbname = new JTextField();
		txtbname.setBounds(152, 58, 231, 20);
		panel.add(txtbname);
		txtbname.setColumns(10);
		
		txtedition = new JTextField();
		txtedition.setColumns(10);
		txtedition.setBounds(152, 114, 231, 20);
		panel.add(txtedition);
		
		txtprice = new JTextField();
		txtprice.setColumns(10);
		txtprice.setBounds(152, 172, 231, 20);
		panel.add(txtprice);
		
		JButton btnNewButton = new JButton("Save");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
                String bname, edition, price, bid;
				
				bname = txtbname.getText();
				edition = txtedition.getText();
				price = txtprice.getText();
			
			
				if (bname.length() == 0 || edition.length() == 0 || price.length() == 0) 
				{
					System.out.println("Please Fill the Empty Textbox");
					JOptionPane.showMessageDialog(null, "Fill the empty textfield");
				
					return;
				}
			    
					
		
				
			
			try { 
				  pst = con.prepareStatement("insert into book (bname,edition,price) values(?,?,?)");
				  pst.setString(1, bname);
				  pst.setString(2, edition);
				  pst.setString(3, price);
				  
				  
				  
				  pst.executeUpdate();
				  JOptionPane.showMessageDialog(null, "Recordedddddd!!!!!");
				  table_load();
				  txtbname.setText("");
				  txtedition.setText("");
				  txtprice.setText("");
				  
				  
				  txtbname.requestFocus();
			}
			catch (SQLException e1 ) {
				e1.printStackTrace();
			}

				
			
			}
			});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnNewButton.setBounds(20, 357, 106, 44);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnExit_1 = new JButton("Exit");
		btnExit_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnExit_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnExit_1.setBounds(170, 357, 106, 44);
		frame.getContentPane().add(btnExit_1);
		
		JButton btnExit = new JButton("Clear");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				  txtbname.setText("");
				  txtedition.setText("");
				  txtprice.setText("");
				  
				  
				  txtbname.requestFocus();
			}
		});
		btnExit.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnExit.setBounds(316, 357, 106, 44);
		frame.getContentPane().add(btnExit);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(491, 93, 315, 281);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable();
		scrollPane.setColumnHeaderView(table);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "Search", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(20, 412, 461, 49);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel_1_1_2 = new JLabel("Book ID");
		lblNewLabel_1_1_2.setBounds(53, 19, 70, 20);
		lblNewLabel_1_1_2.setFont(new Font("Tahoma", Font.BOLD, 16));
		panel_1.add(lblNewLabel_1_1_2);
		
		txtbid = new JTextField();
		txtbid.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				try {
					 String id = txtbid.getText();
					 
					 pst =con.prepareStatement("select bname,edition,price from book where id = ?");
					 pst.setString(1, id);
					 ResultSet rs = pst.executeQuery();
				
					 
					 if (rs.next()==true)
					 { 
						 String bname = rs.getString(1);
						 String edition = rs.getString(2);
						 String price = rs.getString(3);
						 
						 txtbname.setText(bname);
						 txtedition.setText(edition);
						 txtprice.setText(price);
			         }
					 else
					 {  txtbname.setText("");
					    txtedition.setText("");
					    txtprice.setText("");
					    
					 }
				}
				      catch (SQLException ex) {
				      }
				      }
				
	});
		txtbid.setBounds(151, 22, 157, 18);
		txtbid.setColumns(10);
		panel_1.add(txtbid);
		
		JButton btnUpdate_1 = new JButton("Update");
		btnUpdate_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
	            String bname, edition, price, bid;
				
					bname = txtbname.getText();
					edition = txtedition.getText();
					price = txtprice.getText();
					bid = txtbid.getText();
					
				
				try { 
					  pst = con.prepareStatement("update book set bname= ? , edition =?,price=? where id=?");
					  pst.setString(1, bname);
					  pst.setString(2, edition);
					  pst.setString(3, price);
					  pst.setString(4, bid);
					  
					  
					  pst.executeUpdate();
					  JOptionPane.showMessageDialog(null, "Record updatedddddd!!!!!");
					  table_load();
					  txtbname.setText("");
					  txtedition.setText("");
					  txtprice.setText("");
					  txtbid.setText("");
					  
					  txtbname.requestFocus();
				}
				catch (SQLException e1 ) {
					e1.printStackTrace();
				}
			}
		});
		btnUpdate_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnUpdate_1.setBounds(505, 408, 106, 44);
		frame.getContentPane().add(btnUpdate_1);
		
		JButton btnUpdate = new JButton("Delete");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
	            String  bid;
				
					
						bid = txtbid.getText();
						
					
					try { 
						  pst = con.prepareStatement("delete from book where id=?");
						
						  pst.setString(1, bid);
						  
						  
						  pst.executeUpdate();
						  JOptionPane.showMessageDialog(null, "Record Deletedddddd!!!!!");
						  table_load();
						  txtbname.setText("");
						  txtedition.setText("");
						  txtprice.setText("");
						  txtbid.setText("");
						  
						  txtbname.requestFocus();
					}
					catch (SQLException e1 ) {
						e1.printStackTrace();
					}
				}
			
		});
		btnUpdate.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnUpdate.setBounds(646, 408, 106, 44);
		frame.getContentPane().add(btnUpdate);
		
		
			}
		}
	
	
		

