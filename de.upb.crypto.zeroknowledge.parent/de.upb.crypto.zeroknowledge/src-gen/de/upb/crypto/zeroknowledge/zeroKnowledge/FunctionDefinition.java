/**
 * generated by Xtext 2.17.0
 */
package de.upb.crypto.zeroknowledge.zeroKnowledge;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Function Definition</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link de.upb.crypto.zeroknowledge.zeroKnowledge.FunctionDefinition#getName <em>Name</em>}</li>
 *   <li>{@link de.upb.crypto.zeroknowledge.zeroKnowledge.FunctionDefinition#getParameterList <em>Parameter List</em>}</li>
 *   <li>{@link de.upb.crypto.zeroknowledge.zeroKnowledge.FunctionDefinition#getBody <em>Body</em>}</li>
 * </ul>
 *
 * @see de.upb.crypto.zeroknowledge.zeroKnowledge.ZeroKnowledgePackage#getFunctionDefinition()
 * @model
 * @generated
 */
public interface FunctionDefinition extends EObject
{
  /**
   * Returns the value of the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the value of the '<em>Name</em>' attribute.
   * @see #setName(String)
   * @see de.upb.crypto.zeroknowledge.zeroKnowledge.ZeroKnowledgePackage#getFunctionDefinition_Name()
   * @model
   * @generated
   */
  String getName();

  /**
   * Sets the value of the '{@link de.upb.crypto.zeroknowledge.zeroKnowledge.FunctionDefinition#getName <em>Name</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Name</em>' attribute.
   * @see #getName()
   * @generated
   */
  void setName(String value);

  /**
   * Returns the value of the '<em><b>Parameter List</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the value of the '<em>Parameter List</em>' containment reference.
   * @see #setParameterList(ParameterList)
   * @see de.upb.crypto.zeroknowledge.zeroKnowledge.ZeroKnowledgePackage#getFunctionDefinition_ParameterList()
   * @model containment="true"
   * @generated
   */
  ParameterList getParameterList();

  /**
   * Sets the value of the '{@link de.upb.crypto.zeroknowledge.zeroKnowledge.FunctionDefinition#getParameterList <em>Parameter List</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Parameter List</em>' containment reference.
   * @see #getParameterList()
   * @generated
   */
  void setParameterList(ParameterList value);

  /**
   * Returns the value of the '<em><b>Body</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the value of the '<em>Body</em>' containment reference.
   * @see #setBody(Expression)
   * @see de.upb.crypto.zeroknowledge.zeroKnowledge.ZeroKnowledgePackage#getFunctionDefinition_Body()
   * @model containment="true"
   * @generated
   */
  Expression getBody();

  /**
   * Sets the value of the '{@link de.upb.crypto.zeroknowledge.zeroKnowledge.FunctionDefinition#getBody <em>Body</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Body</em>' containment reference.
   * @see #getBody()
   * @generated
   */
  void setBody(Expression value);

} // FunctionDefinition
