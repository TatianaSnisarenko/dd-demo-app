package com.presentation.dd_demo_app.repository.entity;

import static jakarta.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PRIVATE;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import lombok.experimental.FieldDefaults;

@Entity
@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = PRIVATE)
@Table(name = "inventory")
public class Inventory {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id")
    Long id;

    @OneToOne
    @JoinColumn(name = "accessory_id", nullable = false)
    Accessory accessory;

    @Column(name = "quantity")
    int quantity;


}
