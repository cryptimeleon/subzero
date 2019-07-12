/**
 * generated by Xtext 2.17.0
 */
package de.upb.crypto.zeroknowledge.zeroKnowledge;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Parameter List</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link de.upb.crypto.zeroknowledge.zeroKnowledge.ParameterList#getParameters <em>Parameters</em>}</li>
 * </ul>
 *
 * @see de.upb.crypto.zeroknowledge.zeroKnowledge.ZeroKnowledgePackage#getParameterList()
 * @model
 * @generated
 */
public interface ParameterList extends EObject
{
  /**
   * Returns the value of the '<em><b>Parameters</b></em>' containment reference list.
   * The list contents are of type {@link de.upb.crypto.zeroknowledge.zeroKnowledge.Parameter}.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the value of the '<em>Parameters</em>' containment reference list.
   * @see de.upb.crypto.zeroknowledge.zeroKnowledge.ZeroKnowledgePackage#getParameterList_Parameters()
   * @model containment="true"
   * @generated
   */
  EList<Parameter> getParameters();

} // ParameterList
