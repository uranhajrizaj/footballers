package com.project.footballers;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GeneratorType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="Player")
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Integer id;
    public String name;
    public String nationality;
    public Integer scoreOutOfTen;
    public Boolean isReplacement;


    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "teamId", nullable = true)
    public Team team;
    public Player(){
    }
}

@RestController
class StudentController {

  @Autowired
  private PlayerRepository playerRepository;
  
  @Autowired
  private TeamRepository teamRepository;

  @GetMapping("/players")
  public List<Player> getaAllpPlayers() {
    return playerRepository.findAll();
  }

  @PostMapping("/teams/{teamId}/players")
  public Player createTeam(@RequestBody Player newplayer,@PathVariable Integer teamId) {
    newplayer.team=teamRepository.findById(teamId).get();
    return playerRepository.save(newplayer);
  }
}

interface PlayerRepository extends JpaRepository<Player, Integer> {
}

interface TeamRepository extends JpaRepository<Team, Integer> {
}

