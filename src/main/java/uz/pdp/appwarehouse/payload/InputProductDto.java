package uz.pdp.appwarehouse.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.pdp.appwarehouse.entity.Input;
import uz.pdp.appwarehouse.entity.Product;
import uz.pdp.appwarehouse.entity.Supplier;

import javax.persistence.Column;
import javax.persistence.ManyToOne;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class InputProductDto {

    private Integer productId;

    private Integer supplierId;

    private Double  amount;

    private Double  price;

    private Date expireDate;

    private Integer inputId;
}
