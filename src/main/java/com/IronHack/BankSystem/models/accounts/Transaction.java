package com.IronHack.BankSystem.models.accounts;

import com.IronHack.BankSystem.models.Enum.TransactionType;
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

        @Enumerated(EnumType.STRING)
        private TransactionType transactionType;

        @JsonIgnore
        private LocalDateTime dateTime;

        private String formattedDateTime; //Dar formato para que sea mas facil de leer.

        private BigDecimal senderBalance;
        private BigDecimal receiverBalance;



        @ManyToOne
        @JoinColumn(name = "fromAccount")
        private Account sender;

        @ManyToOne
        @JoinColumn(name = "toAccount")
        private Account receiver;


}

