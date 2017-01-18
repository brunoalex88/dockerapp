package com.obruno.dockerapp.controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.event.ValueChangeEvent;
import javax.inject.Inject;
import javax.inject.Named;

import com.obruno.dockerapp.model.Lancamento;
import com.obruno.dockerapp.model.Pessoa;
import com.obruno.dockerapp.model.TipoLancamento;
import com.obruno.dockerapp.repository.LancamentosRepository;
import com.obruno.dockerapp.repository.PessoasRepository;
import com.obruno.dockerapp.service.CadastroLancamentos;
import com.obruno.dockerapp.service.NegocioException;

@ViewScoped
@Named
@javax.faces.view.ViewScoped
public class CadastroLancamentoBean implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Inject
	private CadastroLancamentos cadastro;
	
	@Inject
	private PessoasRepository pessoas;
	
	@Inject LancamentosRepository lancamentos;
	
	private Lancamento lancamento = new Lancamento();
	private List<Pessoa> todasPessoas;

	public void preparaCadastro() {
			this.todasPessoas = pessoas.todas();
	}

	public void salvar() {
		FacesContext context = FacesContext.getCurrentInstance();
		
		try {
			cadastro.salvar(this.lancamento);
			this.lancamento = new Lancamento();
			context.addMessage(null, new FacesMessage("Lançamento salvo com sucesso!"));
		} catch (NegocioException e) {
			FacesMessage mensagem = new FacesMessage(e.getMessage());
			mensagem.setSeverity(FacesMessage.SEVERITY_ERROR);
			context.addMessage(null, mensagem);
		}
	}

	public List<String> pesquisarDescricoes(String descricao) {
		return this.lancamentos.descricoesQueContem(descricao);
	}
	
	public void descricaoModificada(ValueChangeEvent event) {
		System.out.println("Valor antigo: " + event.getOldValue());
		System.out.println("Valor novo: " + event.getNewValue());
		//FacesContext.getCurrentInstance().renderResponse();
	}

	public void dataVencimentoAlterada(AjaxBehaviorEvent event) {
		if (this.lancamento.getDataPagamento() == null)
			this.lancamento.setDataPagamento(this.lancamento.getDataVencimento());
	}
	
	public List<Pessoa> getTodasPessoas() {
		return this.todasPessoas;
	}

	public TipoLancamento[] getTiposLancamentos() {
		return TipoLancamento.values();
	}

	public Lancamento getLancamento() {
		return lancamento;
	}

	public void setLancamento(Lancamento lancamento) {
		this.lancamento = lancamento;
	}
}
