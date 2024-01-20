package org.project.services.classes;

import lombok.AllArgsConstructor;
import org.project.contracts.AlbumDto;
import org.project.contracts.GenreDto;
import org.project.creators.GenreCreator;
import org.project.model.Genre;
import org.project.repositories.RepositoriesCatalog;
import org.project.services.interfaces.IGenreService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class GenreService implements IGenreService {
    private final RepositoriesCatalog database;
    @Override
    public Long createGenre(GenreCreator genreCreator) {
        var newGenre = new Genre();
        newGenre.setName(genreCreator.getName());

        var savedGenre = database.getGenres().save(newGenre);
        return savedGenre.getId();
    }
    @Override
    public List<GenreDto> getAll() {
        return database.getGenres().findAll()
                .stream().map(GenreService::mapFromGenre)
                .toList();
    }
    @Override
    public GenreDto getById(Long id) {
        var genre = database.getGenres().findById(id).orElse(null);
        if(genre==null) return null;
        return mapFromGenre(genre);
    }
    @Override
    public Long save(GenreDto genreDto) {
        Genre genre = getGenreFromDto(genreDto);
        var savedGenre = database.getGenres().save(genre);
        return savedGenre.getId();
    }
    @Override
    public GenreDto delete(Long id) {
        var genre = database.getGenres().findById(id).orElse(null);
        if(genre==null) return null;
        database.getGenres().delete(genre);
        return mapFromGenre(genre);
    }
    @Override
    public GenreDto update(Long id, GenreDto genreDto) {
        var genre = database.getGenres().findById(id).orElse(null);
        if(genre==null) return null;
        database.getGenres().save(getGenreFromDto(genreDto, genre));
        return genreDto;
    }
    @Override
    public List<AlbumDto> getAlbums(Long genreId) {
        ArrayList<AlbumDto> albumDtos = new ArrayList<>();
        var genre = database.getGenres().findById(genreId).orElse(null);
        if(genre==null) return null;
        var albums = database.getAlbums().findByGenreId(genreId);
        if(albums.isEmpty()) return null;
        for(var album : albums){
            albumDtos.add(
                    new AlbumDto().setId(album.getId())
                            .setName(album.getName())
                            .setYear(album.getYear())
            );
        }
        return albumDtos;
    }
    private static Genre getGenreFromDto(GenreDto genreDto){ return getGenreFromDto(genreDto, new Genre()); }
    private static Genre getGenreFromDto(GenreDto genreDto, Genre genre){
        genre.setName(genreDto.getName());
        return genre;
    }
    private static GenreDto mapFromGenre(Genre genre){
        return new GenreDto()
                .setId(genre.getId())
                .setName(genre.getName());
    }
}
