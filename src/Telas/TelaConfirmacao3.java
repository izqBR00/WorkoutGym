package Telas;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

import Banco.UsuarioBD;
import Dados.Usuario;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;

public class TelaConfirmacao3 extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	
	private UsuarioBD usuarioBD;
	private Usuario usuario;
	private int idUsuario;
	private TelaFuncionario tf;
	

	
	/**
	 * Create the frame.
	 */
	public TelaConfirmacao3(int idUsuario, TelaFuncionario tf) {
		this.idUsuario = idUsuario;
		this.tf = tf;
		usuarioBD = new UsuarioBD();
		usuario = new Usuario();
		
		setIconImage(Toolkit.getDefaultToolkit().getImage(TelaConfirmacao3.class.getResource("/Imagem/WorkoutGym logo png.png")));
		setResizable(false);
		setTitle("Excluir funcionário");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 405, 230);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 0, 0));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNao = new JButton("Não");
		btnNao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnNao.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnNao.setBounds(53, 127, 89, 36);
		contentPane.add(btnNao);
		
		JButton btnSim = new JButton("Sim");
		btnSim.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean excluiuUsuario = usuarioBD.excluirUsuario(idUsuario);
		        
		        if (excluiuUsuario) {
		            JOptionPane.showMessageDialog(null, "Funcionário excluído com sucesso!");
		            dispose();
		            tf.dispose();			            
		        } else {
		            JOptionPane.showMessageDialog(null, "Erro ao excluir o cliente.");
		        }
			}
		});
		btnSim.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnSim.setBounds(247, 127, 89, 36);
		contentPane.add(btnSim);
		
		JLabel lblNewLabel = new JLabel("Deseja excluir o funcionário?");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 23));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setBounds(49, 43, 298, 54);
		contentPane.add(lblNewLabel);
		
		setLocationRelativeTo(null);
	}
}
