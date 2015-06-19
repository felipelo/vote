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

	public Morador findByHash(final int hash) {
		Morador morador = new Morador();
		Query query = em.createQuery("SELECT m FROM Morador m WHERE m.hash = :hash");
		query.setParameter("hash", hash);

		List<Morador> result = query.getResultList();
		if (result.size() > 0) {
			morador = result.get(0);
		}

		return morador;
	}
	
}