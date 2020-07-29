package myexam.service;

import myexam.model.entity.Product;
import myexam.model.service.ProductServiceModel;
import myexam.model.view.ProductViewModel;

import java.util.List;

public interface ProductService {
    void addProduct(ProductServiceModel productServiceModel);

    List<ProductViewModel> findAllProducts();

    void delete(String id);

    void deleteAll();

}
