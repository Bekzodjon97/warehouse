package uz.pdp.appwarehouse.controllar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appwarehouse.entity.Category;
import uz.pdp.appwarehouse.entity.Client;
import uz.pdp.appwarehouse.payload.CategoryDto;
import uz.pdp.appwarehouse.payload.Result;
import uz.pdp.appwarehouse.service.CategoryService;

import java.util.List;

@RestController
@RequestMapping(value = "category")
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    //CREATE
    @PostMapping
    public Result add(@RequestBody CategoryDto categoryDto){
        return categoryService.addCategoryService(categoryDto);

    }

    //READ
    @GetMapping
    public List<Category> get(){
        return categoryService.getCategory();
    }


    //READ BY ID
    @GetMapping(value = "/{id}")
    public Category getById(@PathVariable Integer id){
        return categoryService.getCategoryById(id);
    }

    //DELETE
    @DeleteMapping(value = "/{id}")
    public Result delete(@PathVariable Integer id){
        return  categoryService.deleteCategory(id);
    }

    //UPDATE
    @PutMapping(value = "/{id}")
    public Result update(@PathVariable Integer id, @RequestBody CategoryDto categoryDto){
        return categoryService.updateCategory(id, categoryDto);
    }
}
