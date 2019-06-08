/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import javax.enterprise.context.Dependent;

@Dependent
public class Horario implements Serializable{
    
    public String id;
    public String manha;
    public String tarde;
    public String noite;
    public String periodo;

    public Horario() {}
    
    public Horario(String id, String periodo) {
        this.id = id;
        this.periodo = periodo;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getManha() {
        return manha;
    }

    public void setManha(String manha) {
        this.manha = manha;
    }

    public String getTarde() {
        return tarde;
    }

    public void setTarde(String tarde) {
        this.tarde = tarde;
    }

    public String getNoite() {
        return noite;
    }

    public void setNoite(String noite) {
        this.noite = noite;
    }

    public String getPeriodo() {
        return periodo;
    }

    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }
    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + (this.id != null ? this.id.hashCode() : 0);
        return hash;
    }
 
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Horario other = (Horario) obj;
        if ((this.id == null) ? (other.id != null) : !this.id.equals(other.id)) {
            return false;
        }
        return true;
    }
}
