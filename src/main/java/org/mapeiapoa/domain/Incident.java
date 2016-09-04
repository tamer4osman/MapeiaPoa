package org.mapeiapoa.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Data
public class Incident {
    @Id
    private String id;
    private String description;
    private String latitude;
    private String longitude;
}
