package pb.mulesoft.estados.controller.form;

import java.math.BigDecimal;

import pb.mulesoft.estados.modelo.Estado;
import pb.mulesoft.estados.modelo.Regiao;

public class EstadoForm {
	
	private String nome;
	private Regiao regiao;
	private Long populacao;
	private String capital;
	private BigDecimal area;
	
	public EstadoForm(String nome, Regiao regiao, Long populacao, String capital, BigDecimal area) {
		this.nome = nome;
		this.regiao = regiao;
		this.populacao = populacao;
		this.capital = capital;
		this.area = area;
	}
	
	public Estado converter() {
		return new Estado(nome, regiao, populacao, capital, area);
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Regiao getRegiao() {
		return regiao;
	}

	public void setRegiao(Regiao regiao) {
		this.regiao = regiao;
	}

	public Long getPopulacao() {
		return populacao;
	}

	public void setPopulacao(Long populacao) {
		this.populacao = populacao;
	}

	public String getCapital() {
		return capital;
	}

	public void setCapital(String capital) {
		this.capital = capital;
	}

	public BigDecimal getArea() {
		return area;
	}

	public void setArea(BigDecimal area) {
		this.area = area;
	}

}
