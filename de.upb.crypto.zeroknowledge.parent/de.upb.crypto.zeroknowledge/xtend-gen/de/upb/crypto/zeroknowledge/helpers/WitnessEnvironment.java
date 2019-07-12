package de.upb.crypto.zeroknowledge.helpers;

import java.util.Set;

@SuppressWarnings("all")
public class WitnessEnvironment {
  private Set<String> witnesses;
  
  public WitnessEnvironment(final Set<String> witnessSet) {
    this.witnesses = witnessSet;
  }
  
  public boolean contains(final String element) {
    return this.witnesses.contains(element);
  }
}
