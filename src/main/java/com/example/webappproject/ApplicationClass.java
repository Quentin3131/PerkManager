package com.example.webappproject;

import com.example.webappproject.enumerations.MembershipType;
import com.example.webappproject.enumerations.ProductType;
import com.example.webappproject.model.AppUser;
import com.example.webappproject.model.Perk;
import com.example.webappproject.repository.PerkRepository;
import com.example.webappproject.repository.ProfileRepository;
import com.example.webappproject.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;

@SpringBootApplication
public class ApplicationClass {

    public static void main(String[] args) {
        SpringApplication.run(ApplicationClass.class);
    }

    @Bean
    public CommandLineRunner demo(ProfileRepository profileRepository, PerkRepository perkRepository, UserRepository userRepository) {
        return (args) -> {
            AppUser user1 = new AppUser("aperson@gmail.com", "password");
            Perk perk1 = new Perk("10% off movies with VISA", MembershipType.VISA, ProductType.MOVIES, LocalDate.now(), LocalDate.now().plusMonths(1), user1);
            Perk perk2 = new Perk("Free domestic flight with 10,000 Air Miles", MembershipType.AIRMILES, ProductType.FLIGHTS, LocalDate.now(), LocalDate.now().plusMonths(6), user1);
            Perk perk3 = new Perk("Save 20% on hotel bookings with CAA", MembershipType.CAA, ProductType.HOTELS, LocalDate.now(), LocalDate.now().plusMonths(2), user1);

            perk3.upvote();
            perk3.upvote();
            perk1.upvote();
            perk1.upvote();
            perk3.upvote();
            perk3.upvote();
            perk3.upvote();

            userRepository.save(user1);
            perkRepository.save(perk1);
            perkRepository.save(perk2);
            perkRepository.save(perk3);

            System.out.println("Finding all perks!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
            perkRepository.findAll().forEach(perk -> {
                System.out.println(perk);
            });
            System.out.println("Finding all perks with the VISA membership!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
            perkRepository.findByMembership(MembershipType.VISA).forEach(perk -> {
                System.out.println(perk);
            });
            System.out.println("Sorting the perks by upvotes descending!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
            perkRepository.findAllByOrderByUpvotesDesc().forEach(perk -> {
                System.out.println(perk);
            });
        };
    }
}

