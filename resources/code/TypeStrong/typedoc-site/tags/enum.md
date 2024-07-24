---
layout: "guide"
tags: tag
eleventyNavigation:
    key: "@enum"
    parent: Tags
    order: 6
---

# @enum

{% include 'tag-info', kind: 'Modifier' %}

If present on an object with string or number literal values, TypeDoc will convert the variable as an
enumeration instead of a variable.

## Example

```ts
/**
 * This will be displayed as an enumeration.
 * @enum
 */
export const MyEnum = {
    /**
     * Doc comments may be included here.
     */
    A: "a",
    B: "b",
} as const;

/**
 * This works too, but is more verbose
 * @enum
 */
export const MyEnum2: { A: "a" } = { A: "a" };

/**
 * So does this, for declaration files
 */
export declare const MyEnum3: { A: "a" };
```
