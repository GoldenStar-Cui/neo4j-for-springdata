package com.demo.neo4j.dao;

import com.demo.neo4j.entity.Teleplay;
import org.springframework.data.neo4j.repository.Neo4jRepository;

public interface TeleplayRepository extends Neo4jRepository<Teleplay, Long> {

}
