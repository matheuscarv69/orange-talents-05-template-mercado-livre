package mercadoLivre.entities.user.auth;

public class AuthTokenDto {

    private final String token;
    private final String type;

    public AuthTokenDto(String token, String type) {
        this.token = token;
        this.type = type;
    }

    public String getToken() {
        return token;
    }

    public String getType() {
        return type;
    }
}
