import java.sql.ResultSet;
import java.sql.SQLException;

public class Comandante {

   private Integer id;
   private String nome;
   private String tropa;
   private Tank tank;

    public Comandante(Integer id, String nome, String tropa, Tank tank) {
        this.id = id;
        this.nome = nome;
        this.tropa = tropa;
        this.tank = tank;
    }

    public Comandante(ResultSet resultSet) throws SQLException {
        this.id = resultSet.getInt("id");
        this.nome = resultSet.getString("nome");
        this.tropa = resultSet.getString("tropa");
        int id_tank = resultSet.getInt("id_tank");
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

    public String getTropa() {
        return tropa;
    }

    public void setTropa(String tropa) {
        this.tropa = tropa;
    }

    public Tank getTank() {
        return tank;
    }

    public void setTank(Tank tank) {
        this.tank = tank;
    }

    @Override
    public String toString() {
        return "Comandante{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", tropa='" + tropa + '\'' +
                ", tank=" + tank +
                '}';
    }
}
