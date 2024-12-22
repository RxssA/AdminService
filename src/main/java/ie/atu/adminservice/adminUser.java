package ie.atu.adminservice;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "users")
public class adminUser {
    @Id
    private String id;
    @NotBlank(message = "Name is required")
    private String username;
    @Email(message = "Email should be valid")
    private String email;
    @NotBlank(message = "Role is required")
    private String role; //user or admin

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public @NotBlank(message = "Name is required") String getUsername() {
        return username;
    }

    public void setUsername(@NotBlank(message = "Name is required") String username) {
        this.username = username;
    }

    public @Email(message = "Email should be valid") String getEmail() {
        return email;
    }

    public void setEmail(@Email(message = "Email should be valid") String email) {
        this.email = email;
    }

    public @NotBlank(message = "Role is required") String getRole() {
        return role;
    }

    public void setRole(@NotBlank(message = "Role is required") String role) {
        this.role = role;
    }
}
