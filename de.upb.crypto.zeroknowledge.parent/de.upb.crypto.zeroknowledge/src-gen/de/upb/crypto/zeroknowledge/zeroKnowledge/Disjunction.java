/**
 * generated by Xtext 2.17.0
 */
package de.upb.crypto.zeroknowledge.zeroKnowledge;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Disjunction</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link de.upb.crypto.zeroknowledge.zeroKnowledge.Disjunction#getLeft <em>Left</em>}</li>
 *   <li>{@link de.upb.crypto.zeroknowledge.zeroKnowledge.Disjunction#getOperation <em>Operation</em>}</li>
 *   <li>{@link de.upb.crypto.zeroknowledge.zeroKnowledge.Disjunction#getRight <em>Right</em>}</li>
 * </ul>
 *
 * @see de.upb.crypto.zeroknowledge.zeroKnowledge.ZeroKnowledgePackage#getDisjunction()
 * @model
 * @generated
 */
public interface Disjunction extends Expression
{
  /**
   * Returns the value of the '<em><b>Left</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the value of the '<em>Left</em>' containment reference.
   * @see #setLeft(Expression)
   * @see de.upb.crypto.zeroknowledge.zeroKnowledge.ZeroKnowledgePackage#getDisjunction_Left()
   * @model containment="true"
   * @generated
   */
  Expression getLeft();

  /**
   * Sets the value of the '{@link de.upb.crypto.zeroknowledge.zeroKnowledge.Disjunction#getLeft <em>Left</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Left</em>' containment reference.
   * @see #getLeft()
   * @generated
   */
  void setLeft(Expression value);

  /**
   * Returns the value of the '<em><b>Operation</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the value of the '<em>Operation</em>' attribute.
   * @see #setOperation(String)
   * @see de.upb.crypto.zeroknowledge.zeroKnowledge.ZeroKnowledgePackage#getDisjunction_Operation()
   * @model
   * @generated
   */
  String getOperation();

  /**
   * Sets the value of the '{@link de.upb.crypto.zeroknowledge.zeroKnowledge.Disjunction#getOperation <em>Operation</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Operation</em>' attribute.
   * @see #getOperation()
   * @generated
   */
  void setOperation(String value);

  /**
   * Returns the value of the '<em><b>Right</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the value of the '<em>Right</em>' containment reference.
   * @see #setRight(Expression)
   * @see de.upb.crypto.zeroknowledge.zeroKnowledge.ZeroKnowledgePackage#getDisjunction_Right()
   * @model containment="true"
   * @generated
   */
  Expression getRight();

  /**
   * Sets the value of the '{@link de.upb.crypto.zeroknowledge.zeroKnowledge.Disjunction#getRight <em>Right</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Right</em>' containment reference.
   * @see #getRight()
   * @generated
   */
  void setRight(Expression value);

} // Disjunction
