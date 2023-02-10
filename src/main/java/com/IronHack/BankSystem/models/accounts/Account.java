package com.IronHack.BankSystem.models.accounts;

import com.IronHack.BankSystem.Money;
import com.IronHack.BankSystem.models.Enum.AcountType;
import com.IronHack.BankSystem.models.Enum.Status;
import com.IronHack.BankSystem.models.users.AccountHolder;
import com.IronHack.BankSystem.models.users.ThirdParty;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;

@NoArgsConstructor
@Setter
@Getter
@ToString

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Account {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;
    private BigDecimal balance;
    private String secretKey;
    private String primaryOwner;
    private String secondaryOwner;

    private Float penaltyFee;

    @Enumerated(EnumType.STRING)
    private Status status;
    @Enumerated(EnumType.STRING)
    private AcountType accountType;

    @CreationTimestamp
    @Column(insertable = false, updatable = false)
    private LocalDate creationDate;
    @UpdateTimestamp
    @Column(insertable = false)
    private LocalDate updateDate;

    @ManyToOne
    private ThirdParty thirdParty;

    @ManyToOne
    @JoinColumn
    private AccountHolder accountHolder;

}

