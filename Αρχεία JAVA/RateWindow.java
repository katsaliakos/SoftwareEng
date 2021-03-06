import java.awt.EventQueue;
import java.awt.Image;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.ButtonGroup;
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

import javax.swing.JLabel;
import javax.swing.JRadioButton;

import org.eclipse.wb.swing.FocusTraversalOnArray;

import java.awt.Component;
import java.awt.Color;
import java.awt.Toolkit;


public class RateWindow extends JFrame implements ActionListener {
	
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
	ButtonGroup buttonGroup = new ButtonGroup();
	
	private JPanel contentPane;
	private JLabel labelcompany;
	private JList<Object> hrlist;
	private JLabel lblHr;
	private JScrollPane scrollPane;

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
	public RateWindow() throws SQLException {
	 	setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Company\\CompanyProg\\imgs\\rateicon.png"));
	 	setResizable(false);
	 	setTitle("ΑΞΙΟΛΟΓΗΣΗ HR");
		String[] companieslist = new String[200];
		int[] theseiscp =new int[200];
		int[] theseishr =new int[200];
		String[][] hrcat = new String[200][4];
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 760, 512);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
			
		JButton RateBTN = new JButton("SUBMIT");
		Image img = new ImageIcon(this.getClass().getResource("/ok.png")).getImage();
		RateBTN.setIcon(new ImageIcon(img));
		RateBTN.setFont(new Font("Tahoma", Font.PLAIN, 14));
		RateBTN.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				company = (String) companylist.getSelectedValue();
				hr = (String) hrlist.getSelectedValue();
				String rdb = buttonGroup.getSelection().getActionCommand();
				String[] splited = hr.split("\\s+");
							
				try{
					java.sql.Connection connection = DriverManager.getConnection("jdbc:mysql://83.212.120.62:3306/companies","root","1234");
					String query3="UPDATE "+company+" SET rate='"+rdb+"' WHERE phone='"+splited[2]+"'";
					PreparedStatement pst3=(PreparedStatement) connection.prepareStatement(query3);
					pst3.executeUpdate();
					pst3.close();
					try{
					    AudioInputStream audioInputStream =AudioSystem.getAudioInputStream(this.getClass().getResource("/ok.wav"));
					    Clip clip = AudioSystem.getClip();
					    clip.open(audioInputStream);
					    clip.start( );
					}catch(Exception ex){}
					JOptionPane.showMessageDialog(null, "Η Αξιολόγηση έγινε!");
				}catch (Exception err){
					try{
					    AudioInputStream audioInputStream =AudioSystem.getAudioInputStream(this.getClass().getResource("/error.wav"));
					    Clip clip = AudioSystem.getClip();
					    clip.open(audioInputStream);
					    clip.start( );
					}catch(Exception ex){}
					JOptionPane.showMessageDialog(null, err);
					
				}
				try{
					company = (String) companylist.getSelectedValue();
					java.sql.Connection connection = DriverManager.getConnection("jdbc:mysql://83.212.120.62:3306/companies","root","1234");
					String query2="SELECT name,surname,phone,rate FROM " +company +"";
					PreparedStatement pst2=(PreparedStatement) connection.prepareStatement(query2);
					ResultSet rs2=pst2.executeQuery();
					DefaultListModel<Object> DLM2 = new DefaultListModel<Object>();
					while(rs2.next()){
						hrcat[j][0]=rs2.getString("name");
						hrcat[j][1]=rs2.getString("surname");
						hrcat[j][2]=rs2.getString("phone");
						hrcat[j][3]=rs2.getString("rate");
						var=hrcat[j][0]+ "  "+hrcat[j][1]+"  "+hrcat[j][2]+"  "+hrcat[j][3];
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
					}catch(Exception ex){
						
					}
					JOptionPane.showMessageDialog(null, err);
					
				}
						
			}
		});
		RateBTN.setBounds(555, 349, 156, 44);
		contentPane.add(RateBTN);
		
		JButton RateLogoutBTN = new JButton("LOGOUT");
		Image img1 = new ImageIcon(this.getClass().getResource("/out.png")).getImage();
		RateLogoutBTN.setIcon(new ImageIcon(img1));
		RateLogoutBTN.setFont(new Font("Tahoma", Font.PLAIN, 14));
		RateLogoutBTN.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try{
				    AudioInputStream audioInputStream =AudioSystem.getAudioInputStream(this.getClass().getResource("/logout.wav"));
				    Clip clip = AudioSystem.getClip();
				    clip.open(audioInputStream);
				    clip.start( );
				}catch(Exception ex){}
				dispose();
			}
		});
		RateLogoutBTN.setBounds(293, 405, 137, 57);
		contentPane.add(RateLogoutBTN);
		
		JButton loadcompaniesBTN = new JButton("\u03A6\u03CC\u03C1\u03C4\u03C9\u03C3\u03B7 \u03A4\u03BC\u03B7\u03BC\u03AC\u03C4\u03C9\u03BD");
		Image img2 = new ImageIcon(this.getClass().getResource("/load.png")).getImage();
		loadcompaniesBTN.setIcon(new ImageIcon(img2));
		loadcompaniesBTN.setFont(new Font("Tahoma", Font.PLAIN, 14));
		loadcompaniesBTN.setBounds(33, 349, 197, 44);
		contentPane.add(loadcompaniesBTN);
		
		labelcompany = new JLabel("\u0395\u03A4\u0391\u0399\u03A1\u0399\u0395\u03A3");
		labelcompany.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		labelcompany.setBounds(90, 11, 94, 57);
		contentPane.add(labelcompany);
		
		lblHr = new JLabel("HR");
		lblHr.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblHr.setBounds(434, 11, 94, 57);
		contentPane.add(lblHr);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(33, 51, 197, 287);
		contentPane.add(scrollPane);
		
		companylist = new JList<>();
		scrollPane.setViewportView(companylist);
		companylist.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		
		
		JRadioButton rd1 = new JRadioButton("1", true);
		rd1.setBackground(Color.WHITE);
		rd1.setActionCommand("1");
		rd1.addActionListener(this);
		rd1.setBounds(622, 65, 44, 23);
		buttonGroup.add(rd1);
		getContentPane().add(rd1);
		
		JRadioButton rd2 = new JRadioButton("2");
		rd2.setBackground(Color.WHITE);
		rd2.setActionCommand("2");
		rd2.addActionListener(this);
		rd2.setBounds(622, 91, 44, 23);
		buttonGroup.add(rd2);
		getContentPane().add(rd2);
		
		JRadioButton rd3 = new JRadioButton("3");
		rd3.setBackground(Color.WHITE);
		rd3.setActionCommand("3");
		rd3.addActionListener(this);
		rd3.setBounds(622, 117, 44, 23);
		buttonGroup.add(rd3);
		getContentPane().add(rd3);
		
		JRadioButton rd4 = new JRadioButton("4");
		rd4.setBackground(Color.WHITE);
		rd4.setActionCommand("4");
		rd4.addActionListener(this);
		rd4.setBounds(622, 143, 44, 23);
		buttonGroup.add(rd4);
		getContentPane().add(rd4);
		
		JRadioButton rd5 = new JRadioButton("5");
		rd5.setBackground(Color.WHITE);
		rd5.setActionCommand("5");
		rd5.addActionListener(this);
		rd5.setBounds(622, 169, 44, 23);
		buttonGroup.add(rd5);
		getContentPane().add(rd5);
		
		JRadioButton rd6 = new JRadioButton("6");
		rd6.setBackground(Color.WHITE);
		rd6.setActionCommand("6");
		rd6.addActionListener(this);
		rd6.setBounds(622, 195, 44, 23);
		buttonGroup.add(rd6);
		getContentPane().add(rd6);
		
		JRadioButton rd7 = new JRadioButton("7");
		rd7.setBackground(Color.WHITE);
		rd7.setActionCommand("7");
		rd7.addActionListener(this);
		rd7.setBounds(622, 220, 44, 23);
		buttonGroup.add(rd7);
		getContentPane().add(rd7);
		
		JRadioButton rd8 = new JRadioButton("8");
		rd8.setBackground(Color.WHITE);
		rd8.setActionCommand("8");
		rd8.addActionListener(this);
		rd8.setBounds(622, 246, 44, 23);
		buttonGroup.add(rd8);
		getContentPane().add(rd8);
		
		JRadioButton rd9 = new JRadioButton("9");
		rd9.setBackground(Color.WHITE);
		rd9.setActionCommand("9");
		rd9.addActionListener(this);
		rd9.setBounds(622, 272, 44, 23);
		buttonGroup.add(rd9);
		getContentPane().add(rd9);
						
		JRadioButton rd10 = new JRadioButton("10");
		rd10.setBackground(Color.WHITE);
		rd10.setActionCommand("10");
		rd10.addActionListener(this);
		rd10.setBounds(622, 298, 44, 23);
		buttonGroup.add(rd10);
		getContentPane().add(rd10);
		
		JLabel lblNewLabel = new JLabel("\u0391\u03BE\u03B9\u03BF\u03BB\u03CC\u03B3\u03B7\u03C3\u03B7");
		lblNewLabel.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblNewLabel.setBounds(616, 21, 95, 37);
		contentPane.add(lblNewLabel);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(348, 52, 193, 283);
		contentPane.add(scrollPane_1);
		
		hrlist = new JList<Object>();
		scrollPane_1.setViewportView(hrlist);
		hrlist.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		
		JButton btnNewButton = new JButton("\u0395\u03C0\u03B9\u03BB\u03BF\u03B3\u03AE");
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					company = (String) companylist.getSelectedValue();
					java.sql.Connection connection = DriverManager.getConnection("jdbc:mysql://83.212.120.62:3306/companies","root","1234");
					String query2="SELECT name,surname,phone,rate FROM " +company +"";
					PreparedStatement pst2=(PreparedStatement) connection.prepareStatement(query2);
					ResultSet rs2=pst2.executeQuery();
					DefaultListModel<Object> DLM2 = new DefaultListModel<Object>();
					while(rs2.next()){
						hrcat[j][0]=rs2.getString("name");
						hrcat[j][1]=rs2.getString("surname");
						hrcat[j][2]=rs2.getString("phone");
						hrcat[j][3]=rs2.getString("rate");
						var=hrcat[j][0]+ "  "+hrcat[j][1]+"  "+hrcat[j][2]+"  "+hrcat[j][3];
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
					}catch(Exception ex){
						
					}
					JOptionPane.showMessageDialog(null, err);
					
				}
			}
		});
		btnNewButton.setBounds(240, 154, 89, 49);
		contentPane.add(btnNewButton);
		
		JLabel background = new JLabel("");
		Image backg = new ImageIcon(this.getClass().getResource("/background_2.jpg")).getImage();
		background.setIcon(new ImageIcon(backg));
		background.setBounds(0, 0, 754, 484);
		contentPane.add(background);
		setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{contentPane, RateBTN, RateLogoutBTN, loadcompaniesBTN, labelcompany, lblHr, scrollPane, companylist, rd1, rd2, rd3, rd4, rd5, rd6, rd7, rd8, rd9, rd10, lblNewLabel, scrollPane_1, hrlist, btnNewButton, background}));
		
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
					try{
					    AudioInputStream audioInputStream =AudioSystem.getAudioInputStream(this.getClass().getResource("/error.wav"));
					    Clip clip = AudioSystem.getClip();
					    clip.open(audioInputStream);
					    clip.start( );
					}catch(Exception ex){}
					JOptionPane.showMessageDialog(null, e);
					
				}
				
			}
		});
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}
