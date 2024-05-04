package com.ch.virtual.wallet.repository.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Table(name = "T_VIRTUAL_WALLET")
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class VirtualWalletEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(updatable = false)
    private Long createTime;
    private BigDecimal balance;
}
