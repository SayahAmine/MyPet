package stock;

import enums.ArticleCategory;
import enums.ArticalUnit;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.*;

public class ArticalManager {

    private List<Artical> articals = new ArrayList<>();
    Scanner scanner = new Scanner(System.in);

    public void addArtical() {
        try {
            System.out.print("Enter artical name: ");
            String name = scanner.nextLine();

            System.out.print("Enter quantity: ");
            int quantity = Integer.parseInt(scanner.nextLine());

            System.out.print("Enter low quantity alert: ");
            int lowquantity = Integer.parseInt(scanner.nextLine());

            System.out.print("Enter price: ");
            int price = Integer.parseInt(scanner.nextLine());

            System.out.print("Enter expiration date (yyyy-MM-dd): ");
            String dateStr = scanner.nextLine();
            LocalDate expirationDate = LocalDate.parse(dateStr, DateTimeFormatter.ISO_LOCAL_DATE);

            System.out.print("Enter category : ");
            ArticleCategory category = ArticleCategory.valueOf(scanner.nextLine().toUpperCase());

            System.out.print("Enter unit (box, others...): ");
            ArticalUnit unit = ArticalUnit.valueOf(scanner.nextLine().toUpperCase());


            if (quantity < 0) {
                System.out.println("Quantity must be >= 0");
                return;
            }

            if (expirationDate.isBefore(LocalDate.now())) {
                System.out.println("Expiration date must be in the future");
                return;
            }

            Artical artical = new Artical(name, quantity, lowquantity, price,
                    expirationDate, category, unit);

            articals.add(artical);

            System.out.println("Artical added successfully!");

        } catch (Exception e) {
            System.out.println("Invalid input: " + e.getMessage());
        }
    }

    public void updateArtical() {


        System.out.print("Enter artical name: ");
        String name = scanner.nextLine();

        for(Artical artical : articals) {
            if(artical.getName().equals(name)) {
                System.out.print("Enter quantity: ");
                int quantity = Integer.parseInt(scanner.nextLine());
                artical.setQuantity(quantity);
                System.out.print("Enter low quantity alert: ");
                int lowquantity = Integer.parseInt(scanner.nextLine());
                artical.setLowquantity(lowquantity);
                System.out.print("Enter price: ");
                int price = Integer.parseInt(scanner.nextLine());
                artical.setPrice(price);

            }
        }
    }

    public  void deleteArtical() {

        System.out.print("Enter artical name: ");
        String name = scanner.nextLine();

        Iterator<Artical> iterator = articals.iterator();

        while (iterator.hasNext()) {
            if (iterator.next().getName().equals(name)) {
                iterator.remove();
                System.out.println("Artical deleted successfully!");
                break;
            }
        }

        System.out.println("Artical dose not  exists!");
    }

    public void checkLowStock() {

        for (Artical artical : articals) {
            if (artical.getLowquantity() <5) {
                System.out.println( artical.getName() + " is low (" + artical.getQuantity() + ")");

            }
        }
    }

    public  void checkExpiry() {

        LocalDate today = LocalDate.now();

        for (Artical artical : articals) {
            long days = ChronoUnit.DAYS.between(today, artical.getExpirationDate());

            if (days <= 30) {
                System.out.println(artical.getName() + " expires in " + days + " days");
            } else if (days ==0) {
                System.out.println(artical.getName() + " artical in exprired ");

            }
        }
    }

    public void displayArticals() {
        for (Artical artical : articals) {
            System.out.println(artical);
        }
    }
}