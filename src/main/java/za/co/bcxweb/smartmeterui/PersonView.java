/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package za.co.bcxweb.smartmeterui;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;
 
@ManagedBean(name="personView")
@ViewScoped
public class PersonView implements Serializable {
     
    private List<Person> person1;
    private List<Person> person2;
    private List<Person> person3;
    private List<Person> person4;
    private List<Person> person5;
    private List<Person> person6;
    private Person selectedPerson;
    private List<Person> selectedPersons;
    
    @ManagedProperty("#{personService}")
    private PersonService service;
     
    @PostConstruct
    public void init() {
        person1 = service.createPersons(10);
        person2 = service.createPersons(10);
        person3 = service.createPersons(10);
        person4 = service.createPersons(10);
        person5 = service.createPersons(10);
        person6 = service.createPersons(10);
    }
 
    public List<Person> getPersons1() {
        return person1;
    }
 
    public List<Person> getPersons2() {
        return person2;
    }
 
    public List<Person> getPersons3() {
        return person3;
    }
 
    public List<Person> getPersons4() {
        return person4;
    }
 
    public List<Person> getPersons5() {
        return person5;
    }
 
    public List<Person> getPersons6() {
        return person6;
    }
     
    public void setService(PersonService service) {
        this.service = service;
    }
 
    public Person getSelectedPerson() {
        return selectedPerson;
    }
 
    public void setSelectedPerson(Person selectedPerson) {
        this.selectedPerson = selectedPerson;
    }
 
    public List<Person> getSelectedPersons() {
        return selectedPersons;
    }
 
    public void setSelectedPersons(List<Person> selectedPersons) {
        this.selectedPersons = selectedPersons;
    }
     
    public void onRowSelect(SelectEvent event) {
        FacesMessage msg = new FacesMessage("Person Selected", ((Person) event.getObject()).getId());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
 
    public void onRowUnselect(UnselectEvent event) {
        FacesMessage msg = new FacesMessage("Person Unselected", ((Person) event.getObject()).getId());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
}
