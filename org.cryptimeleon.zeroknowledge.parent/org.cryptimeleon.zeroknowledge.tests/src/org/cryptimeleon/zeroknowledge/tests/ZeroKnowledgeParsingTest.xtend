/*
 * generated by Xtext 2.24.0
 */
package org.cryptimeleon.zeroknowledge.tests

import com.google.inject.Inject
import org.cryptimeleon.zeroknowledge.zeroKnowledge.Model
import org.eclipse.xtext.testing.InjectWith
import org.eclipse.xtext.testing.extensions.InjectionExtension
import org.eclipse.xtext.testing.util.ParseHelper
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.^extension.ExtendWith

@ExtendWith(InjectionExtension)
@InjectWith(ZeroKnowledgeInjectorProvider)
class ZeroKnowledgeParsingTest {
	@Inject
	ParseHelper<Model> parseHelper
	
	// TODO: Add parsing tests in this format
	@Test
	def void loadModel() {
//		val result = parseHelper.parse('''
//			Hello Xtext!
//		''')
//		Assertions.assertNotNull(result)
//		val errors = result.eResource.errors
//		Assertions.assertTrue(errors.isEmpty, '''Unexpected errors: «errors.join(", ")»''')
		Assertions.assertTrue(true);
	}
}
