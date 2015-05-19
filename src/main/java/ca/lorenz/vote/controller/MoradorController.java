package ca.lorenz.vote.controller;

import ca.lorenz.controller.CommonService;
import ca.lorenz.vote.model.Morador;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

public class MoradorController extends CommonService<Morador, Integer> {

	public MoradorController(EntityManager em) {
		super(em);
	}

	public List<Morador> findByProposta(final int id) {
		Query query = em.createQuery("SELECT m FROM Morador m WHERE m.proposta.id = :id");
		query.setParameter("id", id);

		return query.getResultList();
	}
	
}