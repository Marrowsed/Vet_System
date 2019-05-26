package beans;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.sql.SQLException;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import model.Usuario;
import model.UsuarioDAO;
import session.SessionContext;

@Named(value = "usuarioBean")
@SessionScoped
public class UsuarioBean implements Serializable {
    @Inject
    private Usuario usuario;

    public UsuarioBean() {
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    
    public String autenticar() throws SQLException {
        UsuarioDAO dao = new UsuarioDAO();
        Usuario _usuario;
        if ((_usuario = dao.autenticar(usuario.getLogin(), usuario.getSenha())) != null) {
            usuario = _usuario;
            System.out.println("Usuario logado: " + usuario);
            SessionContext session = SessionContext.getInstance();
            session.setAttribute("usuario", usuario);
            return "/homeLogado";
        }
        FacesMessage message = new FacesMessage("Login/senha inválidos!");
        message.setSeverity(FacesMessage.SEVERITY_ERROR);
        FacesContext.getCurrentInstance().addMessage(null, message);
        return null;
    }
    
    public String cadastrar() throws SQLException {
        UsuarioDAO udao = new UsuarioDAO();
        udao.adicionar(usuario);
        usuario = new Usuario();
        System.out.println("Usuario cadastrado: " + usuario);
        return "/home";
    }
    
}
