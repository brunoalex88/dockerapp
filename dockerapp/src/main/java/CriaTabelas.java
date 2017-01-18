import java.math.BigDecimal;
import java.util.Calendar;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import com.obruno.dockerapp.model.Lancamento;
import com.obruno.dockerapp.model.Pessoa;
import com.obruno.dockerapp.model.TipoLancamento;
import com.obruno.dockerapp.util.JpaUtil;

public class CriaTabelas {

	public static void main(String[] args) {
		EntityManager manager = JpaUtil.getEntityManager();
		EntityTransaction trx = manager.getTransaction();
		trx.begin();

		Calendar dataVencimento1 = Calendar.getInstance();
		dataVencimento1.set(2013, 10, 1, 0, 0, 0);
		Calendar dataVencimento2 = Calendar.getInstance();
		dataVencimento2.set(2013, 12, 10, 0, 0, 0);
		
/*		Pessoa cliente = new Pessoa();
		cliente.setNome("Indústria de Alimentos");*/
		
		Pessoa fornecedor = new Pessoa();
		fornecedor.setNome("TecX Inc");

		Lancamento lancamento1 = new Lancamento();
		lancamento1.setDescricao("Móveis para escritório");
		lancamento1.setPessoa(fornecedor);
		lancamento1.setDataVencimento(dataVencimento1.getTime());
		lancamento1.setDataPagamento(dataVencimento2.getTime());
		lancamento1.setValor(new BigDecimal(36_000));
		lancamento1.setTipo(TipoLancamento.DESPESA);
		
		manager.persist(fornecedor);
		manager.persist(lancamento1);
		
		trx.commit();
		manager.close();
		
	}

}
