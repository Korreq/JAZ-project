package org.project.updater.mappers;

import org.project.contracts.TrackDto;
import org.project.model.Track;
import org.springframework.stereotype.Component;

@Component
public class TrackMapper implements IMap<TrackDto, Track>{
    @Override
    public Track toEntity(TrackDto trackDto) {
        var track = new Track();
        track.setName(trackDto.getName());
        track.setNumber(trackDto.getNumber());
        track.setDuration(trackDto.getDuration());
        return track;
    }
}
