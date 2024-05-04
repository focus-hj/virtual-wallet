package com.ch.virtual.wallet.service;

import com.ch.virtual.wallet.enums.TransactionType;
import com.ch.virtual.wallet.exception.NoSufficientBalanceException;
import com.ch.virtual.wallet.repository.entity.VirtualWalletEntity;
import com.ch.virtual.wallet.repository.VirtualWalletRepository;
import com.ch.virtual.wallet.repository.entity.VirtualWalletTransactionEntity;
import com.ch.virtual.wallet.repository.VirtualWalletTransactionRepository;
import com.ch.virtual.wallet.service.bo.VirtualWalletBo;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
public class VirtualWalletService {

    private VirtualWalletRepository walletRepository;
    private VirtualWalletTransactionRepository transactionRepository;

    public VirtualWalletBo getVirtualWallet(Long walletId) {
        VirtualWalletEntity walletEntity = walletRepository.getWalletEntity(walletId);
        return convert(walletEntity);
    }

    private VirtualWalletBo convert(VirtualWalletEntity walletEntity) {
        VirtualWalletBo walletBo = new VirtualWalletBo();
        BeanUtils.copyProperties(walletEntity, walletBo);
        return walletBo;
    }

    public BigDecimal getBalance(Long walletId) {
        return walletRepository.getBalance(walletId);
    }

    @Transactional
    public void debit(Long walletId, BigDecimal amount) {
        VirtualWalletEntity walletEntity = walletRepository.getWalletEntity(walletId);
        BigDecimal balance = walletEntity.getBalance();
        if (balance.compareTo(amount) < 0) {
            throw new NoSufficientBalanceException();
        }
        VirtualWalletTransactionEntity transactionEntity = new VirtualWalletTransactionEntity();
        transactionEntity.setAmount(amount);
        transactionEntity.setCreateTime(System.currentTimeMillis());
        transactionEntity.setType(TransactionType.DEBIT);
        transactionEntity.setFromWalletId(walletId);
        transactionRepository.saveTransaction(transactionEntity);
        walletRepository.updateBalance(walletId, balance.subtract(amount));
    }

    @Transactional
    public void credit(Long walletId, BigDecimal amount) {
        VirtualWalletTransactionEntity transactionEntity = new VirtualWalletTransactionEntity();
        transactionEntity.setAmount(amount);
        transactionEntity.setCreateTime(System.currentTimeMillis());
        transactionEntity.setType(TransactionType.CREDIT);
        transactionEntity.setFromWalletId(walletId);
        transactionRepository.saveTransaction(transactionEntity);
        VirtualWalletEntity walletEntity = walletRepository.getWalletEntity(walletId);
        BigDecimal balance = walletEntity.getBalance();
        walletRepository.updateBalance(walletId, balance.subtract(amount));
    }

    @Transactional
    public void transfer(Long fromWalletId, Long toWalletId, BigDecimal amount) {
        VirtualWalletTransactionEntity transactionEntity = new VirtualWalletTransactionEntity();
        transactionEntity.setAmount(amount);
        transactionEntity.setCreateTime(System.currentTimeMillis());
        transactionEntity.setType(TransactionType.TRANSFER);
        transactionEntity.setFromWalletId(fromWalletId);
        transactionEntity.setToWalletId(toWalletId);
        transactionRepository.saveTransaction(transactionEntity);
        debit(fromWalletId, amount);
        credit(toWalletId,amount);
    }
}
