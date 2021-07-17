package uz.pdp.appwarehouse.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.appwarehouse.entity.Client;
import uz.pdp.appwarehouse.entity.Output;
import uz.pdp.appwarehouse.entity.OutputProduct;
import uz.pdp.appwarehouse.entity.Product;
import uz.pdp.appwarehouse.payload.OutputProductDto;
import uz.pdp.appwarehouse.payload.Result;
import uz.pdp.appwarehouse.repository.OutputProductRepository;
import uz.pdp.appwarehouse.repository.OutputRepository;
import uz.pdp.appwarehouse.repository.ProductRepository;

import java.util.List;
import java.util.Optional;

@Service
public class OutputProductService {

    @Autowired
    OutputProductRepository outputProductRepository;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    OutputRepository outputRepository;

    public Result addOutputProductService(OutputProductDto outputProductDto){
        boolean existsByProductIdAndAmountAndPriceAndOutputId = outputProductRepository.existsByProductIdAndAmountAndPriceAndOutputId(outputProductDto.getProductId(),outputProductDto.getAmount(),outputProductDto.getPrice(),outputProductDto.getOutputId());
        if (existsByProductIdAndAmountAndPriceAndOutputId) {
            return new Result("Bunday chiqim mavjud", false);
        }
        Optional<Product> optionalProduct = productRepository.findById(outputProductDto.getProductId());
        if (!optionalProduct.isPresent()) {
            return new Result("Bunday mahsulot mavjud emas", false);
        }
        Optional<Output> optionalOutput = outputRepository.findById(outputProductDto.getOutputId());
        if (!optionalOutput.isPresent()) {
            return new Result("Bunday chiqim hujjati mavjud emas", false);
        }
        OutputProduct outputProduct=new OutputProduct();
        outputProduct.setAmount(outputProductDto.getAmount());
        outputProduct.setOutput(optionalOutput.get());
        outputProduct.setProduct(optionalProduct.get());
        outputProduct.setPrice(outputProductDto.getPrice());
        outputProductRepository.save(outputProduct);
        return new Result("Muaffaqiyatli saqlandi",true);
    }
    public List<OutputProduct> getOutputProduct(){
        return outputProductRepository.findAll();
    }
    public  OutputProduct getOutputProductById(Integer id){
        Optional<OutputProduct> optionalOutputProduct = outputProductRepository.findById(id);
        if (!optionalOutputProduct.isPresent()) {
            return new OutputProduct();
        }
        return optionalOutputProduct.get();
    }
    public  Result deleteOutputProduct(Integer id){
        Optional<OutputProduct> optionalOutputProduct = outputProductRepository.findById(id);
        if (!optionalOutputProduct.isPresent()) {
            return new Result("OutputProduct not found", false);
        }
        try {
            outputProductRepository.deleteById(id);
            return new Result("OutputProduct deleted", true);
        } catch (Exception e) {
            return new Result("OutputProduct not deleted", false);
        }
    }
    public Result updateOutputProduct(Integer id, OutputProductDto outputProductDto){
        Optional<OutputProduct> optionalOutputProduct = outputProductRepository.findById(id);
        if (!optionalOutputProduct.isPresent()) {
            return new Result("Bunday chiquvchi mahsulot mavjud emas", false);
        }
        boolean existsByProductIdAndAmountAndPriceAndOutputId = outputProductRepository.existsByProductIdAndAmountAndPriceAndOutputId(outputProductDto.getProductId(),outputProductDto.getAmount(),outputProductDto.getPrice(),outputProductDto.getOutputId());
        if (existsByProductIdAndAmountAndPriceAndOutputId) {
            return new Result("Bunday chiqim  mavjud", false);
        }
        Optional<Product> optionalProduct = productRepository.findById(outputProductDto.getProductId());
        if (!optionalProduct.isPresent()) {
            return new Result("Bunday mahsulot mavjud emas", false);
        }
        Optional<Output> optionalOutput = outputRepository.findById(outputProductDto.getOutputId());
        if (!optionalOutput.isPresent()) {
            return new Result("Bunday chiqim hujjati mavjud emas", false);
        }
        OutputProduct outputProduct=optionalOutputProduct.get();
        outputProduct.setAmount(outputProductDto.getAmount());
        outputProduct.setOutput(optionalOutput.get());
        outputProduct.setProduct(optionalProduct.get());
        outputProduct.setPrice(outputProductDto.getPrice());
        outputProductRepository.save(outputProduct);
        return new Result("Muaffaqiyatli o'zgartirildi",true);
    }
}

