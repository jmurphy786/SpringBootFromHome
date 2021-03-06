package mySpringBootController;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import mySpringBootDatabaseApp.mySpringBootDataModel;
import mySpringBootRepository.personRepository;
import mySpringBootException.ResouceNotFoundException;

@RestController
@RequestMapping("/api")
public class mySpringBootController {

	@Autowired
	personRepository myRepository;
	
	//Method to create a person
	@PostMapping("/person")
	public mySpringBootDataModel createPerson(@Valid @RequestBody mySpringBootDataModel mSDM) {
		return myRepository.save(mSDM);
	}
	
	//Method to get a person
	@GetMapping("person/{id}")
	public mySpringBootDataModel getPersonById(@PathVariable(value = "id")Long personID) {
		return myRepository.findById(personID).orElseThrow(()-> new ResouceNotFoundException("MySpringBootDataModel", "id", personID));
	}
	
	//Method to get all people
	@GetMapping("/person")
	public List<mySpringBootDataModel> getAllPeople(){
		return myRepository.findAll();
	}
	
	//Method to update a person
	@PutMapping("/person/{id}")
	public mySpringBootDataModel updatePerson(@PathVariable(value = "id") Long personID, @Valid @RequestBody mySpringBootDataModel personDetails) {
		mySpringBootDataModel mSDM = myRepository.findById(personID).orElseThrow(()-> new ResouceNotFoundException("Person", "id", personID));
		mSDM.setName(personDetails.getName());
		mSDM.setAddress(personDetails.getAddress());
		mSDM.setAge(personDetails.getAge());
		
		mySpringBootDataModel updateData = myRepository.save(mSDM);
		return updateData;
	}
	
	//Method to remove a person
	@DeleteMapping("/person/{id}")
	public ResponseEntity<?> deletePerson(@PathVariable(value = "id") Long personID){
		mySpringBootDataModel mSDM = myRepository.findById(personID).orElseThrow(()-> new ResouceNotFoundException("Person", "id", personID));
		
		myRepository.delete(mSDM);
		return ResponseEntity.ok().build();
		
	}
	
		
}
