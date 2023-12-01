package Banco;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Dados.Cliente;
import Dados.Equipamento;


public class EquipamentoBD
{

    Connection connection = null;

    public boolean inserirEquipamento(Equipamento equipamento) 
    {
        System.out.println("Inserir equipamento");
        
        connection = Conexao.getInstance().getConnection();
        System.out.println("Conectado e pronto para inserir");
        Statement stmt = null;
    
        try
        {
            stmt = connection.createStatement();

            String sql = "INSERT INTO aparelhos (nome,quantidade,estado) "
               + "VALUES('"+equipamento.getNome()+"','"+equipamento.getQuantidade()+"',"
               + "'"+equipamento.getEstado()+"');";
            System.out.println("SQL: " + sql);
            stmt.executeUpdate(sql);
            
            return true;
            
        } 
        catch (SQLException e)
        {
            System.out.println(e.getMessage());
            return false;
        }
        finally
        {
            try
            {
                stmt.close();
                connection.close();
            }
            catch (SQLException e)
            {
                System.out.println("Erro ao desconectar" + e.getMessage());
            }
        }
    } 

    public boolean atualizarEquipamento(Equipamento equipamento) 
    {
    	System.out.println("Atualizar equipamento");
        connection = Conexao.getInstance().getConnection();
        System.out.println("Conectado e pronto para atualizar");
        Statement stmt = null;

        try {
            stmt = connection.createStatement();

            String sql = "UPDATE aparelhos SET nome='" + equipamento.getNome() 
                         + "', quantidade=" + equipamento.getQuantidade() 
                         + ", estado='" + equipamento.getEstado() 
                         + "' WHERE codigo=" + equipamento.getCodigo() + ";";
            System.out.println("SQL: " + sql);
            stmt.executeUpdate(sql);

            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        } finally {
            try {
                stmt.close();
                connection.close();
            } catch (SQLException e) {
                System.out.println("Erro ao desconectar" + e.getMessage());
            }
        }
    } 

    
    public void relatorioEquipamento(Equipamento equipamento, DefaultTableModel modelo1)      
    {
       connection = Conexao.getInstance().getConnection();
       System.out.println("Conectado!! Preparando a exclus�o");
       Statement stmt = null;
         
  	        
  	   try
  	   {  		    
  		   stmt = connection.createStatement();
           ResultSet res = stmt.executeQuery("SELECT * FROM aparelhos ORDER BY codigo");
         
  		   while(res.next())
  		   {
  			   equipamento.setCodigo(res.getInt("codigo"));
  			   equipamento.setNome(res.getString("nome"));	
  			   equipamento.setQuantidade(res.getInt("quantidade"));
			   equipamento.setEstado(res.getString("estado"));
			  			   
  			   modelo1.addRow(new Object[] {equipamento.getCodigo(), equipamento.getNome(),
  					   equipamento.getQuantidade(),equipamento.getEstado()});		    
	   
  		   }
  	   }
  	   catch(SQLException ex)
  	   {
  		   System.out.println("Erro SQL: " + ex.getMessage());
  	   }
  	   finally 
       {
         try
         {
             stmt.close();
             connection.close();
         }
         catch (SQLException e)
         {
             System.out.println("Erro ao desconectar" + e.getMessage());
         }
     }
  	
    	
    }
    
    
    
    public boolean excluirEquipamento(int idEquipamento)
    {
        System.out.println("Excluir cosmetico");
        connection = Conexao.getInstance().getConnection();
        System.out.println("Conectado!! Preparando a exclus�o");
        Statement stmt = null;
        
        try
        {
            stmt = connection.createStatement();

            String sql = "DELETE FROM aparelhos WHERE codigo="+idEquipamento+";";
            System.out.println("SQL: " + sql);
            stmt.executeUpdate(sql);
            
            return true;
        } 
        catch (SQLException e)
        {
            System.out.println(e.getMessage());
            return false;
        }
        finally 
        {
            try
            {
                stmt.close();
                connection.close();
            }
            catch (SQLException e)
            {
                System.out.println("Erro ao desconectar" + e.getMessage());
            }
        }
    }
    
    public Equipamento obterEquipamentoPorId(int idEquipamento) {
    	connection = Conexao.getInstance().getConnection();
        System.out.println("Conectado!! Preparando para obter o equipamento por ID");
        Statement stmt = null;
        Equipamento equipamento = null;

        try {
            stmt = connection.createStatement();
            String sql = "SELECT * FROM aparelhos WHERE codigo = " + idEquipamento;
            ResultSet res = stmt.executeQuery(sql);

            if (res.next()) {
                equipamento = new Equipamento();
                equipamento.setCodigo(res.getInt("codigo"));
                equipamento.setNome(res.getString("nome"));
                equipamento.setQuantidade(res.getInt("quantidade"));
                equipamento.setEstado(res.getString("estado"));

            }
        } catch (SQLException ex) {
            System.out.println("Erro SQL: " + ex.getMessage());
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
                connection.close();
            } catch (SQLException e) {
                System.out.println("Erro ao desconectar" + e.getMessage());
            }
        }

        return equipamento;
    }
}
