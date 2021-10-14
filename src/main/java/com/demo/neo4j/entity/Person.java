package com.demo.neo4j.entity;

import lombok.Data;
import lombok.ToString;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.util.List;

/**
 * @ClassName Person
 * @Deacription Person实体
 * @Author GoldenStar
 * @Date 2021/10/14 11:25
 * @Version 1.0
 **/
@Node("person")
@Data
@ToString
public class Person {

    @Id
    @GeneratedValue
    private long id;

    private String name;

    private String actor;

    private String level;

    @Relationship(type = "夫妻", direction = Relationship.Direction.INCOMING)
    private List<Person> teleplayRoleList;

    public Person() {
    }

    public Person(String name, String actor, String level) {
        this.name = name;
        this.actor = actor;
        this.level = level;
    }
}
