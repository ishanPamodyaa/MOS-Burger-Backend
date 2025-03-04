package edu.ICET.dto;

import edu.ICET.entity.Order;
import lombok.Data;

import java.util.Date;
import java.util.List;


@Data
public class CustomerDto {


    private String customerId;
    private String nic;
    private String fstName;
    private String lstName;
    private String email;
    private String contactNumber;
    private String address;
    private String city;
    private String gender;
    private Date dateOfJoined;
    private byte[] profilePic;
    private List<String> orderIds;
}
