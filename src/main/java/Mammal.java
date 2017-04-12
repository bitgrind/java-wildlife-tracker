import org.sql2o.*;
import java.util.ArrayList;
import java.util.List;

public abstract class Mammal{
  public String name;
  public int id;

  public String getName() {
    return name;
  }

  public int getId() {
    return id;
  }
}
