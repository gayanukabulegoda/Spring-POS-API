package lk.ijse.springposapi.entity.impl;

import jakarta.persistence.*;
import lk.ijse.springposapi.entity.SuperEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "`order`")
public class Order implements SuperEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false)
    private String date;
    @Column(nullable = false, length = 10)
    private double total;
    @Column(nullable = false, length = 8)
    private String discount;
    @Column(name = "sub_total", nullable = false, length = 8)
    private double subTotal;
    @Column(nullable = false, length = 10)
    private double cash;
    @Column(nullable = false, length = 8)
    private double balance;
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "customer_id", referencedColumnName = "id", nullable = false)
    private Customer customer;
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<OrderDetail> orderDetails = new ArrayList<>();
}
