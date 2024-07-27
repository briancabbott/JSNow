> _This document has been archived._
>
> As part of creating the ECMAScript Modules implementation for Node.js 12.0.0, use cases were brainstormed, which led to a list of features. This feature list, which previously existed as a section in this repo's root [README](../../README.md), informed what became the [plan for the implementation](./plan-for-new-modules-implementation.md).

# Features

Based on [these use cases](./doc/use-cases.md) ([#55](https://github.com/nodejs/modules/issues/55)), our implementation aims to support the following features (subject to change):

### Baseline Modules Implementation Features:
* Spec compliance ([#132](https://github.com/nodejs/modules/issues/132))
* Browser equivalence ([#133](https://github.com/nodejs/modules/issues/133))
* Don’t break CommonJS ([#112](https://github.com/nodejs/modules/issues/112))
* No refactoring ([#87](https://github.com/nodejs/modules/issues/87))

#### Browser Interop:
* Browser and Node compatibility without building ([#107](https://github.com/nodejs/modules/issues/107))
* Browser-compatible specifier resolution ([#109](https://github.com/nodejs/modules/issues/109))
* Browser-compatible builds for ESM and CommonJS ([#108](https://github.com/nodejs/modules/issues/108))

#### CommonJS Interop:
* Named exports when importing CJS ([#81](https://github.com/nodejs/modules/issues/81))
* Multi-mode packages ([#94](https://github.com/nodejs/modules/issues/94))
* Transparent interoperability for ESM importing CommonJS ([#100](https://github.com/nodejs/modules/issues/100))
* Consumer-agnostic imports ([#105](https://github.com/nodejs/modules/issues/105))
* Mixed module types within app/module; gradual migration from CommonJS to ESM ([#99](https://github.com/nodejs/modules/issues/99))
* ESM in .js files ([#151](https://github.com/nodejs/modules/issues/151))

### Existing Node.js Utility Features:

* Importing non-JavaScript files ([#115](https://github.com/nodejs/modules/issues/115))
* NodeJS contextual pathing use cases ([#121](https://github.com/nodejs/modules/issues/121))
* ESM in executable files ([#152](https://github.com/nodejs/modules/issues/152))
* Callable resolver ([#157](https://github.com/nodejs/modules/issues/157))

### Loader Features:

* Code coverage/instrumentation ([#95](https://github.com/nodejs/modules/issues/95))
* Pluggable Loaders to support multiple use cases ([#82](https://github.com/nodejs/modules/issues/82))
* Runtime loaders, transpilation at import time ([#96](https://github.com/nodejs/modules/issues/96))
* Arbitrary sources for module source text ([#97](https://github.com/nodejs/modules/issues/97))
* Mock modules (injection) ([#98](https://github.com/nodejs/modules/issues/98))
* Specifier resolution customization ([#110](https://github.com/nodejs/modules/issues/110))
* Package encapsulation ([#111](https://github.com/nodejs/modules/issues/111))
* Conditional imports ([#113](https://github.com/nodejs/modules/issues/113))

### WASM Features:

* WASM modules (#[106](https://github.com/nodejs/modules/issues/106))

### Developer and Tooling Features:

* File / path / URL resolving (#[103](https://github.com/nodejs/modules/issues/103))
* Import CommonJS without needing asynchronous syntax (#[116](https://github.com/nodejs/modules/issues/116))
* Tree shaking (#[102](https://github.com/nodejs/modules/issues/102))
* Polyfillability (#[101](https://github.com/nodejs/modules/issues/101))
