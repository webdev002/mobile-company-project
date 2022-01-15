package pdp.uz.mobilecompanyspringbootproject.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Branches {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String address;

    private String managerDirector;

    @OneToMany(mappedBy = "branches",cascade = CascadeType.ALL)
    private List<Employee> employees;
}
