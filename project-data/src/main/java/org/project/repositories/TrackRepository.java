package org.project.repositories;

import org.project.model.Track;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface TrackRepository extends JpaRepository<Track, Long> {
    Optional<Track> findFirstByNameAndNumber(String name, Integer number);
    List<Track> findByAlbumId(Long id);
    @Query(value = "select x from Track x where x.album.name = :name")
    List<Track> findByAlbumName(String name);
    @Query(value = "select x from Track x where x.duration > :duration")
    List<Track> findByDurationLongerThan(Float duration);
    @Query(value = "select x from Track x where x.duration < :duration")
    List<Track> findByDurationShorterThan(Float duration);
}
