package com.gef_biotag.Biotag.dto;

import com.gef_biotag.Biotag.model.NFC;

public record NfcDTO(Long idNFC) {
    public NfcDTO(NFC nfc) {
        this(nfc.getIdNFC());
    }
}
