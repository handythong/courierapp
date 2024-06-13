package com.fdmgroup.courierapp.model;

import jakarta.persistence.*;

@Entity
public class Party {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "partyId_IdSeq")
    @SequenceGenerator(name = "partyId_IdSeq", sequenceName = "partyId_IdSeq", allocationSize = 1, initialValue = 1)
    private long id;

    private String fullName;
    private String email;
    private String phoneNo;
    @Enumerated(EnumType.STRING)
    private PartyEnum partyType;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id")
    private Address address;
    @ManyToOne
    @JoinColumn(name = "customer_order_id")
    private CustomerOrder customerOrder;

    public Party() {
    }

    public Party(long id, String fullName, String email, String phoneNo, PartyEnum partyType, Address address, CustomerOrder customerOrder) {
        this.id = id;
        this.fullName = fullName;
        this.email = email;
        this.phoneNo = phoneNo;
        this.partyType = partyType;
        this.address = address;
        this.customerOrder = customerOrder;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public PartyEnum getPartyType() {
        return partyType;
    }

    public void setPartyType(PartyEnum partyType) {
        this.partyType = partyType;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public CustomerOrder getCustomerOrder() {
        return customerOrder;
    }

    public void setCustomerOrder(CustomerOrder customerOrder) {
        this.customerOrder = customerOrder;
    }
}
