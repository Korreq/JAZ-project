package org.project.services.interfaces;

import jakarta.transaction.Transactional;
import org.project.contracts.AlbumDto;
import org.project.contracts.ArtistDto;
import org.project.creators.ArtistCreator;

import java.util.List;

public interface IArtistService {
    @Transactional
    Long createArtist(ArtistCreator artistCreator);
    List<ArtistDto> getByPage(int size, int page);
    ArtistDto getById(Long id);
    Long save(ArtistDto artistDto);
    ArtistDto delete(Long id);
    ArtistDto update(Long id, ArtistDto artistDto);
    List<AlbumDto> getAlbums(Long artistId);
    ArtistDto getByNameAndAge(String name, Integer age);
    List<ArtistDto> getByStartYearAfter(Integer year);
    List<ArtistDto> getByStartYearBefore(Integer year);
    List<ArtistDto> getByEndYearAfter(Integer year);
    List<ArtistDto> getByEndYearBefore(Integer year);
}
