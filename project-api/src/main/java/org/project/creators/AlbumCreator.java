package org.project.creators;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.project.model.Artist;
import org.project.model.Genre;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AlbumCreator {
    @NotBlank
    private String name;
    private String year;
    private Long artistId;
    private Long genreId;
}
