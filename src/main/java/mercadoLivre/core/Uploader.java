package mercadoLivre.core;

import org.springframework.web.multipart.MultipartFile;

import java.util.Set;

public interface Uploader {

    Set<String> uploadImages(Set<MultipartFile> newImagesProductForm);

}
