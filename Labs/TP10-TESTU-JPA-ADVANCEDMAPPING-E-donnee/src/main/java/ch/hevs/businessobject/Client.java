package ch.hevs.businessobject;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Client extends Person {

    private String description;

    // relations
    @Embedded
    private Address address;

    @ManyToOne
    private Agency agency;

    @OneToMany(cascade = CascadeType.ALL)
    private List<ExternalAccount> externalAccounts;

    @ManyToMany(cascade = CascadeType.ALL)
    private List<InternalAccount> internalAccounts;

    // description
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    // constructors
    public Client() {
    }

    public Client(String lastname, String firstname, String description) {
        super(lastname, firstname);
        this.description = description;
        this.externalAccounts = new ArrayList<>();
        this.internalAccounts = new ArrayList<>();
    }

    // helper methods
    public void addInternalAccount(InternalAccount c) {
        internalAccounts.add(c);
        c.addOwner(this);
    }

    public void addExternalAccount(ExternalAccount c) {
        externalAccounts.add(c);
    }

    public void setAgency(Agency agency) {
        this.agency = agency;
    }

}
