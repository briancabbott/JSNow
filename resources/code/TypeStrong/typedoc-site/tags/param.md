---
layout: "guide"
tags: tag
eleventyNavigation:
    key: "@param"
    parent: Tags
    order: 24
---

# @param

{% include 'tag-info', kind: 'Block', tsdoc: 'param' %}

The `@param` tag is used to document a parameter of a function or method.

## Example

```ts
/**
 * @param a - the first number
 * @param b - the second number
 */
export function sum(a: number, b: number) {
    return a + b;
}
```

## Object Literals

If the type of a parameter is an object literal type, a union containing an object literal type,
or an intersection containing an object literal type, property names may be separated by `.` to
add a comment to a single level of a nested property.

```ts
/**
 * @param options - documentation for the whole parameter
 * @param options.value - documentation for the `value` property
 * @param options.nested.value - NOT supported
 */
export function configure(
    options: { value: string; nested: { value: string } } | undefined
) {}
```

## Destructured Parameters

If your function uses destructured parameters, TypeDoc will attempt to infer the parameter name from
your usage of the `@param` tag. For it to be successful, all parameters must be documented. If TypeDoc
fails to infer the name of the destructured parameter, it will be documented as `__namedParameters`.

```ts
/**
 * @param options - docs
 */
export function configure({ value }: { value: string }) {}

// will be documented as if written as
export function configure(options: { value: string }) {}
```

## TSDoc Compatibility

The TSDoc standard requires that the `@param` tag _not_ include types and that the parameter name must be followed
by a hyphen to separate it from the description. For improved compatibility with projects using TypeScript type
annotations in JavaScript files and the [JSDoc `@param` tag](https://jsdoc.app/tags-param.html), TypeDoc does
not enforce these requirements. The following `@param` tags will all be passed in the same way by TypeDoc.

```ts
/**
 * @param test - description
 * @param test description
 * @param {string} test - description
 * @param {string} test description
 */
```
