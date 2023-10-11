import java.sql.*;
import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.Set;

public class Main {

    public static void main(String[] args) throws SQLException {

        String url_banco = "jdbc:mysql://localhost:3306/atividade";
        String userName = "root";
        String password = "root";
        try (Connection connection = DriverManager.getConnection(url_banco,userName,password)) {
            inserir(2,"Wesley","7 Batalhao",6,connection);

        }catch (SQLException e){
            e.printStackTrace();
        }

    }


    public static void inserir(Integer id, String nome, String tropa, Integer tank,Connection connection){
        String comandoSQL = "insert into comandante value(?,?,?,?)";
        try(PreparedStatement statement = connection.prepareStatement(comandoSQL)){
            statement.setInt(1,id);
            statement.setString(2,nome);
            statement.setString(3,tropa);
            statement.setInt(4,tank);
            statement.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static void deletar(Integer id, Connection connection, String tabela){
        String comandoSQL = "Delete from ? where id = ?";
        try(PreparedStatement statement = connection.prepareStatement(comandoSQL)){
            statement.setString(1,tabela);
            statement.setInt(2,id);
            statement.execute();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static Set<Comandante> buscarTodos(Connection connection) {
        String comandoSQL = "Select * from comandante"  ;
        try(PreparedStatement statement = connection.prepareStatement(comandoSQL)){
            ResultSet resultSet = statement.executeQuery();
            Set<Comandante>listaComandante= new HashSet<>();
            while(resultSet.next()){
                Integer id = resultSet.getInt("id");
                String nome = resultSet.getString("nome");
                String tropa = resultSet.getString("tropa");
                Integer id_tank = resultSet.getInt("id_tank");

                listaComandante.add(new Comandante(id,nome,tropa,new Tank(id_tank)));
            }
            return listaComandante;
        }catch (SQLException throwables){
            throwables.printStackTrace();
        }
        return null;
    }

    public static Set<Tank> buscarTodosTank(Connection connection) {
        String comandoSQL = "Select * from tank";
        try (PreparedStatement statement = connection.prepareStatement(comandoSQL)) {
            ResultSet resultSet = statement.executeQuery();
            Set<Tank> listaTank = new HashSet<>();
            while (resultSet.next()) {
                Integer id = resultSet.getInt("id");
                String nome = resultSet.getString("nome");
                Integer cano = resultSet.getInt("cano");
                Integer blindagem = resultSet.getInt("blindagem");
                listaTank.add(new Tank(id,nome,cano,blindagem));
            }
            return listaTank;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static Comandante buscarUm(Integer id, Connection connection){
        String comandoSQL = "Select * from comandante where id = ?";
        try(PreparedStatement statement = connection.prepareStatement(comandoSQL)) {
            statement.setInt(1,id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()){
                String nome = resultSet.getString("nome");
                String tropa = resultSet.getString("tropa");
                Integer id_tank = resultSet.getInt("id_tank");
                return new Comandante(id,nome,tropa,buscarUmTank(id_tank,connection));
            } throw new NoSuchElementException();
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    public static Tank buscarUmTank(Integer id, Connection connection){
        String comandoSQL = "Select * from tank where id = ?";
        try(PreparedStatement statement = connection.prepareStatement(comandoSQL)) {
            statement.setInt(1,id);
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()) {
                String nome = resultSet.getString("nome");
                Integer cano = resultSet.getInt("cano");
                Integer blindagem = resultSet.getInt("blindagem");
                return new Tank(id, nome, cano, blindagem);
            }
            throw new NoSuchElementException();
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    public static void atualizar(Integer id,String nome,String tropa, Integer id_tank, Connection connection){
        String comandoSQL = "Update comandante set nome = ?, tropa = ?, id_tank = ? where id = ?";
        try(PreparedStatement statement = connection.prepareStatement(comandoSQL)){
            statement.setString(1,nome);
            statement.setString(2,tropa);
            statement.setInt(3,id_tank);
            statement.setInt(4,id);
            statement.execute();
        }catch (SQLException throwables){
            throwables.printStackTrace();
        }
    }

    public static void inserirNovoTank(Integer id, String nome, Integer cano, Integer blindagem, Connection connection)  {
        String comandoSQL = "Insert into tank value (?,?,?,?)";
        try(PreparedStatement statement = connection.prepareStatement(comandoSQL)){
            statement.setInt(1,id);
            statement.setString(2,nome);
            statement.setInt(3,cano);
            statement.setInt(4,blindagem);
            statement.execute();
        }catch (SQLException throwables){
            throwables.printStackTrace();
        }
    }

    public static void atualizarTank(Integer id, String nome, Integer cano, Integer blindagem, Connection connection){
        String comandoSQL = "Update tank set nome = ?, cano = ?, blindagem = ? where id = ?";
        try(PreparedStatement statement = connection.prepareStatement(comandoSQL)){
            statement.setString(1,nome);
            statement.setInt(2,cano);
            statement.setInt(3,blindagem);
            statement.setInt(4,id);
            statement.execute();
        }catch (SQLException throwables){
            throwables.printStackTrace();
        }
    }
}
