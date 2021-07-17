package uz.pdp.appwarehouse.controllar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appwarehouse.entity.Measurement;
import uz.pdp.appwarehouse.entity.Warehouse;
import uz.pdp.appwarehouse.payload.Result;
import uz.pdp.appwarehouse.repository.WarehouseRepository;
import uz.pdp.appwarehouse.service.MeasurementService;
import uz.pdp.appwarehouse.service.WarehouseService;

import java.util.List;

@RestController
@RequestMapping(value = "/warehouse")
public class WarehouseController {

    @Autowired
    WarehouseService warehouseService;

    //CREATE
    @PostMapping
    public Result add(@RequestBody Warehouse warehouse){
        return warehouseService.addWarehouseService(warehouse);
    }

    //READ
    @GetMapping
    public List<Warehouse> get(){
        return warehouseService.getWarehouse();
    }


    //READ BY ID
    @GetMapping(value = "/{id}")
    public Warehouse getById(@PathVariable Integer id){
        return warehouseService.getWarehouseById(id);
    }

    //DELETE
    @DeleteMapping(value = "/{id}")
    public Result delete(@PathVariable Integer id){
        return  warehouseService.deleteWarehouse(id);
    }

    //UPDATE
    @PutMapping(value = "/{id}")
    public Result update(@PathVariable Integer id, @RequestBody Warehouse warehouse){
        return warehouseService.updateWarehouse(id, warehouse);
    }
}
