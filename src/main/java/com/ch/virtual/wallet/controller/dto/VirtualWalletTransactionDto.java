package com.ch.virtual.wallet.controller.dto;

import com.ch.virtual.wallet.enums.TransactionType;

import java.math.BigDecimal;

public class VirtualWalletTransactionDto {
    private BigDecimal amount;
    private Long createTime;
    private TransactionType type;
    private Long fromWalletId;
    private Long toWalletId;
}
