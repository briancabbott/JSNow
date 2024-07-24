## Promise.prototype.finally ( _onFinally_ )

When the `finally` method is called with argument _onFinally_, the following steps are taken:
  1. Let _promise_ be the **this** value.
  1. If <a href="https://tc39.github.io/ecma262/#sec-ecmascript-data-types-and-values">Type</a>(_promise_) is not Object, throw a *TypeError* exception.
  1. Assert: <a href="https://tc39.github.io/ecma262/#sec-isconstructor">IsConstructor</a>(_C_) is *true*.
    1. If <a href="https://tc39.github.io/ecma262/#sec-iscallable">IsCallable</a>(_onFinally_) is *false*,
      1. Let _thenFinally_ be _onFinally_.
      1. Let _catchFinally_ be _onFinally_.
    1. Else,
      1. Let _thenFinally_ be a new built-in function object as defined in <a href="#sec-thenfinallyfunction">ThenFinally Function</a>.
      1. Let _catchFinally_ be a new built-in function object as defined in <a href="#sec-catchfinallyfunction">CatchFinally Function</a>.
      1. Set _thenFinally_ and _catchFinally_'s [[Constructor]] internal slots to _C_.
      1. Set _thenFinally_ and _catchFinally_'s [[OnFinally]] internal slots to _onFinally_.
  1. Return ? <a href="https://tc39.github.io/ecma262/#sec-invoke">Invoke</a>(_promise_, *"then"*, &laquo; _thenFinally_, _catchFinally_ &raquo;).

### ThenFinally Function

A ThenFinally function is an anonymous built-in function that has a [[Constructor]] and an [[OnFinally]] internal slot. The value of the [[Constructor]] internal slot is a `Promise`-like constructor function object, and the value of the [[OnFinally]] internal slot is a function object.

When a ThenFinally function _F_ is called with argument _value_, the following steps are taken:
  1. Let _onFinally_ be _F_.[[OnFinally]].
  1. Assert: <a href="https://tc39.github.io/ecma262/#sec-iscallable">IsCallable</a>(_onFinally_) is *true*.
  1. Let _result_ be ? Call(_onFinally_, *undefined*).
  1. Let _C_ be _F_.[[Constructor]].
  1. Assert: IsConstructor(_C_) is *true*.
  1. Let _promise_ be ? PromiseResolve(_C_, _result_).
  1. Let _valueThunk_ be equivalent to a function that returns _value_.
  1. Return ? Invoke(_promise_, `"then"`, &laquo; _valueThunk_ &raquo;).

### CatchFinally Function

A CatchFinally function is an anonymous built-in function that has a [[Constructor]] and an [[OnFinally]] internal slot. The value of the [[Constructor]] internal slot is a `Promise`-like constructor function object, and the value of the [[OnFinally]] internal slot is a function object.

When a CatchFinally function _F_ is called with argument _reason_, the following steps are taken:
  1. Let _onFinally_ be _F_.[[OnFinally]].
  1. Assert: <a href="https://tc39.github.io/ecma262/#sec-iscallable">IsCallable</a>(_onFinally_) is *true*.
  1. Let _result_ be ? Call(_onFinally_, *undefined*).
  1. Let _C_ be _F_.[[Constructor]].
  1. Assert: IsConstructor(_C_) is *true*.
  1. Let _promise_ be ? PromiseResolve(_C_, _result_).
  1. Let _thrower_ be equivalent to a function that throws _reason_.
  1. Return ? Invoke(_promise_, `"then"`, &laquo; _thrower_ &raquo;).

## Promise.resolve ( _x_ )

The `resolve` function returns either a new promise resolved with the passed argument, or the argument itself if the argument is a promise produced by this constructor.
  1. Let _C_ be the *this* value.
  1. If <a href="https://tc39.github.io/ecma262/#sec-ecmascript-data-types-and-values">Type</a>(_C_) is not Object, throw a *TypeError* exception.
  1. Return ? PromiseResolve(_C_, _x_).

Note: the `resolve` function expects its *this* value to be a constructor function that supports the parameter conventions of the `Promise` constructor.

## PromiseResolve ( _C_, _x_ )
The abstract operation PromiseResolve, given a constructor and a value, returns a new promise resolved with that value.
  1. Assert: <a href="https://tc39.github.io/ecma262/#sec-ecmascript-data-types-and-values">Type</a>(_C_) is Object.
  1. If <a href="http://www.ecma-international.org/ecma-262/6.0/#sec-ispromise">IsPromise</a>(_x_) is *true*, then
    1. Let _xConstructor_ be ? <a href="http://www.ecma-international.org/ecma-262/6.0/#sec-get-o-p">Get</a>(_x_, `"constructor"`).
    1. If SameValue(_xConstructor_, _C_) is *true*, return _x_.
  1. Let _promiseCapability_ be ? <a href="http://www.ecma-international.org/ecma-262/6.0/index.html#sec-newpromisecapability">NewPromiseCapability</a>(_C_).
  1. Perform ? <a href="http://www.ecma-international.org/ecma-262/6.0/index.html#sec-call">Call</a>(_promiseCapability_.[[Resolve]], *undefined*, &laquo; _x_ &raquo;).
  1. Return _promiseCapability_.[[Promise]].
