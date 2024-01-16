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
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "pattern_order_detail")
@SuperBuilder
@NoArgsConstructor
public class PatternOrderDetail extends BaseEntity {
  @Column(name = "transaction_id", nullable = false, unique = true)
  private String transactionId;

  @Enumerated(EnumType.STRING)
  @Column(name = "status", nullable = false)
  private PaymentStatus status;

  @CreationTimestamp
  @Temporal(TemporalType.DATE)
  @Column(name = "order_date", nullable = false)
  private Date orderDate;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "pattern_order_id", columnDefinition = "BINARY(16) NOT NULL")
  private PatternOrder patternOrder;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "pattern_id", columnDefinition = "BINARY(16) NOT NULL")
  private Pattern pattern;
}
