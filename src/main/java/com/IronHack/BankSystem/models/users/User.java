package com.IronHack.BankSystem.models.users;
import com.IronHack.BankSystem.models.Address;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;


@NoArgsConstructor
@Setter
@Getter

@Entity
@Inheritance(strategy=InheritanceType.JOINED)
public abstract  class User {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer userid;
    private String firstName;
    private String lastName;
    private String email;
    private LocalDate dateOfBirth;


    @OneToOne                               //indica que cada usuario solo tiene una dirección primaria.
    @JoinColumn(name="primaryAddressId") //indica que la primaryAddress es la columna principal en la tabla de usuarios.
    private Address primaryAddress;

    @OneToOne
    //@JoinColumn(name="mailing_address_id")
    private Address mailingAddress;
    // Getters and setters ...
}