package org.project.services.interfaces;

import jakarta.transaction.Transactional;
import org.project.contracts.AlbumDto;
import org.project.contracts.ArtistDto;
import org.project.contracts.GenreDto;
import org.project.contracts.TrackDto;
import org.project.creators.AlbumCreator;

import java.util.List;

public interface IAlbumService {
    @Transactional
    Long createAlbum(AlbumCreator albumCreator);
    List<AlbumDto> getByPage(int size, int page);
    AlbumDto getById(Long id);
    Long save(AlbumDto albumDto);
    AlbumDto delete(Long id);
    AlbumDto update(Long id, AlbumDto albumDto);
    ArtistDto getArtist(Long albumId);
    GenreDto getGenre(Long albumId);
    List<TrackDto> getTracks(Long albumId);
    List<AlbumDto> getByArtistName(String name);
    List<AlbumDto> findByGenreNameAndYear(String name, String year);
}
