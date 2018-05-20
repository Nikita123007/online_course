package hibernate;

import javax.persistence.*;

@Entity
@Table(name = "video_link", schema = "online_course", catalog = "")
public class VideoLinkEntity {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id_video_link")
    private int id;

    @Basic
    @Column(name = "link")
    private String link;

    @ManyToOne
    @JoinColumn(name = "lection")
    private LectionEntity lection;

    public int getId() {
        return id;
    }
    public void setId(int idVideoLink) {
        this.id = idVideoLink;
    }

    public String getLink() {
        return link;
    }
    public void setLink(String link) {
        this.link = link;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        VideoLinkEntity that = (VideoLinkEntity) o;

        if (id != that.id) return false;
        if (link != null ? !link.equals(that.link) : that.link != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (link != null ? link.hashCode() : 0);
        return result;
    }

    public LectionEntity getLection() {
        return lection;
    }
    public void setLection(LectionEntity lection) {
        if(! lection.equals(this.lection)){
            this.lection = lection;
            lection.addVideoLink(this);
        }
    }
}
