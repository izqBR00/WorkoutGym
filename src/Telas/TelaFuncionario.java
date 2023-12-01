package Telas;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import Banco.UsuarioBD;
import Dados.Equipamento;
import Dados.Usuario;
import java.awt.Toolkit;

public class TelaFuncionario extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField campoNome;
	private JTextField campoSenha;
	private JTextField campoEmail;
	private JTextField campoTelefone;
	private JTextField campoCpf;
	private JComboBox campoNivel;
	
	private int idUsuario;
	private Usuario usuario;
	private UsuarioBD usuarioBD;
	private TelaFuncionarioEditar tfe;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaFuncionario frame = new TelaFuncionario();
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
	public TelaFuncionario() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(TelaFuncionario.class.getResource("/Imagem/WorkoutGym logo png.png")));
		setResizable(false);
		usuarioBD = new UsuarioBD();
		
		setTitle("Ficha do funcionário");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 507, 540);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 0, 0));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		campoNome = new JTextField();
		campoNome.setEditable(false);
		campoNome.setFont(new Font("Tahoma", Font.PLAIN, 15));
		campoNome.setBounds(45, 54, 412, 25);
		contentPane.add(campoNome);
		campoNome.setColumns(10);
		
		JLabel lblNome = new JLabel("Nome");
		lblNome.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNome.setForeground(new Color(255, 255, 255));
		lblNome.setBounds(45, 36, 62, 18);
		contentPane.add(lblNome);
		
		campoSenha = new JTextField();
		campoSenha.setEditable(false);
		campoSenha.setFont(new Font("Tahoma", Font.PLAIN, 15));
		campoSenha.setColumns(10);
		campoSenha.setBounds(45, 128, 412, 25);
		contentPane.add(campoSenha);
		
		JLabel lblSenha = new JLabel("Senha");
		lblSenha.setForeground(Color.WHITE);
		lblSenha.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblSenha.setBounds(45, 110, 62, 18);
		contentPane.add(lblSenha);
		
		campoEmail = new JTextField();
		campoEmail.setEditable(false);
		campoEmail.setFont(new Font("Tahoma", Font.PLAIN, 15));
		campoEmail.setColumns(10);
		campoEmail.setBounds(45, 196, 412, 25);
		contentPane.add(campoEmail);
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setForeground(Color.WHITE);
		lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblEmail.setBounds(45, 177, 62, 18);
		contentPane.add(lblEmail);
		
		campoTelefone = new JTextField();
		campoTelefone.setEditable(false);
		campoTelefone.setFont(new Font("Tahoma", Font.PLAIN, 15));
		campoTelefone.setColumns(10);
		campoTelefone.setBounds(45, 271, 171, 25);
		contentPane.add(campoTelefone);
		
		campoCpf = new JTextField();
		campoCpf.setEditable(false);
		campoCpf.setFont(new Font("Tahoma", Font.PLAIN, 15));
		campoCpf.setColumns(10);
		campoCpf.setBounds(286, 271, 171, 25);
		contentPane.add(campoCpf);
		
		JLabel lblTelefone = new JLabel("Telefone");
		lblTelefone.setForeground(Color.WHITE);
		lblTelefone.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblTelefone.setBounds(45, 250, 86, 18);
		contentPane.add(lblTelefone);
		
		JLabel lblCpf = new JLabel("CPF");
		lblCpf.setForeground(Color.WHITE);
		lblCpf.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblCpf.setBounds(286, 250, 86, 18);
		contentPane.add(lblCpf);
		
		campoNivel = new JComboBox();
		campoNivel.setEnabled(false);
		campoNivel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		campoNivel.setModel(new DefaultComboBoxModel(new String[] {"Funcionário", "Adiministrador"}));
		campoNivel.setBounds(45, 350, 171, 22);
		contentPane.add(campoNivel);
		
		JLabel lblNivel = new JLabel("Nível de acesso");
		lblNivel.setForeground(Color.WHITE);
		lblNivel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNivel.setBounds(45, 332, 171, 18);
		contentPane.add(lblNivel);
		
		JButton btnConcluido = new JButton("Concluído");
		btnConcluido.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				dispose();
		        
			}
		});
		btnConcluido.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnConcluido.setBounds(357, 431, 100, 40);
		contentPane.add(btnConcluido);
		
		JButton btnExcluir = new JButton("Excluir");
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				TelaConfirmacao3 tc3 = new TelaConfirmacao3(idUsuario, TelaFuncionario.this);
				tc3.setVisible(true);
				
			}
		});
		btnExcluir.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnExcluir.setBounds(45, 431, 100, 40);
		contentPane.add(btnExcluir);
		
		JButton btnEditar = new JButton("Editar");
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				tfe = new TelaFuncionarioEditar(idUsuario, TelaFuncionario.this);
				tfe.preencherCampos(idUsuario);
				tfe.setVisible(true);
			}
		});
		btnEditar.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnEditar.setBounds(199, 431, 100, 40);
		contentPane.add(btnEditar);
		
		setLocationRelativeTo(null);
	}
	
	public void preencherCampos(int idUsuario) {
	    this.idUsuario = idUsuario;
	    usuario = usuarioBD.obterUsuarioPorId(idUsuario);

	    if (usuario != null) {
	    	campoNome.setText(usuario.getNome());
	        campoSenha.setText(usuario.getSenha());
	        campoEmail.setText(usuario.getEmail());
	        campoTelefone.setText(usuario.getTelefone());
	        campoCpf.setText(usuario.getCpf());
	        
	        
	        int nivelDeAcesso = usuario.getNivel();
	        campoNivel.setSelectedIndex(nivelDeAcesso);
	        
	        
	    }
	}
}
