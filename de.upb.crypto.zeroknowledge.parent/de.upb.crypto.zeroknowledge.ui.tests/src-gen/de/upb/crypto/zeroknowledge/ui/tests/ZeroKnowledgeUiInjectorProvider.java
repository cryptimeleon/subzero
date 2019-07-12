/*
 * generated by Xtext 2.17.0
 */
package de.upb.crypto.zeroknowledge.ui.tests;

import com.google.inject.Injector;
import de.upb.crypto.zeroknowledge.ui.internal.ZeroknowledgeActivator;
import org.eclipse.xtext.testing.IInjectorProvider;

public class ZeroKnowledgeUiInjectorProvider implements IInjectorProvider {

	@Override
	public Injector getInjector() {
		return ZeroknowledgeActivator.getInstance().getInjector("de.upb.crypto.zeroknowledge.ZeroKnowledge");
	}

}
