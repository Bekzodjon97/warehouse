package uz.pdp.appwarehouse.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class InputProductDto {

    private Integer productId;

    private Double  amount;

    private Double  price;

    private Date expireDate;

    private Integer inputId;
}
