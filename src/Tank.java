import java.sql.ResultSet;
import java.sql.SQLException;

public class Tank {

    private Integer id;
    private String nome;
    private Integer cano;
    private Integer blindagem;

    public Tank(Integer id, String nome, Integer cano, Integer blindagem) {
        this.id = id;
        this.nome = nome;
        this.cano = cano;
        this.blindagem = blindagem;
    }

    public Tank(Integer id){
      this.id = id;
    }

    public Tank(ResultSet resultSet) throws SQLException {
        this.id = resultSet.getInt("id");
        this.nome = resultSet.getString("nome");
        this.cano = resultSet.getInt("cano");
        this.blindagem = resultSet.getInt("blindagem");
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getCano() {
        return cano;
    }

    public void setCano(Integer cano) {
        this.cano = cano;
    }

    public Integer getBlindagem() {
        return blindagem;
    }

    public void setBlindagem(Integer blindagem) {
        this.blindagem = blindagem;
    }

    @Override
    public String toString() {
        return "Tank{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", cano=" + cano +
                ", blindagem=" + blindagem +
                '}';
    }
}
