package repositories;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Survey;

@Repository
public interface SurveyRepository extends JpaRepository<Survey, Integer> {

	// Consulta a la base de datos que nos devuelve una lista de Survey
	// cuya fecha de finalizaciÃ³n sea la que se pasa por parÃ¡metro.
	/**
	 * 
	 * @param now
	 *            es la fecha actual del sistema
	 * @return Este método devuelve una lista de Survey (votaciÃ³nes) cuya fecha
	 *         de finalizaciÃ³n sea igual a la fecha actual del sistema.
	 */
	@Query("select s from Survey s where s.endDate = NOW() or s.endDate < NOW()")
	public List<Survey> allFinishedSurveys();
	
	
	// Consulta a la base de datos que nos devuelve una lista de Survey las cuales estan activas
	/**
	 * 
	 * 
	 *            
	 * @return Este método devuelve una lista de Survey (votaciÃ³nes) activas, es decir que la fecha actual este
	 *         entre la fecha de comienzo y la de finalizaciÃ³n de la votaciÃ³n	
	 */
	@Query("select s from Survey s where s.startDate<NOW() and s.endDate>NOW()")
	public List<Survey> findAllActiveSurveys();

	// Cosulta a la base de datos que nos devuelve una lista de Survey
	// que han sido creadas por un username que se pasa por parÃ¡metro.
	/**
	 * 
	 * @param username
	 *            es el nombre de un usuario
	 * @return Este método devuelve una lista con los Survey (votaciones) que
	 *         han sido creados por un usuario.
	 */
	@Query("select s from Survey s where s.usernameCreator = ?1")
	public Collection<Survey> allCreatedSurveys(String username);

	// Cosulta a la base de datos que nos devuelve una lista de Survey
	// que han sido creadas por un username que se pasa por parÃ¡metro.
	/**
	 * 
	 * @param postalCode
	 *            código postal del lugar donde buscar encuestas
	 * @return Este método devuelve una lista con las Survey (votaciones) realizadas
	 *         en un determinado lugar (mismo código postal).
	 */
	
	@Query("select s from Survey s where s.postalCode = ?1")
	public Collection<Survey> findSurveysByPlace(String postalCode);
}
