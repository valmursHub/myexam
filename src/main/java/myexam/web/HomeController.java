package myexam.web;

import myexam.model.entity.CategoryName;
import myexam.model.view.*;
import myexam.service.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Controller
public class HomeController {

    private final ProductService productService;
    private final ModelMapper modelMapper;

    public HomeController(ProductService productService, ModelMapper modelMapper) {
        this.productService = productService;
        this.modelMapper = modelMapper;
    }


//    @GetMapping("/")
//    public String index(){
//        return "index";
//    }

    @GetMapping("/")
    public ModelAndView index(HttpSession httpSession, ModelAndView modelAndView){

        //        return httpSession.getAttribute("user") == null ? "index" : "home";

        if (httpSession.getAttribute("user") == null){
            modelAndView.setViewName("index");
        }else {
            //TODO Tazi logika triabva da ia premestia v Service i mislia da napavia po edno ...ViewModel za vsiaka kategotia i da gi podavam, no niama vreme
            //TODO A cenata shte ia vzema(sumiram) ot allProducts i shte ia modelAndView.addObject
            List<ProductViewModel> allProducts =  this.productService.findAllProducts();
            List<FoodViewModel> foodViewModels = new ArrayList<>();
            List<DrinksViewModel> drinksViewModels = new ArrayList<>();
            List<HouseholdsViewModel> householdsViewModels = new ArrayList<>();
            List<OtherViewModel> otherViewModels = new ArrayList<>();

            BigDecimal totalPriceOfProducts = BigDecimal.ZERO;
            for (ProductViewModel product : allProducts) {
                totalPriceOfProducts = totalPriceOfProducts.add(product.getPrice());
            }

            for (ProductViewModel product : allProducts) {
                if (product.getCategoryName().equals(CategoryName.FOOD)){
                    foodViewModels.add(this.modelMapper.map(product, FoodViewModel.class));
                }
                if (product.getCategoryName().equals(CategoryName.DRINK)){
                    drinksViewModels.add(this.modelMapper.map(product, DrinksViewModel.class));
                }
                if (product.getCategoryName().equals(CategoryName.HOUSEHOLD)){
                    householdsViewModels.add(this.modelMapper.map(product, HouseholdsViewModel.class));
                }
                if (product.getCategoryName().equals(CategoryName.OTHER)){
                    otherViewModels.add(this.modelMapper.map(product, OtherViewModel.class));
                }
            }

            modelAndView.addObject("totalPriceOfProducts" ,totalPriceOfProducts);
            modelAndView.addObject("foodProducts" ,foodViewModels);
            modelAndView.addObject("drinksProducts" ,drinksViewModels);
            modelAndView.addObject("householdsProducts" ,householdsViewModels);
            modelAndView.addObject("otherProducts" ,otherViewModels);

            System.out.println();
            modelAndView.setViewName("home");
        }
        return modelAndView;
    }
}