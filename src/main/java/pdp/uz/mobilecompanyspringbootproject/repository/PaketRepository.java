package pdp.uz.mobilecompanyspringbootproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pdp.uz.mobilecompanyspringbootproject.entity.Paket;

public interface PaketRepository extends JpaRepository<Paket,Integer> {

    boolean existsByAmalQilishMuddatiAndIdNot(String amalQilishMuddati, Integer id);
}
