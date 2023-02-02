/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.memocicio.db;

import br.com.memocicio.db.controller.CardJpaController;
import br.com.memocicio.db.controller.ConjuntoJpaController;
import br.com.memocicio.db.controller.RevisaoJpaController;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author GanGss
 */
public abstract class DBFactory {

    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("StartMemoPU");
    private static CardJpaController cardjpa;
    private static ConjuntoJpaController conjuntojpa;
    private static RevisaoJpaController revisaojpa;

    public static CardJpaController getCardsController() {
        if (cardjpa == null) {
            cardjpa = new CardJpaController(emf);
        }
        return cardjpa;
    }

    public static ConjuntoJpaController getConjuntoController() {
        if (conjuntojpa == null) {
            conjuntojpa = new ConjuntoJpaController(emf);
        }
        return conjuntojpa;
    }

    public static RevisaoJpaController getRevisaoController() {
        if (revisaojpa == null) {
            revisaojpa = new RevisaoJpaController(emf);
        }
        return revisaojpa;
    }

}
