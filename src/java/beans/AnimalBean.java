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
import model.Cliente;

@Named(value = "animalBean")
@SessionScoped
public class AnimalBean implements Serializable {

    @Inject
    private Animal animal;
    @Inject
    private Cliente cliente;
    private List<Animal> animals;
    private List<Cliente> clientes;

    public AnimalBean() throws SQLException {
        animals = new ArrayList<>();
        AnimalDAO adao = new AnimalDAO();
        for (Object o : adao.listar()) {
            animals.add((Animal) o);
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

    public String cadastrar() throws SQLException {
        AnimalDAO adao = new AnimalDAO();
        animal.setCliente(new Cliente());
        animal.getCliente().setId(cliente.getId());
        adao.adicionar(animal);
        animals = new ArrayList<>();
        for (Object o : adao.listar()) {
            animals.add((Animal) o);
        }
        animal = new Animal();
        return "/protected/animal";
    }

    public String consultar() throws SQLException {
        AnimalDAO adao = new AnimalDAO();
        for (Object o : adao.consultar(animal.getNome())) {
            animals.add((Animal) o);
        }
        return null;
    }

    public void exluir(Animal a) throws SQLException {
        AnimalDAO adao = new AnimalDAO();
        adao.excluir(a.getId());
        animals.remove(a);
    }

   /* public List<SelectItem> getListaAnimais() throws SQLException {
        List<SelectItem> lista = new ArrayList<>();
        AnimalDAO adao = new AnimalDAO();
        for (Object o : adao.listar()) {
            Animal a = (Animal) o;
            if (Objects.equals(a.getCliente().getId(), cliente.getId())) {
                lista.add(new SelectItem(a.getId(), a.getNome()));
            }
        }
        return lista;
    }*/
}
