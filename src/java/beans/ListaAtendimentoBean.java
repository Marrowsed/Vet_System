package beans;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import model.Atendimento;
import model.AtendimentoDAO;


@Named(value = "listaAtendimento")
@SessionScoped
public class ListaAtendimentoBean implements Serializable {

    @Inject
    private Atendimento atendimento;
    private List<Atendimento> atendimentos;

    
    public ListaAtendimentoBean() throws SQLException, Exception {
        atendimentos = new ArrayList<>();
        AtendimentoDAO atdao = new AtendimentoDAO();
        for (Object o : atdao.listar()) {
            atendimentos.add((Atendimento) o);
        }
    }

    public Atendimento getAtendimento() {
        return atendimento;
    }

    public void setAtendimento(Atendimento atendimento) {
        this.atendimento = atendimento;
    }

    public List<Atendimento> getAtendimentos() {
        return atendimentos;
    }

    public void setAtendimentos(List<Atendimento> atendimentos) {
        this.atendimentos = atendimentos;
    }
    
}
