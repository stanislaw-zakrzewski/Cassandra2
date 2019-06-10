package com.example.demo.controller;

import com.example.demo.commands.MatchForm;
import com.example.demo.model.Match;
import com.example.demo.service.MatchService;
import data.DataInsertApp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
public class MatchController {
    private MatchService matchService;

    @Autowired
    public void setMatchService(MatchService matchService) {
        this.matchService = matchService;
    }



    @GetMapping("/matches")
    public List<Match> getMatches() {
        return matchService.listAll();
    }

    @GetMapping("/initialize")
    public String initialize() { return DataInsertApp.insert(matchService); }

    @GetMapping("/match/{id}")
    public Match getMatch(@PathVariable UUID id) {
        return matchService.getById(id);
    }

    @PutMapping("/match/{id}")
    public Match updateMatch(@RequestBody MatchForm form) {
        return matchService.saveOrUpdateMatchForm(form);
    }

    @DeleteMapping(value = "/match/{id}", produces = "application/json; charset=utf-8")
    public boolean deleteMatch(@PathVariable UUID id) {
        matchService.delete(id);
        return true;
    }

    @PostMapping("/match")
    public Match addMatch(@RequestBody MatchForm form) {
        return matchService.saveOrUpdateMatchForm(form);
    }

    //MORE ADVANCED REST

    /**
     * It returns list of all matches in given league
     * @param league Name of league
     * @return List of matches in given league
     */
    @GetMapping("/getMatchesInLeague/{league}")
    public List<Match> getMatchesInLeague(@PathVariable String league) {
        return matchService.listAll().stream().filter(m -> m.getLeagueDivision().equals(league)).collect(Collectors.toList());
    }

    /**
     * It returns list of all matches for given team
     * @param team Name of team
     * @return List of matches for given team
     */
    @GetMapping("/getMatchesOfTeam/{team}")
    public List<Match> getMatchesOfTeam(@PathVariable String team) {
        return matchService.listAll().stream().filter(m -> m.getAwayTeam().equals(team) || m.getHomeTeam().equals(team)).collect(Collectors.toList());
    }

    /**
     * It returns list of all matches for given referee
     * @param referee Name of referee
     * @return List of matches for given referee
     */
    @GetMapping("/getMatchesForReferee/{referee}")
    public List<Match> getMatchesForReferee(@PathVariable String referee) {
        return matchService.listAll().stream().filter(m -> m.getReferee().equals(referee)).collect(Collectors.toList());
    }

    /**
     * It returns list of all matches where hosts got more goals than given number
     * @param goals Number of goals
     * @return List of matches where hosts got more goals than given
     */
    @GetMapping("/getAllMatchesWhereHostsGotMoreGoalsThan/{goals}")
    public List<Match> getAllMatchesWhereHostsGotMoreGoalsThan(@PathVariable int goals) {
        return matchService.listAll().stream().filter(m -> m.getFullTimeHomeGoals() > goals).collect(Collectors.toList());
    }

    /**
     * It returns list of all matches where guests got more goals than given number
     * @param goals Number of goals
     * @return List of matches where guests got more goals than given
     */
    @GetMapping("/getAllMatchesWhereGuestsGotMoreGoalsThan/{goals}")
    public List<Match> getAllMatchesWhereGuestsGotMoreGoalsThan(@PathVariable int goals) {
        return matchService.listAll().stream().filter(m -> m.getFullTimeAwayGoals() > goals).collect(Collectors.toList());
    }

    /**
     * It returns the biggest number of lost goals by given team
     * @param team Name of team
     * @return The biggest number of lost goals
     */
    @GetMapping("/getMostGoalsLostByTeam/{team}")
    public int getMostGoalsLostByTeam(@PathVariable String team) {
        int goals = matchService.listAll().stream().filter(m -> m.getHomeTeam().equals(team)).max(Comparator.comparing(Match::getFullTimeAwayGoals)).get().getFullTimeAwayGoals();
        int goals2 = matchService.listAll().stream().filter(m -> m.getAwayTeam().equals(team)).max(Comparator.comparing(Match::getFullTimeHomeGoals)).get().getFullTimeHomeGoals();
        return Math.max(goals, goals2);
    }

    /**
     * It returns the biggest number of scored goals by given team
     * @param team Name of team
     * @return The biggest number of scored goals
     */
    @GetMapping("/getMostGoalsScoredByTeam/{team}")
    public int getMostGoalsScoredByTeam(@PathVariable String team) {
        int goals = matchService.listAll().stream().filter(m -> m.getAwayTeam().equals(team)).max(Comparator.comparing(Match::getFullTimeAwayGoals)).get().getFullTimeAwayGoals();
        int goals2 = matchService.listAll().stream().filter(m -> m.getHomeTeam().equals(team)).max(Comparator.comparing(Match::getFullTimeHomeGoals)).get().getFullTimeHomeGoals();
        return Math.max(goals, goals2);
    }

    /**
     * It returns match witch biggest number of scored goals in given league
     * @param league Name of league
     * @return List of math with biggest number of scored goals
     */
    @GetMapping("/getMathWithMostGoalsScoredInLeague/{league}")
    public List<Match> getMathWithMostGoalsScoredInLeague(@PathVariable String league){
        final int goals = matchService.listAll().stream().filter(m -> m.getLeagueDivision().equals(league)).max(Comparator.comparing(Match::getFullTimeAwayGoals)).get().getFullTimeAwayGoals();
        final int goals2 = matchService.listAll().stream().filter(m -> m.getLeagueDivision().equals(league) && m.getFullTimeAwayGoals() == goals).max(Comparator.comparing(Match::getFullTimeHomeGoals)).get().getFullTimeHomeGoals();
        final int resolt = goals+goals2;
        return matchService.listAll().stream().filter(m -> m.getFullTimeHomeGoals() + m.getFullTimeAwayGoals() == resolt && m.getLeagueDivision().equals(league)).collect(Collectors.toList());
    }
}