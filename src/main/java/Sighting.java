import org.sql2o.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.time.*;
import java.text.SimpleDateFormat;

public class Sighting {
  private int id;
  private String animal_name;
  private String location_name;
  private String ranger_name;
  private String date_sighted;

  public Sighting(String animal_name, String location_name, String ranger_name) {
    this.animal_name = animal_name;
    this.location_name = location_name;
    this.ranger_name = ranger_name;
    Date makeDate = new Date();
    this.date_sighted = new SimpleDateFormat("MM-dd-yyyy").format(makeDate);
  }

  public int getId() {
    return id;
  }

  public String getAnimalName() {
    return animal_name;
  }

  public String getLocationName() {
    return location_name;
  }

  public String getRangerName() {
    return ranger_name;
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
      String sql = "INSERT INTO sightings (animal_name, location_name, ranger_name, date_sighted) VALUES (:animal_name, :location_name, :ranger_name, :date_sighted);";
      this.id = (int) con.createQuery(sql, true)
        .addParameter("animal_name", this.animal_name)
        .addParameter("location_name", this.location_name)
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
}
