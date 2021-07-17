package uz.pdp.appwarehouse.controllar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appwarehouse.entity.Measurement;
import uz.pdp.appwarehouse.entity.Supplier;
import uz.pdp.appwarehouse.payload.Result;
import uz.pdp.appwarehouse.service.SupplierService;

import java.util.List;

@RestController
@RequestMapping(value = "/supplier")
public class SupplierController {

    @Autowired
    SupplierService supplierService;

    //CREATE
    @PostMapping
    public Result add(@RequestBody Supplier supplier){
        return supplierService.addSupplierService(supplier);
    }

    //READ
    @GetMapping
    public List<Supplier> get(){
        return supplierService.getSupplier();
    }


    //READ BY ID
    @GetMapping(value = "/{id}")
    public Supplier getById(@PathVariable Integer id){
        return supplierService.getSupplierById(id);
    }

    //DELETE
    @DeleteMapping(value = "/{id}")
    public Result delete(@PathVariable Integer id){
        return  supplierService.deleteSupplier(id);
    }

    //UPDATE
    @PutMapping(value = "/{id}")
    public Result update(@PathVariable Integer id, @RequestBody Supplier supplier){
        return supplierService.updateSupplier(id, supplier);
    }
}
