package Models;

import Models.Enums.Gender;
import Models.Enums.Status;

import java.util.ArrayList;
import java.util.Objects;

public class Character {
  private int id;
  private String name;
  private Status status;
  private Gender gender;
  private String species;
  private String type;
  private String origin;
  private ArrayList<String> episodes;

  // Constructor
  public Character(int id, String name, Status status, Gender gender, String species, String type, String origin,
      ArrayList<String> episodes) {
    this.id = id;
    this.name = name;
    this.status = status;
    this.gender = gender;
    this.species = species;

    if (Objects.equals(type, ""))
      this.type = "sin tipo";
    else
      this.type = type;

    this.origin = origin;
    this.episodes = episodes;
  }

  public int getId() {
    return this.id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Status getStatus() {
    return status;
  }

  public void setStatus(Status status) {
    this.status = status;
  }

  public Gender getGender() {
    return gender;
  }

  public void setGender(Gender gender) {
    this.gender = gender;
  }

  public String getSpecies() {
    return species;
  }

  public void setSpecies(String species) {
    this.species = species;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public String getOrigin() {
    return origin;
  }

  public void setOrigin(String origin) {
    this.origin = origin;
  }

  public ArrayList<String> getEpisodes() {
    return episodes;
  }

  public void setEpisodes(ArrayList<String> episodes) {
    this.episodes = episodes;
  }

  public void addEpisode(String episode) {
    this.episodes.add(episode);
  }

  public void print() {
    System.out.println(
        this.getId() + " - " + this.getName() + " "
            + this.getStatus() + " " + this.getGender() + " - "
            + this.getSpecies() + "/" + this.getType() + " - " + this.getOrigin());
  }
}
