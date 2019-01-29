/*
 * Aparajita Sahay
* Stateless session beans.
* Perform CRUD operation in datababe when user request to search wine catalog in database.
* No intermediate information is stored in a session therefore used @stateless session beans.
* USed entity manager to access database.
 */
package com.example;

import java.io.PrintWriter;
import static java.lang.System.out;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Aparajita Sahay
 * Uses stateless session beans. Do not have to store intermediate result in a session only performs CRUD 
 * operation in database using entity manager.
 */
@Stateless
public class WineFacade extends AbstractFacade<Wine> implements WineFacadeLocal {

    @PersistenceContext(unitName = "wine-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public WineFacade() {
        super(Wine.class);
    }

    @Override
    public List<Wine> findByCategory(String category) { 
        javax.persistence.Query q = getEntityManager().createNamedQuery("Wine.findByCategory");
        q.setParameter("category", category);
        return q.getResultList();
    }

    @Override
    public List<Wine> findById(String id) {
        javax.persistence.Query q = getEntityManager().createNamedQuery("Wine.findById");
        int idValue = Integer.parseInt(id);
        q.setParameter("id", idValue);
        return q.getResultList();
    }
}
