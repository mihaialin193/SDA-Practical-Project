package com.sda.practicalproject.controler;

import com.sda.practicalproject.repository.exception.EntityUpdateFailedException;
import com.sda.practicalproject.service.PetService;

import javax.swing.text.DateFormatter;
import java.time.DateTimeException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Date;
import java.util.Scanner;

public class PetController {
    private final Scanner scanner;
    private final PetService petService;

    public PetController(Scanner scanner, PetService petService) {
        this.scanner = scanner;
        this.petService = petService;
    }

    public void createPet() {
        try {
            System.out.println("Please insert pet's race");
            String race = scanner.nextLine();
            System.out.println("Is your pet vaccinated? true/false");
            boolean isVaccinated = Boolean.parseBoolean(scanner.nextLine());
            System.out.println("Please let us know owner's name");
            String ownerName = scanner.nextLine();
            System.out.println("Please insert pet's date of birth: YY-MM-DD");
            Date dateOfBirth = Date.from(LocalDate.parse(scanner.nextLine(), DateTimeFormatter.ISO_LOCAL_DATE)
                    .atStartOfDay()
                    .toInstant(ZoneOffset.UTC));


            petService.addPet(race, dateOfBirth, isVaccinated, ownerName);
            System.out.println("Pet added successfully");
        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
        } catch (EntityUpdateFailedException e) {
            System.err.println(e.getMessage());
            System.out.println("Please retry");
        } catch (DateTimeParseException e) {
            System.err.println("Invalid date format");
        } catch (Exception e) {
            System.err.println("Internal server error");
        }
    }
}
