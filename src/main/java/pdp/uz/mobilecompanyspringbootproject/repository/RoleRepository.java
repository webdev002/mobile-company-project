package pdp.uz.mobilecompanyspringbootproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pdp.uz.mobilecompanyspringbootproject.entity.Role;
import pdp.uz.mobilecompanyspringbootproject.entity.enums.RoleName;

public interface RoleRepository extends JpaRepository<Role,Integer> {
    Role findAllByRoleName(RoleName roleName);
}
