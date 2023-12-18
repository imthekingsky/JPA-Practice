package hellojpa;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("B") // 기본값 = entity(Book) 이름
public class Book extends Item{
    private String author;
    private String isbn;

}
