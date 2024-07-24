---
layout: "guide"
tags: guide
eleventyNavigation:
    order: 3
    key: Comments
    parent: Options
---

# Comment Options

These options control how TypeDoc parses comments.

## commentStyle

```bash
$ typedoc --commentStyle block
```

Determines what comment types TypeDoc will use. Note: Writing non-JSDoc comments will cause poorer
intellisense in VSCode and is therefore generally not recommended.

| Value           | Behavior                               |
| --------------- | -------------------------------------- |
| jsdoc (default) | Use block comments starting with `/**` |
| block           | Use all block comments                 |
| line            | Use `//` comments                      |
| all             | Use both block and line comments       |

## useTsLinkResolution

```bash
$ typedoc --useTsLinkResolution false
```

Indicates that `{@link}` tags should be resolved with TypeScript's parsing rules. This is on by default.

## preserveLinkText

```bash
$ typedoc --preserveLinkText false
```

Indicates whether or not `{@link}` tags should include just the name of the target reflection, or the original link text. This is on by default.

## jsDocCompatibility

CLI:

```bash
$ typedoc --jsDocCompatibility false
$ typedoc --jsDocCompatibility.defaultTags false
```

typedoc.json (defaults):

```json
{
    "jsDocCompatibility": {
        "exampleTags": true,
        "defaultTags": true,
        "inheritDocTag": true,
        "ignoreUnescapedBraces": true
    }
}
```

JSDoc specifies that the `@example` and `@default` tags indicate that the following content should be parsed
as code. This conflicts with the TSDoc standard. With this option on, TypeDoc will attempt to infer from the
tag content whether it should be parsed as code by checking if the tag content contains a code block.

TSDoc specifies that `@inheritdoc` should be spelled with a capitalized `D`, `@inheritDoc`. If `inheritDocTag`
is set to `false`, TypeDoc will produce a warning when rewriting `@inheritdoc` to `@inheritDoc`.

TSDoc specifies that braces (`{}`) must be escaped within comments to avoid ambiguity between the start of
an inline tag and a brace to be included in the rendered text. TypeDoc's `ignoreUnescapedBraces` option
determines if warnings are emitted if a brace is found within regular comment text without being escaped.

## blockTags

```json
// typedoc.json
{
    "blockTags": ["@param", "@returns"]
}
```

Override TypeDoc's supported block tags, emit warnings for any tags not listed here.
This option will be set by `tsdoc.json` if present.

## inlineTags

```json
// typedoc.json
{
    "inlineTags": ["@link"]
}
```

Override TypeDoc's supported inline tags, emit warnings for any tags not listed here.
This option will be set by `tsdoc.json` if present.

## modifierTags

```json
// typedoc.json
{
    "modifierTags": ["@hidden", "@packageDocumentation"]
}
```

Override TypeDoc's supported modifier tags, emit warnings for any tags not listed here.
This option will be set by `tsdoc.json` if present.

## cascadedModifierTags

```json
// typedoc.json
{
    "modifierTags": ["@alpha", "@beta", "@experimental"]
}
```

Specifies modifier tags which should be copied to all children of the parent reflection.

Note: `@deprecated` is a block tag, not a modifier tag, so should not be specified here.

## excludeTags

```bash
$ typedoc --excludeTags apidefine
```

Specify tags that should be removed from doc comments when parsing.
Useful if your project uses [apiDoc](https://apidocjs.com/) for documenting RESTful web APIs.

## externalSymbolLinkMappings

```json
// typedoc.json
{
    // format: { [packageName: string]: { [exportName: string]: string } }
    "externalSymbolLinkMappings": {
        // {@link typescript!Partial} will use this link as well as
        // type Foo = Partial<Bar>
        "typescript": {
            "Partial": "https://www.typescriptlang.org/docs/handbook/utility-types.html#partialtype"
        }
    }
}
```

Can be used to specify locations of externally defined types. If the external library uses namespaces,
qualify the name with `.` as a separator. These definitions will be used for both types linked to by
the user via a `{@link}` tag and in code.

TypeDoc assumes that if a symbol was referenced from a package, it was exported from that package.
This will be true for most native TypeScript packages, but packages which rely on `@types` will be linked
according to the `@types` package, not the original module name. If both are intended to be supported,
both packages must be listed.

```json
// typedoc.json
{
    "externalSymbolLinkMappings": {
        // used by `class Foo extends Component {}`
        "@types/react": {
            "Component": "https://reactjs.org/docs/react-component.html"
        },
        // used by {@link react!Component}
        "react": {
            "Component": "https://reactjs.org/docs/react-component.html"
        }
    }
}
```

Global external types are supported, but may have surprising behavior. Types which are defined in the TypeScript
lib files (including `Array`, `Promise`, ...) will be detected as belonging to the `typescript` package rather than
the special `global` package reserved for global types.

```json
// typedoc.json
{
    "externalSymbolLinkMappings": {
        // used by {@link !Promise}
        "global": {
            "Promise": "https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Global_Objects/Promise"
        },
        // used by type Foo = Promise<string>
        "typescript": {
            "Promise": "https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Global_Objects/Promise"
        }
    }
}
```
