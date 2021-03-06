import java.awt.EventQueue;
import java.awt.Image;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JScrollPane;

import com.mysql.jdbc.PreparedStatement;

import java.awt.Font;

import javax.swing.JTextField;
import javax.swing.JLabel;

import org.eclipse.wb.swing.FocusTraversalOnArray;

import java.awt.Component;
import java.awt.Toolkit;



public class EditCompany extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JList<Object> companylist;
	int i=0;
	int j=0;
	String company=null;
	String hr=null;
	String var=null;
	
	
	private JPanel contentPane;
	private JLabel labelcompany;
	private JList<Object> hrlist;
	private JLabel lblHr;
	private JScrollPane scrollPane;
	private JTextField nameText;
	private JTextField surnameText;
	private JTextField SalText;
	private JTextField PhoneText;
	private JButton DeleteButton;
	private JScrollPane scrollPane_1;
	private JLabel label_6;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RateWindow frame = new RateWindow();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	 /**
	 * Create the frame.
	 * @throws SQLException 
	 */
	public EditCompany() throws SQLException {
	 	setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Company\\CompanyProg\\imgs\\rateicon.png"));
	 	setResizable(false);
	 	setTitle("ΕΠΕΞΕΡΓΑΣΙΑ ΤΜΗΜΑΤΩΝ");
		String[] companieslist = new String[200];
		int[] theseiscp =new int[200];
		int[] theseishr =new int[200];
		String[][] hrcat = new String[200][4];
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 785, 564);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
			
		JButton editButton = new JButton("ΕΠΕΞΕΡΓΑΣΙΑ");
		Image img2 = new ImageIcon(this.getClass().getResource("/edit.png")).getImage();
		editButton.setIcon(new ImageIcon(img2));
		editButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
		editButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				company = (String) companylist.getSelectedValue();
				hr = (String) hrlist.getSelectedValue();
				String name = nameText.getText();
				String surname = surnameText.getText();
				String salary = SalText.getText();
				String phone = PhoneText.getText();
				String[] splited = hr.split("\\s+");
				try{
						java.sql.Connection connection = DriverManager.getConnection("jdbc:mysql://83.212.120.62:3306/companies","root","1234");
						String query3="UPDATE "+company+" SET name='"+name+"',surname='"+surname+"',salary='"+salary+"',phone='"+phone+"' WHERE phone='"+splited[3]+"'";
						PreparedStatement pst3=(PreparedStatement) connection.prepareStatement(query3);
						pst3.executeUpdate();
						pst3.close();
						try{
						    AudioInputStream audioInputStream =AudioSystem.getAudioInputStream(this.getClass().getResource("/ok.wav"));
						    Clip clip = AudioSystem.getClip();
						    clip.open(audioInputStream);
						    clip.start( );
						}catch(Exception ex){}
						JOptionPane.showMessageDialog(null, "Η Επεξεργασία πραγματοποιήθηκε!");
						nameText.setText("");
						surnameText.setText("");
						SalText.setText("");
						PhoneText.setText("");
					}catch (Exception err){
						JOptionPane.showMessageDialog(null, err);
					}
				try{
					company = (String) companylist.getSelectedValue();
					java.sql.Connection connection = DriverManager.getConnection("jdbc:mysql://83.212.120.62:3306/companies","root","1234");
					String query2="SELECT name,surname,salary,phone FROM " +company +"";
					PreparedStatement pst2=(PreparedStatement) connection.prepareStatement(query2);
					ResultSet rs2=pst2.executeQuery();
					DefaultListModel<Object> DLM2 = new DefaultListModel<Object>();
					while(rs2.next()){
						hrcat[j][0]=rs2.getString("name");
						hrcat[j][1]=rs2.getString("surname");
						hrcat[j][2]=rs2.getString("salary");
						hrcat[j][3]=rs2.getString("phone");
						var=hrcat[j][0]+ " "+hrcat[j][1]+" "+hrcat[j][2]+" "+hrcat[j][3];
						DLM2.addElement(var);
						theseishr[j]=j;
						j=j+1;
					}
					hrlist.setModel(DLM2);
					pst2.close();
					rs2.close();		
				}catch (Exception err){
					JOptionPane.showMessageDialog(null, err);
				}
				}
			
						
		});
		editButton.setBounds(576, 349, 156, 44);
		contentPane.add(editButton);
		
		JButton RateLogoutBTN = new JButton("CANCEL");
		Image img = new ImageIcon(this.getClass().getResource("/cancel.png")).getImage();
		RateLogoutBTN.setIcon(new ImageIcon(img));		
		RateLogoutBTN.setFont(new Font("Tahoma", Font.PLAIN, 14));
		RateLogoutBTN.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		RateLogoutBTN.setBounds(280, 446, 137, 57);
		contentPane.add(RateLogoutBTN);
		
		JButton loadcompaniesBTN = new JButton("\u03A6\u03CC\u03C1\u03C4\u03C9\u03C3\u03B7 \u03A4\u03BC\u03B7\u03BC\u03AC\u03C4\u03C9\u03BD");
		Image img1 = new ImageIcon(this.getClass().getResource("/load.png")).getImage();
		loadcompaniesBTN.setIcon(new ImageIcon(img1));		
		loadcompaniesBTN.setFont(new Font("Tahoma", Font.PLAIN, 14));
		loadcompaniesBTN.setBounds(33, 349, 197, 44);
		contentPane.add(loadcompaniesBTN);
		
		labelcompany = new JLabel("\u0395\u03A4\u0391\u0399\u03A1\u0399\u0395\u03A3");
		labelcompany.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		labelcompany.setBounds(90, 11, 94, 57);
		contentPane.add(labelcompany);
		
		lblHr = new JLabel("Ον.");
		lblHr.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblHr.setBounds(358, 11, 30, 57);
		contentPane.add(lblHr);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(33, 51, 197, 287);
		contentPane.add(scrollPane);
		
		companylist = new JList<>();
		scrollPane.setViewportView(companylist);
		companylist.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		
		JButton btnNewButton = new JButton("\u0395\u03C0\u03B9\u03BB\u03BF\u03B3\u03AE");
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					company = (String) companylist.getSelectedValue();
					java.sql.Connection connection = DriverManager.getConnection("jdbc:mysql://83.212.120.62:3306/companies","root","1234");
					String query2="SELECT name,surname,salary,phone FROM " +company +"";
					PreparedStatement pst2=(PreparedStatement) connection.prepareStatement(query2);
					ResultSet rs2=pst2.executeQuery();
					DefaultListModel<Object> DLM2 = new DefaultListModel<Object>();
					while(rs2.next()){
						hrcat[j][0]=rs2.getString("name");
						hrcat[j][1]=rs2.getString("surname");
						hrcat[j][2]=rs2.getString("salary");
						hrcat[j][3]=rs2.getString("phone");
						var=hrcat[j][0]+ " "+hrcat[j][1]+" "+hrcat[j][2]+" "+hrcat[j][3];
						DLM2.addElement(var);
						theseishr[j]=j;
						j=j+1;
					}
					hrlist.setModel(DLM2);
					pst2.close();
					rs2.close();		
				}catch (Exception err){
					try{
					AudioInputStream audioInputStream =AudioSystem.getAudioInputStream(this.getClass().getResource("/error.wav"));
				    Clip clip = AudioSystem.getClip();
				    clip.open(audioInputStream);
				    clip.start( );
				}catch(Exception ex){}
					JOptionPane.showMessageDialog(null, err);
				}
			}
		});
		btnNewButton.setBounds(240, 175, 89, 44);
		contentPane.add(btnNewButton);
		
		nameText = new JTextField();
		nameText.setFont(new Font("Tahoma", Font.PLAIN, 13));
		nameText.setToolTipText("Παρακαλώ δώσε έγκυρο όνομα");
		nameText.setColumns(10);
		nameText.setBounds(576, 54, 158, 44);
		contentPane.add(nameText);
		
		surnameText = new JTextField();
		surnameText.setFont(new Font("Tahoma", Font.PLAIN, 13));
		surnameText.setToolTipText("Παρακαλώ δώσε έγκυρο επίθετο");
		surnameText.setColumns(10);
		surnameText.setBounds(576, 132, 158, 44);
		contentPane.add(surnameText);
		
		SalText = new JTextField();
		SalText.setFont(new Font("Tahoma", Font.PLAIN, 13));
		SalText.setToolTipText("Παρακαλώ δώσε έγκυρο μισθό");
		SalText.setColumns(10);
		SalText.setBounds(576, 212, 158, 44);
		contentPane.add(SalText);
		
		PhoneText = new JTextField();
		PhoneText.setFont(new Font("Tahoma", Font.PLAIN, 13));
		PhoneText.setToolTipText("Παρακαλώ δώσε έγκυρο αριθμό");
		PhoneText.setColumns(10);
		PhoneText.setBounds(578, 290, 158, 44);
		contentPane.add(PhoneText);
		
		JLabel label = new JLabel("Όνομα Εργάτη:");
		label.setFont(new Font("Times New Roman", Font.BOLD, 18));
		label.setBounds(576, 26, 158, 33);
		contentPane.add(label);
		
		JLabel label_1 = new JLabel("Επίθετο Εργάτη:");
		label_1.setFont(new Font("Times New Roman", Font.BOLD, 18));
		label_1.setBounds(576, 104, 158, 33);
		contentPane.add(label_1);
		
		JLabel label_2 = new JLabel("Μισθός Εργάτη:");
		label_2.setFont(new Font("Times New Roman", Font.BOLD, 18));
		label_2.setBounds(576, 181, 158, 33);
		contentPane.add(label_2);
		
		JLabel label_3 = new JLabel("Τηλέφωνο Εργάτη:");
		label_3.setFont(new Font("Times New Roman", Font.BOLD, 18));
		label_3.setBounds(576, 262, 158, 33);
		contentPane.add(label_3);
		
		JButton addButton = new JButton("ΠΡΟΣΘΗΚΗ");
		Image img3 = new ImageIcon(this.getClass().getResource("/add.png")).getImage();
		addButton.setIcon(new ImageIcon(img3));
		addButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				company = (String) companylist.getSelectedValue();
				String name = nameText.getText();
				String surname = surnameText.getText();
				String salary = SalText.getText();
				String phone = PhoneText.getText();
				try{
					java.sql.Connection connection = DriverManager.getConnection("jdbc:mysql://83.212.120.62:3306/companies","root","1234");
					String query4="INSERT INTO "+company+" VALUES('"+name+"','"+surname+"','"+salary+"','"+phone+"',NULL)";
					PreparedStatement pst4=(PreparedStatement) connection.prepareStatement(query4);
					pst4.executeUpdate();
					pst4.close();
					try{
					    AudioInputStream audioInputStream =AudioSystem.getAudioInputStream(this.getClass().getResource("/ok.wav"));
					    Clip clip = AudioSystem.getClip();
					    clip.open(audioInputStream);
					    clip.start( );
					}catch(Exception ex){}
					JOptionPane.showMessageDialog(null, "Νέο μέλος HR προστέθηκε!");
					nameText.setText("");
					surnameText.setText("");
					SalText.setText("");
					PhoneText.setText("");
				}catch (Exception err){
					try{
					    AudioInputStream audioInputStream =AudioSystem.getAudioInputStream(this.getClass().getResource("/error.wav"));
					    Clip clip = AudioSystem.getClip();
					    clip.open(audioInputStream);
					    clip.start( );
					}catch(Exception ex){}
					JOptionPane.showMessageDialog(null, "Παρακαλώ δώστε όλα τα στοιχεία!");
				}
				try{
					company = (String) companylist.getSelectedValue();
					java.sql.Connection connection = DriverManager.getConnection("jdbc:mysql://83.212.120.62:3306/companies","root","1234");
					String query2="SELECT name,surname,salary,phone FROM " +company +"";
					PreparedStatement pst2=(PreparedStatement) connection.prepareStatement(query2);
					ResultSet rs2=pst2.executeQuery();
					DefaultListModel<Object> DLM2 = new DefaultListModel<Object>();
					while(rs2.next()){
						hrcat[j][0]=rs2.getString("name");
						hrcat[j][1]=rs2.getString("surname");
						hrcat[j][2]=rs2.getString("salary");
						hrcat[j][3]=rs2.getString("phone");
						var=hrcat[j][0]+ " "+hrcat[j][1]+" "+hrcat[j][2]+" "+hrcat[j][3];
						DLM2.addElement(var);
						theseishr[j]=j;
						j=j+1;
					}
					hrlist.setModel(DLM2);
					pst2.close();
					rs2.close();		
				}catch (Exception err){
					try{
					    AudioInputStream audioInputStream =AudioSystem.getAudioInputStream(this.getClass().getResource("/error.wav"));
					    Clip clip = AudioSystem.getClip();
					    clip.open(audioInputStream);
					    clip.start( );
					}catch(Exception ex){}
					JOptionPane.showMessageDialog(null, "Δεν εισήχθηκε τίποτα!");
				}
			}
		});
		addButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
		addButton.setBounds(576, 404, 156, 44);
		contentPane.add(addButton);
		
		DeleteButton = new JButton("ΔΙΑΓΡΑΦΗ");
		Image img4 = new ImageIcon(this.getClass().getResource("/trash.png")).getImage();
		DeleteButton.setIcon(new ImageIcon(img4));
		DeleteButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				company = (String) companylist.getSelectedValue();
				hr = (String) hrlist.getSelectedValue();
				String[] splited = hr.split("\\s+");
				try{
				    AudioInputStream audioInputStream =AudioSystem.getAudioInputStream(this.getClass().getResource("/warning.wav"));
				    Clip clip = AudioSystem.getClip();
				    clip.open(audioInputStream);
				    clip.start( );
				}catch(Exception ex){}
				int action=JOptionPane.showConfirmDialog(null,"Είστε σίγουρος για την διαγραφή;","ΠΡΟΣΟΧΗ!",JOptionPane.YES_NO_OPTION);
				if(action==0){
				try{
						java.sql.Connection connection = DriverManager.getConnection("jdbc:mysql://83.212.120.62:3306/companies","root","1234");
						String query5="DELETE FROM "+company+" WHERE phone='"+splited[3]+"'";
						PreparedStatement pst5=(PreparedStatement) connection.prepareStatement(query5);
						pst5.executeUpdate();
						pst5.close();
						try{
						    AudioInputStream audioInputStream =AudioSystem.getAudioInputStream(this.getClass().getResource("/ok.wav"));
						    Clip clip = AudioSystem.getClip();
						    clip.open(audioInputStream);
						    clip.start( );
						}catch(Exception ex){}
						JOptionPane.showMessageDialog(null, "Η Διαγραφή πραγματοποιήθηκε!");
						nameText.setText("");
						surnameText.setText("");
						SalText.setText("");
						PhoneText.setText("");
					}catch (Exception err){
						JOptionPane.showMessageDialog(null, err);
					}
				try{
					company = (String) companylist.getSelectedValue();
					java.sql.Connection connection = DriverManager.getConnection("jdbc:mysql://83.212.120.62:3306/companies","root","1234");
					String query2="SELECT name,surname,salary,phone FROM " +company +"";
					PreparedStatement pst2=(PreparedStatement) connection.prepareStatement(query2);
					ResultSet rs2=pst2.executeQuery();
					DefaultListModel<Object> DLM2 = new DefaultListModel<Object>();
					while(rs2.next()){
						hrcat[j][0]=rs2.getString("name");
						hrcat[j][1]=rs2.getString("surname");
						hrcat[j][2]=rs2.getString("salary");
						hrcat[j][3]=rs2.getString("phone");
						var=hrcat[j][0]+ " "+hrcat[j][1]+" "+hrcat[j][2]+" "+hrcat[j][3];
						DLM2.addElement(var);
						theseishr[j]=j;
						j=j+1;
					}
					hrlist.setModel(DLM2);
					pst2.close();
					rs2.close();		
				}catch (Exception err){
					JOptionPane.showMessageDialog(null, err);
				}
				}else{
					
				}
				}				
		});
		DeleteButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
		DeleteButton.setBounds(576, 459, 156, 44);
		contentPane.add(DeleteButton);
		
		scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(339, 52, 227, 286);
		contentPane.add(scrollPane_1);
		
		hrlist = new JList<Object>();
		scrollPane_1.setViewportView(hrlist);
		hrlist.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		
		JLabel label_4 = new JLabel("Επ.");
		label_4.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		label_4.setBounds(398, 11, 30, 57);
		contentPane.add(label_4);
		
		JLabel label_5 = new JLabel("Μισθ.");
		label_5.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		label_5.setBounds(438, 11, 52, 57);
		contentPane.add(label_5);
		
		label_6 = new JLabel("Τηλ.");
		label_6.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		label_6.setBounds(500, 11, 66, 57);
		contentPane.add(label_6);
		
		JLabel background = new JLabel("");
		Image backg = new ImageIcon(this.getClass().getResource("/background_2.jpg")).getImage();
		background.setIcon(new ImageIcon(backg));
		background.setBounds(0, 0, 779, 536);
		contentPane.add(background);
		setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{nameText, surnameText, SalText, PhoneText, contentPane, editButton, RateLogoutBTN, loadcompaniesBTN, labelcompany, lblHr, scrollPane, companylist, btnNewButton, label, label_1, label_2, label_3, addButton, DeleteButton, scrollPane_1, hrlist, label_4, label_5, label_6}));
		
		loadcompaniesBTN.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try{
					java.sql.Connection connection = DriverManager.getConnection("jdbc:mysql://83.212.120.62:3306/companies","root","1234");
					String query1="SHOW TABLES";
					PreparedStatement pst1=(PreparedStatement) connection.prepareStatement(query1);
					ResultSet rs=pst1.executeQuery();
					DefaultListModel<Object> DLM = new DefaultListModel<Object>();
					
					while(rs.next()){
						companieslist[i]=rs.getString("Tables_in_companies");
						DLM.addElement(companieslist[i]);
						theseiscp[i]=i;
						i=i+1;
					}
					companylist.setModel(DLM);
					pst1.close();
					rs.close();		
				}catch (Exception e){
					JOptionPane.showMessageDialog(null, e);
				}
				
			}
		});
	}
}
