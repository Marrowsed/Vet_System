package model;

import java.io.Serializable;
import java.util.Date;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.validation.constraints.Past;


@Dependent
public class Animal implements Serializable {
    private Long id;
    private String nome, especie, raca;
    @Past
    private Date nascimento;
    @Inject
    private Cliente cliente;

    public Animal() {
    }

    public Animal(Long id, String nome, String especie, String raca, Date nascimento) {
        this.id = id;
        this.nome = nome;
        this.especie = especie;
        this.raca = raca;
        this.nascimento = nascimento;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEspecie() {
        return especie;
    }

    public void setEspecie(String especie) {
        this.especie = especie;
    }

    public String getRaca() {
        return raca;
    }

    public void setRaca(String raca) {
        this.raca = raca;
    }

    public Date getNascimento() {
        return nascimento;
    }

    public void setNascimento(Date nascimento) {
        this.nascimento = nascimento;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
    
}
