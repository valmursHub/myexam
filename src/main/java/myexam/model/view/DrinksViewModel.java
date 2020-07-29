package myexam.model.view;

import myexam.model.entity.CategoryName;

import java.math.BigDecimal;

public class DrinksViewModel {

    private String id;
    private String name;
    private BigDecimal price;
    private CategoryName categoryName;

    public DrinksViewModel() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public CategoryName getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(CategoryName categoryName) {
        this.categoryName = categoryName;
    }
}
