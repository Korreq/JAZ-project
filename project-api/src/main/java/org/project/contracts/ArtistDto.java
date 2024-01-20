package org.project.contracts;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.List;

@Getter
@Setter
@Accessors(chain = true)
public class ArtistDto {
    private Long id;
    private String name;
    private String startYear;
    private String endYear;
    private Integer age;
    private List<AlbumDto> albums;
}
