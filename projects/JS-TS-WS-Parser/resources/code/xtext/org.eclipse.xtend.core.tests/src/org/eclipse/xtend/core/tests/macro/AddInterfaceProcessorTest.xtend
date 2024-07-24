/*******************************************************************************
 * Copyright (c) 2013, 2016 itemis AG (http://www.itemis.eu) and others.
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package org.eclipse.xtend.core.tests.macro

import org.eclipse.xtend.lib.macro.AbstractClassProcessor
import org.eclipse.xtend.lib.macro.Active
import org.eclipse.xtend.lib.macro.RegisterGlobalsContext
import org.eclipse.xtend.lib.macro.declaration.ClassDeclaration
import org.junit.Test

class AddInterfaceProcessorTest extends AbstractActiveAnnotationTest {
	
	@Test def void testAddInterface() {
		'''
			@org.eclipse.xtend.core.tests.macro.AddInterface class Foo {}
		'''.assertCompilesTo('''
			MULTIPLE FILES WERE GENERATED
			
			File 1 : /myProject/xtend-gen/Foo.java
			
			import org.eclipse.xtend.core.tests.macro.AddInterface;
			
			@AddInterface
			@SuppressWarnings("all")
			public class Foo {
			}
			
			File 2 : /myProject/xtend-gen/de/test/Test.java
			
			package de.test;
			
			@SuppressWarnings("all")
			public interface Test {
			}

		''')
	}
}

@Active(typeof(AddInterfaceProcessor))
annotation AddInterface {}
class AddInterfaceProcessor extends AbstractClassProcessor {
	
	override doRegisterGlobals(ClassDeclaration annotatedClass, RegisterGlobalsContext context) {
		context.registerInterface('de.test.Test')
	}
	
}