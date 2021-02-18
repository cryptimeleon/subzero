package de.upb.crypto.zeroknowledge.model;

@SuppressWarnings("all")
public class ModelMapController {
  private boolean breakCalled;
  
  private boolean continueCalled;
  
  public ModelMapController() {
    this.breakCalled = false;
    this.continueCalled = false;
  }
  
  public void breakMap() {
    this.breakCalled = true;
  }
  
  public void continueTraversal() {
    this.continueCalled = true;
  }
  
  public boolean triggerBreak() {
    return this.breakCalled;
  }
  
  public boolean triggerContinue() {
    final boolean tempValue = this.continueCalled;
    this.continueCalled = false;
    return tempValue;
  }
}
