package org.project.updater.mappers;

import org.project.contracts.GenreDto;
import org.project.model.Genre;
import org.springframework.stereotype.Component;

@Component
public class GenreMapper implements IMap<GenreDto, Genre>{
    @Override
    public Genre toEntity(GenreDto genreDto) {
        var genre = new Genre();
        genre.setName(genreDto.getName());
        return genre;
    }
}
