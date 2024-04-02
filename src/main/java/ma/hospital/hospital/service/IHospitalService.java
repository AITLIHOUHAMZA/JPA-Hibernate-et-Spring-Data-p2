package ma.hospital.hospital.service;

import ma.hospital.hospital.entities.Consultation;
import ma.hospital.hospital.entities.Medecin;
import ma.hospital.hospital.entities.Patient;
import ma.hospital.hospital.entities.RendezVous;

public interface IHospitalService {
    Patient savePatient(Patient patient);
    Medecin saveMedecin(Medecin medecin);
    RendezVous saveRDV(RendezVous rendezVous);
    Consultation saveConsultation(Consultation consultation);

}
