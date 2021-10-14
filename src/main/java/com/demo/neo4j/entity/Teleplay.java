package com.demo.neo4j.entity;


import lombok.Data;
import lombok.ToString;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.util.List;

/**
 * @ClassName Teleplay
 * @Deacription 电视剧实体
 * @Author GoldenStar
 * @Date 2021/11/14 11:03
 * @Version 1.0
 **/
@Node("teleplay")
@Data
@ToString
public class Teleplay {

    @Id
    @GeneratedValue
    private long id;

    private String name;

    private String author;

    private String bron;

    @Relationship(type = "角色", direction = Relationship.Direction.INCOMING)
    private List<Person> teleplayRoleList;

    public Teleplay() {
    }

    public Teleplay(String name, String author, String bron) {
        this.name = name;
        this.author = author;
        this.bron = bron;
    }
}
