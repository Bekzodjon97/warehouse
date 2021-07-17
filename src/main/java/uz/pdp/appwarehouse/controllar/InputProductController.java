package uz.pdp.appwarehouse.controllar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appwarehouse.entity.InputProduct;
import uz.pdp.appwarehouse.entity.OutputProduct;
import uz.pdp.appwarehouse.payload.InputProductDto;
import uz.pdp.appwarehouse.payload.OutputProductDto;
import uz.pdp.appwarehouse.payload.Result;
import uz.pdp.appwarehouse.repository.WarehouseRepository;
import uz.pdp.appwarehouse.service.InputProductService;

import java.util.List;

@RestController
@RequestMapping(value = "/input/product")
public class InputProductController {

    @Autowired
    InputProductService inputProductService;


    //CREATE
    @PostMapping
    public Result add(@RequestBody InputProductDto inputProductDto){
        return inputProductService.addInputProductService(inputProductDto);

    }

    //READ
    @GetMapping
    public List<InputProduct> get(){
        return inputProductService.getInputProduct();
    }


    //READ BY ID
    @GetMapping(value = "/{id}")
    public InputProduct getById(@PathVariable Integer id){
        return inputProductService.getInputProductById(id);
    }

    //DELETE
    @DeleteMapping(value = "/{id}")
    public Result delete(@PathVariable Integer id){
        return  inputProductService.deleteInputProduct(id);
    }

    //UPDATE
    @PutMapping(value = "/{id}")
    public Result update(@PathVariable Integer id, @RequestBody InputProductDto inputProductDto){
        return inputProductService.updateInputProduct(id, inputProductDto);
    }
}
