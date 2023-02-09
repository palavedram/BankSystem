package com.IronHack.BankSystem.models.accounts;

import com.IronHack.BankSystem.Money;
import com.IronHack.BankSystem.models.Enum.AcountType;
import com.IronHack.BankSystem.models.Enum.Status;
import com.IronHack.BankSystem.models.users.AccountHolder;
import com.IronHack.BankSystem.models.users.ThirdParty;
import com.IronHack.BankSystem.models.users.User;
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
public abstract class Account {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;
    private BigDecimal balance;
    private String secretKey;

    @OneToOne
    private User primaryOwner;

    @OneToOne
    private User secondaryOwner;

    private float minimumBalance;
    private float penaltyFee;

    @Column(nullable = true)
    private float monthlyMaintenanceFee;


    @Enumerated(EnumType.STRING)
    private Status status;

    @ManyToOne
    private ThirdParty thirdParty;

    @ManyToOne
    @JoinColumn(name = "account_holder_id")
    private AccountHolder accountHolder;

    @CreationTimestamp
    @Column(insertable = false, updatable = false)
    private LocalDate creationDate;
    @UpdateTimestamp
    @Column(insertable = false)
    private LocalDate updateDate;

}

