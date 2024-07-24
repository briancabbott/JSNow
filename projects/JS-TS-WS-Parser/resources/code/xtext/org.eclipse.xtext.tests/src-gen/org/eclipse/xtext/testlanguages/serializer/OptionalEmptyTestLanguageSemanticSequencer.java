/*******************************************************************************
 * Copyright (c) 2010, 2024 itemis AG (http://www.itemis.eu) and others.
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package org.eclipse.xtext.testlanguages.serializer;

import com.google.inject.Inject;
import java.util.Set;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.xtext.Action;
import org.eclipse.xtext.Parameter;
import org.eclipse.xtext.ParserRule;
import org.eclipse.xtext.serializer.ISerializationContext;
import org.eclipse.xtext.serializer.acceptor.SequenceFeeder;
import org.eclipse.xtext.serializer.sequencer.AbstractDelegatingSemanticSequencer;
import org.eclipse.xtext.serializer.sequencer.ITransientValueService.ValueTransient;
import org.eclipse.xtext.testlanguages.optionalEmpty.Greeting;
import org.eclipse.xtext.testlanguages.optionalEmpty.Model;
import org.eclipse.xtext.testlanguages.optionalEmpty.OptionalEmptyPackage;
import org.eclipse.xtext.testlanguages.services.OptionalEmptyTestLanguageGrammarAccess;

@SuppressWarnings("all")
public class OptionalEmptyTestLanguageSemanticSequencer extends AbstractDelegatingSemanticSequencer {

	@Inject
	private OptionalEmptyTestLanguageGrammarAccess grammarAccess;
	
	@Override
	public void sequence(ISerializationContext context, EObject semanticObject) {
		EPackage epackage = semanticObject.eClass().getEPackage();
		ParserRule rule = context.getParserRule();
		Action action = context.getAssignedAction();
		Set<Parameter> parameters = context.getEnabledBooleanParameters();
		if (epackage == OptionalEmptyPackage.eINSTANCE)
			switch (semanticObject.eClass().getClassifierID()) {
			case OptionalEmptyPackage.GREETING:
				sequence_Greeting(context, (Greeting) semanticObject); 
				return; 
			case OptionalEmptyPackage.MODEL:
				sequence_Model(context, (Model) semanticObject); 
				return; 
			}
		if (errorAcceptor != null)
			errorAcceptor.accept(diagnosticProvider.createInvalidContextOrTypeDiagnostic(semanticObject, context));
	}
	
	/**
	 * <pre>
	 * Contexts:
	 *     Greeting returns Greeting
	 *
	 * Constraint:
	 *     name=ID
	 * </pre>
	 */
	protected void sequence_Greeting(ISerializationContext context, Greeting semanticObject) {
		if (errorAcceptor != null) {
			if (transientValues.isValueTransient(semanticObject, OptionalEmptyPackage.Literals.GREETING__NAME) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, OptionalEmptyPackage.Literals.GREETING__NAME));
		}
		SequenceFeeder feeder = createSequencerFeeder(context, semanticObject);
		feeder.accept(grammarAccess.getGreetingAccess().getNameIDTerminalRuleCall_1_0(), semanticObject.getName());
		feeder.finish();
	}
	
	
	/**
	 * <pre>
	 * Contexts:
	 *     Model returns Model
	 *
	 * Constraint:
	 *     child=Greeting
	 * </pre>
	 */
	protected void sequence_Model(ISerializationContext context, Model semanticObject) {
		if (errorAcceptor != null) {
			if (transientValues.isValueTransient(semanticObject, OptionalEmptyPackage.Literals.MODEL__CHILD) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, OptionalEmptyPackage.Literals.MODEL__CHILD));
		}
		SequenceFeeder feeder = createSequencerFeeder(context, semanticObject);
		feeder.accept(grammarAccess.getModelAccess().getChildGreetingParserRuleCall_0(), semanticObject.getChild());
		feeder.finish();
	}
	
	
}
