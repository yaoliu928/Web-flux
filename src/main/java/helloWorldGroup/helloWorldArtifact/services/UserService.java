package helloWorldGroup.helloWorldArtifact.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import helloWorldGroup.helloWorldArtifact.models.Users;
import helloWorldGroup.helloWorldArtifact.repositories.UserRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class UserService {

  @Autowired
  UserRepository userRepository;

  // Mono 0 - 1 // single
  // Flux 0 - N // reactive sequence of items

  public Mono<Users> getUserById(Long id) {
    return userRepository.findById(id);
  }

  public Flux<Users> getAllUsers() {
    return userRepository.findAll();
  }

  public void saveUser(Users users) {
    userRepository.save(users).subscribe();
  }

  public Mono<Users> updateUser(Users user) {
    return userRepository.findById(user.getId())
        // if user not exist, throw exception
        .switchIfEmpty(Mono.error(new Exception("User not found")))
        .map(olderUser -> {
          if (user.getSurname() != null)
            olderUser.setSurname(user.getSurname());
          if (user.getUsername() != null)
            olderUser.setUsername(user.getUsername());
          if (user.getEmail() != null)
            olderUser.setEmail(user.getEmail());
          if (user.getName() != null)
            olderUser.setName(user.getName());
          return olderUser;
        })
        .flatMap(userRepository::save);
  }

  public Mono<Void> deleteUser(Long id) {
    return userRepository.deleteById(id)
        .switchIfEmpty(Mono.error(new Exception("User not found")));
  }
}