package uz.pdp.appwarehouse.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.appwarehouse.entity.Measurement;
import uz.pdp.appwarehouse.entity.Warehouse;
import uz.pdp.appwarehouse.payload.Result;
import uz.pdp.appwarehouse.repository.WarehouseRepository;

import java.util.List;
import java.util.Optional;

@Service
public class WarehouseService {

    @Autowired
    WarehouseRepository warehouseRepository;

    public Result addWarehouseService(Warehouse warehouse){
        boolean existsByName = warehouseRepository.existsByName(warehouse.getName());
        if (existsByName) {
            return new Result("Bunday ombor mavjud", false);
        }
        warehouseRepository.save(warehouse);
        return new Result("Muaffaqiyatli saqlandi",true);
    }
    public List<Warehouse> getWarehouse(){
        return warehouseRepository.findAll();
    }
    public  Warehouse getWarehouseById(Integer id){
        Optional<Warehouse> optionalWarehouse = warehouseRepository.findById(id);
        return optionalWarehouse.orElseGet(Warehouse::new);
    }
    public  Result deleteWarehouse(Integer id){
        Optional<Warehouse> optionalWarehouse = warehouseRepository.findById(id);
        if (!optionalWarehouse.isPresent()) {
            return new Result("Warehouse not found", false);
        }
        warehouseRepository.deleteById(id);
        return new Result("Warehouse deleted", true);
    }
    public Result updateWarehouse(Integer id, Warehouse warehouse){
        Optional<Warehouse> optionalWarehouse = warehouseRepository.findById(id);
        if (!optionalWarehouse.isPresent()) {
            return  new Result("Warehouse not found", false);
        }
        Warehouse editedWarehouse = optionalWarehouse.get();
        editedWarehouse.setName(warehouse.getName());
        warehouseRepository.save(editedWarehouse);
        return new Result("Warehouse edited", true);
    }
}

