package com.crochet.spring.jpa.demo.model;

import com.crochet.spring.jpa.demo.type.RoleType;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.hibernate.proxy.HibernateProxy;

import java.util.List;
import java.util.Objects;

@Getter
@Setter
@Entity
@Table(name = "customer")
@SuperBuilder
@NoArgsConstructor
public class Customer extends BaseEntity {
    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "address", length = 512, nullable = false)
    private String address;

    @Column(name = "email", length = 128, nullable = false)
    private String email;

    @Column(name = "phone", length = 32, nullable = false)
    private String phone;

    @Column(name = "username", nullable = false)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "enabled", nullable = false)
    private boolean enabled;

    @Enumerated(EnumType.STRING)
    @Column(name = "role", columnDefinition = "varchar(5) DEFAULT 'USER'")
    private RoleType role = RoleType.USER;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private List<Order> orders;

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
                || Objects.equals(getEmail(), customer.getEmail())
                || Objects.equals(getPhone(), customer.getPhone());
    }
}
