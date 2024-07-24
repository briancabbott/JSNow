/*******************************************************************************
 * Copyright (c) 2010, 2022 itemis AG (http://www.itemis.eu) and others.
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package org.eclipse.xtext.ui.tests.editor.contentassist;

import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.xtext.scoping.IScope;
import org.eclipse.xtext.scoping.IScopeProvider;
import org.eclipse.xtext.scoping.Scopes;
import org.eclipse.xtext.scoping.impl.SimpleLocalScopeProvider;
import org.eclipse.xtext.ui.tests.editor.contentassist.bug291022TestLanguage.Bug291022TestLanguagePackage;
import org.eclipse.xtext.ui.tests.editor.contentassist.bug291022TestLanguage.ModelElement;

import com.google.common.collect.Lists;

/**
 * Use this class to register components to be used within the IDE.
 */
public class Bug291022TestLanguageRuntimeModule extends AbstractBug291022TestLanguageRuntimeModule {

	@Override
	public Class<? extends IScopeProvider> bindIScopeProvider() {
		return ScopeProvider.class;
	}
	
	public static class ScopeProvider extends SimpleLocalScopeProvider {
		
		@Override
		public IScope getScope(EObject context, EReference reference) {
			if (Bug291022TestLanguagePackage.Literals.MODEL_ELEMENT__SECOND_REFERENCE == reference) {
				List<EObject> scope = Lists.newArrayList();
				if (context instanceof ModelElement) {
					ModelElement modelElement = (ModelElement) context;
					if (modelElement.getFirstReference() != null && !modelElement.getFirstReference().eIsProxy())
						scope.add(modelElement.getFirstReference());
				}
				return Scopes.scopeFor(scope);
			}
			return super.getScope(context, reference);
		}
		
	}
}
