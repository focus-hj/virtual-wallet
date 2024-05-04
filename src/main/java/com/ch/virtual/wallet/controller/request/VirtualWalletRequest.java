package com.ch.virtual.wallet.controller.request;

import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class VirtualWalletRequest {
    private Long fromWalletId;
    private Long toWalletId;
    private BigDecimal amount;
}
