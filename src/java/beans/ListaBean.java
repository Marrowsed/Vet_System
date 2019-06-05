package beans;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.faces.model.SelectItem;
import javax.inject.Inject;
import model.Animal;
import model.AnimalDAO;
import model.Atendimento;
import model.AtendimentoDAO;
import model.Cliente;
import model.ClienteDAO;



@Named(value = "lista")
@SessionScoped
public class ListaBean implements Serializable {

    @Inject
    private Animal animal;
    @Inject
    private Cliente cliente;
    @Inject
    private Atendimento atendimento;
    private List<Animal> animals;
    private List<Cliente> clientes;
    private List<Atendimento> atendimentos;
    
    public ListaBean() throws SQLException, Exception {
        animals = new ArrayList<>();
        clientes = new ArrayList<>();
        atendimentos = new ArrayList<>();
        ClienteDAO cdao = new ClienteDAO();
        AnimalDAO adao = new AnimalDAO();
        AtendimentoDAO atdao = new AtendimentoDAO();
        for (Object o : cdao.listar()) {
            clientes.add((Cliente) o);
        }
        for (Object o : adao.listar()) {
            animals.add((Animal) o);
        }
        for (Object o : atdao.listar()) {
            atendimentos.add((Atendimento) o);
        }
    }
    

    public Animal getAnimal() {
        return animal;
    }

    public void setAnimal(Animal animal) {
        this.animal = animal;
    }

    public List<Animal> getAnimals() {
        return animals;
    }

    public void setAnimals(List<Animal> animals) {
        this.animals = animals;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public List<Cliente> getClientes() {
        return clientes;
    }

    public void setClientes(List<Cliente> clientes) {
        this.clientes = clientes;
    }

    public Atendimento getAtendimento() {
        return atendimento;
    }

    public void setAtendimento(Atendimento atendimento) {
        this.atendimento = atendimento;
    }

    public List<Atendimento> getAtendimentos() {
        return atendimentos;
    }

    public void setAtendimentos(List<Atendimento> atendimentos) {
        this.atendimentos = atendimentos;
    }

    
}
