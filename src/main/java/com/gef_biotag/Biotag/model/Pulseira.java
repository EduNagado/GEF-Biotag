package com.gef_biotag.Biotag.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "pulseira")
public class Pulseira {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pulseira_seq")
    @SequenceGenerator(name = "pulseira_seq", sequenceName = "SEQ_PULSEIRA", allocationSize = 1)
    @Column(name = "ID_PULSEIRA") 
    private Long PulseiraId;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "NFC_ID", unique = true)
    private NFC nfc;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "IOT_ID", unique = true)
    private BatimentoCardiaco batimento;
}
