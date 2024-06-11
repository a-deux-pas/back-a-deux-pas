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
        // saves five article pictures for the first ad
        this.articlePictureRepository.save(new ArticlePicture(("https://d4yxl4pe8dqlj.cloudfront.net/images/1cf6fa0d-2a74-4e0e-a98f-1a4d1c0dab5c/90b00509-dd97-46e3-b832-24eb8eaa8e0b_large.jpg"), ads.getFirst()));
        this.articlePictureRepository.save(new ArticlePicture(("https://d2yn9m4p3q9iyv.cloudfront.net/rockymountain/2023/growler-40/thumbs/1000/62903.webp"), ads.getFirst()));
        this.articlePictureRepository.save(new ArticlePicture(("https://d4yxl4pe8dqlj.cloudfront.net/images/1cf6fa0d-2a74-4e0e-a98f-1a4d1c0dab5c/13185af6-1f03-4cdc-8545-af767b7e6bb3_large.jpg"), ads.getFirst()));
        this.articlePictureRepository.save(new ArticlePicture(("https://i.ytimg.com/vi/2B1bWKBTROE/sddefault.jpg"), ads.getFirst()));
        this.articlePictureRepository.save(new ArticlePicture(("https://d4yxl4pe8dqlj.cloudfront.net/images/1cf6fa0d-2a74-4e0e-a98f-1a4d1c0dab5c/cf15ac41-1979-4264-9193-3b0c2d302907_large.jpg"), ads.getFirst()));
        // saves two article pictures each for every remaining ad
        this.articlePictureRepository.save(new ArticlePicture(("https://i.ebayimg.com/images/g/5DMAAOSwpIVldRxH/s-l1200.webp"), ads.get(1)));
        this.articlePictureRepository.save(new ArticlePicture(("https://i.etsystatic.com/5308923/r/il/b6e93b/3992576022/il_fullxfull.3992576022_ldkx.jpg"), ads.get(1)));
        this.articlePictureRepository.save(new ArticlePicture(("https://andreemilio.com/wp-content/uploads/2021/03/Mens-Light-gray-3-Piece-Suit-2.jpg"), ads.get(2)));
        this.articlePictureRepository.save(new ArticlePicture(("https://consiglieri.ro/cdn/shop/products/Costum-elegant-3-piese-mire.jpg?v=1672937997&width=1000"), ads.get(2)));
        this.articlePictureRepository.save(new ArticlePicture(("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQsejXk4iTIzAqvORn0DSomectnXd0l3A3fVQ&usqp=CAU"), ads.get(3)));
        this.articlePictureRepository.save(new ArticlePicture(("https://img.ricardostatic.ch/images/650d7fbb-695f-4267-935c-f61a9267e9cc/t_1000x750/frederique-constant-1988-moonphase-limit"), ads.get(3)));
        this.articlePictureRepository.save(new ArticlePicture(("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTBezaIpsONE88lxdf3WCBUKcVLDd9gWxBCEQ&usqp=CAU"), ads.get(4)));
        this.articlePictureRepository.save(new ArticlePicture(("https://mainguyen.sgp1.digitaloceanspaces.com/245767/anh-bia.jpg"), ads.get(4)));
        this.articlePictureRepository.save(new ArticlePicture(("https://mymerchandize.com/cdn/shop/products/A-_127.jpg?v=1641220439&width=823"), ads.get(5)));
        this.articlePictureRepository.save(new ArticlePicture(("https://mymerchandize.com/cdn/shop/products/D_127.jpg?v=1641220439&width=823"), ads.get(5)));
        this.articlePictureRepository.save(new ArticlePicture(("https://i.pinimg.com/originals/bc/e9/1b/bce91b03652cc6172ddb755cc9128f45.jpg"), ads.get(6)));
        this.articlePictureRepository.save(new ArticlePicture(("https://i.pinimg.com/564x/14/fa/46/14fa4658eb01318b93cb05f5e4887cf4.jpg"), ads.get(6)));
        this.articlePictureRepository.save(new ArticlePicture(("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSmvcDyC-LjyFt6_RI_dYLUcHhaJahuXUexcw&usqp=CAU"), ads.get(7)));
        this.articlePictureRepository.save(new ArticlePicture(("https://images.pangobooks.com/book_images/JYQR3EaqAxSxN6R0UaFSnchBnNt1/1670885193125_JYQR3EaqAxSxN6R0UaFSnchBnNt1?width=800&quality=85&crop=1%3A1"), ads.get(7)));
        this.articlePictureRepository.save(new ArticlePicture(("https://images1.vinted.net/t/03_00a48_mULjimGpe2vn5FFRc2LkMjzB/f800/1715844892.jpeg?s=2b48555079b07bcd57846666ac1791f6504e6335"), ads.get(8)));
        this.articlePictureRepository.save(new ArticlePicture(("https://images1.vinted.net/t/03_01715_DimQnivrfXtzpAnGBPidzFpL/f800/1715844892.jpeg?s=e52fabb367ead8569766f9645474a802005d8c9a"), ads.get(8)));
        this.articlePictureRepository.save(new ArticlePicture(("https://images1.vinted.net/t/03_019e3_8ArcAYcwkuAYQfb3Ny5Gr7aQ/f800/1669748518.jpeg?s=ca2a7afe9cb14e32e0ba7b2bf9d02d3b1417bbf9"), ads.get(9)));
        this.articlePictureRepository.save(new ArticlePicture(("https://images1.vinted.net/t/03_0025e_zKviz275Ew3Y2TdbXfrtMXz1/f800/1669748518.jpeg?s=26d8772eb9cd8e6971de703f06a7ef8d3bc167e9"), ads.get(9)));
        this.articlePictureRepository.save(new ArticlePicture(("https://images1.vinted.net/t/03_01ff4_Ly9usSkKK7x4ZLUNJYEJY7vV/f800/1711768613.jpeg?s=eda3db9c2dabce566e20a82b06293f490b7054f7"), ads.get(10)));
        this.articlePictureRepository.save(new ArticlePicture(("https://images1.vinted.net/t/03_00cf8_AXxrpSNdKDVAsGbaayzjMSTy/f800/1688314139.jpeg?s=35055d2668c3ad00fc89f974e5528b9e259eaebc"), ads.get(10)));
        this.articlePictureRepository.save(new ArticlePicture(("https://images1.vinted.net/t/01_01675_4WVxZh5H89zCFzPUiHBpb6fM/f800/1687273961.jpeg?s=c83e0a320536ca46f91b127415d8dc3900acf860"), ads.get(11)));
        this.articlePictureRepository.save(new ArticlePicture(("https://images1.vinted.net/t/02_0082f_5dndpFkSM5oziH4g1GAASsVP/f800/1687273961.jpeg?s=b463f489b364da55e557f18c6fc26a0b64b62c3c"), ads.get(11)));
        this.articlePictureRepository.save(new ArticlePicture(("https://images1.vinted.net/t/02_01250_5wEmb1CuSsx6tTYuxYmKoshy/f800/1706121420.jpeg?s=213c95c84c1bffe64a45df9d2a2af046c0c73531"), ads.get(12)));
        this.articlePictureRepository.save(new ArticlePicture(("https://images1.vinted.net/t/01_02548_rYEV1aD76b6PyPdbXTk7peY1/f800/1706121420.jpeg?s=c911290bbe073c0c3d19fa801a45b1aea859a96c"), ads.get(12)));
        this.articlePictureRepository.save(new ArticlePicture(("https://images1.vinted.net/t/03_02156_MTr4fa7do3PScBHVNJDCBT2W/f800/1638128210.jpeg?s=8f350ea7f139f98bab6d8cf99306c5b67a6ec8ca"), ads.get(13)));
        this.articlePictureRepository.save(new ArticlePicture(("https://images1.vinted.net/t/03_003ac_UiSZuqisFc7RBsK3s33zSmN3/f800/1638128210.jpeg?s=d122ae1b114e5d1de1bb38bf5616e9673864a1a6"), ads.get(13)));
        this.articlePictureRepository.save(new ArticlePicture(("https://images1.vinted.net/t/01_021d2_GVTqnTQ3oFnegSHv1mJU9Amz/f800/1686792993.jpeg?s=1451865905183fb060fd76197322267ae23cc159"), ads.get(14)));
        this.articlePictureRepository.save(new ArticlePicture(("https://lostandfoundry.ca/cdn/shop/files/4-2_bc23edfc-9716-44a4-84b5-f7b067cbe0eb.jpg?v=1704726410&width=1946"), ads.get(14)));
        this.articlePictureRepository.save(new ArticlePicture(("https://images1.vinted.net/t/03_01db7_rx6w7AuhZV8G5n25C6dEzfSd/f800/1639374722.jpeg?s=bb12e15cd5c911fb16293b7d434515fd2f125639"), ads.get(15)));
        this.articlePictureRepository.save(new ArticlePicture(("https://images1.vinted.net/t/02_022a0_qbB8mDJ4fW87SVANVnrPR2j4/f800/1639374722.jpeg?s=fe5db7961fd154710590b8477f3728ab931a7745"), ads.get(15)));
        this.articlePictureRepository.save(new ArticlePicture(("https://images1.vinted.net/t/01_00608_7Bmz2zZTNHi1vqPwXDbVvtHd/f800/1594642435.jpeg?s=42b393c8671492d4b4d2d270602fbff332505d2a"), ads.get(16)));
        this.articlePictureRepository.save(new ArticlePicture(("https://images1.vinted.net/t/01_0242d_HPaeV9t4Tfst6H9qz5UB9FrP/f800/1594642435.jpeg?s=c699ec82a36fc8b5d13d63ae2ec5de05b279992a"), ads.get(16)));
        this.articlePictureRepository.save(new ArticlePicture(("https://i.etsystatic.com/19718742/r/il/c40c8e/2473538663/il_794xN.2473538663_2i24.jpg"), ads.get(17)));
        this.articlePictureRepository.save(new ArticlePicture(("https://i.etsystatic.com/19718742/r/il/579da6/2473538735/il_794xN.2473538735_3gse.jpg"), ads.get(17)));
    }
}
