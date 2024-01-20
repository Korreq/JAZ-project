package org.project.repositories;

public interface IRepositoriesCatalog {
    ArtistRepository getArtists();
    AlbumRepository getAlbums();
    GenreRepository getGenres();
    TrackRepository getTracks();
}
