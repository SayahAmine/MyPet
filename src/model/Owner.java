package model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.util.ArrayList;

@Getter
@Setter
public class Owner {

    private Long id;

    private  String name;
    private String email;
    private int phoneNumber;
    private ArrayList<Pet> pets = new ArrayList<>();

    public Owner() {}

    public void addPet(Pet pet) {
        pets.add(pet);
        pet.setOwner(this);
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
}
