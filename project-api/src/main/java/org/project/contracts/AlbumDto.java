package org.project.contracts;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.List;

@Getter
@Setter
@Accessors(chain = true)
public class AlbumDto {
    private Long id;
    private String name;
    private String year;
    private ArtistDto artist;
    private List<TrackDto> tracks;
    private GenreDto genre;
}
