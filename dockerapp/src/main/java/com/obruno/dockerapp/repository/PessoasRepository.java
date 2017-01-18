package com.obruno.dockerapp.repository;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import com.obruno.dockerapp.model.Pessoa;

public class PessoasRepository implements Serializable {
	private static final long serialVersionUID = 1L;
	private EntityManager manager;
	
	@Inject
	public PessoasRepository(EntityManager manager) {
		this.manager = manager;
	}
	
	public Pessoa porId(Long id) {
		return manager.find(Pessoa.class, id);
	}

	public List<Pessoa> todas() {
		TypedQuery<Pessoa> query = manager.createQuery(
				"from Pessoa", Pessoa.class);
		return query.getResultList();
	}
	
}
