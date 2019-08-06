/**
 * generated by Xtext 2.17.0
 */
package de.upb.crypto.zeroknowledge.zeroKnowledge;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Witness List</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link de.upb.crypto.zeroknowledge.zeroKnowledge.WitnessList#getWitnesses <em>Witnesses</em>}</li>
 *   <li>{@link de.upb.crypto.zeroknowledge.zeroKnowledge.WitnessList#getSymbol <em>Symbol</em>}</li>
 * </ul>
 *
 * @see de.upb.crypto.zeroknowledge.zeroKnowledge.ZeroKnowledgePackage#getWitnessList()
 * @model
 * @generated
 */
public interface WitnessList extends EObject
{
  /**
   * Returns the value of the '<em><b>Witnesses</b></em>' containment reference list.
   * The list contents are of type {@link de.upb.crypto.zeroknowledge.zeroKnowledge.Witness}.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the value of the '<em>Witnesses</em>' containment reference list.
   * @see de.upb.crypto.zeroknowledge.zeroKnowledge.ZeroKnowledgePackage#getWitnessList_Witnesses()
   * @model containment="true"
   * @generated
   */
  EList<Witness> getWitnesses();

  /**
   * Returns the value of the '<em><b>Symbol</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the value of the '<em>Symbol</em>' attribute.
   * @see #setSymbol(String)
   * @see de.upb.crypto.zeroknowledge.zeroKnowledge.ZeroKnowledgePackage#getWitnessList_Symbol()
   * @model
   * @generated
   */
  String getSymbol();

  /**
   * Sets the value of the '{@link de.upb.crypto.zeroknowledge.zeroKnowledge.WitnessList#getSymbol <em>Symbol</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Symbol</em>' attribute.
   * @see #getSymbol()
   * @generated
   */
  void setSymbol(String value);

} // WitnessList
