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

import org.eclipse.wb.swing.FocusTraversalOnArray;

import java.awt.Component;
import java.awt.Toolkit;

public class HRLogin extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField HRTextField;
	private JPasswordField HRPasswordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HRLogin JFrame = new HRLogin();
					JFrame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

 
	/**
	 * Create the frame.
	 */
	public HRLogin() {
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Company\\CompanyProg\\imgs\\rateicon.png"));
		setResizable(false);
		setTitle("HR Login");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 400, 290);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		JButton LoginHRBTN = new JButton("LOGIN");
		Image img = new ImageIcon(this.getClass().getResource("/ok.png")).getImage();
		LoginHRBTN.setIcon(new ImageIcon(img));
		contentPane.getRootPane().setDefaultButton(LoginHRBTN);
		
			
		JLabel LabelUsernameHR = new JLabel("Username:");
		LabelUsernameHR.setFont(new Font("Times New Roman", Font.BOLD, 18));
		LabelUsernameHR.setBounds(148, 39, 89, 25);
		contentPane.add(LabelUsernameHR);
		
		HRTextField = new JTextField();
		HRTextField.setFont(new Font("Tahoma", Font.PLAIN, 13));
		HRTextField.setBounds(247, 40, 132, 25);
		contentPane.add(HRTextField);
		HRTextField.setColumns(10);
		
		JLabel LabelPasswordHR = new JLabel("Password:");
		LabelPasswordHR.setFont(new Font("Times New Roman", Font.BOLD, 18));
		LabelPasswordHR.setBounds(148, 123, 89, 25);
		contentPane.add(LabelPasswordHR);
		
		HRPasswordField = new JPasswordField();
		HRPasswordField.setFont(new Font("Tahoma", Font.PLAIN, 13));
		HRPasswordField.setBounds(247, 125, 132, 25);
		contentPane.add(HRPasswordField);
		
		LoginHRBTN.setFont(new Font("Tahoma", Font.PLAIN, 14));
		LoginHRBTN.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
				try {
					String query1="SELECT * FROM hrlogin WHERE usernamehr=? AND passwordhr=? ";
					java.sql.Connection connection = DriverManager.getConnection("jdbc:mysql://83.212.120.62:3306/login","root","1234");
					PreparedStatement pst1=(PreparedStatement) connection.prepareStatement(query1);
					pst1.setString(1,HRTextField.getText());
					pst1.setString(2,HRPasswordField.getText());
					
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
						HRwork hw = new HRwork();
						hw.setVisible(true);
						dispose();
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
						HRTextField.setText("");
						HRPasswordField.setText("");
					}
					
					rs.close();
					pst1.close();
					connection.close();
					}catch(Exception e1){
						try{
						    AudioInputStream audioInputStream =AudioSystem.getAudioInputStream(this.getClass().getResource("/error.wav"));
						    Clip clip = AudioSystem.getClip();
						    clip.open(audioInputStream);
						    clip.start( );
						}catch(Exception ex){}
					JOptionPane.showMessageDialog(null, "No Internet Connection");
				}
			}
		});
		LoginHRBTN.setBounds(232, 195, 147, 50);
		contentPane.add(LoginHRBTN);
		
		JButton CancelHR = new JButton("CANCEL");
		Image img2 = new ImageIcon(this.getClass().getResource("/cancel.png")).getImage();
		CancelHR.setIcon(new ImageIcon(img2));		
		CancelHR.setFont(new Font("Tahoma", Font.PLAIN, 14));
		CancelHR.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		CancelHR.setBounds(10, 195, 147, 50);
		contentPane.add(CancelHR);
		
		JLabel lblNewLabel = new JLabel("");
		Image img1 = new ImageIcon(this.getClass().getResource("/login.png")).getImage();
		lblNewLabel.setIcon(new ImageIcon(img1));
		lblNewLabel.setBounds(10, 39, 128, 128);
		contentPane.add(lblNewLabel);
		
		JLabel back = new JLabel("");
		Image backg = new ImageIcon(this.getClass().getResource("/login.jpg")).getImage();
		back.setIcon(new ImageIcon(backg));
		back.setBounds(0, 0, 394, 261);
		contentPane.add(back);
		setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{HRTextField, HRPasswordField, contentPane, LabelUsernameHR, LabelPasswordHR, LoginHRBTN, CancelHR, lblNewLabel, back}));
		
		}
		
}
