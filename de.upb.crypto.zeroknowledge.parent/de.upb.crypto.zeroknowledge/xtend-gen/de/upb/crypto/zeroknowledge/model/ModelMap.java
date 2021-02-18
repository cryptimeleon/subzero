package de.upb.crypto.zeroknowledge.model;

import de.upb.crypto.zeroknowledge.model.BranchState;
import de.upb.crypto.zeroknowledge.model.ModelMapController;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.xbase.lib.Functions.Function1;
import org.eclipse.xtext.xbase.lib.Procedures.Procedure1;
import org.eclipse.xtext.xbase.lib.Procedures.Procedure2;

/**
 * A helper class for applying general functions to each node of the model
 * through various tree traversals
 */
@SuppressWarnings("all")
public class ModelMap {
  public static void preorder(final EObject node, final Procedure1<? super EObject> function) {
    function.apply(node);
    EList<EObject> _eContents = node.eContents();
    for (final EObject child : _eContents) {
      ModelMap.preorder(child, function);
    }
  }
  
  public static void postorder(final EObject node, final Procedure1<? super EObject> function) {
    EList<EObject> _eContents = node.eContents();
    for (final EObject child : _eContents) {
      ModelMap.postorder(child, function);
    }
    function.apply(node);
  }
  
  public static boolean preorderAny(final EObject node, final Function1<? super EObject, ? extends Boolean> function) {
    Boolean _apply = function.apply(node);
    if ((_apply).booleanValue()) {
      return true;
    }
    EList<EObject> _eContents = node.eContents();
    for (final EObject child : _eContents) {
      boolean _preorderAny = ModelMap.preorderAny(child, function);
      if (_preorderAny) {
        return true;
      }
    }
    return false;
  }
  
  public static boolean postorderAny(final EObject node, final Function1<? super EObject, ? extends Boolean> function) {
    EList<EObject> _eContents = node.eContents();
    for (final EObject child : _eContents) {
      boolean _postorderAny = ModelMap.postorderAny(child, function);
      if (_postorderAny) {
        return true;
      }
    }
    return (function.apply(node)).booleanValue();
  }
  
  public static void preorderWithState(final EObject node, final BranchState state, final Procedure2<? super EObject, ? super BranchState> function) {
    function.apply(node, state);
    state.updateState(node);
    EList<EObject> _eContents = node.eContents();
    for (final EObject child : _eContents) {
      BranchState _branchState = new BranchState(state);
      ModelMap.preorderWithState(child, _branchState, function);
    }
  }
  
  public static void preorderWithControl(final EObject node, final Procedure2<? super EObject, ? super ModelMapController> function) {
    final ModelMapController controller = new ModelMapController();
    ModelMap.preorderWithControlHelper(node, controller, function);
  }
  
  private static void preorderWithControlHelper(final EObject node, final ModelMapController controller, final Procedure2<? super EObject, ? super ModelMapController> function) {
    boolean _triggerBreak = controller.triggerBreak();
    if (_triggerBreak) {
      return;
    }
    function.apply(node, controller);
    boolean _triggerContinue = controller.triggerContinue();
    if (_triggerContinue) {
      return;
    }
    EList<EObject> _eContents = node.eContents();
    for (final EObject child : _eContents) {
      ModelMap.preorderWithControlHelper(child, controller, function);
    }
  }
}
