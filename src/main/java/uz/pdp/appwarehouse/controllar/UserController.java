package uz.pdp.appwarehouse.controllar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appwarehouse.entity.Product;
import uz.pdp.appwarehouse.entity.User;
import uz.pdp.appwarehouse.payload.ProductDto;
import uz.pdp.appwarehouse.payload.Result;
import uz.pdp.appwarehouse.payload.UserDto;
import uz.pdp.appwarehouse.service.UserService;

import java.util.List;

@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    UserService userService;

    //CREATE
    @PostMapping
    public Result add(@RequestBody UserDto userDto){
        return userService.addUserService(userDto);
    }

    //READ
    @GetMapping
    public List<User> get(){
        return userService.getUser();
    }


    //READ BY ID
    @GetMapping(value = "/{id}")
    public User getById(@PathVariable Integer id){
        return userService.getUserById(id);
    }

    //DELETE
    @DeleteMapping(value = "/{id}")
    public Result delete(@PathVariable Integer id){
        return  userService.deleteUser(id);
    }

    //UPDATE
    @PutMapping(value = "/{id}")
    public Result update(@PathVariable Integer id, @RequestBody UserDto userDto){
        return userService.updateUser(id, userDto);
    }
}
