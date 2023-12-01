package Dados;

public class Cosmetico
{
    private int codigo;
    private String descricao;
    private String dataFabricacao;
    private String dataValidade;
    private String fabricante;
    private double valor;
    private int quantidade;
    
	public int getCodigo() 
	{
		return codigo;
	}
	public void setCodigo(int codigo) 
	{
		this.codigo = codigo;
	}
	public String getDescricao() 
	{
		return descricao;
	}
	public void setDescricao(String descricao) 
	{
		this.descricao = descricao;
	}
	public String getDataFabricacao() 
	{
		return dataFabricacao;
	}
	public void setDataFabricacao(String dataFabricacao) 
	{
		this.dataFabricacao = dataFabricacao;
	}
	public String getDataValidade()
	{
		return dataValidade;
	}
	public void setDataValidade(String dataValidade) 
	{
		this.dataValidade = dataValidade;
	}
	public String getFabricante() 
	{
		return fabricante;
	}
	public void setFabricante(String fabricante)
	{
		this.fabricante = fabricante;
	}
	public double getValor() 
	{
		return valor;
	}
	public void setValor(double valor) 
	{
		this.valor = valor;
	}
	public int getQuantidade() 
	{
		return quantidade;
	}
	public void setQuantidade(int quantidade) 
	{
		this.quantidade = quantidade;
	}   
    
}
