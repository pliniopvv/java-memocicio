/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.memocicio.db.controller;

import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import br.com.memocicio.db.Card;
import br.com.memocicio.db.Revisao;
import br.com.memocicio.db.controller.exceptions.NonexistentEntityException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author GanGss
 */
public class RevisaoJpaController implements Serializable {

    public RevisaoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Revisao revisao) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Card card = revisao.getCard();
            if (card != null) {
                card = em.getReference(card.getClass(), card.getId());
                revisao.setCard(card);
            }
            em.persist(revisao);
            if (card != null) {
                card.getRevisaoList().add(revisao);
                card = em.merge(card);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Revisao revisao) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Revisao persistentRevisao = em.find(Revisao.class, revisao.getId());
            Card cardOld = persistentRevisao.getCard();
            Card cardNew = revisao.getCard();
            if (cardNew != null) {
                cardNew = em.getReference(cardNew.getClass(), cardNew.getId());
                revisao.setCard(cardNew);
            }
            revisao = em.merge(revisao);
            if (cardOld != null && !cardOld.equals(cardNew)) {
                cardOld.getRevisaoList().remove(revisao);
                cardOld = em.merge(cardOld);
            }
            if (cardNew != null && !cardNew.equals(cardOld)) {
                cardNew.getRevisaoList().add(revisao);
                cardNew = em.merge(cardNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = revisao.getId();
                if (findRevisao(id) == null) {
                    throw new NonexistentEntityException("The revisao with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Revisao revisao;
            try {
                revisao = em.getReference(Revisao.class, id);
                revisao.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The revisao with id " + id + " no longer exists.", enfe);
            }
            Card card = revisao.getCard();
            if (card != null) {
                card.getRevisaoList().remove(revisao);
                card = em.merge(card);
            }
            em.remove(revisao);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Revisao> findRevisaoEntities() {
        return findRevisaoEntities(true, -1, -1);
    }

    public List<Revisao> findRevisaoEntities(int maxResults, int firstResult) {
        return findRevisaoEntities(false, maxResults, firstResult);
    }

    private List<Revisao> findRevisaoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Revisao.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Revisao findRevisao(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Revisao.class, id);
        } finally {
            em.close();
        }
    }

    public int getRevisaoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Revisao> rt = cq.from(Revisao.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
