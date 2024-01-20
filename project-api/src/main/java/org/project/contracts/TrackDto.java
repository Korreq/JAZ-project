package org.project.contracts;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
public class TrackDto {
    private Long id;
    private String name;
    private Integer number;
    private Float duration;
    private AlbumDto album;
}
