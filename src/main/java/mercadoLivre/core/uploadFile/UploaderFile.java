package mercadoLivre.core.uploadFile;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.util.Set;
import java.util.stream.Collectors;

@Primary
@Component
public class UploaderFile implements UploaderInterface {

    @Override
    public Set<String> uploadImages(Set<MultipartFile> newImagesProductForm) {
        return newImagesProductForm
                .stream()
                .map(image ->
                        "https://aws.amazon.com/s3/"
                                + image.getOriginalFilename()
                ).collect(Collectors.toSet());
    }


}
