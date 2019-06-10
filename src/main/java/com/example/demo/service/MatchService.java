package com.example.demo.service;

import com.example.demo.commands.MatchForm;
import com.example.demo.model.Match;

import java.util.List;
import java.util.UUID;

public interface MatchService {

    List<Match> listAll();

    Match getById(UUID id);

    Match saveOrUpdate(Match match);

    void delete(UUID id);

    Match saveOrUpdateMatchForm(MatchForm matchForm);
}
