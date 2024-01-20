package org.project.updater.mappers;

import org.project.contracts.ArtistDto;
import org.project.model.Artist;
import org.springframework.stereotype.Component;

@Component
public class ArtistMapper implements IMap<ArtistDto, Artist>{
    @Override
    public Artist toEntity(ArtistDto artistDto) {
        var artist = new Artist();
        artist.setName(artistDto.getName());
        artist.setStartYear(artistDto.getStartYear());
        artist.setEndYear(artistDto.getEndYear());
        artist.setAge(artistDto.getAge());
        return artist;
    }
}
