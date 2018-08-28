package br.caelum.financas.teste;

import javax.persistence.EntityManager;

import br.caelum.financas.dao.MovimentacaoDao;
import br.caelum.financas.modelo.Movimentacao;
import br.caelum.financas.util.JPAUtil;

public class TestaBuscaContaDaMovimentacao {
	public static void main(String[] args) {
		JPAUtil jpaUtil = new JPAUtil();

		EntityManager em = null;

		try {
			em = jpaUtil.getControleFinancasEM();

			MovimentacaoDao movimentacaoDao = new MovimentacaoDao(em);

			Movimentacao movimentacao = movimentacaoDao.busca(3);

			System.out.println(movimentacao);
		} finally {
			if (em != null) {
				em.close();
			}
			jpaUtil.closeControleFinancasEmf();
		}
	}
}
