package ca.lorenz.vote.action;

import ca.lorenz.vote.controller.MoradorController;
import ca.lorenz.vote.controller.VoteController;
import ca.lorenz.vote.controller.PerguntaController;
import ca.lorenz.vote.controller.PropostaController;
import ca.lorenz.vote.model.Morador;
import ca.lorenz.vote.model.Pergunta;
import ca.lorenz.vote.model.Proposta;
import ca.lorenz.vote.model.Voto;
import java.util.Date;
import javax.persistence.EntityManager;

public class VoteAction {

	private EntityManager em;
	private Voto voto;
	private int hash;
	private Pergunta pergunta;
	//private List<Pergunta> perguntas;

	public String vote() {
		VoteController serv = new VoteController(em);
		MoradorController mServ = new MoradorController(em);

		//validar voto
		Morador morador = mServ.findByHash(hash);

		//validar se morador pertence a proposta
		Integer idMorador = morador.getId();
		Integer idProposta = morador.getProposta().getId();
		if ( !serv.existsPropostaByMorador(idProposta, idMorador) ) {
			return "error";
		}

		//validar se o já foi realizado.
		if ( serv.existsVoto(idProposta, idMorador) ) {
			return "error";
		}

		try {
			em.getTransaction().begin();

			voto.setMorador(morador);
			voto.setProposta(morador.getProposta());
			voto.setData(new Date());
			
			serv.save(voto);

			em.getTransaction().commit();
		} catch (Exception ex) {
			ex.printStackTrace();
			em.getTransaction().rollback();
			return "error";
		}

		return "success";
	}

	public String perguntar() {
		MoradorController mServ = new MoradorController(em);

		Morador morador = mServ.findByHash(hash);

		pergunta.setMorador(morador);
		pergunta.setProposta(morador.getProposta());
		pergunta.setData(new Date());

		PerguntaController pServ = new PerguntaController(em);

		try {
			em.getTransaction().begin();
			
			pServ.save(pergunta);

			em.getTransaction().commit();
		} catch (Exception ex) {
			ex.printStackTrace();
			em.getTransaction().rollback();
			return "error";
		}

		return "success";
	}

	public void setEm(EntityManager em) {
		this.em = em;
	}

	public void setVoto(Voto voto) {
		this.voto = voto;
	}

	public Voto getVoto() {
		return this.voto;
	}

	public void setHash(int hash) {
		this.hash = hash;
	}

	public void setPergunta(Pergunta pergunta) {
		this.pergunta = pergunta;
	}

	public Pergunta getPergunta() {
		return this.pergunta;
	}
/*
	public void setPerguntas(List<Pergunta> perguntas) {
		this.perguntas = perguntas;
	}

	public List<Pergunta> getPerguntas() {
		return this.perguntas;
	}
*/
}