package com.demo.neo4j.rest;

import com.demo.neo4j.dao.PersonRepository;
import com.demo.neo4j.dao.TeleplayRepository;
import com.demo.neo4j.entity.Person;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName Neo4jTestRest
 * @Deacription Neo4j测试rest
 * @Author GoldenStar
 * @Date 2021/10/14 10:33
 * @Version 1.0
 **/
@Api(tags = "Neo4j测试")
@RestController
@RequestMapping("/neo4j")
public class Neo4jTestRest {

    @Resource
    private TeleplayRepository templayRepository;

    @Resource
    private PersonRepository personRepository;

    @ApiOperation(value = "单个添加person")
    @RequestMapping(value = "/addPerson", method = RequestMethod.POST)
    public void addPerson() {
        Person person = new Person();
        person.setName("测试单个添加");
        person.setActor("测试单个添加");
        personRepository.save(person);
    }

    @ApiOperation(value = "批量添加person")
    @RequestMapping(value = "/addBatchPerson", method = RequestMethod.POST)
    public void addBatchPerson() {
        List<Person> list = new ArrayList<>();
        Person person = new Person();
        person.setName("测试批量添加1");
        person.setActor("测试批量添加1");
        list.add(person);

        person = new Person();
        person.setName("测试批量添加2");
        person.setActor("测试批量添加2");
        list.add(person);

        person = new Person();
        person.setName("测试批量添加3");
        person.setActor("测试批量添加3");
        list.add(person);

        person = new Person();
        person.setName("测试批量添加4");
        person.setActor("测试批量添加4");
        list.add(person);

        personRepository.saveAll(list);
    }

    @ApiOperation(value = "修改person")
    @RequestMapping(value = "/modifyPerson", method = RequestMethod.POST)
    public void modifyPerson() {
        Person person = personRepository.findByName("测试单个添加");

        personRepository.modifyById(person.getId(), "测试修改节点");
    }

    @ApiOperation(value = "查询person")
    @RequestMapping(value = "/getPerson", method = RequestMethod.POST)
    public void getPerson() {
        Person person = personRepository.findByName("测试修改节点");

        Person person1 = personRepository.findById(person.getId()).get();
        System.out.println("查询到的person为：" + person1);
    }

    @ApiOperation(value = "查询所有person")
    @RequestMapping(value = "/listPerson", method = RequestMethod.POST)
    public void listPerson() {
        List<Person> list = personRepository.findAll();
        System.out.println("查询所有条数：" + list.size());

        System.out.println("查询所有列表：");
        list.forEach(System.out::println);
    }

    @ApiOperation(value = "删除person")
    @RequestMapping(value = "/deletePerson", method = RequestMethod.POST)
    public void deletePerson() {
        // 单个删除
        Person person = personRepository.findByName("测试修改节点");
        personRepository.deleteById(person.getId());

        // 多个删除
        List<Long> list = new ArrayList<>();
        person = personRepository.findByName("测试批量添加1");
        list.add(person.getId());
        person = personRepository.findByName("测试批量添加2");
        list.add(person.getId());
        person = personRepository.findByName("测试批量添加3");
        list.add(person.getId());
        person = personRepository.findByName("测试批量添加4");
        list.add(person.getId());
        personRepository.deleteAllById(list);

        // 删除全部
        //personRepository.deleteAll();
    }

    @ApiOperation(value = "查询老九门所有角色")
    @RequestMapping(value = "/teleplayPersons", method = RequestMethod.POST)
    public void teleplayPersons() {
        List<Person> list = personRepository.queryAllPersonByTeleplayName("老九门");
        list.forEach(System.out::println);
    }

    @ApiOperation(value = "查询霍锦惜到霍秀秀最短路径")
    @RequestMapping(value = "/shortestpathPersons", method = RequestMethod.POST)
    public void shortestpathPersons() {
        List<Person> list = personRepository.queryshortPerson("霍锦惜", "霍秀秀");
        list.forEach(System.out::println);
    }
}
