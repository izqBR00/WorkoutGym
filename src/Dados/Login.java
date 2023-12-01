package Dados;

public class Login
{	
	private int codigo;
    private String nome;
    private String senha;
    private int nivel;
    private String email;
    private String telefone;
    private String cpf;
    
   

    public Login() 
    {
        this.nome=null;
        this.senha=null;
          
    }

    public Login(String nome, String senha)
    {
        this.nome = nome;
        this.senha = senha;
         
    }
    
    public int getCodigo() {
    	return codigo;
    }
    
    public void setCodigo(int codigo) {
    	this.codigo = codigo;
    }

    public String getNome()
    {
        return nome;
    }

    public void setNome(String nome)
    {
        this.nome = nome;
    }

    public String getSenha()
    {
        return senha;
    }
    
    public void setSenha(String senha)
    {
        this.senha = senha;
    }
    
    public int getNivel() {
    	return nivel;
    }
    
    public void setNivel(int nivel) {
    	this.nivel = nivel;
    }
    
    public String getEmail() {
    	return email;
    }
    
    public void setEmail(String email) {
    	this.email = email;
    }
    
    public String getTelefone() {
    	return telefone;
    }
    
    public void setTelefone(String telefone) {
    	this.telefone = telefone;
    }
    
    public String getCpf() {
    	return cpf;
    }
    
    public void setCpf(String cpf) {
    	this.cpf = cpf;
    }
    
}
