package uz.pdp.appwarehouse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uz.pdp.appwarehouse.entity.Outputs;

import java.util.List;

public interface OutputRepository extends JpaRepository<Outputs, Integer> {
    @Query(value = "select code from output",nativeQuery = true)
    List<Integer> getCode();

}
