import java.awt.EventQueue;
import java.awt.Image;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JButton;

import com.mysql.jdbc.PreparedStatement;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JList;
import javax.swing.JScrollPane;
import java.awt.Toolkit;

public class CreateCompany extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField NameCompTextField;
	private JButton SubmitBTN;
	private JButton btnCancel;
	String compname;
	private JLabel background;
	private JList<Object> companylist;
	private JScrollPane scrollPane;
	public int i;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CreateCompany frame = new CreateCompany();
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
	public CreateCompany() throws SQLException {
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Company\\CompanyProg\\imgs\\rateicon.png"));
		String[] companieslist = new String[200];
		int[] theseiscp =new int[200];
		setResizable(false);
		setTitle("\u0394\u0397\u039C\u0399\u039F\u03A5\u03A1\u0393\u0399\u0391 \u03A4\u039C\u0397\u039C\u0391\u03A4\u039F\u03A3");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 353, 281);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		SubmitBTN = new JButton("SUBMIT");
		Image img = new ImageIcon(this.getClass().getResource("/ok.png")).getImage();
		SubmitBTN.setIcon(new ImageIcon(img));
		contentPane.getRootPane().setDefaultButton(SubmitBTN);
		companylist = new JList<Object>();
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
		
	
		NameCompTextField = new JTextField();
		NameCompTextField.setToolTipText("ΣΥΜΠΛΗΡΩΣΕ ΟΛΑ ΤΑ ΣΤΟΙΧΕΙΑ");
		NameCompTextField.setBounds(10, 54, 158, 44);
		contentPane.add(NameCompTextField);
		NameCompTextField.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("\u038C\u03BD\u03BF\u03BC\u03B1 \u03A4\u03BC\u03AE\u03BC\u03B1\u03C4\u03BF\u03C2:");
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblNewLabel.setBounds(10, 27, 158, 33);
		contentPane.add(lblNewLabel);
		
		SubmitBTN.setFont(new Font("Tahoma", Font.PLAIN, 14));
		SubmitBTN.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				compname=NameCompTextField.getText();
				try{
						java.sql.Connection connection = DriverManager.getConnection("jdbc:mysql://83.212.120.62:3306/companies","root","1234");
						String query1="CREATE TABLE "+compname+"(name VARCHAR(255), surname VARCHAR(255), salary VARCHAR(255), phone VARCHAR(255), rate VARCHAR(2))";
						PreparedStatement pst1=(PreparedStatement) connection.prepareStatement(query1);
						pst1.executeUpdate();
						pst1.close();
						try{
						    AudioInputStream audioInputStream =AudioSystem.getAudioInputStream(this.getClass().getResource("/ok.wav"));
						    Clip clip = AudioSystem.getClip();
						    clip.open(audioInputStream);
						    clip.start( );
						}catch(Exception ex){}
						JOptionPane.showMessageDialog(null, "Δημιουργήθηκε με επιτυχία το τμήμα: "+compname+" !");
						NameCompTextField.setText("");
					}catch (Exception e){
						try{
						    AudioInputStream audioInputStream =AudioSystem.getAudioInputStream(this.getClass().getResource("/error.wav"));
						    Clip clip = AudioSystem.getClip();
						    clip.open(audioInputStream);
						    clip.start( );
						}catch(Exception ex){}
						JOptionPane.showMessageDialog(null, "Δώστε έγκυρο όνομα!");
						
					}
				
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
		SubmitBTN.setBounds(10, 109, 158, 44);
		contentPane.add(SubmitBTN);
		
		btnCancel = new JButton("CANCEL");
		Image img1 = new ImageIcon(this.getClass().getResource("/cancel.png")).getImage();
		btnCancel.setIcon(new ImageIcon(img1));
		btnCancel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCancel.setBounds(10, 164, 158, 44);
		contentPane.add(btnCancel);
		Image backg = new ImageIcon(this.getClass().getResource("/background_3.jpg")).getImage();;
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(202, 54, 135, 188);
		contentPane.add(scrollPane);
		
		scrollPane.setViewportView(companylist);
		
		
		background = new JLabel("");
		background.setIcon(new ImageIcon(backg));
		background.setBounds(0, 0, 347, 253);
		contentPane.add(background);
	}
}
