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

import Dados.Cosmetico;


public class CosmeticoBD
{

    Connection connection = null;

    public boolean inserircosmetico(Cosmetico cosmetico) 
    {
        System.out.println("Inserir cosmetico");
        
        connection = Conexao.getInstance().getConnection();
        System.out.println("Conectado e pronto para inserir");
        Statement stmt = null;
    
        try
        {
            stmt = connection.createStatement();

            String sql = "INSERT INTO cosmeticos (descricao,dataFabricacao,dataValidade,fabricante,valor,quantidade) "
               + "VALUES('"+cosmetico.getDescricao()+"','"+cosmetico.getDataFabricacao()+"',"
               + "'"+cosmetico.getDataValidade()+"','"+cosmetico.getFabricante()+"',"
               + ""+cosmetico.getValor()+","+cosmetico.getQuantidade()+");";
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

    public boolean atualizarcosmetico(Cosmetico cosmetico) 
    {
        System.out.println("Atualizar cosmetico");
        connection = Conexao.getInstance().getConnection();
        System.out.println("Conectado e pronto para atualizar");
        Statement stmt = null;
    
        try
        {
            stmt = connection.createStatement();

            String sql = "UPDATE cosmetico SET codigo='"+cosmetico.getCodigo()+"' WHERE codigo="+cosmetico.getCodigo()+";";
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

    
    public void relatoriocosmetico(Cosmetico cosmetico, DefaultTableModel modelo)      
    {
       connection = Conexao.getInstance().getConnection();
       System.out.println("Conectado!! Preparando a exclus�o");
       Statement stmt = null;
         
  	        
  	   try
  	   {  		    
  		   stmt = connection.createStatement();
           ResultSet res = stmt.executeQuery("SELECT * FROM cosmeticos ORDER BY codigo");
         
  		   while(res.next())
  		   {
  			   cosmetico.setCodigo(res.getInt("codigo"));
  			   cosmetico.setDescricao(res.getString("descricao"));	
  			   cosmetico.setDataFabricacao(res.getString("dataFabricacao"));
			   cosmetico.setDataValidade(res.getString("dataValidade"));	
			   cosmetico.setFabricante(res.getString("fabricante"));
  			   cosmetico.setValor(res.getDouble("valor"));
  			   cosmetico.setQuantidade(res.getInt("quantidade"));	
			  			   
  			   modelo.addRow(new Object[] {cosmetico.getCodigo(), cosmetico.getDescricao(),
  					   cosmetico.getDataFabricacao(),cosmetico.getDataValidade(),
  					   cosmetico.getFabricante(), cosmetico.getValor(),
  					   cosmetico.getQuantidade()});		    
	   
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
    
    
    
    public boolean excluircosmetico(Cosmetico cosmetico)
    {
        System.out.println("Excluir cosmetico");
        connection = Conexao.getInstance().getConnection();
        System.out.println("Conectado!! Preparando a exclus�o");
        Statement stmt = null;
        
        try
        {
            stmt = connection.createStatement();

            String sql = "DELETE FROM cosmetico WHERE codigo="+cosmetico.getCodigo()+";";
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
