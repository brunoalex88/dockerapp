package com.obruno.dockerapp.controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.obruno.dockerapp.model.Lancamento;
import com.obruno.dockerapp.repository.LancamentosRepository;

@Named
@ViewScoped
public class ConsultaLancamentosBean implements Serializable {
	private static final long serialVersionUID = 1L;
	private List<Lancamento> lancamentos;

	@Inject
	private LancamentosRepository lancamentosRepository;
	
	public void consultar() {
		this.lancamentos = lancamentosRepository.todos();
	}

	public List<Lancamento> getLancamentos() {
		return lancamentos;
	}
}
