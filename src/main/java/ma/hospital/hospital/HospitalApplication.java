package ma.hospital.hospital;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import ma.hospital.hospital.entities.*;
import ma.hospital.hospital.repositories.ConsultationRepository;
import ma.hospital.hospital.repositories.MedecinRepository;
import ma.hospital.hospital.repositories.PatientRepository;
import ma.hospital.hospital.repositories.RendezVousRepository;
import ma.hospital.hospital.service.IHospitalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;
import java.util.stream.Stream;

@SpringBootApplication
public class HospitalApplication  {

	public static void main(String[] args) {
		SpringApplication.run(HospitalApplication.class, args);
	}
	@Bean
	CommandLineRunner start(IHospitalService hospitalService
			,PatientRepository patientRepository
			,RendezVousRepository rendezVousRepository,
	        MedecinRepository medecinRepository){
		return args -> {
			Stream.of("hamid","hassan","samira")
					.forEach(name->{
						Patient patient=new Patient();
						patient.setNom(name);
						patient.setDateNaissance(new Date());
						patient.setMalade(false);
						hospitalService.savePatient(patient);
					});
			Stream.of("Dr Bouziane","Dr Khouira"," Dr Oukacha")
					.forEach(name->{
						Medecin medecin=new Medecin();
						medecin.setNom(name);
						medecin.setEmail(name+"@gmail.com");
						medecin.setSpecialite(Math.random()>0.5?"Cardio":"Dentiste");
						hospitalService.saveMedecin(medecin);
					});

			Patient patient=patientRepository.findById(1L).orElse(null);
			Patient patient1=patientRepository.findByNom("hamid");

			Medecin medecin=medecinRepository.findByNom("Dr Bouziane");

			RendezVous rendezVous=new RendezVous();
			rendezVous.setDate(new Date());
			rendezVous.setStatus(StatusRDV.PENDING);
			rendezVous.setMedecin(medecin);
			rendezVous.setPatient(patient);
			RendezVous saveDRDV=hospitalService.saveRDV(rendezVous);
			System.out.println(saveDRDV.getId());


			RendezVous rendezVous1=rendezVousRepository.findAll().get(0);
			Consultation consultation=new Consultation();
			consultation.setDateConsultation(new Date());
			consultation.setRendezVous(rendezVous1);
			consultation.setRapport("Rapport de la consultation.....");
			hospitalService.saveConsultation(consultation);
		};
	}

	}

