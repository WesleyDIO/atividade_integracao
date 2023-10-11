import java.sql.SQLException;
import java.util.Set;

public interface ICRUD <T,ID> extends AutoCloseable{

  void inserir(T obj);

  T buscarUm(ID id);

  Set<T> buscarTodos() throws SQLException;

  void atualizar(T obj);

  void deletar(ID id);
}
