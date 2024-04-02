// package adeuxpas.back.config;

// import com.cloudinary.Cloudinary;
// import com.cloudinary.utils.ObjectUtils;
// import org.springframework.beans.factory.annotation.Value;
// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;

// @Configuration
// public class CloudinaryConfig {

// @Value("${cloudinary.CLOUD_NAME}")
// private String cloudName;

// @Value("${cloudinary.API_KEY}")
// private String apiKey;

// @Value("${cloudinary.API_SECRET}")
// private String apiSecret;

// @Bean
// public Cloudinary cloudinary() {
// return new Cloudinary(ObjectUtils.asMap(
// "CLOUD_NAME", cloudName,
// "API_KEY", apiKey,
// "API_SECRET", apiSecret));
// }
// }
