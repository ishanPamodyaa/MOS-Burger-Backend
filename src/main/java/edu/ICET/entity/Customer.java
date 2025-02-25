package edu.ICET.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;
import java.util.List;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String customerId;
    private String nic;
    private String fName;
    private String lName;
    private String email;
    private String contactNumber;
    private String address;
    private String city;
    private String gender;

    @Temporal(TemporalType.DATE)
    private Date dateOfJoined;

    @Lob
    private byte[] profilePic;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, fetch=FetchType.LAZY)
    private List<Order> orders;

}
