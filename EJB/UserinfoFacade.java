/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Aparjita Sahay
 * Stateless session bean.
 * Do not store intermediate result in a client session. 
 * Used entity manager to interact with database to validate used login credential in database
 */
@Stateless
public class UserinfoFacade extends AbstractFacade<Userinfo> implements UserinfoFacadeLocal {

    @PersistenceContext(unitName = "wine-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UserinfoFacade() {
        super(Userinfo.class);
    }

    @Override
    public List<Userinfo> login(String username, String password) {
        javax.persistence.Query q = getEntityManager().createNamedQuery("Userinfo.login");
            q.setParameter("username", username);
            q.setParameter("password", password);
            return q.getResultList();
    }
    
    @Override
    public List<Userinfo> getById(int id) {
        javax.persistence.Query q = getEntityManager().createNamedQuery("Userinfo.findById");
            q.setParameter("id", id);
            return q.getResultList();
    }
    
}
