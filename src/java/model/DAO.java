package model;

import java.sql.SQLException;
import java.util.List;


public interface DAO {
    Object adicionar(Object obj) throws SQLException;
    List<Object> listar() throws SQLException;
    List<Object> consultar(String param) throws SQLException;
    void excluir(Long id) throws SQLException;
    void alterar(Object obj) throws SQLException;
}
