import java.awt.EventQueue;

import javax.swing.*;

import java.awt.event.WindowEvent;
import java.awt.Font;
import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.SystemColor;

import org.eclipse.wb.swing.FocusTraversalOnArray;

import java.awt.Component;
import java.awt.Toolkit;

public class WelcomeGUI {

	private JFrame frmCompany;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					WelcomeGUI window = new WelcomeGUI();
					window.frmCompany.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


	/**
	 * Create the application.
	 */
	public WelcomeGUI() {
		initialize();
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmCompany = new JFrame();
		frmCompany.setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Company\\CompanyProg\\imgs\\rateicon.png"));
		frmCompany.setFont(new Font("Tempus Sans ITC", Font.BOLD, 14));
		frmCompany.setTitle("Company");
		frmCompany.setResizable(false);
		frmCompany.getContentPane().setForeground(SystemColor.menu);
		frmCompany.getContentPane().setBackground(Color.WHITE);
		frmCompany.setBounds(100, 100, 768, 480);
		frmCompany.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmCompany.getContentPane().setLayout(null);
		frmCompany.setLocationRelativeTo ( null );
		
		JButton buttonHR = new JButton("HR LOGIN");
		Image img3 = new ImageIcon(this.getClass().getResource("/hr.png")).getImage();
		buttonHR.setIcon(new ImageIcon(img3));
		buttonHR.setFont(new Font("Tahoma", Font.PLAIN, 14));
		buttonHR.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				HRLogin hr = new HRLogin();
				hr.setVisible(true);
			}
		});
		buttonHR.setBounds(32, 319, 151, 51);
		frmCompany.getContentPane().add(buttonHR);
		
		JButton buttonCHIEF = new JButton("CHIEF LOGIN");
		Image img4 = new ImageIcon(this.getClass().getResource("/chief.png")).getImage();
		buttonCHIEF.setIcon(new ImageIcon(img4));
		buttonCHIEF.setFont(new Font("Tahoma", Font.PLAIN, 14));
		buttonCHIEF.setBounds(233, 319, 151, 51);
		buttonCHIEF.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				CHIEFLogin cf = new CHIEFLogin();
				cf.setVisible(true);
			}
		});
		frmCompany.getContentPane().add(buttonCHIEF);
		
		JButton btnNewButton = new JButton("\u0388\u03BE\u03BF\u03B4\u03BF\u03C2");
		Image img5 = new ImageIcon(this.getClass().getResource("/out.png")).getImage();
		btnNewButton.setIcon(new ImageIcon(img5));
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frmCompany.dispatchEvent(new WindowEvent(frmCompany, WindowEvent.WINDOW_CLOSING));
			}
		});
		btnNewButton.setBounds(140, 381, 141, 51);
		frmCompany.getContentPane().add(btnNewButton);
		
		JLabel label_1 = new JLabel("");
		Image img = new ImageIcon(this.getClass().getResource("/complogo.png")).getImage();
		label_1.setIcon(new ImageIcon(img));
		label_1.setBounds(0, 0, 480, 258);
		frmCompany.getContentPane().add(label_1);
		
		JLabel lblNewLabel = new JLabel("");
		Image img1 = new ImageIcon(this.getClass().getResource("/teipir.png")).getImage();
		lblNewLabel.setIcon(new ImageIcon(img1));
		lblNewLabel.setBounds(548, 233, 194, 100);
		frmCompany.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("New label");
		Image img2 = new ImageIcon(this.getClass().getResource("/slogan.png")).getImage();
		lblNewLabel_1.setIcon(new ImageIcon(img2));
		lblNewLabel_1.setBounds(531, 51, 221, 135);
		frmCompany.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("");
		Image img6 = new ImageIcon(this.getClass().getResource("/copyrights.png")).getImage();
		lblNewLabel_2.setIcon(new ImageIcon(img6));
		lblNewLabel_2.setBounds(558, 347, 192, 85);
		frmCompany.getContentPane().add(lblNewLabel_2);
		
		JLabel background = new JLabel("");
		background.setBounds(0, 0, 762, 451);
		frmCompany.getContentPane().add(background);
		frmCompany.setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{frmCompany.getContentPane(), buttonHR, buttonCHIEF, btnNewButton, lblNewLabel, lblNewLabel_1, lblNewLabel_2, background, label_1}));
		Image backg = new ImageIcon(this.getClass().getResource("/background.jpg")).getImage();
		background.setIcon(new ImageIcon(backg));
		
		
	}
}
