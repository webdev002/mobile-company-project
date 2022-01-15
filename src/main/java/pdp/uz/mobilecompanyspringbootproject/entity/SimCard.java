package pdp.uz.mobilecompanyspringbootproject.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class SimCard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String number;

    @Column(nullable = false)
    private Double balance;

    @OneToMany(mappedBy = "simCard",cascade = CascadeType.ALL)
    private List<Definition> definition;


    @ManyToOne(optional = false,fetch = FetchType.LAZY)
    private Customer customer;


    private boolean active;

    public SimCard(String number, Double balance, boolean active, Customer customer) {
        this.number=number;
        this.balance =balance;
        this.active =active;
        this.customer=customer;
    }
}
