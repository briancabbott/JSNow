/*
 * Copyright (c) 2012-present The ANTLR Project. All rights reserved.
 * Use of this file is governed by the BSD 3-clause license that
 * can be found in the LICENSE.txt file in the project root.
 */

package org.antlr.v5.codegen.model.decl;

import org.antlr.v5.codegen.OutputModelFactory;

/** */
public class RuleContextDecl extends Decl {
	public String ctxName;
	public boolean isImplicit;

	public RuleContextDecl(OutputModelFactory factory, String name, String ctxName) {
		super(factory, name);
		this.ctxName = ctxName;
	}
}
