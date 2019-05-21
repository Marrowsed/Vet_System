package beans;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.sql.SQLException;
import javax.inject.Inject;
import model.Cliente;
import model.ClienteDAO;


@Named(value = "clienteBean")
@SessionScoped
public class ClienteBean implements Serializable {

    @Inject
    private Cliente cliente;

    public ClienteBean() {
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setUsuario(Cliente cliente) {
        this.cliente = cliente;
    }
    
    public String cadastrar() throws SQLException {
        ClienteDAO cdao = new ClienteDAO();
        cdao.adicionar(cliente);
        cliente = new Cliente();
        return "/home";
    }
}
