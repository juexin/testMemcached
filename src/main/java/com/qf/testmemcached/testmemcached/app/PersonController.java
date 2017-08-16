package com.qf.testmemcached.testmemcached.app;

import com.qf.testmemcached.testmemcached.domain.Person;
import com.qf.testmemcached.testmemcached.service.IPersonService;
import com.qf.testmemcached.testmemcached.service.PersonService;
import com.qf.testmemcached.testmemcached.util.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * @author gpotes
 */
@RestController
@Component
public class PersonController {
  private IPersonService personService;

  /**
   * @param personService
   */
  @Autowired
  PersonController(
          final PersonService personService) {
    this.personService = personService;
  }

  /**
   * @return
   */
  @RequestMapping("/persons")
  public List<Person> findAll() {
    List<Person> list = personService.findAll();
    return list;
  }

  /**
   * @return
   */
  @RequestMapping("/person/")
  public Person findById(@RequestParam(value = "id") final String id) {
    return personService.findById(id);
  }

  /**
   * @return
   */
  @RequestMapping(value = "/savePerson", method = RequestMethod.POST)
  public List<Person> save(HttpServletRequest request) {
    Map m = request.getParameterMap();
    Person person = JsonUtils.toObject((String) m.keySet().iterator().next(), Person.class);
    person.setId(UUID.randomUUID().toString());
    personService.save(person);
//    HttpHeaders httpHeaders = new HttpHeaders();

//        Link forOnePerson = new PersonResource(savedPerson).getLink("self");
//        httpHeaders.setLocation(URI.create(forOnePerson.getHref()));


//        httpHeaders.setLocation(
//            ServletUriComponentsBuilder.fromCurrentRequest()
//            .path("person/{id}").buildAndExpand(savedPerson.getId()).toUri());
    return this.findAll();
  }
}