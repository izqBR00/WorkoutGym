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

public class TelaMatricula extends JFrame {

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
	
	private int idCliente;
	private Cliente cliente;
	private ClienteBD clienteBD;
	private JTextField campoData;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaMatricula frame = new TelaMatricula();
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
	public TelaMatricula() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(TelaMatricula.class.getResource("/Imagem/WorkoutGym logo png.png")));
		
		this.idCliente = idCliente;
		cliente = new Cliente();
		clienteBD = new ClienteBD();
		
		setResizable(false);
		setTitle("Ficha do aluno");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 520, 573);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 0, 0));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		campoNome = new JTextField();
		campoNome.setEditable(false);
		campoNome.setBounds(43, 30, 424, 20);
		contentPane.add(campoNome);
		campoNome.setColumns(10);
		
		campoRua = new JTextField();
		campoRua.setEditable(false);
		campoRua.setColumns(10);
		campoRua.setBounds(43, 82, 292, 20);
		contentPane.add(campoRua);
		
		campoEmail = new JTextField();
		campoEmail.setEditable(false);
		campoEmail.setColumns(10);
		campoEmail.setBounds(43, 202, 424, 20);
		contentPane.add(campoEmail);
		
		campoTelefone = new JTextField();
		campoTelefone.setEditable(false);
		campoTelefone.setColumns(10);
		campoTelefone.setBounds(43, 270, 181, 20);
		contentPane.add(campoTelefone);
		
		JLabel lblNome = new JLabel("Nome");
		lblNome.setForeground(new Color(255, 255, 255));
		lblNome.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNome.setBounds(43, 11, 79, 20);
		contentPane.add(lblNome);
		
		JLabel lblEndereco = new JLabel("Rua");
		lblEndereco.setForeground(Color.WHITE);
		lblEndereco.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblEndereco.setBounds(43, 61, 113, 20);
		contentPane.add(lblEndereco);
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setForeground(Color.WHITE);
		lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblEmail.setBounds(43, 180, 79, 20);
		contentPane.add(lblEmail);
		
		JLabel lblTelefone = new JLabel("Telefone");
		lblTelefone.setForeground(Color.WHITE);
		lblTelefone.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblTelefone.setBounds(43, 248, 79, 20);
		contentPane.add(lblTelefone);
		
		campoCpf = new JTextField();
		campoCpf.setEditable(false);
		campoCpf.setColumns(10);
		campoCpf.setBounds(286, 270, 181, 20);
		contentPane.add(campoCpf);
		
		JLabel lblCpf = new JLabel("CPF");
		lblCpf.setForeground(Color.WHITE);
		lblCpf.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblCpf.setBounds(286, 248, 79, 20);
		contentPane.add(lblCpf);
		
		JButton btnExcluir = new JButton("Excluir");
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
		      TelaConfirmacao tm = new TelaConfirmacao(idCliente, TelaMatricula.this);
		      tm.setVisible(true);
		    }
		});
		btnExcluir.setBounds(43, 446, 100, 30);
		contentPane.add(btnExcluir);
		
		JButton btnConcluido = new JButton("Concluído");
		btnConcluido.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
                    dispose();
			}				
		});
		
		btnConcluido.setBounds(367, 446, 100, 30);
		contentPane.add(btnConcluido);
		
		campoBairro = new JTextField();
		campoBairro.setEditable(false);
		campoBairro.setColumns(10);
		campoBairro.setBounds(43, 134, 181, 20);
		contentPane.add(campoBairro);
		
		JLabel lblBairro = new JLabel("Bairro");
		lblBairro.setForeground(Color.WHITE);
		lblBairro.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblBairro.setBounds(43, 113, 113, 20);
		contentPane.add(lblBairro);
		
		campoCidade = new JTextField();
		campoCidade.setEditable(false);
		campoCidade.setColumns(10);
		campoCidade.setBounds(286, 134, 181, 20);
		contentPane.add(campoCidade);
		
		JLabel lblCidade = new JLabel("Cidade");
		lblCidade.setForeground(Color.WHITE);
		lblCidade.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblCidade.setBounds(286, 113, 113, 20);
		contentPane.add(lblCidade);
		
		campoNumero = new JTextField();
		campoNumero.setEditable(false);
		campoNumero.setColumns(10);
		campoNumero.setBounds(367, 82, 100, 20);
		contentPane.add(campoNumero);
		
		JLabel lblNumero = new JLabel("Número");
		lblNumero.setForeground(Color.WHITE);
		lblNumero.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNumero.setBounds(367, 61, 113, 20);
		contentPane.add(lblNumero);
		
		JButton btnEditar = new JButton("Editar");
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaMatriculaEditar tme = new TelaMatriculaEditar(idCliente, TelaMatricula.this);
				tme.setVisible(true);
			}
		});
		btnEditar.setBounds(208, 446, 100, 30);
		contentPane.add(btnEditar);
		
		campoPlano = new JComboBox();
		campoPlano.setEnabled(false);
		campoPlano.setModel(new DefaultComboBoxModel(new String[] {"Mensal - R$80", "Trimenstral - R$210", "Semestral - R$360", "Anual - R$600"}));
		campoPlano.setBounds(43, 375, 181, 22);
		contentPane.add(campoPlano);
		
		JLabel lblPlane = new JLabel("Plano");
		lblPlane.setForeground(Color.WHITE);
		lblPlane.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblPlane.setBounds(43, 354, 79, 20);
		contentPane.add(lblPlane);
		
		campoStatus = new JComboBox();
		campoStatus.setEnabled(false);
		campoStatus.setModel(new DefaultComboBoxModel(new String[] {"Ativo", "Vencido"}));
		campoStatus.setBounds(286, 375, 181, 22);
		contentPane.add(campoStatus);
		
		JLabel lblStatus = new JLabel("Status");
		lblStatus.setForeground(Color.WHITE);
		lblStatus.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblStatus.setBounds(286, 354, 79, 20);
		contentPane.add(lblStatus);
		
		campoData = new JTextField();
		campoData.setEditable(false);
		campoData.setColumns(10);
		campoData.setBounds(43, 323, 181, 20);
		contentPane.add(campoData);
		
		JLabel lblData = new JLabel("Início do plano");
		lblData.setForeground(Color.WHITE);
		lblData.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblData.setBounds(43, 301, 181, 20);
		contentPane.add(lblData);
		
		preencherCampos(idCliente);
		setLocationRelativeTo(null);
	}
	
	public void preencherCampos(int idCliente) {
		this.idCliente = idCliente;
		Cliente cliente = clienteBD.obterClientePorId(idCliente);

		    if (cliente != null) {
		        campoNome.setText(cliente.getNome());
		        campoRua.setText(cliente.getRua());
		        campoNumero.setText(String.valueOf(cliente.getNumero()));
		        campoBairro.setText(cliente.getBairro());
		        campoCidade.setText(cliente.getCidade());
		        campoEmail.setText(cliente.getEmail());
		        campoCpf.setText(cliente.getCpf());
		        campoTelefone.setText(String.valueOf(cliente.getTelefone()));
		        campoData.setText(cliente.getData());
		        campoPlano.setSelectedItem(cliente.getPlano());
		        campoStatus.setSelectedItem(cliente.getStatus());
		        
		    }
		
	}
}
