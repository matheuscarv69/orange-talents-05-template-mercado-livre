package mercadoLivre.core.uploadFile;

import org.springframework.web.multipart.MultipartFile;

import java.util.Set;

public interface UploaderInterface {

    Set<String> uploadImages(Set<MultipartFile> newImagesProductForm);

}
