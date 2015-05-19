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
public class Voto implements PersistenceUnit<Integer>, Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@ManyToOne
	private Proposta proposta;
	@ManyToOne
	private Morador morador;
	private boolean voto;
	@Temporal(value=TemporalType.TIMESTAMP)
	private Date data;

	public Voto() {
		this(0, new Proposta(), new Morador());
	}

	public Voto( Proposta proposta, Morador morador) {
		this(0, proposta, morador);
	}

	public Voto(Integer id, Proposta proposta, Morador morador) {
		this.id = id;
		this.proposta = proposta;
		this.morador = morador;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public boolean isVoto() {
		return this.voto;
	}

	public void setVoto(boolean voto) {
		this.voto = voto;
	}

	public Date getData() {
		return this.data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "Voto [id=" + id + ", proposta=" + proposta.getProposta() + ", morador=" + morador.getNome() + ", voto=" + voto + ", data=" + data + "]";
	}
}