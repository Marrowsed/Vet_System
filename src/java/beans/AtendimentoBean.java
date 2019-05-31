package beans;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;
import javax.inject.Inject;
import model.Animal;
import model.Atendimento;
import model.AtendimentoDAO;
import model.Cliente;


@Named(value = "atendimentoBean")
@SessionScoped
public class AtendimentoBean implements Serializable {

    @Inject
    private Atendimento atendimento;
    private Animal animal;
    private Cliente cliente;
    private List<Atendimento> atendimentos;
    private List<Animal> animals;
    private List<Cliente> clientes;

    public AtendimentoBean() {
    }

    public Atendimento getAtendimento() {
        return atendimento;
    }

    public void setAtendimento(Atendimento atendimento) {
        this.atendimento = atendimento;
    }

    public Animal getAnimal() {
        return animal;
    }

    public void setAnimal(Animal animal) {
        this.animal = animal;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public List<Atendimento> getAtendimentos() {
        return atendimentos;
    }

    public void setAtendimentos(List<Atendimento> atendimentos) {
        this.atendimentos = atendimentos;
    }

    public List<Animal> getAnimals() {
        return animals;
    }

    public void setAnimals(List<Animal> animals) {
        this.animals = animals;
    }

    public List<Cliente> getClientes() {
        return clientes;
    }

    public void setClientes(List<Cliente> clientes) {
        this.clientes = clientes;
    }
    
    
    
    public String cadastrar() throws SQLException {
        AtendimentoDAO atdao = new AtendimentoDAO();
        atdao.adicionar(atendimento);
        atendimento = new Atendimento();
        return "/atendimento";
    }
    
    public String consultar() throws SQLException {
        AtendimentoDAO atdao = new AtendimentoDAO();
        for (Object o : atdao.consultar(animal.getNome()))
            animals.add((Animal) o);
        return null;
    }
    
    public void exluir(Atendimento a) throws SQLException {
        AtendimentoDAO atdao = new AtendimentoDAO();
        atdao.excluir(a.getId());        
        atendimentos.remove(a);
    }
}
