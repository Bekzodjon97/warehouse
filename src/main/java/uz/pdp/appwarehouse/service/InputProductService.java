package uz.pdp.appwarehouse.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.appwarehouse.entity.*;
import uz.pdp.appwarehouse.payload.InputProductDto;
import uz.pdp.appwarehouse.payload.OutputProductDto;
import uz.pdp.appwarehouse.payload.Result;
import uz.pdp.appwarehouse.repository.InputProductRepository;
import uz.pdp.appwarehouse.repository.InputRepository;
import uz.pdp.appwarehouse.repository.ProductRepository;
import uz.pdp.appwarehouse.repository.SupplierRepository;

import java.util.List;
import java.util.Optional;

@Service
public class InputProductService {

    @Autowired
    InputProductRepository inputProductRepository;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    InputRepository inputRepository;
    @Autowired
    SupplierRepository supplierRepository;

    public Result addInputProductService(InputProductDto inputProductDto){
        Optional<Product> optionalProduct = productRepository.findById(inputProductDto.getProductId());
        if (!optionalProduct.isPresent()) {
            return new Result("Bunday mahsulot mavjud  emas", false);
        }
        Optional<Input> optionalInput = inputRepository.findById(inputProductDto.getInputId());
        if (!optionalInput.isPresent()) {
            return new Result("Bunday kirim hujjati mavjud emas", false);
        }
        Optional<Supplier> optionalSupplier = supplierRepository.findById(inputProductDto.getSupplierId());
        if (!optionalSupplier.isPresent()) {
            return new Result("Bunday yetkazib beruchi mavjud emas", false);
        }
        InputProduct inputProduct=new InputProduct();
        inputProduct.setAmount(inputProductDto.getAmount());
        inputProduct.setInput(optionalInput.get());
        inputProduct.setProduct(optionalProduct.get());
        inputProduct.setPrice(inputProductDto.getPrice());
        inputProduct.setExpireDate(inputProductDto.getExpireDate());
        inputProduct.setSupplier(optionalSupplier.get());
        inputProductRepository.save(inputProduct);
        return new Result("Muaffaqiyatli saqlandi",true);
    }
    public List<InputProduct> getInputProduct(){
        return inputProductRepository.findAll();
    }
    public  InputProduct getInputProductById(Integer id){
        Optional<InputProduct> optionalInputProduct = inputProductRepository.findById(id);
        if (!optionalInputProduct.isPresent()) {
            return new InputProduct();
        }
        return optionalInputProduct.get();
    }
    public  Result deleteInputProduct(Integer id){
        Optional<InputProduct> optionalInputProduct = inputProductRepository.findById(id);
        if (!optionalInputProduct.isPresent()) {
            return new Result("InputProduct not found", false);
        }
        try {
            inputProductRepository.deleteById(id);
            return new Result("InputProduct deleted", true);
        } catch (Exception e) {
            return new Result("InputProduct not deleted", false);
        }
    }
    public Result updateInputProduct(Integer id, InputProductDto inputProductDto){
        Optional<InputProduct> optionalInputProduct = inputProductRepository.findById(id);
        if (!optionalInputProduct.isPresent()) {
            return new Result("Bunday kirim mavjud emas", false);
        }

        Optional<Product> optionalProduct = productRepository.findById(inputProductDto.getProductId());
        if (!optionalProduct.isPresent()) {
            return new Result("Bunday  mahsulot mavjud  emas", false);
        }
        Optional<Input> optionalInput = inputRepository.findById(inputProductDto.getInputId());
        if (!optionalInput.isPresent()) {
            return new Result("Bunday kirim  hujjati mavjud emas", false);
        }
        Optional<Supplier> optionalSupplier = supplierRepository.findById(inputProductDto.getSupplierId());
        if (!optionalSupplier.isPresent()) {
            return new Result("Bunday yetkazib beruchi mavjud emas", false);
        }
        InputProduct inputProduct=optionalInputProduct.get();
        inputProduct.setAmount(inputProductDto.getAmount());
        inputProduct.setInput(optionalInput.get());
        inputProduct.setProduct(optionalProduct.get());
        inputProduct.setPrice(inputProductDto.getPrice());
        inputProduct.setExpireDate(inputProductDto.getExpireDate());
        inputProduct.setSupplier(optionalSupplier.get());
        inputProductRepository.save(inputProduct);
        return new Result("Muaffaqiyatli o'zgartirildi",true);
    }
}

