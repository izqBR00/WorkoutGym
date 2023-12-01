package Banco;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Dados.Cliente;

public class ClienteBD
{

    Connection connection = null;

    public boolean inserirCliente(Cliente cliente) 
    {
    	boolean status = true;
    	
        System.out.println("Inserir Cliente");
        
        connection = Conexao.getInstance().getConnection();
        System.out.println("Conectado e pronto para inserir");
        Statement stmt = null;
    
        try
        {
            stmt = connection.createStatement();

            String sql = "INSERT INTO clientes (nome,rua,numero,bairro,cidade,cpf,telefone,email,data,plano,status) "
               + "VALUES('"+cliente.getNome()+"','"+cliente.getRua()+"',"
               + " "+cliente.getNumero()+",'"+cliente.getBairro()+"','"+cliente.getCidade()+"',"
               + "'"+cliente.getCpf()+"','"+cliente.getTelefone()+"','"+cliente.getEmail()+"','"+cliente.getData()+"','"
               +cliente.getPlano()+"','"+cliente.getStatus()+"');";
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

    public boolean atualizarCliente(Cliente cliente) 
    {
    	 System.out.println("Atualizar Cliente");
    	    connection = Conexao.getInstance().getConnection();
    	    System.out.println("Conectado e pronto para atualizar");
    	    Statement stmt = null;

    	    try {
    	        stmt = connection.createStatement();

    	        String sql = "UPDATE clientes SET nome = '" + cliente.getNome() +
    	                "', rua = '" + cliente.getRua() +
    	                "', numero = " + cliente.getNumero() +
    	                ", bairro = '" + cliente.getBairro() +
    	                "', cidade = '" + cliente.getCidade() +
    	                "', cpf = '" + cliente.getCpf() +
    	                "', telefone = '" + cliente.getTelefone() +
    	                "', email = '" + cliente.getEmail() + "', data = '" + cliente.getData() +"', plano = '" + cliente.getPlano() + 
    	                "', status = '" + cliente.getStatus() +
    	                "' WHERE codigo = " + cliente.getCodigo() + ";";

    	        System.out.println("SQL: " + sql);
    	        int linhasAfetadas = stmt.executeUpdate(sql);

    	        if (linhasAfetadas > 0) {
    	            System.out.println("Cliente atualizado com sucesso.");
    	            return true;
    	        } else {
    	            System.out.println("Nenhuma linha afetada. Cliente não encontrado ou não modificado.");
    	            return false;
    	        }
    	    } catch (SQLException e) {
    	        System.out.println("Erro ao atualizar cliente: " + e.getMessage());
    	        return false;
    	    } finally {
    	        try {
    	            stmt.close();
    	            connection.close();
    	        } catch (SQLException e) {
    	            System.out.println("Erro ao desconectar: " + e.getMessage());
    	        }
    	    }
    } 

    
    public void relatorioCliente(Cliente cliente, DefaultTableModel modelo)      
    {
       connection = Conexao.getInstance().getConnection();
       System.out.println("Conectado!! Preparando a exclus�o");
       Statement stmt = null;
         
  	        
  	   try
  	   {  		    
  		   stmt = connection.createStatement();
           ResultSet res = stmt.executeQuery("SELECT * FROM clientes ORDER BY codigo");
         
  		   while(res.next())
  		   {
  			   cliente.setCodigo(res.getInt("codigo"));
  			   cliente.setNome(res.getString("nome"));	
			   
  			   modelo.addRow(new Object[] {cliente.getCodigo(), cliente.getNome()});		    
	   
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
    
    
    
    public boolean excluirCliente(int idCliente)
    {
        System.out.println("Excluir Cliente");
        connection = Conexao.getInstance().getConnection();
        System.out.println("Conectado!! Preparando a exclus�o");
        Statement stmt = null;
        
        try
        {
            stmt = connection.createStatement();

            String sql = "DELETE FROM clientes WHERE codigo = " + idCliente + ";";
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
    
    public Cliente obterClientePorId(int id) {
    	connection = Conexao.getInstance().getConnection();
        System.out.println("Conectado!! Preparando para obter o cliente por ID");
        Statement stmt = null;
        Cliente cliente = null;

        try {
            stmt = connection.createStatement();
            String sql = "SELECT * FROM clientes WHERE codigo = " + id;
            ResultSet res = stmt.executeQuery(sql);

            if (res.next()) {
                cliente = new Cliente();
                cliente.setCodigo(res.getInt("codigo"));
                cliente.setNome(res.getString("nome"));
                cliente.setRua(res.getString("rua"));
                cliente.setNumero(res.getInt("numero"));
                cliente.setBairro(res.getString("bairro"));
                cliente.setCidade(res.getString("cidade"));
                cliente.setCpf(res.getString("cpf"));
                cliente.setTelefone(res.getString("telefone"));
                cliente.setEmail(res.getString("email"));
                cliente.setData(res.getString("data"));
                cliente.setPlano(res.getString("plano"));
                cliente.setStatus(res.getString("status"));

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

        return cliente;
    }
    
    public boolean clienteRepetido(String nome) {
        connection = Conexao.getInstance().getConnection();
        System.out.println("Conectado!! Preparando para verificar existência do usuário por nome");
        PreparedStatement stmt = null;

        try {
            String sql = "SELECT COUNT(*) FROM clientes WHERE nome = ?";
            stmt = connection.prepareStatement(sql);
            stmt.setString(1, nome);

            ResultSet resultSet = stmt.executeQuery();

            if (resultSet.next()) {
                int count = resultSet.getInt(1);
                return count > 0;
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

        return false;
    }
}
