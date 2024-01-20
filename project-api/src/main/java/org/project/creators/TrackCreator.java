package org.project.creators;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TrackCreator {
    @NotBlank
    private String name;
    private Integer number;
    private Float duration;
    private Long albumId;
}
