package pdp.uz.mobilecompanyspringbootproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pdp.uz.mobilecompanyspringbootproject.entity.Branches;

public interface BranchRepository extends JpaRepository<Branches,Integer> {
    boolean existsByManagerDirectorAndIdNot(String managerDirector, Integer id);
}
