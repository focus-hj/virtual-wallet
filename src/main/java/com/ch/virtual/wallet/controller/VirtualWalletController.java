package com.ch.virtual.wallet.controller;

import com.ch.virtual.wallet.controller.dto.VirtualWalletDto;
import com.ch.virtual.wallet.controller.dto.VirtualWalletTransactionDto;
import com.ch.virtual.wallet.controller.request.VirtualWalletRequest;
import com.ch.virtual.wallet.repository.entity.VirtualWalletEntity;
import com.ch.virtual.wallet.service.VirtualWalletService;
import com.ch.virtual.wallet.service.bo.VirtualWalletBo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/virtual/wallet")
public class VirtualWalletController {
    @Autowired
    private VirtualWalletService walletService;

    @GetMapping("/balance/{id}")
    public Double getBalance(@PathVariable Long id) {
        BigDecimal balance = walletService.getBalance(id);
        return balance.doubleValue();
    }

    @GetMapping("/{id}")
    public VirtualWalletDto getVirtualWallet(@PathVariable Long id) {
        VirtualWalletBo walletBo = walletService.getVirtualWallet(id);
        return convert(walletBo);
    }

    @PostMapping("/debit/")
    @ResponseStatus(HttpStatus.OK)
    public Long debit(@RequestBody VirtualWalletRequest request) {
        walletService.debit(request.getFromWalletId(), request.getAmount());
        return request.getFromWalletId();
    }

    @PostMapping("/credit/")
    @ResponseStatus(HttpStatus.OK)
    public Long credit(@RequestBody VirtualWalletRequest request) {
        walletService.credit(request.getFromWalletId(), request.getAmount());
        return request.getFromWalletId();
    }

    @PostMapping("/transfer/")
    @ResponseStatus(HttpStatus.OK)
    public Long transfer(@RequestBody VirtualWalletRequest request) {
        walletService.transfer(request.getFromWalletId(), request.getToWalletId(), request.getAmount());
        return request.getFromWalletId();
    }

    private VirtualWalletDto convert(VirtualWalletBo walletBo) {
        VirtualWalletDto walletDto = new VirtualWalletDto();
        BeanUtils.copyProperties(walletBo, walletDto);
        return walletDto;
    }
}
