package org.project.repositories;

import jakarta.persistence.criteria.CriteriaBuilder;
import org.project.model.Artist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ArtistRepository extends JpaRepository<Artist, Long> {
    @Query(value = "select x from Artist x where x.startYear >= :year")
    List<Artist> findByStartYearAfter(Integer year);
    @Query(value = "select x from Artist x where x.startYear <= :year")
    List<Artist> findByStartYearBefore(Integer year);
    @Query(value = "select x from Artist x where x.endYear >= :year")
    List<Artist> findByEndYearAfter(Integer year);
    @Query(value = "select x from Artist x where x.endYear <= :year")
    List<Artist> findByEndYearBefore(Integer year);
    Optional<Artist> findFirstByNameAndAge(String name, Integer age);
}
