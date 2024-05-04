package com.ch.virtual.wallet.repository;

import com.ch.virtual.wallet.repository.entity.VirtualWalletTransactionEntity;
import org.springframework.data.repository.CrudRepository;

public interface VirtualWalletTransactionRepository extends CrudRepository<VirtualWalletTransactionEntity, Long> {

    void saveTransaction(VirtualWalletTransactionEntity transactionEntity);
}
