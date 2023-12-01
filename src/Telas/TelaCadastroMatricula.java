package Telas;

import java.awt.EventQueue;
import Banco.ClienteBD;
import Dados.Cliente;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.Toolkit;



public class TelaCadastroMatricula extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField campoNome;
	private JTextField campoRua;
	private JTextField campoEmail;
	private JTextField campoTelefone;
	private JTextField campoCpf;
	private JTextField campoBairro;
	private JTextField campoCidade;
	private JTextField campoNumero;
	private JComboBox campoPlano;
	private JComboBox campoStatus;
	
	private Cliente cliente;
	private ClienteBD clienteBD;
	private JTextField campoData;


	/**
	 * Create the frame.
	 */
	public TelaCadastroMatricula() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(TelaCadastroMatricula.class.getResource("/Imagem/WorkoutGym logo png.png")));
		
		cliente = new Cliente();
		clienteBD = new ClienteBD();
		
		setResizable(false);
		setTitle("Matricula de aluno");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 520, 573);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 0, 0));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		campoNome = new JTextField();
		campoNome.setBounds(43, 48, 424, 20);
		contentPane.add(campoNome);
		campoNome.setColumns(10);
		
		campoRua = new JTextField();
		campoRua.setColumns(10);
		campoRua.setBounds(43, 100, 292, 20);
		contentPane.add(campoRua);
		
		campoEmail = new JTextField();
		campoEmail.setColumns(10);
		campoEmail.setBounds(43, 213, 424, 20);
		contentPane.add(campoEmail);
		
		campoTelefone = new JTextField();
		campoTelefone.setColumns(10);
		campoTelefone.setBounds(43, 277, 181, 20);
		contentPane.add(campoTelefone);
		
		JLabel lblNome = new JLabel("Nome");
		lblNome.setForeground(new Color(255, 255, 255));
		lblNome.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNome.setBounds(43, 29, 79, 20);
		contentPane.add(lblNome);
		
		JLabel lblEndereco = new JLabel("Rua");
		lblEndereco.setForeground(Color.WHITE);
		lblEndereco.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblEndereco.setBounds(43, 79, 113, 20);
		contentPane.add(lblEndereco);
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setForeground(Color.WHITE);
		lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblEmail.setBounds(43, 191, 79, 20);
		contentPane.add(lblEmail);
		
		JLabel lblTelefone = new JLabel("Telefone");
		lblTelefone.setForeground(Color.WHITE);
		lblTelefone.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblTelefone.setBounds(43, 255, 79, 20);
		contentPane.add(lblTelefone);
		
		campoCpf = new JTextField();
		campoCpf.setColumns(10);
		campoCpf.setBounds(286, 277, 181, 20);
		contentPane.add(campoCpf);
		
		JLabel lblCpf = new JLabel("CPF");
		lblCpf.setForeground(Color.WHITE);
		lblCpf.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblCpf.setBounds(286, 255, 79, 20);
		contentPane.add(lblCpf);
		
		JButton btnLimpar = new JButton("Limpar");
		btnLimpar.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				campoNome.setText("");
				campoRua.setText("");
				campoNumero.setText("");
				campoBairro.setText("");
				campoCidade.setText("");
				campoEmail.setText("");
				campoCpf.setText("");
				campoTelefone.setText("");
				campoData.setText("");
				campoPlano.setSelectedItem("Mensal - R$80");
				campoStatus.setSelectedItem("Ativo");
			}
		});
		btnLimpar.setBounds(43, 458, 100, 40);
		contentPane.add(btnLimpar);
		
		JButton btnConfirmar = new JButton("Confirmar");
		btnConfirmar.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnConfirmar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(todosCamposPreenchidos()) {
					if(clienteBD.clienteRepetido(campoNome.getText())) {
						TelaAviso4 ta4 = new TelaAviso4();
						ta4.setVisible(true);
					}
					else {
						cliente.setNome(campoNome.getText());								
						cliente.setRua(campoRua.getText());								
						cliente.setNumero(Integer.parseInt(campoNumero.getText()));								
						cliente.setBairro(campoBairro.getText());								
						cliente.setCidade(campoCidade.getText());								
						cliente.setCpf(campoCpf.getText());								
						cliente.setTelefone(campoTelefone.getText());
						cliente.setEmail(campoEmail.getText());
						cliente.setData(campoData.getText());
						cliente.setPlano((String) campoPlano.getSelectedItem());
						cliente.setStatus((String) campoStatus.getSelectedItem());
						
						if(clienteBD.inserirCliente(cliente))
						{
							JOptionPane.showMessageDialog(null,"Cadastro realizado com sucesso!!!","Sucesso!!!",JOptionPane.INFORMATION_MESSAGE);
						}
						else
						{
							JOptionPane.showMessageDialog(null,"Cadastro não realizado!!!","Erro",JOptionPane.INFORMATION_MESSAGE);
						}
						
						campoNome.setText("");
						campoRua.setText("");
						campoNumero.setText("");
						campoBairro.setText("");
						campoCidade.setText("");
						campoEmail.setText("");
						campoCpf.setText("");
						campoTelefone.setText("");
						campoData.setText("");
						campoPlano.setSelectedItem("Mensal - R$80");
						campoStatus.setSelectedItem("Ativo");
						dispose();
					}
					
				}
				else {
					TelaAviso2 ta2 = new TelaAviso2();
					ta2.setVisible(true);
					
				}
				
				
			}
		});
		btnConfirmar.setBounds(367, 458, 100, 40);
		contentPane.add(btnConfirmar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				campoNome.setText("");
				campoRua.setText("");
				campoNumero.setText("");
				campoBairro.setText("");
				campoCidade.setText("");
				campoEmail.setText("");
				campoCpf.setText("");
				campoTelefone.setText("");
				dispose();
			}
		});
		btnCancelar.setBounds(202, 458, 113, 40);
		contentPane.add(btnCancelar);
		
		campoBairro = new JTextField();
		campoBairro.setColumns(10);
		campoBairro.setBounds(43, 160, 181, 20);
		contentPane.add(campoBairro);
		
		JLabel lblBairro = new JLabel("Bairro");
		lblBairro.setForeground(Color.WHITE);
		lblBairro.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblBairro.setBounds(43, 139, 113, 20);
		contentPane.add(lblBairro);
		
		campoCidade = new JTextField();
		campoCidade.setColumns(10);
		campoCidade.setBounds(286, 160, 181, 20);
		contentPane.add(campoCidade);
		
		JLabel lblCidade = new JLabel("Cidade");
		lblCidade.setForeground(Color.WHITE);
		lblCidade.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblCidade.setBounds(286, 139, 113, 20);
		contentPane.add(lblCidade);
		
		campoNumero = new JTextField();
		campoNumero.setColumns(10);
		campoNumero.setBounds(367, 100, 100, 20);
		contentPane.add(campoNumero);
		
		JLabel lblNumero = new JLabel("Número");
		lblNumero.setForeground(Color.WHITE);
		lblNumero.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNumero.setBounds(367, 79, 113, 20);
		contentPane.add(lblNumero);
		
		campoData = new JTextField();
		campoData.setColumns(10);
		campoData.setBounds(43, 340, 181, 20);
		contentPane.add(campoData);
		
		JLabel lblData = new JLabel("Início do plano");
		lblData.setForeground(Color.WHITE);
		lblData.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblData.setBounds(43, 318, 181, 20);
		contentPane.add(lblData);
		
		JLabel lblPlane = new JLabel("Plano");
		lblPlane.setForeground(Color.WHITE);
		lblPlane.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblPlane.setBounds(43, 371, 79, 20);
		contentPane.add(lblPlane);
		
		campoPlano = new JComboBox();
		campoPlano.setModel(new DefaultComboBoxModel(new String[] {"Mensal - R$80", "Trimenstral - R$210", "Semestral - R$360", "Anual - R$600"}));
		campoPlano.setBounds(43, 392, 181, 22);
		contentPane.add(campoPlano);
		
		JLabel lblStatus = new JLabel("Status");
		lblStatus.setForeground(Color.WHITE);
		lblStatus.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblStatus.setBounds(286, 371, 79, 20);
		contentPane.add(lblStatus);
		
		campoStatus = new JComboBox();
		campoStatus.setModel(new DefaultComboBoxModel(new String[] {"Ativo", "Vencido"}));
		campoStatus.setBounds(286, 392, 181, 22);
		contentPane.add(campoStatus);
		
		setLocationRelativeTo(null);
	}
	
	private boolean todosCamposPreenchidos() {
	    return !campoNome.getText().isEmpty() && !campoRua.getText().isEmpty() && !campoNumero.getText().isEmpty()
	            && !campoBairro.getText().isEmpty() && !campoCidade.getText().isEmpty() && !campoEmail.getText().isEmpty()
	            && !campoCpf.getText().isEmpty() && !campoTelefone.getText().isEmpty() && !campoData.getText().isEmpty();
	}
}
