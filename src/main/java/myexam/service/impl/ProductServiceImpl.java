package myexam.service.impl;

import myexam.model.entity.Product;
import myexam.model.service.ProductServiceModel;
import myexam.model.view.ProductViewModel;
import myexam.repository.ProductRepository;
import myexam.service.CategoryService;
import myexam.service.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;
    private final CategoryService categoryService;

    public ProductServiceImpl(ProductRepository productRepository, ModelMapper modelMapper, CategoryService categoryService) {
        this.productRepository = productRepository;
        this.modelMapper = modelMapper;
        this.categoryService = categoryService;
    }

    @Override
    public void addProduct(ProductServiceModel productServiceModel) {

        Product product = this.modelMapper.map(productServiceModel, Product.class);

        product.setCategory(this.categoryService.find(productServiceModel.getCategory().getCategoryName()));

        this.productRepository.saveAndFlush(product);

    }

    @Override
    public List<ProductViewModel> findAllProducts() {

        return this.productRepository.findAll()
                .stream()
                .map(product -> this.modelMapper.map(product, ProductViewModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public void delete(String id) {
        this.productRepository.deleteById(id);
    }

    @Override
    public void deleteAll() {
        this.productRepository.deleteAll();
    }
}
