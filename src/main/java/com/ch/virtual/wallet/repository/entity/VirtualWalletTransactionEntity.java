package com.ch.virtual.wallet.repository.entity;

import com.ch.virtual.wallet.enums.TransactionType;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
public class VirtualWalletTransactionEntity {
    private Long id;
    private BigDecimal amount;
    private Long createTime;
    private TransactionType type;
    private Long fromWalletId;
    private Long toWalletId;
}
