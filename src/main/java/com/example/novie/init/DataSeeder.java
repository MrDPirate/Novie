package com.example.novie.init;

import com.example.novie.model.*;
import com.example.novie.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.Year;
import java.util.HashSet;
import java.util.Set;

@Configuration
public class DataSeeder {

    @Bean
    CommandLineRunner initDatabase(
            CategoryRepository categoryRepository,
            MovieRepository movieRepository,
            UserRepository userRepository,
            RoleRepository roleRepository,
            UserProfileRepository userProfileRepository,
            ReviewRepository reviewRepository,
            PasswordEncoder passwordEncoder) {

        return args -> {
            // Check if data already exists
            if (categoryRepository.count() > 0) {
                System.out.println("Database already seeded. Skipping seed data...");
                return;
            }

            System.out.println("Starting database seeding...");

            // ========== SEED CATEGORIES ==========
            Category actionCategory = new Category();
            actionCategory.setName("Action");
            actionCategory = categoryRepository.save(actionCategory);

            Category dramaCategory = new Category();
            dramaCategory.setName("Drama");
            dramaCategory = categoryRepository.save(dramaCategory);

            Category sciFiCategory = new Category();
            sciFiCategory.setName("Science Fiction");
            sciFiCategory = categoryRepository.save(sciFiCategory);

            Category thrillerCategory = new Category();
            thrillerCategory.setName("Thriller");
            thrillerCategory = categoryRepository.save(thrillerCategory);

            Category comedyCategory = new Category();
            comedyCategory.setName("Comedy");
            comedyCategory = categoryRepository.save(comedyCategory);

            Category horrorCategory = new Category();
            horrorCategory.setName("Horror");
            horrorCategory = categoryRepository.save(horrorCategory);

            Category romanceCategory = new Category();
            romanceCategory.setName("Romance");
            romanceCategory = categoryRepository.save(romanceCategory);

            Category fantasyCategory = new Category();
            fantasyCategory.setName("Fantasy");
            fantasyCategory = categoryRepository.save(fantasyCategory);

            Category crimeCategory = new Category();
            crimeCategory.setName("Crime");
            crimeCategory = categoryRepository.save(crimeCategory);

            Category adventureCategory = new Category();
            adventureCategory.setName("Adventure");
            adventureCategory = categoryRepository.save(adventureCategory);

            System.out.println("✓ Categories seeded successfully");

            // ========== SEED MOVIES ==========
            Movie inception = new Movie();
            inception.setName("Inception");
            inception.setDescription("A skilled thief is offered a chance at redemption by planting an idea into a target's subconscious through dream infiltration.");
            inception.setYear(Year.of(2010));
            inception.setCategory(sciFiCategory);
            Set<Category> inceptionSubs = new HashSet<>();
            inceptionSubs.add(actionCategory);
            inceptionSubs.add(thrillerCategory);
            inception.setSubCategories(inceptionSubs);
            movieRepository.save(inception);

            Movie darkKnight = new Movie();
            darkKnight.setName("The Dark Knight");
            darkKnight.setDescription("Batman must accept one of the greatest psychological and physical tests to fight injustice when the menace known as the Joker wreaks havoc on Gotham City.");
            darkKnight.setYear(Year.of(2008));
            darkKnight.setCategory(actionCategory);
            Set<Category> darkKnightSubs = new HashSet<>();
            darkKnightSubs.add(crimeCategory);
            darkKnightSubs.add(dramaCategory);
            darkKnight.setSubCategories(darkKnightSubs);
            movieRepository.save(darkKnight);

            Movie matrix = new Movie();
            matrix.setName("The Matrix");
            matrix.setDescription("A computer hacker learns about the true nature of reality and his role in the war against its controllers.");
            matrix.setYear(Year.of(1999));
            matrix.setCategory(sciFiCategory);
            Set<Category> matrixSubs = new HashSet<>();
            matrixSubs.add(actionCategory);
            matrix.setSubCategories(matrixSubs);
            movieRepository.save(matrix);

            Movie interstellar = new Movie();
            interstellar.setName("Interstellar");
            interstellar.setDescription("A team of explorers travel through a wormhole in space in an attempt to ensure humanity's survival.");
            interstellar.setYear(Year.of(2014));
            interstellar.setCategory(sciFiCategory);
            Set<Category> interstellarSubs = new HashSet<>();
            interstellarSubs.add(dramaCategory);
            interstellarSubs.add(adventureCategory);
            interstellar.setSubCategories(interstellarSubs);
            movieRepository.save(interstellar);

            Movie parasite = new Movie();
            parasite.setName("Parasite");
            parasite.setDescription("Greed and class discrimination threaten the newly formed symbiotic relationship between the wealthy Park family and the destitute Kim clan.");
            parasite.setYear(Year.of(2019));
            parasite.setCategory(dramaCategory);
            Set<Category> parasiteSubs = new HashSet<>();
            parasiteSubs.add(thrillerCategory);
            parasite.setSubCategories(parasiteSubs);
            movieRepository.save(parasite);

            Movie pulpFiction = new Movie();
            pulpFiction.setName("Pulp Fiction");
            pulpFiction.setDescription("The lives of two mob hitmen, a boxer, a gangster and his wife intertwine in four tales of violence and redemption.");
            pulpFiction.setYear(Year.of(1994));
            pulpFiction.setCategory(crimeCategory);
            Set<Category> pulpFictionSubs = new HashSet<>();
            pulpFictionSubs.add(dramaCategory);
            pulpFiction.setSubCategories(pulpFictionSubs);
            movieRepository.save(pulpFiction);

            Movie shawshank = new Movie();
            shawshank.setName("The Shawshank Redemption");
            shawshank.setDescription("Two imprisoned men bond over a number of years, finding solace and eventual redemption through acts of common decency.");
            shawshank.setYear(Year.of(1994));
            shawshank.setCategory(dramaCategory);
            Set<Category> shawshankSubs = new HashSet<>();
            shawshankSubs.add(crimeCategory);
            shawshank.setSubCategories(shawshankSubs);
            movieRepository.save(shawshank);

            Movie forrestGump = new Movie();
            forrestGump.setName("Forrest Gump");
            forrestGump.setDescription("The presidencies of Kennedy and Johnson, the Vietnam War, and other historical events unfold from the perspective of an Alabama man with an IQ of 75.");
            forrestGump.setYear(Year.of(1994));
            forrestGump.setCategory(dramaCategory);
            Set<Category> forrestSubs = new HashSet<>();
            forrestSubs.add(romanceCategory);
            forrestGump.setSubCategories(forrestSubs);
            movieRepository.save(forrestGump);

            Movie lordOfRings = new Movie();
            lordOfRings.setName("The Lord of the Rings: The Return of the King");
            lordOfRings.setDescription("Gandalf and Aragorn lead the World of Men against Sauron's army to draw his gaze from Frodo and Sam as they approach Mount Doom with the One Ring.");
            lordOfRings.setYear(Year.of(2003));
            lordOfRings.setCategory(fantasyCategory);
            Set<Category> lotrSubs = new HashSet<>();
            lotrSubs.add(adventureCategory);
            lotrSubs.add(actionCategory);
            lordOfRings.setSubCategories(lotrSubs);
            movieRepository.save(lordOfRings);

            Movie goodfellas = new Movie();
            goodfellas.setName("Goodfellas");
            goodfellas.setDescription("The story of Henry Hill and his life in the mob, covering his relationship with his wife Karen Hill and his mob partners Jimmy Conway and Tommy DeVito.");
            goodfellas.setYear(Year.of(1990));
            goodfellas.setCategory(crimeCategory);
            Set<Category> goodfellasSubs = new HashSet<>();
            goodfellasSubs.add(dramaCategory);
            goodfellas.setSubCategories(goodfellasSubs);
            movieRepository.save(goodfellas);

            System.out.println("✓ Movies seeded successfully");

            // ========== SEED ADMIN USER (KHALIL) ==========
            roleRepository.findByName(RoleName.ROLE_ADMIN).ifPresentOrElse(adminRole -> {
                // Create admin profile (will be saved via cascade)
                UserProfile adminProfile = new UserProfile();

                // Create admin user - Khalil
                User adminUser = new User();
                adminUser.setUserName("khalil");
                adminUser.setEmailAddress("khalil.ak.bh110@gmail.com");
                adminUser.setPassword(passwordEncoder.encode("123"));
                adminUser.setUserProfile(adminProfile);
                Set<Role> adminRoles = new HashSet<>();
                adminRoles.add(adminRole);
                adminUser.setRoles(adminRoles);
                adminUser.setAccountVerified(true);
                userRepository.save(adminUser);

                System.out.println("✓ Admin user created successfully");
                System.out.println("  Username: khalil");
                System.out.println("  Email: khalil.ak.bh110@gmail.com");
                System.out.println("  Password: 123");
            }, () -> System.out.println("⚠ Admin role not found. Please ensure RoleInitializer runs first."));

            // ========== SEED REGULAR USERS ==========
            roleRepository.findByName(RoleName.ROLE_USER).ifPresentOrElse(userRole -> {
                // Create user profile for Mohammed (will be saved via cascade)
                UserProfile mohammedProfile = new UserProfile();

                // Create user - Mohammed
                User mohammedUser = new User();
                mohammedUser.setUserName("Mohammed");
                mohammedUser.setEmailAddress("m0hx@protonmail.com");
                mohammedUser.setPassword(passwordEncoder.encode("123"));
                mohammedUser.setUserProfile(mohammedProfile);
                Set<Role> mohammedRoles = new HashSet<>();
                mohammedRoles.add(userRole);
                mohammedUser.setRoles(mohammedRoles);
                mohammedUser.setAccountVerified(true);
                mohammedUser = userRepository.save(mohammedUser);

                // Create user profile for Mueen (will be saved via cascade)
                UserProfile mueenProfile = new UserProfile();

                // Create user - Mueen
                User mueenUser = new User();
                mueenUser.setUserName("Mueen");
                mueenUser.setEmailAddress("hejiy2002@gmail.com");
                mueenUser.setPassword(passwordEncoder.encode("123"));
                mueenUser.setUserProfile(mueenProfile);
                Set<Role> mueenRoles = new HashSet<>();
                mueenRoles.add(userRole);
                mueenUser.setRoles(mueenRoles);
                mueenUser.setAccountVerified(true);
                mueenUser = userRepository.save(mueenUser);

                // Create sample reviews from Mohammed
                Review review1 = new Review();
                review1.setRate("5");
                review1.setDescription("Inception is an absolute masterpiece! The concept of dream infiltration is brilliantly executed with stunning visuals and a thought-provoking storyline. Christopher Nolan at his best!");
                review1.setMovie(inception);
                review1.setUser(mohammedUser);
                reviewRepository.save(review1);

                Review review2 = new Review();
                review2.setRate("5");
                review2.setDescription("The Dark Knight redefined superhero movies. Heath Ledger's performance as the Joker is legendary and the film explores deep themes of chaos versus order.");
                review2.setMovie(darkKnight);
                review2.setUser(mohammedUser);
                reviewRepository.save(review2);

                // Create sample reviews from Mueen
                Review review3 = new Review();
                review3.setRate("4");
                review3.setDescription("The Matrix was groundbreaking for its time. The action sequences and philosophical questions it raises still hold up today. A must-watch for sci-fi fans.");
                review3.setMovie(matrix);
                review3.setUser(mueenUser);
                reviewRepository.save(review3);

                Review review4 = new Review();
                review4.setRate("5");
                review4.setDescription("Interstellar is a beautiful exploration of love, time, and humanity. The visuals are breathtaking and Hans Zimmer's score is phenomenal!");
                review4.setMovie(interstellar);
                review4.setUser(mueenUser);
                reviewRepository.save(review4);

                System.out.println("✓ Regular users created successfully");
                System.out.println("  Username: Mohammed | Email: m0hx@protonmail.com | Password: 123");
                System.out.println("  Username: Mueen | Email: hejiy2002@gmail.com | Password: 123");
                System.out.println("✓ Sample reviews created successfully");
            }, () -> System.out.println("⚠ User role not found. Please ensure RoleInitializer runs first."));

            System.out.println("\n========================================");
            System.out.println("✓ Database seeding completed successfully!");
            System.out.println("========================================\n");
        };
    }
}
