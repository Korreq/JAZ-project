package org.project.services.interfaces;

import jakarta.transaction.Transactional;
import org.project.contracts.AlbumDto;
import org.project.contracts.GenreDto;
import org.project.creators.GenreCreator;

import java.util.List;

public interface IGenreService {
    @Transactional
    Long createGenre(GenreCreator genreCreator);
    List<GenreDto> getAll();
    GenreDto getById(Long id);
    Long save(GenreDto genreDto);
    GenreDto delete(Long id);
    GenreDto update(Long id, GenreDto genreDto);
    List<AlbumDto> getAlbums(Long genreId);
}
