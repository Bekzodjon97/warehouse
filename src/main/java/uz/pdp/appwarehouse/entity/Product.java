package uz.pdp.appwarehouse.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import sun.security.krb5.internal.crypto.EType;
import uz.pdp.appwarehouse.entity.template.AbsEntity;

import javax.persistence.*;
import java.lang.annotation.ElementType;
import java.lang.annotation.Target;
import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Product extends AbsEntity {
    @ManyToOne(optional = false )
   private Category category;

    @OneToOne
    private Attachment photo;

    @Column(nullable = false)
    private Integer code;

    @ManyToOne
    private Measurement measurement;

    @ManyToMany
    private Set<Warehouse> warehouses;

}
