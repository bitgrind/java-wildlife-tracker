import org.sql2o.*;
import java.util.ArrayList;
import java.util.List;

public class Location extends Mammal {
  public Location(String name){
    this.name = name;
  }

  public void save() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO location (name) VALUES (:name);";
      this.id = (int) con.createQuery(sql, true)
        .addParameter("name", this.name)
        .executeUpdate()
        .getKey();
    }
  }

  public static List<Location> all() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM location;";
      return con.createQuery(sql)
        .executeAndFetch(Location.class);
    }
  }

  public static Location find(int id) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM location WHERE id=:id;";
      Location location = con.createQuery(sql)
        .addParameter("id", id)
        .executeAndFetchFirst(Location.class);
      return location;
    }
  }
}
