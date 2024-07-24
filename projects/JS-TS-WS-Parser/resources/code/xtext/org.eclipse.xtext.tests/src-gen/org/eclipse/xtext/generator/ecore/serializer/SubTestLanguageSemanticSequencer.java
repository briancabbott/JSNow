/*******************************************************************************
 * Copyright (c) 2010, 2024 itemis AG (http://www.itemis.eu) and others.
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package org.eclipse.xtext.generator.ecore.serializer;

import com.google.inject.Inject;
import java.util.Set;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.xtext.Action;
import org.eclipse.xtext.Parameter;
import org.eclipse.xtext.ParserRule;
import org.eclipse.xtext.generator.ecore.services.SubTestLanguageGrammarAccess;
import org.eclipse.xtext.generator.ecore.subPackage.AnotherSuperMain;
import org.eclipse.xtext.generator.ecore.subPackage.SubMain;
import org.eclipse.xtext.generator.ecore.subPackage.SubPackagePackage;
import org.eclipse.xtext.generator.ecore.superPackage.SuperMain;
import org.eclipse.xtext.generator.ecore.superPackage.SuperPackagePackage;
import org.eclipse.xtext.serializer.ISerializationContext;
import org.eclipse.xtext.serializer.acceptor.SequenceFeeder;
import org.eclipse.xtext.serializer.sequencer.ITransientValueService.ValueTransient;

@SuppressWarnings("all")
public class SubTestLanguageSemanticSequencer extends SuperTestLanguageSemanticSequencer {

	@Inject
	private SubTestLanguageGrammarAccess grammarAccess;
	
	@Override
	public void sequence(ISerializationContext context, EObject semanticObject) {
		EPackage epackage = semanticObject.eClass().getEPackage();
		ParserRule rule = context.getParserRule();
		Action action = context.getAssignedAction();
		Set<Parameter> parameters = context.getEnabledBooleanParameters();
		if (epackage == SubPackagePackage.eINSTANCE)
			switch (semanticObject.eClass().getClassifierID()) {
			case SubPackagePackage.ANOTHER_SUPER_MAIN:
				sequence_AnotherSuperMain(context, (AnotherSuperMain) semanticObject); 
				return; 
			case SubPackagePackage.SUB_MAIN:
				sequence_SubMain(context, (SubMain) semanticObject); 
				return; 
			}
		else if (epackage == SuperPackagePackage.eINSTANCE)
			switch (semanticObject.eClass().getClassifierID()) {
			case SuperPackagePackage.SUPER_MAIN:
				sequence_SuperMain(context, (SuperMain) semanticObject); 
				return; 
			}
		if (errorAcceptor != null)
			errorAcceptor.accept(diagnosticProvider.createInvalidContextOrTypeDiagnostic(semanticObject, context));
	}
	
	/**
	 * <pre>
	 * Contexts:
	 *     AnotherSuperMain returns AnotherSuperMain
	 *
	 * Constraint:
	 *     name=ID
	 * </pre>
	 */
	protected void sequence_AnotherSuperMain(ISerializationContext context, AnotherSuperMain semanticObject) {
		if (errorAcceptor != null) {
			if (transientValues.isValueTransient(semanticObject, SuperPackagePackage.Literals.ANOTHER_SUPER_MAIN__NAME) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, SuperPackagePackage.Literals.ANOTHER_SUPER_MAIN__NAME));
		}
		SequenceFeeder feeder = createSequencerFeeder(context, semanticObject);
		feeder.accept(grammarAccess.getAnotherSuperMainAccess().getNameIDTerminalRuleCall_1_0(), semanticObject.getName());
		feeder.finish();
	}
	
	
	/**
	 * <pre>
	 * Contexts:
	 *     SubMain returns SubMain
	 *
	 * Constraint:
	 *     (superMains+=SuperMain another=AnotherSuperMain?)
	 * </pre>
	 */
	protected void sequence_SubMain(ISerializationContext context, SubMain semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
}
