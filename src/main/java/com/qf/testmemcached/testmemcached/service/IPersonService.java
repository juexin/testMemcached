package com.qf.testmemcached.testmemcached.service;

import com.qf.testmemcached.testmemcached.domain.Person;

import java.util.List;

/**
 * @author gpotes
 *
 */
public interface IPersonService
{
    List<Person> findAll();
    
    Person findById(String id);
    
    Person save(Person person);
}
