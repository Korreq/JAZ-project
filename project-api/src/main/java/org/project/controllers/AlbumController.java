package org.project.controllers;

import lombok.RequiredArgsConstructor;
import org.project.contracts.AlbumDto;
import org.project.contracts.ArtistDto;
import org.project.contracts.GenreDto;
import org.project.contracts.TrackDto;
import org.project.creators.AlbumCreator;
import org.project.services.interfaces.IAlbumService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("api/album")
@RequiredArgsConstructor
public class AlbumController {
    private final IAlbumService albumService;
    @GetMapping
    public ResponseEntity<List<AlbumDto>> getPage(
            @RequestParam(required = false, defaultValue = "50") int size,
            @RequestParam(required = false, defaultValue = "0") int page
    ){ return ResponseEntity.ok(albumService.getByPage(size, page)); }
    @PostMapping("/create")
    public ResponseEntity createAlbum( @RequestBody @Validated AlbumCreator albumCreator){
        var id = albumService.createAlbum(albumCreator);
        return ResponseEntity.created(getLocationUri(id)).build();
    }
    @PostMapping
    public ResponseEntity saveAlbum(@RequestBody AlbumDto albumDto){
        var id = albumService.save(albumDto);
        return ResponseEntity.created(getLocationUri(id)).build();
    }
    @GetMapping("{id}")
    public ResponseEntity<AlbumDto> getById(@PathVariable Long id){
        var result = albumService.getById(id);
        if(result==null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(result);
    }
    @DeleteMapping("{id}")
    public ResponseEntity delete(@PathVariable Long id){
        var result = albumService.delete(id);
        if(result==null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(result);
    }
    @PutMapping("{id}")
    public ResponseEntity update(@PathVariable Long id, @RequestBody AlbumDto albumDto){
        var result = albumService.update(id, albumDto);
        if(result==null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok().build();
    }
    @GetMapping("{id}/genre")
    public ResponseEntity<GenreDto> getGenre(@PathVariable("id") Long albumId){
        var genreDto = albumService.getGenre(albumId);
        if(genreDto==null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(genreDto);
    }
    @GetMapping("{id}/artist")
    public ResponseEntity<ArtistDto> getArtist(@PathVariable("id") Long albumId){
        var artistDto = albumService.getArtist(albumId);
        if(artistDto==null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(artistDto);
    }
    @GetMapping("{id}/tracks")
    public ResponseEntity<List<TrackDto>> getTracks(@PathVariable("id") Long albumId){
        var trackDtos = albumService.getTracks(albumId);
        if(trackDtos==null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(trackDtos);
    }
    @GetMapping("byArtist")
    public ResponseEntity<List<AlbumDto>> getByArtistName(@RequestParam String name){
        return ResponseEntity.ok(albumService.getByArtistName(name));
    }
    @GetMapping("byGenreAndYear")
    public ResponseEntity<List<AlbumDto>> getByGenreNameAndYear(@RequestParam String name, @RequestParam String year){
        return ResponseEntity.ok(albumService.findByGenreNameAndYear(name, year));
    }
    private static URI getLocationUri(Long id){
        return ServletUriComponentsBuilder.fromCurrentRequest()
                .path(id+"")
                .buildAndExpand(1)
                .toUri();
    }
}
