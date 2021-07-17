package uz.pdp.appwarehouse.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.appwarehouse.entity.Client;
import uz.pdp.appwarehouse.entity.User;
import uz.pdp.appwarehouse.payload.Result;
import uz.pdp.appwarehouse.payload.UserDto;
import uz.pdp.appwarehouse.repository.UserRepository;
import uz.pdp.appwarehouse.repository.WarehouseRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    WarehouseRepository warehouseRepository;

    public Result addUserService(UserDto userDto){
        boolean existsByPhoneNumber = userRepository.existsByPhoneNumber(userDto.getPhoneNumber());
        if (existsByPhoneNumber) {
            return new Result("Bunday raqamli user mavjud", false);
        }
        User newUser=new User();
        newUser.setFirstName(userDto.getFirstName());
        newUser.setLastName(userDto.getLastName());
        newUser.setCode(getCode());
        newUser.setPassword(userDto.getPassword());
        newUser.setPhoneNumber(userDto.getPhoneNumber());
        newUser.setWarehouses(warehouseRepository.findAllById(userDto.getWarehousesId()));
        userRepository.save(newUser);
        return new Result("Muaffaqiyatli saqlandi",true);
    }
    public List<User> getUser(){
        return userRepository.findAll();
    }
    public  User getUserById(Integer id){
        Optional<User> optionalUser = userRepository.findById(id);
        if (!optionalUser.isPresent()) {
            return new User();
        }
        return optionalUser.get();
    }
    public  Result deleteUser(Integer id){
        Optional<User> optionalUser = userRepository.findById(id);
        if (!optionalUser.isPresent()) {
            return new Result("User not found", false);
        }
        userRepository.deleteById(id);
        return new Result("User deleted", true);
    }
    public Result updateUser(Integer id, UserDto userDto){
        Optional<User> optionalUser = userRepository.findById(id);
        if (!optionalUser.isPresent()) {
            return new Result("User topilmadi", false);
        }
        boolean existsByPhoneNumber = userRepository.existsByPhoneNumber(userDto.getPhoneNumber());
        if (existsByPhoneNumber) {
            return new Result("Bunday raqamli user mavjud", false);
        }
        User editedUser=optionalUser.get();
        editedUser.setFirstName(userDto.getFirstName());
        editedUser.setLastName(userDto.getLastName());
        editedUser.setPassword(userDto.getPassword());
        editedUser.setPhoneNumber(userDto.getPhoneNumber());
        editedUser.setWarehouses(warehouseRepository.findAllById(userDto.getWarehousesId()));
        userRepository.save(editedUser);
        return new Result("Muaffaqiyatli o'zgartirildi",true);
    }
    public Integer getCode(){
        List<Integer> integerList = userRepository.getAllCodeNative();
        Integer maxCode=0;
        for (Integer integer : integerList) {
            if (integer>maxCode){
                maxCode=integer;
            }
        }
        return maxCode;
    }
}

