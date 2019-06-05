package beans;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import model.Cliente;
import model.ClienteDAO;

@Named(value = "listaCliente")
@SessionScoped
public class ListaClienteBean implements Serializable {

    @Inject
    private Cliente cliente;
    private List<Cliente> clientes;
    
    public ListaClienteBean() throws SQLException, Exception {
        clientes = new ArrayList<>();
        ClienteDAO cdao = new ClienteDAO();
        for (Object o : cdao.listar()) {
            clientes.add((Cliente) o);
        }
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
    
}
