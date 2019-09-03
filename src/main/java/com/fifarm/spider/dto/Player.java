package com.fifarm.spider.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fifarm.spider.dto.deserializer.PlayerDeserializer;

import javax.persistence.*;
import java.util.Objects;

@Entity
@JsonDeserialize(using = PlayerDeserializer.class)
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="league_id")
    private League league;

    @ManyToOne
    @JoinColumn(name="nation_id")
    private Nation nation;

    @ManyToOne
    @JoinColumn(name="club_id")
    private Club club;

    private String firstName;
    private String lastName;
    private String position;

    private int height;
    private String birthdate;
    private int weight;
    private int age;

    private String headshotImgUrl;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public League getLeague() {
        return league;
    }

    public void setLeague(League league) {
        this.league = league;
    }

    public Nation getNation() {
        return nation;
    }

    public void setNation(Nation nation) {
        this.nation = nation;
    }

    public Club getClub() {
        return club;
    }

    public void setClub(Club club) {
        this.club = club;
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
                birthdate.equals(player.birthdate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, position, height, birthdate, weight, age);
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
                ", headshotImgUrl='" + headshotImgUrl + '\'' +
                '}';
    }

}
