import java.awt.EventQueue;
import java.awt.Image;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;

import com.mysql.jdbc.PreparedStatement;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.awt.Font;
import java.awt.SystemColor;

import org.eclipse.wb.swing.FocusTraversalOnArray;

import java.awt.Component;
import java.awt.Toolkit;


public class CHIEFLogin extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField ChiefTextField;
	private JPasswordField ChiefPasswordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CHIEFLogin frame = new CHIEFLogin();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public CHIEFLogin(){
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Company\\CompanyProg\\imgs\\rateicon.png"));
		setResizable(false);
		setTitle("Chief Login");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 400, 290);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.menu);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		JButton LoginChiefBTN = new JButton("LOGIN");
		Image img = new ImageIcon(this.getClass().getResource("/ok.png")).getImage();
		LoginChiefBTN.setIcon(new ImageIcon(img));
		contentPane.getRootPane().setDefaultButton(LoginChiefBTN);
		
		JLabel LabelUsernameChief = new JLabel("Username:");
		LabelUsernameChief.setFont(new Font("Times New Roman", Font.BOLD, 18));
		LabelUsernameChief.setBounds(148, 39, 89, 25);
		contentPane.add(LabelUsernameChief);
		
		ChiefTextField = new JTextField();
		ChiefTextField.setFont(new Font("Tahoma", Font.PLAIN, 13));
		ChiefTextField.setBounds(247, 40, 132, 25);
		contentPane.add(ChiefTextField);
		ChiefTextField.setColumns(10);
		
		JLabel LabelPasswordChief = new JLabel("Password:");
		LabelPasswordChief.setFont(new Font("Times New Roman", Font.BOLD, 18));
		LabelPasswordChief.setBounds(148, 123, 89, 25);
		contentPane.add(LabelPasswordChief);
		
		ChiefPasswordField = new JPasswordField();
		ChiefPasswordField.setFont(new Font("Tahoma", Font.PLAIN, 13));
		ChiefPasswordField.setBounds(247, 125, 132, 25);
		contentPane.add(ChiefPasswordField);
		
		
		LoginChiefBTN.setFont(new Font("Tahoma", Font.PLAIN, 14));
		LoginChiefBTN.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent arg0) {
				try {
					String query2="SELECT * FROM chieflogin WHERE usernamechief=? AND passwordchief=? ";
					java.sql.Connection conn = DriverManager.getConnection("jdbc:mysql://83.212.120.62:3306/login","root","1234");
					PreparedStatement pst1=(PreparedStatement) conn.prepareStatement(query2);
					pst1.setString(1,ChiefTextField.getText());
					pst1.setString(2,ChiefPasswordField.getText());
					
					ResultSet rs=pst1.executeQuery();
					int count =0 ;
					while(rs.next()){
						count++;						
					}
					if(count==1)
					{
						try{
						    AudioInputStream audioInputStream =AudioSystem.getAudioInputStream(this.getClass().getResource("/logon.wav"));
						    Clip clip = AudioSystem.getClip();
						    clip.open(audioInputStream);
						    clip.start( );
						}catch(Exception ex){}
						dispose();
						RateWindow rw = new RateWindow();
						rw.setVisible(true);
					}
					else if (count>1){
						try{
						    AudioInputStream audioInputStream =AudioSystem.getAudioInputStream(this.getClass().getResource("/error.wav"));
						    Clip clip = AudioSystem.getClip();
						    clip.open(audioInputStream);
						    clip.start( );
						}catch(Exception ex){}
						JOptionPane.showMessageDialog(null, "Duplicate Username and password. Please Try Again!");
					}
					else{
						try{
						    AudioInputStream audioInputStream =AudioSystem.getAudioInputStream(this.getClass().getResource("/error.wav"));
						    Clip clip = AudioSystem.getClip();
						    clip.open(audioInputStream);
						    clip.start( );
						}catch(Exception ex){}
						JOptionPane.showMessageDialog(null, "Username and password is not Correct! Please Try Again!");
						ChiefTextField.setText("");
						ChiefPasswordField.setText("");
					}
					rs.close();
					pst1.close();
					conn.close();
				}catch(Exception e1){
					try{
					    AudioInputStream audioInputStream =AudioSystem.getAudioInputStream(this.getClass().getResource("/error.wav"));
					    Clip clip = AudioSystem.getClip();
					    clip.open(audioInputStream);
					    clip.start( );
					}catch(Exception ex){}
					JOptionPane.showMessageDialog(null, "No internet Connection");
				}
			}
			
		});
		LoginChiefBTN.setBounds(232, 195, 147, 50);
		contentPane.add(LoginChiefBTN);
		
		JButton CancelCHIEF = new JButton("CANCEL");
		Image img2 = new ImageIcon(this.getClass().getResource("/cancel.png")).getImage();
		CancelCHIEF.setIcon(new ImageIcon(img2));
		CancelCHIEF.setFont(new Font("Tahoma", Font.PLAIN, 14));
		CancelCHIEF.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		CancelCHIEF.setBounds(10, 195, 147, 50);
		contentPane.add(CancelCHIEF);
		
		JLabel label = new JLabel("");
		Image img1 = new ImageIcon(this.getClass().getResource("/login.png")).getImage();
		label.setIcon(new ImageIcon(img1));
		label.setBounds(10, 39, 128, 128);
		contentPane.add(label);
		
		JLabel back = new JLabel("");
		Image backg = new ImageIcon(this.getClass().getResource("/login.jpg")).getImage();
		back.setIcon(new ImageIcon(backg));
		back.setBounds(0, 0, 394, 261);
		contentPane.add(back);
		setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{ChiefTextField, ChiefPasswordField, contentPane, LabelUsernameChief, LabelPasswordChief, LoginChiefBTN, CancelCHIEF, label, back}));
	}
}
