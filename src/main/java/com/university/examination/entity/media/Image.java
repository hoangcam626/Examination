package com.university.examination.entity.media;

import com.university.examination.entity.AbstractAudit;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "image")
public class Image extends AbstractAudit {

    @Column(name="filename")
    private String filename;

    @Column(name = "type")
    private String type;

}
