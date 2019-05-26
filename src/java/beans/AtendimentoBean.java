package beans;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.sql.SQLException;
import javax.inject.Inject;
import model.Atendimento;
import model.AtendimentoDAO;


@Named(value = "atendimentoBean")
@SessionScoped
public class AtendimentoBean implements Serializable {

    @Inject
    private Atendimento atendimento;

    public AtendimentoBean() {
    }

    public Atendimento getAtendimento() {
        return atendimento;
    }

    public void setAtendimento(Atendimento atendimento) {
        this.atendimento = atendimento;
    }
    
    public String cadastrar() throws SQLException {
        AtendimentoDAO atdao = new AtendimentoDAO();
        atdao.adicionar(atendimento);
        atendimento = new Atendimento();
        return "/atendimento";
    }
}
