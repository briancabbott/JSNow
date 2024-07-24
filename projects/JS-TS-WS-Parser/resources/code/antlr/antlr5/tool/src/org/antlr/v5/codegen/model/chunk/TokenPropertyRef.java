/*
 * Copyright (c) 2012-present The ANTLR Project. All rights reserved.
 * Use of this file is governed by the BSD 3-clause license that
 * can be found in the LICENSE.txt file in the project root.
 */

package org.antlr.v5.codegen.model.chunk;

import org.antlr.v5.codegen.model.decl.StructDecl;

/** */
public class TokenPropertyRef extends ActionChunk {
	public String label;

	public TokenPropertyRef(StructDecl ctx, String label) {
		super(ctx);
		this.label = label;
	}
}
