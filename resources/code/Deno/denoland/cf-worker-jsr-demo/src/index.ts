import * as JSONC from '@jsr/std__jsonc';
import { escape } from '@jsr/std__html';

export interface Env {}

// curl -X POST --data-binary @file.jsonc http://localhost:8787/
export default {
	async fetch(req: Request, env: Env, ctx: ExecutionContext): Promise<Response> {
		const form = await req.formData();
		const input = Array.from(form.keys())[0];

		try {
			JSONC.parse(input);
		} catch (err) {
			return new Response(`<h1>Invalid: ${escape(err.message)}</h1>`);
		}
		return new Response('<h1>valid</h1>');
	},
};
