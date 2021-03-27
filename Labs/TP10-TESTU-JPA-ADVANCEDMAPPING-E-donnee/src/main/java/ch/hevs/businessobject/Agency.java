package ch.hevs.businessobject;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Agency {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    // relations
    @Embedded
    private Address address;

    @OneToMany(mappedBy = "agency", cascade = CascadeType.ALL)
    private List<Banker> employees;
    @OneToMany(mappedBy = "agency", cascade = CascadeType.ALL)
    private List<Client> clients;


    // id
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    // constructors
    public Agency() {
        this.employees = new ArrayList<>();
        this.clients = new ArrayList<>();
    }

    // helper methods
    public void addClient(Client c) {
        clients.add(c);
        c.setAgency(this);
    }

    public void addEmployee(Banker b) {
        employees.add(b);
        b.setAgency(this);
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
