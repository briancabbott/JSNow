---
layout: "guide"
tags: tag
eleventyNavigation:
    key: "@typeParam"
    parent: Tags
    order: 38
---

# @typeParam

{% include 'tag-info', kind: 'Block', tsdoc: 'typeParam' %}

The `@typeParam` tag is used to document a type parameter of a function, method, class, interface or type alias.
TypeDoc recognizes the `@template` tag as an alias of `@typeParam`.

## Example

```ts
/**
 * @typeParam T - the identity type
 */
export function identity<T>(x: T): T {
    return x;
}
```

## TSDoc Compatibility

The TSDoc standard requires that the `@param` tag _not_ include types and that the parameter name must be followed
by a hyphen to separate it from the description. For improved compatibility with projects using TypeScript type
annotations in JavaScript files, TypeDoc does not enforce these requirements. The following `@typeParam` tags will
all be passed in the same way by TypeDoc.

```ts
/**
 * @typeParam test - description
 * @typeParam test description
 * @typeParam {string} test - description
 * @typeParam {string} test description
 */
```

## See Also

-   The [`@template`](/tags/template/) tag
