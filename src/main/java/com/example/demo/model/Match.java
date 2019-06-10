package com.example.demo.model;

import com.datastax.driver.core.DataType;
import org.springframework.data.cassandra.core.mapping.CassandraType;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

@Table("matches")
public class Match {

    @PrimaryKey
    @CassandraType(type = DataType.Name.UUID)
    private UUID id;

    private String leagueDivision;
    private Date date;
    private String homeTeam;
    private String awayTeam;
    private String referee;
    private int fullTimeHomeGoals;
    private int fullTimeAwayGoals;
    private String fullTimeResult;
    private int halfTimeHomeTeamGoals;
    private int halfTimeAwayTeamGoals;
    private String halfTimeResult;

    public Match() {
        id = UUID.randomUUID();
    }

    public UUID getId() {
        return id;
    }

    public String getLeagueDivision() {
        return leagueDivision;
    }

    public Date getDate() {
        return date;
    }

    public String getHomeTeam() {
        return homeTeam;
    }

    public String getAwayTeam() {
        return awayTeam;
    }

    public String getReferee() {
        return referee;
    }

    public int getFullTimeHomeGoals() {
        return fullTimeHomeGoals;
    }

    public int getFullTimeAwayGoals() {
        return fullTimeAwayGoals;
    }

    public String getFullTimeResult() {
        return fullTimeResult;
    }

    public int getHalfTimeHomeTeamGoals() {
        return halfTimeHomeTeamGoals;
    }

    public int getHalfTimeAwayTeamGoals() {
        return halfTimeAwayTeamGoals;
    }

    public String getHalfTimeResult() {
        return halfTimeResult;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public void setLeagueDivision(String leagueDivision) {
        this.leagueDivision = leagueDivision;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setHomeTeam(String homeTeam) {
        this.homeTeam = homeTeam;
    }

    public void setAwayTeam(String awayTeam) {
        this.awayTeam = awayTeam;
    }

    public void setReferee(String referee) {
        this.referee = referee;
    }

    public void setFullTimeHomeGoals(int fullTimeHomeGoals) {
        this.fullTimeHomeGoals = fullTimeHomeGoals;
    }

    public void setFullTimeAwayGoals(int fullTimeAwayGoals) {
        this.fullTimeAwayGoals = fullTimeAwayGoals;
    }

    public void setFullTimeResult(String fullTimeResult) {
        this.fullTimeResult = fullTimeResult;
    }

    public void setHalfTimeHomeTeamGoals(int halfTimeHomeTeamGoals) {
        this.halfTimeHomeTeamGoals = halfTimeHomeTeamGoals;
    }

    public void setHalfTimeAwayTeamGoals(int halfTimeAwayTeamGoals) {
        this.halfTimeAwayTeamGoals = halfTimeAwayTeamGoals;
    }

    public void setHalfTimeResult(String halfTimeResult) {
        this.halfTimeResult = halfTimeResult;
    }
}