/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.taufiq.peminjaman.service;

import com.taufiq.peminjaman.VO.Anggota;
import com.taufiq.peminjaman.VO.Buku;
import com.taufiq.peminjaman.VO.ResponseTemplateVO;
import com.taufiq.peminjaman.entity.Peminjaman;
import com.taufiq.peminjaman.repository.PeminjamanRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


/**
 *
 * @author user
 */
@Service
public class PeminjamanService {
    @Autowired
    private PeminjamanRepository peminjamanRepository;
    
    @Autowired
    private RestTemplate restTemplate;
    
    public Peminjaman savePeminjaman(Peminjaman peminjaman){
        return peminjamanRepository.save(peminjaman);
    }
    
    public ResponseTemplateVO getPeminjaman(Long peminjamanId){
        ResponseTemplateVO vo = new ResponseTemplateVO();
        Peminjaman peminjaman = 
                peminjamanRepository.findByPeminjamanId(peminjamanId);
        Anggota anggota = restTemplate.getForObject("http://localhost:9001/anggota/" + peminjaman.getAnggotaId(), Anggota.class);
        Buku buku = restTemplate.getForObject("http://localhost:9002/buku/" + peminjaman.getBukuId(), Buku.class);
        vo.setPeminjaman(peminjaman);
        vo.setAnggota(anggota);
        vo.setBuku(buku);
        return vo;
    }

    public Peminjaman findPeminjamanById(Long peminjamanId){
        return peminjamanRepository.findByPeminjamanId(peminjamanId);
    }
    
    public List<Peminjaman> getAllPeminjaman(){
        return peminjamanRepository.findAll();
    }
    
    public Peminjaman updatePeminjaman(Peminjaman peminjaman){
        return peminjamanRepository.save(peminjaman);
    }
    
    public void deleteById(Long peminjamanId) {
        peminjamanRepository.deleteById(peminjamanId);
    }
}
