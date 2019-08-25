package com.fifarm.spider.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Objects;

@Entity
@JsonDeserialize(using = PlayerDeserializer.class)
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String lastName;
    private String position;

    private int height;
    private String birthdate;
    private int weight;
    private int age;

    private String leagueName;
    private String leagueImgUrl;

    private String nationName;
    private String nationImgUrl;

    private String clubName;
    private String clubImgUrl;

    private String headshotImgUrl;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getLeagueName() {
        return leagueName;
    }

    public void setLeagueName(String leagueName) {
        this.leagueName = leagueName;
    }

    public String getLeagueImgUrl() {
        return leagueImgUrl;
    }

    public void setLeagueImgUrl(String leagueImgUrl) {
        this.leagueImgUrl = leagueImgUrl;
    }

    public String getNationName() {
        return nationName;
    }

    public void setNationName(String nationName) {
        this.nationName = nationName;
    }

    public String getNationImgUrl() {
        return nationImgUrl;
    }

    public void setNationImgUrl(String nationImgUrl) {
        this.nationImgUrl = nationImgUrl;
    }

    public String getClubName() {
        return clubName;
    }

    public void setClubName(String clubName) {
        this.clubName = clubName;
    }

    public String getClubImgUrl() {
        return clubImgUrl;
    }

    public void setClubImgUrl(String clubImgUrl) {
        this.clubImgUrl = clubImgUrl;
    }

    public String getHeadshotImgUrl() {
        return headshotImgUrl;
    }

    public void setHeadshotImgUrl(String headshotImgUrl) {
        this.headshotImgUrl = headshotImgUrl;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return height == player.height &&
                weight == player.weight &&
                age == player.age &&
                firstName.equals(player.firstName) &&
                lastName.equals(player.lastName) &&
                position.equals(player.position) &&
                birthdate.equals(player.birthdate) &&
                leagueName.equals(player.leagueName) &&
                nationName.equals(player.nationName) &&
                clubName.equals(player.clubName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, position, height, birthdate, weight, age, leagueName, nationName, clubName);
    }

    @Override
    public String toString() {
        return "Player{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", position='" + position + '\'' +
                ", height=" + height +
                ", birthdate='" + birthdate + '\'' +
                ", weight=" + weight +
                ", age=" + age +
                ", leagueName='" + leagueName + '\'' +
                ", leagueImgUrl='" + leagueImgUrl + '\'' +
                ", nationName='" + nationName + '\'' +
                ", nationImgUrl='" + nationImgUrl + '\'' +
                ", clubName='" + clubName + '\'' +
                ", clubImgUrl='" + clubImgUrl + '\'' +
                ", headshotImgUrl='" + headshotImgUrl + '\'' +
                '}';
    }
}
