package com.obruno.dockerapp.service;

import java.io.Serializable;
import java.util.Date;

import javax.inject.Inject;

import com.obruno.dockerapp.model.Lancamento;
import com.obruno.dockerapp.repository.LancamentosRepository;
import com.obruno.dockerapp.util.Transactional;

public class CadastroLancamentos implements Serializable {
	private static final long serialVersionUID = 1L;

	@Inject
	private LancamentosRepository lancamentos;
	
	@Transactional
	public void salvar(Lancamento lancamento) throws NegocioException {
		if (lancamento.getDataPagamento() != null &&
				lancamento.getDataPagamento().after(new Date())) {
			throw new NegocioException(
					"Data de pagamento não pode ser uma data futura.");
		}
		
		this.lancamentos.adicionar(lancamento);
	}
	
}
