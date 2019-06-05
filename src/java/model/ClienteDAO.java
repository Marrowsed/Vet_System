package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class ClienteDAO implements DAO {
    private static final String SQL_INSERIR_CLIENTE = "insert into clientes (nome, cpf, endereco, telefone, email) values(?,?,?,?,?)";
    private static final String SQL_LISTAR_CLIENTES = "select * from clientes order by nome";
    private static final String SQL_CONSULTAR_CLIENTE = "select * from clientes where nome like ? order by nome";
    private static final String SQL_EXCLUIR_CLIENTE = "delete from clientes where id = ?";
    private static final String SQL_ALTERAR_CLIENTE = "update clientes set nome=?, cpf=?, endereco=?, telefone=?, email=? where id=?";
    
    private Connection connection;

    @Override
    public Object adicionar(Object obj) throws SQLException {
        Cliente cliente = (Cliente) obj;
        try {
            connection = ConnectionFactory.getConnection();
            try {
                PreparedStatement stmt = connection.prepareStatement(SQL_INSERIR_CLIENTE, Statement.RETURN_GENERATED_KEYS);
                stmt.setString(1, cliente.getNome());
                stmt.setString(2, cliente.getCpf());
                stmt.setString(3, cliente.getEndereco());
                stmt.setString(4, cliente.getTelefone());
                stmt.setString(5, cliente.getEmail());
                cliente.setId(new Long(stmt.executeUpdate()));
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
        List<Object> clientes = new ArrayList<>();
        try {
            connection = ConnectionFactory.getConnection();
            try {
                PreparedStatement stmt = connection.
                        prepareStatement(SQL_LISTAR_CLIENTES);
                ResultSet rs = stmt.executeQuery();
                while (rs.next()) {
                    Cliente c = new Cliente();
                    c.setId(rs.getLong("id"));
                    c.setNome(rs.getString("nome"));
                    c.setCpf(rs.getString("cpf"));
                    c.setEndereco(rs.getString("endereco"));
                    c.setTelefone(rs.getString("telefone"));
                    c.setEmail(rs.getString("email"));
                    clientes.add(c);
                }
                stmt.close();
                rs.close();
            } finally {
                connection.close();
            }
        } catch (SQLException e) {
            throw e;
        }
        return clientes;
    }

        @Override
    public List<Object> consultar(String nome) throws SQLException {
        List<Object> clientes = new ArrayList<>();
        try {
            connection = ConnectionFactory.getConnection();
            try {
                PreparedStatement stmt = connection.
                        prepareStatement(SQL_CONSULTAR_CLIENTE);
                stmt.setString(1, '%' + nome + '%');
                ResultSet rs = stmt.executeQuery();
                while (rs.next()) {
                    Cliente c = new Cliente();
                    c.setId(rs.getLong("id"));
                    c.setNome(rs.getString("nome"));
                    c.setCpf(rs.getString("cpf"));
                    c.setEndereco(rs.getString("endereco"));
                    c.setTelefone(rs.getString("telefone"));
                    c.setEmail(rs.getString("email"));
                    clientes.add(c);
                }
                stmt.close();
                rs.close();
            } finally {
                connection.close();
            }
        } catch (SQLException e) {
            throw e;
        }
        return clientes;
    }
    
    @Override
    public void excluir(Long id) throws SQLException {
        try {
            connection = ConnectionFactory.getConnection();
            try {
                PreparedStatement stmt = connection.prepareStatement(SQL_EXCLUIR_CLIENTE);
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
        Cliente cliente = (Cliente) obj;
        try {
            connection = ConnectionFactory.getConnection();
            try {
                PreparedStatement stmt = connection.prepareStatement(SQL_ALTERAR_CLIENTE);
                stmt.setString(1, cliente.getNome());
                stmt.setString(2, cliente.getCpf());
                stmt.setString(3, cliente.getEndereco());
                stmt.setString(4, cliente.getTelefone());
                stmt.setString(5, cliente.getEmail());
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