package com.gef_biotag.Biotag.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gef_biotag.Biotag.model.BatimentoCardiaco;
import com.gef_biotag.Biotag.model.NFC;
import com.gef_biotag.Biotag.model.Pulseira;
import com.gef_biotag.Biotag.repository.PulseiraRepository;

@Service
public class PulseiraService {

    @Autowired
    private PulseiraRepository pulseiraRepository;

    public Pulseira criarPulseira(Integer bpm) {
        Pulseira pulseira = new Pulseira();

        NFC nfc = new NFC(); 
        pulseira.setNfc(nfc);

        BatimentoCardiaco batimento = new BatimentoCardiaco();
        batimento.setBpm(bpm);

        pulseira.setNfc(nfc);
        pulseira.setBatimento(batimento);

        return pulseiraRepository.save(pulseira);
    }
}