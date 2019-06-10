package springboot.converters;

import com.example.demo.commands.MatchForm;
import com.example.demo.model.Match;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class MatchToMatchForm implements Converter<Match, MatchForm> {
    @Override
    public MatchForm convert(Match match) {
        MatchForm matchForm = new MatchForm();
        matchForm.setId(match.getId());
        matchForm.setLeagueDivision(match.getLeagueDivision());
        matchForm.setDate(match.getDate());
        matchForm.setHomeTeam(match.getHomeTeam());
        matchForm.setAwayTeam(match.getAwayTeam());
        matchForm.setReferee(match.getReferee());
        matchForm.setFullTimeHomeGoals(match.getFullTimeHomeGoals());
        matchForm.setFullTimeAwayGoals(match.getFullTimeAwayGoals());
        matchForm.setFullTimeResult(match.getFullTimeResult());
        matchForm.setHalfTimeHomeTeamGoals(match.getHalfTimeHomeTeamGoals());
        matchForm.setHalfTimeAwayTeamGoals(match.getHalfTimeAwayTeamGoals());
        matchForm.setHalfTimeResult(match.getHalfTimeResult());
        return matchForm;
    }
}
