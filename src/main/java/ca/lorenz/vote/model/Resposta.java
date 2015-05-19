package ca.lorenz.vote.model;

import ca.lorenz.model.PersistenceUnit;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Resposta implements PersistenceUnit<Integer>, Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String resposta;
	@Temporal(value=TemporalType.TIMESTAMP)
	private Date data;
	@ManyToOne
	private Morador morador;
	@ManyToOne
	private Pergunta pergunta;

	public Resposta() {
		this(0, "", new Date(), new Morador(), new Pergunta());
	}

	public Resposta(String resposta, Morador morador, Pergunta pergunta) {
		this(0, resposta, new Date(), morador, pergunta);
	}

	public Resposta(String resposta, Date data, Morador morador, Pergunta pergunta) {
		this(0, resposta, data, morador, pergunta);
	}

	public Resposta(Integer id, String resposta, Date data, Morador morador, Pergunta pergunta) {
		this.id = id;
		this.resposta = resposta;
		this.pergunta = pergunta;
		this.data = data;
		this.morador = morador;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getResposta() {
		return this.resposta;
	}

	public void setResposta(String resposta) {
		this.resposta = resposta;
	}

	public Date getData() {
		return this.data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public Morador getMorador() {
		return this.morador;
	}

	public void setMorador(Morador morador) {
		this.morador = morador;
	}

	public Pergunta getPergunta() {
		return this.pergunta;
	}

	public void setPergunta(Pergunta pergunta) {
		this.pergunta = pergunta;
	}

	@Override
	public String toString() {
		return "Resposta [id=" + id + ", resposta=" + resposta + ", data=" + data + ", morador=" + morador.getNome() + ", pergunta=" + pergunta.getPergunta() + "]";
	}
}