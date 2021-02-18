package de.upb.crypto.zeroknowledge.model;

import de.upb.crypto.zeroknowledge.model.BranchState;
import de.upb.crypto.zeroknowledge.model.ModelMap;
import de.upb.crypto.zeroknowledge.type.TypeInference;
import de.upb.crypto.zeroknowledge.zeroKnowledge.FunctionCall;
import de.upb.crypto.zeroknowledge.zeroKnowledge.FunctionDefinition;
import de.upb.crypto.zeroknowledge.zeroKnowledge.Model;
import de.upb.crypto.zeroknowledge.zeroKnowledge.NumberLiteral;
import de.upb.crypto.zeroknowledge.zeroKnowledge.Parameter;
import de.upb.crypto.zeroknowledge.zeroKnowledge.Variable;
import de.upb.crypto.zeroknowledge.zeroKnowledge.Witness;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.xbase.lib.Procedures.Procedure2;

/**
 * A debugging class to print out a formatted view of the model to the console
 */
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
      boolean _matched = false;
      if (node instanceof Witness) {
        _matched=true;
        String _string = ((Witness)node).getName().toString();
        String _plus = (" - " + _string);
        System.out.print(_plus);
      }
      if (!_matched) {
        if (node instanceof FunctionCall) {
          _matched=true;
          String _string = ((FunctionCall)node).getName().toString();
          String _plus = (" - " + _string);
          System.out.print(_plus);
        }
      }
      if (!_matched) {
        if (node instanceof FunctionDefinition) {
          _matched=true;
          String _string = ((FunctionDefinition)node).getName().toString();
          String _plus = (" - " + _string);
          System.out.print(_plus);
        }
      }
      if (!_matched) {
        if (node instanceof Parameter) {
          _matched=true;
          String _string = ((Parameter)node).getName().toString();
          String _plus = (" - " + _string);
          System.out.print(_plus);
        }
      }
      if (!_matched) {
        if (node instanceof Variable) {
          _matched=true;
          String _string = ((Variable)node).getName().toString();
          String _plus = (" - " + _string);
          System.out.print(_plus);
        }
      }
      if (!_matched) {
        if (node instanceof NumberLiteral) {
          _matched=true;
          String _string = Integer.valueOf(((NumberLiteral)node).getValue()).toString();
          String _plus = (" - " + _string);
          System.out.print(_plus);
        }
      }
      if (!_matched) {
        System.out.print("");
      }
      if (((TypeInference.getTypes() != null) && TypeInference.getTypes().containsKey(node))) {
        String _string = TypeInference.getTypes().get(node).toString();
        String _plus = (" - " + _string);
        System.out.print(_plus);
      } else {
        System.out.print("");
      }
      if (((TypeInference.getSizes() != null) && TypeInference.getSizes().containsKey(node))) {
        String _string_1 = TypeInference.getSizes().get(node).toString();
        String _plus_1 = (" (" + _string_1);
        String _plus_2 = (_plus_1 + ")");
        System.out.print(_plus_2);
      } else {
        System.out.print("");
      }
      System.out.println("");
    };
    ModelMap.preorderWithState(model, _branchState, _function);
  }
}
