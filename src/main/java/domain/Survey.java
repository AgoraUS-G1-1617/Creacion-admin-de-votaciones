package domain;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.LinkedList;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Access(AccessType.PROPERTY)
public class Survey extends DomainEntity implements Serializable {

	/**
	 * @Class Survey
	 * @classDec La clase contiene los atributos que forman la entidad Survey (votación), así como las relaciones
	 * que tiene esta entidad con el resto de entidades del dominio. 
	 * 
	 */
	private static final long serialVersionUID = 749544364605664829L;
	// Attributes
	private String title;
	private String description;
	private Date startDate;
	private Date endDate;
	private Integer census;
	private String tipo;

	public Survey() {
		super();
		questions = new LinkedList<Question>();
	}

	// Methods

	// Título de la votación
	
	/**
	 * @return Este metodo devuelve el título de la votación.
	*/

	@NotBlank
	@Length(min = 5, max = 100, message = "The field must be between 5 and 10 characters")
	public String getTitle() {
		return title;
	}
	/**
	 * @param title es el titulo de la votacion.
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	// Descripción de la votación
	/**
	 * @return Este metodo devuelve la descripción de la votación.
	 */
	@NotBlank
	public String getDescription() {
		return description;
	}
	/**
	 * 
	 * @param description es la descripción de la votación.
	 */

	public void setDescription(String description) {
		this.description = description;
	}

	// Fecha de creación de la votación
	// con @DateTimeFormat especificamos el formato para la fecha que se va a
	// almacenar en la base de datos del sistema.

	/**
	 * 
	 * @return Este metodo devuelve la fecha de inicio de la votación.
	 */
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	public Date getStartDate() {
		return startDate;
	}
	/**
	 * 
	 * @param startDate es la fecha de inicio de la votación.
	 */

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	// Fecha fin de la votación
	
	/**
	 * 
	 * @return Este metodo devuelve la fecha fin de la votación.
	 */

	@DateTimeFormat(pattern = "dd/MM/yyyy")
	public Date getEndDate() {
		return endDate;
	}
	/**
	 * 
	 * @param endDate es la fecha fin de la votación.
	 */

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	// El atributo tipo indica de que tipo de votación se trata.

	/**
	 * 
	 * @return devuelve el tipo de censo (abierto o cerrado).
	 */
	public String getTipo() {
		return tipo;
	}
	/**
	 * 
	 * @param tipo es el tipo de censo de la votación (abierto o cerrado).
	 */

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	// Indica la id del censo relacionado a la votacion

	/**
	 * 
	 * @return Este metodo devuelve la Id del censo al que pertenece la votación.
	 */
	public Integer getCensus() {
		return census;
	}

	/**
	 * 
	 * @param census es la id del centro al que pertenece la votación.
	 */
	public void setCensus(Integer census) {
		this.census = census;
	}

	// Relationships
	private Collection<Question> questions;
	private String usernameCreator;

	// Asociación con la clase entidad userNameControl
	
	/**
	 * 
	 * @return Este metodo devuelve el nombre del creador de la votación.
	 */
	public String getUsernameCreator() {
		return usernameCreator;
	}
	/**
	 * 
	 * @param usernameCreator es el nombre del creador de la votación
	 */

	public void setUsernameCreator(String usernameCreator) {
		this.usernameCreator = usernameCreator;
	}

	// Asociación con la clase entidad questions
	
	/**
	 * 
	 * @return Este metodo devuelve la lista de questions (preguntas).
	 */
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	public Collection<Question> getQuestions() {
		return questions;
	}
	/**
	 * 
	 * @param questions es una colección de questions(preguntas).
	 */

	public void setQuestions(Collection<Question> questions) {
		this.questions = questions;
	}
	
	/**
	 * 
	 * @param q es una question (pregunta).
	 */
	public void addQuestion(Question q) {
		questions.add(q);
	}
	
	/**
	 * 
	 * @param q es una question (pregunta).
	 */
	public void removeQuestion(Question q) {
		questions.remove(q);
	}

	// Método toString para la representación del la entidad Survey

	/**
	 * 
	 * @return Este metodo devuelve la representación como cadena de la entidad Survey (votación).
	 */
	@Override
	public String toString() {
		return "Survey [title=" + title + ", description=" + description + ", startDate=" + startDate + ", endDate="
				+ endDate + ", census=" + census + ", questions=" + questions + "]";
	}

}
