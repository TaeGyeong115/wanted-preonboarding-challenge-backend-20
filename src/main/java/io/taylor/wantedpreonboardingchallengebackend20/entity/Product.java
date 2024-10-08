package io.taylor.wantedpreonboardingchallengebackend20.entity;

import io.taylor.wantedpreonboardingchallengebackend20.model.ProductStatus;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "products", indexes = {
        @Index(name = "products_idx_id", columnList = "id", unique = true)
})
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
public class Product extends BaseEntity {
    @Column
    private String name;
    @Column
    private long memberId;
    @Column
    private int inventory;
    @Column
    private long price;
    @Column
    private int status;

    public Product(String name, long price, int inventory) {
        this.name = name;
        this.price = price;
        this.inventory = inventory;
        this.status = ProductStatus.Available.getNumber();
    }
}
