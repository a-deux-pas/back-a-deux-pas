package adeuxpas.back.datainit.seeder;

import adeuxpas.back.entity.Ad;
import adeuxpas.back.entity.ArticlePicture;
import adeuxpas.back.repository.ArticlePictureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * A component responsible for seeding article pictures into the database.
 */
@Component
public class ArticlePictureSeeder {
    private final ArticlePictureRepository articlePictureRepository;

    /**
     * Constructs an instance of ArticlePictureSeeder.
     * @param articlePictureRepository The repository for article pictures.
     */
    public ArticlePictureSeeder(@Autowired ArticlePictureRepository articlePictureRepository){
        this.articlePictureRepository = articlePictureRepository;
    }

    /**
     * Seeds article pictures into the database for the provided list of ads.
     * @param ads The list of ads for which article pictures will be seeded.
     */
    public void seedArticlePictures(List<Ad> ads) {
        // saves 5 article pictures for the first ad
        this.articlePictureRepository.save(new ArticlePicture(("https://d4yxl4pe8dqlj.cloudfront.net/images/1cf6fa0d-2a74-4e0e-a98f-1a4d1c0dab5c/90b00509-dd97-46e3-b832-24eb8eaa8e0b_large.jpg"), ads.getFirst()));
        this.articlePictureRepository.save(new ArticlePicture(("https://d2yn9m4p3q9iyv.cloudfront.net/rockymountain/2023/growler-40/thumbs/1000/62903.webp"), ads.getFirst()));
        this.articlePictureRepository.save(new ArticlePicture(("https://d4yxl4pe8dqlj.cloudfront.net/images/1cf6fa0d-2a74-4e0e-a98f-1a4d1c0dab5c/13185af6-1f03-4cdc-8545-af767b7e6bb3_large.jpg"), ads.getFirst()));
        this.articlePictureRepository.save(new ArticlePicture(("https://i.ytimg.com/vi/2B1bWKBTROE/sddefault.jpg"), ads.getFirst()));
        this.articlePictureRepository.save(new ArticlePicture(("https://d4yxl4pe8dqlj.cloudfront.net/images/1cf6fa0d-2a74-4e0e-a98f-1a4d1c0dab5c/cf15ac41-1979-4264-9193-3b0c2d302907_large.jpg"), ads.getFirst()));
        // saves one article picture for every remaining ad
        this.articlePictureRepository.save(new ArticlePicture(("https://i.ebayimg.com/images/g/5DMAAOSwpIVldRxH/s-l1200.webp"), ads.get(1)));
        this.articlePictureRepository.save(new ArticlePicture(("https://andreemilio.com/wp-content/uploads/2021/03/Mens-Light-gray-3-Piece-Suit-2.jpg"), ads.get(2)));
        this.articlePictureRepository.save(new ArticlePicture(("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQsejXk4iTIzAqvORn0DSomectnXd0l3A3fVQ&usqp=CAU"), ads.get(3)));
        this.articlePictureRepository.save(new ArticlePicture(("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTBezaIpsONE88lxdf3WCBUKcVLDd9gWxBCEQ&usqp=CAU"), ads.get(4)));
        this.articlePictureRepository.save(new ArticlePicture(("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSWRH_Z_65KbwGUDdA2KcCzqroUzVXmmq68NA&usqp=CAU"), ads.get(5)));
        this.articlePictureRepository.save(new ArticlePicture(("https://i.pinimg.com/originals/bc/e9/1b/bce91b03652cc6172ddb755cc9128f45.jpg"), ads.get(6)));
        this.articlePictureRepository.save(new ArticlePicture(("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSmvcDyC-LjyFt6_RI_dYLUcHhaJahuXUexcw&usqp=CAU"), ads.get(7)));
        this.articlePictureRepository.save(new ArticlePicture(("https://images1.vinted.net/t/01_00e44_XkRJUZhGUrNwxbLGfBs8erYr/f800/1711863484.jpeg?s=27fff3a33315f91019270a1a3bb53e9615c2f962"), ads.get(8)));
        this.articlePictureRepository.save(new ArticlePicture(("https://images1.vinted.net/t/03_018e1_N2JsK2fxtrZn1xk16xNo9kyr/f800/1711856284.jpeg?s=091fb7a67a8dc1adab7bd7994442b87c7f7b112e"), ads.get(9)));
        this.articlePictureRepository.save(new ArticlePicture(("https://images1.vinted.net/t/03_01ff4_Ly9usSkKK7x4ZLUNJYEJY7vV/f800/1711768613.jpeg?s=eda3db9c2dabce566e20a82b06293f490b7054f7"), ads.get(10)));
        this.articlePictureRepository.save(new ArticlePicture(("https://images1.vinted.net/t/03_01441_xix4NLunHyRfpdhiqMyka3BL/f800/1711760473.jpeg?s=8abaf00255f5ec6a3422800b711dc43e8e3d7cfe"), ads.get(11)));
        this.articlePictureRepository.save(new ArticlePicture(("https://images1.vinted.net/t/01_009bf_biwV7SSYALHvZjeMx9DzEgPF/f800/1711666972.jpeg?s=796daddfcb0cd0dec79c877ac08569f87e693da5"), ads.get(12)));
        this.articlePictureRepository.save(new ArticlePicture(("https://images1.vinted.net/t/01_01107_AQkaWBDeUJWEFWcx4sxwaFTe/f800/1702265856.jpeg?s=19117baf0b87627abb81a9e27574b0ac30133f07"), ads.get(13)));
        this.articlePictureRepository.save(new ArticlePicture(("https://images1.vinted.net/t/01_021d2_GVTqnTQ3oFnegSHv1mJU9Amz/f800/1686792993.jpeg?s=1451865905183fb060fd76197322267ae23cc159"), ads.get(14)));
        this.articlePictureRepository.save(new ArticlePicture(("https://images1.vinted.net/t/02_009c7_eEKstfbvuKJyXRHLbJvUQhVy/f800/1711815268.jpeg?s=4b5e6eded4ab45c8c932850aa25179984646042b"), ads.get(15)));
        this.articlePictureRepository.save(new ArticlePicture(("https://images1.vinted.net/t/02_020f4_3yFUxux5NH3ajCpZwWfR6P5n/f800/1707671737.jpeg?s=d603b47dc9eec529765f9eae6c152c752a0954bd"), ads.get(16)));
        this.articlePictureRepository.save(new ArticlePicture(("https://images1.vinted.net/t/03_0017b_9CsbxFBTWrpcWW1YRLbLaiuk/f800/1711794225.jpeg?s=e8e59ac218eeba65a2b6f543f20c0ae7e526c60e"), ads.get(5)));
        this.articlePictureRepository.save(new ArticlePicture(("https://images1.vinted.net/t/03_01b2f_QECcdvwJTvYk5dWoHqheetyQ/f800/1707104826.jpeg?s=d8b8a122fd856daf5a0141ab2a7083418509a327"), ads.get(6)));
        this.articlePictureRepository.save(new ArticlePicture(("https://images1.vinted.net/t/03_003bc_zRPMb8VY3YDhXxrghu6gJcUM/f800/1710778480.jpeg?s=e35e3cb9915ac7f628d1d00785971d264e31f47e"), ads.get(17)));
    }
}
