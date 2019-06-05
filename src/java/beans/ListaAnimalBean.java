package beans;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import model.Animal;
import model.AnimalDAO;
import model.Cliente;
import model.ClienteDAO;



@Named(value = "listaAnimal")
@SessionScoped
public class ListaAnimalBean implements Serializable {

    @Inject
    private Animal animal;
    private List<Animal> animals;

    
    public ListaAnimalBean() throws SQLException, Exception {
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
    
}
