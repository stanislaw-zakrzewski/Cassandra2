package com.example.demo.converters;

import com.example.demo.commands.MatchForm;
import com.example.demo.model.Match;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
public class MatchFormToMatch implements Converter<MatchForm, Match> {

    @Override
    public Match convert(MatchForm form) {
        Match match = new Match();
        if (form.getId() != null && !StringUtils.isEmpty(form.getId())) {
            match.setId(form.getId());
        }
        match.setLeagueDivision(form.getLeagueDivision());
        match.setDate(form.getDate());
        match.setHomeTeam(form.getHomeTeam());
        match.setAwayTeam(form.getAwayTeam());
        match.setReferee(form.getReferee());
        match.setFullTimeHomeGoals(form.getFullTimeHomeGoals());
        match.setFullTimeAwayGoals(form.getFullTimeAwayGoals());
        match.setFullTimeResult(form.getFullTimeResult());
        match.setHalfTimeHomeTeamGoals(form.getHalfTimeHomeTeamGoals());
        match.setHalfTimeAwayTeamGoals(form.getHalfTimeAwayTeamGoals());
        match.setHalfTimeResult(form.getHalfTimeResult());
        return match;
    }
}
