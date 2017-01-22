package com.qf.testmemcached.testmemcached.app;

import com.qf.testmemcached.testmemcached.resources.PersonResource;
import com.qf.testmemcached.testmemcached.service.IPersonService;
import com.qf.testmemcached.testmemcached.service.PersonService;
import com.qf.testmemcached.testmemcached.domain.Person;

import java.net.URI;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.qf.testmemcached.testmemcached.util.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author gpotes
 *
 */
@RestController
@Component
public class PersonController
{
    private IPersonService personService;

    /**
     * @param personService
     */
    @Autowired
    PersonController(
        final PersonService personService)
    {
        this.personService = personService;
    }

    /**
     * @return
     */
    @RequestMapping("/persons")
    public List<Person> findAll()
    {
        return personService.findAll();
    }

    /**
     * @return
     */
    @RequestMapping("/person/")
    public Person findById(@RequestParam(value = "id") final String id)
    {
        return personService.findById(id);
    }

    /**
     * @return
     */
    @RequestMapping(value = "/saveperson", method = RequestMethod.POST)
    public List<Person> save(HttpServletRequest request)
    {
        Map m= request.getParameterMap();
        Person person = JsonUtils.toObject((String) m.keySet().iterator().next(),Person.class);
        Person savedPerson = personService.save(person);
        HttpHeaders httpHeaders = new HttpHeaders();
        
//        Link forOnePerson = new PersonResource(savedPerson).getLink("self");
//        httpHeaders.setLocation(URI.create(forOnePerson.getHref()));
        

//        httpHeaders.setLocation(
//            ServletUriComponentsBuilder.fromCurrentRequest()
//            .path("person/{id}").buildAndExpand(savedPerson.getId()).toUri());
        return personService.findAll();
    }
}