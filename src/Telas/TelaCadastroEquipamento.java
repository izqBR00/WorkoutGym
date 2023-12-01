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

import Banco.EquipamentoBD;
import Dados.Equipamento;
import java.awt.Toolkit;

public class TelaCadastroEquipamento extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField campoNome;
	private JTextField campoQuantidade;
	
	private Equipamento equipamento;
	private EquipamentoBD equipamentoBD;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaCadastroEquipamento frame = new TelaCadastroEquipamento();
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
	public TelaCadastroEquipamento() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(TelaCadastroEquipamento.class.getResource("/Imagem/WorkoutGym logo png.png")));
		setResizable(false);
		equipamento = new Equipamento();
		equipamentoBD = new EquipamentoBD();
		
		setTitle("Cadastro de equipamento");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 440, 389);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 0, 0));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		campoNome = new JTextField();
		campoNome.setFont(new Font("Tahoma", Font.PLAIN, 15));
		campoNome.setBounds(32, 64, 362, 31);
		contentPane.add(campoNome);
		campoNome.setColumns(10);
		
		JLabel lblNome = new JLabel("Nome");
		lblNome.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNome.setForeground(new Color(255, 255, 255));
		lblNome.setBounds(32, 37, 81, 31);
		contentPane.add(lblNome);
		
		campoQuantidade = new JTextField();
		campoQuantidade.setFont(new Font("Tahoma", Font.PLAIN, 15));
		campoQuantidade.setColumns(10);
		campoQuantidade.setBounds(32, 176, 160, 31);
		contentPane.add(campoQuantidade);
		
		JLabel lblQuantidade = new JLabel("Quantidade");
		lblQuantidade.setForeground(Color.WHITE);
		lblQuantidade.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblQuantidade.setBounds(32, 150, 111, 31);
		contentPane.add(lblQuantidade);
		
		JComboBox campoEstado = new JComboBox();
		campoEstado.setFont(new Font("Tahoma", Font.PLAIN, 15));
		campoEstado.setModel(new DefaultComboBoxModel(new String[] {"Funcional", "Em manutenção", "Quebrado"}));
		campoEstado.setMaximumRowCount(3);
		campoEstado.setBounds(248, 176, 146, 31);
		contentPane.add(campoEstado);
		
		JLabel lblEstado = new JLabel("Estado");
		lblEstado.setForeground(Color.WHITE);
		lblEstado.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblEstado.setBounds(248, 150, 111, 31);
		contentPane.add(lblEstado);
		
		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(todosCamposPreenchidos()) {
						equipamento.setNome(campoNome.getText());
					equipamento.setQuantidade(Integer.parseInt(campoQuantidade.getText()));
					String estadoSelecionado = (String) campoEstado.getSelectedItem();
			        equipamento.setEstado(estadoSelecionado);
			        
			        if(equipamentoBD.inserirEquipamento(equipamento))
					{
						JOptionPane.showMessageDialog(null,"Cadastro realizado com sucesso!!!","Sucesso!!!",JOptionPane.INFORMATION_MESSAGE);
					}
					else
					{
						JOptionPane.showMessageDialog(null,"Cadastro não realizado!!!","Erro",JOptionPane.INFORMATION_MESSAGE);
					}
			        
			        campoNome.setText("");
			        campoQuantidade.setText("");
			        dispose();
				}
				else {
					TelaAviso2 ta2 = new TelaAviso2();
					ta2.setVisible(true);
				}
				
		        
			}
		});
		btnSalvar.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnSalvar.setBounds(248, 273, 89, 41);
		contentPane.add(btnSalvar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCancelar.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnCancelar.setBounds(94, 273, 89, 41);
		contentPane.add(btnCancelar);
		
		setLocationRelativeTo(null);
	}
	
	private boolean todosCamposPreenchidos() {
	    return !campoNome.getText().isEmpty() && !campoQuantidade.getText().isEmpty(); 
	}
}
