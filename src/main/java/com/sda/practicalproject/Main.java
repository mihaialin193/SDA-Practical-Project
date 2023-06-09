package com.sda.practicalproject;

import com.sda.practicalproject.controler.ConsultController;
import com.sda.practicalproject.controler.PetController;
import com.sda.practicalproject.controler.VetController;
import com.sda.practicalproject.controler.menu.MenuItem;
import com.sda.practicalproject.repository.ConsultRepositoryImpl;
import com.sda.practicalproject.repository.PetRepositoryImpl;
import com.sda.practicalproject.repository.VetRepositoryImpl;
import com.sda.practicalproject.service.ConsultService;
import com.sda.practicalproject.service.ConsultServiceImpl;
import com.sda.practicalproject.service.PetServiceImpl;
import com.sda.practicalproject.service.VetServiceImpl;
import com.sda.practicalproject.utils.SessionManager;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        SessionManager.getSessionFactory().openSession();
        VetController vetController = new VetController(
                new VetServiceImpl(new VetRepositoryImpl()),
                scanner
        );

        PetController petController = new PetController(
                scanner, new PetServiceImpl(new PetRepositoryImpl())
        );

        ConsultController consultController = new ConsultController(
                scanner,
                new ConsultServiceImpl(
                        new VetRepositoryImpl(),
                        new PetRepositoryImpl(),
                        new ConsultRepositoryImpl()
                )
        );



        for (int i = 0; i < 100; i++) {
            System.out.println("////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////");

        }

        MenuItem selectedOption = MenuItem.UNKNOWN;

        while (selectedOption != MenuItem.EXIT) {
            MenuItem.printMenuItems();
            System.out.println(" ");
            System.out.println("Please select an option");
            try {
                int numericOption = Integer.parseInt(scanner.nextLine());
                selectedOption = MenuItem.searchByOption(numericOption);
            } catch (NumberFormatException e){
                System.out.println("Please use a numeric value");
                selectedOption= MenuItem.UNKNOWN;
            }
            switch (selectedOption) {
                case ADD_VET:
                    vetController.createVet();
                    break;
                case UPDATE_VET:
                    vetController.updateVet();
                    break;
                case DELETE_VET:
                    vetController.deleteVetById();
                    break;
                case VIEW_VET_LIST:
                    vetController.displayAllVets();
                    break;
                case VIEW_VET_BY_ID:
                    vetController.findVetById();
                    break;
                case ADD_PET:
                    petController.createPet();
                    break;
                case VIEW_PET_LIST:
                    petController.viewAllPets();
                    break;
                case VIEW_PET_BY_ID:
                    petController.viewPetById();
                    break;
                case DELETE_PET_BY_ID:
                    petController.deletePetById();
                case CREATE_CONSULT:
                    consultController.createConsult();
                    break;
                case VIEW_ALL_CONSULTS:
                    consultController.viewAllConsults();
                    break;
                case VIEW_CONSULT_BY_ID:
                    consultController.getConsultById();
                    break;
                case UPDATE_CONSULT:
                    consultController.updateConsultById();
                    break;
                case EXIT:
                    System.out.println("Goodbye");
                    break;
                case UNKNOWN:
                    System.out.println("Please insert a valid option");
                    break;
                default:
                    System.out.println("Option not implemented");
            }
        }
        SessionManager.shutdown();
    }
}