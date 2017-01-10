package repositories;

import java.util.Collection;
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

	// Consulta a la base de datos que nos devuelve una lista de Survey
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

	// Consulta a la base de datos que nos devuelve una lista de Survey
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
	

	/**
	 * 
	 * @return Este método devuelve una lista con las Survey (votaciones) realizadas
	 *         en Sevilla (c�digo postal que comienza por 41).
	 */
	@Query("select s from Survey s where s.postalCode like '41%' and s.usernameCreator = ?1")
	public Collection<Survey> findSurveysFromSevilla(String username);
	
	/**
	 * 
	 * @return Este método devuelve una lista con las Survey (votaciones) ya
	 *         comenzadas.
	 */
	
	@Query("select s from Survey s where s.startDate<NOW() and s.usernameCreator = ?1")
	public Collection<Survey> findSurveysAlreadyStarted(String username);
	
	/**
	 * 
	 * @return Este método devuelve el ratio de Survey (votaciones) a�n no comenzadas.
	 */
	
	@Query("select distinct (select count(*) from Survey s where s.startDate>NOW() and s.usernameCreator = ?1)/(select count(*) from Survey s2 where s2.usernameCreator = ?1)*1.0 from Survey s3")
	public Double ratioOfSurveysWhichHaveNotStartedYet(String username);
	
	/**
	 * 
	 * @return Este método devuelve la media de preguntas por cada Survey (votaciones).
	 */
	
	@Query("select avg(s.questions.size) from Survey s where s.usernameCreator = ?1")
	public Double averageOfQuestionsPerSurvey(String username);
}
