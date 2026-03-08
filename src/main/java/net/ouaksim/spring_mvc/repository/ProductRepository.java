package net.ouaksim.spring_mvc.repository;

import net.ouaksim.spring_mvc.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
