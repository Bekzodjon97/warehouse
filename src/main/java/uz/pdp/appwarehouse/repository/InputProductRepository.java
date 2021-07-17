package uz.pdp.appwarehouse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.appwarehouse.entity.InputProduct;

import java.util.Date;

public interface InputProductRepository extends JpaRepository<InputProduct, Integer> {

}
