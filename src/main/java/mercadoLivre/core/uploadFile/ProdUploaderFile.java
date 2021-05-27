package mercadoLivre.core.uploadFile;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.util.Set;
import java.util.stream.Collectors;

@Component
public class ProdUploaderFile implements UploaderInterface {

    @Override
    public Set<String> uploadImages(Set<MultipartFile> newImagesProductForm) {
        return newImagesProductForm
                .stream()
                .map(image ->
                        "https://prod.amazon.com/s3/"
                                + image.getOriginalFilename()
                ).collect(Collectors.toSet());
    }


}
