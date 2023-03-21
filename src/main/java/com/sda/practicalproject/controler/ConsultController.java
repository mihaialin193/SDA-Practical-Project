package com.sda.practicalproject.controler;

import com.sda.practicalproject.model.Consult;
import com.sda.practicalproject.repository.exception.EntityUpdateFailedException;
import com.sda.practicalproject.service.ConsultService;
import com.sda.practicalproject.service.exception.EntityNotFoundException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Date;
import java.util.Optional;
import java.util.Scanner;


public class ConsultController {
    private static final DateTimeFormatter CONSULT_TIME = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    private final Scanner scanner;
    private final ConsultService consultService;


    public ConsultController(Scanner scanner, ConsultService consultService) {
        this.scanner = scanner;
        this.consultService = consultService;
    }

    public void createConsult() {
        try {
            System.out.println("Please add vet id");
            long vetId = Long.parseLong(scanner.nextLine().trim());
            System.out.println("Please add pet id");
            long petId = Long.parseLong(scanner.nextLine().trim());
            System.out.println("Please add description");
            String description = scanner.nextLine();
            System.out.println("Please add a date for the consult: YY-MM-DD HH:MM");
            Date consultDate = Date.from(LocalDateTime.parse(scanner.nextLine(), CONSULT_TIME)
                    .toInstant(ZoneOffset.of("+2")));


            consultService.createConsult(vetId, petId, consultDate, description);
        } catch (NumberFormatException e) {
            System.err.println("Please insert a numeric value for petid/vetid");
        } catch (EntityUpdateFailedException e) {
            System.err.println(e.getMessage());
            System.out.println("Please retry");
        } catch (EntityNotFoundException e) {
            System.err.println(e.getMessage());
        } catch (DateTimeParseException e) {
            System.err.println("Invalid date format");
        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
        } catch (Exception e) {
            System.err.println("Internal server error");
        }
    }

    public void viewAllConsults() {
        consultService.getAllConsults().
                forEach(c ->
                        System.out.println(
                                "Consult id: " + c.getId() +
                                        " vet first and lastname " + c.getVet().getLastName()
                                        + " " + c.getVet().getFirstName() +
                                        " pet owner's name " + c.getPet().getOwnerName() +
                                        " date of consult " + c.getAppointmentDate()
                        ));
    }

    public void getConsultById() {
        try {
            System.out.println("Please insert consult id");
            long id = Long.parseLong(scanner.nextLine().trim());
            Optional<Consult> consultOptional = consultService.getConsultById(id);
            if (consultOptional.isPresent()) {
                Consult consult = consultOptional.get();
                System.out.println(consult + " " + consult.getVet() + " " + consult.getPet());
            } else {
                System.err.println("Consult not found");
            }
        } catch (NumberFormatException e) {
            System.err.println("Please insert a numeric value ");
        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
        } catch (Exception e) {
            System.err.println("Internal server error");
        }
    }

    public void updateConsultById() {
        try {
            System.out.println("Please insert consult id");
            long id = Long.parseLong(scanner.nextLine().trim());
            System.out.println("Please add description");
            String description = scanner.nextLine();
            consultService.updateConsult(id, description);
            System.out.println("Consult updated");
        } catch (
                NumberFormatException e) {
            System.err.println("Please insert a numeric value for id");
        } catch (
                EntityUpdateFailedException e) {
            System.err.println(e.getMessage());
            System.out.println("Please retry");
        } catch (
                EntityNotFoundException e) {
            System.err.println(e.getMessage());
        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
        } catch (
                Exception e) {
            System.err.println("Internal server error");
        }
    }
}
