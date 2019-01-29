/*
 * Author : Aparajita Sahay
*Statless session beans 
 */
package com.example;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Funapp
 */
@Entity
@Table(name = "WINE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Wine.findAll", query = "SELECT w FROM Wine w"),
    @NamedQuery(name = "Wine.findById", query = "SELECT w FROM Wine w WHERE w.id = :id"),
    @NamedQuery(name = "Wine.findByName", query = "SELECT w FROM Wine w WHERE w.name = :name"),
    @NamedQuery(name = "Wine.findByCategory", query = "SELECT w FROM Wine w WHERE w.category = :category")})
public class Wine implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private Integer id;
    @Size(max = 50)
    @Column(name = "NAME")
    private String name;
    @Size(max = 50)
    @Column(name = "CATEGORY")
    private String category;
    @ManyToMany(mappedBy = "wineCollection")
    private Collection<Userinfo> userinfoCollection;

    public Wine() {
    }

    public Wine(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @XmlTransient
    public Collection<Userinfo> getUserinfoCollection() {
        return userinfoCollection;
    }

    public void setUserinfoCollection(Collection<Userinfo> userinfoCollection) {
        this.userinfoCollection = userinfoCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Wine)) {
            return false;
        }
        Wine other = (Wine) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Wine[ id=" + id + ", name =" + name + ", category =" + category + "]";
    }
    
}
