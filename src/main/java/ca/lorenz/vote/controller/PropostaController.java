package ca.lorenz.vote.controller;

import ca.lorenz.controller.CommonService;
import ca.lorenz.vote.model.Proposta;
import ca.lorenz.vote.model.Morador;
import javax.persistence.EntityManager;

public class PropostaController extends CommonService<Proposta, Integer> {

	public PropostaController(EntityManager em) {
		super(em);
	}

	@Override
    public Proposta save(final Proposta proposta) throws Exception {
    	MoradorController serv = new MoradorController(em);
    	Morador morador = serv.findByID(proposta.getMorador().getId());
    	proposta.setMorador(morador);

    	return super.save(proposta);
    }

}