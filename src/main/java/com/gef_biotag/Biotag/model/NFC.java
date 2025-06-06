package com.gef_biotag.Biotag.model;

import jakarta.persistence.Column;
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
@Table(name = "nfc")
public class NFC {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "nfc_seq")
    @SequenceGenerator(name = "nfc_seq", sequenceName = "SEQ_NFC", allocationSize = 1)
    @Column(name = "ID_NFC") 
    private Long idNFC;
}