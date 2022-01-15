package pdp.uz.mobilecompanyspringbootproject.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Paket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String types;

    @Column(nullable = false)
    private Double price;

    @Column(nullable = false)
    private String amalQilishMuddati;

    @Column(nullable = false)
    private boolean active;

    @OneToMany(mappedBy = "paket",cascade = CascadeType.ALL)
    private List<Definition> definition;

}
