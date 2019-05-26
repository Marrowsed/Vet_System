package beans;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;
import javax.inject.Inject;
import model.Cliente;
import model.ClienteDAO;


@Named(value = "clienteBean")
@SessionScoped
public class ClienteBean implements Serializable {

    @Inject
    private Cliente cliente;
    private List<Cliente> clientes;

    public ClienteBean() {
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setUsuario(Cliente cliente) {
        this.cliente = cliente;
    }
    
    public List<Cliente> getClientes() {
        return clientes;
    }

    public void setClientes(List<Cliente> clientes) {
        this.clientes = clientes;
    }
    
    public String cadastrar() throws SQLException {
        ClienteDAO cdao = new ClienteDAO();
        cdao.adicionar(cliente);
        cliente = new Cliente();
        return "/home";
    }
    
    public String consultar() throws SQLException {
        ClienteDAO cdao = new ClienteDAO();
        for (Object o : cdao.consultar(cliente.getNome()))
            clientes.add((Cliente) o);
        return null;
    }
    
    public void exluir(Cliente c) throws SQLException {
        ClienteDAO cdao = new ClienteDAO();
        cdao.excluir(c.getId());        
        clientes.remove(c);
    }
    
}
