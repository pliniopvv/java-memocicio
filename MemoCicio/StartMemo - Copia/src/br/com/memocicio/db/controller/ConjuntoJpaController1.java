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
import br.com.memocicio.db.Conjunto;
import br.com.memocicio.db.controller.exceptions.NonexistentEntityException;
import br.com.memocicio.db.controller.exceptions.PreexistingEntityException;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author GanGss
 */
public class ConjuntoJpaController1 implements Serializable {

    public ConjuntoJpaController1(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Conjunto conjunto) throws PreexistingEntityException, Exception {
        if (conjunto.getCardList() == null) {
            conjunto.setCardList(new ArrayList<Card>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Card> attachedCardList = new ArrayList<Card>();
            for (Card cardListCardToAttach : conjunto.getCardList()) {
                cardListCardToAttach = em.getReference(cardListCardToAttach.getClass(), cardListCardToAttach.getId());
                attachedCardList.add(cardListCardToAttach);
            }
            conjunto.setCardList(attachedCardList);
            em.persist(conjunto);
            for (Card cardListCard : conjunto.getCardList()) {
                Conjunto oldConjuntoFkOfCardListCard = cardListCard.getConjuntoFk();
                cardListCard.setConjuntoFk(conjunto);
                cardListCard = em.merge(cardListCard);
                if (oldConjuntoFkOfCardListCard != null) {
                    oldConjuntoFkOfCardListCard.getCardList().remove(cardListCard);
                    oldConjuntoFkOfCardListCard = em.merge(oldConjuntoFkOfCardListCard);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findConjunto(conjunto.getId()) != null) {
                throw new PreexistingEntityException("Conjunto " + conjunto + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Conjunto conjunto) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Conjunto persistentConjunto = em.find(Conjunto.class, conjunto.getId());
            List<Card> cardListOld = persistentConjunto.getCardList();
            List<Card> cardListNew = conjunto.getCardList();
            List<Card> attachedCardListNew = new ArrayList<Card>();
            for (Card cardListNewCardToAttach : cardListNew) {
                cardListNewCardToAttach = em.getReference(cardListNewCardToAttach.getClass(), cardListNewCardToAttach.getId());
                attachedCardListNew.add(cardListNewCardToAttach);
            }
            cardListNew = attachedCardListNew;
            conjunto.setCardList(cardListNew);
            conjunto = em.merge(conjunto);
            for (Card cardListOldCard : cardListOld) {
                if (!cardListNew.contains(cardListOldCard)) {
                    cardListOldCard.setConjuntoFk(null);
                    cardListOldCard = em.merge(cardListOldCard);
                }
            }
            for (Card cardListNewCard : cardListNew) {
                if (!cardListOld.contains(cardListNewCard)) {
                    Conjunto oldConjuntoFkOfCardListNewCard = cardListNewCard.getConjuntoFk();
                    cardListNewCard.setConjuntoFk(conjunto);
                    cardListNewCard = em.merge(cardListNewCard);
                    if (oldConjuntoFkOfCardListNewCard != null && !oldConjuntoFkOfCardListNewCard.equals(conjunto)) {
                        oldConjuntoFkOfCardListNewCard.getCardList().remove(cardListNewCard);
                        oldConjuntoFkOfCardListNewCard = em.merge(oldConjuntoFkOfCardListNewCard);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = conjunto.getId();
                if (findConjunto(id) == null) {
                    throw new NonexistentEntityException("The conjunto with id " + id + " no longer exists.");
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
            Conjunto conjunto;
            try {
                conjunto = em.getReference(Conjunto.class, id);
                conjunto.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The conjunto with id " + id + " no longer exists.", enfe);
            }
            List<Card> cardList = conjunto.getCardList();
            for (Card cardListCard : cardList) {
                cardListCard.setConjuntoFk(null);
                cardListCard = em.merge(cardListCard);
            }
            em.remove(conjunto);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Conjunto> findConjuntoEntities() {
        return findConjuntoEntities(true, -1, -1);
    }

    public List<Conjunto> findConjuntoEntities(int maxResults, int firstResult) {
        return findConjuntoEntities(false, maxResults, firstResult);
    }

    private List<Conjunto> findConjuntoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Conjunto.class));
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

    public Conjunto findConjunto(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Conjunto.class, id);
        } finally {
            em.close();
        }
    }

    public int getConjuntoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Conjunto> rt = cq.from(Conjunto.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
