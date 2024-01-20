package org.project.repositories;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
@Getter
public class RepositoriesCatalog implements IRepositoriesCatalog{
    private final ArtistRepository artists;
    private final AlbumRepository albums;
    private final GenreRepository genres;
    private final TrackRepository tracks;
}
