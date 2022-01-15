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
public class Definition {//Tarif
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Double price;


    @ManyToOne(optional = false,fetch = FetchType.LAZY)
    private Paket paket;

    @ManyToOne(optional = false,fetch = FetchType.LAZY)
    private SimCard simCard;

    public Definition(String name, Double price, Paket paket) {
        this.name = name;
        this.price=price;
        this.paket=paket;
    }

    public Definition(String name, Double price, SimCard simCard) {
        this.name = name;
        this.price=price;
        this.simCard =simCard;

    }
}
