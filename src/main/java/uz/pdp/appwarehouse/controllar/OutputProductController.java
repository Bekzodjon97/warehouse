package uz.pdp.appwarehouse.controllar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appwarehouse.entity.Client;
import uz.pdp.appwarehouse.entity.OutputProduct;
import uz.pdp.appwarehouse.payload.OutputProductDto;
import uz.pdp.appwarehouse.payload.Result;
import uz.pdp.appwarehouse.service.OutputProductService;

import java.util.List;

@RestController
@RequestMapping(value = "/output/product")
public class OutputProductController {

    @Autowired
    OutputProductService outputProductService;

    //CREATE
    @PostMapping
    public Result add(@RequestBody OutputProductDto outputProductDto){
        return outputProductService.addOutputProductService(outputProductDto);

    }

    //READ
    @GetMapping
    public List<OutputProduct> get(){
        return outputProductService.getOutputProduct();
    }


    //READ BY ID
    @GetMapping(value = "/{id}")
    public OutputProduct getById(@PathVariable Integer id){
        return outputProductService.getOutputProductById(id);
    }

    //DELETE
    @DeleteMapping(value = "/{id}")
    public Result delete(@PathVariable Integer id){
        return  outputProductService.deleteOutputProduct(id);
    }

    //UPDATE
    @PutMapping(value = "/{id}")
    public Result update(@PathVariable Integer id, @RequestBody OutputProductDto outputProductDto){
        return outputProductService.updateOutputProduct(id, outputProductDto);
    }
}
