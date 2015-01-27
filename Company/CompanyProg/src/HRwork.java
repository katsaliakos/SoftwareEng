import java.awt.EventQueue;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;








import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.SQLException;
import java.awt.Image;
import java.awt.SystemColor;

import javax.swing.JLabel;
import java.awt.Toolkit;


public class HRwork extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HRwork frame = new HRwork();
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
	public HRwork() {
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Company\\CompanyProg\\imgs\\rateicon.png"));
		setBackground(SystemColor.menu);
		setResizable(false);
		setTitle("HR Utilities");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 350, 458);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.menu);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton EditCompBTN = new JButton("\u0395\u03C0\u03B5\u03BE\u03B5\u03C1\u03B3\u03B1\u03C3\u03AF\u03B1 \u03A4\u03BC\u03AE\u03BC\u03B1\u03C4\u03BF\u03C2");
		EditCompBTN.setFont(new Font("Tahoma", Font.PLAIN, 14));
		EditCompBTN.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				EditCompany ec;
				try {
					ec = new EditCompany();
					ec.setVisible(true);
					try{
					    AudioInputStream audioInputStream =AudioSystem.getAudioInputStream(this.getClass().getResource("/warning.wav"));
					    Clip clip = AudioSystem.getClip();
					    clip.open(audioInputStream);
					    clip.start( );
					}catch(Exception ex){}
					JOptionPane.showMessageDialog(null, "Παρακαλώ συμπληρώστε όλα τα πεδία για την σωστή εκχώρηση των δεδομένων.");
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					try{
					    AudioInputStream audioInputStream =AudioSystem.getAudioInputStream(this.getClass().getResource("/error.wav"));
					    Clip clip = AudioSystem.getClip();
					    clip.open(audioInputStream);
					    clip.start( );
					}catch(Exception ex){}
					e.printStackTrace();				
				}
				
			}
		});
		EditCompBTN.setBounds(69, 179, 197, 70);
		contentPane.add(EditCompBTN);
		
		JButton DeleteCompBTN = new JButton("\u0394\u03B9\u03B1\u03B3\u03C1\u03B1\u03C6\u03AE \u03A4\u03BC\u03AE\u03BC\u03B1\u03C4\u03BF\u03C2");
		DeleteCompBTN.setFont(new Font("Tahoma", Font.PLAIN, 14));
		DeleteCompBTN.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				DeleteComp dc=new DeleteComp();
				dc.setVisible(true);
			}
		});
		DeleteCompBTN.setBounds(69, 260, 197, 70);
		contentPane.add(DeleteCompBTN);
		
		JButton LogoutBTN = new JButton("LOGOUT");
		Image img = new ImageIcon(this.getClass().getResource("/out.png")).getImage();
		LogoutBTN.setIcon(new ImageIcon(img));
		LogoutBTN.setFont(new Font("Tahoma", Font.PLAIN, 14));
		LogoutBTN.addActionListener(new ActionListener() {
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
		LogoutBTN.setBounds(69, 341, 197, 60);
		contentPane.add(LogoutBTN);
		
		JButton CreateCompBTN = new JButton("\u0394\u03B7\u03BC\u03B9\u03BF\u03C5\u03C1\u03B3\u03AF\u03B1 \u03A4\u03BC\u03AE\u03BC\u03B1\u03C4\u03BF\u03C2");
		CreateCompBTN.setFont(new Font("Tahoma", Font.PLAIN, 14));
		CreateCompBTN.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CreateCompany cc;
				try {
					cc = new CreateCompany();
					cc.setVisible(true);
					try{
					    AudioInputStream audioInputStream =AudioSystem.getAudioInputStream(this.getClass().getResource("/warning.wav"));
					    Clip clip = AudioSystem.getClip();
					    clip.open(audioInputStream);
					    clip.start( );
					}catch(Exception ex){}
					JOptionPane.showMessageDialog(null, "Παρακαλώ χρησιμοποιήστε μόνο αγγλικούς χαρακτήρες");
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					try{
					    AudioInputStream audioInputStream =AudioSystem.getAudioInputStream(this.getClass().getResource("/error.wav"));
					    Clip clip = AudioSystem.getClip();
					    clip.open(audioInputStream);
					    clip.start( );
					}catch(Exception ex){}
					e1.printStackTrace();
					
				}
			}
		});
		CreateCompBTN.setBounds(69, 98, 197, 70);
		contentPane.add(CreateCompBTN);
		
		JLabel lblNewLabel = new JLabel("");
		Image img1 = new ImageIcon(this.getClass().getResource("/utilities.png")).getImage();
		lblNewLabel.setIcon(new ImageIcon(img1));
		lblNewLabel.setBounds(33, 23, 64, 64);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("");
		Image img2 = new ImageIcon(this.getClass().getResource("/hrutilities.png")).getImage();
		lblNewLabel_1.setIcon(new ImageIcon(img2));
		lblNewLabel_1.setBounds(107, 23, 159, 64);
		contentPane.add(lblNewLabel_1);
		
		JLabel label = new JLabel("");
		Image backg = new ImageIcon(this.getClass().getResource("/background_3.jpg")).getImage();
		label.setIcon(new ImageIcon(backg));		
		label.setBounds(0, 0, 344, 430);
		contentPane.add(label);
	}
}
