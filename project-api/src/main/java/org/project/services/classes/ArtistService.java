package org.project.services.classes;

import lombok.AllArgsConstructor;
import org.project.contracts.AlbumDto;
import org.project.contracts.ArtistDto;
import org.project.creators.ArtistCreator;
import org.project.model.Artist;
import org.project.repositories.RepositoriesCatalog;
import org.project.services.interfaces.IArtistService;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Service
@AllArgsConstructor
public class ArtistService implements IArtistService {
    private final RepositoriesCatalog database;
    @Override
    public Long createArtist(ArtistCreator artistCreator) {
        var newArtist = new Artist();
        newArtist.setName(artistCreator.getName());
        newArtist.setStartYear(artistCreator.getStartYear());
        newArtist.setEndYear(artistCreator.getEndYear());
        newArtist.setAge(getAge(artistCreator.getStartYear(), artistCreator.getEndYear()));

        var savedArtist = database.getArtists().save(newArtist);
        return savedArtist.getId();
    }
    private Integer getAge(String startYear, String endYear){
        int startNumber, endNumber;
        try{ startNumber = Integer.parseInt(startYear); }
        catch (RuntimeException e){ return null; }
        try{ endNumber = Integer.parseInt(endYear); }
        catch (RuntimeException e){ endNumber = Calendar.getInstance().get(Calendar.YEAR); }
        return endNumber - startNumber;
    }
    @Override
    public List<ArtistDto> getByPage(int size, int page) {
        return database.getArtists().findAll(PageRequest.of(page, size))
                .stream().map(ArtistService::mapFromArtist)
                .toList();
    }
    @Override
    public ArtistDto getById(Long id) {
        var artist = database.getArtists().findById(id).orElse(null);
        if(artist==null) return null;
        return mapFromArtist(artist);
    }
    @Override
    public Long save(ArtistDto artistDto) {
        Artist artist = getArtistFromDto(artistDto);
        var savedArtist = database.getArtists().save(artist);
        return savedArtist.getId();
    }
    @Override
    public ArtistDto delete(Long id) {
        var artist = database.getArtists().findById(id).orElse(null);
        if(artist==null) return null;
        database.getArtists().delete(artist);
        return mapFromArtist(artist);
    }
    @Override
    public ArtistDto update(Long id, ArtistDto artistDto) {
        var artist = database.getArtists().findById(id).orElse(null);
        if(artist==null) return null;
        database.getArtists().save(getArtistFromDto(artistDto, artist));
        return artistDto;
    }
    @Override
    public List<AlbumDto> getAlbums(Long artistId) {
        ArrayList<AlbumDto> albumDtos = new ArrayList<>();
        var artist = database.getArtists().findById(artistId).orElse(null);
        if(artist==null) return null;
        var albums = database.getAlbums().findByArtistId(artistId);
        if (albums.isEmpty()) return null;
        for(var album : albums){
            albumDtos.add(
                    new AlbumDto().setId(album.getId())
                            .setName(album.getName())
                            .setYear(album.getYear())
            );
        }
        return albumDtos;
    }
    @Override
    public ArtistDto getByNameAndAge(String name, Integer age) {
        var artist = database.getArtists().findFirstByNameAndAge(name, age).orElse(null);
        if(artist==null) return null;
        return mapFromArtist(artist);
    }
    @Override
    public List<ArtistDto> getByStartYearAfter(Integer year) {
        return database.getArtists().findByStartYearAfter(year).stream().map(ArtistService::mapFromArtist).toList();
    }
    @Override
    public List<ArtistDto> getByStartYearBefore(Integer year) {
        return database.getArtists().findByStartYearBefore(year).stream().map(ArtistService::mapFromArtist).toList();
    }
    @Override
    public List<ArtistDto> getByEndYearAfter(Integer year) {
        return database.getArtists().findByEndYearAfter(year).stream().map(ArtistService::mapFromArtist).toList();
    }
    @Override
    public List<ArtistDto> getByEndYearBefore(Integer year) {
        return database.getArtists().findByEndYearBefore(year).stream().map(ArtistService::mapFromArtist).toList();
    }
    private static Artist getArtistFromDto(ArtistDto artistDto){
        return getArtistFromDto(artistDto, new Artist());
    }
    private static Artist getArtistFromDto(ArtistDto artistDto, Artist artist){
        artist.setName(artistDto.getName());
        artist.setStartYear(artistDto.getStartYear());
        artist.setEndYear(artistDto.getEndYear());
        artist.setAge(artistDto.getAge());
        return artist;
    }
    private static ArtistDto mapFromArtist(Artist artist){
        return new ArtistDto()
                .setId(artist.getId())
                .setName(artist.getName())
                .setStartYear(artist.getStartYear())
                .setEndYear(artist.getEndYear())
                .setAge(artist.getAge());
    }
}
