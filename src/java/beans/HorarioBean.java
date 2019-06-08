/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import model.Horario;
import org.primefaces.event.TabChangeEvent;
import org.primefaces.event.TabCloseEvent;

@Named(value = "horarioBean")
@SessionScoped
public class HorarioBean implements Serializable {
    
    private List<Horario> horarios;
 
    @PostConstruct
    public void init() {
        horarios = new ArrayList<Horario>();
        horarios.add(new Horario("MANHA", "Das 08:00 as 11:00"));
        horarios.add(new Horario("TARDE", "Das 13:00 as 16:00"));
        horarios.add(new Horario("NOITE", "Das 19:00 as 21:00"));
    }
     
    public List<Horario> getHorarios() {
        return horarios;
    }
     
    public void onTabChange(TabChangeEvent event) {
        FacesMessage msg = new FacesMessage("Tab Changed", "Active Tab: " + event.getTab().getTitle());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
         
    public void onTabClose(TabCloseEvent event) {
        FacesMessage msg = new FacesMessage("Tab Closed", "Closed tab: " + event.getTab().getTitle());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
}
