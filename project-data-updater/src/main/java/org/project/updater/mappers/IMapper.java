package org.project.updater.mappers;

import org.project.contracts.AlbumDto;
import org.project.contracts.ArtistDto;
import org.project.contracts.GenreDto;
import org.project.contracts.TrackDto;
import org.project.model.Album;
import org.project.model.Artist;
import org.project.model.Genre;
import org.project.model.Track;

public interface IMapper {
    IMap<AlbumDto, Album> album();
    IMap<ArtistDto, Artist> artist();
    IMap<GenreDto, Genre> genre();
    IMap<TrackDto, Track> track();
}
