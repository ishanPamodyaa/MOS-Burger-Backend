package edu.ICET.dto;

import edu.ICET.entity.Order;
import lombok.Data;

import java.util.Date;
import java.util.List;


@Data
public class CustomerDto {

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
    private Date dateOfJoined;
    private byte[] profilePic;
    private List<OrderDto> orders;
}
