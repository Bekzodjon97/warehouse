package uz.pdp.appwarehouse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.appwarehouse.entity.OutputProduct;

public interface OutputProductRepository extends JpaRepository<OutputProduct, Integer> {
    boolean existsByProductIdAndAmountAndPriceAndOutputId(Integer product_id, Double amount, Double price, Integer output_id);

}
