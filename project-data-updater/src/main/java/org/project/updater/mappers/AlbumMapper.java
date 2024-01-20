package org.project.updater.mappers;

import org.project.contracts.AlbumDto;
import org.project.model.Album;
import org.springframework.stereotype.Component;

@Component
public class AlbumMapper implements IMap<AlbumDto, Album>{
    @Override
    public Album toEntity(AlbumDto albumDto) {
        var album = new Album();
        album.setName(albumDto.getName());
        album.setYear(albumDto.getYear());
        return album;
    }
}
