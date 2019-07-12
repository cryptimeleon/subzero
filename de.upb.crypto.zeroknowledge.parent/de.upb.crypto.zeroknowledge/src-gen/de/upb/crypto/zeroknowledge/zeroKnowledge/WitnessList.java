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

} // WitnessList
