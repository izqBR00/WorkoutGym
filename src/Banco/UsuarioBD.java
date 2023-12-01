package Banco;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Dados.Equipamento;
import Dados.Usuario;
import Dados.Login;

public class UsuarioBD
{
	
	private Login login;
    Connection connection = null;

    public boolean inserirUsuario(Usuario usuario) 
    {
    	boolean status = true;
    	
        System.out.println("Inserir fornecedor");
        
        connection = Conexao.getInstance().getConnection();
        System.out.println("Conectado e pronto para inserir");
        Statement stmt = null;
    
        try
        {
            stmt = connection.createStatement();

            String sql = "INSERT INTO usuarios (nome,senha,nivel,email,telefone,cpf) "
               + "VALUES('"+usuario.getNome()+"','"+usuario.getSenha()+"','"+usuario.getNivel()+"','"+usuario.getEmail()+"','"+usuario.getTelefone()+"','"+usuario.getCpf()+"');";
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

    public boolean atualizarUsuario(Usuario usuario) 
    {
        System.out.println("Atualizar usuario");
        connection = Conexao.getInstance().getConnection();
        System.out.println("Conectado e pronto para atualizar");
        Statement stmt = null;
    
        try
        {
            stmt = connection.createStatement();

            String sql = "UPDATE usuarios SET nome = '" + usuario.getNome() 
            + "', senha = '" + usuario.getSenha() 
            + "', nivel = '" + usuario.getNivel() 
            + "', email = '" + usuario.getEmail()
            + "', telefone = '" + usuario.getTelefone()
            + "', cpf = '" + usuario.getCpf() + "' WHERE codigo=" + usuario.getCodigo() + ";";
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

    
    public void relatorioUsuario(Usuario usuario, DefaultTableModel modelo2)      
    {
       connection = Conexao.getInstance().getConnection();
       System.out.println("Conectado!! Preparando a exclus�o");
       Statement stmt = null;
         
  	        
  	   try
  	   {  		    
  		   stmt = connection.createStatement();
           ResultSet res = stmt.executeQuery("SELECT * FROM usuarios ORDER BY codigo");
         
  		   while(res.next())
  		   {
  			   usuario.setCodigo(res.getInt("codigo"));
  			   usuario.setNome(res.getString("nome"));	
			   
			   
			   
  			   modelo2.addRow(new Object[] {usuario.getCodigo(), usuario.getNome()});		    
	   
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
    
    
    
    public boolean excluirUsuario(int idUsuario)
    {
        System.out.println("Excluir fornecedor");
        connection = Conexao.getInstance().getConnection();
        System.out.println("Conectado!! Preparando a exclus�o");
        Statement stmt = null;
        
        try
        {
            stmt = connection.createStatement();

            String sql = "DELETE FROM usuarios WHERE codigo = " + idUsuario+";";
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
    
    public Usuario obterUsuarioPorId(int idUsuario) {
    	connection = Conexao.getInstance().getConnection();
        System.out.println("Conectado!! Preparando para obter o usuario por ID");
        Statement stmt = null;
        Usuario usuario = null;

        try {
            stmt = connection.createStatement();
            String sql = "SELECT * FROM usuarios WHERE codigo = " + idUsuario;
            ResultSet res = stmt.executeQuery(sql);

            if (res.next()) {
                usuario = new Usuario();
                usuario.setCodigo(res.getInt("codigo"));
                usuario.setNome(res.getString("nome"));
                usuario.setSenha(res.getString("senha"));
                usuario.setNivel(res.getInt("nivel"));
                usuario.setEmail(res.getString("email"));
                usuario.setTelefone(res.getString("telefone"));
                usuario.setCpf(res.getString("cpf"));

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

        return usuario;
    }
    
    public boolean atualizarUsuario2(Usuario usuario) 
    {	
    	login = new Login();
        System.out.println("Atualizar usuario");
        connection = Conexao.getInstance().getConnection();
        System.out.println("Conectado e pronto para atualizar");
        Statement stmt = null;
    
        try
        {
            stmt = connection.createStatement();

            String sql = "UPDATE usuarios SET nome = '" + usuario.getNome() 
            + "', senha = '" + usuario.getSenha() 
            + "', nivel = '" + usuario.getNivel() 
            + "', email = '" + usuario.getEmail()
            + "', telefone = '" + usuario.getTelefone()
            + "', cpf = '" + usuario.getCpf() + "' WHERE codigo=" + login.getCodigo() + ";";
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
    
    public boolean usuarioRepetido(String nome) {
        connection = Conexao.getInstance().getConnection();
        System.out.println("Conectado!! Preparando para verificar existência do usuário por nome");
        PreparedStatement stmt = null;

        try {
            String sql = "SELECT COUNT(*) FROM usuarios WHERE nome = ?";
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
