package io.taylor.domain.order;

import io.taylor.domain.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.math.BigDecimal;

@Entity
@Getter
@EqualsAndHashCode(callSuper = true)
@Table(name = "orders", indexes = {
        @Index(name = "orders_idx_id", columnList = "id", unique = true)
})
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
public class Order extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private long productId;
    private long customerId;
    private BigDecimal price;
    private int quantity;
    private BigDecimal totalPrice;

    @Enumerated(EnumType.ORDINAL)
    private OrderStatus status;

    @Builder
    Order(long productId, long customerId, BigDecimal price, int quantity) {
        this.productId = productId;
        this.customerId = customerId;
        this.price = price;
        this.quantity = quantity;
        this.totalPrice = price.multiply(new BigDecimal(quantity));
        this.status = OrderStatus.IN_PROGRESS;
    }

    public void updateStatue(OrderStatus status) {
        this.status = status;
    }
}

