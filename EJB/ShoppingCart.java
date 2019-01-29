/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Aparajita Sahay
 * entity bean used entity manager to access database. 
 * Statefull bean since shopping cart needs to be stored through each session.
 */
@Stateful
public class ShoppingCart implements ShoppingCartLocal {

    @PersistenceContext(unitName = "wine-ejbPU")
    private EntityManager em;

    List<Wine> winesAdded;

    @PostConstruct
    private void init( ) {
        winesAdded = new ArrayList<>( );
    }
        
    //Add wine to database 
    @Override
    public void addWine(Wine wine) {
        winesAdded.add(wine);
    }

    //checkout where data is stored in the databse and then shopping cart details are removed from cart.
    @Override
    public void checkout(int userId) {
        Userinfo user = em.getReference(Userinfo.class, userId);
        user.addWineCollection(winesAdded);
        em.merge(user);
        em.flush(); 
        winesAdded.clear();
    }
    
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

    //Display items
    @Override
    public void print(PrintWriter out) {
        out.println( "<br><br><b>Your items:</b><br>");
        for( Wine wine : winesAdded ) {
            out.println( wine.toString() + "<br>");
        }
    }

    //persit data in the database 
    public void persist(Object object) {
        em.persist(object);
        em.flush();
    }
}
