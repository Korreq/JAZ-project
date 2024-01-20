package org.project.repositories;

import org.project.model.Album;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AlbumRepository extends JpaRepository<Album, Long> {
    List<Album> findByArtistName(String name);
    List<Album> findByArtistId(Long id);
    List<Album> findByGenreId(Long id);
    List<Album> findByGenreNameAndYear(String name, String year);
}
