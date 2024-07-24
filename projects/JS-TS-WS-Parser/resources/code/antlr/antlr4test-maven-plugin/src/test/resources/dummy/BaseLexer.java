/*
 [The "BSD license"]
 Copyright (c) 2020 Gil Cesar Faria
 All rights reserved.

 Redistribution and use in source and binary forms, with or without
 modification, are permitted provided that the following conditions
 are met:
 1. Redistributions of source code must retain the above copyright
    notice, this list of conditions and the following disclaimer.
 2. Redistributions in binary form must reproduce the above copyright
    notice, this list of conditions and the following disclaimer in the
    documentation and/or other materials provided with the distribution.
 3. The name of the author may not be used to endorse or promote products
    derived from this software without specific prior written permission.

 THIS SOFTWARE IS PROVIDED BY THE AUTHOR ``AS IS'' AND ANY EXPRESS OR
 IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES
 OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED.
 IN NO EVENT SHALL THE AUTHOR BE LIABLE FOR ANY DIRECT, INDIRECT,
 INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT
 NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,
 DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY
 THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF
 THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package dummy;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Lexer;

public abstract class BaseLexer extends Lexer {

	public enum Mode {
		None, IgnoreSpace
	}

	private Mode mode = Mode.None;

	public BaseLexer() {
	}

	public BaseLexer(CharStream input) {
		super(input);
		this.mode = Mode.None;
	}

	public void setMode(Mode mode) {
		this.mode = mode;
	}
	
	public boolean isIgnoreSpaceModeActive() {
		return mode == Mode.IgnoreSpace;
	}

	public int checkTypeID(int proposed) {
		int i = 1;
		if (isIgnoreSpaceModeActive()) {
			int input = _input.LA(i);
			while ((char) input == ' ' || (char) input == '\t' || (char) input == '\r' || (char) input == '\n') {
				i++;
				input = _input.LA(i);
			}
		}
		return (char) _input.LA(i) == '(' ? proposed : TestGrammarLexer.ID;
	}

}
