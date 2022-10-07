package com.project.footballers;

import java.util.Set;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;




@Entity
@Table(name="Team")
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Integer id;
    public String name;
     
    @OneToMany(mappedBy = "team")
    public Set<Player> players;

    public Team(){
    }
}

@RestController
class TeacherController {

  @Autowired
  private TeamRepository teamRepository;

  @GetMapping("/teams")
  public List<Team> getAllTeams() {
    return teamRepository.findAll();
  }

  @PostMapping("/teams")
  public Team createTeam(@RequestBody Team newteam) {
    return teamRepository.save(newteam);
  }
}

interface TeamRepository extends JpaRepository<Team, Integer> {
}
