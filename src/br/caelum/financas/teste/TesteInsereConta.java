package br.caelum.financas.teste;

import javax.persistence.EntityManager;

import br.caelum.financas.dao.ContaDao;
import br.caelum.financas.modelo.Conta;
import br.caelum.financas.util.JPAUtil;

public class TesteInsereConta {
	public static void main(String[] args) {
		long inicio = System.nanoTime();
		
		JPAUtil jpaUtil = new JPAUtil();
		EntityManager em = null;
		
		try {
			em = jpaUtil.getControleFinancasEM();
			
			ContaDao contaDao = new ContaDao(em);
			
			em.getTransaction().begin();
			
			Conta conta = getContaMock();
			contaDao.salva(conta);
			
			em.getTransaction().commit();
			
			System.out.println("Conta gravada com sucesso!");
			System.out.println(conta);
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

	private static Conta getContaMock() {
		Conta conta = new Conta();
		conta.setTitular("Alex Alves");
		conta.setBanco("Banco do Brasil");
		conta.setNumero("123456-6");
		conta.setAgencia("0999");
		return conta;
	}
}
