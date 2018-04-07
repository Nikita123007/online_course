package hibernate;

import javax.persistence.*;

@Entity
@Table(name = "video_link", schema = "online_course", catalog = "")
public class VideoLinkEntity {
    private int idVideoLink;
    private int lection;
    private String link;
    private LectionEntity lectionByLection;

    @Id
    @Column(name = "id_video_link")
    public int getIdVideoLink() {
        return idVideoLink;
    }

    public void setIdVideoLink(int idVideoLink) {
        this.idVideoLink = idVideoLink;
    }

    @Basic
    @Column(name = "lection")
    public int getLection() {
        return lection;
    }

    public void setLection(int lection) {
        this.lection = lection;
    }

    @Basic
    @Column(name = "link")
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

        if (idVideoLink != that.idVideoLink) return false;
        if (lection != that.lection) return false;
        if (link != null ? !link.equals(that.link) : that.link != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idVideoLink;
        result = 31 * result + lection;
        result = 31 * result + (link != null ? link.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "lection", referencedColumnName = "id_lection", nullable = false, insertable = false, updatable = false)
    public LectionEntity getLectionByLection() {
        return lectionByLection;
    }

    public void setLectionByLection(LectionEntity lectionByLection) {
        this.lectionByLection = lectionByLection;
    }
}
