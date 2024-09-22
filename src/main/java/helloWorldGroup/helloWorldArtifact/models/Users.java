package helloWorldGroup.helloWorldArtifact.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Table
@NoArgsConstructor
public class Users {
  @Id
  private String id;
  private String name;
  private String surname;
  private String username;
  private String email;
  private String password;
}