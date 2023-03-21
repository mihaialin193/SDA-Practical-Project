package com.sda.practicalproject.controler;

import com.sda.practicalproject.repository.exception.EntityUpdateFailedException;
import com.sda.practicalproject.service.ConsultService;
import com.sda.practicalproject.service.exception.EntityNotFoundException;

import java.time.LocalDate;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Date;
import java.util.Scanner;

public class ConsultController {
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
            System.out.println("Please add a date for the consult: YY-MM-DD");
            Date consultDate = Date.from(LocalDate.parse(scanner.nextLine(), DateTimeFormatter.ISO_LOCAL_DATE)
                    .atStartOfDay()
                    .toInstant(ZoneOffset.UTC));


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
}
