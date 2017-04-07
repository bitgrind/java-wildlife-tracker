import org.sql2o.*;
import java.util.ArrayList;
import java.util.List;

public class Ranger extends Mammal {
  public static final String EMPLOYER = "Wild Life Reserve";

  public Ranger(String name) {
    this.name = name;
  }

  public void save() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO rangers (name, employer) VALUES (:name, :employer);";
      this.id = (int) con.createQuery(sql, true)
        .addParameter("name", this.name)
        .addParameter("employer", this.EMPLOYER)
        .executeUpdate()
        .getKey();
    }
  }

  public static List<Ranger> all() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM rangers;";
      return con.createQuery(sql)
        .executeAndFetch(Ranger.class);
    }
  }

  public List<Sighting> getSightings() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM sightings WHERE ranger_id = :id;";
      List<Sighting> sightings = con.createQuery(sql)
        .addParameter("id", id)
        .executeAndFetch(Sighting.class);
      return sightings;
    }
  }

  public static Ranger find(int id) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM rangers WHERE id=:id;";
      Ranger ranger = con.createQuery(sql)
        .addParameter("id", id)
        .executeAndFetchFirst(Ranger.class);
      return ranger;
    }
  }
}
