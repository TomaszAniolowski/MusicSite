package com.musicsite.rating;

import com.musicsite.album.Album;
import com.musicsite.entity.Ens;
import com.musicsite.performer.Performer;
import com.musicsite.track.Track;
import com.musicsite.user.User;

import javax.persistence.*;

@Entity
@Table(name = "ratings")
public class Rating extends Ens {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User user;

    private int rating;

    @ManyToOne(cascade = CascadeType.REMOVE)
    private Performer performer;

    @ManyToOne(cascade = CascadeType.REMOVE)
    private Album album;

    @ManyToOne(cascade = CascadeType.REMOVE)
    private Track track;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public Performer getPerformer() {
        return performer;
    }

    public void setPerformer(Performer performer) {
        this.performer = performer;
    }

    public Album getAlbum() {
        return album;
    }

    public void setAlbum(Album album) {
        this.album = album;
    }

    public Track getTrack() {
        return track;
    }

    public void setTrack(Track track) {
        this.track = track;
    }

    @PrePersist
    public void prePer() {

    }

    @PreUpdate
    public void preUp() {

    }

    @Override
    public String toString() {
        return "(rating: " + rating + ")";
    }
}
