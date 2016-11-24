package domain;

import javax.persistence.Entity;

@Entity
public class Question extends DomainEntity {
	/**
	 * @Class Question
	 * @classDec La clase contiene los atributos que forman la entidad Question (pregunta), así como las relaciones
	 * que tiene esta entidad con el resto de entidades del dominio. 
	 * 
	 */
	// Attributes
	private String text;
	private Integer surveyId;

	public Question() {
		super();
		text = "";
	}

	// Methods

	// Este atributo indica el texto que forma la question.
	/**
	 * 
	 * @return Este metodo devuelve el texto de la question (pregunta).
	 */
	public String getText() {
		return text;
	}
	/**
	 * 
	 * @param text es el texto que forma la question (pregunta).
	 */

	public void setText(String text) {
		this.text = text;
	}
	/**
	 * 
	 * @return Este metodo devuelve la id de la votación a la que pertenece la pregunta. 
	 */
	public Integer getSurveyId() {
		return surveyId;
	}
	/**
	 * 
	 * @param id es la id de una votación.
	 */
	public void setSurveyId(Integer id) {
		surveyId = id;
	}

	// Método toString para la representación como cadena de la clase entidad.

	/**
	 * 
	 * @return Este metodo devuelve la representación como cadena de la entidad question (pregunta).
	 */
	@Override
	public String toString() {
		return "Question [text=" + text + "]";
	}

}
