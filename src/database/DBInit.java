package database;



import java.sql.Connection;
import java.sql.Statement;

public class DBInit {

    public static void init() {

        try (Connection conn = DBConnection.connect();
             Statement stmt = conn.createStatement()) {

            // OWNER
            stmt.execute("""
                CREATE TABLE IF NOT EXISTS owner (
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    name TEXT,
                    email TEXT,
                    phone INTEGER
                )
            """);

            // PET
            stmt.execute("""
                CREATE TABLE IF NOT EXISTS pet (
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    name TEXT,
                    race TEXT,
                    age INTEGER,
                    weight INTEGER,
                    isNeutered BOOLEAN,
                    owner_id INTEGER,
                    FOREIGN KEY(owner_id) REFERENCES owner(id)
                )
            """);

            // APPOINTMENT
            stmt.execute("""
                CREATE TABLE IF NOT EXISTS appointment (
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    pet_id INTEGER,
                    date TEXT,
                    FOREIGN KEY(pet_id) REFERENCES pet(id)
                )
            """);

            // ARTICAL
            stmt.execute("""
                CREATE TABLE IF NOT EXISTS artical (
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    name TEXT,
                    quantity INTEGER,
                    lowquantity INTEGER,
                    price INTEGER,
                    expirationDate TEXT,
                    category TEXT,
                    unit TEXT
                )
            """);

            // BILL
            stmt.execute("""
                CREATE TABLE IF NOT EXISTS bill (
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    amount REAL,
                    appointment_id INTEGER,
                    FOREIGN KEY(appointment_id) REFERENCES appointment(id)
                )
            """);

            // BILL_ARTICAL (relation table)!!!
            stmt.execute("""
                CREATE TABLE IF NOT EXISTS bill_artical (
                    bill_id INTEGER,
                    artical_id INTEGER,
                    PRIMARY KEY (bill_id, artical_id),
                    FOREIGN KEY(bill_id) REFERENCES bill(id),
                    FOREIGN KEY(artical_id) REFERENCES artical(id)
                )
            """);

            System.out.println("Database initialized!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}