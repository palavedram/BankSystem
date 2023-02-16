package com.IronHack.BankSystem.models.DTOs;


import com.IronHack.BankSystem.models.users.AccountHolder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDate;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor

public class AccountHolderDTO {

//User attributes:
    private String name;
    private String username;
    private String password;

//AccountHolder attributes:

    private String email;
    private LocalDate dateOfBirth;

//Address attributes:

    private String street;
    private String city;
    private String country;
    private String zipCode;

//Account attributes

/*
    private Integer balance;
    private String secretKey;
    private String primaryOwner;
    private String secondaryOwner;
    private Integer minimumBalance;
    private Integer penaltyFee;
    private Integer monthlyMaintenanceFee;
    private String status;
    private String accountType;
    private AccountHolder accountHolder;
*/


/*    private Integer primaryAddress;
    private Integer mailingAddress;
    private Integer accounts;*/


        /*"firstName": "Pere",
            "lastName": "Alavedra",
            "email": "pere@alavedra.com",
            "dateOfBirth": "1993-05-20",
            "primaryAddress": {
        "address_id": 1
   */

}
