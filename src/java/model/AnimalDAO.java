package model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class AnimalDAO implements DAO {
    private static final String SQL_INSERIR_ANIMAL = "insert into animals (nome, especie, raca, nascimento, cliente_id) values(?,?,?,?,?)";
    private static final String SQL_LISTAR_ANIMALS = "select * from animals as a left join clientes as c on a.cliente_id = a.id order by a.nome";
    private static final String SQL_CONSULTAR_ANIMAL = "select * from animal where nome like ? order by nome";
    private static final String SQL_EXCLUIR_ANIMAL = "delete from animal where id = ?";
    private static final String SQL_ALTERAR_ANIMAL = "update animals set nome=?, especie=?, raca=?, nascimento=? where id=?";
    
    private Connection connection;

    @Override
    public Object adicionar(Object obj) throws SQLException {
        Animal animal = (Animal) obj;
        try {
            connection = ConnectionFactory.getConnection();
            try {
                PreparedStatement stmt = connection.prepareStatement(SQL_INSERIR_ANIMAL, Statement.RETURN_GENERATED_KEYS);
                stmt.setString(1, animal.getNome());
                stmt.setString(2, animal.getEspecie());
                stmt.setString(3, animal.getRaca());
                stmt.setDate(4, new java.sql.Date(animal.getNascimento().getTime()));
                stmt.setLong(5, animal.getCliente().getId());
                stmt.executeUpdate();
                ResultSet rs = stmt.getGeneratedKeys();
                if (rs != null && rs.next()) {                   
                    animal.setId(rs.getLong(1));
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
        List<Object> animals = new ArrayList<>();
        try {
            connection = ConnectionFactory.getConnection();
            try {
                PreparedStatement stmt = connection.
                        prepareStatement(SQL_LISTAR_ANIMALS);
                ResultSet rs = stmt.executeQuery();
                while (rs.next()) {
                    Animal a = new Animal();
                    a.setId(rs.getLong("id"));
                    a.setNome(rs.getString("nome"));
                    a.setEspecie(rs.getString("especie"));
                    a.setRaca(rs.getString("raca"));
                    a.setNascimento(rs.getDate("nascimento"));
                    a.setCliente(new Cliente());
                    a.getCliente().setId(rs.getLong("cliente_id"));
                    animals.add(a);
                }
                stmt.close();
                rs.close();
            } finally {
                connection.close();
            }
        } catch (SQLException e) {
            throw e;
        }
        return animals;
    }

        @Override
    public List<Object> consultar(String nome) throws SQLException {
        List<Object> animals = new ArrayList<>();
        try {
            connection = ConnectionFactory.getConnection();
            try {
                PreparedStatement stmt = connection.
                        prepareStatement(SQL_CONSULTAR_ANIMAL);
                stmt.setString(1, '%' + nome + '%');
                ResultSet rs = stmt.executeQuery();
                while (rs.next()) {
                    Animal a = new Animal();
                    a.setId(rs.getLong("id"));
                    a.setNome(rs.getString("nome"));
                    a.setEspecie(rs.getString("especie"));
                    a.setRaca(rs.getString("raca"));
                    a.setNascimento(rs.getDate("nascimento"));
                    animals.add(a);
                }
                stmt.close();
                rs.close();
            } finally {
                connection.close();
            }
        } catch (SQLException e) {
            throw e;
        }
        return animals;
    }
    
    @Override
    public void excluir(Long id) throws SQLException {
        try {
            connection = ConnectionFactory.getConnection();
            try {
                PreparedStatement stmt = connection.prepareStatement(SQL_EXCLUIR_ANIMAL);
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
        Animal animal = (Animal) obj;
        try {
            connection = ConnectionFactory.getConnection();
            try {
                PreparedStatement stmt = connection.prepareStatement(SQL_ALTERAR_ANIMAL);
                stmt.setString(1, animal.getNome());
                stmt.setString(2, animal.getEspecie());
                stmt.setString(3, animal.getRaca());
                stmt.setDate(4, new java.sql.Date(animal.getNascimento().getTime()));
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