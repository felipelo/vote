package ca.lorenz.vote.controller;

import ca.lorenz.controller.CommonService;
import ca.lorenz.vote.model.Voto;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

public class VoteController extends CommonService<Voto, Integer> {

	public VoteController(EntityManager em) {
		super(em);
	}

	public boolean existsPropostaByMorador(final int idProposta, final int idMorador) {
		Query query = em.createQuery("SELECT m FROM Morador m WHERE m.id = :idMorador AND m.proposta.id = :idProposta");
		query.setParameter("idMorador", idMorador);
		query.setParameter("idProposta", idProposta);

		List result = query.getResultList();
		return result.size() > 0;
	}

	public boolean existsVoto(final int idProposta, final int idMorador) {
		Query query = em.createQuery("SELECT v FROM Voto v WHERE v.proposta.id = :idProposta AND v.morador.id = :idMorador");
		query.setParameter("idMorador", idMorador);
		query.setParameter("idProposta", idProposta);

		List result = query.getResultList();
		return result.size() > 0;
	}

}