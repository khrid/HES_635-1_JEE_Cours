package ch.hevs.businessobject;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Agency {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    // add relations
    @OneToMany(mappedBy = "agency", cascade = CascadeType.ALL) // One Agency To Many Bankers
            List<Banker> bankers;
    @OneToMany(mappedBy = "agency", cascade = CascadeType.ALL) // One Agency To Many Clients
    List<Client> clients;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_address", nullable = true)
    private Address address;

    // id
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    // bankers
    public List<Banker> getBankers() {
        return bankers;
    }

    public void setBankers(List<Banker> bankers) {
        this.bankers = bankers;
    }

    // clients
    public List<Client> getClients() {
        return clients;
    }

    public void setClients(List<Client> clients) {
        this.clients = clients;
    }

    // address
    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    // constructors
    public Agency() {
        this.bankers = new ArrayList<>();
        this.clients = new ArrayList<>();
    }

    // helper methods
    public void addBanker(Banker banker) {
        this.bankers.add(banker);
        banker.setAgency(this); // ne pas oublier !!
    }

    public void addClient(Client client) {
        this.clients.add(client);
        client.setAgency(this); // ne pas oublier !!
    }
}
