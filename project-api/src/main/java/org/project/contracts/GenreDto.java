package org.project.contracts;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.List;

@Getter
@Setter
@Accessors(chain = true)
public class GenreDto {
    private Long id;
    private String name;
    private List<AlbumDto> albums;
}
