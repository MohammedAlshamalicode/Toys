package be.vdab.toys.products;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findByProductLineName(String productLineName);

    @Modifying
    @Query(value = """
             update products p
             join orderdetails od on p.id = od.productid
             set p.inStock = p.inStock - od.ordered,
                 p.inOrder = p.inOrder - od.ordered
             where od.orderId = :orderId
            """, nativeQuery = true)
    void updateStock(long orderId);
}
