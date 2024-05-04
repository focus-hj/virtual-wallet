package com.ch.virtual.wallet.repository;

import com.ch.virtual.wallet.repository.entity.VirtualWalletEntity;
import org.springframework.data.repository.CrudRepository;

import java.math.BigDecimal;

public interface VirtualWalletRepository extends CrudRepository<VirtualWalletEntity, Long> {

    VirtualWalletEntity getWalletEntity(Long id);

    BigDecimal getBalance(Long walletId);

    void updateBalance(Long walletId, BigDecimal balance);
}
