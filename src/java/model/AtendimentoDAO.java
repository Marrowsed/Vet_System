package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class AtendimentoDAO implements DAO {
    private static final String SQL_INSERIR_ATENDIMENTO = "insert into atendimentos (data, observacao, cliente_id, animal_id) values(?,?,?,?)";
    private static final String SQL_LISTAR_ATENDIMENTOS = "select at.id, at.data, at.observacao, c.id, c.nome, a.id, a.nome \n" +
        "from atendimentos as at \n" +
        "left join clientes as c on at.cliente_id = c.id  \n " +  
        "left join animals as a on at.animal_id = a.id order by a.nome\n";
    private static final String SQL_CONSULTAR_ATENDIMENTO = "select * from atendimentos where data like ? order by data";
    private static final String SQL_EXCLUIR_ATENDIMENTO = "delete from atendimentos where id = ?";
    private static final String SQL_ALTERAR_ATENDIMENTO = "update atendimentos set data=?, observacao=? where id=?";
    
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
                System.out.println(atendimento.getCliente());
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
                    at.setCliente(new Cliente());
                    at.getCliente().setId(rs.getLong(4));
                    at.getCliente().setNome(rs.getString(5));
                    at.setAnimal(new Animal());
                    at.getAnimal().setId(rs.getLong(6));
                    at.getAnimal().setNome(rs.getString(7));
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
                    at.setCliente(new Cliente());
                    at.setCliente(new Cliente());
                    at.getCliente().setId(rs.getLong(4));
                    at.getCliente().setNome(rs.getString(5));
                    at.setAnimal(new Animal());
                    at.getAnimal().setId(rs.getLong(6));
                    at.getAnimal().setNome(rs.getString(7));
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
                stmt.setLong(3, atendimento.getId());
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