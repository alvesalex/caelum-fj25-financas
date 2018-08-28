package br.caelum.financas.dao;

import java.util.List;

import javax.persistence.EntityManager;

import br.caelum.financas.modelo.Conta;

public class ContaDao {
	private EntityManager em;
	
	public ContaDao(EntityManager em) {
		this.em = em;
	}
	
	public Conta busca(Integer id) {
		return em.find(Conta.class, id);
	}
	
	public List<Conta> lista() {
		return em.createQuery("select c from Conta c", Conta.class).getResultList();
	}
	
	public void salva(Conta conta) {
		em.persist(conta);
	}
	
	public void remove(Conta conta) {
		em.remove(conta);
	}
}
