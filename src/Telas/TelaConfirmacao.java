package Telas;

import java.awt.EventQueue;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import Banco.ClienteBD;
import Dados.Cliente;
import java.awt.Toolkit;

public class TelaConfirmacao extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	
	private ClienteBD clienteBD;
	private Cliente cliente;
	private int idCliente;
	private TelaMatricula tm;

	/**
	 * Launch the application.
	 */
	

	/**
	 * Create the frame.
	 */
	public TelaConfirmacao(int idCliente, TelaMatricula tm) {
		
		this.idCliente = idCliente;
		this.tm = tm;
		clienteBD = new ClienteBD();
		
		setIconImage(Toolkit.getDefaultToolkit().getImage(TelaConfirmacao.class.getResource("/Imagem/WorkoutGym logo png.png")));
		setResizable(false);
		setTitle("Excluir matrícula");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 405, 230);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 0, 0));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Deseja excluir a matrícula?");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setBounds(34, 31, 328, 59);
		contentPane.add(lblNewLabel);
		
		JButton btnNao = new JButton("Não");
		btnNao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnNao.setBackground(new Color(255, 255, 255));
		btnNao.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnNao.setBounds(47, 115, 89, 36);
		contentPane.add(btnNao);
		
		JButton btnSim = new JButton("Sim");
		btnSim.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			        
			        boolean excluiuCliente = clienteBD.excluirCliente(idCliente);
			        
			        if (excluiuCliente) {
			            JOptionPane.showMessageDialog(null, "Cliente excluído com sucesso!");
			            dispose();
			            tm.dispose();			            
			        } else {
			            JOptionPane.showMessageDialog(null, "Erro ao excluir o cliente.");
			        }
				
			}
		});
		btnSim.setBackground(new Color(255, 255, 255));
		btnSim.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnSim.setBounds(251, 115, 89, 36);
		contentPane.add(btnSim);
		
		setLocationRelativeTo(null);
	}
}
