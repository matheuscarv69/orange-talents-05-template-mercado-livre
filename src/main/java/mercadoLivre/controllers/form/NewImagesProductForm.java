package mercadoLivre.controllers.form;

import com.fasterxml.jackson.annotation.JsonCreator;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

public class NewImagesProductForm {

    @Size(min = 1)
    @NotNull
    private Set<MultipartFile> images = new HashSet<>();

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public NewImagesProductForm(Set<MultipartFile> images) {
        this.images = images;
    }

    public Set<MultipartFile> getImages() {
        return images;
    }

}
