package de.upb.crypto.zeroknowledge.model;

import de.upb.crypto.zeroknowledge.model.BranchState;
import de.upb.crypto.zeroknowledge.model.ModelMapControl;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.xbase.lib.Functions.Function1;
import org.eclipse.xtext.xbase.lib.Procedures.Procedure1;
import org.eclipse.xtext.xbase.lib.Procedures.Procedure2;

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
  
  public static void preorderWithControl(final EObject node, final Procedure2<? super EObject, ? super ModelMapControl> function) {
    final ModelMapControl controller = new ModelMapControl();
    ModelMap.preorderWithControlHelper(node, controller, function);
  }
  
  public static void preorderWithControlHelper(final EObject node, final ModelMapControl controller, final Procedure2<? super EObject, ? super ModelMapControl> function) {
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