package be.vdab.toys.products;

import be.vdab.toys.orders.OrderController;
import be.vdab.toys.orders.OrderDetails;
import be.vdab.toys.productLines.ProductLine;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@RequestMapping("products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    Stream<ProductDetails> findAll() {
        return productService.findAll()
                .stream()
                .map(product -> new ProductDetails(product));
    }



    private record ProductDetails(long id, String name, String scale, String description, String productLine) {
        ProductDetails(Product product){
            this(product.getId(),product.getName(),product.getScale(),product.getDescription(),product.getProductLine().getName());
        }
    }
}
