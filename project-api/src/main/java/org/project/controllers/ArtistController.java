package org.project.controllers;

import lombok.RequiredArgsConstructor;
import org.project.contracts.AlbumDto;
import org.project.contracts.ArtistDto;
import org.project.creators.ArtistCreator;
import org.project.services.interfaces.IArtistService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("api/artist")
@RequiredArgsConstructor
public class ArtistController {
    private final IArtistService artistService;
    @GetMapping
    public ResponseEntity<List<ArtistDto>> getPage(
            @RequestParam(required = false, defaultValue = "50") int size,
            @RequestParam(required = false, defaultValue = "1") int page
    ){ return ResponseEntity.ok(artistService.getByPage(size, page)); }
    @PostMapping("/create")
    public ResponseEntity createArtist(@RequestBody @Validated ArtistCreator artistCreator){
        var id = artistService.createArtist(artistCreator);
        return ResponseEntity.created(getLocationUri(id)).build();
    }
    @PostMapping
    public ResponseEntity saveArtist(@RequestBody ArtistDto artistDto){
        var id = artistService.save(artistDto);
        return ResponseEntity.created(getLocationUri(id)).build();
    }
    @GetMapping("{id}")
    public ResponseEntity<ArtistDto> getById(@PathVariable Long id){
        var result = artistService.getById(id);
        if(result==null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(result);
    }
    @DeleteMapping("{id}")
    public ResponseEntity delete(@PathVariable Long id){
        var result = artistService.delete(id);
        if(result==null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(result);
    }
    @PutMapping("{id}")
    public ResponseEntity update(@PathVariable Long id, @RequestBody ArtistDto artistDto){
        var result = artistService.update(id, artistDto);
        if(result==null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok().build();
    }
    @GetMapping("{id}/albums")
    public ResponseEntity<List<AlbumDto>> getAlbums(@PathVariable("id") Long artistId){
        var albumsDtos = artistService.getAlbums(artistId);
        if(albumsDtos==null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(albumsDtos);
    }
    @GetMapping("byNameAndAge")
    public ResponseEntity<ArtistDto> getByNameAndAge(@RequestParam String name, @RequestParam Integer age){
        var result = artistService.getByNameAndAge(name, age);
        if(result==null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(result);
    }
    @GetMapping("byStartYear/After")
    public ResponseEntity<List<ArtistDto>> getByStartYearAfter(@RequestParam Integer year){
        return ResponseEntity.ok(artistService.getByStartYearAfter(year));
    }
    @GetMapping("byStartYear/Before")
    public ResponseEntity<List<ArtistDto>> getByStartYearBefore(@RequestParam Integer year){
        return ResponseEntity.ok(artistService.getByStartYearBefore(year));
    }
    @GetMapping("byEndYear/After")
    public ResponseEntity<List<ArtistDto>> getByEndYearAfter(@RequestParam Integer year){
        return ResponseEntity.ok(artistService.getByEndYearAfter(year));
    }
    @GetMapping("byEndYear/Before")
    public ResponseEntity<List<ArtistDto>> getByEndYearBefore(@RequestParam Integer year){
        return ResponseEntity.ok(artistService.getByEndYearBefore(year));
    }
    private static URI getLocationUri(Long id){
        return ServletUriComponentsBuilder.fromCurrentRequest()
                .path(id+"")
                .buildAndExpand(1)
                .toUri();
    }
}
