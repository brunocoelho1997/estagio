package hello.Post;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity(name = "post_contact")
public class PostContact extends hello.Entity{

    public static final int MAX_NAME_LENGHT = 30;

    @NotNull
    @Size(min=1, max = MAX_NAME_LENGHT)
    @Column(nullable = false, length = MAX_NAME_LENGHT)
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}