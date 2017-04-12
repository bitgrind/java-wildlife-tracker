import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;
import static spark.Spark.*;

public class App {
  public static void main(String[] args) {
    staticFileLocation("/public");
    String layout = "templates/layout.vtl";

    get("/", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      model.put("animals", Animal.all());
      model.put("rangers", Ranger.all());
      model.put("locations", Location.all());
      model.put("sights", Sighting.all());
      //model.put("endangeredAnimals", EndangeredAnimal.all());
      model.put("template", "templates/index.vtl");
      model.put("header", "templates/header.vtl");
      model.put("all-animals", "templates/all-animals.vtl");
      model.put("all-rangers", "templates/all-rangers.vtl");
      model.put("allsightings", "templates/allsightings.vtl");
      model.put("all-locations", "templates/all-locations.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/ranger/new", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      model.put("rangers", Ranger.all());
      model.put("template", "templates/index.vtl");
      model.put("add-ranger", "templates/add-ranger.vtl");
      model.put("header", "templates/header.vtl");
      model.put("all-rangers", "templates/all-rangers.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/ranger/new", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      model.put("rangers", Ranger.all());
      String rangerName = request.queryParams("ranger-name");
      Ranger newRanger = new Ranger(rangerName);
      newRanger.save();
      model.put("template", "templates/index.vtl");
      model.put("header", "templates/header.vtl");
      model.put("add-ranger", "templates/add-ranger.vtl");
      model.put("all-rangers", "templates/all-rangers.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/ranger/:id", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      Ranger ranger = Ranger.find(Integer.parseInt(request.params("id")));
      model.put("ranger", ranger);
      model.put("rangers", Ranger.all());
      model.put("ranger-sightings", ranger.getSightings());
      model.put("animals", Animal.all());
      model.put("template", "templates/ranger.vtl");
      model.put("header", "templates/header.vtl");
      model.put("all-rangers", "templates/all-rangers.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/animal/new", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      model.put("template", "templates/index.vtl");
      model.put("add-animal", "templates/add-animal.vtl");
      model.put("header", "templates/header.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/animal/new", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      boolean endangered = request.queryParamsValues("endangered")!=null;
      model.put("header", "templates/header.vtl");
      if (endangered) {
        String name = request.queryParams("name");
        String health = request.queryParams("health");
        String age = request.queryParams("age");
        EndangeredAnimal endangeredAnimal = new EndangeredAnimal(name, health, age);
        endangeredAnimal.save();
        model.put("animals", Animal.all());
        model.put("endangeredAnimals", EndangeredAnimal.all());
      } else {
        String name = request.queryParams("name");
        Animal animal = new Animal(name);
        animal.save();
        model.put("animals", Animal.all());
        model.put("endangeredAnimals", EndangeredAnimal.all());
      }
    response.redirect("/");
      return null;
    });

    get("/animal/:id", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      Animal animal = Animal.find(Integer.parseInt(request.params("id")));
      model.put("animal", animal);
      model.put("template", "templates/animal.vtl");
      model.put("header", "templates/header.vtl");
      model.put("sighting", "templates/endangered-sighting.vtl");
      model.put("sighting", "templates/nonendangered-sighting.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/sightings", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      //model.put("sights", Sighting.all());
      model.put("animals", Animal.all());
      model.put("rangers", Ranger.all());
      model.put("locations", Location.all());
      model.put("allsightings", "templates/allsightings.vtl");
      model.put("header", "templates/header.vtl");
      model.put("add-sighting", "templates/add-sighting.vtl");
      model.put("template", "templates/index.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/sightings/:id", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      model.put("sights", Sighting.all());
      model.put("animals", Animal.all());
      model.put("rangers", Ranger.all());
      model.put("locations", Location.all());
      model.put("allsightings", "templates/allsightings.vtl");
      model.put("header", "templates/header.vtl");
      model.put("add-sighting", "templates/add-sighting.vtl");
      model.put("template", "templates/index.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/sighting/new", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      String ranger_name = request.queryParams("ranger_name");
      String animal_name = request.queryParams("animal_name");
      String location_name = request.queryParams("location_name");
      Sighting newSighting = new Sighting(animal_name, location_name, ranger_name);
      newSighting.save();
      response.redirect("/sightings");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/sightings/new/:id", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      String ranger_name = request.queryParams("ranger_name");
      String animal_name = request.queryParams("animal_name");
      String location_name = request.queryParams("location_name");
      Sighting sighting = new Sighting(animal_name, location_name, ranger_name);
      sighting.save();
      model.put("sighting", sighting);
      model.put("animals", Animal.all());
      //String animal = Animal.find(animal_id).getName();
      //model.put("animal", animal);
      model.put("template", "templates/success.vtl");
      model.put("header", "templates/header.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/location", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      model.put("animals", Animal.all());
      model.put("rangers", Ranger.all());
      model.put("endangeredAnimals", EndangeredAnimal.all());
      model.put("header", "templates/header.vtl");
      model.put("add-location", "templates/add-location.vtl");
      model.put("template", "templates/index.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/location/new", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      model.put("animals", Animal.all());
      model.put("rangers", Ranger.all());
      model.put("endangeredAnimals", EndangeredAnimal.all());
      model.put("header", "templates/header.vtl");
      model.put("add-location", "templates/add-location.vtl");
      model.put("template", "templates/index.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/location/new", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      String locationName = request.queryParams("locationName");
      Location newLocation = new Location(locationName);
      newLocation.save();
      response.redirect("/");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/endangered_animal/:id", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      EndangeredAnimal endangeredAnimal = EndangeredAnimal.find(Integer.parseInt(request.params("id")));
      model.put("endangeredAnimal", endangeredAnimal);
      model.put("template", "templates/endangered_animal.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/error", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      model.put("template", "templates/error.vtl");
      model.put("header", "templates/header.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());
  }
}
