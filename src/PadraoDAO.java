import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.Set;

public abstract class PadraoDAO<T,ID> implements ICRUD<T,ID> {

       protected Connection connection;
       protected String comandoSQL;
       private String tabela;

       public PadraoDAO(String tabela) throws SQLException {
          this.connection = BancoDeDados.conexao();
          this.tabela = tabela;
       }

       public Set<T> buscarTodos() {
          comandoSQL = "Select * from " + tabela + ";";
          try(PreparedStatement statement = connection.prepareStatement(comandoSQL)){
              ResultSet resultSet = statement.executeQuery();
              Set<T> lista = new HashSet<>();
              while (resultSet.next()){
                  lista.add(converte(resultSet));
              }
              return lista;
          }catch (SQLException e){
             throw new RuntimeException(e);
          }
       }

       public T buscarUm(Integer id){
           comandoSQL = "Select * from "+tabela+" where id = ?;";
           try(PreparedStatement statement = connection.prepareStatement(comandoSQL)){
               statement.setInt(1,id);
               ResultSet resultSet = statement.executeQuery();
               if (resultSet.next()){
                  return converte(resultSet);
               }
               throw new NoSuchElementException();
           }catch (SQLException e){
               throw new RuntimeException(e);
           }
       }

       public abstract T converte(ResultSet resultSet) throws SQLException;

       public void deletar(Integer id){
         comandoSQL = "Delete from "+ tabela + "where id = ?";
         try (PreparedStatement statement = connection.prepareStatement(comandoSQL)){
              statement.setInt(1,id);
              statement.execute();
         }catch (SQLException e){
             throw new RuntimeException(e);
         }
       }

    @Override
    public void close() throws Exception {
       this.connection.close();
    }
}
