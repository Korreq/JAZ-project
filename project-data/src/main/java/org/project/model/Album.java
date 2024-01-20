package org.project.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
public class Album {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    private Artist artist;
    @OneToMany(mappedBy = "album")
    private List<Track> tracks;
    @ManyToOne
    private Genre genre;

    private String name, year;
}
