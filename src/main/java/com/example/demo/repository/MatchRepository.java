package com.example.demo.repository;

import com.example.demo.model.Match;
import org.springframework.data.cassandra.repository.CassandraRepository;

import java.util.UUID;

public interface MatchRepository extends CassandraRepository<Match, UUID> {

}