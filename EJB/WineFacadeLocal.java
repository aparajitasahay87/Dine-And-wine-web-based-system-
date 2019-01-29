/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example;

import java.io.PrintWriter;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Funapp
 */
@Local
public interface WineFacadeLocal {

    void create(Wine wine);

    void edit(Wine wine);

    void remove(Wine wine);

    Wine find(Object id);

    List<Wine> findAll();

    List<Wine> findRange(int[] range);

    int count();
    
    List<Wine> findByCategory(String category);
    List<Wine> findById(String id);
}
