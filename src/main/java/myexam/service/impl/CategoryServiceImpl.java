package myexam.service.impl;

import myexam.model.entity.Category;
import myexam.model.entity.CategoryName;
import myexam.repository.CategoryRepository;
import myexam.service.CategoryService;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void initCategories() {

        if (this.categoryRepository.count() == 0){
            Arrays.stream(CategoryName.values())
                    .forEach(categoryName -> {
                        this.categoryRepository
                                .save(new Category(categoryName,
                                        String.format("Dscription for %s", categoryName.name())));
                    });
        }

    }

    @Override
    public Category find(CategoryName categoryName) {
        return this.categoryRepository
                .findByCategoryName(categoryName)
                .orElse(null);

    }
}
