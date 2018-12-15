package com.gameondigital.gameonapp.Model;

import android.text.TextUtils;
import android.util.Patterns;

import com.gameondigital.gameonapp.dao.FirebaseConfiguration;
import com.google.firebase.database.DatabaseReference;

/**
 * Created by pmoreirr on 22/08/2017.
 */

public class User {

    private String id;
    private String name;
    private String email;
    private String password;
    private String platformer;
    private String game;
    private String psn;
    private String live;
    private String steam;
    private String country;
    private String state;
    private Long first_place;
    private Long second_place;
    private Long third_place;
    private Long wins;
    private Long draws;
    private Long losses;

    public User() {
    }

    public void save(){
        DatabaseReference databaseReference = FirebaseConfiguration.getFirebase();
        databaseReference.child("user").child(String.valueOf(getId())).setValue(this);
        //databaseReference.child("match").child(String.valueOf(getId())).setValue(this);
    }

    /*public User(String id, String name, String email, String password) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
    }*/

    public User(String id, String name, String email, String psn) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.psn = psn;
    }

    public User(String id, String name, String email, String password, String platformer, String game, String psn, String live, String steam, String country, String state) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.platformer = platformer;
        this.game = game;
        this.psn = psn;
        this.live = live;
        this.steam = steam;
        this.country = country;
        this.state = state;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPlatformer() {
        return platformer;
    }

    public void setPlatformer(String platformer) {
        this.platformer = platformer;
    }

    public String getGame() {
        return game;
    }

    public void setGame(String game) {
        this.game = game;
    }

    public String getPsn() {
        return psn;
    }

    public void setPsn(String psn) {
        this.psn = psn;
    }

    public String getLive() {
        return live;
    }

    public void setLive(String live) {
        this.live = live;
    }

    public String getSteam() {
        return steam;
    }

    public void setSteam(String steam) {
        this.steam = steam;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Long getFirst_place() {
        return first_place;
    }

    public void setFirst_place(Long first_place) {
        this.first_place = first_place;
    }

    public Long getSecond_place() {
        return second_place;
    }

    public void setSecond_place(Long second_place) {
        this.second_place = second_place;
    }

    public Long getThird_place() {
        return third_place;
    }

    public void setThird_place(Long third_place) {
        this.third_place = third_place;
    }

    public Long getWins() {
        return wins;
    }

    public void setWins(Long wins) {
        this.wins = wins;
    }

    public Long getDraws() {
        return draws;
    }

    public void setDraws(Long draws) {
        this.draws = draws;
    }

    public Long getLosses() {
        return losses;
    }

    public void setLosses(Long losses) {
        this.losses = losses;
    }
}
