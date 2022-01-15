package pdp.uz.mobilecompanyspringbootproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pdp.uz.mobilecompanyspringbootproject.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer,Integer> {
}
