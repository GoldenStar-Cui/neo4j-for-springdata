package com.demo.neo4j.dao;

import com.demo.neo4j.entity.Person;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;

import java.util.List;

public interface PersonRepository extends Neo4jRepository<Person, Long> {

    /**
     * 根据名称查询节点
     *
     * @param name 名称
     * @return Person
     */
    Person findByName(String name);

    @Query("MATCH (n) WHERE id(n) = {0} SET n.name = {1}")
    void modifyById(long id, String name);

    /**
     * 根据电视剧名称查询所有角色
     *
     * @param teleplayName 电视剧名称
     * @return List<Person>
     */
    @Query("match (:teleplay{name:{0}})-[:角色]->(n:person) return n")
    List<Person> queryAllPersonByTeleplayName(String teleplayName);

    @Query("match p=shortestpath((:person{name:{0}})-[*]->(:person{name:{1}})) return p")
    List<Person> queryshortPerson(String beginName, String endName);
}
