package model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
public class Pet {

    private Long id;
    private String name;
    private String race;
    private int age;
    private int weight;
    private boolean isNeutered;
    private Owner owner;

    private ArrayList<Appointment> appointments = new ArrayList<>();
    private ArrayList<Pet> pets = new ArrayList<>();

    public Pet() {}

    public void addPet(Pet pet) {
        pets.add(pet);
        pet.setOwner(this.getOwner());
    }

    public void removePet(Pet pet) {
        pets.remove(pet);
        pet.setOwner(null);
    }

    public Pet findPetByName(String name) {
        for (Pet p : pets) {
            if (p.getName().equalsIgnoreCase(name)) {
                return p;
            }
        }
        return null;
    }

    public void addAppointment(Appointment appointment) {
        appointments.add(appointment);
        appointment.setPet(this);
    }

    public void removeAppointment(Appointment appointment) {
        appointments.remove(appointment);
    }

    public ArrayList<Appointment> getNextAppointments() {
        ArrayList<Appointment> upcoming = new ArrayList<>();

        for (Appointment a : appointments) {
            if (a.getDate().isAfter(java.time.LocalDateTime.now())) {
                upcoming.add(a);
            }
        }

        return upcoming;
    }
}
