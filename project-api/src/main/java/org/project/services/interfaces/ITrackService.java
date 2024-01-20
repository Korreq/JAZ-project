package org.project.services.interfaces;

import jakarta.transaction.Transactional;
import org.project.contracts.AlbumDto;
import org.project.contracts.TrackDto;
import org.project.creators.TrackCreator;

import java.util.List;

public interface ITrackService {
    @Transactional
    Long createTrack(TrackCreator trackCreator);
    List<TrackDto> getByPage(int size, int page);
    TrackDto getById(Long id);
    Long save(TrackDto trackDto);
    TrackDto delete(Long id);
    TrackDto update(Long id, TrackDto trackDto);
    AlbumDto getAlbum(Long trackId);
    List<TrackDto> getByAlbumName(String name);
    TrackDto getByNameAndNumber(String name, Integer number);
    List<TrackDto> getByDurationLongerThan(Float duration);
    List<TrackDto> getByDurationShorterThan(Float duration);
}
