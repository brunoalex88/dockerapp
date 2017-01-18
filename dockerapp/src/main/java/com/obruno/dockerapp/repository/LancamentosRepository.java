package com.obruno.dockerapp.repository;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import com.obruno.dockerapp.model.Lancamento;

public class LancamentosRepository implements Serializable {
	private static final long serialVersionUID = 1L;
	private EntityManager manager;
	
	@Inject
	public LancamentosRepository(EntityManager manager) {
		this.manager = manager;
	}
	
	public List<Lancamento> todos() {
		TypedQuery<Lancamento> query = manager.createQuery(
				"from Lancamento", Lancamento.class);
		return query.getResultList();
	}
	
	public void adicionar(Lancamento lancamento) {
		this.manager.persist(lancamento);
	}
	
	public List<String> descricoesQueContem(String descricao) {
		TypedQuery<String> query = manager.createQuery(
				"select distinct descricao from Lancamento "
				+ "where upper(descricao) like upper(:descricao)",
				String.class);
		query.setParameter("descricao", "%" + descricao + "%");
		return query.getResultList();
	}
	
}
