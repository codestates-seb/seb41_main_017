package com.codestates.culinari.order.entitiy;

import com.codestates.culinari.audit.AuditingFields;
import com.codestates.culinari.payment.entity.Payment;
import com.codestates.culinari.product.entitiy.ProductReviewLike;
import com.codestates.culinari.user.entitiy.Profile;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class Orders extends AuditingFields {
    @Id
    @Column(length = 16)
    private String id;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private String receiverName;

    @Column(nullable = false)
    private String receiverPhoneNumber;

    @ManyToOne(optional = false)
    private Profile profile;

    @OneToMany(mappedBy = "orders")
    private List<OrderDetail> orderDetails = new ArrayList<>();

    @Setter
    @OneToOne
    private Payment payment;

    private Orders(String id, String address, String receiverName, String receiverPhoneNumber, Profile profile) {
        this.id = id;
        this.address = address;
        this.receiverName = receiverName;
        this.receiverPhoneNumber = receiverPhoneNumber;
        this.profile = profile;
    }

    public static Orders of(String id, String address, String receiverName, String receiverPhoneNumber, Profile profile) {
        return new Orders(id, address, receiverName, receiverPhoneNumber, profile);
    }

}
