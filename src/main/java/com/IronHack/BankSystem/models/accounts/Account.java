package com.IronHack.BankSystem.models.accounts;

import com.IronHack.BankSystem.Money;
import com.IronHack.BankSystem.models.Enum.AcountType;
import com.IronHack.BankSystem.models.Enum.Status;
import com.IronHack.BankSystem.models.users.AccountHolder;
import com.IronHack.BankSystem.models.users.ThirdParty;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import java.math.BigDecimal;
import java.time.LocalDateTime;


@NoArgsConstructor
@Setter
@Getter
@ToString

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private BigDecimal balance = BigDecimal.valueOf(0);
    private String secretKey;
    private String primaryOwner;
    private String secondaryOwner;

    BigDecimal PENALTY_FEE = BigDecimal.valueOf(-40);

    @Enumerated(EnumType.STRING)
    private Status status;
    @Enumerated(EnumType.STRING)
    private AcountType accountType;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime creationDate;

    @UpdateTimestamp
    @Column
    private LocalDateTime updateDate;


    @ManyToOne
    private ThirdParty thirdParty;

    @ManyToOne
    private AccountHolder accountHolder;



}

