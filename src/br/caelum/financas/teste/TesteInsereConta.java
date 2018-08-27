package br.caelum.financas.teste;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import br.caelum.financas.modelo.Conta;

public class TesteInsereConta {
	public static void main(String[] args) {
		long inicio = System.nanoTime();
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("controlefinancas");
		
		EntityManager em = null;
		
		Conta conta = new Conta();
		conta.setTitular("José Ricardo");
		conta.setBanco("Banco do Brasil");
		conta.setNumero("123456-6");
		conta.setAgencia("0999");
		
		try {
			em = emf.createEntityManager();
			
			em.getTransaction().begin();
			
			em.persist(conta);
			
			em.getTransaction().commit();
		} catch (Exception ex) {
			ex.printStackTrace();
			
			if (em != null && em.getTransaction() != null) {
				em.getTransaction().rollback();
			}
		} finally {
			if (em != null) {
				em.close();
			}
			if (emf != null) {
				emf.close();
			}
		}
		
		long fim = System.nanoTime();
		
		System.out.println("Executado em: " + (fim - inicio) + "ns");
	}
}
