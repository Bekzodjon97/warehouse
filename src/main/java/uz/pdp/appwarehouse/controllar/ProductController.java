package uz.pdp.appwarehouse.controllar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appwarehouse.entity.Product;
import uz.pdp.appwarehouse.entity.Warehouse;
import uz.pdp.appwarehouse.payload.ProductDto;
import uz.pdp.appwarehouse.payload.Result;
import uz.pdp.appwarehouse.service.ProductService;

import java.util.List;

@RestController
@RequestMapping(value = "/product")
public class ProductController {

    @Autowired
    ProductService productService;

    //CREATE
    @PostMapping
    public Result add(@RequestBody ProductDto productDto){
        return productService.addProductService(productDto);
    }

    //READ
    @GetMapping
    public List<Product> get(){
        return productService.getProduct();
    }


    //READ BY ID
    @GetMapping(value = "/{id}")
    public Product getById(@PathVariable Integer id){
        return productService.getProductById(id);
    }

    //DELETE
    @DeleteMapping(value = "/{id}")
    public Result delete(@PathVariable Integer id){
        return  productService.deleteProduct(id);
    }

    //UPDATE
    @PutMapping(value = "/{id}")
    public Result update(@PathVariable Integer id, @RequestBody ProductDto productDto){
        return productService.updateProduct(id, productDto);
    }
}
