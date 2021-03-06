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
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JButton;

import com.mysql.jdbc.PreparedStatement;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.awt.Font;

import javax.swing.JLabel;
import java.awt.Toolkit;


public class DeleteComp extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	int i=0;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DeleteComp frame = new DeleteComp();
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
	public DeleteComp() {
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Company\\CompanyProg\\imgs\\rateicon.png"));
		setResizable(false);
		String[] companieslist = new String[200];
		int[] theseiscp =new int[200];
		setTitle("\u0394\u0399\u0391\u0393\u03A1\u0391\u03A6\u0397 \u03A4\u039C\u0397\u039C\u0391\u03A4\u039F\u03A3");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 421, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		JButton button_1 = new JButton("\u0394\u03B9\u03B1\u03B3\u03C1\u03B1\u03C6\u03AE");
		Image img1 = new ImageIcon(this.getClass().getResource("/trash.png")).getImage();
		button_1.setIcon(new ImageIcon(img1));
		contentPane.getRootPane().setDefaultButton(button_1);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(33, 57, 168, 249);
		contentPane.add(scrollPane);
		
		JList<String> companylist = new JList<String>();
		scrollPane.setViewportView(companylist);
		
		JButton btnCancel = new JButton("CANCEL");
		Image img2 = new ImageIcon(this.getClass().getResource("/cancel.png")).getImage();
		btnCancel.setIcon(new ImageIcon(img2));
		btnCancel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCancel.setBounds(247, 262, 158, 44);
		contentPane.add(btnCancel);
		
		button_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String company = (String) companylist.getSelectedValue();
				try{
				    AudioInputStream audioInputStream =AudioSystem.getAudioInputStream(this.getClass().getResource("/warning.wav"));
				    Clip clip = AudioSystem.getClip();
				    clip.open(audioInputStream);
				    clip.start( );
				}catch(Exception ex){}
				int action=JOptionPane.showConfirmDialog(null,"Είστε σίγουρος για την διαγραφή;","ΠΡΟΣΟΧΗ!",JOptionPane.YES_NO_OPTION);
				
				if (action==0){
					try{
						java.sql.Connection connection = DriverManager.getConnection("jdbc:mysql://83.212.120.62:3306/companies","root","1234");
						String query1="DROP TABLE "+company+" ";
						PreparedStatement pst1=(PreparedStatement) connection.prepareStatement(query1);
						pst1.executeUpdate();
						pst1.close();
						try{
						    AudioInputStream audioInputStream =AudioSystem.getAudioInputStream(this.getClass().getResource("/ok.wav"));
						    Clip clip = AudioSystem.getClip();
						    clip.open(audioInputStream);
						    clip.start( );
						}catch(Exception ex){}
						JOptionPane.showMessageDialog(null, "Διαγράφτηκε");
						try{
							java.sql.Connection connection1 = DriverManager.getConnection("jdbc:mysql://83.212.120.62:3306/companies","root","1234");
							String query3="SHOW TABLES";
							PreparedStatement pst3=(PreparedStatement) connection1.prepareStatement(query3);
							ResultSet rs3=pst3.executeQuery();
							DefaultListModel<String> DLM = new DefaultListModel<String>();
							
							while(rs3.next()){
								companieslist[i]=rs3.getString("Tables_in_companies");
								DLM.addElement(companieslist[i]);
								theseiscp[i]=i;
								i=i+1;
							}
							companylist.setModel(DLM);
							pst1.close();
							rs3.close();		
						}catch (Exception ex){
							try{
							    AudioInputStream audioInputStream =AudioSystem.getAudioInputStream(this.getClass().getResource("/error.wav"));
							    Clip clip = AudioSystem.getClip();
							    clip.open(audioInputStream);
							    clip.start( );
							}catch(Exception ex2){}
							JOptionPane.showMessageDialog(null, ex);
						}
					}catch(Exception E){
						try{
						    AudioInputStream audioInputStream =AudioSystem.getAudioInputStream(this.getClass().getResource("/error.wav"));
						    Clip clip = AudioSystem.getClip();
						    clip.open(audioInputStream);
						    clip.start( );
						}catch(Exception ex){}
						JOptionPane.showMessageDialog(null, "Παρακαλώ επιλέγξτε τμήμα πρώτα.");
					}
				}else{
					
				}
				
			}
		});
		button_1.setBounds(247, 175, 158, 44);
		contentPane.add(button_1);
		
		JButton button_2 = new JButton("Φόρτωση Τμημάτων");
		Image img = new ImageIcon(this.getClass().getResource("/load.png")).getImage();
		button_2.setIcon(new ImageIcon(img));
		button_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					java.sql.Connection connection = DriverManager.getConnection("jdbc:mysql://83.212.120.62:3306/companies","root","1234");
					String query1="SHOW TABLES";
					PreparedStatement pst1=(PreparedStatement) connection.prepareStatement(query1);
					ResultSet rs=pst1.executeQuery();
					DefaultListModel<String> DLM = new DefaultListModel<String>();
					
					while(rs.next()){
						companieslist[i]=rs.getString("Tables_in_companies");
						DLM.addElement(companieslist[i]);
						theseiscp[i]=i;
						i=i+1;
					}
					companylist.setModel(DLM);
					pst1.close();
					rs.close();		
				}catch (Exception ex){
					try{
					    AudioInputStream audioInputStream =AudioSystem.getAudioInputStream(this.getClass().getResource("/error.wav"));
					    Clip clip = AudioSystem.getClip();
					    clip.open(audioInputStream);
					    clip.start( );
					}catch(Exception ex1){}
					JOptionPane.showMessageDialog(null, ex);
				}
			}
		});
		button_2.setBounds(10, 317, 218, 43);
		contentPane.add(button_2);
		
		JLabel lblNewLabel = new JLabel("");
		Image img3 = new ImageIcon(this.getClass().getResource("/warning.png")).getImage();
		lblNewLabel.setIcon(new ImageIcon(img3));
		lblNewLabel.setBounds(259, 36, 128, 128);
		contentPane.add(lblNewLabel);
		
		JLabel background = new JLabel("");
		Image backg = new ImageIcon(this.getClass().getResource("/background_3.jpg")).getImage();
		background.setIcon(new ImageIcon(backg));
		background.setBounds(0, 0, 415, 372);
		contentPane.add(background);
	}
}
