package model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class AtendimentoDAO implements DAO {
    private static final String SQL_INSERIR_ATENDIMENTO = "insert into atendimentos (data, observacao, cliente_id, animal_id) values(?,?,?,?)";
    private static final String SQL_LISTAR_ATENDIMENTOS = "select * from atendimentos order by data";
    private static final String SQL_CONSULTAR_ATENDIMENTO = "select * from atendimento where data like ? order by data";
    private static final String SQL_EXCLUIR_ATENDIMENTO = "delete from atendimento where id = ?";
    private static final String SQL_ALTERAR_ATENDIMENTO = "update animals set data=?, observacao=? where id=?";
    
    private Connection connection;

    @Override
    public Object adicionar(Object obj) throws SQLException {
        Atendimento atendimento = (Atendimento) obj;
        try {
            connection = ConnectionFactory.getConnection();
            try {
                PreparedStatement stmt = connection.prepareStatement(SQL_INSERIR_ATENDIMENTO, Statement.RETURN_GENERATED_KEYS);
                stmt.setDate(1, new java.sql.Date(atendimento.getData().getTime()));
                stmt.setString(2, atendimento.getObservacao());
                stmt.setLong(3, atendimento.getCliente().getId());
                stmt.setLong(4, atendimento.getAnimal().getId());
                stmt.executeUpdate();
                ResultSet rs = stmt.getGeneratedKeys();
                if (rs != null && rs.next()) {                   
                    atendimento.setId(rs.getLong(1));
                    rs.close();
                }
                stmt.close();
            } finally {
                connection.close();
            }
        } catch (SQLException ex) {
            throw ex;
        }        
        return obj;
    }

    @Override
    public List<Object> listar() throws SQLException {
        List<Object> atendimentos = new ArrayList<>();
        try {
            connection = ConnectionFactory.getConnection();
            try {
                PreparedStatement stmt = connection.
                        prepareStatement(SQL_LISTAR_ATENDIMENTOS);
                ResultSet rs = stmt.executeQuery();
                while (rs.next()) {
                    Atendimento at = new Atendimento();
                    at.setId(rs.getLong("id"));
                    at.setData(rs.getDate("data"));
                    at.setObservacao(rs.getString("observacao"));
                    atendimentos.add(at);
                }
                stmt.close();
                rs.close();
            } finally {
                connection.close();
            }
        } catch (SQLException e) {
            throw e;
        }
        return atendimentos;
    }

        @Override
    public List<Object> consultar(String nome) throws SQLException {
            List<Object> atendimentos = new ArrayList<>();
        try {
            connection = ConnectionFactory.getConnection();
            try {
                PreparedStatement stmt = connection.
                        prepareStatement(SQL_CONSULTAR_ATENDIMENTO);
                stmt.setString(1, '%' + nome + '%');
                ResultSet rs = stmt.executeQuery();
                while (rs.next()) {
                    Atendimento at = new Atendimento();
                    at.setId(rs.getLong("id"));
                    at.setData(rs.getDate("data"));
                    at.setObservacao(rs.getString("observacao"));
                    atendimentos.add(at);
                }
                stmt.close();
                rs.close();
            } finally {
                connection.close();
            }
        } catch (SQLException e) {
            throw e;
        }
        return atendimentos;
    }
    
    @Override
    public void excluir(Long id) throws SQLException {
        try {
            connection = ConnectionFactory.getConnection();
            try {
                PreparedStatement stmt = connection.prepareStatement(SQL_EXCLUIR_ATENDIMENTO);
                stmt.setLong(1, id);
                stmt.execute();
                stmt.close();
            } finally {
                connection.close();
            }
        } catch (SQLException ex) {
            throw ex;
        }
    }

    @Override
    public void alterar(Object obj) throws SQLException {
        Atendimento atendimento = (Atendimento) obj;
        try {
            connection = ConnectionFactory.getConnection();
            try {
                PreparedStatement stmt = connection.prepareStatement(SQL_ALTERAR_ATENDIMENTO);
                stmt.setDate(1, new java.sql.Date(atendimento.getData().getTime()));
                stmt.setString(2, atendimento.getObservacao());
                stmt.execute();
                stmt.close();
            } finally {
                connection.close();
            }
        } catch (SQLException ex) {
            throw ex;
        }
    }  
}