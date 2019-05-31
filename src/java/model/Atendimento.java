package model;

import java.io.Serializable;
import java.util.Date;
import javax.enterprise.context.Dependent;
import javax.validation.constraints.Future;


@Dependent
public class Atendimento implements Serializable {
    
    private Long id;
    @Future
    private Date data;
    private String observacao;
    private Cliente cliente;
    private Animal animal;

    public Atendimento() {
    }

    public Atendimento(Date data, String observacao) {
        this.data = data;
        this.observacao = observacao;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Animal getAnimal() {
        return animal;
    }

    public void setAnimal(Animal animal) {
        this.animal = animal;
    }
    
    

}
