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
@Table(name = "customer")
public class Customer implements SuperEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false, length = 40)
    private String name;
    @Column(unique = true, nullable = false, length = 20)
    private String email;
    @Column(nullable = false, length = 30)
    private String city;
    @Column(name = "profile_pic", columnDefinition = "LONGTEXT")
    private String profilePic;
    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Order> orders = new ArrayList<>();
}
