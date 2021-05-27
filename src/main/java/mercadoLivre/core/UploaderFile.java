package mercadoLivre.core;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.util.Set;
import java.util.stream.Collectors;

@Component
@Primary
public class UploaderFile implements Uploader {

    @Override
    public Set<String> uploadImages(Set<MultipartFile> newImagesProductForm) {
        System.out.println("DEv");
        return newImagesProductForm
                .stream()
                .map(image ->
                        "https://aws.amazon.com/s3/"
                                + image.getOriginalFilename()
                ).collect(Collectors.toSet());
    }


}
