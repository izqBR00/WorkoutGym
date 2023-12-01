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

import Dados.Fornecedor;

public class FornecedorBD
{

    Connection connection = null;

    public boolean inserirFornecedor(Fornecedor fornecedor) 
    {
    	boolean status = true;
    	
        System.out.println("Inserir fornecedor");
        
        connection = Conexao.getInstance().getConnection();
        System.out.println("Conectado e pronto para inserir");
        Statement stmt = null;
    
        try
        {
            stmt = connection.createStatement();

            String sql = "INSERT INTO fornecedores (nome,endereco,numero,bairro,cidade,cnpj,telefone) "
               + "VALUES('"+fornecedor.getNome()+"','"+fornecedor.getEndereco()+"',"
               + " "+fornecedor.getNumero()+",'"+fornecedor.getBairro()+"','"+fornecedor.getCidade()+"',"
               + "'"+fornecedor.getCnpj()+"','"+fornecedor.getTelefone()+"');";
            System.out.println("SQL: " + sql);
            stmt.executeUpdate(sql);
            
            status = true;
            
        } 
        catch (SQLException e)
        {
            System.out.println(e.getMessage());
            status = false;
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
                status = false;
            }
        }
        
        return status;
    } 

    public boolean atualizarAluno(Fornecedor fornecedor) 
    {
        System.out.println("Atualizar fornecedor");
        connection = Conexao.getInstance().getConnection();
        System.out.println("Conectado e pronto para atualizar");
        Statement stmt = null;
    
        try
        {
            stmt = connection.createStatement();

            String sql = "UPDATE aluno SET nomealuno = '" + fornecedor.getNome() + "' WHERE codaluno = " + fornecedor.getCodigo() + ";";
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

    
    public void relatorioFornecedor(Fornecedor fornecedor, DefaultTableModel modelo)      
    {
       connection = Conexao.getInstance().getConnection();
       System.out.println("Conectado!! Preparando a exclus�o");
       Statement stmt = null;
         
  	        
  	   try
  	   {  		    
  		   stmt = connection.createStatement();
           ResultSet res = stmt.executeQuery("SELECT * FROM fornecedores ORDER BY codigo");
         
  		   while(res.next())
  		   {
  			   fornecedor.setCodigo(res.getInt("codigo"));
  			   fornecedor.setNome(res.getString("nome"));	
  			   fornecedor.setEndereco(res.getString("endereco"));
			   fornecedor.setNumero(res.getInt("numero"));	
			   fornecedor.setBairro(res.getString("bairro"));
  			   fornecedor.setCidade(res.getString("cidade"));
  			   fornecedor.setCnpj(res.getString("cnpj"));	
			   fornecedor.setTelefone(res.getString("telefone"));
			   
			   
  			   modelo.addRow(new Object[] {fornecedor.getCodigo(), fornecedor.getNome(),
  					   fornecedor.getEndereco(),fornecedor.getNumero(),fornecedor.getBairro(),
  					   fornecedor.getCidade(), fornecedor.getCnpj(),fornecedor.getTelefone(),
  					   });		    
	   
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
    
    
    
    public boolean excluirAluno(Fornecedor fornecedor)
    {
        System.out.println("Excluir fornecedor");
        connection = Conexao.getInstance().getConnection();
        System.out.println("Conectado!! Preparando a exclus�o");
        Statement stmt = null;
        
        try
        {
            stmt = connection.createStatement();

            String sql = "DELETE FROM fornecedores WHERE codigo = " + fornecedor.getCodigo() + ";";
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
