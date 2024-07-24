/*******************************************************************************
 * Copyright (c) 2010, 2024 itemis AG (http://www.itemis.eu) and others.
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package org.eclipse.xtext.parser.unorderedGroups.serializer;

import com.google.inject.Inject;
import java.util.Set;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.xtext.Action;
import org.eclipse.xtext.Parameter;
import org.eclipse.xtext.ParserRule;
import org.eclipse.xtext.parser.unorderedGroups.backtrackingBug325745TestLanguage.BacktrackingBug325745TestLanguagePackage;
import org.eclipse.xtext.parser.unorderedGroups.backtrackingBug325745TestLanguage.DataType;
import org.eclipse.xtext.parser.unorderedGroups.backtrackingBug325745TestLanguage.Element;
import org.eclipse.xtext.parser.unorderedGroups.backtrackingBug325745TestLanguage.Expression;
import org.eclipse.xtext.parser.unorderedGroups.backtrackingBug325745TestLanguage.Model;
import org.eclipse.xtext.parser.unorderedGroups.backtrackingBug325745TestLanguage.SimpleTerm;
import org.eclipse.xtext.parser.unorderedGroups.services.BacktrackingBug325745TestLanguageGrammarAccess;
import org.eclipse.xtext.serializer.ISerializationContext;
import org.eclipse.xtext.serializer.sequencer.AbstractDelegatingSemanticSequencer;

@SuppressWarnings("all")
public abstract class AbstractBacktrackingBug325745TestLanguageSemanticSequencer extends AbstractDelegatingSemanticSequencer {

	@Inject
	private BacktrackingBug325745TestLanguageGrammarAccess grammarAccess;
	
	@Override
	public void sequence(ISerializationContext context, EObject semanticObject) {
		EPackage epackage = semanticObject.eClass().getEPackage();
		ParserRule rule = context.getParserRule();
		Action action = context.getAssignedAction();
		Set<Parameter> parameters = context.getEnabledBooleanParameters();
		if (epackage == BacktrackingBug325745TestLanguagePackage.eINSTANCE)
			switch (semanticObject.eClass().getClassifierID()) {
			case BacktrackingBug325745TestLanguagePackage.DATA_TYPE:
				sequence_DataType(context, (DataType) semanticObject); 
				return; 
			case BacktrackingBug325745TestLanguagePackage.ELEMENT:
				sequence_Element(context, (Element) semanticObject); 
				return; 
			case BacktrackingBug325745TestLanguagePackage.EXPRESSION:
				sequence_Expression(context, (Expression) semanticObject); 
				return; 
			case BacktrackingBug325745TestLanguagePackage.MODEL:
				sequence_Model(context, (Model) semanticObject); 
				return; 
			case BacktrackingBug325745TestLanguagePackage.SIMPLE_TERM:
				sequence_SimpleTerm(context, (SimpleTerm) semanticObject); 
				return; 
			}
		if (errorAcceptor != null)
			errorAcceptor.accept(diagnosticProvider.createInvalidContextOrTypeDiagnostic(semanticObject, context));
	}
	
	/**
	 * <pre>
	 * Contexts:
	 *     DataType returns DataType
	 *
	 * Constraint:
	 *     (baseType=ID defaultValue=STRING?)
	 * </pre>
	 */
	protected void sequence_DataType(ISerializationContext context, DataType semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * <pre>
	 * Contexts:
	 *     Element returns Element
	 *
	 * Constraint:
	 *     (name=ID dataType=DataType? expression=Expression)
	 * </pre>
	 */
	protected void sequence_Element(ISerializationContext context, Element semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * <pre>
	 * Contexts:
	 *     Expression returns Expression
	 *
	 * Constraint:
	 *     (prefix=STRING* terms+=SimpleTerm* postfix=STRING*)
	 * </pre>
	 */
	protected void sequence_Expression(ISerializationContext context, Expression semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * <pre>
	 * Contexts:
	 *     Model returns Model
	 *
	 * Constraint:
	 *     fields+=Element+
	 * </pre>
	 */
	protected void sequence_Model(ISerializationContext context, Model semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * <pre>
	 * Contexts:
	 *     SimpleTerm returns SimpleTerm
	 *
	 * Constraint:
	 *     ((lineCount=INT charCount=INT? charSet=ID?) | refChar=ID)
	 * </pre>
	 */
	protected void sequence_SimpleTerm(ISerializationContext context, SimpleTerm semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
}
