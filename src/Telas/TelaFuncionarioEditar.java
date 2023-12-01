package Telas;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JComboBox;

import Banco.UsuarioBD;
import Dados.Cliente;
import Dados.Usuario;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.DefaultComboBoxModel;
import java.awt.Toolkit;

public class TelaFuncionarioEditar extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField campoNome;
	private JTextField campoSenha;
	private JTextField campoEmail;
	private JTextField campoTelefone;
	private JTextField campoCpf;
	private JComboBox campoNivel;
	
	private TelaFuncionario tf;
	private int idUsuario;
	private UsuarioBD usuarioBD;
	private Usuario usuario;



	/**
	 * Create the frame.
	 */
	public TelaFuncionarioEditar(int idUsuario, TelaFuncionario tf) {
		setIconImage(Toolkit.getDefaultToolkit().getImage(TelaFuncionarioEditar.class.getResource("/Imagem/WorkoutGym logo png.png")));
		setResizable(false);
		this.tf = tf;
		this.idUsuario = idUsuario;
		usuarioBD = new UsuarioBD();
		usuario = usuarioBD.obterUsuarioPorId(idUsuario);
		
		setTitle("Editar funcionário");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 507, 540);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 0, 0));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNome = new JLabel("Nome");
		lblNome.setForeground(Color.WHITE);
		lblNome.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNome.setBounds(43, 23, 62, 18);
		contentPane.add(lblNome);
		
		campoNome = new JTextField();
		campoNome.setFont(new Font("Tahoma", Font.PLAIN, 15));
		campoNome.setColumns(10);
		campoNome.setBounds(43, 41, 412, 25);
		contentPane.add(campoNome);
		
		JLabel lblSenha = new JLabel("Senha");
		lblSenha.setForeground(Color.WHITE);
		lblSenha.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblSenha.setBounds(43, 97, 62, 18);
		contentPane.add(lblSenha);
		
		campoSenha = new JTextField();
		campoSenha.setFont(new Font("Tahoma", Font.PLAIN, 15));
		campoSenha.setColumns(10);
		campoSenha.setBounds(43, 115, 412, 25);
		contentPane.add(campoSenha);
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setForeground(Color.WHITE);
		lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblEmail.setBounds(43, 164, 62, 18);
		contentPane.add(lblEmail);
		
		campoEmail = new JTextField();
		campoEmail.setFont(new Font("Tahoma", Font.PLAIN, 15));
		campoEmail.setColumns(10);
		campoEmail.setBounds(43, 183, 412, 25);
		contentPane.add(campoEmail);
		
		JLabel lblTelefone = new JLabel("Telefone");
		lblTelefone.setForeground(Color.WHITE);
		lblTelefone.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblTelefone.setBounds(43, 237, 86, 18);
		contentPane.add(lblTelefone);
		
		campoTelefone = new JTextField();
		campoTelefone.setFont(new Font("Tahoma", Font.PLAIN, 15));
		campoTelefone.setColumns(10);
		campoTelefone.setBounds(43, 258, 171, 25);
		contentPane.add(campoTelefone);
		
		JLabel lblCpf = new JLabel("CPF");
		lblCpf.setForeground(Color.WHITE);
		lblCpf.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblCpf.setBounds(284, 237, 86, 18);
		contentPane.add(lblCpf);
		
		campoCpf = new JTextField();
		campoCpf.setFont(new Font("Tahoma", Font.PLAIN, 15));
		campoCpf.setColumns(10);
		campoCpf.setBounds(284, 258, 171, 25);
		contentPane.add(campoCpf);
		
		JLabel lblNivel = new JLabel("Nível de acesso");
		lblNivel.setForeground(Color.WHITE);
		lblNivel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNivel.setBounds(43, 319, 171, 18);
		contentPane.add(lblNivel);
		
		campoNivel = new JComboBox();
		campoNivel.setModel(new DefaultComboBoxModel(new String[] {"Funcionário", "Administrador"}));
		campoNivel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		campoNivel.setBounds(43, 337, 171, 22);
		contentPane.add(campoNivel);
		
		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(todosCamposPreenchidos()) {
					String novoNome = campoNome.getText();
					if (novoNome.equals(usuario.getNome())) {
					     	usuario.setCodigo(idUsuario);
					        usuario.setNome(novoNome);
					        usuario.setSenha(campoSenha.getText());
					        usuario.setEmail(campoEmail.getText());
					        usuario.setTelefone(campoTelefone.getText());
					        usuario.setCpf(campoCpf.getText());

					        String nivelSelecionado = (String) campoNivel.getSelectedItem();
					        int nivel = (nivelSelecionado.equals("Funcionário")) ? 0 : 1;
					        usuario.setNivel(nivel);

					        if (usuarioBD.atualizarUsuario(usuario)) {
					            JOptionPane.showMessageDialog(null, "Usuário atualizado com sucesso!");
					            dispose();
					            tf.dispose();
					        } else {
					            JOptionPane.showMessageDialog(null, "Erro ao atualizar usuário no banco de dados.");
					        }
					}
					else {
						if (usuarioBD.usuarioRepetido(novoNome)) {
					        TelaAviso3 ta3 = new TelaAviso3();
					        ta3.setVisible(true);
					    } 
					    else {
					    	usuario.setCodigo(idUsuario);
					        usuario.setNome(novoNome);
					        usuario.setSenha(campoSenha.getText());
					        usuario.setEmail(campoEmail.getText());
					        usuario.setTelefone(campoTelefone.getText());
					        usuario.setCpf(campoCpf.getText());

					        String nivelSelecionado = (String) campoNivel.getSelectedItem();
					        int nivel = (nivelSelecionado.equals("Funcionário")) ? 0 : 1;
					        usuario.setNivel(nivel);

					        if (usuarioBD.atualizarUsuario(usuario)) {
					            JOptionPane.showMessageDialog(null, "Usuário atualizado com sucesso!");
					            dispose();
					            tf.dispose();
					        } else {
					            JOptionPane.showMessageDialog(null, "Erro ao atualizar usuário no banco de dados.");
					        }
					    }
					}
				}
				else {
					TelaAviso2 ta2 = new TelaAviso2();
					ta2.setVisible(true);
				}
				
				
				
				
			}
		});
		btnSalvar.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnSalvar.setBounds(310, 414, 110, 42);
		contentPane.add(btnSalvar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCancelar.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnCancelar.setBounds(68, 414, 110, 42);
		contentPane.add(btnCancelar);
		
		setLocationRelativeTo(null);
	}
	
	public void preencherCampos(int idUsuario) {
		this.idUsuario = idUsuario;
		Usuario usuario = usuarioBD.obterUsuarioPorId(idUsuario);

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
	
	private boolean todosCamposPreenchidos() {
	    return !campoNome.getText().isEmpty() && !campoSenha.getText().isEmpty() && !campoEmail.getText().isEmpty()
	            && !campoCpf.getText().isEmpty() && !campoTelefone.getText().isEmpty();
	}

}
