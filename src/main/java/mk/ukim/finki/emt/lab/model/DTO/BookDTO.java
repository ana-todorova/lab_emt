package mk.ukim.finki.emt.lab.model.DTO;

import lombok.Data;
import mk.ukim.finki.emt.lab.model.enumerations.Category;

@Data
public class BookDTO {
    private String name;
    private Category category;
    private Long authorId;
    private Integer availableCopies;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Long authorId) {
        this.authorId = authorId;
    }

    public Integer getAvailableCopies() {
        return availableCopies;
    }

    public void setAvailableCopies(Integer availableCopies) {
        this.availableCopies = availableCopies;
    }
}
