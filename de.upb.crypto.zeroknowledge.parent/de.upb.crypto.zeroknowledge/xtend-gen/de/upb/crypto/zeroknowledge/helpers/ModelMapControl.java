package de.upb.crypto.zeroknowledge.helpers;

@SuppressWarnings("all")
public class ModelMapControl {
  private boolean breakCalled;
  
  private boolean continueCalled;
  
  public ModelMapControl() {
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
