package com.fdmgroup.courierapp.model;

import jakarta.persistence.*;

import java.util.List;
import java.util.Map.Entry;
import java.util.Objects;

@Entity
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "addressId_IdSeq")
    @SequenceGenerator(name = "addressId_IdSeq", sequenceName = "addressId_IdSeq", allocationSize = 1, initialValue = 1)
    private long id;

    @OneToOne(mappedBy = "address")
    @JoinColumn(name = "party_id")
    private Party party;
    private String address;
    private String postalCode;
    private String country;
    private String city;
    private String region;

    public Address() {
    }

    public Address(long id, String address, String postalCode, String country, String city) {
        this.id = id;
        this.address = address;
        this.postalCode = postalCode;
        this.country = country;
        this.city = city;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getRegion() { return region; }

    public void setRegion(String postalCode) {
        Regions regions = new Regions();
        for (Entry<RegionEnum, List<String>> entry: regions.regionsHash.entrySet()) {
            for (String value : entry.getValue()) {
                if (Objects.equals(value, postalCode.substring(0, 2))) {
                    this.region = entry.getKey().name();
                    break;
                }
            }
        }
    }
}
