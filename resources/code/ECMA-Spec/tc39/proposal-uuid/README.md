# ECMAScript proposal: JavaScript standard library UUID

**&#x26a0;&#xfe0f;&#x26a0;&#xfe0f; UPDATE 2021: This proposal is now being pursued at:
https://github.com/WICG/uuid &#x26a0;&#xfe0f;&#x26a0;&#xfe0f;**

Status: Stage 1

## Authors

- Benjamin Coe ([@bcoe](https://github.com/bcoe))
- Robert Kieffer ([@broofa](https://github.com/broofa))
- Christoph Tavan ([@ctavan](https://github.com/ctavan))

## Synopsis

The [JavaScript standard library][standard-library-proposal] UUID describes an API for generating
character encoded Universally Unique Identifiers (UUID) based on [IETF RFC 4122][rfc-4122],
available for import in JavaScript engines.

## Motivation

### UUID generation is an extremely common software requirement

The [`uuid` module](https://www.npmjs.com/package/uuid) on npm currently receives some
[64,000,000 monthly downloads](https://npm-stat.com/charts.html?package=uuid) and is relied on by
over 2,600,000 repositories (as of June 2019).

The ubiquitous nature of the `uuid` module demonstrates that UUID generation is a common
requirement for JavaScript software applications, making the functionality a good candidate for the
standard library.

### Developers "re-inventing the wheel" is potentially harmful

Developers who have not been exposed to RFC 4122 might naturally opt to invent their own approaches
to UUID generation, potentially using `Math.random()` (in [TIFU by using `Math.random()`][tifu]
there's an in-depth discussion of why a Cryptographically-Secure-Pseudo-Random-Number-Generator
(_CSPRNG_) should be used when generating UUIDs).

Introducing a UUID standard library, which dictates that a CSPRNG must be used, helps protect
developers from security pitfalls.

## Overview

### UUID API

The UUID standard library provides an API for generating RFC 4122 identifiers.

The only export of the UUID library that is initially supported is `randomUUID()`, a method which
implements the
[version 4 "Algorithm for Creating a UUID from Truly Random or Pseudo-Random Numbers"](https://tools.ietf.org/html/rfc4122#section-4.4),
and returns the string representation _(as described in RFC-4122)_.

```js
// We're not yet certain as to how the API will be accessed (whether it's in the global, or a
// future built-in module), and this will be part of the investigative process as we continue
// working on the proposal.
randomUUID(); // "52e6953d-edbe-4953-be2e-65ed3836b2f0"
```

### `Math.getRandomValues()`

`Math.getRandomValues()` exposes an identical API to the
[W3C `crypto.getRandomValues()`](https://www.w3.org/TR/WebCryptoAPI/#Crypto-method-getRandomValues)
recommendation. With the same guarantees, regarding the quality of randomness:

> Implementations should generate cryptographically random values using well-established
> cryptographic pseudo-random number generators seeded with high-quality entropy, such as from an
> operating-system entropy source (e.g., "/dev/urandom"). This specification provides no
> lower-bound on the information theoretic entropy present in cryptographically random values, but
> implementations should make a best effort to provide as much entropy as practicable.
>
> - [WebCryptoAPI 10.1. Description](https://www.w3.org/TR/WebCryptoAPI/#Crypto-description)

`Math.getRandomValues()` will act as the foundation for implementing UUID algorithms, providing a
single mockable (see [#25](https://github.com/tc39/proposal-uuid/issues/25)) source of randomness.

## Out of scope

Algorithms described in RFC 4122 other than version 4 are not initially supported.

Statistics we've collected ([see analysis/README.md](./analysis/README.md)) indicate that the
version 4 algorithm is most widely used:

| Algorithm Version | Repo Count | %     | Weighted by Watch Count | %     |
| ----------------- | ---------- | ----- | ----------------------- | ----- |
| v4                | 4315       | 77.0% | 149802                  | 89.5% |
| v1                | 1228       | 21.9% | 16219                   | 9.7%  |
| v5                | 51         | 0.9%  | 1290                    | 0.8%  |
| v3                | 11         | 0.2%  | 116                     | 0.1%  |

### Regarding other UUID versions

While there is utility in other UUID versions, we are advocating starting with a minimal API
surface that supports a large percentage of users _(the string representation of version 4 UUIDs)._

If research and/or user feedback later indicates that additional functionality, such as versions 1,
3, and 5 UUIDs, would add value, this proposal does not preclude these additions.

## Use cases

How do folks in the community use RFC 4122 UUIDs?

### Creating unique keys for database entries

### Generating fake testing data

### Writing to temporary files

## FAQ

### What are the advantages to uuid being in the standard library?

- The `uuid` module is relied on by `> 2,600,000` repos on GitHub (June 2019). Guaranteeing a
  secure, consistent, well-maintained UUID implementation provides value to millions of developers.
- The 12 kb `uuid` module is downloaded from npm `> 62,000,000` times a month (June 2019); making
  it available in the standard library eventually saves TBs of bandwidth globally. If we continue
  to address user needs, such as `uuid`, with the standard library, bandwidth savings add up.

### How unique are v4 UUIDs?

If you ignore the
[challenges involved in random number generation](https://hackaday.com/2017/11/02/what-is-entropy-and-how-do-i-get-more-of-it/),
then v4 UUIDs are unique enough for all but the most stringent use cases. For example, the odds of
a collision among 3.3 quadrillion version 4 UUIDs (equivalent to generating a million UUIDs/second
for 104 years) is roughly one in a million (p = 0.000001).
[Source](https://en.wikipedia.org/wiki/Universally_unique_identifier#Collisions).

That said, the quality of the random number generator is vital to uniqueness. Flawed RNG
implementations have led to
[UUID collisions in real-world systems](https://github.com/bcoe/proposal-standard-library-uuid/issues/20).
It is for this reason that this spec mandates that any random numbers used come from a
"cryptographically secure" source, thereby (hopefully) avoiding such issues.

### Why call the export `randomUUID()` and not something like `uuidV4()`?

As pointed out
[in the disucssion](https://github.com/tc39/proposal-uuid/issues/3#issuecomment-544173041) `v4`
UUIDs have the maximum amount of entropy possible for a valid UUID as defined in [IETF RFC
4122][rfc-4122].

UUIDs defined in [IETF RFC 4122][rfc-4122] are 128 bit numbers that follow a specific byte layout.
All of them contain a "version" field comprising 4 bits and a "variant" field comprising 2 bits,
meaning that 6 out of 128 bits are reserved for meta information.

Since `v4` UUIDs are defined to have all remaining 122 bits set to random values, there cannot be
another UUID version that would contain more randomness.

While any name involving `v4` requires a rather deep understanding of the intricate meaning of the
term "version" in the context of the UUID spec, the term `randomUUID()` appears to be much more
descriptive for `v4` UUIDs.

### Aren't v1 UUIDs better because they are guaranteed to be unique?

As an oversimplification, `v1` UUIDs consist of two parts: A high-precision `timestamp` and a
`node` id. [IETF RFC 4122][rfc-4122] contains several requirements that are supposed to ensure that
the resulting `v1` UUIDs are unique.

- The timestamp has 100 nanosecond resolution and implementations are
  [required to throw an error or stall](https://tools.ietf.org/html/rfc4122#section-4.2.1.2) on
  attempts to generate UUIDs at a rate higher than 10M/second on a single `node`. Realistically
  that's only enforceable within a single thread/process on a single host. Enforcing this across
  multiple processes / hosts requires non-trivial architectures that run counter to the
  [main thesis the UUID spec](https://tools.ietf.org/html/rfc4122#section-2): _"One of the main
  reasons for using UUIDs is that no centralized authority is required to administer them"._
- The mechanism for generating `node` values preferred by the RFC is to use the host system's IEEE
  802 MAC address. This made sense back when the RFC was authored and MAC addresses could
  reasonably be expected to be unique, but this is arguably no longer the case, not with the
  proliferation of virtual machines and containers where MAC addresses may not be unique
  [_by design_](https://stackoverflow.com/a/42947044).

So in practice, modern implementations will generate a random 48 bit `node` value each time a
process is started leaving a probability of 1 in 2<sup>48</sup> for collisions in the `node` part.
In the unlikely event of such a collision
[it would take only 75 milliseconds](https://github.com/bcoe/proposal-standard-library-uuid/issues/15#issuecomment-522415349)
for a duplicate `v1` UUID to appear when generating UUIDs at a rate of 1M/second. So while also
unlikely, [just like with `v4` UUIDs](#how-unique-are-v4-uuids) there is no practical guarantee
that `v1` UUIDs are unique.

### Are there privacy concerns related to v1 UUIDs?

If implementations follow
[the primary recommendations of RFC 4122](https://tools.ietf.org/html/rfc4122#section-4.1.6) then
`v1` UUIDs would indeed leak the hardware MAC address of the machine where they are being created.
[As discussed above](#but-arent-v1-uuids-better-because-they-are-guaranteed-to-be-unique) this
would most likely not be the case in modern JavaScript implementations where hardware MAC addresses
are either unavailable (browser, serverless functions) or not necessarily unique
([containers](https://stackoverflow.com/a/42947044)). However, there are
[rumors that the presence of the MAC address lead to the arrest of the authors of the Melissa Virus](https://news.ycombinator.com/item?id=8575606)
and according to the manual even
[MySQL 8.0 still uses the hardware MAC address on some operating systems](https://dev.mysql.com/doc/refman/8.0/en/miscellaneous-functions.html#function_uuid).

In any case the exact creation time of any `v1` UUID will be contained within the UUID. This alone
can be a privacy or data protection concern for many use cases (e.g. leaking the creation timestamp
of a user account) so it's yet another reason to be very careful when choosing to use `v1` UUIDs.

### How do other languages/libraries deal with UUIDs?

Some other languages/libraries use the term "random" to describe version 4 UUIDs as well
([go](https://godoc.org/github.com/google/uuid#NewRandom),
[Java](<https://docs.oracle.com/javase/10/docs/api/java/util/UUID.html#randomUUID()>),
[C++ Boost](https://www.boost.org/doc/libs/1_71_0/boost/uuid/random_generator.hpp)).

Apart from that, UUID adoption across other languages/libraries seems to be rather inconsistent:

- [deno](https://github.com/denoland/deno/tree/master/std/uuid) added UUID to their standard
  library, leaving out `v3`. The code for UUID creation is essentially copied from the
  [`uuid` npm module](https://github.com/uuidjs/uuid), hence method naming follows the `vX` scheme.
- [Java](https://docs.oracle.com/javase/10/docs/api/java/util/UUID.html) provides methods for
  generating
  `v3`([`UUID.nameUUIDFromBytes()`](<https://docs.oracle.com/javase/10/docs/api/java/util/UUID.html#nameUUIDFromBytes(byte%5B%5D)>))
  and `v4`
  ([`UUID.randomUUID()`](<https://docs.oracle.com/javase/10/docs/api/java/util/UUID.html#randomUUID()>))
  UUIDs but not `v1` or `v5`. It would be interesting to investigate further as to why these
  algorithms were chosen, given that on the one hand time-based UUIDs (`v1`) appear to have much
  broader use than name-based (`v3`/`v5`) UUIDs and that on the other hand for name-based UUIDs the
  [RFC already recommends `v5` over `v3`](https://tools.ietf.org/html/rfc4122#section-4.3).
- [C++ Boost](https://www.boost.org/doc/libs/1_71_0/libs/uuid/doc/uuid.html#boost/uuid/name_generator.hpp)
  defaults to `v5` over `v3` for name-based UUIDs but in its implementation anticipates that `v5`
  (which uses SHA-1 for hashing) will be followed up by a newer name-based UUID version which will
  use a different hashing algorithm ("In anticipation of a new RFC for uuid arriving…").
- [Google's implementation for go](https://godoc.org/github.com/google/uuid#NewUUID) has chosen
  `v1` to be the "default" export whose generator method is called `NewUUID()`, whereas the other
  exposed methods have names closer to the abstraction we propose: `NewRandom()` for `v4`,
  `NewMD5()` for `v3`, `NewSHA1()` for `v5`.
- [Python](https://docs.python.org/3/library/uuid.html) provides methods for generating UUIDs named
  after the version for all 4 versions (`uuid.uuid1()`, `uuid.uuid3()`, `uuid.uuid4()` and
  `uuid.uuid5()`) plus a `UUID` class to represent UUIDs and transform them into various
  representations.
- [Rust](https://docs.rs/uuid/latest/uuid/) provides methods for generating UUIDs named after the
  version for all 4 versions (`Uuid::new_v1()`, `Uuid::new_v3()`, `Uuid::new_v4()` and
  `Uuid::new_v5()`) as static members of a `Uuid` class which is used to represent UUIDs and
  transform them into various representations.

## TODO

- [x] Identify champion to advance addition (stage-1)
- [x] Prose outlining the problem or need and general shape of the solution (stage-1)
- [x] Illustrative examples of usage (stage-1)
- [x] High-level API (stage-1)
- [ ] Initial spec text (stage-2)
- [ ] Babel plugin (stage-2)
- [ ] Finalize and reviewer sign-off for spec text (stage-3)
- [ ] Test262 acceptance tests (stage-4)
- [ ] tc39/ecma262 pull request with integrated spec text (stage-4)
- [ ] Reviewer sign-off (stage-4)

## References

- [IETF RFC 4122][rfc-4122]
- [JavaScript Standard Library Proposal][standard-library-proposal]
- [TIFU by using `Math.random()`][tifu]
- [Cryptographically secure pseudorandom number generator][csprng]

[rfc-4122]: https://tools.ietf.org/html/rfc4122
[standard-library-proposal]: https://github.com/tc39/proposal-javascript-standard-library
[tifu]: https://medium.com/@betable/tifu-by-using-math-random-f1c308c4fd9d
[csprng]: https://en.wikipedia.org/wiki/Cryptographically_secure_pseudorandom_number_generator
