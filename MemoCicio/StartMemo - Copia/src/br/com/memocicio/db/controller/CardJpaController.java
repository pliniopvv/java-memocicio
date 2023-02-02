/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.memocicio.db.controller;

import br.com.memocicio.db.Card;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import br.com.memocicio.db.Conjunto;
import br.com.memocicio.db.controller.exceptions.NonexistentEntityException;
import br.com.memocicio.db.controller.exceptions.PreexistingEntityException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author GanGss
 */
public class CardJpaController implements Serializable {

    public CardJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Card card) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Conjunto conjuntoFk = card.getConjuntoFk();
            if (conjuntoFk != null) {
                conjuntoFk = em.getReference(conjuntoFk.getClass(), conjuntoFk.getId());
                card.setConjuntoFk(conjuntoFk);
            }
            em.persist(card);
            if (conjuntoFk != null) {
                conjuntoFk.getCardList().add(card);
                conjuntoFk = em.merge(conjuntoFk);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findCard(card.getId()) != null) {
                throw new PreexistingEntityException("Card " + card + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Card card) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Card persistentCard = em.find(Card.class, card.getId());
            Conjunto conjuntoFkOld = persistentCard.getConjuntoFk();
            Conjunto conjuntoFkNew = card.getConjuntoFk();
            if (conjuntoFkNew != null) {
                conjuntoFkNew = em.getReference(conjuntoFkNew.getClass(), conjuntoFkNew.getId());
                card.setConjuntoFk(conjuntoFkNew);
            }
            card = em.merge(card);
            if (conjuntoFkOld != null && !conjuntoFkOld.equals(conjuntoFkNew)) {
                conjuntoFkOld.getCardList().remove(card);
                conjuntoFkOld = em.merge(conjuntoFkOld);
            }
            if (conjuntoFkNew != null && !conjuntoFkNew.equals(conjuntoFkOld)) {
                conjuntoFkNew.getCardList().add(card);
                conjuntoFkNew = em.merge(conjuntoFkNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = card.getId();
                if (findCard(id) == null) {
                    throw new NonexistentEntityException("The card with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void editAll(List<Card> listaCard) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            for (Card card : listaCard) {
                Card persistentCard = em.find(Card.class, card.getId());
                Conjunto conjuntoFkOld = persistentCard.getConjuntoFk();
                Conjunto conjuntoFkNew = card.getConjuntoFk();
                if (conjuntoFkNew != null) {
                    conjuntoFkNew = em.getReference(conjuntoFkNew.getClass(), conjuntoFkNew.getId());
                    card.setConjuntoFk(conjuntoFkNew);
                }
                card = em.merge(card);
                if (conjuntoFkOld != null && !conjuntoFkOld.equals(conjuntoFkNew)) {
                    conjuntoFkOld.getCardList().remove(card);
                    conjuntoFkOld = em.merge(conjuntoFkOld);
                }
                if (conjuntoFkNew != null && !conjuntoFkNew.equals(conjuntoFkOld)) {
                    conjuntoFkNew.getCardList().add(card);
                    conjuntoFkNew = em.merge(conjuntoFkNew);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            ex.printStackTrace();
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
            Card card;
            try {
                card = em.getReference(Card.class, id);
                card.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The card with id " + id + " no longer exists.", enfe);
            }
            Conjunto conjuntoFk = card.getConjuntoFk();
            if (conjuntoFk != null) {
                conjuntoFk.getCardList().remove(card);
                conjuntoFk = em.merge(conjuntoFk);
            }
            em.remove(card);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Card> findCardEntities() {
        return findCardEntities(true, -1, -1);
    }

    public List<Card> findCardEntities(int maxResults, int firstResult) {
        return findCardEntities(false, maxResults, firstResult);
    }

    private List<Card> findCardEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Card.class));
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

    public Card findCard(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Card.class, id);
        } finally {
            em.close();
        }
    }

    public int getCardCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Card> rt = cq.from(Card.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
