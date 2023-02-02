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
import br.com.memocicio.db.Revisao;
import br.com.memocicio.db.controller.exceptions.IllegalOrphanException;
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
public class CardJpaController1 implements Serializable {

    public CardJpaController1(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Card card) throws PreexistingEntityException, Exception {
        if (card.getRevisaoList() == null) {
            card.setRevisaoList(new ArrayList<Revisao>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Conjunto conjuntoFk = card.getConjuntoFk();
            if (conjuntoFk != null) {
                conjuntoFk = em.getReference(conjuntoFk.getClass(), conjuntoFk.getId());
                card.setConjuntoFk(conjuntoFk);
            }
            List<Revisao> attachedRevisaoList = new ArrayList<Revisao>();
            for (Revisao revisaoListRevisaoToAttach : card.getRevisaoList()) {
                revisaoListRevisaoToAttach = em.getReference(revisaoListRevisaoToAttach.getClass(), revisaoListRevisaoToAttach.getId());
                attachedRevisaoList.add(revisaoListRevisaoToAttach);
            }
            card.setRevisaoList(attachedRevisaoList);
            em.persist(card);
            if (conjuntoFk != null) {
                conjuntoFk.getCardList().add(card);
                conjuntoFk = em.merge(conjuntoFk);
            }
            for (Revisao revisaoListRevisao : card.getRevisaoList()) {
                Card oldCardOfRevisaoListRevisao = revisaoListRevisao.getCard();
                revisaoListRevisao.setCard(card);
                revisaoListRevisao = em.merge(revisaoListRevisao);
                if (oldCardOfRevisaoListRevisao != null) {
                    oldCardOfRevisaoListRevisao.getRevisaoList().remove(revisaoListRevisao);
                    oldCardOfRevisaoListRevisao = em.merge(oldCardOfRevisaoListRevisao);
                }
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

    public void edit(Card card) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Card persistentCard = em.find(Card.class, card.getId());
            Conjunto conjuntoFkOld = persistentCard.getConjuntoFk();
            Conjunto conjuntoFkNew = card.getConjuntoFk();
            List<Revisao> revisaoListOld = persistentCard.getRevisaoList();
            List<Revisao> revisaoListNew = card.getRevisaoList();
            List<String> illegalOrphanMessages = null;
            for (Revisao revisaoListOldRevisao : revisaoListOld) {
                if (!revisaoListNew.contains(revisaoListOldRevisao)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Revisao " + revisaoListOldRevisao + " since its card field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (conjuntoFkNew != null) {
                conjuntoFkNew = em.getReference(conjuntoFkNew.getClass(), conjuntoFkNew.getId());
                card.setConjuntoFk(conjuntoFkNew);
            }
            List<Revisao> attachedRevisaoListNew = new ArrayList<Revisao>();
            for (Revisao revisaoListNewRevisaoToAttach : revisaoListNew) {
                revisaoListNewRevisaoToAttach = em.getReference(revisaoListNewRevisaoToAttach.getClass(), revisaoListNewRevisaoToAttach.getId());
                attachedRevisaoListNew.add(revisaoListNewRevisaoToAttach);
            }
            revisaoListNew = attachedRevisaoListNew;
            card.setRevisaoList(revisaoListNew);
            card = em.merge(card);
            if (conjuntoFkOld != null && !conjuntoFkOld.equals(conjuntoFkNew)) {
                conjuntoFkOld.getCardList().remove(card);
                conjuntoFkOld = em.merge(conjuntoFkOld);
            }
            if (conjuntoFkNew != null && !conjuntoFkNew.equals(conjuntoFkOld)) {
                conjuntoFkNew.getCardList().add(card);
                conjuntoFkNew = em.merge(conjuntoFkNew);
            }
            for (Revisao revisaoListNewRevisao : revisaoListNew) {
                if (!revisaoListOld.contains(revisaoListNewRevisao)) {
                    Card oldCardOfRevisaoListNewRevisao = revisaoListNewRevisao.getCard();
                    revisaoListNewRevisao.setCard(card);
                    revisaoListNewRevisao = em.merge(revisaoListNewRevisao);
                    if (oldCardOfRevisaoListNewRevisao != null && !oldCardOfRevisaoListNewRevisao.equals(card)) {
                        oldCardOfRevisaoListNewRevisao.getRevisaoList().remove(revisaoListNewRevisao);
                        oldCardOfRevisaoListNewRevisao = em.merge(oldCardOfRevisaoListNewRevisao);
                    }
                }
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

    public void destroy(Integer id) throws IllegalOrphanException, NonexistentEntityException {
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
            List<String> illegalOrphanMessages = null;
            List<Revisao> revisaoListOrphanCheck = card.getRevisaoList();
            for (Revisao revisaoListOrphanCheckRevisao : revisaoListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Card (" + card + ") cannot be destroyed since the Revisao " + revisaoListOrphanCheckRevisao + " in its revisaoList field has a non-nullable card field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
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
