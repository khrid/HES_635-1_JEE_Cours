package ch.hevs.businessobject;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class InternalAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String number;
    private String description;
    private float saldo;

    // add relations
    @ManyToMany(mappedBy = "internalAccounts", cascade = CascadeType.ALL)
    private List<Client> clients;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_internalaccount", nullable = true)
    private List<Operation> operations;

    // id
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    // number
    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    // description
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    // saldo
    public float getSaldo() {
        return saldo;
    }

    public void setSolde(float saldo) {
        this.saldo = saldo;
    }

    // clients
    public List<Client> getClients() {
        return this.clients;
    }

    public void setClients(List<Client> clients) {
        this.clients = clients;
    }

    // operations
    public List<Operation> getOperations() {
        return this.operations;
    }

    public void setOperations(List<Operation> operations) {
        this.operations = operations;
    }

    // constructors
    public InternalAccount() {
    }

    public InternalAccount(String number, String description, float saldo) {
        this.description = description;
        this.number = number;
        this.saldo = saldo;
        this.clients = new ArrayList<>();
        this.operations = new ArrayList<>();
    }

    // helper method
    public void addOperation(Operation operation) {
        this.operations.add(operation);
    }

    public void addClient(Client client) {
        clients.add(client);
    }
}
