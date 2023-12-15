package hellojpa;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("A") // 기본값 = entity(Album) 이름
public class Album extends Item{
    private String artist;

}
