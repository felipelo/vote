package ca.lorenz.vote.model;

import ca.lorenz.model.PersistenceUnit;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Pergunta implements PersistenceUnit<Integer>, Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String pergunta;
	@Temporal(value=TemporalType.TIMESTAMP)
	private Date data;
	@ManyToOne
	private Morador morador;
	@ManyToOne
	private Proposta proposta;
	@OneToMany(mappedBy = "pergunta", fetch = FetchType.EAGER)
	@OrderBy("data")
	private List<Resposta> respostas;

	public Pergunta() {
		this(0, "", new Date(), new Morador(), new Proposta());
	}

	public Pergunta(String pergunta, Morador morador, Proposta proposta) {
		this(0, pergunta, new Date(), morador, proposta);
	}

	public Pergunta(String pergunta, Date data, Morador morador, Proposta proposta) {
		this(0, pergunta, data, morador, proposta);
	}

	public Pergunta(Integer id, String pergunta, Date data, Morador morador, Proposta proposta) {
		this.id = id;
		this.pergunta = pergunta;
		this.data = data;
		this.morador = morador;
		this.proposta = proposta;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPergunta() {
		return this.pergunta;
	}

	public void setPergunta(String pergunta) {
		this.pergunta = pergunta;
	} 

	public Date getData() {
		return this.data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public Proposta getProposta() {
		return this.proposta;
	}

	public void setProposta(Proposta proposta) {
		this.proposta = proposta;
	}

	public Morador getMorador() {
		return this.morador;
	}

	public void setMorador(Morador morador) {
		this.morador = morador;
	}

	public List<Resposta> getRespostas() {
		return this.respostas;
	}

	public void setRespostas(List<Resposta> respostas) {
		this.respostas = respostas;
	}

	@Override
	public String toString() {
		return "Pergunta [id=" + id + ", pergunta=" + pergunta + ", data=" + data + ", morador=" + morador.getNome() + ", proposta=" + proposta.getProposta() + "]";
	}
}