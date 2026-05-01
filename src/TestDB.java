import DAO.*;
import database.DBInit;
import enums.ArticalUnit;
import enums.ArticleCategory;
import model.Appointment;
import model.Owner;
import model.Pet;
import stock.Artical;

import java.sql.Connection;
import java.sql.DriverManager;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Scanner;

public class TestDB {
    public static void main(String[] args) {
        try {
            Connection conn = DriverManager.getConnection("jdbc:sqlite:clinic.db");
            System.out.println("Connected to SQLite DB!!");
        } catch (Exception e) {
            e.printStackTrace();
        }

        DBInit.init();

        OwnerDAO ownerDAO = new OwnerDAO();
        PetDAO petDAO = new PetDAO();
        AppointmentDAO appointmentDAO = new AppointmentDAO();
        ArticalDAO articalDAO = new ArticalDAO();
        BillDAO billDAO = new BillDAO();

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n--- Clinic Menu ---");
            System.out.println("1. Add Owner");
            System.out.println("2. Add Pet");
            System.out.println("3. Add Article");
            System.out.println("4. Add Appointment");
            System.out.println("5. Exit");
            System.out.print("Choose option: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    Owner owner = new Owner();
                    System.out.print("Owner name: ");
                    owner.setName(scanner.nextLine());

                    System.out.print("Owner email: ");
                    owner.setEmail(scanner.nextLine());

                    System.out.print("Owner phone: ");
                    owner.setPhoneNumber(scanner.nextInt());

                    ownerDAO.save(owner);
                    System.out.println("Owner saved!");
                    break;

                case 2:
                    Pet pet = new Pet();
                    System.out.print("Pet name: ");
                    pet.setName(scanner.nextLine());

                    petDAO.save(pet);
                    System.out.println("Pet saved!");
                    break;

                case 3:
                    Artical med = new Artical();

                    System.out.print("Article name: ");
                    med.setName(scanner.nextLine());

                    System.out.print("Quantity: ");
                    med.setQuantity(scanner.nextInt());

                    System.out.print("Low quantity threshold: ");
                    med.setLowquantity(scanner.nextInt());

                    System.out.print("Price: ");
                    med.setPrice(scanner.nextInt());
                    scanner.nextLine();

                    System.out.print("Expiration year: ");
                    int year = scanner.nextInt();
                    System.out.print("Expiration month: ");
                    int month = scanner.nextInt();
                    System.out.print("Expiration day: ");
                    int day = scanner.nextInt();
                    med.setExpirationDate(LocalDate.of(year, month, day));
                    scanner.nextLine();

                    System.out.print("Category ( MEDICINE, .....): ");
                    med.setCategory(ArticleCategory.valueOf(scanner.nextLine().toUpperCase()));

                    System.out.print("Unit ( BOX, OTHER): ");
                    med.setUnit(ArticalUnit.valueOf(scanner.nextLine().toUpperCase()));

                    articalDAO.save(med);
                    System.out.println("Article saved!");

                    break;

                case 4:
                    Appointment appt = new Appointment();
                    appt.setDate(LocalDateTime.now());
                    appointmentDAO.save(appt);
                    System.out.println("Appointment saved!");
                    break;

                case 5:
                    System.out.println("Exiting...");
                    return;

                default:
                    System.out.println("Invalid choice!");
            }
        }
    }
}
