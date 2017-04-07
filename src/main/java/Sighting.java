import org.sql2o.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.time.*;
import java.text.SimpleDateFormat;

public class Sighting {
  private int id;
  private int animal_id;
  private String animal_name;
  private int location_id;
  private String location_name;
  private int ranger_id;
  private String ranger_name;
  private String date_sighted;

  public Sighting(int animal_id, String animal_name, int location_id, String location_name, int ranger_id, String ranger_name) {
    this.animal_id = animal_id;
    this.animal_name = animal_name;
    this.location_id = location_id;
    this.location_name = location_name;
    this.ranger_id = ranger_id;
    this.ranger_name = ranger_name;
    Date makeDate = new Date();
    this.date_sighted = new SimpleDateFormat("MM-dd-yyyy").format(makeDate);
  }

  public int getId() {
    return id;
  }

  public int getAnimalId() {
    return animal_id;
  }

  public int getLocationId() {
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
      String sql = "INSERT INTO sightings (animal_id, animal_name, location_id, location_name, ranger_id, ranger_name, date_sighted) VALUES (:animal_id, :animal_name, :location_id, :location_name, :ranger_id, :ranger_name, :date_sighted);";
      this.id = (int) con.createQuery(sql, true)
        .addParameter("animal_id", this.animal_id)
        .addParameter("animal_name", this.animal_name)
        .addParameter("location_id", this.location_id)
        .addParameter("location_name", this.location_name)
        .addParameter("ranger_id", this.ranger_id)
        .addParameter("ranger_name", this.ranger_name)
        .addParameter("date_sighted", this.date_sighted)
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
  //
  // public static Sighting find(int id) {
  //   try(Connection con = DB.sql2o.open()) {
  //     String sql = "SELECT * FROM sightings WHERE id=:id;";
  //     Sighting sighting = con.createQuery(sql)
  //       .addParameter("id", id)
  //       .executeAndFetchFirst(Sighting.class);
  //     return sighting;
  //   } catch (IndexOutOfBoundsException exception) {
  //     return null;
  //   }
  // }

}
