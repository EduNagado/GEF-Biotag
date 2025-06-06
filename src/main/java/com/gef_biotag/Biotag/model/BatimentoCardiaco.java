package com.gef_biotag.Biotag.model;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "batimento_cardiaco")
public class BatimentoCardiaco {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "iot_seq")
    @SequenceGenerator(name = "iot_seq", sequenceName = "SEQ_IOT", allocationSize = 1)
    private Long idBatimentoCardiaco;
    private Integer bpm;

    private LocalDateTime timestamp;

    public Integer getBpm() {
        return bpm;
    }

    public LocalDateTime getTimestamp(){
        return timestamp;
    }


}
