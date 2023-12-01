package Banco;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.sql.PreparedStatement;

import Dados.Login;

public class AcessoBD
{

    Connection connection = null;

    public boolean verificaAcesso(Login login) {
    	
    	connection = Conexao.getInstance().getConnection();
        System.out.println("Conectado e verificando acesso");
        Statement stmt = null;

        boolean status = false;

        try {
            stmt = connection.createStatement();
            ResultSet res = stmt.executeQuery("SELECT * FROM usuarios");

            while (res.next()) {
                if (login.getNome().compareTo(res.getString("nome")) == 0 && login.getSenha().compareTo(res.getString("senha")) == 0) {
                	login.setNivel(res.getInt("nivel"));	
                    status = true;
                    break;
                }
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            status = false;
        } finally {
            try {
                stmt.close();
                connection.close();
            } catch (SQLException e) {
                System.out.println("Erro ao desconectar" + e.getMessage());
            }
        }

        return status;
    }
    
    public Login obterLoginPorNome(Login login) {
    	connection = Conexao.getInstance().getConnection();
        Statement stmt = null;

        try {
            stmt = connection.createStatement();
            ResultSet res = stmt.executeQuery("SELECT * FROM usuarios WHERE nome='" + login.getNome() + "' AND senha='" + login.getSenha() + "'");

            while (res.next()) {
            	login.setCodigo(res.getInt("codigo"));
            	login.setNome(res.getString("nome"));
            	login.setSenha(res.getString("senha"));
                login.setNivel(res.getInt("nivel"));
                login.setEmail(res.getString("email"));
                login.setTelefone(res.getString("telefone"));
                login.setCpf(res.getString("cpf"));
                
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            // ...
        }

        return login;
    }
    
}
