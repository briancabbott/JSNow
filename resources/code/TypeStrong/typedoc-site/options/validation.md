---
layout: "guide"
tags: guide
eleventyNavigation:
    order: 5
    key: Validation
    parent: Options
---

# Validation Options

Options that control how TypeDoc validates your documentation.

## validation

CLI:

```bash
$ typedoc --validation.invalidLink
$ typedoc --validation
```

typedoc.json (defaults):

```json
{
    "validation": {
        "notExported": true,
        "invalidLink": true,
        "notDocumented": false
    }
}
```

Specifies validation steps TypeDoc should perform on your generated documentation.

## treatWarningsAsErrors

```bash
$ typedoc --treatWarningsAsErrors
```

Causes TypeDoc to treat any reported warnings as fatal errors that can prevent documentation from being generated.

## treatValidationWarningsAsErrors

```bash
$ typedoc --treatValidationWarningsAsErrors
```

Limited version of `treatWarningsAsErrors` that only applies to warnings emitted during validation of a project.
This option cannot be used to turn `treatWarningsAsErrors` off for validation warnings.

## intentionallyNotExported

Lists symbols which are intentionally excluded from the documentation output and should not produce warnings.
Entries may optionally specify a file name before a colon to only suppress warnings for symbols declared in a specific file.

typedoc.json:

```json
{
    "intentionallyNotExported": ["InternalClass", "src/other.ts:OtherInternal"]
}
```

## requiredToBeDocumented

Set the list of reflection types that must be documented, used by `validation.notDocumented`

The full list of available values are below, with entries not required by default commented out.

typedoc.json:

```json
{
    "requiredToBeDocumented": [
        //"Project",
        //"Module",
        //"Namespace",
        "Enum",
        "EnumMember",
        "Variable",
        "Function",
        "Class",
        "Interface",
        //"Constructor",
        "Property",
        "Method",
        // Implicitly set if function/method is set (this means you can't require docs on methods, but not functions)
        // This exists because methods/functions can have multiple signatures due to overloads, and TypeDoc puts comment
        // data on the signature. This might be improved someday, so you probably shouldn't set this directly.
        //    "CallSignature",
        // Index signature { [k: string]: string } "properties"
        //    "IndexSignature",
        // Equivalent to Constructor due to the same implementation detail as CallSignature
        //    "ConstructorSignature",
        //"Parameter",
        // Used for object literal types. You probably should set TypeAlias instead, which refers to types created with `type X =`.
        // This only really exists because of an implementation detail.
        //    "TypeLiteral",
        //"TypeParameter",
        "Accessor", // shorthand for GetSignature + SetSignature
        //   "GetSignature",
        //    "SetSignature",
        "TypeAlias",
        // TypeDoc creates reference reflections if a symbol is exported from a package with multiple names. Most projects
        // won't have any of these, and they just render as a link to the canonical name.
        //    "Reference",
    ]
}
```
