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

import Dados.Medicamento;

public class MedicamentoBD
{

    Connection connection = null;

    public boolean inserirMedicamento(Medicamento medicamento) 
    {
        System.out.println("Inserir Medicamento");
        
        connection = Conexao.getInstance().getConnection();
        System.out.println("Conectado e pronto para inserir");
        Statement stmt = null;
    
        try
        {
            stmt = connection.createStatement();

            String sql = "INSERT INTO medicamentos (descricao,dataFabricacao,dataValidade,fabricante,valor,quantidade) "
               + "VALUES('"+medicamento.getDescricao()+"','"+medicamento.getDataFabricacao()+"',"
               + "'"+medicamento.getDataValidade()+"','"+medicamento.getFabricante()+"',"
               + ""+medicamento.getValor()+","+medicamento.getQuantidade()+");";
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

    public boolean atualizarMedicamento(Medicamento medicamento) 
    {
        System.out.println("Atualizar medicamento");
        connection = Conexao.getInstance().getConnection();
        System.out.println("Conectado e pronto para atualizar");
        Statement stmt = null;
    
        try
        {
            stmt = connection.createStatement();

            String sql = "UPDATE medicamento SET codigo='"+medicamento.getCodigo()+"' WHERE codigo="+medicamento.getCodigo()+";";
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

    
    public void relatorioMedicamento(Medicamento medicamento, DefaultTableModel modelo)      
    {
       connection = Conexao.getInstance().getConnection();
       System.out.println("Conectado!! Preparando a exclus�o");
       Statement stmt = null;
         
  	        
  	   try
  	   {  		    
  		   stmt = connection.createStatement();
           ResultSet res = stmt.executeQuery("SELECT * FROM medicamentos ORDER BY codigo");
         
  		   while(res.next())
  		   {
  			   medicamento.setCodigo(res.getInt("codigo"));
  			   medicamento.setDescricao(res.getString("descricao"));	
  			   medicamento.setDataFabricacao(res.getString("dataFabricacao"));
			   medicamento.setDataValidade(res.getString("dataValidade"));	
			   medicamento.setFabricante(res.getString("fabricante"));
  			   medicamento.setValor(res.getDouble("valor"));
  			   medicamento.setQuantidade(res.getInt("quantidade"));	
			  			   
  			   modelo.addRow(new Object[] {medicamento.getCodigo(), medicamento.getDescricao(),
  					   medicamento.getDataFabricacao(),medicamento.getDataValidade(),
  					   medicamento.getFabricante(), medicamento.getValor(),
  					   medicamento.getQuantidade()});		    
	   
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
    
    
    
    public boolean excluirMedicamento(Medicamento medicamento)
    {
        System.out.println("Excluir medicamento");
        connection = Conexao.getInstance().getConnection();
        System.out.println("Conectado!! Preparando a exclus�o");
        Statement stmt = null;
        
        try
        {
            stmt = connection.createStatement();

            String sql = "DELETE FROM medicamento WHERE codigo="+medicamento.getCodigo()+";";
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
}
