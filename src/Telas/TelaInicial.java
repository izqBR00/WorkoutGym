package Telas;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Banco.AcessoBD;
import Dados.Login;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Panel;
import java.awt.Color;
import javax.swing.JPasswordField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Toolkit;
import javax.swing.JCheckBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;

public class TelaInicial extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField campoNome;
	private JPasswordField campoSenha;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaInicial frame = new TelaInicial();
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
	public TelaInicial() {
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(TelaInicial.class.getResource("/Imagem/WorkoutGym logo png.png")));
		setTitle("WorkoutGym App");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 460);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(12, 192, 223));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel labelEmail = new JLabel("Login");
		labelEmail.setFont(new Font("Tahoma", Font.PLAIN, 18));
		labelEmail.setBounds(349, 87, 166, 36);
		contentPane.add(labelEmail);
		
		campoNome = new JTextField();
		campoNome.setFont(new Font("Tahoma", Font.PLAIN, 15));
		campoNome.setBounds(349, 117, 200, 50);
		contentPane.add(campoNome);
		campoNome.setColumns(10);
		
		JLabel lblSenha = new JLabel("Senha");
		lblSenha.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblSenha.setBounds(349, 191, 166, 36);
		contentPane.add(lblSenha);
		
		Panel panel = new Panel();
		panel.setBackground(new Color(0, 0, 0));
		panel.setBounds(0, -14, 305, 448);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("WORK");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Stencil", Font.PLAIN, 72));
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setBounds(35, 98, 238, 83);
		panel.add(lblNewLabel);
		
		JLabel lblOut = new JLabel("GYM");
		lblOut.setHorizontalAlignment(SwingConstants.CENTER);
		lblOut.setForeground(new Color(12, 192, 223));
		lblOut.setFont(new Font("Stencil", Font.PLAIN, 72));
		lblOut.setBounds(64, 242, 180, 73);
		panel.add(lblOut);
		
		JLabel lblOut_1 = new JLabel("OUT");
		lblOut_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblOut_1.setForeground(Color.WHITE);
		lblOut_1.setFont(new Font("Stencil", Font.PLAIN, 72));
		lblOut_1.setBounds(64, 173, 180, 73);
		panel.add(lblOut_1);
		
		JButton botaoEntrar = new JButton("Entrar");
		botaoEntrar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				String nome;
				String senha;
				
				AcessoBD acesso = new AcessoBD();
				Login login = new Login();
				
				nome = campoNome.getText();
				senha = campoSenha.getText();
				
				login.setNome(nome);
				login.setSenha(senha);
				
				if(acesso.verificaAcesso(login) == true)
				{
					acesso.obterLoginPorNome(login);
					TelaPrincipal tp = new TelaPrincipal(login);
					tp.setVisible(true);
					dispose();
				}
				else
				{
					JOptionPane.showMessageDialog(null, "Erro nos dados informados", "Erro", JOptionPane.ERROR_MESSAGE);
				}
				
			}
		});
		botaoEntrar.setForeground(new Color(255, 255, 255));
		botaoEntrar.setBackground(new Color(0, 0, 0));
		botaoEntrar.setBounds(403, 307, 89, 23);
		contentPane.add(botaoEntrar);
		
		campoSenha = new JPasswordField();
		campoSenha.setEchoChar('*');
		campoSenha.setBounds(349, 221, 200, 50);
		contentPane.add(campoSenha);
		
		JCheckBox toggleSenha = new JCheckBox("Exibir senha");
		toggleSenha.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(toggleSenha.isSelected()) {
					campoSenha.setEchoChar((char) (0));
				}
				else {
					campoSenha.setEchoChar('*');
				}
			}
		});
		toggleSenha.setBackground(new Color(12, 192, 223));
		toggleSenha.setBounds(349, 277, 200, 23);
		contentPane.add(toggleSenha);
		
		setLocationRelativeTo(null);
	}
}
