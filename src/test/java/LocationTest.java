import org.junit.*;
import static org.junit.Assert.*;
import org.sql2o.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

public class LocationTest {
  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void ranger_instantiatesCorrectly_ture() {
    Location testLocation = new Location("River Bank");
    assertEquals(true, testLocation instanceof Location);
  }

  @Test
  public void getName_animalInstantiatesWithName_Deer() {
    Ranger testRanger = new Ranger("Dave");
    assertEquals("Dave", testRanger.getName());
  }

}
