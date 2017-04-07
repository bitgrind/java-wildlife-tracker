import org.junit.*;
import static org.junit.Assert.*;
import org.sql2o.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

public class RangerTest {
  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void ranger_instantiatesCorrectly_ture() {
    Ranger testRanger = new Ranger("Dave");
    assertEquals(true, testRanger instanceof Ranger);
  }

  @Test
  public void getName_animalInstantiatesWithName_Deer() {
    Ranger testRanger = new Ranger("Dave");
    assertEquals("Dave", testRanger.getName());
  }
}
