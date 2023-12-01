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
import Dados.Usuario;
import java.awt.Toolkit;

public class TelaCadastroFuncionario extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField campoNome;
	private JTextField campoSenha;
	private JTextField campoEmail;
	private JTextField campoTelefone;
	private JTextField campoCpf;

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
	public TelaCadastroFuncionario() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(TelaCadastroFuncionario.class.getResource("/Imagem/WorkoutGym logo png.png")));
		setResizable(false);
		setTitle("Cadastrar funcionário");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 507, 540);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 0, 0));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		campoNome = new JTextField();
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
		campoTelefone.setFont(new Font("Tahoma", Font.PLAIN, 15));
		campoTelefone.setColumns(10);
		campoTelefone.setBounds(45, 271, 171, 25);
		contentPane.add(campoTelefone);
		
		campoCpf = new JTextField();
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
		
		JComboBox comboNivel = new JComboBox();
		comboNivel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		comboNivel.setModel(new DefaultComboBoxModel(new String[] {"Funcionário", "Administrador"}));
		comboNivel.setBounds(45, 350, 171, 22);
		contentPane.add(comboNivel);
		
		JLabel lblNivel = new JLabel("Nível de acesso");
		lblNivel.setForeground(Color.WHITE);
		lblNivel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNivel.setBounds(45, 332, 171, 18);
		contentPane.add(lblNivel);
		
		JButton btnConfirmar = new JButton("Confirmar");
		btnConfirmar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Usuario usuario = new Usuario();
		        UsuarioBD usuarioBD = new UsuarioBD();
		        
		        if(todosCamposPreenchidos()) {
		        	if(usuarioBD.usuarioRepetido(campoNome.getText())){
			        	TelaAviso3 ta3 = new TelaAviso3();
		        		ta3.setVisible(true);
		        	}
		        	else {
		        		String nivelSelecionado = (String) comboNivel.getSelectedItem();
				        int nivel = (nivelSelecionado.equals("Funcionário")) ? 0 : 1;
				        
				        usuario.setNome(campoNome.getText());
				        usuario.setSenha(campoSenha.getText());
				        usuario.setNivel(nivel);
				        usuario.setEmail(campoEmail.getText());
				        usuario.setTelefone(campoTelefone.getText());
				        usuario.setCpf(campoCpf.getText());
				        
				        if(usuarioBD.inserirUsuario(usuario))
						{
							JOptionPane.showMessageDialog(null,"Cadastro realizado com sucesso!!!","Sucesso!!!",JOptionPane.INFORMATION_MESSAGE);
						}
						else
						{
							JOptionPane.showMessageDialog(null,"Cadastro não realizado!!!","Erro",JOptionPane.INFORMATION_MESSAGE);
						}
				        
				        campoNome.setText("");
						campoSenha.setText("");
						campoEmail.setText("");
						campoTelefone.setText("");
						campoCpf.setText("");
						comboNivel.setSelectedItem("Funcionário");
						dispose();
		        		
		        	}
			        	
		        }
		        else {
		        	TelaAviso2 ta2 = new TelaAviso2();
					ta2.setVisible(true);
		        }
				
				
		        
			}
		});
		btnConfirmar.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnConfirmar.setBounds(357, 431, 100, 40);
		contentPane.add(btnConfirmar);
		
		JButton btnLimpar = new JButton("Limpar");
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				campoNome.setText("");
				campoSenha.setText("");
				campoEmail.setText("");
				campoTelefone.setText("");
				campoCpf.setText("");
				comboNivel.setSelectedItem("Funcionário");
				
			}
		});
		btnLimpar.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnLimpar.setBounds(45, 431, 100, 40);
		contentPane.add(btnLimpar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				campoNome.setText("");
				campoSenha.setText("");
				campoEmail.setText("");
				campoTelefone.setText("");
				campoCpf.setText("");
				comboNivel.setSelectedItem("Funcionário");
				
				dispose();
			}
		});
		btnCancelar.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnCancelar.setBounds(199, 431, 100, 40);
		contentPane.add(btnCancelar);
		
		setLocationRelativeTo(null);
	}
	
	private boolean todosCamposPreenchidos() {
	    return !campoNome.getText().isEmpty() && !campoSenha.getText().isEmpty() && !campoEmail.getText().isEmpty()
	            && !campoCpf.getText().isEmpty() && !campoTelefone.getText().isEmpty();
	}
}
