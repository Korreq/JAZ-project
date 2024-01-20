package org.project.controllers;

import lombok.RequiredArgsConstructor;
import org.project.contracts.AlbumDto;
import org.project.contracts.GenreDto;
import org.project.creators.GenreCreator;
import org.project.services.interfaces.IGenreService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("api/genre")
@RequiredArgsConstructor
public class GenreController {
    private final IGenreService genreService;

    @GetMapping
    public ResponseEntity<List<GenreDto>> getAll(){
        return ResponseEntity.ok(genreService.getAll());
    }
    @PostMapping("/create")
    public ResponseEntity createGenre(@RequestBody @Validated GenreCreator genreCreator){
        var id = genreService.createGenre(genreCreator);
        return ResponseEntity.created(getLocationUri(id)).build();
    }
    @PostMapping
    public ResponseEntity saveGenre(@RequestBody GenreDto genreDto){
        var id = genreService.save(genreDto);
        return ResponseEntity.created(getLocationUri(id)).build();
    }
    @GetMapping("{id}")
    public ResponseEntity<GenreDto> getById(@PathVariable Long id){
        var result = genreService.getById(id);
        if(result==null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(result);
    }
    @DeleteMapping("{id}")
    public ResponseEntity delete(@PathVariable Long id){
        var result = genreService.delete(id);
        if(result==null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(result);
    }
    @PutMapping("{id}")
    public ResponseEntity update(@PathVariable Long id, @RequestBody GenreDto genreDto){
        var result = genreService.update(id, genreDto);
        if(result==null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok().build();
    }
    @GetMapping("{id}/albums")
    public ResponseEntity<List<AlbumDto>> getAlbums(@PathVariable("id") Long genreId){
        var albumsDtos = genreService.getAlbums(genreId);
        if(albumsDtos==null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(albumsDtos);
    }
    private static URI getLocationUri(Long id){
        return ServletUriComponentsBuilder.fromCurrentRequest()
                .path(id+"")
                .buildAndExpand(1)
                .toUri();
    }
}
