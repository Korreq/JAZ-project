package org.project.services.classes;

import lombok.RequiredArgsConstructor;
import org.project.contracts.AlbumDto;
import org.project.contracts.ArtistDto;
import org.project.contracts.GenreDto;
import org.project.contracts.TrackDto;
import org.project.creators.AlbumCreator;
import org.project.model.Album;
import org.project.repositories.RepositoriesCatalog;
import org.project.services.interfaces.IAlbumService;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AlbumService implements IAlbumService {
    private final RepositoriesCatalog database;
    @Override
    public Long createAlbum(AlbumCreator albumCreator) {
        var newAlbum = new Album();
        newAlbum.setName(albumCreator.getName());
        newAlbum.setYear(albumCreator.getYear());

        var artist = database.getArtists().findById(albumCreator.getArtistId()).orElse(null);
        newAlbum.setArtist(artist);
        var genre = database.getGenres().findById(albumCreator.getGenreId()).orElse(null);
        newAlbum.setGenre(genre);

        var savedAlbum = database.getAlbums().save(newAlbum);
        return savedAlbum.getId();
    }
    @Override
    public List<AlbumDto> getByPage(int size, int page) {
        return database.getAlbums().findAll(PageRequest.of(page, size))
                .stream().map(AlbumService::mapFromAlbum)
                .toList();
    }
    @Override
    public AlbumDto getById(Long id) {
        var album = database.getAlbums().findById(id).orElse(null);
        if(album==null) return null;
        return mapFromAlbum(album);
    }
    @Override
    public Long save(AlbumDto albumDto) {
        Album album = getAlbumFromDto(albumDto);
        var savedAlbum = database.getAlbums().save(album);
        return savedAlbum.getId();
    }
    @Override
    public AlbumDto delete(Long id) {
        var album = database.getAlbums().findById(id).orElse(null);
        if(album==null) return null;
        database.getAlbums().delete(album);
        return mapFromAlbum(album);
    }
    @Override
    public AlbumDto update(Long id, AlbumDto albumDto) {
        var album = database.getAlbums().findById(id).orElse(null);
        if(album==null) return null;
        database.getAlbums().save(getAlbumFromDto(albumDto, album));
        return albumDto;
    }
    @Override
    public ArtistDto getArtist(Long albumId) {
        var album = database.getAlbums().findById(albumId).orElse(null);
        if(album==null) return null;
        var artist = album.getArtist();
        if(artist==null) return null;
        return new ArtistDto().setId(artist.getId())
                .setName(artist.getName())
                .setStartYear(artist.getStartYear())
                .setEndYear(artist.getEndYear())
                .setAge(artist.getAge());
    }
    @Override
    public GenreDto getGenre(Long albumId) {
        var album = database.getAlbums().findById(albumId).orElse(null);
        if(album==null) return null;
        var genre = album.getGenre();
        if(genre==null) return null;
        return new GenreDto().setId(genre.getId())
                .setName(genre.getName());
    }
    @Override
    public List<TrackDto> getTracks(Long albumId) {
        ArrayList<TrackDto> trackDtos = new ArrayList<>();
        var album = database.getAlbums().findById(albumId).orElse(null);
        if(album==null) return null;
        var tracks = database.getTracks().findByAlbumId(albumId);
        if(tracks.isEmpty()) return null;
        for(var track : tracks){
            trackDtos.add(
                    new TrackDto().setId(track.getId())
                            .setName(track.getName())
                            .setNumber(track.getNumber())
                            .setDuration(track.getDuration())
            );
        }
        return trackDtos;
    }
    @Override
    public List<AlbumDto> getByArtistName(String name) {
        return database.getAlbums().findByArtistName(name).stream().map(AlbumService::mapFromAlbum).toList();
    }
    @Override
    public List<AlbumDto> findByGenreNameAndYear(String name, String year) {
        return database.getAlbums().findByGenreNameAndYear(name, year).stream().map(AlbumService::mapFromAlbum).toList();
    }
    private static Album getAlbumFromDto(AlbumDto albumDto){
        return getAlbumFromDto(albumDto, new Album());
    }
    private static Album getAlbumFromDto(AlbumDto albumDto, Album album){
        album.setName(albumDto.getName());
        album.setYear(albumDto.getYear());
        return album;
    }
    private static AlbumDto mapFromAlbum(Album album){
        return new AlbumDto()
                .setId(album.getId())
                .setName(album.getName())
                .setYear(album.getYear());
    }


}
