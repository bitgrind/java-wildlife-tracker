import org.sql2o.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.text.SimpleDateFormat;

public class Sighting {
  private int animal_id;
  private int location_id;
  private int ranger_id;
  private int id;
  private String date_sighted;

  public Sighting(int animal_id, int location_id, int ranger_id) {
    this.animal_id = animal_id;
    this.location_id = location_id;
    this.ranger_id = ranger_id;
    this.id = id;
    Date makeDate = new Date();
    this.date_sighted = new SimpleDateFormat("MM-dd-yyyy").format(makeDate);
  }

  public int getId() {
    return id;
  }

  public int getAnimalId() {
    return animal_id;
  }

  public int getLocation() {
    return location_id;
  }

  public int getRangerId() {
    return ranger_id;
  }

  @Override
  public boolean equals(Object otherSighting) {
    if(!(otherSighting instanceof Sighting)) {
      return false;
    } else {
      Sighting newSighting = (Sighting) otherSighting;
      return true;
    }
  }

  public void save() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO sightings (animal_id, location_id, ranger_id, date_sighted) VALUES (:animal_id, :location_id, :ranger_id, :date_sighted);";
      this.id = (int) con.createQuery(sql, true)
        .addParameter("animal_id", this.animal_id)
        .addParameter("location_id", this.location_id)
        .addParameter("ranger_name", this.ranger_id)
        .addParameter("date_sighted", this.date_sighted)
        .throwOnMappingFailure(false)
        .executeUpdate()
        .getKey();
    }
  }

  public static List<Sighting> all() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM sightings;";
      return con.createQuery(sql)
        .executeAndFetch(Sighting.class);
    }
  }

  public static Sighting find(int id) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM sightings WHERE id=:id;";
      Sighting sighting = con.createQuery(sql)
        .addParameter("id", id)
        .executeAndFetchFirst(Sighting.class);
      return sighting;
    } catch (IndexOutOfBoundsException exception) {
      return null;
    }
  }

}
