


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

public class TestDB {
    public static void main(String[] args) {
        try {
            Connection conn = DriverManager.getConnection("jdbc:sqlite:clinic.db");
            System.out.println(" Connected to SQLite DB!!");
        } catch (Exception e) {
            e.printStackTrace();
        }

        DBInit.init();

        OwnerDAO ownerDAO = new OwnerDAO();
        PetDAO petDAO = new PetDAO();
        AppointmentDAO appointmentDAO = new AppointmentDAO();
        ArticalDAO articalDAO = new ArticalDAO();
        BillDAO billDAO = new BillDAO();

        //  Owner
        Owner owner = new Owner();
        owner.setName("Ali");
        owner.setEmail("ali@mail.com");
        owner.setPhoneNumber(123456);
        ownerDAO.save(owner);

        //  Pet
        Pet pet = new Pet();
        pet.setName("Cat");
        pet.setOwner(owner);
        petDAO.save(pet);

        // Artical
        Artical med = new Artical();
        med.setName("Paracetamol");
        med.setQuantity(100);
        med.setLowquantity(10);
        med.setPrice(50);
        med.setExpirationDate(LocalDate.of(2026, 12, 31));
        med.setCategory(ArticleCategory.MEDICINE);
        med.setUnit(ArticalUnit.BOX);


        articalDAO.save(med);


        // Appointment
        Appointment appt = new Appointment();
        appt.setPet(pet);
        appt.setDate(LocalDateTime.now());
        appointmentDAO.save(appt);

        // Bill
        Appointment.Bill bill = new Appointment.Bill();
        bill.addArtical(med);

        billDAO.save(bill, appt.getId());


    }
}