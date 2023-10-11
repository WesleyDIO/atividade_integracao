import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Set;

public class ComandanteDAO extends PadraoDAO<Comandante,Integer> {
    public ComandanteDAO() throws SQLException {
        super("comandante");
    }

    @Override
    public void inserir(Comandante obj) {
        String comandoSQL = "Insert into tank value (?,?,?,?)";
        try(PreparedStatement statement = connection.prepareStatement(comandoSQL)){
            statement.setInt(1,obj.getId());
            statement.setString(2, obj.getNome());
            statement.setString(3,obj.getTropa());
            statement.setInt(4,obj.getTank().getId());
            statement.execute();
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public Comandante converte(ResultSet resultSet) throws SQLException {
        return new Comandante(resultSet);
    }

    @Override
    public void atualizar(Comandante obj) {
         comandoSQL = "Update comandante set nome = ?, tropa = ?, id_tank = ? where id = ?";
        try(PreparedStatement statement = connection.prepareStatement(comandoSQL)){
            statement.setString(1,obj.getNome());
            statement.setString(2,obj.getTropa());
            statement.setInt(3,obj.getTank().getId());
            statement.setInt(4,obj.getId());
            statement.execute();
        }catch (SQLException throwables){
            throwables.printStackTrace();
        }
    }
}
