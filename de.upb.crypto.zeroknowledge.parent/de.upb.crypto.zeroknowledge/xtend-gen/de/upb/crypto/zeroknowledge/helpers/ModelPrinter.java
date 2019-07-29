package de.upb.crypto.zeroknowledge.helpers;

import de.upb.crypto.zeroknowledge.helpers.BranchState;
import de.upb.crypto.zeroknowledge.helpers.ModelMap;
import de.upb.crypto.zeroknowledge.helpers.TypeResolution;
import de.upb.crypto.zeroknowledge.zeroKnowledge.FunctionCall;
import de.upb.crypto.zeroknowledge.zeroKnowledge.FunctionDefinition;
import de.upb.crypto.zeroknowledge.zeroKnowledge.Model;
import de.upb.crypto.zeroknowledge.zeroKnowledge.NumberLiteral;
import de.upb.crypto.zeroknowledge.zeroKnowledge.Parameter;
import de.upb.crypto.zeroknowledge.zeroKnowledge.Variable;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.xbase.lib.Procedures.Procedure2;

@SuppressWarnings("all")
public class ModelPrinter {
  public static void print(final Model model) {
    BranchState _branchState = new BranchState();
    final Procedure2<EObject, BranchState> _function = (EObject node, BranchState state) -> {
      for (int i = 0; (i < state.getDepth()); i++) {
        System.out.print("---|");
      }
      String className = node.getClass().toString();
      final int periodIndex = className.lastIndexOf(".");
      if ((periodIndex > 0)) {
        className = className.substring((periodIndex + 1));
      }
      int _length = className.length();
      int _minus = (_length - 4);
      System.out.print(className.substring(0, _minus));
      if (((TypeResolution.getTypes() != null) && TypeResolution.getTypes().containsKey(node))) {
        String _string = TypeResolution.getTypes().get(node).toString();
        String _plus = (" - " + _string);
        System.out.print(_plus);
      }
      boolean _matched = false;
      if (node instanceof FunctionCall) {
        _matched=true;
        String _string_1 = ((FunctionCall)node).getName().toString();
        String _plus_1 = (" - " + _string_1);
        System.out.println(_plus_1);
      }
      if (!_matched) {
        if (node instanceof FunctionDefinition) {
          _matched=true;
          String _string_1 = ((FunctionDefinition)node).getName().toString();
          String _plus_1 = (" - " + _string_1);
          System.out.println(_plus_1);
        }
      }
      if (!_matched) {
        if (node instanceof Parameter) {
          _matched=true;
          String _string_1 = ((Parameter)node).getName().toString();
          String _plus_1 = (" - " + _string_1);
          System.out.println(_plus_1);
        }
      }
      if (!_matched) {
        if (node instanceof Variable) {
          _matched=true;
          String _string_1 = ((Variable)node).getName().toString();
          String _plus_1 = (" - " + _string_1);
          System.out.println(_plus_1);
        }
      }
      if (!_matched) {
        if (node instanceof NumberLiteral) {
          _matched=true;
          String _string_1 = Integer.valueOf(((NumberLiteral)node).getValue()).toString();
          String _plus_1 = (" - " + _string_1);
          System.out.println(_plus_1);
        }
      }
      if (!_matched) {
        System.out.println("");
      }
    };
    ModelMap.preorderWithState(model, _branchState, _function);
  }
}
