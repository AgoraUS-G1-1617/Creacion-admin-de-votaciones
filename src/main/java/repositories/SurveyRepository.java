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
	// cuya fecha de finalización sea la que se pasa por parámetro.
	/**
	 * 
	 * @param now
	 *            es la fecha actual del sistema
	 * @return Este m�todo devuelve una lista de Survey (votaciónes) cuya fecha
	 *         de finalización sea igual a la fecha actual del sistema.
	 */
	@Query("select s from Survey s where s.endDate = ?1")
	public List<Survey> allFinishedSurveys(Date now);
	
	
	// Consulta a la base de datos que nos devuelve una lista de Survey las cuales estan activas
	/**
	 * 
	 * 
	 *            
	 * @return Este m�todo devuelve una lista de Survey (votaciónes) activas, es decir que la fecha actual este
	 *         entre la fecha de comienzo y la de finalización de la votación	
	 */
	@Query("select s from Survey s where s.startDate<NOW() and s.endDate>NOW()")
	public List<Survey> findAllActiveSurveys();

	// Cosulta a la base de datos que nos devuelve una lista de Survey
	// que han sido creadas por un username que se pasa por parámetro.
	/**
	 * 
	 * @param username
	 *            es el nombre de un usuario
	 * @return Este m�todo devuelve una lista con los Survey (votaciones) que
	 *         han sido creados por un usuario.
	 */
	@Query("select s from Survey s where s.usernameCreator = ?1")
	public Collection<Survey> allCreatedSurveys(String username);

	// Cosulta a la base de datos que nos devuelve una lista de Survey
	// que han sido creadas por un username que se pasa por parámetro.
	/**
	 * 
	 * @param postalCode
	 *            c�digo postal del lugar donde buscar encuestas
	 * @return Este m�todo devuelve una lista con las Survey (votaciones) realizadas
	 *         en un determinado lugar (mismo c�digo postal).
	 */
	
	@Query("select s from Survey s where s.postalCode = ?1")
	public Collection<Survey> findSurveysByPlace(String postalCode);
}
