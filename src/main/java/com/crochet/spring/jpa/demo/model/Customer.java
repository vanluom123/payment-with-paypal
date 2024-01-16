package com.crochet.spring.jpa.demo.model;

import com.crochet.spring.jpa.demo.type.RoleType;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.hibernate.proxy.HibernateProxy;

import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "customer")
@SuperBuilder
@NoArgsConstructor
public class Customer extends BaseEntity {
  @Column(name = "name", columnDefinition = "NVARCHAR(50)")
  private String name;

  @Email
  @Column(name = "email", length = 128, nullable = false, unique = true)
  private String email;

  @Column(name = "username", nullable = false)
  private String username;

  @Column(name = "password", nullable = false)
  private String password;

  @Enumerated(EnumType.STRING)
  @Column(name = "role", columnDefinition = "VARCHAR(5) DEFAULT 'USER'")
  @Builder.Default
  private RoleType role = RoleType.USER;

  @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
  private Set<PatternOrder> patternOrders;

  @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
  private Set<ProductOrder> productOrders;

  @OneToOne(mappedBy = "customer", cascade = CascadeType.ALL)
  private Cart cart;

  @OneToOne(mappedBy = "customer")
  private Shop shop;

  @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
  private Set<Contact> contacts;

  @Override
  public final boolean equals(Object o) {
    if (this == o) return true;
    if (o == null) return false;
    Class<?> oEffectiveClass =
        o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() :
            o.getClass();
    Class<?> thisEffectiveClass = this instanceof HibernateProxy ?
        ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
    if (thisEffectiveClass != oEffectiveClass) return false;
    Customer customer = (Customer) o;
    return (getId() != null && Objects.equals(getId(), customer.getId()))
        || Objects.equals(getUsername(), customer.getUsername())
        || Objects.equals(getEmail(), customer.getEmail());
  }
}
