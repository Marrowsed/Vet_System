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

}
