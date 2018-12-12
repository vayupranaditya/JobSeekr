package controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;
import java.util.*;
import model.Category;

@CrossOrigin
@RestController
@RequestMapping("category")
public class CategoryController {

    @PostMapping("add")
    public Category add(@RequestParam Map<String, String> params) {
        if (params.get("name").equals(null) && params.get("id").equals(null)) return null;
    	Category newCategory = new Category(
                params.get("name")
                );
        newCategory.save();
    	return newCategory.get(params.get("name"));
    }

    @GetMapping("index")
    public ArrayList <Category> index() {
        Category category = new Category();
        // ArrayList <Category> categoryList = category.getAll();
        ArrayList <Category> categoryList = new ArrayList <Category>();
    	return categoryList;
    }

    @GetMapping("view")
    public Category view(@RequestParam long id) {
    	Category category = new Category();
        return category.get(id);
    }


    @PostMapping("update")
    public Category update(@RequestParam Map<String, String> params) {
    	Category category = new Category();
        category = category.get(params.get("id"));
        category.setName(params.get("name"));
        category.update();
        return category.get(params.get("id"));
    }

    @PostMapping("delete")
    public String delete(@RequestParam long id) {
    	Category category = new Category();
    	category = category.get(id);
        category.delete();
        return "Success";
    }
}