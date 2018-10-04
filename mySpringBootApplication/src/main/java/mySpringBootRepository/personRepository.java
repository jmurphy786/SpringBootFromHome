package mySpringBootRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import mySpringBootDatabaseApp.mySpringBootDataModel;

@Repository
public interface personRepository extends JpaRepository<mySpringBootDataModel, Long> {

}
