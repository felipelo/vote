package ca.lorenz.vote.controller;

import ca.lorenz.controller.CommonService;
import ca.lorenz.vote.model.Pergunta;
import ca.lorenz.vote.model.Proposta;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

public class PerguntaController extends CommonService<Pergunta, Integer> {

	public PerguntaController(EntityManager em) {
		super(em);
	}

	public List<Pergunta> findByProposta(Proposta proposta) {
		Query query = em.createQuery(
			"SELECT p FROM Pergunta p WHERE p.proposta.id = :propostaId ORDER BY p.data ASC");
		query.setParameter("propostaId", proposta.getId());

		return query.getResultList();
	}

}