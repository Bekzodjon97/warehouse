package uz.pdp.appwarehouse.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Outputs {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private Timestamp date;

    @ManyToOne
    private Warehouse  warehouse;

    @ManyToOne
    private Client  client;

    @ManyToOne
    private Currency  currency;


    @Column(nullable = false)
    private String  factureNumber;

    @Column(nullable = false, unique = true)
    private Integer  code;


}
