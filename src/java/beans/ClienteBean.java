package beans;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.faces.model.SelectItem;
import javax.inject.Inject;
import model.Cliente;
import model.ClienteDAO;

@Named(value = "clienteBean")
@SessionScoped
public class ClienteBean implements Serializable {

    @Inject
    private Cliente cliente;
    private List<Cliente> clientes;

    public ClienteBean() throws SQLException {
        clientes = new ArrayList<>();
        ClienteDAO cdao = new ClienteDAO();
        for (Object o : cdao.listar()) {
            clientes.add((Cliente) o);
        }
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
        clientes = new ArrayList<>();
        for (Object o : cdao.listar()){
            clientes.add((Cliente) o);
        }
        cliente = new Cliente();
        return "/protected/cliente";
    }

    public String consultar() throws SQLException {
        ClienteDAO cdao = new ClienteDAO();
        for (Object o : cdao.consultar(cliente.getNome())) {
            clientes.add((Cliente) o);
        }
        return null;
    }

    public void exluir(Cliente c) throws SQLException {
        ClienteDAO cdao = new ClienteDAO();
        cdao.excluir(c.getId());
        clientes.remove(c);
    }

    public List<SelectItem> getListaClientes() throws SQLException {
        List<SelectItem> lista = new ArrayList<>();
        ClienteDAO cdao = new ClienteDAO();
        for (Object o : cdao.listar()) {
            Cliente c = (Cliente) o;
            lista.add(new SelectItem(c.getId(), c.getNome()));
        }
        return lista;
    }
    
    public String pag_alterar(Cliente c) {
        cliente = c;
        return "/protected/alterar_cliente";
    }
    
    public String alterar() throws SQLException {
        ClienteDAO cdao = new ClienteDAO();
        cdao.alterar(cliente);        
        cliente = new Cliente();
        return "/protected/cliente";
    }

    public String cancelar() {
        cliente = new Cliente();
        return "/protected/cliente";
    }

}
