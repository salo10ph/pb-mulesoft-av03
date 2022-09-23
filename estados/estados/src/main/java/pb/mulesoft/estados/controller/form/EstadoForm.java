package pb.mulesoft.estados.controller.form;

import java.math.BigDecimal;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import pb.mulesoft.estados.modelo.Estado;
import pb.mulesoft.estados.modelo.Regiao;
import pb.mulesoft.estados.repository.EstadoRepository;

public class EstadoForm {
	
	@NotBlank @Length(min = 2)
	private String nome;
	private Regiao regiao;
	@NotNull
	private Long populacao;
	@NotBlank @Length(min = 2)
	private String capital;
	@NotNull
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

	public Estado atualizar(Long id, EstadoRepository estadoRepository) {
			Estado estado = estadoRepository.getOne(id);
			
			estado.setNome(this.nome);
			estado.setRegiao(this.regiao);
			estado.setPopulacao(this.populacao);
			estado.setCapital(this.capital);
			estado.setArea(this.area);
			
		return estado;
	}

}
