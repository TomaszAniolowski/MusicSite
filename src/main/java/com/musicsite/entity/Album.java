package com.musicsite.entity;

import com.musicsite.validation.AlbumValidationGroup;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.validation.groups.Default;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "albums")
public class Album extends Opus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(groups = {AlbumValidationGroup.class, Default.class})
    @Size(min = 2, max = 50, groups = {AlbumValidationGroup.class, Default.class})
    private String name;

    @ManyToOne
    @NotNull(groups = AlbumValidationGroup.class)
    private Performer performer;

    @OneToMany(mappedBy = "album", fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    private List<Track> tracks = new ArrayList<>();

    @Pattern(regexp = "\\d{4}", groups = {AlbumValidationGroup.class, Default.class})
    @Column(name = "year_of_publication")
    private String yearOfPublication;

    @OneToMany(mappedBy = "album", fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    private List<Rating> ratings = new ArrayList<>();

    @Column(precision = 3, scale = 2)
    private Double average;

    @Column(columnDefinition = "BIT")
    private boolean proposition;

    @ManyToMany(fetch = FetchType.EAGER)
    @NotEmpty
    private List<Category> categories = new ArrayList<>();


    public Album() {
        proposition = true;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getYearOfPublication() {
        return yearOfPublication;
    }

    @Override
    public void setYearOfPublication(String yearOfPublication) {
        this.yearOfPublication = yearOfPublication;
    }

    public Performer getPerformer() {
        return performer;
    }

    public void setPerformer(Performer performer) {
        this.performer = performer;
    }

    public List<Track> getTracks() {
        return tracks;
    }

    public void setTracks(List<Track> tracks) {
        this.tracks = tracks;
    }

    public Double getAverage() {
        return average;
    }

    public void setAverage(Double average) {
        this.average = average;
    }

    public List<Rating> getRatings() {
        return ratings;
    }

    public void setRatings(List<Rating> ratings) {
        this.ratings = ratings;
    }

    public boolean isProposition() {
        return proposition;
    }

    public void setProposition(boolean proposition) {
        this.proposition = proposition;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    @PrePersist
    public void prePer() {
        average = 0.0;
        name = name.trim();
    }

    @PreUpdate
    public void preUp() {
        double sum = 0.0;
        for (Rating rating : ratings)
            sum += rating.getRating();


        average = sum / ratings.size();
        name = name.trim();
    }

    @Override
    public String toString() {
        return performer.getPseudonym() + " - " + yearOfPublication + " - " + name;
    }
}
