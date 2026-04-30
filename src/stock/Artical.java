package stock;

import enums.ArticleCategory;
import enums.ArticalUnit;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class Artical {

    private static long counter = 1; //  ID

    private Long id;
    private String name;
    private int quantity;
    private int lowquantity;
    private int price;
    private LocalDate expirationDate;
    private ArticleCategory category;
    private ArticalUnit unit;

    public Artical() {}

    public Artical(String name, int quantity, int lowquantity, int price,
                   LocalDate expirationDate, ArticleCategory category, ArticalUnit unit) {

        this.id = counter++;
        this.name = name;
        this.quantity = quantity;
        this.lowquantity = lowquantity;
        this.price = price;
        this.expirationDate = expirationDate;
        this.category = category;
        this.unit = unit;
    }
}