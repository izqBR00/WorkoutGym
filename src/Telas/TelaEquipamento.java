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
import Dados.Cliente;
import Dados.Equipamento;
import java.awt.Toolkit;

public class TelaEquipamento extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField campoNome;
	private JTextField campoQuantidade;
	
	private Equipamento equipamento;
	private EquipamentoBD equipamentoBD;
	private int idEquipamento;
	private JComboBox campoEstado;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaEquipamento frame = new TelaEquipamento();
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
	public TelaEquipamento() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(TelaEquipamento.class.getResource("/Imagem/WorkoutGym logo png.png")));
		setResizable(false);
		equipamento = new Equipamento();
		equipamentoBD = new EquipamentoBD();
		
		setTitle("Editar equipamento");
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
		
		campoEstado = new JComboBox();
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
					equipamento.setCodigo(idEquipamento);
					equipamento.setNome(campoNome.getText());
	                equipamento.setQuantidade(Integer.parseInt(campoQuantidade.getText()));
	                
	                equipamento.setEstado((String) campoEstado.getSelectedItem());
	    	        
	    	        campoEstado.setSelectedItem(equipamento.getEstado());
	                
	
	                if (equipamentoBD.atualizarEquipamento(equipamento)) {
	                    JOptionPane.showMessageDialog(null, "Equipamento atualizado com sucesso!");
	                    dispose();
	                } else {
	                    JOptionPane.showMessageDialog(null, "Erro ao atualizar equipamento no banco de dados.");
	                }
				}
				else {
					TelaAviso2 ta2 = new TelaAviso2();
					ta2.setVisible(true);
				}
				
				
                
			}	
		});
		btnSalvar.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnSalvar.setBounds(305, 273, 89, 41);
		contentPane.add(btnSalvar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCancelar.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnCancelar.setBounds(171, 273, 89, 41);
		contentPane.add(btnCancelar);
		
		JButton btnExcluir = new JButton("Excluir");
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaConfirmacao2 tc2 = new TelaConfirmacao2(idEquipamento, TelaEquipamento.this);
				tc2.setVisible(true);
			}
		});
		btnExcluir.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnExcluir.setBounds(32, 273, 89, 41);
		contentPane.add(btnExcluir);
		
		setLocationRelativeTo(null);
	}
	
	public void preencherCampos(int idEquipamento) {
	    this.idEquipamento = idEquipamento;
	    Equipamento equipamento = equipamentoBD.obterEquipamentoPorId(idEquipamento);

	    if (equipamento != null) {
	    	campoNome.setText(equipamento.getNome());
	        campoQuantidade.setText(String.valueOf(equipamento.getQuantidade()));
	        
	        String estadoDoBanco = equipamento.getEstado();
	        campoEstado.setSelectedItem(estadoDoBanco);
	    }
	}
	
	private boolean todosCamposPreenchidos() {
	    return !campoNome.getText().isEmpty() && !campoQuantidade.getText().isEmpty(); 
	}
}
