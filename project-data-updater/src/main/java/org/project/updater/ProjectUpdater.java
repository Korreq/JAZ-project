package org.project.updater;

import lombok.AllArgsConstructor;
import org.project.contracts.AlbumDto;
import org.project.contracts.TrackDto;
import org.project.model.Album;
import org.project.model.Track;
import org.project.repositories.IRepositoriesCatalog;
import org.project.updater.mappers.IMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Service
public class ProjectUpdater{
    final IMapper mapper;
    final IRepositoriesCatalog database;
    void saveAlbum(AlbumDto albumDto){
        var album = mapper.album().toEntity(albumDto);
        var artist = mapper.artist().toEntity(albumDto.getArtist());
        var genre = mapper.genre().toEntity(albumDto.getGenre());
        var tracks = albumDto.getTracks().stream().map(x->mapper.track().toEntity(x)).toList();
        for(var track : tracks){
            track.setAlbum(album);
            database.getTracks().save(track);
        }
        album.setTracks(tracks);
        album.setArtist(artist);
        album.setGenre(genre);
        genre.getAlbums().add(album);
        artist.getAlbums().add(album);
        database.getAlbums().save(album);
        database.getGenres().save(genre);
        database.getArtists().save(artist);
    }
}
