package pdp.uz.mobilecompanyspringbootproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pdp.uz.mobilecompanyspringbootproject.entity.User;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {

    Optional<User> findAllByFirstname(String firstname);
}
