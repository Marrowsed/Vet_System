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

@Named(value = "atendimentoBean")
@SessionScoped
public class AtendimentoBean implements Serializable {

    @Inject
    private Atendimento atendimento;
    @Inject
    private Animal animal;
    @Inject
    private Cliente cliente;
    private List<Atendimento> atendimentos;
    private List<Animal> animals;
    private List<Cliente> clientes;
    private boolean ListaAnimaisDesabilitado;

    public AtendimentoBean() throws SQLException {
        atendimentos = new ArrayList<>();
        AtendimentoDAO atdao = new AtendimentoDAO();
        for (Object o : atdao.listar()) {
            atendimentos.add((Atendimento) o);
        }
        ListaAnimaisDesabilitado = true;
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

    public boolean isListaAnimaisDesabilitado() {
        return ListaAnimaisDesabilitado;
    }

    public void setListaAnimaisDesabilitado(boolean ListaAnimaisDesabilitado) {
        this.ListaAnimaisDesabilitado = ListaAnimaisDesabilitado;
    }

    public String adicionar() throws SQLException {
        AtendimentoDAO atdao = new AtendimentoDAO();
        atendimento.setAnimal(new Animal());
        atendimento.setCliente(new Cliente());
        System.out.println("Cliente Id: " + cliente.getId());
        System.out.println("Animal Id: " + animal.getId());

        atendimento.getCliente().setId(cliente.getId());
        atendimento.getAnimal().setId(animal.getId());
        atdao.adicionar(atendimento);
        atendimentos = new ArrayList<>();
        for (Object o : atdao.listar()) {
            atendimentos.add((Atendimento) o);
        }
        atendimento = new Atendimento();
        return "/protected/atendimento";
    }

    public String consultar() throws SQLException {
        AtendimentoDAO atdao = new AtendimentoDAO();
        for (Object o : atdao.consultar(animal.getNome())) {
            animals.add((Animal) o);
        }
        return null;
    }

    public void exluir(Atendimento a) throws SQLException {
        AtendimentoDAO atdao = new AtendimentoDAO();
        atdao.excluir(a.getId());
        atendimentos.remove(a);
    }

    public void habilita() {
        ListaAnimaisDesabilitado = false;
    }
    
     public List<SelectItem> getListaAnimais() throws SQLException {
        List<SelectItem> lista = new ArrayList<>();
        AnimalDAO adao = new AnimalDAO();
        for (Object o : adao.listar()) {
            Animal a = (Animal) o;
            if (Objects.equals(a.getCliente().getId(), cliente.getId())) {
                lista.add(new SelectItem(a.getId(), a.getNome()));
            }
        }
        return lista;
    }
     
     public String pag_alterar(Atendimento atd) {
        atendimento = atd;
        return "/protected/alterar_atendimento";
    }
     
     public String alterar() throws SQLException {
        AtendimentoDAO atdao = new AtendimentoDAO();
        atdao.alterar(atendimento);        
        atendimento = new Atendimento();
        return "/protected/atendimento";
    }

    public String cancelar() {
        atendimento = new Atendimento();
        return "/protected/atendimento";
    }
}
