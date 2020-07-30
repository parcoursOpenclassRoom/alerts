package com.safetynet.alerts.Controller;

import com.safetynet.alerts.Entity.Person;
import com.safetynet.alerts.Manager.person.PersonManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/person")
public class PersonController {

    @Autowired
    PersonManager personManager;

    @PostMapping
    public Person create(@RequestBody Person person){
        return personManager.save(person);
    }

    @PutMapping
    public Person update(@RequestBody Person person){
        return personManager.save(person);
    }

    @DeleteMapping("/{firstName}/{lastName}")
    public void delete(@PathVariable String firstName, @PathVariable String lastName){
        personManager.delete(firstName, lastName);
    }
}
