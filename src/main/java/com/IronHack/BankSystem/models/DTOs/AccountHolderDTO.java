package com.IronHack.BankSystem.models.DTOs;


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


    private String firstName;
    private String lastName;
    private String email;
    private LocalDate dateOfBirth;
    private Integer primaryAddress;
    private Integer mailingAddress;
    private Integer accounts;


        /*"firstName": "Pere",
            "lastName": "Alavedra",
            "email": "pere@alavedra.com",
            "dateOfBirth": "1993-05-20",
            "primaryAddress": {
        "address_id": 1
   */

}
