package com.qf.testmemcached.testmemcached.service;

import com.qf.testmemcached.testmemcached.domain.Person;
import com.qf.testmemcached.testmemcached.repository.PersonRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author gpotes
 *
 */
@Component
@Transactional
public class PersonService implements IPersonService
{
    private PersonRepository personRepository;
    private final String PERSON = "person";
    @Autowired
    public PersonService(final PersonRepository personRepository)
    {
        this.personRepository = personRepository;
    }

    @Override
    public List<Person> findAll()
    {
        return personRepository.findAll();
    }
    @Cacheable(value = PERSON,key = "#person.id")
    @Override
    public Person save(final Person person)
    {
        return personRepository.save(person);
    }

    @Cacheable(value = PERSON)
    @Override
    public Person findById(final String id) {
        return personRepository.findById(id);
    }

}
