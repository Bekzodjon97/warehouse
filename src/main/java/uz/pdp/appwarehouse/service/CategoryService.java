package uz.pdp.appwarehouse.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.appwarehouse.entity.Category;
import uz.pdp.appwarehouse.payload.CategoryDto;
import uz.pdp.appwarehouse.payload.Result;
import uz.pdp.appwarehouse.repository.CategoryRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    CategoryRepository categoryRepository;

    public Result addCategoryService(CategoryDto categoryDto){
        boolean existsByName = categoryRepository.existsByName(categoryDto.getName());
        if (existsByName) {
            return new Result("Bunday karegoriya mavjud", false);
        }
        Optional<Category> optionalCategory = categoryRepository.findById(categoryDto.getParentCategoryId());
        if (!optionalCategory.isPresent()) {
            return new Result("Parent category not found", false);
        }
        Category newCategory=new Category();
        newCategory.setName(categoryDto.getName());
        newCategory.setParentCategory(optionalCategory.get());
        categoryRepository.save(newCategory);
        return new Result("Muaffaqiyatli saqlandi",true);
    }
    public List<Category> getCategory(){
        return categoryRepository.findAll();
    }
    public  Category getCategoryById(Integer id){
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        return optionalCategory.orElseGet(Category::new);
    }
    public  Result deleteCategory(Integer id){
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        if (!optionalCategory.isPresent()) {
            return new Result("Category not found", false);
        }
        categoryRepository.deleteById(id);
        return new Result("Category deleted", true);
    }
    public Result updateCategory(Integer id, CategoryDto categoryDto){
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        if (!optionalCategory.isPresent()) {
            return  new Result("Category not found", false);
        }
        Optional<Category> optionalParentCategory = categoryRepository.findById(categoryDto.getParentCategoryId());
        if (!optionalParentCategory.isPresent()) {
            return new Result("Parent category not found", false);
        }
        Category editedCategory = optionalParentCategory.get();
        editedCategory.setName(categoryDto.getName());
        editedCategory.setParentCategory(optionalParentCategory.get());
        categoryRepository.save(editedCategory);
        return new Result("Category edited", true);
    }
}

