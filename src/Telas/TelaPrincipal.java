package Telas;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTabbedPane;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.ScrollPaneConstants;
import java.awt.Container;

import Banco.ClienteBD;
import Banco.Conexao;
import Dados.Cliente;
import Banco.EquipamentoBD;
import Dados.Equipamento;
import Banco.UsuarioBD;
import Dados.Usuario;
import Banco.AcessoBD;
import Dados.Login;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Statement;

import javax.swing.ListSelectionModel;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.Toolkit;


public class TelaPrincipal extends JFrame {
	
	private TelaCadastroMatricula tcm;
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	private DefaultTableModel modelo;
	private DefaultTableModel modelo1;
	private DefaultTableModel modelo2;
	private Cliente cliente;
	private ClienteBD clienteBD;
	private Equipamento equipamento;
	private EquipamentoBD equipamentoBD;
	private Usuario usuario;
	private UsuarioBD usuarioBD;
	private Login login;
	private AcessoBD acessoBD;
	private TelaInicial ti;
	private TelaAviso ta;
	private JTable tabelaMatriculas;
	private JTable tabelaEquipamento;
	private JTable tabelaFuncionarios;
	private JTextField campoNome;
	private JTextField campoSenha;
	private JTextField campoEmail;
	private JTextField campoTelefone;
	private JTextField campoCpf;
	private JComboBox campoNivel;
	private JComboBox campoNivel2;

	
	/**
	 * Create the frame.
	 */
	public TelaPrincipal(Login login) {
		setIconImage(Toolkit.getDefaultToolkit().getImage(TelaPrincipal.class.getResource("/Imagem/WorkoutGym logo png.png")));
		setResizable(false);
		ti = new TelaInicial();		
		tcm = new TelaCadastroMatricula();
		ta = new TelaAviso();
		acessoBD = new AcessoBD();
		Login loginObtido = acessoBD.obterLoginPorNome(login);
		
		setTitle("WorkoutGym App");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.LEFT);
		tabbedPane.setForeground(new Color(0, 0, 0));
		tabbedPane.setBackground(new Color(12, 192, 223));
		tabbedPane.setFont(new Font("Tahoma", Font.PLAIN, 30));
		tabbedPane.setBounds(0, 0, 684, 461);
		contentPane.add(tabbedPane);
		
		JPanel abaMatriculas = new JPanel();
		tabbedPane.addTab("Matrículas", null, abaMatriculas, null);
		abaMatriculas.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 488, 404);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		abaMatriculas.add(scrollPane);
		
		cliente = new Cliente();
		clienteBD = new ClienteBD();
		
		modelo = new DefaultTableModel();
		tabelaMatriculas = new JTable(modelo);
		
		tabelaMatriculas.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int selectedRow = tabelaMatriculas.getSelectedRow();
		        if (selectedRow != -1) {
		            int idCliente = (int) tabelaMatriculas.getValueAt(selectedRow, 0);
		            TelaMatricula tm = new TelaMatricula();
		            tm.preencherCampos(idCliente);
		            tm.setVisible(true);
			}
			}});
		
		modelo.addColumn("Código");
		modelo.addColumn("Nome");
		
		clienteBD.relatorioCliente(cliente, modelo);
		
		scrollPane.setViewportView(tabelaMatriculas);
		
		JButton botaoCadastrarMat = new JButton("Novo");
		botaoCadastrarMat.setBounds(294, 415, 130, 23);
		botaoCadastrarMat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tcm.setVisible(true);
			}
		});
		botaoCadastrarMat.setForeground(new Color(255, 255, 255));
		botaoCadastrarMat.setFont(new Font("Tahoma", Font.PLAIN, 20));
		botaoCadastrarMat.setBackground(new Color(0, 0, 0));
		abaMatriculas.add(botaoCadastrarMat);
		
		JButton botaoAtualizar = new JButton("Atualizar");
		botaoAtualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				modelo.setRowCount(0);
				clienteBD.relatorioCliente(cliente, modelo);
			}
		});
		botaoAtualizar.setForeground(Color.WHITE);
		botaoAtualizar.setFont(new Font("Tahoma", Font.PLAIN, 20));
		botaoAtualizar.setBackground(Color.BLACK);
		botaoAtualizar.setBounds(72, 415, 130, 23);
		abaMatriculas.add(botaoAtualizar);
		tabbedPane.setBackgroundAt(0, new Color(12, 192, 223));
		
		JPanel abaEquipamento = new JPanel();
		tabbedPane.addTab("Equipamento", null, abaEquipamento, null);
		abaEquipamento.setLayout(null);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(0, 0, 488, 404);
		scrollPane_1.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		abaEquipamento.add(scrollPane_1);
		
		equipamento = new Equipamento();
		equipamentoBD = new EquipamentoBD();
		
		modelo1 = new DefaultTableModel();
		tabelaEquipamento = new JTable(modelo1);
		tabelaEquipamento.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int selectedRow1 = tabelaEquipamento.getSelectedRow();
		        if (selectedRow1 != -1) {
		            int idEquipamento = (int) tabelaEquipamento.getValueAt(selectedRow1, 0);
		            TelaEquipamento te = new TelaEquipamento();
		            te.preencherCampos(idEquipamento);
		            te.setVisible(true);
			}
			}});
		
		modelo1.addColumn("Código");
		modelo1.addColumn("Nome");
		modelo1.addColumn("Quantidade");
		modelo1.addColumn("Estado");
		
		equipamentoBD.relatorioEquipamento(equipamento, modelo1);
		
		scrollPane_1.setViewportView(tabelaEquipamento);
		
		JButton botaoAtualizarEquip = new JButton("Atualizar");
		botaoAtualizarEquip.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				modelo1.setRowCount(0);
				equipamentoBD.relatorioEquipamento(equipamento, modelo1);
			}
		});
		botaoAtualizarEquip.setBounds(72, 415, 130, 23);
		botaoAtualizarEquip.setForeground(Color.WHITE);
		botaoAtualizarEquip.setFont(new Font("Tahoma", Font.PLAIN, 20));
		botaoAtualizarEquip.setBackground(Color.BLACK);
		abaEquipamento.add(botaoAtualizarEquip);
		
		JButton botaoCadastrarEquip = new JButton("Novo");
		botaoCadastrarEquip.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaCadastroEquipamento tce = new TelaCadastroEquipamento();
				tce.setVisible(true);
			}
		});
		botaoCadastrarEquip.setBounds(294, 415, 130, 23);
		botaoCadastrarEquip.setForeground(Color.WHITE);
		botaoCadastrarEquip.setFont(new Font("Tahoma", Font.PLAIN, 20));
		botaoCadastrarEquip.setBackground(Color.BLACK);
		abaEquipamento.add(botaoCadastrarEquip);
		tabbedPane.setBackgroundAt(1, new Color(12, 192, 223));
		
		JPanel abaFuncionarios = new JPanel();
		tabbedPane.addTab("Funcionários", null, abaFuncionarios, null);
		tabbedPane.setBackgroundAt(2, new Color(12, 192, 223));
		abaFuncionarios.setLayout(null);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane_2.setBounds(0, 0, 488, 404);
		abaFuncionarios.add(scrollPane_2);
		
		usuario = new Usuario();
		usuarioBD = new UsuarioBD();
		
		modelo2 = new DefaultTableModel();
		
		tabelaFuncionarios = new JTable(modelo2);
		tabelaFuncionarios.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int selectedRow2 = tabelaFuncionarios.getSelectedRow();
		        if (selectedRow2 != -1) {
		        	if(login.getNivel() == 1) {
		        		int idUsuario = (int) tabelaFuncionarios.getValueAt(selectedRow2, 0);
		        		TelaFuncionario tf = new TelaFuncionario();
		        		tf.preencherCampos(idUsuario);
		        		tf.setVisible(true);
		        	}
		        	else {
		        		ta.setVisible(true);
		        	}
		        
		        }
			}
		});
		
		modelo2.addColumn("Código");
		modelo2.addColumn("Nome");
		
		usuarioBD.relatorioUsuario(usuario, modelo2);
		
		scrollPane_2.setViewportView(tabelaFuncionarios);
		
		JButton botaoCadastrarFunc = new JButton("Novo");
		botaoCadastrarFunc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(login.getNivel() == 1) {
					TelaCadastroFuncionario tcf = new TelaCadastroFuncionario();
					tcf.setVisible(true);
				}
				else {
					ta.setVisible(true);
				}
				
				
			}
		});
		botaoCadastrarFunc.setForeground(Color.WHITE);
		botaoCadastrarFunc.setFont(new Font("Tahoma", Font.PLAIN, 20));
		botaoCadastrarFunc.setBackground(Color.BLACK);
		botaoCadastrarFunc.setBounds(294, 415, 130, 23);
		abaFuncionarios.add(botaoCadastrarFunc);
		
		JButton botaoAtualizarFunc = new JButton("Atualizar");
		botaoAtualizarFunc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				modelo2.setRowCount(0);
				usuarioBD.relatorioUsuario(usuario, modelo2);
			}
		});
		botaoAtualizarFunc.setForeground(Color.WHITE);
		botaoAtualizarFunc.setFont(new Font("Tahoma", Font.PLAIN, 20));
		botaoAtualizarFunc.setBackground(Color.BLACK);
		botaoAtualizarFunc.setBounds(72, 415, 130, 23);
		abaFuncionarios.add(botaoAtualizarFunc);
		
		JPanel abaPerfil = new JPanel();
		abaPerfil.setBackground(new Color(0, 0, 0));
		tabbedPane.addTab("Perfil", null, abaPerfil, null);
		abaPerfil.setLayout(null);
		
		campoNome = new JTextField();
		campoNome.setBounds(24, 45, 440, 27);
		abaPerfil.add(campoNome);
		campoNome.setColumns(10);
		
		campoSenha = new JTextField();
		campoSenha.setColumns(10);
		campoSenha.setBounds(24, 113, 440, 27);
		abaPerfil.add(campoSenha);
		
		campoEmail = new JTextField();
		campoEmail.setColumns(10);
		campoEmail.setBounds(24, 182, 440, 27);
		abaPerfil.add(campoEmail);
		
		campoTelefone = new JTextField();
		campoTelefone.setColumns(10);
		campoTelefone.setBounds(25, 253, 187, 27);
		abaPerfil.add(campoTelefone);
		
		campoCpf = new JTextField();
		campoCpf.setColumns(10);
		campoCpf.setBounds(277, 253, 187, 27);
		abaPerfil.add(campoCpf);
		
		JLabel lblNome = new JLabel("Nome");
		lblNome.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNome.setForeground(new Color(255, 255, 255));
		lblNome.setBounds(24, 23, 86, 27);
		abaPerfil.add(lblNome);
		
		JLabel lblSenha = new JLabel("Senha");
		lblSenha.setForeground(Color.WHITE);
		lblSenha.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblSenha.setBounds(24, 89, 86, 27);
		abaPerfil.add(lblSenha);
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setForeground(Color.WHITE);
		lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblEmail.setBounds(24, 160, 86, 27);
		abaPerfil.add(lblEmail);
		
		JLabel lblTelefone = new JLabel("Telefone");
		lblTelefone.setForeground(Color.WHITE);
		lblTelefone.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblTelefone.setBounds(24, 229, 86, 27);
		abaPerfil.add(lblTelefone);
		
		JLabel lblCpf = new JLabel("CPF");
		lblCpf.setForeground(Color.WHITE);
		lblCpf.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblCpf.setBounds(277, 229, 86, 27);
		abaPerfil.add(lblCpf);
		
		JLabel lblNivel = new JLabel("Nivel de acesso");
		lblNivel.setForeground(Color.WHITE);
		lblNivel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNivel.setBounds(24, 296, 171, 27);
		abaPerfil.add(lblNivel);
		
		JButton btnSair = new JButton("Sair");
		btnSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ti.setVisible(true);
				dispose();
			}
		});
		btnSair.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnSair.setBounds(77, 383, 100, 47);
		abaPerfil.add(btnSair);
		
		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(todosCamposPreenchidos()) {
					String novoNome = campoNome.getText();
					if (novoNome.equals(loginObtido.getNome())) {
					     	usuario.setCodigo(login.getCodigo());
					        usuario.setNome(novoNome);
					        usuario.setSenha(campoSenha.getText());
					        usuario.setEmail(campoEmail.getText());
					        usuario.setTelefone(campoTelefone.getText());
					        usuario.setCpf(campoCpf.getText());

					        String nivelSelecionado = (String) campoNivel2.getSelectedItem();
					        int nivel = (nivelSelecionado.equals("Funcionário")) ? 0 : 1;
					        usuario.setNivel(nivel);

					        if (usuarioBD.atualizarUsuario(usuario)) {
					            JOptionPane.showMessageDialog(null, "Usuário atualizado com sucesso!");
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
					    	usuario.setCodigo(login.getCodigo());
					        usuario.setNome(novoNome);
					        usuario.setSenha(campoSenha.getText());
					        usuario.setEmail(campoEmail.getText());
					        usuario.setTelefone(campoTelefone.getText());
					        usuario.setCpf(campoCpf.getText());

					        String nivelSelecionado = (String) campoNivel2.getSelectedItem();
					        int nivel = (nivelSelecionado.equals("Funcionário")) ? 0 : 1;
					        usuario.setNivel(nivel);

					        if (usuarioBD.atualizarUsuario(usuario)) {
					            JOptionPane.showMessageDialog(null, "Usuário atualizado com sucesso!");
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
		btnSalvar.setBounds(325, 383, 100, 47);
		abaPerfil.add(btnSalvar);
		
		campoNivel2 = new JComboBox();
		campoNivel2.setEnabled(false);
		campoNivel2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		campoNivel2.setModel(new DefaultComboBoxModel(new String[] {"Funcionário", "Administrador"}));
		campoNivel2.setBounds(24, 324, 188, 27);
		abaPerfil.add(campoNivel2);
		
		preencherCampos(login);
		
		setLocationRelativeTo(null);
	}
	
	public void preencherCampos(Login login) {
		Login loginObtido = acessoBD.obterLoginPorNome(login);
		
		campoNome.setText(loginObtido.getNome());
		campoSenha.setText(loginObtido.getSenha());
		campoEmail.setText(loginObtido.getEmail());
		campoTelefone.setText(loginObtido.getTelefone());
		campoCpf.setText(loginObtido.getCpf());
		campoNivel2.setSelectedIndex(loginObtido.getNivel());
		
	}
	
	private boolean todosCamposPreenchidos() {
	    return !campoNome.getText().isEmpty() && !campoSenha.getText().isEmpty() && !campoEmail.getText().isEmpty()
	            && !campoCpf.getText().isEmpty() && !campoTelefone.getText().isEmpty();
	}

}

