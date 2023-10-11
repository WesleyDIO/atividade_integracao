import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Set;

public class TankDAO extends PadraoDAO<Tank,Integer>{
    public TankDAO() throws SQLException {
        super("tank");
    }

    @Override
    public void inserir(Tank obj) {
        String comandoSQL = "insert into comandante value(?,?,?,?)";
            try(PreparedStatement statement = connection.prepareStatement(comandoSQL)){
                statement.setInt(1,obj.getId());
                statement.setString(2,obj.getNome());
                statement.setInt(3,obj.getCano());
                statement.setInt(4,obj.getBlindagem());
                statement.execute();
            } catch (SQLException e) {
                throw new RuntimeException(e);
        }
    }

    @Override
    public Tank converte(ResultSet resultSet) throws SQLException {
        return new Tank(resultSet);
    }

    @Override
    public void atualizar(Tank obj) {
     comandoSQL = "Update tank set nome = ?, cano = ?, blindagem = ? where id = ?";
     try(PreparedStatement statement = connection.prepareStatement(comandoSQL)){
         statement.setString(1,obj.getNome());
         statement.setInt(2,obj.getCano());
         statement.setInt(3,obj.getBlindagem());
         statement.setInt(4,obj.getId());
         statement.execute();
     }catch (SQLException e){
         throw new RuntimeException(e);
     }
    }

}
