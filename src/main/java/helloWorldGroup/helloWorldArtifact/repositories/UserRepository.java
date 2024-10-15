package helloWorldGroup.helloWorldArtifact.repositories;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

import helloWorldGroup.helloWorldArtifact.models.Users;

@Repository
public interface UserRepository extends ReactiveCrudRepository<Users, Long> {
  
}
