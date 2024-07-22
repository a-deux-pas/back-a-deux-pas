package adeuxpas.back.datainit.seeder;

import adeuxpas.back.entity.Ad;
import adeuxpas.back.entity.UsersFavoriteAds;
import adeuxpas.back.entity.UsersFavoriteAdsKey;
import adeuxpas.back.entity.User;
import adeuxpas.back.enums.AdStatus;
import adeuxpas.back.repository.AdRepository;
import adeuxpas.back.repository.UsersFavoriteAdsRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Seeder class responsible for generating sample ad entities.
 * Each ad is initialized with sample data such as title, price, creation date,
 * publisher, etc.
 */
@Component
public class AdSeeder {

        private final AdRepository adRepository;
        private final UsersFavoriteAdsRepository favoriteRepository;

        /**
         * Constructs a new AdSeeder with the provided AdRepository.
         * 
         * @param adRepository the AdRepository to be used for database seeding
         *                     operations.
         */
        AdSeeder(@Autowired AdRepository adRepository, @Autowired UsersFavoriteAdsRepository favoriteRepository) {
                this.adRepository = adRepository;
                this.favoriteRepository = favoriteRepository;
        }

        /**
         * This method creates ad entities.
         * Each ad is initialized with sample data such as title, price, creation date,
         * publisher etc.
         * 
         * @param users the list of potential ad publishers.
         * @return a list of ads.
         */
        public List<Ad> createAds(List<User> users) {
                List<Ad> firstUserAds = new ArrayList<>();
                List<Ad> secondUserAds = new ArrayList<>();
                List<Ad> thirdUserAds = new ArrayList<>();
                List<Ad> fourthUserAds = new ArrayList<>();
                List<Ad> fifthUserAds = new ArrayList<>();

                Ad firstAd = new Ad();
                firstAd.setArticleDescription(
                                "Découvrez l'excellence du vélo de piste avec ce modèle raffiné, conçu pour la vitesse et la performance sur la piste. Doté d'un cadre léger en aluminium et de roues profilées pour réduire la résistance au vent, ce vélo vous propulse vers la ligne d'arrivée avec style et efficacité. Idéal pour les passionnés de cyclisme urbain ou les athlètes cherchant à dominer sur la piste, ce vélo combine agilité et puissance dans un design élégant et épuré. Préparez-vous à repousser vos limites avec ce vélo de piste, prêt à vous faire vivre des sensations fortes à chaque coup de pédale.");
                firstAd.setCreationDate(LocalDateTime.now().plusMinutes(5));
                firstAd.setPrice(BigDecimal.valueOf(1999));
                firstAd.setTitle("Vélo de piste");
                firstAd.setPublisher(users.get(0));
                firstAd.setArticleState("Très bon");
                firstAd.setCategory("Loisirs");
                firstAd.setSubcategory("Sport");
                firstUserAds.add(firstAd);
                firstAd.setStatus(AdStatus.AVAILABLE);

                Ad secondAd = new Ad();
                secondAd.setArticleDescription(
                                "Découvrez l'histoire avec ces gants de boxe vintage, un hommage authentique à l'ère glorieuse du noble art. Fabriqués avec un cuir de qualité supérieure et une construction robuste, ces gants allient tradition et performance. Parfaits pour les collectionneurs passionnés ou les amateurs de boxe cherchant à ajouter une touche rétro à leur équipement, ces gants offrent non seulement style et charisme, mais aussi une expérience de boxe authentique. Revivez l'époque des grands champions avec ces gants de boxe vintage, prêts à marquer votre propre histoire sur le ring.");
                secondAd.setCreationDate(LocalDateTime.now().plusMinutes(4));
                secondAd.setPrice(BigDecimal.valueOf(8));
                secondAd.setTitle("Gants de boxe vintage");
                secondAd.setPublisher(users.get(1));
                secondAd.setArticleState("Satisfaisant");
                secondAd.setCategory("Loisirs");
                secondAd.setSubcategory("Sport");
                secondUserAds.add(secondAd);
                secondAd.setStatus(AdStatus.AVAILABLE);

                Ad thirdAd = new Ad();
                thirdAd.setArticleDescription("Gris clair, laine italienne, taille 48");
                thirdAd.setCreationDate(LocalDateTime.now().plusMinutes(2));
                thirdAd.setPrice(BigDecimal.valueOf(59));
                thirdAd.setTitle("costume 3 pièces");
                thirdAd.setPublisher(users.get(4));
                thirdAd.setArticleState("Neuf avec étiquette");
                thirdAd.setCategory("Mode");
                thirdAd.setSubcategory("Hauts");
                thirdAd.setArticleGender("Homme");
                fifthUserAds.add(thirdAd);
                thirdAd.setStatus(AdStatus.AVAILABLE);

                Ad fourthAd = new Ad();
                fourthAd.setArticleDescription("mécanique et faite en suisse, c'est une édition limitée");
                fourthAd.setCreationDate(LocalDateTime.now().plusMinutes(3));
                fourthAd.setPrice(BigDecimal.valueOf(999));
                fourthAd.setTitle("Frédérique Constant 1988");
                fourthAd.setPublisher(users.get(0));
                fourthAd.setArticleState("Satisfaisant");
                fourthAd.setCategory("Mode");
                fourthAd.setSubcategory("Accessoires");
                firstUserAds.add(fourthAd);
                fourthAd.setStatus(AdStatus.AVAILABLE);

                Ad fifthAd = new Ad();
                fifthAd.setArticleDescription(
                                "Plongez dans un monde de son cristallin avec le casque Bose, le compagnon ultime des mélomanes et des audiophiles exigeants. Conçu pour offrir une expérience immersive, ce casque allie confort supérieur et qualité audio exceptionnelle. Avec sa technologie de réduction de bruit avancée, il vous permet de vous isoler dans votre bulle musicale, où chaque note est reproduite avec une clarté incomparable. Léger et élégant, le casque Bose est le choix parfait pour les passionnés de musique en quête de perfection sonore.");
                fifthAd.setCreationDate(LocalDateTime.now().minusMinutes(1));
                fifthAd.setPrice(BigDecimal.valueOf(199));
                fifthAd.setTitle("Casque Bose");
                fifthAd.setPublisher(users.get(2));
                fifthAd.setArticleState("Bon");
                fifthAd.setCategory("Loisirs");
                fifthAd.setSubcategory("Musique");
                thirdUserAds.add(fifthAd);
                fifthAd.setStatus(AdStatus.AVAILABLE);

                Ad sixthAd = new Ad();
                sixthAd.setArticleDescription("Étui pour téléphone Wonderlust");
                sixthAd.setCreationDate(LocalDateTime.now().plusMinutes(3));
                sixthAd.setPrice(BigDecimal.valueOf(20));
                sixthAd.setTitle("Étui pour téléphone");
                sixthAd.setPublisher(users.get(3));
                sixthAd.setArticleState("Satisfaisant");
                sixthAd.setCategory("Électronique");
                sixthAd.setSubcategory("Autre");
                fourthUserAds.add(sixthAd);
                sixthAd.setStatus(AdStatus.AVAILABLE);

                Ad seventhAd = new Ad();
                seventhAd.setArticleDescription(
                                "Optez pour l'élégance intemporelle avec cette chemise en soie bleue, symbole de raffinement et de confort. Fabriquée à partir de soie naturelle de haute qualité, elle offre une sensation luxueuse contre la peau et un ajustement parfait. Idéale pour les occasions formelles ou décontractées, cette chemise apporte une touche de sophistication avec sa couleur vibrante et sa finition impeccable. Un indispensable dans toute garde-robe moderne, pour ceux qui recherchent l'alliance parfaite entre style et confort.");
                seventhAd.setCreationDate(LocalDateTime.now().plusMinutes(1));
                seventhAd.setPrice(BigDecimal.valueOf(15));
                seventhAd.setTitle("Chemise en soie bleue");
                seventhAd.setPublisher(users.get(3));
                seventhAd.setArticleState("Neuf avec étiquette");
                seventhAd.setCategory("Mode");
                seventhAd.setSubcategory("Hauts");
                seventhAd.setArticleGender("Femme");
                fourthUserAds.add(seventhAd);
                seventhAd.setStatus(AdStatus.AVAILABLE);

                Ad eighthAd = new Ad();
                eighthAd.setArticleDescription(
                                "La collection la plus vendue de Nassim N. Taleb ; comprend : Anti-Fragile, The Black Swan, Skin in the Game, etc. ");
                eighthAd.setCreationDate(LocalDateTime.now().plusMinutes(6));
                eighthAd.setPrice(BigDecimal.valueOf(150));
                eighthAd.setTitle("Collection de livres Incerto");
                eighthAd.setPublisher(users.get(2));
                eighthAd.setArticleState("Neuf sans étiquette");
                eighthAd.setCategory("Loisirs");
                eighthAd.setSubcategory("Livres");
                thirdUserAds.add(eighthAd);
                eighthAd.setStatus(AdStatus.AVAILABLE);

                Ad ninethAd = new Ad();
                ninethAd.setArticleDescription(
                                "Découvrez ce bracelet unique fait main, un véritable bijou artisanal qui capture l'essence de l'élégance et de la créativité. Conçu avec des matériaux de qualité supérieure et une attention méticuleuse aux détails, chaque bracelet est une œuvre d'art en soi. Disponible en plusieurs designs, il ajoute une touche de sophistication à n'importe quelle tenue, faisant de lui un choix parfait pour une soirée spéciale ou un cadeau précieux.");
                ninethAd.setCreationDate(LocalDateTime.now().minusMinutes(10));
                ninethAd.setPrice(BigDecimal.valueOf(10.2));
                ninethAd.setTitle("Bracelet fait main");
                ninethAd.setPublisher(users.get(5));
                ninethAd.setArticleState("Neuf sans étiquette");
                ninethAd.setCategory("Autre");
                ninethAd.setSubcategory("Autre");
                List<Ad> sixthUserAds = new ArrayList<>();
                sixthUserAds.add(ninethAd);
                ninethAd.setStatus(AdStatus.RESERVED);

                Ad tenthAd = new Ad();
                tenthAd.setArticleDescription(
                                "Élégant et fonctionnel, ce sac à main noir est l'accessoire parfait pour toutes les occasions. Fabriqué en cuir de haute qualité, il offre un espace spacieux pour vos essentiels quotidiens tout en ajoutant une touche de sophistication à votre tenue. Parfait état, prêt à compléter votre style avec classe.");
                tenthAd.setCreationDate(LocalDateTime.now().plusMinutes(5));
                tenthAd.setPrice(BigDecimal.valueOf(45));
                tenthAd.setTitle("Sac à main");
                tenthAd.setPublisher(users.get(0));
                tenthAd.setArticleState("Très bon");
                tenthAd.setCategory("Mode");
                tenthAd.setSubcategory("Accessoires");
                firstUserAds.add(tenthAd);
                tenthAd.setStatus(AdStatus.AVAILABLE);

                Ad eleventhAd = new Ad();
                eleventhAd.setArticleDescription(
                                "Machine à laver basse consommation");
                eleventhAd.setCreationDate(LocalDateTime.now().plusMinutes(4));
                eleventhAd.setPrice(BigDecimal.valueOf(250));
                eleventhAd.setTitle("Machine à laver");
                eleventhAd.setPublisher(users.get(1));
                eleventhAd.setArticleState("Neuf sans étiquette");
                eleventhAd.setCategory("Maison");
                eleventhAd.setSubcategory("meubles");
                secondUserAds.add(eleventhAd);
                eleventhAd.setStatus(AdStatus.AVAILABLE);

                Ad twelfthAd = new Ad();
                twelfthAd.setArticleDescription(
                                "Cette valise de voyage est votre compagnon idéal pour toutes vos aventures. Conçue en matériau léger et résistant, elle offre une grande capacité de rangement avec des compartiments pratiques pour organiser vos affaires. Dotée de roulettes pivotantes et d'une poignée télescopique, elle est facile à manœuvrer. En excellent état, cette valise allie fonctionnalité et style pour vos déplacements.");
                twelfthAd.setCreationDate(LocalDateTime.now().plusMinutes(1));
                twelfthAd.setPrice(BigDecimal.valueOf(9));
                twelfthAd.setTitle("Valise de voyage");
                twelfthAd.setPublisher(users.get(4));
                twelfthAd.setArticleState("Neuf sans étiquette");
                twelfthAd.setCategory("Autre");
                twelfthAd.setSubcategory("Autre");
                fifthUserAds.add(twelfthAd);
                twelfthAd.setStatus(AdStatus.AVAILABLE);

                Ad thirteenthAd = new Ad();
                thirteenthAd.setArticleDescription(
                                "Ce sac à dos noir est l'accessoire parfait pour vos déplacements quotidiens. Fabriqué en matériaux durables et résistants à l'eau, il offre plusieurs compartiments spacieux pour organiser vos affaires. Son design élégant et sobre s'adapte à toutes les tenues, que ce soit pour le travail, l'école ou les loisirs. Confortable à porter grâce à ses bretelles rembourrées, il est en excellent état et prêt à vous accompagner partout.");
                thirteenthAd.setCreationDate(LocalDateTime.now().plusMinutes(3));
                thirteenthAd.setPrice(BigDecimal.valueOf(20));
                thirteenthAd.setTitle("Sac à dos noir");
                thirteenthAd.setPublisher(users.get(0));
                thirteenthAd.setArticleState("Satisfaisant");
                thirteenthAd.setCategory("Mode");
                thirteenthAd.setSubcategory("Accessoires");
                firstUserAds.add(thirteenthAd);
                thirteenthAd.setStatus(AdStatus.AVAILABLE);

                Ad fourteenthAd = new Ad();
                fourteenthAd.setArticleDescription(
                                "Plongez dans l'univers de l'Ouest avec ce chapeau de cowboy authentique en cuir véritable. Conçu pour allier style et durabilité, il présente une bordure large pour vous protéger du soleil et un intérieur confortable pour un port prolongé. Parfait pour les festivals, les déguisements ou simplement pour ajouter une touche de caractère à votre tenue quotidienne. En excellent état, ce chapeau est prêt à devenir votre accessoire favori.");
                fourteenthAd.setCreationDate(LocalDateTime.now().minusMinutes(1));
                fourteenthAd.setPrice(BigDecimal.valueOf(49));
                fourteenthAd.setTitle("Chapeau de Cowboy");
                fourteenthAd.setPublisher(users.get(2));
                fourteenthAd.setArticleState("Satisfaisant");
                fourteenthAd.setCategory("Mode");
                fourteenthAd.setSubcategory("Accessoires");
                thirdUserAds.add(fourteenthAd);
                fourteenthAd.setStatus(AdStatus.AVAILABLE);

                Ad fifteenthAd = new Ad();
                fifteenthAd.setArticleDescription("casquette lisse");
                fifteenthAd.setCreationDate(LocalDateTime.now().plusMinutes(3));
                fifteenthAd.setPrice(BigDecimal.valueOf(20));
                fifteenthAd.setTitle("Casquette");
                fifteenthAd.setPublisher(users.get(3));
                fifteenthAd.setArticleState("Bon");
                fifteenthAd.setCategory("Mode");
                fifteenthAd.setSubcategory("Accessoires");
                fourthUserAds.add(fifteenthAd);
                fifteenthAd.setStatus(AdStatus.AVAILABLE);

                Ad sixteenthAd = new Ad();
                sixteenthAd.setArticleDescription("bracelet hippie");
                sixteenthAd.setCreationDate(LocalDateTime.now().plusMinutes(1));
                sixteenthAd.setPrice(BigDecimal.valueOf(5));
                sixteenthAd.setTitle("Bracelet Tree of Life ");
                sixteenthAd.setPublisher(users.get(5));
                sixteenthAd.setArticleState("Bon");
                sixteenthAd.setCategory("Mode");
                sixteenthAd.setSubcategory("Accessoires");
                sixthUserAds.add(sixteenthAd);
                sixteenthAd.setStatus(AdStatus.AVAILABLE);

                Ad seventeenthAd = new Ad();
                seventeenthAd.setArticleDescription("Je le vends parce qu'il est trop petit pour moi");
                seventeenthAd.setCreationDate(LocalDateTime.now().plusMinutes(5));
                seventeenthAd.setPrice(BigDecimal.valueOf(39));
                seventeenthAd.setTitle("Collier eagle");
                seventeenthAd.setPublisher(users.get(2));
                seventeenthAd.setArticleState("Neuf avec étiquette");
                seventeenthAd.setCategory("Mode");
                seventeenthAd.setSubcategory("Accessoires");
                thirdUserAds.add(seventeenthAd);
                seventeenthAd.setStatus(AdStatus.AVAILABLE);

                Ad eighteenthAd = new Ad();
                eighteenthAd.setArticleDescription("Dessous de verre que jevends pour cause de déménagement");
                eighteenthAd.setCreationDate(LocalDateTime.now().plusMinutes(5));
                eighteenthAd.setPrice(BigDecimal.valueOf(4.99));
                eighteenthAd.setTitle("Dessous de verre en bois");
                eighteenthAd.setPublisher(users.get(2));
                eighteenthAd.setArticleState("Neuf sans étiquette");
                eighteenthAd.setCategory("Maison");
                eighteenthAd.setSubcategory("Autre");
                thirdUserAds.add(eighteenthAd);
                eighteenthAd.setStatus(AdStatus.AVAILABLE);

                Ad nineteethAd = new Ad();
                nineteethAd.setArticleDescription(
                                "Découvrez ce magnifique bonsaï, une œuvre d'art vivante qui apportera une touche zen à votre intérieur. Soigneusement sculpté et entretenu, ce bonsaï présente un feuillage dense et une structure élégante, idéale pour les amateurs de plantes et les décorateurs d'intérieur. Facile à entretenir, il est parfait pour apporter une ambiance apaisante et naturelle à votre espace de vie ou de travail.");
                nineteethAd.setCreationDate(LocalDateTime.now().plusMinutes(5));
                nineteethAd.setPrice(BigDecimal.valueOf(30.67));
                nineteethAd.setTitle("Bonsaï");
                nineteethAd.setPublisher(users.get(0));
                nineteethAd.setArticleState("Très bon");
                nineteethAd.setCategory("Maison");
                nineteethAd.setSubcategory("Jardin");
                firstUserAds.add(nineteethAd);
                users.get(0).setAds(firstUserAds);
                nineteethAd.setStatus(AdStatus.AVAILABLE);

                Ad twentiethAd = new Ad();
                twentiethAd.setArticleDescription(
                                "Smartphone dernier cri en excellent état. Utilisé pendant seulement six mois, il offre toutes les fonctionnalités modernes dont vous avez besoin : caméra haute résolution, écran HD, grande capacité de stockage et une autonomie de batterie impressionnante.");
                twentiethAd.setCreationDate(LocalDateTime.now().plusMinutes(10));
                twentiethAd.setPrice(BigDecimal.valueOf(450.00));
                twentiethAd.setTitle("Smartphone dernière génération");
                twentiethAd.setPublisher(users.get(0));
                twentiethAd.setArticleState("Très bon");
                twentiethAd.setCategory("Électronique");
                twentiethAd.setSubcategory("téléphones");
                firstUserAds.add(twentiethAd);
                users.get(0).setAds(firstUserAds);
                twentiethAd.setStatus(AdStatus.AVAILABLE);

                Ad twentyFirstAd = new Ad();
                twentyFirstAd.setArticleDescription(
                                "Guitare acoustique de haute qualité, idéale pour les débutants et les musiciens expérimentés. Son riche et clair, facile à jouer, parfait pour des sessions acoustiques à la maison ou sur scène.");
                twentyFirstAd.setCreationDate(LocalDateTime.now().plusMinutes(15));
                twentyFirstAd.setPrice(BigDecimal.valueOf(150.00));
                twentyFirstAd.setTitle("Guitare acoustique en bois de qualité");
                twentyFirstAd.setPublisher(users.get(0));
                twentyFirstAd.setArticleState("Neuf avec étiquette");
                twentyFirstAd.setCategory("Loisirs");
                twentyFirstAd.setSubcategory("Musique");
                firstUserAds.add(twentyFirstAd);
                users.get(0).setAds(firstUserAds);
                twentyFirstAd.setStatus(AdStatus.AVAILABLE);

                Ad twentySecondAd = new Ad();
                twentySecondAd.setArticleDescription(
                                "Table basse en bois massif, parfaite pour votre salon. Son design élégant et épuré s'intègre facilement dans tout type de décoration intérieure. Elle est robuste et en excellent état.");
                twentySecondAd.setCreationDate(LocalDateTime.now().plusMinutes(20));
                twentySecondAd.setPrice(BigDecimal.valueOf(80.00));
                twentySecondAd.setTitle("Table basse en bois massif");
                twentySecondAd.setPublisher(users.get(0));
                twentySecondAd.setArticleState("Satisfaisant");
                twentySecondAd.setCategory("Maison");
                twentySecondAd.setSubcategory("meubles");
                firstUserAds.add(twentySecondAd);
                users.get(0).setAds(firstUserAds);
                twentySecondAd.setStatus(AdStatus.AVAILABLE);

                Ad twentyThirdAd = new Ad();
                twentyThirdAd.setArticleDescription(
                                "Montre élégante pour homme, avec bracelet en cuir et cadran en acier inoxydable. Parfaite pour toutes les occasions, elle allie style et fonctionnalité avec son chronographe intégré.");
                twentyThirdAd.setCreationDate(LocalDateTime.now().plusMinutes(25));
                twentyThirdAd.setPrice(BigDecimal.valueOf(120.00));
                twentyThirdAd.setTitle("Montre élégante pour homme");
                twentyThirdAd.setPublisher(users.get(0));
                twentyThirdAd.setArticleState("Satisfaisant");
                twentyThirdAd.setCategory("Mode");
                twentyThirdAd.setSubcategory("accessoires");
                firstUserAds.add(twentyThirdAd);
                users.get(0).setAds(firstUserAds);
                twentyThirdAd.setStatus(AdStatus.AVAILABLE);

                Ad twentyFourthAd = new Ad();
                twentyFourthAd.setArticleDescription(
                                "Ordinateur portable performant avec écran HD, processeur rapide et grande capacité de stockage. Idéal pour le travail, les études ou les loisirs, en parfait état de fonctionnement.");
                twentyFourthAd.setCreationDate(LocalDateTime.now().plusMinutes(30));
                twentyFourthAd.setPrice(BigDecimal.valueOf(700.00));
                twentyFourthAd.setTitle("Ordinateur portable performant");
                twentyFourthAd.setPublisher(users.get(0));
                twentyFourthAd.setArticleState("Satisfaisant");
                twentyFourthAd.setCategory("Électronique");
                twentyFourthAd.setSubcategory("ordinateurs");
                firstUserAds.add(twentyFourthAd);
                users.get(0).setAds(firstUserAds);
                twentyFourthAd.setStatus(AdStatus.AVAILABLE);

                Ad twentyFifthAd = new Ad();
                twentyFifthAd.setArticleDescription(
                                "Roman captivant, best-seller international, en parfait état. Une lecture incontournable pour tous les amateurs de littérature, offrant une histoire passionnante et bien écrite.");
                twentyFifthAd.setCreationDate(LocalDateTime.now().plusMinutes(35));
                twentyFifthAd.setPrice(BigDecimal.valueOf(15.00));
                twentyFifthAd.setTitle("Roman captivant - Best-seller international");
                twentyFifthAd.setPublisher(users.get(0));
                twentyFifthAd.setArticleState("Très bon");
                twentyFifthAd.setCategory("Loisirs");
                twentyFifthAd.setSubcategory("Livres");
                firstUserAds.add(twentyFifthAd);
                users.get(0).setAds(firstUserAds);
                twentyFifthAd.setStatus(AdStatus.RESERVED);

                Ad twentySixthAd = new Ad();
                twentySixthAd.setArticleDescription(
                                "Chaussures de sport de marque, en excellent état, idéales pour la course à pied et les activités sportives. Confortables et durables, elles offrent un excellent maintien et une bonne adhérence.");
                twentySixthAd.setCreationDate(LocalDateTime.now().plusMinutes(40));
                twentySixthAd.setPrice(BigDecimal.valueOf(60.00));
                twentySixthAd.setTitle("Chaussures de sport de marque");
                twentySixthAd.setPublisher(users.get(0));
                twentySixthAd.setArticleState("Bon");
                twentySixthAd.setCategory("Mode");
                twentySixthAd.setSubcategory("chaussures");
                firstUserAds.add(twentySixthAd);
                users.get(0).setAds(firstUserAds);
                twentySixthAd.setStatus(AdStatus.RESERVED);

                Ad twentySeventhAd = new Ad();
                twentySeventhAd.setArticleDescription(
                                "Ensemble de DVD de films classiques et récents, en parfait état. Idéal pour les soirées cinéma à la maison, cet ensemble offre une variété de genres pour tous les goûts.");
                twentySeventhAd.setCreationDate(LocalDateTime.now().plusMinutes(45));
                twentySeventhAd.setPrice(BigDecimal.valueOf(40.00));
                twentySeventhAd.setTitle("Ensemble de DVD - Films classiques et récents");
                twentySeventhAd.setPublisher(users.get(0));
                twentySeventhAd.setArticleState("Neuf avec étiquette");
                twentySeventhAd.setCategory("Loisirs");
                twentySeventhAd.setSubcategory("Films");
                firstUserAds.add(twentySeventhAd);
                users.get(0).setAds(firstUserAds);
                twentySeventhAd.setStatus(AdStatus.SOLD);

                Ad twentyEighthAd = new Ad();
                twentyEighthAd.setArticleDescription(
                                "Ensemble de décorations murales en métal et bois, parfait pour ajouter une touche artistique et moderne à votre intérieur. En excellent état, elles s'intégreront parfaitement dans tout type de décoration.");
                twentyEighthAd.setCreationDate(LocalDateTime.now().plusMinutes(50));
                twentyEighthAd.setPrice(BigDecimal.valueOf(55.00));
                twentyEighthAd.setTitle("Décorations murales en métal et bois");
                twentyEighthAd.setPublisher(users.get(0));
                twentyEighthAd.setArticleState("Satisfaisant");
                twentyEighthAd.setCategory("Maison");
                twentyEighthAd.setSubcategory("décorations");
                firstUserAds.add(twentyEighthAd);
                users.get(0).setAds(firstUserAds);
                twentyEighthAd.setStatus(AdStatus.SOLD);

                Ad twentyNinthAd = new Ad();
                twentyNinthAd.setArticleDescription(
                                "Vélo de montagne robuste, parfait pour les amateurs de randonnée et les trajets en terrain accidenté. En excellent état, avec des pneus neufs et un cadre léger en aluminium.");
                twentyNinthAd.setCreationDate(LocalDateTime.now().plusMinutes(5));
                twentyNinthAd.setPrice(BigDecimal.valueOf(300.00));
                twentyNinthAd.setTitle("Vélo de montagne en excellent état");
                twentyNinthAd.setPublisher(users.get(1));
                twentyNinthAd.setArticleState("Très bon");
                twentyNinthAd.setCategory("Loisirs");
                twentyNinthAd.setSubcategory("Sport");
                secondUserAds.add(twentyNinthAd);
                users.get(1).setAds(secondUserAds);
                twentyNinthAd.setStatus(AdStatus.AVAILABLE);

                Ad thirtiethAd = new Ad();
                thirtiethAd.setArticleDescription(
                                "Jeans de marque pour femme, coupe slim, en excellent état. Porté seulement quelques fois, il est comme neuf. Parfait pour un look décontracté et tendance.");
                thirtiethAd.setCreationDate(LocalDateTime.now().plusMinutes(10));
                thirtiethAd.setPrice(BigDecimal.valueOf(40.00));
                thirtiethAd.setTitle("Jeans pour femme - Coupe slim");
                thirtiethAd.setPublisher(users.get(1));
                thirtiethAd.setArticleState("Très bon");
                thirtiethAd.setCategory("Mode");
                thirtiethAd.setSubcategory("bas");
                thirtiethAd.setArticleGender("femme");
                secondUserAds.add(thirtiethAd);
                users.get(1).setAds(secondUserAds);
                thirtiethAd.setStatus(AdStatus.AVAILABLE);

                Ad thirtyFirstAd = new Ad();
                thirtyFirstAd.setArticleDescription(
                                "Télévision 4K Ultra HD, 55 pouces, en parfait état. Image claire et nette, son surround, connectivité intelligente. Idéale pour les soirées cinéma et les jeux vidéo.");
                thirtyFirstAd.setCreationDate(LocalDateTime.now().plusMinutes(15));
                thirtyFirstAd.setPrice(BigDecimal.valueOf(500.00));
                thirtyFirstAd.setTitle("Télévision 4K Ultra HD 55 pouces");
                thirtyFirstAd.setPublisher(users.get(1));
                thirtyFirstAd.setArticleState("Neuf avec étiquette");
                thirtyFirstAd.setCategory("Électronique");
                thirtyFirstAd.setSubcategory("autre");
                secondUserAds.add(thirtyFirstAd);
                users.get(1).setAds(secondUserAds);
                thirtyFirstAd.setStatus(AdStatus.AVAILABLE);

                Ad thirtySecondAd = new Ad();
                thirtySecondAd.setArticleDescription(
                                "Chaise ergonomique pour bureau, réglable et confortable. En très bon état, elle offre un excellent soutien lombaire pour les longues heures de travail.");
                thirtySecondAd.setCreationDate(LocalDateTime.now().plusMinutes(20));
                thirtySecondAd.setPrice(BigDecimal.valueOf(75.00));
                thirtySecondAd.setTitle("Chaise ergonomique pour bureau");
                thirtySecondAd.setPublisher(users.get(1));
                thirtySecondAd.setArticleState("Neuf sans étiquette");
                thirtySecondAd.setCategory("Maison");
                thirtySecondAd.setSubcategory("meubles");
                users.get(1).setAds(secondUserAds);
                secondUserAds.add(thirtySecondAd);
                thirtySecondAd.setStatus(AdStatus.AVAILABLE);

                Ad thirtyThirdAd = new Ad();
                thirtyThirdAd.setArticleDescription(
                                "Livre de recettes de cuisine, comprenant des plats internationaux et des desserts gourmands. En excellent état, idéal pour les amateurs de cuisine.");
                thirtyThirdAd.setCreationDate(LocalDateTime.now().plusMinutes(25));
                thirtyThirdAd.setPrice(BigDecimal.valueOf(20.00));
                thirtyThirdAd.setTitle("Livre de recettes de cuisine");
                thirtyThirdAd.setPublisher(users.get(1));
                thirtyThirdAd.setArticleState("Neuf sans étiquette");
                thirtyThirdAd.setCategory("Loisirs");
                thirtyThirdAd.setSubcategory("Livres");
                users.get(1).setAds(secondUserAds);
                secondUserAds.add(thirtyThirdAd);
                thirtyThirdAd.setStatus(AdStatus.AVAILABLE);

                Ad thirtyFourthAd = new Ad();
                thirtyFourthAd.setArticleDescription(
                                "Casque de réalité virtuelle de dernière génération, en parfait état de fonctionnement. Offrez-vous une expérience immersive unique avec ce casque VR de qualité.");
                thirtyFourthAd.setCreationDate(LocalDateTime.now().plusMinutes(30));
                thirtyFourthAd.setPrice(BigDecimal.valueOf(250.00));
                thirtyFourthAd.setTitle("Casque de réalité virtuelle");
                thirtyFourthAd.setPublisher(users.get(1));
                thirtyFourthAd.setArticleState("Neuf sans étiquette");
                thirtyFourthAd.setCategory("Électronique");
                thirtyFourthAd.setSubcategory("autre");
                users.get(1).setAds(secondUserAds);
                secondUserAds.add(thirtyFourthAd);
                thirtyFourthAd.setStatus(AdStatus.AVAILABLE);

                Ad thirtyFifthAd = new Ad();
                thirtyFifthAd.setArticleDescription(
                                "Bottes en cuir pour homme, de marque renommée, en très bon état. Confortables et élégantes, idéales pour un look chic et décontracté.");
                thirtyFifthAd.setCreationDate(LocalDateTime.now().plusMinutes(35));
                thirtyFifthAd.setPrice(BigDecimal.valueOf(100.00));
                thirtyFifthAd.setTitle("Bottes en cuir pour homme");
                thirtyFifthAd.setPublisher(users.get(1));
                thirtyFifthAd.setArticleState("Satisfaisant");
                thirtyFifthAd.setCategory("Mode");
                thirtyFifthAd.setSubcategory("chaussures");
                thirtyFifthAd.setArticleGender("homme");
                users.get(1).setAds(secondUserAds);
                secondUserAds.add(thirtyFifthAd);
                thirtyFifthAd.setStatus(AdStatus.AVAILABLE);

                Ad thirtySixthAd = new Ad();
                thirtySixthAd.setArticleDescription(
                                "Enceinte Bluetooth portable, en parfait état, offrant une qualité sonore exceptionnelle. Idéale pour écouter de la musique partout où vous allez.");
                thirtySixthAd.setCreationDate(LocalDateTime.now().plusMinutes(40));
                thirtySixthAd.setPrice(BigDecimal.valueOf(50.00));
                thirtySixthAd.setTitle("Enceinte Bluetooth portable");
                thirtySixthAd.setPublisher(users.get(1));
                thirtySixthAd.setArticleState("Satisfaisant");
                thirtySixthAd.setCategory("Électronique");
                thirtySixthAd.setSubcategory("autre");
                users.get(1).setAds(secondUserAds);
                secondUserAds.add(thirtySixthAd);
                thirtySixthAd.setStatus(AdStatus.AVAILABLE);

                Ad thirtySeventhAd = new Ad();
                thirtySeventhAd.setArticleDescription(
                                "Lampe de chevet moderne avec abat-jour en tissu, en très bon état. Parfaite pour ajouter une touche d'élégance à votre chambre ou votre salon.");
                thirtySeventhAd.setCreationDate(LocalDateTime.now().plusMinutes(45));
                thirtySeventhAd.setPrice(BigDecimal.valueOf(30.00));
                thirtySeventhAd.setTitle("Lampe de chevet moderne");
                thirtySeventhAd.setPublisher(users.get(1));
                thirtySeventhAd.setArticleState("Bon");
                thirtySeventhAd.setCategory("Maison");
                thirtySeventhAd.setSubcategory("décorations");
                users.get(1).setAds(secondUserAds);
                secondUserAds.add(thirtySeventhAd);
                thirtySeventhAd.setStatus(AdStatus.AVAILABLE);

                Ad thirtyEighthAd = new Ad();
                thirtyEighthAd.setArticleDescription(
                                "Chemise en lin pour homme, légère et respirante. Parfaite pour les journées chaudes, elle offre un style décontracté et élégant. En excellent état.");
                thirtyEighthAd.setCreationDate(LocalDateTime.now().plusMinutes(50));
                thirtyEighthAd.setPrice(BigDecimal.valueOf(35.00));
                thirtyEighthAd.setTitle("Chemise en lin pour homme");
                thirtyEighthAd.setPublisher(users.get(1));
                thirtyEighthAd.setArticleState("Très bon");
                thirtyEighthAd.setCategory("Mode");
                thirtyEighthAd.setSubcategory("hauts");
                thirtyEighthAd.setArticleGender("homme");
                users.get(1).setAds(secondUserAds);
                secondUserAds.add(thirtyEighthAd);
                thirtyEighthAd.setStatus(AdStatus.RESERVED);

                Ad thirtyNinthAd = new Ad();
                thirtyNinthAd.setArticleDescription(
                                "Console de jeux vidéo avec manettes et jeux inclus. En bon état, idéale pour les amateurs de jeux vidéo cherchant une expérience immersive et divertissante.");
                thirtyNinthAd.setCreationDate(LocalDateTime.now().plusMinutes(39));
                thirtyNinthAd.setPrice(BigDecimal.valueOf(150));
                thirtyNinthAd.setTitle("Console de jeux vidéo avec manettes et jeux");
                thirtyNinthAd.setPublisher(users.get(1));
                thirtyNinthAd.setArticleState("Très bon");
                thirtyNinthAd.setCategory("Électronique");
                thirtyNinthAd.setSubcategory("jeux vidéo");
                thirtyNinthAd.setStatus(AdStatus.RESERVED);
                secondUserAds.add(thirtyNinthAd);

                Ad fortiethAd = new Ad();
                fortiethAd.setArticleDescription(
                                "Table parfaite pour ajouter une touche rustique à votre exterieur. Robuste et élégante, elle s'intégrera parfaitement à toute ambiance.");
                fortiethAd.setCreationDate(LocalDateTime.now().plusMinutes(40));
                fortiethAd.setPrice(BigDecimal.valueOf(75));
                fortiethAd.setTitle("Table verte");
                fortiethAd.setPublisher(users.get(2));
                fortiethAd.setArticleState("Neuf sans étiquette");
                fortiethAd.setCategory("Maison");
                fortiethAd.setSubcategory("meubles");
                fortiethAd.setStatus(AdStatus.SOLD);
                thirdUserAds.add(fortiethAd);

                Ad fortyFirstAd = new Ad();
                fortyFirstAd.setArticleDescription(
                                "Sac à main de marque en cuir, en très bon état, avec de nombreux compartiments pour un rangement pratique. Idéal pour ajouter une touche d'élégance à votre tenue.");
                fortyFirstAd.setCreationDate(LocalDateTime.now().plusMinutes(41));
                fortyFirstAd.setPrice(BigDecimal.valueOf(60));
                fortyFirstAd.setTitle("Sac à main en cuir de marque");
                fortyFirstAd.setPublisher(users.get(2));
                fortyFirstAd.setArticleState("Très bon");
                fortyFirstAd.setCategory("Mode");
                fortyFirstAd.setSubcategory("accessoires");
                fortyFirstAd.setStatus(AdStatus.AVAILABLE);
                thirdUserAds.add(fortyFirstAd);

                Ad fortySecondAd = new Ad();
                fortySecondAd.setArticleDescription(
                                "Appareil photo reflex avec objectifs en parfait état, idéal pour les photographes amateurs ou professionnels. Capturez des moments inoubliables avec une qualité d'image exceptionnelle.");
                fortySecondAd.setCreationDate(LocalDateTime.now().plusMinutes(42));
                fortySecondAd.setPrice(BigDecimal.valueOf(350));
                fortySecondAd.setTitle("Appareil photo reflex avec objectifs");
                fortySecondAd.setPublisher(users.get(2));
                fortySecondAd.setArticleState("Bon");
                fortySecondAd.setCategory("Électronique");
                fortySecondAd.setSubcategory("autre");
                fortySecondAd.setStatus(AdStatus.AVAILABLE);
                thirdUserAds.add(fortySecondAd);

                Ad fortyThirdAd = new Ad();
                fortyThirdAd.setArticleDescription(
                                "Tondeuse à gazon électrique en bon état de fonctionnement, parfaite pour entretenir votre jardin. Simple d'utilisation et efficace, elle vous permettra de garder votre pelouse impeccable.");
                fortyThirdAd.setCreationDate(LocalDateTime.now().plusMinutes(43));
                fortyThirdAd.setPrice(BigDecimal.valueOf(100));
                fortyThirdAd.setTitle("Tondeuse à gazon électrique");
                fortyThirdAd.setPublisher(users.get(2));
                fortyThirdAd.setArticleState("Bon");
                fortyThirdAd.setCategory("Maison");
                fortyThirdAd.setSubcategory("jardin");
                fortyThirdAd.setStatus(AdStatus.AVAILABLE);
                thirdUserAds.add(fortyThirdAd);

                Ad fortyFourthAd = new Ad();
                fortyFourthAd.setArticleDescription(
                                "Canapé confortable en très bon état, pratique pour les petits espaces ou les invités. Transformez votre salon en chambre d'appoint avec ce canapé polyvalent et stylé.");
                fortyFourthAd.setCreationDate(LocalDateTime.now().plusMinutes(44));
                fortyFourthAd.setPrice(BigDecimal.valueOf(200));
                fortyFourthAd.setTitle("Canapé bohème");
                fortyFourthAd.setPublisher(users.get(2));
                fortyFourthAd.setArticleState("Très bon");
                fortyFourthAd.setCategory("Maison");
                fortyFourthAd.setSubcategory("meubles");
                fortyFourthAd.setStatus(AdStatus.AVAILABLE);
                thirdUserAds.add(fortyFourthAd);

                Ad fortyFifthAd = new Ad();
                fortyFifthAd.setArticleDescription(
                                "Montre connectée en excellent état, avec plusieurs fonctionnalités pour suivre votre activité physique et vos notifications. Alliez technologie et style avec cette montre connectée.");
                fortyFifthAd.setCreationDate(LocalDateTime.now().plusMinutes(45));
                fortyFifthAd.setPrice(BigDecimal.valueOf(120));
                fortyFifthAd.setTitle("Montre connectée multifonctions");
                fortyFifthAd.setPublisher(users.get(2));
                fortyFifthAd.setArticleState("Neuf avec étiquette");
                fortyFifthAd.setCategory("Électronique");
                fortyFifthAd.setSubcategory("autre");
                fortyFifthAd.setStatus(AdStatus.SOLD);
                thirdUserAds.add(fortyFifthAd);

                Ad fortySixthAd = new Ad();
                fortySixthAd.setArticleDescription(
                                "Machine à café expresso en parfait état de fonctionnement, idéale pour les amateurs de café. Préparez vos boissons préférées avec cette machine à café performante et élégante.");
                fortySixthAd.setCreationDate(LocalDateTime.now().plusMinutes(46));
                fortySixthAd.setPrice(BigDecimal.valueOf(80));
                fortySixthAd.setTitle("Machine à café expresso");
                fortySixthAd.setPublisher(users.get(2));
                fortySixthAd.setArticleState("Neuf avec étiquette");
                fortySixthAd.setCategory("Électronique");
                fortySixthAd.setSubcategory("autre");
                fortySixthAd.setStatus(AdStatus.RESERVED);
                thirdUserAds.add(fortySixthAd);

                Ad fortySeventhAd = new Ad();
                fortySeventhAd.setArticleDescription(
                                "Tenue de tennis pour femme, en excellent état, incluant une jupe, des brassières et des t-shirts de sport. Parfait pour vos séances de sport et vos entraînements quotidiens.");
                fortySeventhAd.setCreationDate(LocalDateTime.now().plusMinutes(47));
                fortySeventhAd.setPrice(BigDecimal.valueOf(50));
                fortySeventhAd.setTitle("Vêtements de sport pour femme");
                fortySeventhAd.setPublisher(users.get(2));
                fortySeventhAd.setArticleState("Neuf sans étiquette");
                fortySeventhAd.setCategory("Mode");
                fortySeventhAd.setSubcategory("autre");
                fortySeventhAd.setStatus(AdStatus.RESERVED);
                thirdUserAds.add(fortySeventhAd);

                Ad fortyEighthAd = new Ad();
                fortyEighthAd.setArticleDescription(
                                "Que vous aimiez le yoga nidra, le yin yoga ou que vous voulez juste vous faire une bonne séance d'étirement, ce tapis est fait pour vous.");
                fortyEighthAd.setCreationDate(LocalDateTime.now().plusMinutes(48));
                fortyEighthAd.setPrice(BigDecimal.valueOf(30));
                fortyEighthAd.setTitle("Tapis de yoga");
                fortyEighthAd.setPublisher(users.get(3));
                fortyEighthAd.setArticleState("Très bon");
                fortyEighthAd.setCategory("Loisirs");
                fortyEighthAd.setSubcategory("Sport");
                fortyEighthAd.setStatus(AdStatus.AVAILABLE);
                fourthUserAds.add(fortyEighthAd);

                Ad fortyNinthAd = new Ad();
                fortyNinthAd.setArticleDescription(
                                "Cette lampe de bureau moderne et orginale offre un éclairage optimal. Design élégant et fonctionnel.");
                fortyNinthAd.setCreationDate(LocalDateTime.now().plusMinutes(49));
                fortyNinthAd.setPrice(BigDecimal.valueOf(45));
                fortyNinthAd.setTitle("Lampe de bureau moderne");
                fortyNinthAd.setPublisher(users.get(3));
                fortyNinthAd.setArticleState("Neuf sans étiquette");
                fortyNinthAd.setCategory("Maison");
                fortyNinthAd.setSubcategory("décorations");
                fortyNinthAd.setStatus(AdStatus.AVAILABLE);
                fourthUserAds.add(fortyNinthAd);

                Ad fiftiethAd = new Ad();
                fiftiethAd.setArticleDescription(
                                "Clavier d'ordinateur sans fil ergonomique pour une saisie confortable et efficace. Idéal pour travailler sans contraintes de câbles.");
                fiftiethAd.setCreationDate(LocalDateTime.now().plusMinutes(50));
                fiftiethAd.setPrice(BigDecimal.valueOf(70));
                fiftiethAd.setTitle("Clavier d'ordinateur sans fil");
                fiftiethAd.setPublisher(users.get(3));
                fiftiethAd.setArticleState("Neuf sans étiquette");
                fiftiethAd.setCategory("Électronique");
                fiftiethAd.setSubcategory("ordinateurs");
                fiftiethAd.setStatus(AdStatus.AVAILABLE);
                fourthUserAds.add(fiftiethAd);

                Ad fiftyFirstAd = new Ad();
                fiftyFirstAd.setArticleDescription(
                                "Baskets de qualité permettant d'augmenter sa street cred. Confort et style assurés.");
                fiftyFirstAd.setCreationDate(LocalDateTime.now().plusMinutes(51));
                fiftyFirstAd.setPrice(BigDecimal.valueOf(80));
                fiftyFirstAd.setTitle("Baskets montantes");
                fiftyFirstAd.setPublisher(users.get(3));
                fiftyFirstAd.setArticleState("Bon");
                fiftyFirstAd.setCategory("Mode");
                fiftyFirstAd.setSubcategory("chaussures");
                fiftyFirstAd.setStatus(AdStatus.AVAILABLE);
                fourthUserAds.add(fiftyFirstAd);

                Ad fiftySecondAd = new Ad();
                fiftySecondAd.setArticleDescription(
                                "Un manteau chaud et stylé pour les mois d'hiver. Parfait pour rester au chaud tout en gardant un look tendance.");
                fiftySecondAd.setCreationDate(LocalDateTime.now().plusMinutes(52));
                fiftySecondAd.setPrice(BigDecimal.valueOf(120));
                fiftySecondAd.setTitle("Manteau d'hiver élégant");
                fiftySecondAd.setPublisher(users.get(3));
                fiftySecondAd.setArticleState("Très bon");
                fiftySecondAd.setCategory("Mode");
                fiftySecondAd.setSubcategory("manteaux");
                fiftySecondAd.setStatus(AdStatus.AVAILABLE);
                fourthUserAds.add(fiftySecondAd);

                Ad fiftyThirdAd = new Ad();
                fiftyThirdAd.setArticleDescription(
                                "Vase en céramique fait à la main, idéal pour embellir votre intérieur avec style et élégance.");
                fiftyThirdAd.setCreationDate(LocalDateTime.now().plusMinutes(53));
                fiftyThirdAd.setPrice(BigDecimal.valueOf(60));
                fiftyThirdAd.setTitle("Vase en céramique");
                fiftyThirdAd.setPublisher(users.get(3));
                fiftyThirdAd.setArticleState("Neuf");
                fiftyThirdAd.setCategory("Maison");
                fiftyThirdAd.setSubcategory("décorations");
                fiftyThirdAd.setStatus(AdStatus.AVAILABLE);
                fourthUserAds.add(fiftyThirdAd);

                Ad fiftyFourthAd = new Ad();
                fiftyFourthAd.setArticleDescription(
                                "Collection de statuettes grec kitch pour décorer votre espace avec une touche d'originalité et d'humour.");
                fiftyFourthAd.setCreationDate(LocalDateTime.now().plusMinutes(54));
                fiftyFourthAd.setPrice(BigDecimal.valueOf(150));
                fiftyFourthAd.setTitle("Collection de statuettes grec kitch");
                fiftyFourthAd.setPublisher(users.get(3));
                fiftyFourthAd.setArticleState("Neuf");
                fiftyFourthAd.setCategory("Maison");
                fiftyFourthAd.setSubcategory("décorations");
                fiftyFourthAd.setStatus(AdStatus.AVAILABLE);
                fourthUserAds.add(fiftyFourthAd);

                Ad fiftyFifthAd = new Ad();
                fiftyFifthAd.setArticleDescription(
                                "AirPods dernier cri pour une qualité audio supérieure et une expérience sans fil optimale.");
                fiftyFifthAd.setCreationDate(LocalDateTime.now().plusMinutes(55));
                fiftyFifthAd.setPrice(BigDecimal.valueOf(250));
                fiftyFifthAd.setTitle("AirPods dernier cri");
                fiftyFifthAd.setPublisher(users.get(3));
                fiftyFifthAd.setArticleState("Neuf");
                fiftyFifthAd.setCategory("Électronique");
                fiftyFifthAd.setSubcategory("autre");
                fiftyFifthAd.setStatus(AdStatus.RESERVED);
                fourthUserAds.add(fiftyFifthAd);

                Ad fiftySixthAd = new Ad();
                fiftySixthAd.setArticleDescription(
                                "Liseuse légère et pratique pour lire vos livres numériques préférés partout où vous allez.");
                fiftySixthAd.setCreationDate(LocalDateTime.now().plusMinutes(56));
                fiftySixthAd.setPrice(BigDecimal.valueOf(100));
                fiftySixthAd.setTitle("Liseuse numérique");
                fiftySixthAd.setPublisher(users.get(3));
                fiftySixthAd.setArticleState("Très bon");
                fiftySixthAd.setCategory("Électronique");
                fiftySixthAd.setSubcategory("autre");
                fiftySixthAd.setStatus(AdStatus.RESERVED);
                fourthUserAds.add(fiftySixthAd);

                Ad fiftySeventhAd = new Ad();
                fiftySeventhAd.setArticleDescription(
                                "Une trottinette colorée et en bon état qui amusera vos enfants et les motivera à faire des balades.");
                fiftySeventhAd.setCreationDate(LocalDateTime.now().plusMinutes(57));
                fiftySeventhAd.setPrice(BigDecimal.valueOf(300));
                fiftySeventhAd.setTitle("Trottinette violette");
                fiftySeventhAd.setPublisher(users.get(3));
                fiftySeventhAd.setArticleState("Bon");
                fiftySeventhAd.setCategory("Loisirs");
                fiftySeventhAd.setSubcategory("Sport");
                fiftySeventhAd.setStatus(AdStatus.SOLD);
                fourthUserAds.add(fiftySeventhAd);

                Ad fiftyEighthAd = new Ad();
                fiftyEighthAd.setArticleDescription(
                                "Canapé confortable et moderne. Parfait pour un salon cosy et accueillant. En excellent état.");
                fiftyEighthAd.setCreationDate(LocalDateTime.now().plusMinutes(58));
                fiftyEighthAd.setPrice(BigDecimal.valueOf(250));
                fiftyEighthAd.setTitle("Canapé moderne");
                fiftyEighthAd.setPublisher(users.get(3));
                fiftyEighthAd.setArticleState("Très bon");
                fiftyEighthAd.setCategory("Maison");
                fiftyEighthAd.setSubcategory("meubles");
                fiftyEighthAd.setStatus(AdStatus.SOLD);
                fourthUserAds.add(fiftyEighthAd);

                // AdSeeder 2 =>
                // pour le 5é user (fifthUserAds - setPublisher(users.get(4)))
                // ajouter 6 ads AVAILABLE - 3 RESERVED - 1 SOLD
                // de fiftyNinth à sixtyNinthAd

                // AdSeeder 3 =>
                // pour le 6é user (fifthUserAds - setPublisher(users.get(5)))
                // ajouter 8 ads AVAILABLE - 1 RESERVED - 1 SOLD
                // de seventiethAd à seventyNinthAd

                users.get(0).setAds(firstUserAds);
                users.get(1).setAds(secondUserAds);
                users.get(2).setAds(thirdUserAds);
                users.get(3).setAds(fourthUserAds);
                users.get(4).setAds(fifthUserAds);
                users.get(5).setAds(sixthUserAds);

                return Arrays.asList(
                                firstAd, secondAd, thirdAd, fourthAd, fifthAd, sixthAd, seventhAd, eighthAd, ninethAd,
                                tenthAd, eleventhAd, twelfthAd, thirteenthAd, fourteenthAd, fifteenthAd, sixteenthAd,
                                seventeenthAd, eighteenthAd, nineteethAd, twentiethAd, twentyFirstAd, twentySecondAd,
                                twentyThirdAd, twentyFourthAd, twentyFifthAd, twentySixthAd, twentySeventhAd,
                                twentyEighthAd, twentyNinthAd, thirtiethAd, thirtyFirstAd, thirtySecondAd,
                                thirtyThirdAd, thirtyFourthAd, thirtyFifthAd, thirtySixthAd, thirtySeventhAd,
                                thirtyEighthAd, thirtyNinthAd, fortiethAd, fortyFirstAd, fortySecondAd, fortyThirdAd,
                                fortyFourthAd, fortyFifthAd, fortySixthAd, fortySeventhAd, fortyEighthAd, fortyNinthAd,
                                fiftiethAd, fiftyFirstAd, fiftySecondAd, fiftyThirdAd, fiftyFourthAd, fiftyFifthAd,
                                fiftySixthAd, fiftySeventhAd, fiftyEighthAd);
        }

        /**
         * Seeds the database with sample ad data.
         * 
         * @param ads The list of ads to save.
         */
        public void seedAds(List<Ad> ads) {
                this.adRepository.saveAll(ads);
        }

        /**
         * Seeds the database with favorites ads.
         * 
         * @param user The user who adds the ad as favorite.
         * @param ad   The ad added as favorite.
         */
        private UsersFavoriteAds createFavoriteAd(User user, Ad ad) {
                UsersFavoriteAdsKey favoriteKey = new UsersFavoriteAdsKey(user.getId(), ad.getId());
                UsersFavoriteAds favoriteAd = new UsersFavoriteAds(favoriteKey, user, ad, LocalDateTime.now());
                this.favoriteRepository.save(favoriteAd);
                return favoriteAd;
        }

        /**
         * Adds up to two ads to the user's favorites if the postal code of the ad's
         * publisher matches the user's postal code.
         *
         * @param user The list of users.
         * @param ads  The list of ads.
         */
        public void seedFavoritesAds(List<User> users, List<Ad> ads) {
                for (User user : users) {
                        List<Ad> matchedAds = ads.stream()
                                        .filter(ad -> !ad.getPublisher().equals(user)
                                                        && ad.getPublisher().getPostalCode()
                                                                        .equals(user.getPostalCode()))
                                        .limit(2)
                                        .toList();

                        for (Ad ad : matchedAds) {
                                createFavoriteAd(user, ad);
                        }
                }
        }
}
