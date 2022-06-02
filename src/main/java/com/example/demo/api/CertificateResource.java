package com.example.demo.api;


import com.example.demo.entity.Certificate;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.service.CertificateService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(CertificateResource.PATH)
@RequiredArgsConstructor
public class CertificateResource {

    public final static String PATH = "/api/certificate";

    CertificateService certificateService;

    @GetMapping
    public ResponseEntity<List<Certificate>> getAll(){return ResponseEntity.ok(certificateService.getAll());}

    @GetMapping("/{id}")
    public ResponseEntity<Certificate> getCertificateById(@PathVariable(value = "id")Integer id)throws ResourceNotFoundException{
        Certificate certificate = certificateService.findCertificateById(id)
                .orElseThrow(
                () -> new ResourceNotFoundException("Id Not Found!"));
        return ResponseEntity.ok(certificate);
    }

    @PostMapping
    public ResponseEntity<Certificate> createCertificate(@RequestBody Certificate newCertificate){
        Certificate certificate = certificateService.saveCertificate(newCertificate);
        return ResponseEntity.created(URI.create(CertificateResource.PATH+ "/"+ certificate.getCertificateId())).body(certificate);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCertificateById(@PathVariable(value = "id")Integer id) throws  ResourceNotFoundException {
        Certificate certificate = certificateService.findCertificateById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Id Not Found!"));
        certificateService.deleteCertificateById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Certificate> updateCertificateById(@PathVariable(value = "id")Integer id,
                                                             @RequestBody Certificate newCertificate) throws ResourceNotFoundException
    {
        Certificate updateCertificate = certificateService.findCertificateById(id).orElseThrow(() -> new ResourceNotFoundException("Id Not Found!"));
        updateCertificate.setAirplane(newCertificate.getAirplane());
        updateCertificate.setEmployee(newCertificate.getEmployee());

        Certificate savedCertificate = certificateService.saveCertificate(updateCertificate);
        return ResponseEntity.ok(savedCertificate);
    }
}
