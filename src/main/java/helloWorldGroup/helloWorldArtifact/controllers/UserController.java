package helloWorldGroup.helloWorldArtifact.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import helloWorldGroup.helloWorldArtifact.models.Users;
import helloWorldGroup.helloWorldArtifact.services.UserService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api")
public class UserController {

  @Autowired
  UserService userService;

  @GetMapping(value = "/users", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
  @ResponseStatus(HttpStatus.ACCEPTED)
  public Flux<Users> findAllUsers() {
    return userService.getAllUsers();
  }

  @GetMapping("/users/{id}")
  public Mono<Users> findUserById(@PathVariable Long id) {
    return userService.getUserById(id);
  }

  @PostMapping("/save")
  @ResponseStatus(HttpStatus.CREATED)
  public void saveUser(@RequestBody Users users) {
    userService.saveUser(users);
  }

  @PutMapping("/update")
  @ResponseStatus(HttpStatus.OK)
  public Mono<Users> updateUser(@RequestBody Users user) {
    return userService.updateUser(user);
  }



}
