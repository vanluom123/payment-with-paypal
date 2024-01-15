package com.crochet.spring.jpa.demo.model;

import com.crochet.spring.jpa.demo.type.paypal.PaymentStatus;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "payment")
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class Payment extends BaseEntity {
  @Column(name = "transaction_id", nullable = false, unique = true)
  private String transactionId;

  @Column(name = "amount", columnDefinition = "DOUBLE CHECK (amount > 0) NOT NULL")
  private double amount;

  @CreationTimestamp
  @Temporal(TemporalType.DATE)
  @Column(name = "payment_date", nullable = false)
  private Date paymentDate;

  @Enumerated(EnumType.STRING)
  @Column(name = "payment_status", columnDefinition = "VARCHAR(25) DEFAULT 'CREATED' NOT NULL")
  private PaymentStatus paymentStatus;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "product_order_id", columnDefinition = "BINARY(16) NOT NULL")
  private ProductOrder productOrder;
}
