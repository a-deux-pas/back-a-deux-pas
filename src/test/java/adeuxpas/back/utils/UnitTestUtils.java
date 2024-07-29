package adeuxpas.back.utils;

import adeuxpas.back.entity.Ad;
import adeuxpas.back.entity.User;
import adeuxpas.back.enums.AccountStatus;
import adeuxpas.back.enums.AdStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Utility class that provides utility methods for unit testing.
 * @author Mircea Bardan
 */

// In order for the tests to remain relevant, please :
// - do not add or remove the existing objects
// - do not modify the existing objects properties' values (feel free to add new properties, along with their values, when needed)

public class UnitTestUtils {

    /**
     * Utility method that provides sample data for unit testing.
     * It can be used as a set-up by any unit test method
     *
     * @author Mircea Bardan
     */
    public static List<Ad> createMockAds() {
        List<Ad> ads = new ArrayList<>();

        User firstUser = new User();
        firstUser.setAccountStatus(AccountStatus.ACTIVE);
        firstUser.setPostalCode("75000");
        firstUser.setCity("Paris");

        User secondUser = new User();
        secondUser.setAccountStatus(AccountStatus.SUSPENDED);
        secondUser.setPostalCode("69000");
        secondUser.setCity("Lyon");

        Ad firstAd = new Ad();
        firstAd.setTitle("First available ad with active account");
        firstAd.setPrice(BigDecimal.ONE);
        firstAd.setArticleState("Neuf avec étiquette");
        firstAd.setStatus(AdStatus.AVAILABLE);
        firstAd.setPublisher(firstUser);
        firstAd.setCreationDate(LocalDateTime.now().plusMinutes(1));
        firstAd.setCategory("Autre");
        firstAd.setSubcategory("Autre");

        Ad secondAd = new Ad();
        secondAd.setTitle("Second available ad with active account");
        secondAd.setPrice(BigDecimal.valueOf(11));
        secondAd.setArticleState("Neuf sans étiquette");
        secondAd.setStatus(AdStatus.AVAILABLE);
        secondAd.setPublisher(firstUser);
        secondAd.setCreationDate(LocalDateTime.now().plusMinutes(3));
        secondAd.setCategory("Maison");
        secondAd.setSubcategory("Jardin");

        Ad thirdAd = new Ad();
        thirdAd.setTitle("Third available ad with active account");
        thirdAd.setPrice(BigDecimal.ONE);
        thirdAd.setArticleState("Neuf avec étiquette");
        thirdAd.setStatus(AdStatus.AVAILABLE);
        thirdAd.setPublisher(secondUser);
        thirdAd.setCreationDate(LocalDateTime.now().plusMinutes(2));
        thirdAd.setCategory("Mode");
        thirdAd.setSubcategory("Hauts");
        thirdAd.setArticleGender("Homme");


        Ad fourthAd = new Ad();
        fourthAd.setTitle("Fourth available ad with active account");
        fourthAd.setPrice(BigDecimal.TWO);
        fourthAd.setArticleState("Satisfaisant");
        fourthAd.setStatus(AdStatus.AVAILABLE);
        fourthAd.setPublisher(secondUser);
        fourthAd.setCreationDate(LocalDateTime.now());
        fourthAd.setCategory("Mode");
        fourthAd.setSubcategory("Hauts");
        fourthAd.setArticleGender("Femme");

        ads.add(firstAd);
        ads.add(secondAd);
        ads.add(thirdAd);
        ads.add(fourthAd);

        ads.sort((a, b) -> {
            if (a.getCreationDate().isAfter(b.getCreationDate())) return -1;
            if (a.getCreationDate().isBefore(b.getCreationDate())) return 1;
            return 0;
        });

        return ads;
    }
}
