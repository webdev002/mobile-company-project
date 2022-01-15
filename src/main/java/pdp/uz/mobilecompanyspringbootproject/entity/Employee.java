package pdp.uz.mobilecompanyspringbootproject.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pdp.uz.mobilecompanyspringbootproject.payload.EmployeeDto;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String phoneNumber;

    @ManyToOne(optional = false,fetch =FetchType.LAZY)
    private Branches branches;

    public Employee(String name, String phoneNumber, Branches branches) {
        this.name =name;
        this.phoneNumber=phoneNumber;
        this.branches=branches;

    }
}
