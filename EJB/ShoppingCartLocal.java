/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example;

import java.io.PrintWriter;
import javax.ejb.Local;

/**
 *
 * @author Funapp
 */
@Local
public interface ShoppingCartLocal {
    void addWine(Wine wine);
    void checkout(int userId);
    void print(PrintWriter out);
}
