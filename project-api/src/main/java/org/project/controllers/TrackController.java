package org.project.controllers;

import lombok.RequiredArgsConstructor;
import org.project.contracts.AlbumDto;
import org.project.contracts.TrackDto;
import org.project.creators.GenreCreator;
import org.project.creators.TrackCreator;
import org.project.services.interfaces.ITrackService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("api/track")
@RequiredArgsConstructor
public class TrackController {
    private final ITrackService trackService;
    @GetMapping
    public ResponseEntity<List<TrackDto>> getPage(
            @RequestParam(required = false, defaultValue = "50") int size,
            @RequestParam(required = false, defaultValue = "1") int page
    ){ return ResponseEntity.ok(trackService.getByPage(size, page)); }
    @PostMapping("/create")
    public ResponseEntity createTrack(@RequestBody @Validated TrackCreator trackCreator){
        var id = trackService.createTrack(trackCreator);
        return ResponseEntity.created(getLocationUri(id)).build();
    }
    @PostMapping
    public ResponseEntity saveTrack(@RequestBody TrackDto trackDto){
        var id = trackService.save(trackDto);
        return ResponseEntity.created(getLocationUri(id)).build();
    }
    @GetMapping("{id}")
    public ResponseEntity<TrackDto> getById(@PathVariable Long id){
        var result = trackService.getById(id);
        if(result==null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(result);
    }
    @DeleteMapping("{id}")
    public ResponseEntity delete(@PathVariable Long id){
        var result = trackService.delete(id);
        if(result==null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(result);
    }
    @PutMapping("{id}")
    public ResponseEntity update(@PathVariable Long id, @RequestBody TrackDto trackDto){
        var result = trackService.update(id, trackDto);
        if(result==null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok().build();
    }
    @GetMapping("{id}/album")
    public ResponseEntity<AlbumDto> getAlbum(@PathVariable("id") Long trackId){
        var albumDto = trackService.getAlbum(trackId);
        if(albumDto==null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(albumDto);
    }
    @GetMapping("byAlbum")
    public ResponseEntity<List<TrackDto>> getByAlbumName(@RequestParam String name){
        return ResponseEntity.ok(trackService.getByAlbumName(name));
    }
    @GetMapping("byNameAndNumber")
    public ResponseEntity<TrackDto> getByNameAndNumber(@RequestParam String name, @RequestParam Integer number){
        var result = trackService.getByNameAndNumber(name, number);
        if(result==null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(result);
    }
    @GetMapping("byDuration/longerThan")
    public ResponseEntity<List<TrackDto>> getByDurationLongerThan(@RequestParam Float duration){
        return ResponseEntity.ok(trackService.getByDurationLongerThan(duration));
    }
    @GetMapping("byDuration/shorterThan")
    public ResponseEntity<List<TrackDto>> getByDurationShorterThan(@RequestParam Float duration){
        return ResponseEntity.ok(trackService.getByDurationShorterThan(duration));
    }
    private static URI getLocationUri(Long id){
        return ServletUriComponentsBuilder.fromCurrentRequest()
                .path(id+"")
                .buildAndExpand(1)
                .toUri();
    }
}
