package uz.pdp.appwarehouse.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.appwarehouse.entity.Measurement;
import uz.pdp.appwarehouse.entity.Supplier;
import uz.pdp.appwarehouse.payload.Result;
import uz.pdp.appwarehouse.repository.SupplierRepository;

import java.util.List;
import java.util.Optional;

@Service
public class SupplierService {

    @Autowired
    SupplierRepository supplierRepository;

    public Result addSupplierService(Supplier supplier){
        boolean existsByPhoneNumber = supplierRepository.existsByPhoneNumber(supplier.getPhoneNumber());
        if (existsByPhoneNumber) {
            return new Result("Bunday raqamli yetkazuvchi mavjud", false);
        }
        supplierRepository.save(supplier);
        return new Result("Muaffaqiyatli saqlandi",true);
    }
    public List<Supplier> getSupplier(){
        return supplierRepository.findAll();
    }
    public  Supplier getSupplierById(Integer id){
        Optional<Supplier> optionalSupplier = supplierRepository.findById(id);
        if (!optionalSupplier.isPresent()) {
            return new Supplier();
        }
        return optionalSupplier.get();
    }
    public  Result deleteSupplier(Integer id){
        Optional<Supplier> optionalSupplier = supplierRepository.findById(id);
        if (!optionalSupplier.isPresent()) {
            return new Result("Supplier not found", false);
        }
        supplierRepository.deleteById(id);
        return new Result("Supplier deleted", true);
    }
    public Result updateSupplier(Integer id, Supplier supplier){
        Optional<Supplier> optionalSupplier = supplierRepository.findById(id);
        if (!optionalSupplier.isPresent()) {
            return  new Result("Supplier not found", false);
        }
        Supplier editedSupplier = optionalSupplier.get();
        editedSupplier.setName(supplier.getName());
        editedSupplier.setPhoneNumber(supplier.getPhoneNumber());
        supplierRepository.save(editedSupplier);
        return new Result("Supplier edited", true);
    }
}

