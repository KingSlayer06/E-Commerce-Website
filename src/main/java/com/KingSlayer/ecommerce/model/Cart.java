package com.KingSlayer.ecommerce.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data @Builder
@AllArgsConstructor @NoArgsConstructor
@Table(name = "cart")
public class Cart {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "item_id")
    private Integer id;
}
