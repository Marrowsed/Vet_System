package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class UsuarioDAO implements DAO {
    private static final String SQL_INSERIR_USUARIO = "insert into usuarios (nome, login, senha) values(?, ?,MD5(?))";
    private static final String SQL_LISTAR_USUARIOS = "select * from usuarios order by nome";
    private static final String SQL_CONSULTAR_USUARIO = "select * from usuarios where nome like ? order by nome";
    private static final String SQL_AUTENTICAR_USUARIO = "select * from usuarios where login = ? and senha = MD5(?)";
    private static final String SQL_EXCLUIR_USUARIO = "delete from usuarios where id = ?";
    private static final String SQL_ALTERAR_USUARIO = "update usuarios set nome=?, login=?, senha=? where id=?";
    
    private Connection connection;

    @Override
    public Object adicionar(Object obj) throws SQLException {
        Usuario usuario = (Usuario) obj;
        try {
            connection = ConnectionFactory.getConnection();
            try {
                PreparedStatement stmt = connection.prepareStatement(SQL_INSERIR_USUARIO, Statement.RETURN_GENERATED_KEYS);
                stmt.setString(1, usuario.getNome());
                stmt.setString(2, usuario.getLogin());
                stmt.setString(3, usuario.getSenha());
                usuario.setId(new Long(stmt.executeUpdate()));
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
        List<Object> usuarios = new ArrayList<>();
        try {
            connection = ConnectionFactory.getConnection();
            try {
                PreparedStatement stmt = connection.
                        prepareStatement(SQL_LISTAR_USUARIOS);
                ResultSet rs = stmt.executeQuery();
                while (rs.next()) {
                    Usuario u = new Usuario();
                    u.setId(rs.getLong("id"));
                    u.setNome(rs.getString("nome"));
                    u.setLogin(rs.getString("login"));
                    u.setSenha(rs.getString("senha"));
                    usuarios.add(u);
                }
                stmt.close();
                rs.close();
            } finally {
                connection.close();
            }
        } catch (SQLException e) {
            throw e;
        }
        return usuarios;
    }

        @Override
    public List<Object> consultar(String nome) throws SQLException {
        List<Object> usuarios = new ArrayList<>();
        try {
            connection = ConnectionFactory.getConnection();
            try {
                PreparedStatement stmt = connection.
                        prepareStatement(SQL_CONSULTAR_USUARIO);
                stmt.setString(1, '%' + nome + '%');
                ResultSet rs = stmt.executeQuery();
                while (rs.next()) {
                    Usuario u = new Usuario();
                    u.setId(rs.getLong("id"));
                    u.setNome(rs.getString("nome"));
                    u.setLogin(rs.getString("login"));
                    u.setSenha(rs.getString("senha"));
                    usuarios.add(u);
                }
                stmt.close();
                rs.close();
            } finally {
                connection.close();
            }
        } catch (SQLException e) {
            throw e;
        }
        return usuarios;
    }
    
    public Usuario autenticar(String login, String senha) throws SQLException {
        Usuario u = null;
        try {
            connection = ConnectionFactory.getConnection();
            try {
                PreparedStatement stmt = connection.
                        prepareStatement(SQL_AUTENTICAR_USUARIO);
                stmt.setString(1, login);
                stmt.setString(2, senha);
                ResultSet rs = stmt.executeQuery();
                if (rs.next()) {
                    u = new Usuario();
                    u.setId(rs.getLong("id"));
                    u.setNome(rs.getString("nome"));
                    u.setLogin(rs.getString("login"));
                    u.setSenha(rs.getString("senha"));
                }
                stmt.close();
                rs.close();
            } finally {
                connection.close();
            }
        } catch (SQLException e) {
            throw e;
        }
        return u;
    }

    @Override
    public void excluir(Long id) throws SQLException {
        try {
            connection = ConnectionFactory.getConnection();
            try {
                PreparedStatement stmt = connection.prepareStatement(SQL_EXCLUIR_USUARIO);
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
        Usuario usuario = (Usuario) obj;
        try {
            connection = ConnectionFactory.getConnection();
            try {
                PreparedStatement stmt = connection.prepareStatement(SQL_ALTERAR_USUARIO);
                stmt.setString(1, usuario.getNome());
                stmt.setString(2, usuario.getLogin());
                stmt.setString(3, usuario.getSenha());
                stmt.setLong(4, usuario.getId());
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