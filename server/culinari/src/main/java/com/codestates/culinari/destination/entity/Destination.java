package com.codestates.culinari.destination.entity;

import com.codestates.culinari.audit.AuditingFields;
import com.codestates.culinari.destination.dto.request.DestinationPatch;
import com.codestates.culinari.user.entitiy.Profile;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class Destination {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String destinationName;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private String receiverName;

    @Column(nullable = false)
    private String receiverPhoneNumber;

    @Column(nullable = false)
    private Boolean defaultSelect;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Profile profile;

    public Destination(String destinationName, String address, String receiverName, String receiverPhoneNumber, Boolean defaultSelect, Profile profile) {
        this.id = null;
        this.destinationName = destinationName;
        this.address = address;
        this.receiverName = receiverName;
        this.receiverPhoneNumber = receiverPhoneNumber;
        this.defaultSelect = defaultSelect;
        this.profile = profile;
    }

    public static Destination of(String destinationName, String address, String receiverName, String receiverPhoneNumber, Boolean defaultSelect, Profile profile) {
        return new Destination(
                destinationName,
                address,
                receiverName,
                receiverPhoneNumber,
                defaultSelect,
                profile
        );
    }

    public void updateDestination(DestinationPatch destinationPatch) {
        this.destinationName = destinationPatch.destinationName();
        this.address = destinationPatch.address();
        this.receiverName = destinationPatch.receiverName();
        this.receiverPhoneNumber = destinationPatch.receiverPhoneNumber();
    }

    public boolean isDefaultSelect(){
        return this.defaultSelect;
    }

    public void defaultSelectToFalse() {
        this.defaultSelect = false;
    }

    public void defaultSelectToTrue() {
        this.defaultSelect = true;
    }
}
