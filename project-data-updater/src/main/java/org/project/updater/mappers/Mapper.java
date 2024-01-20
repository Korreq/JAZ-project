package org.project.updater.mappers;

import lombok.AllArgsConstructor;
import org.project.contracts.AlbumDto;
import org.project.contracts.ArtistDto;
import org.project.contracts.GenreDto;
import org.project.contracts.TrackDto;
import org.project.model.Album;
import org.project.model.Artist;
import org.project.model.Genre;
import org.project.model.Track;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class Mapper implements IMapper{
    private final IMap<AlbumDto, Album> album;
    private final IMap<ArtistDto, Artist> artist;
    private final IMap<GenreDto, Genre> genre;
    private final IMap<TrackDto, Track> track;
    @Override
    public IMap<AlbumDto, Album> album() { return album; }
    @Override
    public IMap<ArtistDto, Artist> artist() { return artist; }
    @Override
    public IMap<GenreDto, Genre> genre() { return genre; }
    @Override
    public IMap<TrackDto, Track> track() { return track; }
}
