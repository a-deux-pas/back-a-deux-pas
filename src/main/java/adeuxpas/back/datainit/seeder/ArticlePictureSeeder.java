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

        static final String CLOUDINARY_URL = "https://res.cloudinary.com/erikaadeuxpas/image/upload/"; 

        /**
         * Constructs an instance of ArticlePictureSeeder.
         * 
         * @param articlePictureRepository The repository for article pictures.
         */
        public ArticlePictureSeeder(@Autowired ArticlePictureRepository articlePictureRepository) {
                this.articlePictureRepository = articlePictureRepository;
        }

        /**
         * Seeds article pictures into the database for the provided list of ads.
         * 
         * @param ads The list of ads for which article pictures will be seeded.
         */
        // MAnque deux images
        public void seedArticlePictures(List<Ad> ads) {
                this.articlePictureRepository.save(new ArticlePicture(
                                (CLOUDINARY_URL + "t_finalADeuxPasTransform/v1719728385/pexels-luftschnitzel-100582_c22r28.webp"),
                                ads.get(0)));
                this.articlePictureRepository.save(new ArticlePicture(
                                (CLOUDINARY_URL + "t_finalADeuxPasTransform/v1719728384/pexels-bianca-gasparoto-834990-1753137_vamuk1.webp"),
                                ads.get(0)));
                this.articlePictureRepository.save(new ArticlePicture(
                                (CLOUDINARY_URL + "t_finalADeuxPasTransform/v1719728384/pexels-pixabay-276517_kzcfne.webp"),
                                ads.get(0)));
                this.articlePictureRepository
                                .save(new ArticlePicture(("https://i.ytimg.com/vi/2B1bWKBTROE/sddefault.jpg"),
                                                ads.get(0)));
                this.articlePictureRepository.save(new ArticlePicture(
                                ("https://d4yxl4pe8dqlj.cloudfront.net/images/1cf6fa0d-2a74-4e0e-a98f-1a4d1c0dab5c/cf15ac41-1979-4264-9193-3b0c2d302907_large.jpg"),
                                ads.get(0)));

                this.articlePictureRepository
                                .save(new ArticlePicture(
                                                (CLOUDINARY_URL + "t_finalADeuxPasTransform/v1719728384/pexels-cottonbro-4754146_vony3v.webp"),
                                                ads.get(1)));
                this.articlePictureRepository.save(new ArticlePicture(
                                (CLOUDINARY_URL + "t_finalADeuxPasTransform/v1719728384/pexels-cottonbro-4754143_dyidsp.webp"),
                                ads.get(1)));

                this.articlePictureRepository.save(new ArticlePicture(
                                (CLOUDINARY_URL + "t_finalADeuxPasTransform/v1719728385/pexels-teddyjmodel-2955376_sut8xd.webp"),
                                ads.get(2)));
                this.articlePictureRepository.save(new ArticlePicture(
                                (CLOUDINARY_URL + "t_finalADeuxPasTransform/v1719728386/pexels-teddyjmodel-2955375_fq5f2d.webp"),
                                ads.get(2)));

                this.articlePictureRepository.save(new ArticlePicture(
                                (CLOUDINARY_URL + "t_finalADeuxPasTransform/v1719728384/pexels-alexazabache-3766113_aex8zp.webp"),
                                ads.get(3)));
                this.articlePictureRepository.save(
                                new ArticlePicture((CLOUDINARY_URL
                                                + "t_finalADeuxPasTransform/v1719728384/pexels-alexazabache-3766111_c0jpsn.webp"),
                                                ads.get(3)));

                this.articlePictureRepository.save(new ArticlePicture(
                                (CLOUDINARY_URL + "t_finalADeuxPasTransform/v1719728387/pexels-tirachard-kumtanom-112571-1001850_rejk6d.webp"),
                                ads.get(4)));
                this.articlePictureRepository.save(new ArticlePicture(
                                (CLOUDINARY_URL + "t_finalADeuxPasTransform/v1719728386/pexels-sound-on-3756943_dscbvy.webp"),
                                ads.get(4)));
                this.articlePictureRepository.save(new ArticlePicture(
                                (CLOUDINARY_URL + "t_finalADeuxPasTransform/v1719728385/pexels-sound-on-3756767_o4p37g.webp"),
                                ads.get(4)));
                this.articlePictureRepository.save(new ArticlePicture(
                                (CLOUDINARY_URL + "t_finalADeuxPasTransform/v1719728384/pexels-olly-783243_wz7mh3.webp"),
                                ads.get(4)));

                this.articlePictureRepository.save(new ArticlePicture(
                                (CLOUDINARY_URL + "t_finalADeuxPasTransform/v1719728387/pexels-victoria-strelka_ph-128225472-11034413_rlayvc.webp"),
                                ads.get(5)));
                this.articlePictureRepository.save(new ArticlePicture(
                                (CLOUDINARY_URL + "t_finalADeuxPasTransform/v1719728386/pexels-thirdman-5592311_z2l8ui.webp"),
                                ads.get(5)));
                this.articlePictureRepository.save(new ArticlePicture(
                                (CLOUDINARY_URL + "t_finalADeuxPasTransform/v1719728387/pexels-thirdman-5592309_ku7xgp.webp"),
                                ads.get(5)));
                this.articlePictureRepository.save(new ArticlePicture(
                                (CLOUDINARY_URL + "t_finalADeuxPasTransform/v1719728385/pexels-thirdman-5592307_go2ifv.webp"),
                                ads.get(5)));
                this.articlePictureRepository.save(new ArticlePicture(
                                (CLOUDINARY_URL + "t_finalADeuxPasTransform/v1719728386/pexels-thirdman-5592605_mwemdr.webp"),
                                ads.get(5)));

                this.articlePictureRepository.save(new ArticlePicture(
                                (CLOUDINARY_URL + "t_finalADeuxPasTransform/v1719730709/pexels-mikhail-nilov-7676884_cvl4to.webp"),
                                ads.get(6)));
                this.articlePictureRepository.save(new ArticlePicture(
                                (CLOUDINARY_URL + "t_finalADeuxPasTransform/v1719730709/pexels-ron-lach-8892878_znbah0.webp"),
                                ads.get(6)));
                this.articlePictureRepository.save(new ArticlePicture(
                                (CLOUDINARY_URL + "t_finalADeuxPasTransform/v1719730709/pexels-shvets-production-8416421_sqgrfx.webp"),
                                ads.get(6)));

                this.articlePictureRepository.save(new ArticlePicture(
                                (CLOUDINARY_URL + "t_finalADeuxPasTransform/v1719730707/pexels-mccutcheon-1148399_onup0o.webp"),
                                ads.get(7)));
                this.articlePictureRepository.save(new ArticlePicture(
                                (CLOUDINARY_URL + "t_finalADeuxPasTransform/v1719730709/pexels-pixabay-159711_fepwpb.webp"),
                                ads.get(7)));
                this.articlePictureRepository.save(new ArticlePicture(
                                (CLOUDINARY_URL + "t_finalADeuxPasTransform/v1719730707/pexels-jessbaileydesign-762686_p4fdts.webp"),
                                ads.get(7)));
                this.articlePictureRepository.save(new ArticlePicture(
                                (CLOUDINARY_URL + "t_finalADeuxPasTransform/v1719730706/pexels-emily-252615-768125_uxnypu.webp"),
                                ads.get(7)));

                this.articlePictureRepository.save(new ArticlePicture(
                                (CLOUDINARY_URL + "t_finalADeuxPasTransform/v1719730706/pexels-godisable-jacob-226636-1191531_thyfms.webp"),
                                ads.get(8)));
                this.articlePictureRepository.save(new ArticlePicture(
                                (CLOUDINARY_URL + "t_finalADeuxPasTransform/v1719730706/pexels-godisable-jacob-226636-1191536_d7vvgm.webp"),
                                ads.get(8)));

                this.articlePictureRepository.save(new ArticlePicture(
                                (CLOUDINARY_URL + "t_finalADeuxPasTransform/v1719730706/pexels-godisable-jacob-226636-1374910_lg9lid.webp"),
                                ads.get(9)));
                this.articlePictureRepository.save(new ArticlePicture(
                                (CLOUDINARY_URL + "t_finalADeuxPasTransform/v1719730706/pexels-godisable-jacob-226636-1653217_rhksif.webp"),
                                ads.get(9)));
                this.articlePictureRepository.save(new ArticlePicture(
                                (CLOUDINARY_URL + "t_finalADeuxPasTransform/v1719730706/pexels-godisable-jacob-226636-932401_q2fhxc.webp"),
                                ads.get(9)));

                this.articlePictureRepository.save(new ArticlePicture(
                                (CLOUDINARY_URL + "t_finalADeuxPasTransform/v1719772584/pexels-sarah-chai-7282378_kkbzdt.webp"),
                                ads.get(10)));
                this.articlePictureRepository.save(new ArticlePicture(
                                (CLOUDINARY_URL + "t_check/v1719772578/pexels-sarah-chai-7282376_mleva1.webp"),
                                ads.get(10)));

                this.articlePictureRepository.save(new ArticlePicture(
                                (CLOUDINARY_URL + "t_check/v1719730709/pexels-pixabay-160483_cpqqlv.webp"),
                                ads.get(11)));
                this.articlePictureRepository.save(new ArticlePicture(
                                (CLOUDINARY_URL + "t_finalADeuxPasTransform/v1719730710/pexels-thatguycraig000-1682694_ftzssf.webp"),
                                ads.get(11)));
                this.articlePictureRepository.save(new ArticlePicture(
                                (CLOUDINARY_URL + "t_finalADeuxPasTransform/v1719730705/pexels-edgar-okioga-221433-2646521_jeptef.webp"),
                                ads.get(11)));

                this.articlePictureRepository.save(new ArticlePicture(
                                (CLOUDINARY_URL + "t_check/v1719732376/pexels-mizunokozuki-13443813_rq3vnz.webp"),
                                ads.get(12)));
                this.articlePictureRepository.save(new ArticlePicture(
                                (CLOUDINARY_URL + "t_finalADeuxPasTransform/v1719732374/pexels-mizunokozuki-13443809_mcqrqt.webp"),
                                ads.get(12)));
                this.articlePictureRepository.save(new ArticlePicture(
                                (CLOUDINARY_URL + "t_finalADeuxPasTransform/v1719732372/pexels-mizunokozuki-13443804_tweedr.webp"),
                                ads.get(12)));
                this.articlePictureRepository.save(new ArticlePicture(
                                (CLOUDINARY_URL + "t_finalADeuxPasTransform/v1719732371/pexels-mizunokozuki-13443796_pxmq4f.webp"),
                                ads.get(12)));

                this.articlePictureRepository.save(new ArticlePicture(
                                (CLOUDINARY_URL + "t_check/v1719732359/pexels-brett-sayles-2250519_ziwiyg.webp"),
                                ads.get(13)));
                this.articlePictureRepository.save(new ArticlePicture(
                                (CLOUDINARY_URL + "t_check/v1719732383/pexels-trinitykubassek-242149_if5bqn.webp"),
                                ads.get(13)));

                this.articlePictureRepository.save(new ArticlePicture(
                                (CLOUDINARY_URL + "t_check/v1719732362/pexels-hazardos-1475406_aw78hf.webp"),
                                ads.get(14)));
                this.articlePictureRepository.save(new ArticlePicture(
                                (CLOUDINARY_URL + "t_check/v1719732364/pexels-hazardos-1475418_ncbnwj.webp"),
                                ads.get(14)));
                this.articlePictureRepository.save(new ArticlePicture(
                                (CLOUDINARY_URL + "t_check/v1719732379/pexels-rethaferguson-3819775_bm9ewn.webp"),
                                ads.get(14)));

                this.articlePictureRepository.save(new ArticlePicture(
                                (CLOUDINARY_URL + "t_check/v1719732369/pexels-merve-cetin-612403813-21717789_a1rsc2.webp"),
                                ads.get(15)));
                this.articlePictureRepository.save(new ArticlePicture(
                                (CLOUDINARY_URL + "t_check/v1719732367/pexels-merve-cetin-612403813-21717788_vlpsqk.webp"),
                                ads.get(15)));

                this.articlePictureRepository.save(new ArticlePicture(
                                (CLOUDINARY_URL + "t_check/v1719772592/pexels-valeriya-185489_bhy5qs.webp"),
                                ads.get(16)));
                this.articlePictureRepository.save(new ArticlePicture(
                                (CLOUDINARY_URL + "t_check/v1719772596/pexels-valeriya-59662_unm1kn.webp"),
                                ads.get(16)));

                this.articlePictureRepository.save(new ArticlePicture(
                                (CLOUDINARY_URL + "t_finalADeuxPasTransform/v1719732359/pexels-charlotte-may-5946627_roj4vu.webp"),
                                ads.get(17)));
                this.articlePictureRepository.save(new ArticlePicture(
                                (CLOUDINARY_URL + "t_check/v1719732361/pexels-gabby-k-6373839_lflsh0.webp"),
                                ads.get(17)));

                this.articlePictureRepository.save(new ArticlePicture(
                                (CLOUDINARY_URL + "t_check/v1719638601/pexels-psad-2778192-min_zokni4.webp"),
                                ads.get(18)));
                this.articlePictureRepository.save(new ArticlePicture(
                                (CLOUDINARY_URL + "t_check/v1719638603/pexels-zyuliansyah-1671256-min_kw7x0g.webp"),
                                ads.get(18)));
                this.articlePictureRepository.save(new ArticlePicture(
                                (CLOUDINARY_URL + "t_check/v1719638601/pexels-todd-trapani-488382-1382195-min_qxk3iy.webp"),
                                ads.get(18)));
                this.articlePictureRepository.save(new ArticlePicture(
                                (CLOUDINARY_URL + "t_check/v1719638600/pexels-quang-nguyen-vinh-222549-2149105-min_fesj7m.webp"),
                                ads.get(18)));

                this.articlePictureRepository.save(new ArticlePicture(
                                (CLOUDINARY_URL + "t_check/v1719637960/pexels-fotios-photos-1092644-min_j4gat5.webp"),
                                ads.get(19)));
                this.articlePictureRepository.save(new ArticlePicture(
                                (CLOUDINARY_URL + "t_check/v1719637960/pexels-fotios-photos-1092671-min_rv2mhs.webp"),
                                ads.get(19)));
                this.articlePictureRepository.save(new ArticlePicture(
                                (CLOUDINARY_URL + "t_check/v1719637961/pexels-lastly-699122-min_vmqh6m.webp"),
                                ads.get(19)));
                
                this.articlePictureRepository.save(new ArticlePicture(
                                (CLOUDINARY_URL + "t_check/v1719638597/pexels-naeco-625788-min_lcbfec.webp"),
                                ads.get(20)));
                this.articlePictureRepository.save(new ArticlePicture(
                                (CLOUDINARY_URL + "t_finalADeuxPasTransform/v1719732377/pexels-pixabay-164729_llsuw8.webp"),
                                ads.get(20)));

                this.articlePictureRepository.save(new ArticlePicture(
                                (CLOUDINARY_URL + "t_check/v1719637958/pexels-adrienne-andersen-1174503-2387754-min_slohzz.webp"),
                                ads.get(21)));
                this.articlePictureRepository.save(new ArticlePicture(
                                (CLOUDINARY_URL + "t_check/v1719638597/pexels-lina-1813466-min_gco1l1.webp"),
                                ads.get(21)));

                this.articlePictureRepository.save(new ArticlePicture(
                                (CLOUDINARY_URL + "t_finalADeuxPasTransform/v1719637959/pexels-cottonbro-5083015-min_urnm38.webp"),
                                ads.get(22)));
                this.articlePictureRepository.save(new ArticlePicture(
                                (CLOUDINARY_URL + "t_finalADeuxPasTransform/v1719637958/pexels-cottonbro-5083014-min_zqjicj.webp"),
                                ads.get(22)));

                this.articlePictureRepository.save(new ArticlePicture(
                                (CLOUDINARY_URL + "t_check/v1719637960/pexels-fotios-photos-1006293-min_g7mpyi.webp"),
                                ads.get(23)));
                this.articlePictureRepository.save(new ArticlePicture(
                                (CLOUDINARY_URL + "t_check/v1719637959/pexels-format-380633-1029757-min_bhkdhv.webp"),
                                ads.get(23)));
                this.articlePictureRepository.save(new ArticlePicture(
                                (CLOUDINARY_URL + "t_check/v1719638596/pexels-life-of-pix-7974-min_fudjl3.webp"),
                                ads.get(23)));

                this.articlePictureRepository.save(new ArticlePicture(
                                (CLOUDINARY_URL + "t_check/v1719638595/pexels-leah-newhouse-50725-373465-min_aabj1f.webp"),
                                ads.get(24)));
                this.articlePictureRepository.save(new ArticlePicture(
                                (CLOUDINARY_URL + "t_finalADeuxPasTransform/v1719637960/pexels-jessbaileydesign-762687-min_gdxktt.webp"),
                                ads.get(24)));

                this.articlePictureRepository.save(new ArticlePicture(
                                (CLOUDINARY_URL + "t_finalADeuxPasTransform/v1719638600/pexels-nytheone-1032110-min_u2ibtj.webp"),
                                ads.get(25)));
                this.articlePictureRepository.save(new ArticlePicture(
                                (CLOUDINARY_URL + "t_finalADeuxPasTransform/v1719638600/pexels-obviouslyarthur-1102776-min_mudljg.webp"),
                                ads.get(25)));

                this.articlePictureRepository.save(new ArticlePicture(
                                (CLOUDINARY_URL + "t_check/v1719638597/pexels-mati-4734715-min_xcz7fq.webp"),
                                ads.get(26)));
                this.articlePictureRepository.save(new ArticlePicture(
                                (CLOUDINARY_URL + "t_finalADeuxPasTransform/v1719637958/pexels-amaria-14213114-min_vra14z.webp"),
                                ads.get(26)));

                this.articlePictureRepository.save(new ArticlePicture(
                                (CLOUDINARY_URL + "t_finalADeuxPasTransform/v1719637959/pexels-efecan-efe-44398118-8135545-min_ofuu9f.webp"),
                                ads.get(27)));
                this.articlePictureRepository.save(new ArticlePicture(
                                (CLOUDINARY_URL + "t_finalADeuxPasTransform/v1719637959/pexels-charlotte-may-5824901-min_s4qpdy.webp"),
                                ads.get(27)));

                this.articlePictureRepository.save(new ArticlePicture(
                                (CLOUDINARY_URL + "t_check/v1719638598/pexels-noorschal-16998814-min_z4le9a.webp"),
                                ads.get(28)));
                this.articlePictureRepository.save(new ArticlePicture(
                                (CLOUDINARY_URL + "t_finalADeuxPasTransform/v1719637960/pexels-god-picture-369194295-14465199-min_dfbtvj.webp"),
                                ads.get(28)));

                this.articlePictureRepository.save(new ArticlePicture(
                                (CLOUDINARY_URL + "t_check/v1719637958/pexels-automnenoble-398078-min_zyxpua.webp"),
                                ads.get(29)));
                this.articlePictureRepository.save(new ArticlePicture(
                                (CLOUDINARY_URL + "t_finalADeuxPasTransform/v1719637961/pexels-karolina-grabowska-4210863-min_nlg9s6.webp"),
                                ads.get(29)));
                this.articlePictureRepository.save(new ArticlePicture(
                                (CLOUDINARY_URL + "t_finalADeuxPasTransform/v1719637960/pexels-ianandradef-1852907-min_sm1ktv.webp"),
                                ads.get(29)));

                this.articlePictureRepository.save(new ArticlePicture((CLOUDINARY_URL
                                + "t_check/v1719752232/pexels-vidalbalielojrfotografia-1682519_ogjc6q.webp"),
                                ads.get(30)));
                this.articlePictureRepository.save(new ArticlePicture(
                                (CLOUDINARY_URL + "t_check/v1719752230/pexels-jeshoots-com-147458-1201996_bprfml.webp"),
                                ads.get(30)));

                this.articlePictureRepository.save(new ArticlePicture(
                                (CLOUDINARY_URL + "t_check/v1719752232/pexels-pixabay-159839_xqqwvg.webp"),
                                ads.get(31)));
                this.articlePictureRepository.save(new ArticlePicture(
                                (CLOUDINARY_URL + "t_check/v1719752231/pexels-fotios-photos-1957477_h47out.webp"),
                                ads.get(31)));

                this.articlePictureRepository.save(new ArticlePicture(
                                (CLOUDINARY_URL + "t_check/v1719752229/pexels-alexkrugla-12426167_q2fkyn.webp"),
                                ads.get(32)));
                this.articlePictureRepository.save(new ArticlePicture(
                                (CLOUDINARY_URL + "t_check/v1719752229/pexels-dziana-hasanbekava-7809767_itvmwl.webp"),
                                ads.get(32)));
                this.articlePictureRepository.save(new ArticlePicture(
                                (CLOUDINARY_URL + "t_check/v1719752232/pexels-readymade-3850922_lfbepm.webp"),
                                ads.get(32)));

                this.articlePictureRepository.save(new ArticlePicture((CLOUDINARY_URL
                                + "t_check/v1719752232/pexels-michelangelo-buonarroti-8728562_ebuqqf.webp"),
                                ads.get(33)));
                this.articlePictureRepository.save(new ArticlePicture(
                                (CLOUDINARY_URL + "t_check/v1719752232/pexels-olly-3831136_d6l3iv.webp"), ads.get(33)));

                this.articlePictureRepository.save(new ArticlePicture(
                                (CLOUDINARY_URL + "t_check/v1719752231/pexels-greta-hoffman-7728016_ttqvuh.webp"),
                                ads.get(34)));
                this.articlePictureRepository.save(new ArticlePicture(
                                (CLOUDINARY_URL + "t_check/v1719752229/pexels-126286771-10012400_vubcmb.webp"),
                                ads.get(34)));

                this.articlePictureRepository.save(new ArticlePicture(
                                (CLOUDINARY_URL + "t_check/v1719752229/pexels-cottonbro-6322737_scgr7w.webp"),
                                ads.get(35)));
                this.articlePictureRepository.save(new ArticlePicture(
                                (CLOUDINARY_URL + "t_check/v1719752229/pexels-cottonbro-6332446_xtmjsu.webp"),
                                ads.get(35)));
                this.articlePictureRepository.save(new ArticlePicture(
                                (CLOUDINARY_URL + "t_check/v1719752229/pexels-cottonbro-6322734_hlsprm.webp"),
                                ads.get(35)));

                this.articlePictureRepository.save(new ArticlePicture(
                                (CLOUDINARY_URL + "t_check/v1719752232/pexels-victorfreitas-973505_rirom2.webp"),
                                ads.get(36)));
                this.articlePictureRepository.save(new ArticlePicture((CLOUDINARY_URL
                                + "t_check/v1719752231/pexels-nikita-khandelwal-178978-788855_o4ygre.webp"),
                                ads.get(36)));

                this.articlePictureRepository.save(new ArticlePicture((CLOUDINARY_URL
                                + "t_check/v1719752230/pexels-enes-akyol-470444929-16824634_lb4zkp.webp"),
                                ads.get(37)));
                this.articlePictureRepository.save(new ArticlePicture((CLOUDINARY_URL
                                + "t_check/v1719752230/pexels-enes-akyol-470444929-16824629_zerzsh.webp"),
                                ads.get(37)));

                this.articlePictureRepository.save(new ArticlePicture(
                                (CLOUDINARY_URL + "t_check/v1719749204/pexels-simon-5700767_ag1mbm.webp"), ads.get(38)));
                this.articlePictureRepository.save(new ArticlePicture(
                                (CLOUDINARY_URL + "t_check/v1719749202/pexels-karolina-grabowska-4219892_ny8g08.webp"),
                                ads.get(38)));
                this.articlePictureRepository.save(new ArticlePicture(
                                (CLOUDINARY_URL + "t_check/v1719749203/pexels-karolina-grabowska-4219883_fqibfi.webp"),
                                ads.get(38)));

                this.articlePictureRepository.save(new ArticlePicture(
                                (CLOUDINARY_URL + "t_check/v1719749202/pexels-ekrulila-16733153_xcuntu.webp"),
                                ads.get(39)));
                this.articlePictureRepository.save(new ArticlePicture((CLOUDINARY_URL
                                + "t_check/v1719749201/pexels-anna-sorohan_ph-950571252-20156590_zwt9us.webp"),
                                ads.get(39)));

                this.articlePictureRepository.save(new ArticlePicture((CLOUDINARY_URL
                                + "t_finalADeuxPasTransform/v1719749203/pexels-laurachouette-21263484_rgkm9b.webp"),
                                ads.get(40)));
                this.articlePictureRepository.save(new ArticlePicture(
                                (CLOUDINARY_URL + "t_check/v1719749203/pexels-laurachouette-21263486_rlkptq.webp"),
                                ads.get(40)));

                this.articlePictureRepository.save(new ArticlePicture(
                                (CLOUDINARY_URL + "t_check/v1719749201/pexels-atccommphoto-1903308_mcxixo.webp"),
                                ads.get(41)));
                this.articlePictureRepository.save(new ArticlePicture(
                                (CLOUDINARY_URL + "t_check/v1719749203/pexels-jibarofoto-1787220_pmlusl.webp"),
                                ads.get(41)));
                this.articlePictureRepository.save(new ArticlePicture(
                                (CLOUDINARY_URL + "t_check/v1719749204/pexels-sameerkalani-3802602_bqjvwp.webp"),
                                ads.get(41)));

                this.articlePictureRepository.save(new ArticlePicture(
                                (CLOUDINARY_URL + "t_check/v1719749203/pexels-magic-k-24827758-6728919_lcyfci.webp"),
                                ads.get(42)));
                this.articlePictureRepository.save(new ArticlePicture(
                                (CLOUDINARY_URL + "t_check/v1719749203/pexels-fotios-photos-1453499_ogsteq.webp"),
                                ads.get(42)));
                this.articlePictureRepository.save(new ArticlePicture(
                                (CLOUDINARY_URL + "t_check/v1719749202/pexels-dziana-hasanbekava-5480856_mzjoqf.webp"),
                                ads.get(42)));

                this.articlePictureRepository.save(new ArticlePicture((CLOUDINARY_URL
                                + "t_check/v1719770805/pexels-duren-williams-29414623-21633696_desvyb.webp"),
                                ads.get(43)));
                this.articlePictureRepository.save(new ArticlePicture((CLOUDINARY_URL
                                + "t_check/v1719770802/pexels-duren-williams-29414623-21633686_qwdgej.webp"),
                                ads.get(43)));
                this.articlePictureRepository.save(new ArticlePicture((CLOUDINARY_URL
                                + "t_finalADeuxPasTransform/v1719770812/pexels-duren-williams-29414623-21637943_x0yeqi.webp"),
                                ads.get(43)));
                this.articlePictureRepository.save(new ArticlePicture((CLOUDINARY_URL
                                + "t_check/v1719770809/pexels-duren-williams-29414623-21637942_hnlnbb.webp"),
                                ads.get(43)));

                this.articlePictureRepository.save(new ArticlePicture(
                                (CLOUDINARY_URL + "t_check/v1719770922/pexels-emilianovittoriosi-21381889_pgwzln.webp"),
                                ads.get(44)));
                this.articlePictureRepository.save(new ArticlePicture((CLOUDINARY_URL
                                + "t_finalADeuxPasTransform/v1719770918/pexels-emilianovittoriosi-21381887_vkdkdt.webp"),
                                ads.get(44)));

                this.articlePictureRepository.save(new ArticlePicture(
                                (CLOUDINARY_URL + "t_check/v1719770799/pexels-chevanon-324028_vjixle.webp"),
                                ads.get(45)));
                this.articlePictureRepository.save(new ArticlePicture((CLOUDINARY_URL
                                + "t_check/v1719770927/pexels-marta-dzedyshko-1042863-2067628_wmdz5q.webp"),
                                ads.get(45)));
                this.articlePictureRepository.save(new ArticlePicture((CLOUDINARY_URL
                                + "t_finalADeuxPasTransform/v1719770969/pexels-viktoria-alipatova-1083711-2668498_wahdef.webp"),
                                ads.get(45)));

                this.articlePictureRepository.save(new ArticlePicture(
                                (CLOUDINARY_URL + "t_check/v1719770959/pexels-reyna-3007761_norpvc.webp"), ads.get(46)));
                this.articlePictureRepository.save(new ArticlePicture((CLOUDINARY_URL
                                + "t_finalADeuxPasTransform/v1719770948/pexels-reyna-3007760_uce4ne.webp"),
                                ads.get(46)));
                this.articlePictureRepository.save(new ArticlePicture(
                                (CLOUDINARY_URL + "t_check/v1719770939/pexels-reyna-3007759_z3dajw.webp"), ads.get(46)));

                this.articlePictureRepository.save(new ArticlePicture(
                                (CLOUDINARY_URL + "t_check/v1719770910/pexels-elly-fairytale-3822864_ujsoo6.webp"),
                                ads.get(47)));
                this.articlePictureRepository.save(new ArticlePicture((CLOUDINARY_URL
                                + "t_finalADeuxPasTransform/v1719770909/pexels-elly-fairytale-3822906_tpzxb8.webp"),
                                ads.get(47)));
        }
}
