package ca.nomosnow.sportorganizationeventcreatedepartmentservice.repository;

import ca.nomosnow.sportorganizationeventcreatedepartmentservice.model.SportEvent;
import org.springframework.data.repository.CrudRepository;

// SportEventRepository
public interface SportEventRepository extends CrudRepository<SportEvent,String> {

}
