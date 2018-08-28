package br.caelum.financas.dao;

import java.util.List;

import javax.persistence.EntityManager;

import br.caelum.financas.modelo.Movimentacao;

public class MovimentacaoDao {
	private EntityManager em;

	public MovimentacaoDao(EntityManager em) {
		this.em = em;
	}

	public Movimentacao busca(Integer id) {
		return em.find(Movimentacao.class, id);
	}

	public List<Movimentacao> lista() {
		return em.createQuery("select m from Movimentacao m",
				Movimentacao.class).getResultList();
	}

	public void salva(Movimentacao movimentacao) {
		em.persist(movimentacao);
	}

	public void remove(Movimentacao movimentacao) {
		em.remove(movimentacao);
	}
}
