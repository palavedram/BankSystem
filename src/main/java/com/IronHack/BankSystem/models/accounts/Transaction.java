package com.IronHack.BankSystem.models.accounts;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
@Getter
@Setter
@NoArgsConstructor

@Entity
    public class Transaction {
        @Id
        @GeneratedValue(strategy= GenerationType.IDENTITY)
        private Integer transactionId;
        private BigDecimal amount;

        @JsonIgnore
        private LocalDateTime dateTime;

        private String formattedDateTime; //Dar formato para que sea mas facil de leer.

        private BigDecimal fromAccountBalance;
        private BigDecimal toAccountBalance;

        @ManyToOne
        @JoinColumn(name = "from_account_id")
        private Account fromAccount;

        @ManyToOne
        @JoinColumn(name = "to_account_id")
        private Account toAccount;


    }

