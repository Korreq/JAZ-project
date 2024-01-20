package org.project.services.classes;

import lombok.AllArgsConstructor;
import org.project.contracts.AlbumDto;
import org.project.contracts.TrackDto;
import org.project.creators.TrackCreator;
import org.project.model.Track;
import org.project.repositories.RepositoriesCatalog;
import org.project.services.interfaces.ITrackService;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class TrackService implements ITrackService {
    private final RepositoriesCatalog database;
    @Override
    public Long createTrack(TrackCreator trackCreator) {
        var newTrack = new Track();
        newTrack.setName(trackCreator.getName());
        newTrack.setNumber(trackCreator.getNumber());
        newTrack.setDuration(trackCreator.getDuration());

        var album = database.getAlbums().findById(trackCreator.getAlbumId()).orElse(null);
        newTrack.setAlbum(album);

        var savedTrack = database.getTracks().save(newTrack);
        return savedTrack.getId();
    }
    @Override
    public List<TrackDto> getByPage(int size, int page) {
        return database.getTracks().findAll(PageRequest.of(page, size))
                .stream().map(TrackService::mapFromTrack)
                .toList();
    }
    @Override
    public TrackDto getById(Long id) {
        var track = database.getTracks().findById(id).orElse(null);
        if(track==null) return null;
        return mapFromTrack(track);
    }
    @Override
    public Long save(TrackDto trackDto) {
        Track track = getTrackFromDto(trackDto);
        var savedTrack = database.getTracks().save(track);
        return savedTrack.getId();
    }
    @Override
    public TrackDto delete(Long id) {
        var track = database.getTracks().findById(id).orElse(null);
        if(track==null) return null;
        database.getTracks().delete(track);
        return mapFromTrack(track);
    }
    @Override
    public TrackDto update(Long id, TrackDto trackDto) {
        var track = database.getTracks().findById(id).orElse(null);
        if(track==null) return null;
        database.getTracks().save(getTrackFromDto(trackDto, track));
        return trackDto;
    }
    @Override
    public AlbumDto getAlbum(Long trackId) {
        var track = database.getTracks().findById(trackId).orElse(null);
        if(track==null) return null;
        var album = track.getAlbum();
        if(album==null) return null;
        return new AlbumDto().setId(album.getId())
                .setName(album.getName())
                .setYear(album.getYear());
    }
    @Override
    public List<TrackDto> getByAlbumName(String name) {
        return database.getTracks().findByAlbumName(name).stream()
                .map(TrackService::mapFromTrack)
                .toList();
    }
    @Override
    public TrackDto getByNameAndNumber(String name, Integer number) {
        var track = database.getTracks().findFirstByNameAndNumber(name, number).orElse(null);
        if(track==null) return null;
        return mapFromTrack(track);
    }
    @Override
    public List<TrackDto> getByDurationLongerThan(Float duration) {
        return database.getTracks().findByDurationLongerThan(duration).stream()
                .map(TrackService::mapFromTrack)
                .toList();
    }
    @Override
    public List<TrackDto> getByDurationShorterThan(Float duration) {
        return database.getTracks().findByDurationShorterThan(duration).stream()
                .map(TrackService::mapFromTrack)
                .toList();
    }
    private static Track getTrackFromDto(TrackDto trackDto){ return getTrackFromDto(trackDto, new Track()); }
    private static Track getTrackFromDto(TrackDto trackDto, Track track){
        track.setName(trackDto.getName());
        track.setNumber(trackDto.getNumber());
        track.setDuration(trackDto.getDuration());
        return track;
    }
    private static TrackDto mapFromTrack(Track track){
        return new TrackDto()
                .setId(track.getId())
                .setName(track.getName())
                .setNumber(track.getNumber())
                .setDuration(track.getDuration());
    }
}