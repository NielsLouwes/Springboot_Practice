package nl.nielslouwes.testingSpringbootOne.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class UserController {

    //attribute
    private List<String> users = new ArrayList<>();

    //constructor - create the arrayList with preloaded names
    public UserController() {
        users.add("Niels");
        users.add("Steven");
        users.add("Marly");
        users.add("Sarah");
    }

    //get ALL users
    @GetMapping(value = "/users")
    public List<String> getNames() {
        return users;
    }

    //get a single user
    @GetMapping(value = "/users/{id}")
    public String getName(@PathVariable int id) {
        return users.get(id);
    }

    //deleting a user
    @DeleteMapping(value = "/users/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public String deleteUser(@PathVariable int id) {
        users.remove(id);
        return "Deleted!";
    }

    //Adding a user
    @PostMapping(value = "/users")
    @ResponseStatus(HttpStatus.CREATED)
    public String addUser(@RequestBody String name) {
        users.add(name);
        return "User added!";
    }
}
