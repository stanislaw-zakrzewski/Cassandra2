package com.example.demo.service;

import com.example.demo.commands.MatchForm;
import com.example.demo.converters.MatchFormToMatch;
import com.example.demo.model.Match;
import com.example.demo.repository.MatchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class MatchServiceImplementation implements MatchService{

    private MatchRepository matchRepository;
    private MatchFormToMatch matchFormToMatch;

    @Autowired
    public void setMatchRepository(MatchRepository matchRepository) {
        this.matchRepository = matchRepository;
    }

    @Autowired
    public void setMatchFormToMatch(MatchFormToMatch matchFormToMatch) {
        this.matchFormToMatch = matchFormToMatch;
    }

    @Override
    public List<Match> listAll() {
        List<Match> matches = new ArrayList<>();
        matchRepository.findAll().forEach(matches::add);
        return matches;
    }

    @Override
    public Match getById(UUID id) {
        return matchRepository.findById(id).orElse(null);
    }

    @Override
    public Match saveOrUpdate(Match match) {
        matchRepository.save(match);
        return match;
    }

    @Override
    public void delete(UUID id) {
        matchRepository.deleteById(id);
    }

    @Override
    public Match saveOrUpdateMatchForm(MatchForm matchForm) {
        return saveOrUpdate(matchFormToMatch.convert(matchForm));
    }
}
