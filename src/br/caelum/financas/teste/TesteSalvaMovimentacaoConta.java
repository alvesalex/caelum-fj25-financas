package br.caelum.financas.teste;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.EntityManager;

import br.caelum.financas.modelo.Conta;
import br.caelum.financas.modelo.Movimentacao;
import br.caelum.financas.modelo.TipoMovimentacao;
import br.caelum.financas.util.JPAUtil;

public class TesteSalvaMovimentacaoConta {
	public static void main(String[] args) {
		long inicio = System.nanoTime();
		
		JPAUtil jpaUtil = new JPAUtil();
		EntityManager em = null;
		
		try {
			em = jpaUtil.getControleFinancasEM();
			
			em.getTransaction().begin();
			
			Movimentacao movimentacao = getMovimentacaoMock();

			Conta conta = getContaMock();
			movimentacao.setConta(conta);
			
			em.persist(movimentacao);
			
			em.getTransaction().commit();
			
			System.out.println("Movimentação gravada com sucesso!");
			System.out.println(movimentacao);
		} catch (Exception ex) {
			ex.printStackTrace();
			
			if (em != null && em.getTransaction() != null) {
				em.getTransaction().rollback();
			}
		} finally {
			if (em != null) {
				em.close();
			}
			jpaUtil.closeControleFinancasEmf();
		}
		
		long fim = System.nanoTime();
		
		System.out.println("Executado em: " + (fim - inicio) + "ns");
	}

	private static Movimentacao getMovimentacaoMock() {
		Movimentacao movimentacao = new Movimentacao();
		movimentacao.setDescricao("conta de luz - abril 2010");
		movimentacao.setData(LocalDateTime.now());
		movimentacao.setValor(BigDecimal.valueOf(54));
		movimentacao.setTipoMovimentacao(TipoMovimentacao.SAIDA);
		return movimentacao;
	}

	private static Conta getContaMock() {
		Conta conta = new Conta();
		conta.setTitular("Maria");
		conta.setBanco("Banco Santander");
		conta.setNumero("99999-9");
		conta.setAgencia("999");
		return conta;
	}
}
