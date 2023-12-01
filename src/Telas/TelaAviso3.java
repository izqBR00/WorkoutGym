package Telas;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TelaAviso3 extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaAviso3 frame = new TelaAviso3();
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
	public TelaAviso3() {
		setResizable(false);
		setTitle("Aviso");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 440, 177);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 0, 0));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblFuncionrioJCadastrado = new JLabel("Funcionário já cadastrado.");
		lblFuncionrioJCadastrado.setHorizontalAlignment(SwingConstants.CENTER);
		lblFuncionrioJCadastrado.setForeground(Color.WHITE);
		lblFuncionrioJCadastrado.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblFuncionrioJCadastrado.setBounds(72, 24, 282, 40);
		contentPane.add(lblFuncionrioJCadastrado);
		
		JButton btnNewButton = new JButton("Entendido");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnNewButton.setBackground(Color.WHITE);
		btnNewButton.setBounds(126, 75, 174, 35);
		contentPane.add(btnNewButton);
		
		setLocationRelativeTo(null);
	}
}
